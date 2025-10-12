-- Création de la table users dans le schéma demo
CREATE TABLE IF NOT EXISTS geofind.description_lieu (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    lieu varchar(255) NOT NULL,
    image varchar(1024) NOT NULL,
    description TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);