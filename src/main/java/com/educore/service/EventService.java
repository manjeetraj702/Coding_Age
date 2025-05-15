package com.educore.service;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.EventCreateRequest;
import com.educore.model.dto.response.EventDto;
import com.educore.model.entity.Event;

import java.util.List;

public interface EventService {

    ApiResponse<Event> createEvent(EventCreateRequest eventCreateRequest);

    ApiResponse<List<EventDto>> fetchEvent();

    ApiResponse<Boolean> attemptEnquiry(String eventId, String userId);

    Event findById(String id);
}
