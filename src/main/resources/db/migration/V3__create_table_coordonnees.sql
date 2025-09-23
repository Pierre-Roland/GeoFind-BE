-- Création de la table users dans le schéma demo
CREATE TABLE IF NOT EXISTS geofind.coordonnees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    zoom LONG NOT NULL,
    center1 float NOT NULL,
    center2 float NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);