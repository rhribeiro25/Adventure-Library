package com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.mapper;

import com.pictet.technologies.adventureLibrary.domain.model.Option;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto.OptionResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OptionDtoMapper {

    public Set<OptionResponse> toOptionResponses(Set<Option> options) {
        if (options == null || options.isEmpty()) {
            return Collections.emptySet();
        }

        return options.stream()
                .map(this::toOptionResponse)
                .collect(Collectors.toSet());
    }

    public OptionResponse toOptionResponse(Option option) {
        return OptionResponse.builder()
                .description(option.getDescription())
                .gotoId(option.getGotoId())
                .consequence(option.getConsequence())
                .build();
    }
}