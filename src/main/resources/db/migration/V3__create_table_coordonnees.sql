-- Création de la table users dans le schéma demo
CREATE TABLE IF NOT EXISTS test.coordonnees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    zoom BIGINT NOT NULL,
    center1 Double NOT NULL,
    center2 Double NOT NULL,
    country varchar(255) UNIQUE NOT NULL,
    times_visited BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);