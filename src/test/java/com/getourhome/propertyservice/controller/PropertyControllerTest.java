package com.getourhome.propertyservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getourhome.propertyservice.dto.request.CreatePropertyRequestDto;
import com.getourhome.propertyservice.dto.request.UpdatePropertyRequestDto;
import com.getourhome.propertyservice.entity.FacilityInfo;
import com.getourhome.propertyservice.entity.MarketType;
import com.getourhome.propertyservice.entity.PropertyType;
import com.getourhome.propertyservice.service.PropertyService;
import com.getourhome.propertyservice.util.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PropertyController.class)
class PropertyControllerTest {
    @InjectMocks
    private PropertyController propertyController;
    @MockBean
    private PropertyService propertyService;
    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    private final MockMvc mvc;
    private final ObjectMapper objectMapper;

    PropertyControllerTest(
            @Autowired MockMvc mvc,
            @Autowired ObjectMapper objectMapper
    ) {
        this.mvc = mvc;
        this.objectMapper = objectMapper;
    }

    private FacilityInfo facilityInfo = FacilityInfo
            .builder().alarm(true).build();
    CreatePropertyRequestDto creatRequestDto = CreatePropertyRequestDto
            .builder()
            .propertyType(PropertyType.APARTMENT)
            .isUnregistered(false)
            .address("서울특별시 강남구 테헤란로 123")
            .administrativeCode("1100000000")
            .roadNameManagementSerialNumber("123456789012")
            .building("A동")
            .unitNumber("101호")
            .latitude(0L)
            .longitude(0L)
            .propertySize(85D)
            .roomNumber(3)
            .marketType(MarketType.MONTHLY_RENT)
            .price(50)
            .securityDeposit(100)
            .camFee(50000)
            .moveInDate("00000000")
            .isMoveInDateNegotiable(true)
            .totalFloors(15)
            .floorNumber(7)
            .bathroomCount(2)
            .parkingSpaces(2)
            .hasElevator(true)
            .facilityInfo(facilityInfo)
            .title("강남구 테헤란로 3룸 아파트")
            .description("강남구에 위치한 아파트로 교통이 편리합니다.")
            .build();

    UpdatePropertyRequestDto updateRequestDto = UpdatePropertyRequestDto
            .builder()
            .propertyType(PropertyType.APARTMENT)
            .isUnregistered(false)
            .address("서울특별시 강남구 테헤란로")
            .administrativeCode("1100000000")
            .roadNameManagementSerialNumber("123456789012")
            .building("B동")
            .unitNumber("101호")
            .latitude(0L)
            .longitude(0L)
            .propertySize(85D)
            .roomNumber(3)
            .marketType(MarketType.MONTHLY_RENT)
            .price(50)
            .securityDeposit(100)
            .camFee(50000)
            .moveInDate("00000000")
            .isMoveInDateNegotiable(true)
            .totalFloors(15)
            .floorNumber(7)
            .bathroomCount(2)
            .parkingSpaces(2)
            .hasElevator(true)
            .facilityInfo(facilityInfo)
            .title("강남구 테헤란로 3룸 아파트")
            .description("강남구에 위치한 아파트로 교통이 편리합니다.")
            .build();


