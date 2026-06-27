CREATE TABLE books
(
    id               BIGSERIAL PRIMARY KEY,
    title            VARCHAR(255) NOT NULL,
    author           VARCHAR(255) NOT NULL,
    difficulty_level VARCHAR(20)  NOT NULL,
    created_at       TIMESTAMP,
    updated_at       TIMESTAMP
);

CREATE TABLE categories
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE book_categories
(
    book_id     BIGINT NOT NULL,
    category_id BIGINT NOT NULL,

    PRIMARY KEY (book_id, category_id),

    CONSTRAINT fk_book_categories_book
        FOREIGN KEY (book_id)
            REFERENCES books (id)
            ON DELETE CASCADE,

    CONSTRAINT fk_book_categories_category
        FOREIGN KEY (category_id)
            REFERENCES categories (id)
            ON DELETE CASCADE
);

CREATE TABLE sections
(
    id             BIGSERIAL PRIMARY KEY,
    book_id        BIGINT      NOT NULL,
    section_number BIGINT      NOT NULL,
    text           TEXT        NOT NULL,
    type           VARCHAR(20) NOT NULL,
    created_at     TIMESTAMP,
    updated_at     TIMESTAMP,

    CONSTRAINT fk_sections_book
        FOREIGN KEY (book_id)
            REFERENCES books (id)
            ON DELETE CASCADE,

    CONSTRAINT uk_book_section_number
        UNIQUE (book_id, section_number)
);

CREATE TABLE options
(
    id                  BIGSERIAL PRIMARY KEY,
    section_id          BIGINT       NOT NULL,
    description         VARCHAR(255) NOT NULL,
    goto_section_number BIGINT       NOT NULL,
    created_at          TIMESTAMP,
    updated_at          TIMESTAMP,

    CONSTRAINT fk_options_section
        FOREIGN KEY (section_id)
            REFERENCES sections (id)
            ON DELETE CASCADE
);

CREATE TABLE consequences
(
    id         BIGSERIAL PRIMARY KEY,
    option_id  BIGINT      NOT NULL UNIQUE,
    type       VARCHAR(30) NOT NULL,
    value      INTEGER,
    text       TEXT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,

    CONSTRAINT fk_consequence_option
        FOREIGN KEY (option_id)
            REFERENCES options (id)
            ON DELETE CASCADE
);

CREATE TABLE games
(
    id                 BIGSERIAL PRIMARY KEY,
    player_name        VARCHAR(255) NOT NULL,
    health             INTEGER      NOT NULL,
    status             VARCHAR(20)  NOT NULL,
    book_id            BIGINT       NOT NULL,
    current_section_id BIGINT       NOT NULL,
    created_at         TIMESTAMP,
    updated_at         TIMESTAMP,

    CONSTRAINT fk_games_book
        FOREIGN KEY (book_id)
            REFERENCES books (id),

    CONSTRAINT fk_games_current_section
        FOREIGN KEY (current_section_id)
            REFERENCES sections (id)
);

CREATE INDEX idx_sections_book
    ON sections (book_id);

CREATE INDEX idx_options_section
    ON options (section_id);

CREATE INDEX idx_games_book
    ON games (book_id);

CREATE INDEX idx_games_current_section
    ON games (current_section_id);

CREATE INDEX idx_book_title
    ON books (title);

CREATE INDEX idx_book_author
    ON books (author);

CREATE INDEX idx_book_difficulty
    ON books (difficulty_level);

CREATE INDEX idx_category_name
    ON categories (name);