CREATE TABLE login_details (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_username VARCHAR(100) NOT NULL,
    user_password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_login_details_username (user_username)
);