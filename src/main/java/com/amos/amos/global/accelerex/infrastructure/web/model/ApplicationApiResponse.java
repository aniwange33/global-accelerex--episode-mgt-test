package com.amos.amos.global.accelerex.infrastructure.web.model;

import lombok.Data;

@Data
public class ApplicationApiResponse<T> {
    public String message;
    public Object data;

    public ApplicationApiResponse(String message) {
        this.message = message;
    }

    public ApplicationApiResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
