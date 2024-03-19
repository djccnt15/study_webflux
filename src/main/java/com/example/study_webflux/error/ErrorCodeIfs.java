package com.example.study_webflux.error;

public interface ErrorCodeIfs {

    Integer getHttpStatusCode();
    Integer getErrorCode();
    String getDescription();
}
