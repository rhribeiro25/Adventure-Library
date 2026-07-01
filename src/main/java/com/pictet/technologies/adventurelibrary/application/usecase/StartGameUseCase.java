package com.pictet.technologies.adventurelibrary.application.usecase;

import com.pictet.technologies.adventurelibrary.domain.exception.NotFoundException;
import com.pictet.technologies.adventurelibrary.domain.model.Book;
import com.pictet.technologies.adventurelibrary.domain.model.Game;
import com.pictet.technologies.adventurelibrary.domain.model.Player;
import com.pictet.technologies.adventurelibrary.domain.port.in.StartGameRestPort;
import com.pictet.technologies.adventurelibrary.domain.port.out.BookPersistencePort;
import com.pictet.technologies.adventurelibrary.domain.port.out.GamePersistencePort;
import com.pictet.technologies.adventurelibrary.domain.port.out.PlayerPersistencePort;
import com.pictet.technologies.adventurelibrary.domain.service.GameStartService;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.request.StartGameRequest;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.response.GameResponse;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.mapper.GameDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StartGameUseCase implements StartGameRestPort {

    private final BookPersistencePort bookPersistencePort;
    private final PlayerPersistencePort playerPersistencePort;
    private final GamePersistencePort gamePersistencePort;
    private final GameStartService gameStartService;
    private final GameDtoMapper gameDtoMapper;

    @Override
    @Transactional
    public GameResponse execute(Long bookId, StartGameRequest request) {
        Book book = bookPersistencePort.findDetailsById(bookId);
        if (book == null) {
            throw new NotFoundException(
                    "Book with id %d not found.".formatted(bookId)
            );
        }

        Player player = playerPersistencePort.findByEmailIgnoreCase(request.playerEmail())
                .orElseGet(() -> playerPersistencePort.save(
                        Player.builder()
                                .name(request.playerName().trim())
                                .email(request.playerEmail().trim())
                                .build()
                ));

        Game game = gameStartService.start(book, player);

        Game savedGame = gamePersistencePort.save(game);

        return gameDtoMapper.toResponse(savedGame);
    }
}