package com.batu.author.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class AppResponse<T> {
    private final T data;
    private final HttpStatus status;
    private final String message;
}
