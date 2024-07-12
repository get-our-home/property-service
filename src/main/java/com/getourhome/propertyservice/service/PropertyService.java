package com.getourhome.propertyservice.service;

import com.getourhome.propertyservice.dto.request.CreatePropertyRequestDto;
import com.getourhome.propertyservice.dto.request.UpdatePropertyRequestDto;
import com.getourhome.propertyservice.entity.Property;
import com.getourhome.propertyservice.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PropertyService {
    private final PropertyRepository propertyRepository;

    public UUID createProperty(UUID agentUuid, String agentId, CreatePropertyRequestDto createPropertyRequestDto) {
        Property property = createPropertyRequestDto.toEntity(agentUuid, agentId);
        return propertyRepository.save(property).getId();
    }

    public UUID updateProperty(UUID agentUuid, UpdatePropertyRequestDto updatePropertyRequestDto) {
        Property property = propertyRepository.findById(updatePropertyRequestDto.getPropertyUuid()).orElse(null);
        if (property == null || !property.getAgentUuid().equals(agentUuid)) {
            return null;
        }

        updatePropertyDetails(property, updatePropertyRequestDto);

        return propertyRepository.save(property).getId();
    }

    private void updatePropertyDetails(Property property, UpdatePropertyRequestDto dto) {
        property.setPropertyType(dto.getPropertyType());
        property.setIsUnregistered(dto.getIsUnregistered());
        property.setAddress(dto.getAddress());
        property.setAdministrativeCode(dto.getAdministrativeCode());
        property.setRoadNameManagementSerialNumber(dto.getRoadNameManagementSerialNumber());
        property.setBuilding(dto.getBuilding());
        property.setUnitNumber(dto.getUnitNumber());
        property.setLatitude(dto.getLatitude());
        property.setLongitude(dto.getLongitude());
        property.setPropertySize(dto.getPropertySize());
        property.setRoomNumber(dto.getRoomNumber());
        property.setMarketType(dto.getMarketType());
        property.setPrice(dto.getPrice());
        property.setSecurityDeposit(dto.getSecurityDeposit());
        property.setCamFee(dto.getCamFee());
        property.setMoveInDate(dto.getMoveInDate());
        property.setIsMoveInDateNegotiable(dto.getIsMoveInDateNegotiable());
        property.setTotalFloors(dto.getTotalFloors());
        property.setFloorNumber(dto.getFloorNumber());
        property.setBathroomCount(dto.getBathroomCount());
        property.setParkingSpaces(dto.getParkingSpaces());
        property.setHasElevator(dto.getHasElevator());
        property.setFacilityInfo(dto.getFacilityInfo());
        property.setTitle(dto.getTitle());
        property.setDescription(dto.getDescription());
    }
}
