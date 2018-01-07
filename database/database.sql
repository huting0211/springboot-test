Create Database hellotest;

use hellotest;


CREATE  TABLE IF NOT EXISTS testdb.users (
  username VARCHAR(20) NOT NULL ,
  password VARCHAR(20) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username));


CREATE TABLE IF NOT EXISTS testdb.`authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `ix_auth_username` (`username`,`authority`),
  CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
);



INSERT INTO testdb.users(username,password,enabled) VALUES ('bennett','huang', true);
INSERT INTO testdb.users(username,password,enabled) VALUES ('anthony','huang', true);

INSERT INTO testdb.`authorities` (username, authority) VALUES ('anthony', 'ROLE_USER');
INSERT INTO testdb.`authorities` (username, authority) VALUES ('anthony', 'ROLE_ADMIN');
INSERT INTO testdb.`authorities` (username, authority) VALUES ('bennett', 'ROLE_USER');


grant ALL on testdb.* to hellotest