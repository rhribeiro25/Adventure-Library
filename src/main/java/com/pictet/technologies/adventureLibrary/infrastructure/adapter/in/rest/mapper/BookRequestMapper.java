package com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.mapper;

import com.pictet.technologies.adventureLibrary.domain.model.*;
import com.pictet.technologies.adventureLibrary.domain.model.enums.*;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto.BookRequest;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto.ConsequenceRequest;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto.OptionRequest;
import com.pictet.technologies.adventureLibrary.infrastructure.adapter.in.rest.dto.SectionRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookRequestMapper {

    public Book toDomain(BookRequest dto) {
        return Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .difficultyLevel(DifficultyLevel.valueOf(dto.getDifficulty()))
                .sections(toSections(dto.getSections()))
                .build();
    }

    private List<Section> toSections(List<SectionRequest> sections) {
        if (sections == null) {
            return List.of();
        }

        return sections.stream()
                .map(this::toSection)
                .toList();
    }

    private Section toSection(SectionRequest dto) {
        return Section.builder()
                .text(dto.getText())
                .type(SectionType.valueOf(dto.getType()))
                .options(toOptions(dto.getOptions()))
                .build();
    }

    private List<Option> toOptions(List<OptionRequest> options) {
        if (options == null) {
            return List.of();
        }

        return options.stream()
                .map(this::toOption)
                .toList();
    }

    private Option toOption(OptionRequest dto) {
        return Option.builder()
                .description(dto.getDescription())
                .gotoId(dto.getGotoId())
                .consequence(toConsequence(dto.getConsequence()))
                .build();
    }

    private Consequence toConsequence(ConsequenceRequest dto) {
        if (dto == null) {
            return null;
        }

        return Consequence.builder()
                .type(ConsequenceType.valueOf(dto.getType()))
                .value(Integer.valueOf(dto.getValue()))
                .text(dto.getText())
                .build();
    }
}