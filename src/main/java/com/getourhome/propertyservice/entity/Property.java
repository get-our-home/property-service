package com.getourhome.propertyservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Properties")
public class Property {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false) private UUID id;
    @Column(name = "agent_uuid", nullable = false) private UUID agentUuid; // 공인중개사 고유식별번호
    @Column(name = "agent_id", nullable = false) private String agentId; // 공인중개사 아이디
    @Setter @Column(name = "property_type", nullable = false)
    @Enumerated(EnumType.ORDINAL) private PropertyType propertyType; // 오피스텔, 아파트, 다세대..
    @Setter @Column(name = "is_unregistered", nullable = false)
    private Boolean isUnregistered = false; // 미등기 건물인지 아닌지
    @Column(nullable = false) private String address; // 주소
    @Column(name = "administrative_code", nullable = false) private String administrativeCode; // 행정구역코드
    @Column(name = "road_name_management_serial_number", nullable = false)
    private String roadNameManagementSerialNumber; // 도로명코드
    @Column(nullable = true) private String building; // 동, 없는 경우 있음
    @Column(nullable = false) private String unitNumber; // 호
    @Column(nullable = false) private Long latitude;
    @Column(nullable = false) private Long longitude;
    @Column(name = "property_size", nullable = false) private Double propertySize; // 매물 크기, 전용 면적, m^2
    @Column(name = "room_number", nullable = false) private Integer roomNumber; // 방 갯수
    // 방 거실 형태?
    // 방 특징
    @Setter @Column(name = "market_type", nullable = false)
    @Enumerated(EnumType.ORDINAL) private MarketType marketType; // 거래종류, 월세, 전세, 매매
    @Column(nullable = false) private Integer price; // 가격, (월세, 전세), 만원단위
    @Column(name = "security_deposit", nullable = true) private Integer securityDeposit; // 보증금, 만원단위
    @Column(name = "cam_fee", nullable = true) private Integer camFee; // 공용관리비, 원단위
    @Column(name = "move_in_date", nullable = false) private String moveInDate; // 입주 가능일자, (00000000)즉시 입주, (yyyymmdd)일자 선택
    @Column(name = "is_move_in_date_negotiable", nullable = false) private Boolean isMoveInDateNegotiable = false; // 입주 가능일자 협의 가능 여부
    @Column(name = "total_floors", nullable = false) private Integer totalFloors; // 전체 층수
    @Column(name = "floor_number", nullable = false) private Integer floorNumber; // 해당 층수
    @Column(name = "bathroom_count", nullable = false) private Integer bathroomCount; // 욕실 수
    @Column(name = "parking_spaces", nullable = false) private Integer parkingSpaces = -1; // 주차 가능 대수, -1 불가
    @Column(name = "has_elevator", nullable = false) private Boolean hasElevator = false; // 엘레베이터 여부
    @Column(name = "facility_info", nullable = false) @JdbcTypeCode(SqlTypes.JSON)
    private FacilityInfo facilityInfo; // 시설 정보
    @Column(nullable = false) private String title; // 제목, 길이 40자 (120)
    @Column(nullable = false, length = 3000) private String description; // 상세설명, 1000자 제한
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PropertyPhoto> propertyPhotos = new HashSet<>(); // 사진
}