    @Test
    public void givenValidRequest_whenCreateProperty_thenReturnsCreated() throws Exception {
        // Given
        String jwtToken = "valid-jwt-token";
        String authorizationHeader = "Bearer " + jwtToken;

        CreatePropertyRequestDto testRequestDto = creatRequestDto;

        UUID agentUuid = UUID.randomUUID();
        UUID propertyUuid = UUID.randomUUID();
        String agentId = "agent123";

        when(jwtTokenProvider.validateToken(jwtToken)).thenReturn(true);
        when(jwtTokenProvider.getRole(jwtToken)).thenReturn("AGENT");
        when(jwtTokenProvider.getUserPk(jwtToken)).thenReturn(agentUuid);
        when(jwtTokenProvider.getAgencyName(jwtToken)).thenReturn(agentId);
        when(propertyService.createProperty(any(UUID.class), any(String.class), any(CreatePropertyRequestDto.class))).thenReturn(propertyUuid);

        // When & Then
        mvc.perform(post("/properties")
                        .header("Authorization", authorizationHeader)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(testRequestDto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void givenInvalidToken_whenCreateProperty_thenReturnsUnauthorized() throws Exception {
        // Given
        String jwtToken = "invalid-jwt-token";
        String authorizationHeader = "Bearer " + jwtToken;

        CreatePropertyRequestDto testRequestDto = creatRequestDto;

        when(jwtTokenProvider.validateToken(jwtToken)).thenReturn(false);

        // When & Then
        mvc.perform(post("/properties")
                        .header("Authorization", authorizationHeader)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(testRequestDto)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void givenNoAuthorizationHeader_whenCreateProperty_thenReturnsUnauthorized() throws Exception {
        // Given
        CreatePropertyRequestDto testRequestDto = creatRequestDto;

        String jwtToken = "valid-jwt-token";

        when(jwtTokenProvider.validateToken(jwtToken)).thenReturn(true);
        when(jwtTokenProvider.getRole(jwtToken)).thenReturn("USER");

        // When & Then
        mvc.perform(post("/properties")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(testRequestDto)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void givenValidRequest_whenUpdateProperty_thenReturnsOk() throws Exception {
        // Given
        String jwtToken = "valid-jwt-token";
        String authorizationHeader = "Bearer " + jwtToken;
        UpdatePropertyRequestDto testRequestDto = updateRequestDto;
        testRequestDto.setPropertyUuid(UUID.randomUUID());

        UUID agentUuid = UUID.randomUUID();
        UUID propertyUuid = UUID.randomUUID();

        when(jwtTokenProvider.validateToken(jwtToken)).thenReturn(true);
        when(jwtTokenProvider.getRole(jwtToken)).thenReturn("AGENT");
        when(jwtTokenProvider.getUserPk(jwtToken)).thenReturn(agentUuid);
        when(propertyService.updateProperty(any(UUID.class), any(UpdatePropertyRequestDto.class))).thenReturn(propertyUuid);

        // When & Then
        mvc.perform(patch("/properties")
                        .header("Authorization", authorizationHeader)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(testRequestDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void givenInvalidToken_whenUpdateProperty_thenReturnsUnauthorized() throws Exception {
        // Given
        String jwtToken = "invalid-jwt-token";
        String authorizationHeader = "Bearer " + jwtToken;
        UpdatePropertyRequestDto testRequestDto = updateRequestDto;
        testRequestDto.setPropertyUuid(UUID.randomUUID());

        when(jwtTokenProvider.validateToken(jwtToken)).thenReturn(false);

        // When & Then
        mvc.perform(patch("/properties")
                        .header("Authorization", authorizationHeader)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(testRequestDto)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void givenNoAuthorizationHeader_whenUpdateProperty_thenReturnsUnauthorized() throws Exception {
        // Given
        UpdatePropertyRequestDto testRequestDto = updateRequestDto;
        testRequestDto.setPropertyUuid(UUID.randomUUID());

        // When & Then
        mvc.perform(patch("/properties")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(testRequestDto)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void givenPropertyNotFound_whenUpdateProperty_thenReturnsNotFound() throws Exception {
        // Given
        String jwtToken = "valid-jwt-token";
        String authorizationHeader = "Bearer " + jwtToken;
        UpdatePropertyRequestDto testRequestDto = updateRequestDto;
        testRequestDto.setPropertyUuid(UUID.randomUUID());

        UUID agentUuid = UUID.randomUUID();

        when(jwtTokenProvider.validateToken(jwtToken)).thenReturn(true);
        when(jwtTokenProvider.getRole(jwtToken)).thenReturn("AGENT");
        when(jwtTokenProvider.getUserPk(jwtToken)).thenReturn(agentUuid);
        when(propertyService.updateProperty(any(UUID.class), any(UpdatePropertyRequestDto.class))).thenReturn(null);

        // When & Then
        mvc.perform(patch("/properties")
                        .header("Authorization", authorizationHeader)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(testRequestDto)))
                .andExpect(status().isNotFound());
    }
}