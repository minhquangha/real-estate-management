package com.javaweb.model;

import java.util.List;

public class ErrorResponseDTO {
    private String error;
    private List<String> detailErrors;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getDetailErrors() {
        return detailErrors;
    }

    public void setDetailErrors(List<String> detailErrors) {
        this.detailErrors = detailErrors;
    }
}
