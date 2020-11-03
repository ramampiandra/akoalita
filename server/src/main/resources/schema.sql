CREATE TABLE IF NOT EXISTS player_entity(Id INTEGER NOT NULL, 
                     email VARCHAR(100) NOT NULL,
                     username VARCHAR(100) DEFAULT NULL,
                     password VARCHAR(100) DEFAULT NULL,
                     id_game INTEGER DEFAULT NULL,
                     PRIMARY KEY (Id) );
                     
CREATE TABLE IF NOT EXISTS move_history(Id INTEGER NOT NULL, 
                     last_position INTEGER DEFAULT NULL,
                     direction_history VARCHAR(100) DEFAULT NULL,
                     player_one_id INTEGER DEFAULT NULL,
                     PRIMARY KEY (Id) );
                     
CREATE TABLE IF NOT EXISTS game_entity(Id INTEGER NOT NULL, 
                     game_status VARCHAR(100) NOT NULL,
                     player_one_id INTEGER DEFAULT NULL,
                     player_two_id INTEGER DEFAULT NULL,
                     last_states VARCHAR(100) DEFAULT NULL,
                     created DATE,
                     PRIMARY KEY (Id) );
                     