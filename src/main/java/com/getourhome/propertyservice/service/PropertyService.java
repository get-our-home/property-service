package com.getourhome.propertyservice.service;

import com.getourhome.propertyservice.dto.request.CreatePropertyRequestDto;
import com.getourhome.propertyservice.entity.Property;
import com.getourhome.propertyservice.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PropertyService {
    private final PropertyRepository propertyRepository;

    public UUID createProperty(UUID agentUuid, String agentId, CreatePropertyRequestDto createPropertyRequestDto) {
        Property property = createPropertyRequestDto.toEntity(agentUuid, agentId);
        return propertyRepository.save(property).getAgentUuid();
    }
}
