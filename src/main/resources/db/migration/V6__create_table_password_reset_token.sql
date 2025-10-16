CREATE TABLE IF NOT EXISTS test.password_reset_token (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    token VARCHAR(100) NOT NULL,
    expirydate TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id BIGINT NOT NULL,
    CONSTRAINT uq_token UNIQUE (token)
);

-- Ajouter la FK séparément pour éviter certains problèmes TiDB
ALTER TABLE test.password_reset_token
    ADD CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES geofind.users(id) ON DELETE CASCADE;
