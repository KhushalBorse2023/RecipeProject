package com.khushalt.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

public class AuthResponse {
    private String jwt;
    private String message;

    public AuthResponse() {

    }
}
