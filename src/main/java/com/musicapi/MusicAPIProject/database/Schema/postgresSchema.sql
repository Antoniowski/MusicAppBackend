DROP TABLE IF EXISTS songs;
DROP TABLE IF EXISTS album;

CREATE TABLE album (
    id SERIAL NOT NULL,
    name VARCHAR(255),
    songs INT[],
    PRIMARY KEY(id),
);

CREATE TABLE songs (
    id SERIAL NOT NULL,
    name VARCHAR(255),
    duration INT,
    album INT,
    music_path VARCHAR(255),
    image_path VARCHAR(255),
    PRIMARY KEY(id),
    CONSTRAINT fk_album
        FOREIGN KEY(album) 
            REFERENCES album(id) 
);

INSERT INTO album(name, songs) VALUES('testone', ARRAY[1, 3, 4]);
INSERT INTO album(name, songs) VALUES('rabbia repressa', ARRAY[2]);
INSERT INTO album(name, songs) VALUES('famiglia', ARRAY[5, 6]);
INSERT INTO album(name, songs) VALUES('morti per caso', ARRAY[8]);

INSERT INTO songs(name, duration, album) VALUES('mammt a gnoccolara', 69, 2);
INSERT INTO songs(name, duration, album) VALUES('pat t o ricuttar', 44, 2);
INSERT INTO songs(name, duration, album) VALUES('test', 100, 1);
INSERT INTO songs(name, duration, album) VALUES('tautone', 69, 4);