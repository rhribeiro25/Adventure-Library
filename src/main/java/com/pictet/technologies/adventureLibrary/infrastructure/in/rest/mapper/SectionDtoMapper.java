package com.pictet.technologies.adventureLibrary.infrastructure.in.rest.mapper;

import com.pictet.technologies.adventureLibrary.domain.model.Section;
import com.pictet.technologies.adventureLibrary.infrastructure.in.rest.dto.SectionResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SectionDtoMapper {

    private final OptionDtoMapper optionDtoMapper;

    public Set<SectionResponse> toSectionResponses(Set<Section> sections) {
        if (sections == null || sections.isEmpty()) {
            return Collections.emptySet();
        }

        return sections.stream()
                .map(this::toSectionResponse)
                .collect(Collectors.toSet());
    }

    public SectionResponse toSectionResponse(Section section) {
        return SectionResponse.builder()
                .id(section.getId())
                .text(section.getText())
                .type(section.getType())
                .options(optionDtoMapper.toOptionResponses(section.getOptions()))
                .build();
    }
}