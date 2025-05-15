package com.educore.service;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.ContentCreateRequest;
import com.educore.model.dto.response.ContentDTO;
import com.educore.model.enums.ContentCategory;

import java.util.List;

public interface ContentService {

    ApiResponse<ContentDTO> createContent(ContentCreateRequest createRequest);

    ApiResponse<List<ContentDTO>> fetchContents(String category);
}
