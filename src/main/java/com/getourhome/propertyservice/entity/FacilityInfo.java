package com.getourhome.propertyservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FacilityInfo {
    private String heating; // 난방 시설

    // 냉방 시설
    private boolean coolingWallMounted; // 벽걸이형 에어컨 여부
    private boolean coolingStandType; // 스탠드형 에어컨 여부
    private boolean coolingCeilingType; // 천장형 에어컨 여부

    // 생활 시설
    private boolean bed; // 침대 여부
    private boolean desk; // 책상 여부
    private boolean closet; // 옷장 여부
    private boolean diningTable; // 식탁 여부
    private boolean sofa; // 쇼파 여부
    private boolean shoeCabinet; // 신발장 여부
    private boolean refrigerator; // 냉장고 여부
    private boolean washingMachine; // 세탁기 여부
    private boolean dryer; // 건조기 여부
    private boolean showerBooth; // 샤워부스 여부
    private boolean bathtub; // 욕조 여부
    private boolean bidet; // 비데 여부
    private boolean sink; // 싱크대 여부
    private boolean dishwasher; // 식기세척기 여부
    private boolean gasStove; // 가스레인지 여부
    private boolean induction; // 인덕션 여부
    private boolean microwave; // 전자레인지 여부
    private boolean gasOven; // 가스오븐 여부
    private boolean tv; // TV 여부
    private boolean wallCloset; // 붙박이장 여부

    // 보안 시설
    private boolean securityGuard; // 경비원 여부
    private boolean videoPhone; // 비디오폰 여부
    private boolean intercom; // 인터폰 여부
    private boolean keycard; // 카드키 여부
    private boolean cctv; // CCTV 여부
    private boolean alarm; // 사설경비 여부
    private boolean securityLights; // 현관보안 여부
    private boolean crimePrevention; // 방범창 여부

    // 기타 시설
    private boolean fireAlarm; // 화재경보기 여부
    private boolean balcony; // 베란다 여부
    private boolean terrace; // 테라스 여부
    private boolean yard; // 마당 여부
    private boolean parcelBox; // 무인택배함 여부
}
