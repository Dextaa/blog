CREATE DATABASE typesafe_ids;
CREATE USER 'demo_user'@'localhost' IDENTIFIED BY 'letmein';
GRANT SELECT, INSERT, UPDATE, CREATE, DELETE, DROP, EXECUTE ON typesafe_ids.* TO 'demo_user'@'localhost';
