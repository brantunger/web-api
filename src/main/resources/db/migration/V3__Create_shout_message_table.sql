CREATE TABLE shout_message
(
    MESSAGE_ID INT NOT NULL AUTO_INCREMENT,
    USERNAME VARCHAR(50) NOT NULL,
    MESSAGE VARCHAR(255) NOT NULL,
    DATE_CREATED TIMESTAMP NOT NULL,
    CONSTRAINT SHOUT_MESSAGE_PK PRIMARY KEY (MESSAGE_ID)
);