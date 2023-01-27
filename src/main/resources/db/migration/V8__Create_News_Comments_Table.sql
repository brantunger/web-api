CREATE TABLE news_comments (
    COMMENT_ID INT NOT NULL AUTO_INCREMENT,
    NEWS_ID INT NOT NULL,
    PARENT_ID INT,
    CONTENT LONGTEXT NOT NULL,
    CREATED_BY VARCHAR(50) NOT NULL,
    DATE_CREATED TIMESTAMP NOT NULL,
    CONSTRAINT COMMENT_PK PRIMARY KEY (COMMENT_ID)
);

CREATE INDEX IDX_COMMENTS_NEWS_ID ON news_comments(NEWS_ID);

INSERT INTO news_comments (COMMENT_ID, NEWS_ID, PARENT_ID, CONTENT, CREATED_BY, DATE_CREATED)
VALUES  (1, 1, null, 'I am a base comment', 'admin', '2023-01-22 06:59:00'),
        (2, 1, 1, 'I am a reply', 'user', '2023-01-22 07:00:00'),
        (3, 1, 2, 'I am a nested reply', 'user2', '2023-01-22 07:03:00'),
        (4, 1, 1, 'I am a nested reply', 'user2', '2023-01-22 07:03:00'),
        (5, 1, null, 'I am a base comment', 'user', '2023-01-25 08:36:32'),
        (6, 1, 3, 'I am a super nested reply', 'admin', '2023-01-25 15:34:01');