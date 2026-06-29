DO $$
DECLARE
    v_book_id BIGINT;
    v_section_id BIGINT;
    v_option_id BIGINT;
BEGIN
    SELECT id INTO v_book_id FROM books WHERE lower(title) = lower($txt$The Crystal Caverns$txt$) AND lower(author) = lower($txt$Evelyn Stormrider$txt$) LIMIT 1;

    IF v_book_id IS NULL THEN
        INSERT INTO books (title, author, difficulty_level, created_at, updated_at)
        VALUES ($txt$The Crystal Caverns$txt$, $txt$Evelyn Stormrider$txt$, 'EASY', now(), now())
        RETURNING id INTO v_book_id;
    END IF;

    INSERT INTO sections (book_id, section_number, text, type, created_at, updated_at)
    VALUES (v_book_id, 1, $txt$You stand at the entrance of the legendary Crystal Caverns. A cold breeze carries whispers from the darkness below. A broken rope bridge leads into the depths.$txt$, 'BEGIN', now(), now())
    ON CONFLICT ON CONSTRAINT uk_book_section_number DO NOTHING;

    INSERT INTO sections (book_id, section_number, text, type, created_at, updated_at)
    VALUES (v_book_id, 20, $txt$Your hands brush against the cold stone until you find a hidden crevice leading downward. It's dark, but you hear faint dripping water.$txt$, 'NODE', now(), now())
    ON CONFLICT ON CONSTRAINT uk_book_section_number DO NOTHING;

    INSERT INTO sections (book_id, section_number, text, type, created_at, updated_at)
    VALUES (v_book_id, 100, $txt$The bridge creaks under your weight. Halfway across, a plank snaps beneath your foot!$txt$, 'NODE', now(), now())
    ON CONFLICT ON CONSTRAINT uk_book_section_number DO NOTHING;

    INSERT INTO sections (book_id, section_number, text, type, created_at, updated_at)
    VALUES (v_book_id, 200, $txt$You emerge into a glittering chamber filled with glowing crystals. At the center lies a pedestal holding an ancient gemstone.$txt$, 'NODE', now(), now())
    ON CONFLICT ON CONSTRAINT uk_book_section_number DO NOTHING;

    INSERT INTO sections (book_id, section_number, text, type, created_at, updated_at)
    VALUES (v_book_id, 300, $txt$You crawl to safety on the other side, breathing heavily. A winding path descends deeper into the cavern.$txt$, 'NODE', now(), now())
    ON CONFLICT ON CONSTRAINT uk_book_section_number DO NOTHING;

    INSERT INTO sections (book_id, section_number, text, type, created_at, updated_at)
    VALUES (v_book_id, 400, $txt$You barely make the jump, but the rope bridge collapses behind you. There's no turning back now.$txt$, 'NODE', now(), now())
    ON CONFLICT ON CONSTRAINT uk_book_section_number DO NOTHING;

    INSERT INTO sections (book_id, section_number, text, type, created_at, updated_at)
    VALUES (v_book_id, 500, $txt$The moment you touch the gemstone, the chamber begins to shake violently. A secret door opens to reveal a hidden passage.$txt$, 'NODE', now(), now())
    ON CONFLICT ON CONSTRAINT uk_book_section_number DO NOTHING;

    INSERT INTO sections (book_id, section_number, text, type, created_at, updated_at)
    VALUES (v_book_id, 600, $txt$You reach a vast underground lake glowing with bioluminescent crystals. A small boat waits at the shore.$txt$, 'NODE', now(), now())
    ON CONFLICT ON CONSTRAINT uk_book_section_number DO NOTHING;

    INSERT INTO sections (book_id, section_number, text, type, created_at, updated_at)
    VALUES (v_book_id, 700, $txt$After a short rest, you feel strong enough to continue your journey.$txt$, 'NODE', now(), now())
    ON CONFLICT ON CONSTRAINT uk_book_section_number DO NOTHING;

    INSERT INTO sections (book_id, section_number, text, type, created_at, updated_at)
    VALUES (v_book_id, 800, $txt$The hidden passage leads to a giant crystal throne room, where an ancient spirit awaits.$txt$, 'END', now(), now())
    ON CONFLICT ON CONSTRAINT uk_book_section_number DO NOTHING;

    INSERT INTO sections (book_id, section_number, text, type, created_at, updated_at)
    VALUES (v_book_id, 900, $txt$The boat carries you safely across, but you sense something massive moving in the depths below.$txt$, 'NODE', now(), now())
    ON CONFLICT ON CONSTRAINT uk_book_section_number DO NOTHING;

    INSERT INTO sections (book_id, section_number, text, type, created_at, updated_at)
    VALUES (v_book_id, 1000, $txt$You manage to swim across, shivering but alive. The glowing crystals ahead light your way to a massive door.$txt$, 'END', now(), now())
    ON CONFLICT ON CONSTRAINT uk_book_section_number DO NOTHING;

    INSERT INTO sections (book_id, section_number, text, type, created_at, updated_at)
    VALUES (v_book_id, 666, $txt$An unreachable dead end. You shouldn't be here.$txt$, 'NODE', now(), now())
    ON CONFLICT ON CONSTRAINT uk_book_section_number DO NOTHING;

    SELECT id INTO v_section_id FROM sections WHERE book_id = v_book_id AND section_number = 1;
    INSERT INTO options (section_id, description, goto_section_number, created_at, updated_at)
    VALUES (v_section_id, $txt$Cross the rope bridge carefully$txt$, 100, now(), now())
    RETURNING id INTO v_option_id;

    INSERT INTO options (section_id, description, goto_section_number, created_at, updated_at)
    VALUES (v_section_id, $txt$Search the rocky walls for another path$txt$, 20, now(), now())
    RETURNING id INTO v_option_id;

    SELECT id INTO v_section_id FROM sections WHERE book_id = v_book_id AND section_number = 20;
    INSERT INTO options (section_id, description, goto_section_number, created_at, updated_at)
    VALUES (v_section_id, $txt$Enter the crevice$txt$, 200, now(), now())
    RETURNING id INTO v_option_id;
    INSERT INTO consequences (option_id, type, value, text, created_at, updated_at)
    VALUES (v_option_id, 'LOSE_HEALTH', 4, $txt$You scrape your shoulder squeezing through the narrow gap.$txt$, now(), now());

    INSERT INTO options (section_id, description, goto_section_number, created_at, updated_at)
    VALUES (v_section_id, $txt$Return to the entrance$txt$, 1, now(), now())
    RETURNING id INTO v_option_id;

    SELECT id INTO v_section_id FROM sections WHERE book_id = v_book_id AND section_number = 100;
    INSERT INTO options (section_id, description, goto_section_number, created_at, updated_at)
    VALUES (v_section_id, $txt$Hold on tightly and crawl across$txt$, 300, now(), now())
    RETURNING id INTO v_option_id;

    INSERT INTO options (section_id, description, goto_section_number, created_at, updated_at)
    VALUES (v_section_id, $txt$Try to jump to the other side$txt$, 400, now(), now())
    RETURNING id INTO v_option_id;
    INSERT INTO consequences (option_id, type, value, text, created_at, updated_at)
    VALUES (v_option_id, 'LOSE_HEALTH', 7, $txt$You land hard and twist your ankle.$txt$, now(), now());

    SELECT id INTO v_section_id FROM sections WHERE book_id = v_book_id AND section_number = 200;
    INSERT INTO options (section_id, description, goto_section_number, created_at, updated_at)
    VALUES (v_section_id, $txt$Take the gemstone$txt$, 500, now(), now())
    RETURNING id INTO v_option_id;

    INSERT INTO options (section_id, description, goto_section_number, created_at, updated_at)
    VALUES (v_section_id, $txt$Inspect the walls carefully$txt$, 600, now(), now())
    RETURNING id INTO v_option_id;

    SELECT id INTO v_section_id FROM sections WHERE book_id = v_book_id AND section_number = 300;
    INSERT INTO options (section_id, description, goto_section_number, created_at, updated_at)
    VALUES (v_section_id, $txt$Follow the path downward$txt$, 600, now(), now())
    RETURNING id INTO v_option_id;

    INSERT INTO options (section_id, description, goto_section_number, created_at, updated_at)
    VALUES (v_section_id, $txt$Rest and recover your strength$txt$, 700, now(), now())
    RETURNING id INTO v_option_id;
    INSERT INTO consequences (option_id, type, value, text, created_at, updated_at)
    VALUES (v_option_id, 'GAIN_HEALTH', 3, $txt$You feel slightly better after resting.$txt$, now(), now());

    SELECT id INTO v_section_id FROM sections WHERE book_id = v_book_id AND section_number = 400;
    INSERT INTO options (section_id, description, goto_section_number, created_at, updated_at)
    VALUES (v_section_id, $txt$Continue deeper into the cavern$txt$, 600, now(), now())
    RETURNING id INTO v_option_id;

    SELECT id INTO v_section_id FROM sections WHERE book_id = v_book_id AND section_number = 500;
    INSERT INTO options (section_id, description, goto_section_number, created_at, updated_at)
    VALUES (v_section_id, $txt$Enter the hidden passage$txt$, 800, now(), now())
    RETURNING id INTO v_option_id;

    INSERT INTO options (section_id, description, goto_section_number, created_at, updated_at)
    VALUES (v_section_id, $txt$Drop the gemstone and run back$txt$, 200, now(), now())
    RETURNING id INTO v_option_id;

    SELECT id INTO v_section_id FROM sections WHERE book_id = v_book_id AND section_number = 600;
    INSERT INTO options (section_id, description, goto_section_number, created_at, updated_at)
    VALUES (v_section_id, $txt$Take the boat across the lake$txt$, 900, now(), now())
    RETURNING id INTO v_option_id;

    INSERT INTO options (section_id, description, goto_section_number, created_at, updated_at)
    VALUES (v_section_id, $txt$Swim across$txt$, 1000, now(), now())
    RETURNING id INTO v_option_id;
    INSERT INTO consequences (option_id, type, value, text, created_at, updated_at)
    VALUES (v_option_id, 'LOSE_HEALTH', 5, $txt$The freezing water chills you to the bone.$txt$, now(), now());

    SELECT id INTO v_section_id FROM sections WHERE book_id = v_book_id AND section_number = 700;
    INSERT INTO options (section_id, description, goto_section_number, created_at, updated_at)
    VALUES (v_section_id, $txt$Head toward the underground lake$txt$, 600, now(), now())
    RETURNING id INTO v_option_id;

    SELECT id INTO v_section_id FROM sections WHERE book_id = v_book_id AND section_number = 900;
    INSERT INTO options (section_id, description, goto_section_number, created_at, updated_at)
    VALUES (v_section_id, $txt$Ignore it and continue forward$txt$, 800, now(), now())
    RETURNING id INTO v_option_id;

    INSERT INTO options (section_id, description, goto_section_number, created_at, updated_at)
    VALUES (v_section_id, $txt$Investigate the movement$txt$, 666, now(), now())
    RETURNING id INTO v_option_id;

END $$;
