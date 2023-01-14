CREATE TABLE company_info (
    ID INT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(50),
    SHORT_DESCRIPTION VARCHAR(250),
    ADDRESS VARCHAR(100),
    EMAIL VARCHAR(50),
    PHONE VARCHAR(50),
    FAX VARCHAR(50),
    FACEBOOK_URL VARCHAR(250),
    TWITTER_URL VARCHAR(250),
    INSTAGRAM_URL VARCHAR(250),
    LINKED_IN_URL VARCHAR(250),
    GITHUB_URL VARCHAR(250),
    CONSTRAINT COMPANY_INFO_PK PRIMARY KEY (ID)
);

-- Defaults
INSERT INTO company_info (NAME, SHORT_DESCRIPTION, ADDRESS, EMAIL, PHONE, FAX, FACEBOOK_URL, TWITTER_URL, INSTAGRAM_URL, LINKED_IN_URL, GITHUB_URL)
VALUES ('Dreadfall', 'Our CMS allows you to add content to your website dynamically, using a beautiful responsive web design!', '5304 S 106th Place, Mesa, AZ 85212', 'admin@dreadfall.com', '+1 480-555-5555', '+1 480-555-5555', 'https://facebook.com', 'https://twitter.com', 'https://instagram.com', 'https://www.linkedin.com/in/brant-unger', 'https://github.com/brantunger');