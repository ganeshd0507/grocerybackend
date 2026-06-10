package com.grocery.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
@Service
public class GoogleAuthService {

    @Value("${google.client.id}")
    private String clientId;

    public GoogleIdToken.Payload verifyToken(String token)
            throws Exception {

        GoogleIdTokenVerifier verifier =
                new GoogleIdTokenVerifier.Builder(
                        new NetHttpTransport(),
                        JacksonFactory.getDefaultInstance())
                        .setAudience(
                                Collections.singletonList(clientId))
                        .build();

        GoogleIdToken idToken =
                verifier.verify(token);

        if (idToken == null) {
            throw new RuntimeException("Invalid token");
        }

        return idToken.getPayload();
    }
}