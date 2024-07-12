package com.getourhome.propertyservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.getourhome.propertyservice.entity.FacilityInfo;
import com.getourhome.propertyservice.entity.MarketType;
import com.getourhome.propertyservice.entity.Property;
import com.getourhome.propertyservice.entity.PropertyType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePropertyRequestDto {
    @JsonProperty("property_uuid")
    @Schema(description = "매물 고유 번호", example = "90a1fbbb-dfc0-4389-b2a0-b43b21cdfd02")
    @NotBlank(message = "수정할 매물 고유 번호를 입력해주세요")
    private UUID propertyUuid;
    @JsonProperty("property_type")
    @Schema(description = "매물 종류, " +
            "빌라_연립_다세대 = 0, 단독주택 = 1, 다가구주택 = 2, 상가주택 = 3" +
            "기타 = 4, 오피스텔 = 5, 아파트 = 6",
            example = "1")
    @NotBlank(message = "매물 종류를 입력해주세요")
    private PropertyType propertyType;

    @JsonProperty("is_unregistered")
    @Schema(description = "미등기 건물 여부", example = "false")
    @NotBlank(message = "미등기 건물 여부를 입력해주세요")
    private Boolean isUnregistered;

    @JsonProperty("address")
    @Schema(description = "주소", example = "서울특별시 강남구 테헤란로 123")
    @NotBlank(message = "주소를 입력해주세요")
    private String address;

    @JsonProperty("administrative_code")
    @Schema(description = "행정구역 코드", example = "1100000000")
    @NotBlank(message = "행정구역 코드를 입력해주세요")
    private String administrativeCode;

    @JsonProperty("road_name_management_serial_number")
    @Schema(description = "도로명코드", example = "123456789012")
    @NotBlank(message = "도로명코드를 입력해주세요")
    private String roadNameManagementSerialNumber;

    @JsonProperty("building")
    @Schema(description = "건물 동", example = "A동")
    private String building;

    @JsonProperty("unit_number")
    @Schema(description = "호수", example = "101호")
    @NotBlank(message = "호수를 입력해주세요")
    private String unitNumber;

    @JsonProperty("latitude")
    @Schema(description = "위도", example = "37.5665")
    @NotBlank(message = "위도를 입력해주세요")
    private Long latitude;

    @JsonProperty("longitude")
    @Schema(description = "경도", example = "126.9780")
    @NotBlank(message = "경도를 입력해주세요")
    private Long longitude;

    @JsonProperty("property_size")
    @Schema(description = "매물 크기 (m^2)", example = "85.0")
    @NotBlank(message = "매물 크기를 입력해주세요")
    private Double propertySize;

    @JsonProperty("room_number")
    @Schema(description = "방 갯수", example = "3")
    @NotBlank(message = "방 갯수를 입력해주세요")
    private Integer roomNumber;

    @JsonProperty("market_type")
    @Schema(description = "거래 종류, MONTHLY_RENT = 0, JEONSE = 1, SALE = 2", example = "1")
    @NotBlank(message = "거래 종류를 입력해주세요")
    private MarketType marketType;

    @JsonProperty("price")
    @Schema(description = "가격 (만원)", example = "5000")
    @NotBlank(message = "가격을 입력해주세요")
    private Integer price;

    @JsonProperty("security_deposit")
    @Schema(description = "보증금 (만원)", example = "1000")
    private Integer securityDeposit;

    @JsonProperty("cam_fee")
    @Schema(description = "공용관리비 (원)", example = "50000")
    private Integer camFee;

    @JsonProperty("move_in_date")
    @Schema(description = "입주 가능일자, 00000000 -> 즉시입주", example = "20230101")
    @NotBlank(message = "입주 가능일자를 입력해주세요")
    private String moveInDate;

    @JsonProperty("is_move_in_date_negotiable")
    @Schema(description = "입주 가능일자 협의 가능 여부", example = "false")
    @NotBlank(message = "입주 가능일자 협의 가능 여부를 입력해주세요")
    private Boolean isMoveInDateNegotiable;

    @JsonProperty("total_floors")
    @Schema(description = "전체 층수", example = "15")
    @NotBlank(message = "전체 층수를 입력해주세요")
    private Integer totalFloors;

    @JsonProperty("floor_number")
    @Schema(description = "해당 층수", example = "7")
    @NotBlank(message = "해당 층수를 입력해주세요")
    private Integer floorNumber;

    @JsonProperty("bathroom_count")
    @Schema(description = "욕실 수", example = "2")
    @NotBlank(message = "욕실 수를 입력해주세요")
    private Integer bathroomCount;

    @JsonProperty("parking_spaces")
    @Schema(description = "주차 가능 대수", example = "2")
    @NotBlank(message = "주차 가능 대수를 입력해주세요")
    private Integer parkingSpaces;

    @JsonProperty("has_elevator")
    @Schema(description = "엘리베이터 여부", example = "true")
    @NotBlank(message = "엘리베이터 여부를 입력해주세요")
    private Boolean hasElevator;

    @JsonProperty("facility_info")
    @Schema(description = "시설 정보", example = FacilityInfo.DEFAULT_JSON_EXAMPLE)
    @NotBlank(message = "시설 정보를 입력해주세요")
    private FacilityInfo facilityInfo;

    @JsonProperty("title")
    @Schema(description = "제목", example = "강남구 테헤란로 3룸 아파트")
    @NotBlank(message = "제목을 입력해주세요")
    private String title;

    @JsonProperty("description")
    @Schema(description = "상세 설명", example = "강남구에 위치한 아파트로 교통이 편리합니다.")
    @NotBlank(message = "상세 설명을 입력해주세요")
    private String description;

//    @JsonProperty("property_photos")
//    @Schema(description = "사진 목록", example = "사진 파일 배열")
//    @NotNull(message = "사진 목록을 입력해주세요")
//    private MultipartFile[] propertyPhotos;

    public Property toEntity(Property property) {
        return Property.builder()
                .agentUuid(property.getAgentUuid())
                .agentId(property.getAgentId())
                .propertyType(propertyType)
                .isUnregistered(isUnregistered)
                .address(address)
                .administrativeCode(administrativeCode)
                .roadNameManagementSerialNumber(roadNameManagementSerialNumber)
                .building(building)
                .unitNumber(unitNumber)
                .latitude(latitude)
                .longitude(longitude)
                .propertySize(propertySize)
                .roomNumber(roomNumber)
                .marketType(marketType)
                .price(price)
                .securityDeposit(securityDeposit)
                .camFee(camFee)
                .moveInDate(moveInDate)
                .isMoveInDateNegotiable(isMoveInDateNegotiable)
                .totalFloors(totalFloors)
                .floorNumber(floorNumber)
                .bathroomCount(bathroomCount)
                .parkingSpaces(parkingSpaces)
                .hasElevator(hasElevator)
                .facilityInfo(facilityInfo)
                .title(title)
                .description(description)
                .build();
    }
}
