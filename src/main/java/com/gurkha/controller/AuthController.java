package com.gurkha.controller;


 import com.gurkha.authDtos.AuthReq;
 import com.gurkha.authDtos.AuthResponse;
 import com.gurkha.authDtos.RefreshTokenRequest;
 import com.gurkha.authService.AuthService;
 import com.gurkha.authService.JwtService;
 import com.gurkha.authService.RefreshTokenService;
 import com.gurkha.dtos.UserDto;
 import com.gurkha.entities.RefreshToken;
 import com.gurkha.entities.User;
 import com.gurkha.repository.RefreshTokenRepo;
 import com.gurkha.repository.UserRepo;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;

 import javax.swing.text.html.Option;
 import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService service;
    private final RefreshTokenService refreshTokenService;
    private final UserRepo userRepository;
    private final JwtService jwtService;
    private  final RefreshTokenRepo refreshTokenRepo;

    public AuthController(AuthService authService,RefreshTokenRepo refreshTokenRepo, RefreshTokenService refreshTokenService, JwtService jwtService, UserRepo userRepository) {
        this.service = authService;
        this.jwtService = jwtService;
        this.userRepository=userRepository;
        this.refreshTokenService = refreshTokenService;
        this.refreshTokenRepo=refreshTokenRepo;

    }

@PostMapping("/register")
public ResponseEntity<AuthResponse> registerUser(@RequestBody UserDto registerRequest) {
    return ResponseEntity.ok(service.register(registerRequest));
}

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthReq loginRequest) {
        System.out.println("login api hit , requested data are "+loginRequest);
        return ResponseEntity.ok(service.login(loginRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(refreshTokenRequest.getRefreshToken());
        User user = refreshToken.getUser();

        String accessToken = jwtService.generateToken(user);

        return ResponseEntity.ok(AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .build());
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody RefreshTokenRequest request) {

        Optional<RefreshToken> optionalToken = refreshTokenRepo.findByRefreshToken(request.getRefreshToken());

        if (optionalToken.isPresent()) {
            refreshTokenRepo.delete(optionalToken.get());
            return ResponseEntity.ok("Logged out successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Token not found to logout");
        }
    }
}