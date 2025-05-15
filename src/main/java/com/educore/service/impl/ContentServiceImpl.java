package com.educore.service.impl;

import com.educore.common.utils.ApiResponse;
import com.educore.common.utils.VideoUtils;
import com.educore.exception.ApplicationException;
import com.educore.model.dto.requests.create.ContentCreateRequest;
import com.educore.model.dto.response.ContentDTO;
import com.educore.model.entity.Content;
import com.educore.model.enums.ContentCategory;
import com.educore.model.enums.Role;
import com.educore.repository.ContentRepository;
import com.educore.service.AdminService;
import com.educore.service.ContentService;
import com.educore.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ApiResponse<ContentDTO> createContent(ContentCreateRequest createRequest) {
        if(createRequest.getCreatorRole() == Role.ADMIN){
            Optional.ofNullable(adminService.findById(createRequest.getCreatedBy())).orElseThrow(
                    () -> new ApplicationException("User not found"));
        }else {
            Optional.ofNullable(employeeService.findById(createRequest.getCreatedBy())).orElseThrow(
                    () -> new ApplicationException("User not found"));
        }
        ContentDTO content = getContent(createRequest);
        return new ApiResponse<>(content, HttpStatus.CREATED);
    }

    private ContentDTO getContent(ContentCreateRequest createRequest) {
        String thumbnailUrl;
        if(createRequest.getCategory() == ContentCategory.VIDEO){
            thumbnailUrl = VideoUtils.generateThumbnailUrl(createRequest.getContentUrl());
        }else{
            thumbnailUrl = null;
        }

        Content content = new Content();
        content.setCourseId(createRequest.getCourseId());
        content.setTopic(createRequest.getTopic());
        content.setCategory(createRequest.getCategory());
        content.setLevel(createRequest.getLevel());
        content.setTitle(createRequest.getTitle());
        content.setDescription(createRequest.getDescription());
        content.setContentUrl(createRequest.getContentUrl());
        content.setThumbnailUrl(thumbnailUrl);
        content.setPremium(createRequest.isPremium());
        content.setCreatedBy(createRequest.getCreatedBy());
        content = contentRepository.save(content);

        return new ContentDTO(
                content.getId(),
                content.getCourseId(),
                content.getTopic(),
                content.getCategory(),
                content.getLevel(),
                content.getTitle(),
                content.getDescription(),
                content.getContentUrl(),
                content.getThumbnailUrl(),
                content.isPremium()
        );
    }

    @Override
    public ApiResponse<List<ContentDTO>> fetchContents(String category) {
        ContentCategory enumCategory = ContentCategory.valueOf(category);
        List<Content> contentList = contentRepository.findAllByCategory(enumCategory);
        List<ContentDTO> contents = contentList.stream()
                .map(quiz -> modelMapper.map(quiz, ContentDTO.class))
                .toList();
        return new ApiResponse<>(contents, HttpStatus.OK);
    }
}
