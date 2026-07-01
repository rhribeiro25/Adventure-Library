package com.pictet.technologies.adventurelibrary.infrastructure.in.rest.mapper;

import com.pictet.technologies.adventurelibrary.domain.model.Consequence;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.request.CreateConsequenceRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConsequenceDtoMapper {

    public Consequence toConsequence(CreateConsequenceRequest request) {
        if (request == null) {
            return null;
        }
        return Consequence.builder()
                .type(request.type())
                .value(request.value())
                .text(request.text())
                .build();
    }
}