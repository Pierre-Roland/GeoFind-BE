CREATE TABLE IF NOT EXISTS geofind.password_reset_token (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    token VARCHAR(100) NOT NULL,
    expirydate TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES geofind.users(id) ON DELETE CASCADE,
    CONSTRAINT uq_token UNIQUE (token)
);


