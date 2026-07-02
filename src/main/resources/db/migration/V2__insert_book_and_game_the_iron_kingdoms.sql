DO
$$
    DECLARE
        v_player_id          BIGINT;
        v_book_id            BIGINT;
        v_category_fantasy   BIGINT;
        v_category_medieval  BIGINT;
        v_category_adventure BIGINT;
        v_section_1          BIGINT;
        v_section_20         BIGINT;
        v_section_40         BIGINT;
        v_section_60         BIGINT;
        v_section_80         BIGINT;
        v_section_100        BIGINT;
        v_section_120        BIGINT;
        v_section_140        BIGINT;
        v_section_160        BIGINT;
        v_section_180        BIGINT;
        v_section_200        BIGINT;
        v_section_666        BIGINT;
        v_option_id          BIGINT;
    BEGIN
        INSERT INTO players (email, name, created_at, updated_at)
        VALUES ('iron.player@adventure.local', 'Iron Player', NOW(), NOW())
        RETURNING id INTO v_player_id;

        INSERT INTO books (title, author, difficulty_level, created_at, updated_at, version)
        VALUES ('The Iron Kingdoms', 'Adrian Blackwood', 'HARD', NOW(), NOW(), 1)
        RETURNING id INTO v_book_id;

        INSERT INTO categories (name, created_at, updated_at, version)
        VALUES ('Fantasy', NOW(), NOW(), 1)
        ON CONFLICT ON CONSTRAINT uk_categories_name DO NOTHING;

        INSERT INTO categories (name, created_at, updated_at, version)
        VALUES ('Medieval', NOW(), NOW(), 2)
        ON CONFLICT ON CONSTRAINT uk_categories_name DO NOTHING;

        INSERT INTO categories (name, created_at, updated_at, version)
        VALUES ('Political', NOW(), NOW(), 3)
        ON CONFLICT ON CONSTRAINT uk_categories_name DO NOTHING;

        INSERT INTO categories (name, created_at, updated_at, version)
        VALUES ('Adventure', NOW(), NOW(), 4)
        ON CONFLICT ON CONSTRAINT uk_categories_name DO NOTHING;

        SELECT id INTO v_category_fantasy FROM categories WHERE name = 'Fantasy';
        SELECT id INTO v_category_medieval FROM categories WHERE name = 'Medieval';

        SELECT id INTO v_category_adventure FROM categories WHERE name = 'Adventure';

        INSERT INTO book_categories (book_id, category_id)
        VALUES (v_book_id, v_category_fantasy),
               (v_book_id, v_category_medieval),
               (v_book_id, v_category_adventure)
        ON CONFLICT DO NOTHING;

        INSERT INTO sections (id, book_id, text, type, created_at, updated_at)
        VALUES (1, v_book_id,
                'The king is dead. The throne room is silent, but every noble in the capital is already choosing a side.',
                'BEGIN', NOW(), NOW())
        RETURNING id INTO v_section_1;

        INSERT INTO sections (id, book_id, text, type, created_at, updated_at)
        VALUES (2, v_book_id,
                'The Council of Lords gathers under candlelight. Every word sounds polite, but every smile hides a dagger.',
                'NODE', NOW(), NOW())
        RETURNING id INTO v_section_20;

        INSERT INTO sections (id, book_id, text, type, created_at, updated_at)
        VALUES (3, v_book_id,
                'At the Northern Wall, ancient horns echo across the snow. Something old has awakened beyond the ice.',
                'NODE', NOW(), NOW())
        RETURNING id INTO v_section_40;

        INSERT INTO sections (id, book_id, text, type, created_at, updated_at)
        VALUES (4, v_book_id,
                'An assassin waits in the shadows of the royal corridor. The attack is sudden and precise.', 'NODE',
                NOW(), NOW())
        RETURNING id INTO v_section_60;

        INSERT INTO sections (id, book_id, text, type, created_at, updated_at)
        VALUES (5, v_book_id,
                'The Royal Treasury is nearly empty. Gold can buy loyalty, but only if you spend it wisely.', 'NODE',
                NOW(), NOW())
        RETURNING id INTO v_section_80;

        INSERT INTO sections (id, book_id, text, type, created_at, updated_at)
        VALUES (6, v_book_id,
                'War reaches the capital. Banners burn, soldiers fall, and the fate of the kingdom depends on your command.',
                'NODE', NOW(), NOW())
        RETURNING id INTO v_section_100;

        INSERT INTO sections (id, book_id, text, type, created_at, updated_at)
        VALUES (7, v_book_id, 'Inside the mountain, a sleeping dragon opens one golden eye. Power has a price.', 'NODE',
                NOW(), NOW())
        RETURNING id INTO v_section_120;

        INSERT INTO sections (id, book_id, text, type, created_at, updated_at)
        VALUES (8, v_book_id, 'The Frozen Lands stretch endlessly before you. The dead do not rest here.', 'NODE',
                NOW(), NOW())
        RETURNING id INTO v_section_140;

        INSERT INTO sections (id, book_id, text, type, created_at, updated_at)
        VALUES (9, v_book_id, 'You enter the Crown Room. The Iron Crown waits on the black stone throne.', 'NODE',
                NOW(), NOW())
        RETURNING id INTO v_section_160;

        INSERT INTO sections (id, book_id, text, type, created_at, updated_at)
        VALUES (10, v_book_id, 'You are forced into exile. The kingdom survives, but without you.', 'END', NOW(), NOW())
        RETURNING id INTO v_section_180;

        INSERT INTO sections (id, book_id, text, type, created_at, updated_at)
        VALUES (11, v_book_id,
                'The kingdom bends the knee. Against betrayal, war and ancient darkness, you have claimed victory.',
                'END', NOW(), NOW())
        RETURNING id INTO v_section_200;

        INSERT INTO sections (id, book_id, text, type, created_at, updated_at)
        VALUES (12, v_book_id, 'Your enemies win. At dawn, the executioner raises the axe.', 'END', NOW(), NOW())
        RETURNING id INTO v_section_666;

        INSERT INTO games (player_id, health, status, book_id, current_section_id, created_at, updated_at, version)
        VALUES (v_player_id, 10, 'PLAYING', v_book_id, v_section_1, NOW(), NOW(), 1);

        INSERT INTO options (section_id, description, next_section_id, created_at, updated_at)
        VALUES (v_section_1, 'Support House Ravenwood', v_section_20, NOW(), NOW());

        INSERT INTO options (section_id, description, next_section_id, created_at, updated_at)
        VALUES (v_section_1, 'Ride north to investigate the old wall', v_section_40, NOW(), NOW());

        INSERT INTO options (section_id, description, next_section_id, created_at, updated_at)
        VALUES (v_section_20, 'Attempt diplomacy between the houses', v_section_80, NOW(), NOW());

        INSERT INTO options (section_id, description, next_section_id, created_at, updated_at)
        VALUES (v_section_20, 'Arrest the most dangerous nobles', v_section_60, NOW(), NOW())
        RETURNING id INTO v_option_id;

        INSERT INTO consequences (option_id, type, value, text, created_at, updated_at)
        VALUES (v_option_id, 'LOSE_HEALTH', 3, 'The nobles resist. A hidden blade cuts your arm during the arrest.',
                NOW(), NOW());

        INSERT INTO options (section_id, description, next_section_id, created_at, updated_at)
        VALUES (v_section_40, 'Explore the abandoned fortress beyond the wall', v_section_140, NOW(), NOW());

        INSERT INTO options (section_id, description, next_section_id, created_at, updated_at)
        VALUES (v_section_40, 'Return to the capital with a warning', v_section_20, NOW(), NOW());

        INSERT INTO options (section_id, description, next_section_id, created_at, updated_at)
        VALUES (v_section_60, 'Fight the assassin', v_section_100, NOW(), NOW())
        RETURNING id INTO v_option_id;

        INSERT INTO consequences (option_id, type, value, text, created_at, updated_at)
        VALUES (v_option_id, 'LOSE_HEALTH', 4, 'The assassin wounds you before disappearing into the smoke.', NOW(),
                NOW());

        INSERT INTO options (section_id, description, next_section_id, created_at, updated_at)
        VALUES (v_section_60, 'Escape through the servant tunnels', v_section_180, NOW(), NOW());

        INSERT INTO options (section_id, description, next_section_id, created_at, updated_at)
        VALUES (v_section_80, 'Use the gold to hire mercenaries', v_section_100, NOW(), NOW());

        INSERT INTO options (section_id, description, next_section_id, created_at, updated_at)
        VALUES (v_section_80, 'Save the treasury and seek ancient power', v_section_120, NOW(), NOW());

        INSERT INTO options (section_id, description, next_section_id, created_at, updated_at)
        VALUES (v_section_100, 'Lead the army from the front line', v_section_160, NOW(), NOW())
        RETURNING id INTO v_option_id;

        INSERT INTO consequences (option_id, type, value, text, created_at, updated_at)
        VALUES (v_option_id, 'LOSE_HEALTH', 5, 'You inspire your soldiers, but the battle nearly kills you.', NOW(),
                NOW());

        INSERT INTO options (section_id, description, next_section_id, created_at, updated_at)
        VALUES (v_section_100, 'Retreat and regroup', v_section_180, NOW(), NOW());

        INSERT INTO options (section_id, description, next_section_id, created_at, updated_at)
        VALUES (v_section_120, 'Try to tame the dragon', v_section_200, NOW(), NOW())
        RETURNING id INTO v_option_id;

        INSERT INTO consequences (option_id, type, value, text, created_at, updated_at)
        VALUES (v_option_id, 'GAIN_HEALTH', 5, 'The dragon accepts you. Ancient fire strengthens your spirit.', NOW(),
                NOW());

        INSERT INTO options (section_id, description, next_section_id, created_at, updated_at)
        VALUES (v_section_120, 'Attack the dragon', v_section_666, NOW(), NOW())
        RETURNING id INTO v_option_id;

        INSERT INTO consequences (option_id, type, value, text, created_at, updated_at)
        VALUES (v_option_id, 'LOSE_HEALTH', 10, 'Steel cannot defeat ancient fire.', NOW(), NOW());

        INSERT INTO options (section_id, description, next_section_id, created_at, updated_at)
        VALUES (v_section_140, 'Face the army of the dead', v_section_666, NOW(), NOW())
        RETURNING id INTO v_option_id;

        INSERT INTO consequences (option_id, type, value, text, created_at, updated_at)
        VALUES (v_option_id, 'LOSE_HEALTH', 10, 'The frozen dead overwhelm your forces.', NOW(), NOW());

        INSERT INTO options (section_id, description, next_section_id, created_at, updated_at)
        VALUES (v_section_140, 'Search for the dragon mountain', v_section_120, NOW(), NOW());

        INSERT INTO options (section_id, description, next_section_id, created_at, updated_at)
        VALUES (v_section_160, 'Take the Iron Crown', v_section_200, NOW(), NOW());

        INSERT INTO options (section_id, description, next_section_id, created_at, updated_at)
        VALUES (v_section_160, 'Refuse the crown and leave the kingdom', v_section_180, NOW(), NOW());

    END
$$;