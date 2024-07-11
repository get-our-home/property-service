package com.getourhome.propertyservice.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {
        "security.jwt.token.secret-key=mySecretKeymySecretKeymySecretKeymySecretKeymySecretKey"
})
class JwtTokenProviderTest {

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    void setUp() {
        jwtTokenProvider = new JwtTokenProvider(secretKey);
    }

    @Test
    @DisplayName("사용자 ID, UUID로 createTokenWithoutExpiration 정상동작 테스트")
    void givenUserIdAndUserPk_whenCreateTokenWithoutExpiration_thenReturnToken() {
        // Given
        UUID userPk = UUID.randomUUID();
        String userId = "tester";

        // When
        String token = jwtTokenProvider.createTokenWithoutExpiration(userPk, userId);

        // Then
        assertThat(token).isNotNull();
    }

    @Test
    @DisplayName("사용자 ID, UUID로 createTokenWithoutExpiration 생성 토큰 유효성 테스트")
    void givenValidToken_whenValidateTokenAndWithoutExpiration_thenReturnTrue() {
        // Given
        UUID userPk = UUID.randomUUID();
        String agencyName = "test 공인중개사";
        String token = jwtTokenProvider.createTokenWithoutExpiration(userPk, agencyName);

        // When
        boolean isValid = jwtTokenProvider.validateToken(token);

        // Then
        assertThat(isValid).isTrue();
    }

    @Test
    @DisplayName("유효하지 않은 토큰 유효성 테스트")
    void givenInvalidToken_whenValidateTokenAndWithoutExpiration_thenReturnFalse() {
        // Given
        String token = "invalid.token.here";

        // When
        boolean isValid = jwtTokenProvider.validateToken(token);

        // Then
        assertThat(isValid).isFalse();
    }

    @Test
    @DisplayName("사용자 ID, UUID로 createTokenWithoutExpiration 생성 토큰 getUserId 테스트")
    void givenValidTokenNoExpiration_whenGetUserId_thenReturnUserId() {
        // Given
        UUID userPk = UUID.randomUUID();
        String agencyName = "test 공인중개사";
        String token = jwtTokenProvider.createTokenWithoutExpiration(userPk, agencyName);

        // When
        String extractedUserId = jwtTokenProvider.getAgencyName(token);

        // Then
        assertThat(extractedUserId).isEqualTo(agencyName);
    }

    @Test
    @DisplayName("사용자 ID, UUID로 createTokenWithoutExpiration 생성 토큰 getUserPk 테스트")
    void givenValidTokenNoExpiration_whenGetUserPk_thenReturnUserPk() {
        // Given
        UUID userPk = UUID.randomUUID();
        String agencyName = "test 공인중개사";
        String token = jwtTokenProvider.createTokenWithoutExpiration(userPk, agencyName);

        // When
        UUID extractedUserPk = jwtTokenProvider.getUserPk(token);

        // Then
        assertThat(extractedUserPk).isEqualTo(userPk);
    }

    @Test
    @DisplayName("사용자 ID, UUID로 createTokenWithoutExpiration 생성 토큰 getRole 테스트")
    void givenValidTokenNoExpiration_whenGetUserPk_thenReturnRole() {
        // Given
        UUID userPk = UUID.randomUUID();
        String agencyName = "test 공인중개사";
        String token = jwtTokenProvider.createTokenWithoutExpiration(userPk, agencyName);

        // When
        String extractedUserPk = jwtTokenProvider.getRole(token);

        // Then
        assertThat(extractedUserPk).isEqualTo("AGENT");
    }
}
