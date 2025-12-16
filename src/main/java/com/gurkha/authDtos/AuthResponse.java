package com.gurkha.authDtos;

import com.gurkha.entities.Role;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String accessToken;
    private String refreshToken;
    private String email;
    private Role role;
}
