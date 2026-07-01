package com.pictet.technologies.adventurelibrary.application.usecase;

import com.pictet.technologies.adventurelibrary.domain.model.Book;
import com.pictet.technologies.adventurelibrary.domain.model.Game;
import com.pictet.technologies.adventurelibrary.domain.model.Player;
import com.pictet.technologies.adventurelibrary.domain.model.Section;
import com.pictet.technologies.adventurelibrary.domain.port.in.StartGameRestPort;
import com.pictet.technologies.adventurelibrary.domain.port.out.BookPersistencePort;
import com.pictet.technologies.adventurelibrary.domain.port.out.GamePersistencePort;
import com.pictet.technologies.adventurelibrary.domain.port.out.PlayerPersistencePort;
import com.pictet.technologies.adventurelibrary.domain.port.out.SectionPersistencePort;
import com.pictet.technologies.adventurelibrary.domain.service.GameStartService;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.request.StartGameRequest;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.response.GameResponse;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.mapper.GameDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class StartGameUseCase implements StartGameRestPort {

    private final SectionPersistencePort sectionPersistencePort;
    private final PlayerPersistencePort playerPersistencePort;
    private final GamePersistencePort gamePersistencePort;
    private final GameStartService gameStartService;
    private final GameDtoMapper gameDtoMapper;

    @Override
    @Transactional
    public GameResponse execute(Long bookId, StartGameRequest request) {
        Player player = playerPersistencePort.findByEmailIgnoreCase(request.playerEmail())
                .orElseGet(() -> playerPersistencePort.save(
                        Player.builder()
                                .name(request.playerName().trim())
                                .email(request.playerEmail().trim())
                                .build()
                ));
        Set<Section> sections = sectionPersistencePort.findByBookId(bookId);

        Book book = Book.builder()
                .id(bookId)
                .sections(sections)
                .build();

        Game game = gameStartService.start(book, player);

        Game savedGame = gamePersistencePort.save(game);

        return gameDtoMapper.toResponse(savedGame);
    }
}