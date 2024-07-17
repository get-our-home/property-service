package com.getourhome.propertyservice.service;

import com.getourhome.propertyservice.entity.Property;
import com.getourhome.propertyservice.repository.PropertyRepository;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;

public class KafkaConsumer {
    private final PropertyRepository propertyRepository;

    public KafkaConsumer(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @KafkaListener(topics = "agency-name-change", groupId = "property-service-group")
    public void consume(String message) {
        String[] parts = message.split(":");
        String agentId = parts[0];
        String newAgencyName = parts[1];

        List<Property> properties = propertyRepository.findByAgentId(agentId);
        for (Property property : properties) {
            property.setAgentId(newAgencyName);
            propertyRepository.save(property);
        }
    }
}
