package com.educore.model.dto.requests.create;
import com.educore.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DailyChallengeCreateRequest {
    private String question;
    private String description;
    private String createdBy;
    private Role creatorRole;
}
