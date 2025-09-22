-- Créer la base demo
CREATE DATABASE IF NOT EXISTS demo;

-- Créer l'utilisateur pierr pour les migrations normales
CREATE USER IF NOT EXISTS 'pierr'@'%' IDENTIFIED BY 'ton_password';
GRANT ALL PRIVILEGES ON demo.* TO 'pierr'@'%';
FLUSH PRIVILEGES;
