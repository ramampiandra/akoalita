CREATE TABLE IF NOT EXISTS player(Id INTEGER NOT NULL, 
                     email VARCHAR(100) NOT NULL,
                     username VARCHAR(100) DEFAULT NULL,
                     password VARCHAR(100) DEFAULT NULL,
                     id_game INTEGER DEFAULT NULL,
                     PRIMARY KEY (Id) );
                     
CREATE TABLE IF NOT EXISTS game(Id INTEGER NOT NULL, 
                     gameStatus VARCHAR(100) NOT NULL,
                     player_one_id INTEGER DEFAULT NULL,
                     player_two_id INTEGER DEFAULT NULL,
                     created DATE,
                     PRIMARY KEY (Id) );
                     