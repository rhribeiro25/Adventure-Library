package com.pictet.technologies.adventurelibrary.infrastructure.in.rest.adapter;

import com.pictet.technologies.adventurelibrary.domain.model.enums.GameStatus;
import com.pictet.technologies.adventurelibrary.domain.port.in.GetGameDetailsRestPort;
import com.pictet.technologies.adventurelibrary.domain.port.in.NavigateGameRestPort;
import com.pictet.technologies.adventurelibrary.domain.port.in.StartGameRestPort;
import com.pictet.technologies.adventurelibrary.domain.port.in.UpdateGameRestPort;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.request.StartGameRequest;
import com.pictet.technologies.adventurelibrary.infrastructure.in.rest.dto.response.GameResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/games")
@Tag(name = "Games", description = "Game navigation and progress APIs")
public class GameController {

    private final NavigateGameRestPort navigateGameRestPort;
    private final UpdateGameRestPort updateGameRestPort;
    private final StartGameRestPort startGameRestPort;
    private final GetGameDetailsRestPort getGameDetailsRestPort;

    @PatchMapping("/{gameId}/choices")
    @Operation(
            summary = "Choose option",
            description = "Applies the selected option, handles consequences, updates health and moves the game to the next section."
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(schema = @Schema(implementation = GameResponse.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid request",
            content = @Content
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = @Content
    )
    public GameResponse chooseOption(
            @Parameter(description = "Game id", required = true)
            @PathVariable Long gameId,

            @Parameter(description = "Option id")
            @RequestParam Long optionId) {

        log.info("Choosing option. gameId={}, optionId={}", gameId, optionId);

        return navigateGameRestPort.execute(gameId, optionId);
    }

    @PatchMapping("/{gameId}")
    @Operation(
            summary = "Update game",
            description = "Updates the game status. Supported values: PLAYING, PAUSED, STOPPED."
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(schema = @Schema(implementation = GameResponse.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid request",
            content = @Content
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = @Content
    )
    public GameResponse updateGame(
            @Parameter(description = "Game id", required = true)
            @PathVariable Long gameId,

            @Parameter(
                    description = "New game status",
                    required = true,
                    schema = @Schema(
                            implementation = GameStatus.class,
                            allowableValues = {
                                    "PLAYING",
                                    "PAUSED",
                                    "STOPPED"
                            }
                    )
            )
            @RequestParam GameStatus status) {

        log.info("Updating game. gameId={}, status={}", gameId, status);

        return updateGameRestPort.execute(gameId, status);
    }

    @PostMapping("/{bookId}/games")
    @Operation(
            summary = "Start game",
            description = "Starts a new game for the selected book and player."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Created",
            content = @Content(schema = @Schema(implementation = GameResponse.class))
    )
    @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    public ResponseEntity<GameResponse> startGame(
            @Parameter(description = "Book id", required = true)
            @PathVariable Long bookId,

            @Valid @RequestBody StartGameRequest request) {

        log.info("Starting game. bookId={}, playerEmail={}", bookId, request.playerEmail());

        GameResponse response = startGameRestPort.execute(bookId, request);

        return ResponseEntity
                .created(URI.create("/api/v1/games/" + response.id()))
                .body(response);
    }

    @GetMapping("/{gameId}")
    @Operation(
            summary = "Get game details",
            description = "Returns the current game state, including player, book, health, status and current section."
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(schema = @Schema(implementation = GameResponse.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = @Content
    )
    public GameResponse getGameDetails(
            @Parameter(description = "Game id", required = true)
            @PathVariable Long gameId) {

        log.info("Getting game details. gameId={}", gameId);

        return getGameDetailsRestPort.execute(gameId);
    }
}