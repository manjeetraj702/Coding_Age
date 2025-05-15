package com.educore.controller.modules;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.EventCreateRequest;
import com.educore.model.dto.response.EventDto;
import com.educore.model.entity.Event;
import com.educore.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Event>> createEvent(@RequestBody EventCreateRequest eventCreateRequest){
        ApiResponse<Event> response = eventService.createEvent(eventCreateRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/fetch")
    public ResponseEntity<ApiResponse<List<EventDto>>> fetchEvent(){
        ApiResponse<List<EventDto>> response = eventService.fetchEvent();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{eventId}/enquiry/{userId}")
    public ResponseEntity<ApiResponse<Boolean>> attemptEnquiry(
            @PathVariable String eventId, @PathVariable String userId){
        ApiResponse<Boolean> response = eventService.attemptEnquiry(eventId, userId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
