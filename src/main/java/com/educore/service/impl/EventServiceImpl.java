package com.educore.service.impl;

import com.educore.common.utils.ApiResponse;
import com.educore.exception.ApplicationException;
import com.educore.model.dto.requests.create.EventCreateRequest;
import com.educore.model.dto.response.EventDto;
import com.educore.model.dto.response.QuizDto;
import com.educore.model.entity.Event;
import com.educore.model.enums.Role;
import com.educore.repository.EventRepository;
import com.educore.service.AdminService;
import com.educore.service.EmployeeService;
import com.educore.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ApiResponse<Event> createEvent(EventCreateRequest eventCreateRequest) {
        if(eventCreateRequest.getCreatorRole() == Role.EMPLOYEE){
            Optional.ofNullable(employeeService.findById(eventCreateRequest.getCreatedBy())).orElseThrow(
                    () -> new ApplicationException("User not found"));
        }else {
            Optional.ofNullable(adminService.findById(eventCreateRequest.getCreatedBy())).orElseThrow(
                    () -> new ApplicationException("User not found"));
        }
        Event event = new Event();
        event.setTitle(eventCreateRequest.getTitle());
        event.setDescription(eventCreateRequest.getDescription());
        event.setImageUrl(eventCreateRequest.getImageUrl());
        event.setCreatedBy(eventCreateRequest.getCreatedBy());
        event.setActive(true);
        event.setStartDate(eventCreateRequest.getStartDate());
        event.setEndDate(eventCreateRequest.getEndDate());
        event.setLocation(eventCreateRequest.getLocation());
        return new ApiResponse<>(eventRepository.save(event), HttpStatus.CREATED);
    }

    @Override
    public ApiResponse<List<EventDto>> fetchEvent() {
        LocalDateTime now = LocalDateTime.now();
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        List<Event> events = eventRepository.findByEndDateAfter(now, sort);
        List<EventDto> eventDTOs = events.stream()
                .map(event -> modelMapper.map(event, EventDto.class))
                .toList();
        return new ApiResponse<>(eventDTOs, HttpStatus.OK);
    }

    @Override
    public ApiResponse<Boolean> attemptEnquiry(String eventId, String userId) {
        Event event = Optional.ofNullable(findById(eventId)).orElseThrow(
                () -> new ApplicationException("Event not found"));
        event.getEnquirersId().add(userId);
        int number = event.getNoOfEnquirers() + 1;
        event.setNoOfEnquirers(number);
        eventRepository.save(event);
        return new ApiResponse<>(true, HttpStatus.OK);
    }

    @Override
    public Event findById(String id) {
        String replacedId = id.replaceAll("[{}]", "").trim();
        return eventRepository.findById(replacedId).orElse(null);
    }
}
