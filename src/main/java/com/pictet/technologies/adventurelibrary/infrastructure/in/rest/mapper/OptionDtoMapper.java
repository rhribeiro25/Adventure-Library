package com.pictet.technologies.adventurelibrary.infrastructure.in.rest.mapper;

import com.pictet.technologies.adventurelibrary.domain.model.Option;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.request.CreateOptionRequest;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.response.OptionResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OptionDtoMapper {

    private final ConsequenceDtoMapper consequenceDtoMapper;

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
                .id(option.getId())
                .description(option.getDescription())
                .gotoId(option.getNextSectionId())
                .consequence(option.getConsequence())
                .build();
    }

    public Set<Option> toOptions(Set<CreateOptionRequest> options) {
        if (options == null) {
            return Collections.emptySet();
        }

        return options.stream()
                .map(this::toOption)
                .collect(Collectors.toSet());
    }

    public Option toOption(CreateOptionRequest request) {
        return Option.builder()
                .description(request.description().trim())
                .nextSectionId(request.gotoId())
                .consequence(consequenceDtoMapper.toConsequence(request.consequence()))
                .build();
    }
}