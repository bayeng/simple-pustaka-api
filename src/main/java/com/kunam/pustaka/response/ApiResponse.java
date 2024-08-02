package com.kunam.pustaka.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse<T> {

    private String status;
    private String message;
    private T data;
}
