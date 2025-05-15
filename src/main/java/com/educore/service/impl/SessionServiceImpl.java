package com.educore.service.impl;

import com.educore.common.utils.ApiResponse;
import com.educore.exception.ApplicationException;
import com.educore.model.dto.requests.create.MarkAttendanceRequest;
import com.educore.model.dto.requests.create.SessionCreateRequest;
import com.educore.model.dto.requests.create.StudentAttendanceReportRequest;
import com.educore.model.dto.response.DailyAttendanceReport;
import com.educore.model.dto.response.SessionReport;
import com.educore.model.dto.response.StudentAttendanceReport;
import com.educore.model.entity.*;
import com.educore.model.enums.AttendanceStatus;
import com.educore.model.enums.Role;
import com.educore.repository.SessionRepository;
import com.educore.service.AdminService;
import com.educore.service.BatchService;
import com.educore.service.EmployeeService;
import com.educore.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private BatchService batchService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public ApiResponse<Session> createSession(SessionCreateRequest createRequest) {
        Batch batch = Optional.ofNullable(batchService.findById(createRequest.getBatchId()))
                .orElseThrow(() -> new ApplicationException("Batch Not Found"));
        if(createRequest.getCreatorRole() == Role.EMPLOYEE){
            Employee employee = Optional.ofNullable(employeeService.findById(createRequest.getCreatorId()))
                    .orElseThrow(() -> new ApplicationException("Employee Not found"));
        }else {
            Admin admin = Optional.ofNullable(adminService.findById(createRequest.getCreatorId()))
                    .orElseThrow(() -> new ApplicationException("Admin Not found"));
        }
        Session session = new Session();
        session.setBatchId(createRequest.getBatchId());
        session.setTopic(createRequest.getTopic());
        session.setSessionDate(LocalDate.now());
        session.setCreatorId(createRequest.getCreatorId());
        session.setInstructorName(createRequest.getInstructorName());
        session.setStartTime(createRequest.getStartTime());
        session.setEndTime(createRequest.getEndTime());
        session.setCreatedAt(LocalDateTime.now());
        Session savedSession = sessionRepository.save(session);
        batchService.markSession(createRequest.getBatchId(), savedSession.getId());
        return new ApiResponse<>(savedSession, HttpStatus.CREATED);
    }

//    @Override
//    public ApiResponse<Page<Session>> fetchSessionsByBatchId(String batchId, int pageNo, int pageSize) {
//        Sort sort = Sort.by(Sort.Direction.DESC, "sessionDate");
//        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
//        return new ApiResponse<>(sessionRepository.findAllByBatchId(batchId, pageable), HttpStatus.OK);
//    }

    @Override
    public ApiResponse<List<Session>> fetchSessionsByBatchId(String batchId) {
        return new ApiResponse<>(sessionRepository.findAllByBatchId(batchId), HttpStatus.OK);
    }

    @Override
    public ApiResponse<Boolean> markAttendance(MarkAttendanceRequest attendanceRequest) {
        Session session = Optional.ofNullable(findById(attendanceRequest.getSessionId())).orElseThrow(
                () -> new ApplicationException("Session Not found"));
        if(!session.getAttendanceRecord().isEmpty()){
            return new ApiResponse<> (HttpStatus.CONFLICT, "Attendance Already Marked");
        }
        int noOfPresents = 0;
        int noOfAbsents = 0;
        for (Attendance attendance : attendanceRequest.getAttendances()){
            if(attendance.getStatus() == AttendanceStatus.Present){
                noOfPresents = noOfPresents + 1;
            }else if(attendance.getStatus() == AttendanceStatus.Absent){
                noOfAbsents = noOfAbsents + 1;
            }
        }
        session.setAttendanceRecord(attendanceRequest.getAttendances());
        session.setAttendanceMarkedBy(attendanceRequest.getMarkedBy());
        session.setNoOfAbsent(noOfAbsents);
        session.setNoOfPresent(noOfPresents);
        session.setUpdatedAt(LocalDateTime.now());
        sessionRepository.save(session);
        return new ApiResponse<>(true, HttpStatus.OK);
    }

    @Override
    public ApiResponse<Boolean> addPracticeSets(String sessionId, List<String> practiceSets) {
        Session session = Optional.ofNullable(findById(sessionId)).orElseThrow(
                () -> new ApplicationException("Session not found"));
        session.getPracticeSets().addAll(practiceSets);
        sessionRepository.save(session);
        return new ApiResponse<>(true, HttpStatus.OK);
    }

    @Override
    public ApiResponse<Boolean> addNotes(String sessionId, List<String> notes) {
        Session session = Optional.ofNullable(findById(sessionId)).orElseThrow(
                () -> new ApplicationException("Session not found"));
        session.getNotes().addAll(notes);
        sessionRepository.save(session);
        return new ApiResponse<>(true, HttpStatus.OK);
    }

    @Override
    public ApiResponse<StudentAttendanceReport> studentAttendanceReport(
            StudentAttendanceReportRequest reportRequest) {
        AggregationResults<DailyAttendanceReport> results = mongoTemplate.aggregate(Aggregation.newAggregation(
                Aggregation.match(Criteria.where("batchId").is(reportRequest.getBatchId())
                        .and("sessionDate").gte(reportRequest.getFromDate()).lt(reportRequest.getToDate())),
                Aggregation.unwind("attendanceRecord"),
                Aggregation.match(Criteria.where("attendanceRecord.studentId").is(reportRequest.getStudentId())),
                Aggregation.project("sessionDate")
                        .and("attendanceRecord.status").as("status")
                        .and("attendanceRecord.remarks").as("remarks")
        ), "sessions", DailyAttendanceReport.class);

        List<DailyAttendanceReport> dailyReports = results.getMappedResults();
        // Attendance Summary Calculation
        int totalSessions = dailyReports.size();
        int noOfPresents = (int) dailyReports.stream()
                .filter(r -> r.getStatus() == AttendanceStatus.Present)
                .count();
        int noOfAbsents = (int) dailyReports.stream()
                .filter(r -> r.getStatus() == AttendanceStatus.Absent)
                .count();
        int noOfLeaves = (int) dailyReports.stream()
                .filter(r -> r.getStatus() == AttendanceStatus.OnLeave)
                .count();

        StudentAttendanceReport attendanceReport = new StudentAttendanceReport(
                dailyReports, noOfAbsents, noOfPresents, noOfLeaves, totalSessions);
        return new ApiResponse<>(attendanceReport, HttpStatus.OK);
    }

    @Override
    public ApiResponse<List<SessionReport>> sessionReport(String batchId, LocalDate fromDate, LocalDate toDate) {
        AggregationResults<SessionReport> results = mongoTemplate.aggregate(Aggregation.newAggregation(
                Aggregation.match(Criteria.where("batchId").is(batchId)
                        .and("sessionDate").gte(fromDate).lte(toDate)),
                Aggregation.project("sessionDate", "instructorName", "topic", "noOfAbsent", "noOfPresent")
        ), "sessions", SessionReport.class);

        List<SessionReport> sessionReports = results.getMappedResults();
        return new ApiResponse<>(sessionReports, HttpStatus.OK);
    }

    @Override
    public Session findById(String id) {
        return sessionRepository.findById(id).orElse(null);
    }
}

