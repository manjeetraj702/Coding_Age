package com.educore.service;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.MarkAttendanceRequest;
import com.educore.model.dto.requests.create.SessionCreateRequest;
import com.educore.model.dto.requests.create.StudentAttendanceReportRequest;
import com.educore.model.dto.response.SessionReport;
import com.educore.model.dto.response.StudentAttendanceReport;
import com.educore.model.entity.Session;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface SessionService {
    ApiResponse<Session> createSession(SessionCreateRequest createRequest);

//    ApiResponse<Page<Session>> fetchSessionsByBatchId(String batchId, int pageNo, int pageSize);

    ApiResponse<List<Session>> fetchSessionsByBatchId(String batchId);

    ApiResponse<Boolean> markAttendance(MarkAttendanceRequest attendanceRequest);

    Session findById(String id);

    ApiResponse<StudentAttendanceReport> studentAttendanceReport(StudentAttendanceReportRequest reportRequest);

    ApiResponse<List<SessionReport>> sessionReport(String batchId, LocalDate fromDate, LocalDate toDate);

    ApiResponse<Boolean> addPracticeSets(String sessionId, List<String> practiceSets);

    ApiResponse<Boolean> addNotes(String sessionId, List<String> notes);
}
