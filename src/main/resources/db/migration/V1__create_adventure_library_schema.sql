CREATE TABLE players
(
    id         BIGSERIAL PRIMARY KEY,
    email      VARCHAR(255) NOT NULL,
    name       VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,

    CONSTRAINT uk_players_email UNIQUE (email)
);

CREATE TABLE books
(
    id               BIGSERIAL PRIMARY KEY,
    title            VARCHAR(255) NOT NULL,
    author           VARCHAR(255) NOT NULL,
    difficulty_level VARCHAR(20)  NOT NULL,
    created_at       TIMESTAMP,
    updated_at       TIMESTAMP,
    version          BIGINT       NOT NULL
);

CREATE TABLE categories
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    version    BIGINT       NOT NULL,

    CONSTRAINT uk_categories_name UNIQUE (name)
);

CREATE TABLE book_categories
(
    book_id     BIGINT NOT NULL,
    category_id BIGINT NOT NULL,

    PRIMARY KEY (book_id, category_id),

    CONSTRAINT fk_book_categories_book
        FOREIGN KEY (book_id)
            REFERENCES books (id),

    CONSTRAINT fk_book_categories_category
        FOREIGN KEY (category_id)
            REFERENCES categories (id)
);

CREATE TABLE sections
(
    id         BIGINT PRIMARY KEY,
    book_id    BIGINT      NOT NULL,
    text       TEXT        NOT NULL,
    type       VARCHAR(20) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,

    CONSTRAINT fk_sections_book
        FOREIGN KEY (book_id)
            REFERENCES books (id)
);

CREATE TABLE options
(
    id              BIGSERIAL PRIMARY KEY,
    description     VARCHAR(255) NOT NULL,
    next_section_id BIGINT       NOT NULL,
    section_id      BIGINT,
    created_at      TIMESTAMP,
    updated_at      TIMESTAMP,

    CONSTRAINT fk_options_section
        FOREIGN KEY (section_id)
            REFERENCES sections (id)
);

CREATE TABLE consequences
(
    id         BIGSERIAL PRIMARY KEY,
    option_id  BIGINT      NOT NULL,
    type       VARCHAR(30) NOT NULL,
    value      INTEGER,
    text       TEXT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,

    CONSTRAINT uk_consequences_option UNIQUE (option_id),

    CONSTRAINT fk_consequences_option
        FOREIGN KEY (option_id)
            REFERENCES options (id)
);

CREATE TABLE games
(
    id                 BIGSERIAL PRIMARY KEY,
    player_id          BIGINT      NOT NULL,
    health             INTEGER     NOT NULL,
    status             VARCHAR(20) NOT NULL,
    book_id            BIGINT      NOT NULL,
    current_section_id BIGINT      NOT NULL,
    created_at         TIMESTAMP,
    updated_at         TIMESTAMP,
    version            BIGINT      NOT NULL,

    CONSTRAINT fk_games_player
        FOREIGN KEY (player_id)
            REFERENCES players (id),

    CONSTRAINT fk_games_book
        FOREIGN KEY (book_id)
            REFERENCES books (id),

    CONSTRAINT fk_games_current_section
        FOREIGN KEY (current_section_id)
            REFERENCES sections (id)
);

CREATE INDEX idx_players_email ON players (email);
CREATE INDEX idx_book_title ON books (title);
CREATE INDEX idx_book_author ON books (author);
CREATE INDEX idx_book_difficulty ON books (difficulty_level);
CREATE INDEX idx_category_name ON categories (name);
CREATE INDEX idx_sections_book ON sections (book_id);
CREATE INDEX idx_options_section ON options (section_id);
CREATE INDEX idx_options_next_section ON options (next_section_id);
CREATE INDEX idx_games_player ON games (player_id);
CREATE INDEX idx_games_book ON games (book_id);
CREATE INDEX idx_games_current_section ON games (current_section_id);