package com.getourhome.propertyservice.controller;

import com.getourhome.propertyservice.dto.request.CreatePropertyRequestDto;
import com.getourhome.propertyservice.dto.response.BaseResponseDto;
import com.getourhome.propertyservice.service.PropertyService;
import com.getourhome.propertyservice.util.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@Tag(name = "Property API", description = "부동산 매물 생성, 수정, 삭제에 대한 API입니다.")
@RequiredArgsConstructor
@RestController
public class PropertyController {
    private final PropertyService propertyService;

    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/properties")
    @Operation(
            summary = "부동산 매물 등록",
            description = "공인중개사가 부동산 매물을 서비스에 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "부동산 매물 업로드 성공",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponseDto.class)) }),
            @ApiResponse(responseCode = "401", description = "인증된 공인중개사가 아님"),
            @ApiResponse(responseCode = "500", description = "서버 에러")})
    public ResponseEntity<?> createProperty(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
            @RequestBody CreatePropertyRequestDto createPropertyRequestDto) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>("Missing or invalid Authorization header", HttpStatus.UNAUTHORIZED);
        }

        String jwtToken = authorizationHeader.substring(7);
        boolean isValidToken = jwtTokenProvider.validateToken(jwtToken);
        String role = jwtTokenProvider.getRole(jwtToken);
        if (!isValidToken) {
            return new ResponseEntity<>("Invalid JWT token", HttpStatus.UNAUTHORIZED);
        }
        if(!role.equals("AGENT")){
            return new ResponseEntity<>("Invalid JWT token", HttpStatus.UNAUTHORIZED);
        }

        UUID agentUuid = jwtTokenProvider.getUserPk(jwtToken);
        String agentId = jwtTokenProvider.getAgencyName(jwtToken);
        UUID propertyUuid = propertyService.createProperty(agentUuid, agentId, createPropertyRequestDto);

        String msg = propertyUuid.toString();
        BaseResponseDto baseResponseDto = BaseResponseDto.builder().message(msg).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(baseResponseDto);
    }
}
