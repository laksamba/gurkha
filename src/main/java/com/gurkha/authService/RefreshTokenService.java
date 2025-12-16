package com.gurkha.authService;


import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import com.gurkha.entities.RefreshToken;
import com.gurkha.entities.User;
import com.gurkha.exception.UserNotFound;
import com.gurkha.repository.RefreshTokenRepo;
import com.gurkha.repository.UserRepo;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@Service
public class RefreshTokenService {
        private final UserRepo userRepository;

        private final RefreshTokenRepo refreshTokenRepository;

        public RefreshTokenService(UserRepo userRepository, RefreshTokenRepo refreshTokenRepository) {
            this.userRepository = userRepository;
            this.refreshTokenRepository = refreshTokenRepository;
        }

    public RefreshToken createRefreshToken(String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFound("User not found with email : " + username));

        RefreshToken refreshToken = user.getRefreshToken();

        long refreshTokenValidity = 30 * 100000; // 50 minutes

        if (refreshToken == null) {
            // No existing token → create new
            refreshToken = new RefreshToken();
            refreshToken.setUser(user);
        }

        // Set new values (update or new)
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpirationTime(Instant.now().plusMillis(refreshTokenValidity));

        return refreshTokenRepository.save(refreshToken); // Hibernate will INSERT if new, UPDATE if exists
    }


//        public RefreshToken createRefreshToken(String username) {
//            User user = userRepository.findByEmail(username)
//                    .orElseThrow(() -> new UserNotFound("User not found with email : " + username));
//
//
//            RefreshToken refreshToken = user.getRefreshToken();
//
//
//            if (refreshToken == null || refreshToken.getExpirationTime().compareTo(Instant.now())<0) {
//                refreshTokenRepository.delete(refreshToken); // delete old token
//
//                long refreshTokenValidity = 30 * 100000;
//                refreshToken = RefreshToken.builder()
//                        .refreshToken(UUID.randomUUID().toString())
//                        .expirationTime(Instant.now().plusMillis(refreshTokenValidity))
//                        .user(user)
//                        .build();
//
//                refreshTokenRepository.save(refreshToken);
//            }
//
//            return refreshToken;
//        }

        public RefreshToken verifyRefreshToken(String refreshToken) {
            RefreshToken refToken = refreshTokenRepository.findByRefreshToken(refreshToken)
                    .orElseThrow(() -> new RuntimeException("Refresh token not found!"));

            if (refToken.getExpirationTime().compareTo(Instant.now()) < 0) {
                refreshTokenRepository.delete(refToken);
                throw new RuntimeException("Refresh Token expired");
            }

            return refToken;
        }
    }