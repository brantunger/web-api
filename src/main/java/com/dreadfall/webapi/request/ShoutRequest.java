package com.dreadfall.webapi.request;

import lombok.Data;

@Data
public class ShoutRequest {
    private String name;
    private String message;
}
