package com.example.security.service;


import com.example.security.config.PropertySource;
import com.example.security.exception.AuthException;
import com.example.security.model.AuthTokenModel;
import com.example.security.repo.DataStore;
import com.example.security.utils.JWTHelper;
import com.example.security.utils.TokenClaim;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final DataStore dataStore;
    private final PropertySource propertySource;

    public AuthTokenModel validateApiKeyAndGetJwtToken(final String apiKey) {
        try {
            final String userId = validateApiKeyAndGetUserId(apiKey);
            final Map<String, Object> claims = getUserInfo(userId);
            final String jwtTokenValue = JWTHelper.createJWT(claims, propertySource.getAppName(),
                    propertySource.getAppAuthSecret(), propertySource.getAppTimeToLive());
            return getTokenModel(jwtTokenValue);
        } catch (Exception e) {
            throw new AuthException("Unauthorized API key : " + apiKey, e);
        }
    }

    private final String validateApiKeyAndGetUserId(final String apiKey) {
        return Optional.ofNullable(dataStore.getUserIdForApikey(apiKey))
                .orElseThrow(() -> new AuthException("InValid API Key"));
    }

    private final Map<String, Object> getUserInfo(final String userId) {
        final Map<String, Object> claims = new HashMap<>();
        final String userInfo = dataStore.getUserInfo(userId);
        final String userRole = dataStore.getUserRole(userId);
        final List<String> authorities = new ArrayList<>();
        authorities.add(userRole);
        claims.put(TokenClaim.USER_ID.getKey(), userId);
        claims.put(TokenClaim.USER_INFO.getKey(), userInfo);
        claims.put(TokenClaim.AUTHORITIES.getKey(), authorities);
        return claims;
    }

    private final AuthTokenModel getTokenModel(final String jwtTokenValue) {
        final AuthTokenModel tokenModel = new AuthTokenModel();
        tokenModel.setType(propertySource.getAppAuthTokenType());
        tokenModel.setToken(jwtTokenValue);
        return tokenModel;
    }


}
