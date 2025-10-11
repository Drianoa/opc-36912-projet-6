CREATE TABLE `users`
(
    `id`         integer PRIMARY KEY AUTO_INCREMENT,
    `email`      varchar(255) NOT NULL,
    `username`   varchar(255) NOT NULL,
    `password`   varchar(255) NOT NULL
);

CREATE TABLE `posts`
(
    `id`          integer PRIMARY KEY AUTO_INCREMENT,
    `title`       varchar(255),
    `topic_id`    integer NOT NULL,
    `content`     varchar(2000),
    `owner_id`    integer NOT NULL,
    `created_at`  timestamp NOT NULL
);

CREATE TABLE `comments`
(
    `id`         integer PRIMARY KEY AUTO_INCREMENT,
    `post_id`    integer NOT NULL,
    `user_id`    integer NOT NULL,
    `message`    varchar(2000) NOT NULL ,
    `created_at` timestamp NOT NULL
);

CREATE TABLE `topics`
(
    `id`            integer PRIMARY KEY AUTO_INCREMENT,
    `name`          varchar(255) NOT NULL,
    `description`   varchar(2000)
);

CREATE TABLE `subscriptions`
(
    `user_id`       integer NOT NULL,
    `topic_id`      integer NOT NULL,
    PRIMARY KEY (user_id, topic_id)
);


CREATE UNIQUE INDEX `users_index` ON `users` (`email`);

ALTER TABLE `posts` ADD FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`);
ALTER TABLE `posts` ADD FOREIGN KEY (`topic_id`) REFERENCES `topics` (`id`);

ALTER TABLE `comments` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
ALTER TABLE `comments` ADD FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`);

ALTER TABLE `subscriptions` ADD FOREIGN KEY (`topic_id`) REFERENCES `topics` (`id`);
ALTER TABLE `subscriptions` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

INSERT INTO topics (name, description) VALUES ('Java', 'Java est un langage orienté objet, portable grâce à la JVM, sécurisé, robuste et largement utilisé pour les applications web, mobiles, entreprises et systèmes embarqués.');
INSERT INTO topics (name, description) VALUES ('Javascript', 'JavaScript est un langage de programmation web dynamique, léger, interprété par les navigateurs, permettant d’ajouter interactivité, animations et fonctionnalités côté client.');
INSERT INTO topics (name, description) VALUES ('Angular', 'Angular est un framework JavaScript open-source, maintenu par Google, permettant de créer des applications web modernes, dynamiques, modulaires et performantes.');
INSERT INTO topics (name, description) VALUES ('React', 'React est une bibliothèque JavaScript open-source, développée par Facebook, utilisée pour construire des interfaces utilisateur interactives et réactives, en se basant sur des composants réutilisables et un DOM virtuel.');

INSERT INTO users (email, username, password) VALUES ('a@a.a', 'user1', '{noop}password');
INSERT INTO users (email, username, password) VALUES ('b@b.b', 'user2', '{noop}password');

INSERT INTO subscriptions (user_id, topic_id) VALUES (1, 1);
INSERT INTO subscriptions (user_id, topic_id) VALUES (1, 4);
INSERT INTO subscriptions (user_id, topic_id) VALUES (2, 4);


INSERT INTO posts (title, topic_id, content, owner_id, created_at) VALUES ('Introduction à Java', 1, 'Java est un langage de programmation orienté objet, portable et sécurisé, largement utilisé pour les applications web, mobiles et d\'entreprise.', 1, CURRENT_TIMESTAMP);
INSERT INTO posts (title, topic_id, content, owner_id, created_at) VALUES ('Les bases de JavaScript', 2, 'JavaScript est un langage de programmation léger et dynamique, principalement utilisé pour le développement web côté client, permettant d\'ajouter de l\'interactivité aux pages web.', 1, CURRENT_TIMESTAMP);
INSERT INTO posts (title, topic_id, content, owner_id, created_at) VALUES ('Créer des applications avec spring boot', 1, 'Spring Boot est un framework Java open-source qui facilite la création d\'applications autonomes et prêtes pour la production, en simplifiant la configuration et le déploiement.', 2, CURRENT_TIMESTAMP);
INSERT INTO posts (title, topic_id, content, owner_id, created_at) VALUES ('Les composants en React', 4, 'React est une bibliothèque JavaScript développée par Facebook, utilisée pour construire des interfaces utilisateur interactives et réactives, en se basant sur des composants réutilisables.', 2, CURRENT_TIMESTAMP);

INSERT INTO comments (post_id, user_id, message, created_at) VALUES (1, 2, 'Merci pour cette introduction à Java !', CURRENT_TIMESTAMP);
