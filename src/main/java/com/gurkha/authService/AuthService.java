package com.gurkha.authService;


import com.gurkha.authDtos.AuthReq;
import com.gurkha.authDtos.AuthResponse;
import com.gurkha.dtos.UserDto;
import com.gurkha.entities.Role;
import com.gurkha.entities.User;
import com.gurkha.repository.UserRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepository;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(UserDto request){
        var user= User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode( request.getPassword()))
                .role(Role.valueOf(request.getRole()))
                .build();
        System.out.println("pringing the user "+user);

        User savUser= userRepository.save(user);
        var accessToken=jwtService.generateToken(savUser);
        var refreshToken= refreshTokenService.createRefreshToken(savUser.getEmail());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .email(savUser.getEmail())
                .build();
    }

    public AuthResponse login(AuthReq loginRequest) {
        try {
//            System.out.println("this is inside the auth service of login"+loginRequest);

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()

                    )
            );
        } catch (BadCredentialsException e) {
            System.out.println("Authentication failed: Incorrect username or password");
            throw new UsernameNotFoundException("Invalid username or password");
        } catch (Exception e) {
            System.out.println("Unexpected error during authentication: " + e.getMessage());
            throw new RuntimeException("Authentication error: " + e.getMessage());
        }

        var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        var accessToken = jwtService.generateToken(user);
        var refreshToken = refreshTokenService.createRefreshToken(loginRequest.getEmail());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
//                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }


}