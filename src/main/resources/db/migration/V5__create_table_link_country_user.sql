-- Création de la table users dans le schéma demo
CREATE TABLE IF NOT EXISTS geofind.link_country_user (
    id_user BIGINT NOT NULL,
    id_country BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_user, id_country),
    FOREIGN KEY (id_user) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (id_country) REFERENCES coordonnees(id) ON DELETE CASCADE
);