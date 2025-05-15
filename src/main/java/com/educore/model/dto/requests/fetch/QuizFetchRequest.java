package com.educore.model.dto.requests.fetch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuizFetchRequest {
    private String subject;
    private String topic;
    private int pageNo;
    private int pageSize;
}
