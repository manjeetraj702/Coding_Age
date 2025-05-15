package com.educore.controller.modules;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.MarkAttendanceRequest;
import com.educore.model.dto.requests.create.SessionCreateRequest;
import com.educore.model.dto.requests.create.StudentAttendanceReportRequest;
import com.educore.model.dto.response.SessionReport;
import com.educore.model.dto.response.StudentAttendanceReport;
import com.educore.model.entity.Session;
import com.educore.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Session>> createSession(@RequestBody SessionCreateRequest createRequest){
        ApiResponse<Session> response = sessionService.createSession(createRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

//    @GetMapping("/sessions/{batchId}")
//    public ResponseEntity<ApiResponse<Page<Session>>> fetchSessionsByBatchId(@PathVariable String batchId, @RequestParam int pageNo, @RequestParam int pageSize){
//        ApiResponse<Page<Session>> response = sessionService.fetchSessionsByBatchId(batchId, pageNo, pageSize);
//        return ResponseEntity.status(response.getStatus()).body(response);
//    }

    @GetMapping("/sessions/{batchId}")
    public ResponseEntity<ApiResponse<List<Session>>> fetchSessionsByBatchId(@PathVariable String batchId){
        ApiResponse<List<Session>> response = sessionService.fetchSessionsByBatchId(batchId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }


    @PutMapping("/mark-attendance")
    public ResponseEntity<ApiResponse<Boolean>> markAttendance(@RequestBody MarkAttendanceRequest attendanceRequest){
        ApiResponse<Boolean> response = sessionService.markAttendance(attendanceRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{sessionId}/practice-set")
    public ResponseEntity<ApiResponse<Boolean>> addPracticeSets(
            @PathVariable String sessionId, @RequestBody List<String> practiceSets){
        ApiResponse<Boolean> response = sessionService.addPracticeSets(sessionId, practiceSets);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{sessionId}/notes")
    public ResponseEntity<ApiResponse<Boolean>> addNotes(
            @PathVariable String sessionId, @RequestBody List<String> notes){
        ApiResponse<Boolean> response = sessionService.addNotes(sessionId, notes);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/attendance/report")
    public ResponseEntity<ApiResponse<StudentAttendanceReport>> studentAttendanceReport(
            @RequestBody StudentAttendanceReportRequest reportRequest){
        ApiResponse<StudentAttendanceReport> response = sessionService.studentAttendanceReport(reportRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/report/{batchId}")
    public ResponseEntity<ApiResponse<List<SessionReport>>> sessionReport(
            @PathVariable String batchId, @RequestParam LocalDate fromDate, @RequestParam LocalDate toDate){
        ApiResponse<List<SessionReport>> response = sessionService.sessionReport(batchId, fromDate, toDate);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
