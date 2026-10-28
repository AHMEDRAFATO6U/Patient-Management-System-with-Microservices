package com.pm.patientservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ApiResponseDTO {
    private Boolean success;
    private Object data;
    private String message;
    private String error;
}
