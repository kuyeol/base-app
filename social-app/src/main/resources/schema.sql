-- INSERT INTO person (id, username) VALUES (1, 'Entertainment');
-- INSERT INTO person (id, INSERT INTO person (id, name, parent) VALUES (2, 'Movies', '1');
-- , parent) VALUES (2, 'Movies', '1');
-- INSERT INTO person (id, INSERT INTO person (id, name, parent) VALUES (2, 'Movies', '1');
-- , parent) VALUES (3, 'Music', '1');
-- INSERT INTO person (id, INSERT INTO person (id, name, parent) VALUES (2, 'Movies', '1');
-- , parent) VALUES (4, 'Games', '1');
-- INSERT INTO person (id, INSERT INTO person (id, name, parent) VALUES (2, 'Movies', '1');
-- ) VALUES (10, 'Electronics');
-- INSERT INTO person (id, INSERT INTO person (id, name, parent) VALUES (2, 'Movies', '1');
-- , parent) VALUES (12, 'TV', '10');
-- INSERT INTO person (id, INSERT INTO person (id, name, parent) VALUES (2, 'Movies', '1');
-- , parent) VALUES (13, 'Cellphones', '10');
-- INSERT INTO person (id, INSERT INTO person (id, name, parent) VALUES (2, 'Movies', '1');
-- , parent) VALUES (14, 'DVD Players', '10');
--
-- INSERT INTO message (id, followname,messages) VALUES (1, 29.99, 'Return of the Jedi  Episode 6, Luke has the final ');
-- INSERT INTO message (id, followname,messages) VALUES (2, 29.99, 'Return of the Jedi  Episode 6, Luke has the final ');
--
--
-- INSERT INTO follow (id, followid, personid)
-- VALUES (1,1,2);
-- INSERT INTO follow (id, followid, personid)
-- VALUES (2,2,2);

INSERT INTO person(id,username) VALUES (1,'userA');
INSERT INTO person(id,username) VALUES (2,'userB');

INSERT INTO follow(id,requestId,targetId) VALUES (1,'userA','userB');

INSERT INTO message(id,senderId,reciverId,content) VALUES (1,'userA','userB','HELLO WORLD!');



-- CREATE TABLE IF NOT EXISTS PUBLIC."user" (
--     ID INTEGER NOT NULL,
--     USERNAME CHARACTER NOT NULL,
--     PRIMARY KEY (ID)
--     );
--
-- CREATE TABLE IF NOT EXISTS PUBLIC.FOLLOW (
--     ID INTEGER,
--     REQUESTID INTEGER NOT NULL,
--     TARGETID INTEGER NOT NULL,
--     PRIMARY KEY (ID)
--     );
--
-- CREATE TABLE IF NOT EXISTS PUBLIC.MESSAGE (
--     ID INTEGER,
--     SENDER INTEGER NOT NULL,
--     RECIVER INTEGER NOT NULL,
--     CONTENT TEXT,
--     PRIMARY KEY (ID)
--     );
