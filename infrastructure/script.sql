CREATE TABLE `users`
(
    `id`         integer PRIMARY KEY AUTO_INCREMENT,
    `email`      varchar(255) NOT NULL,
    `name`       varchar(255) NOT NULL,
    `password`   varchar(255) NOT NULL,
    `created_at` timestamp,
    `updated_at` timestamp
);

CREATE TABLE `posts`
(
    `id`          integer PRIMARY KEY AUTO_INCREMENT,
    `title`       varchar(255),
    `topic_id`    integer NOT NULL,
    `content`     varchar(2000),
    `owner_id`    integer NOT NULL,
    `created_at`  timestamp,
    `updated_at`  timestamp
);

CREATE TABLE `comments`
(
    `id`         integer PRIMARY KEY AUTO_INCREMENT,
    `post_id`    integer NOT NULL,
    `user_id`    integer,
    `message`    varchar(2000) NOT NULL ,
    `created_at` timestamp,
    `updated_at` timestamp
);

CREATE TABLE `topics`
(
    `id`            integer PRIMARY KEY AUTO_INCREMENT,
    `name`          varchar(255) NOT NULL,
    `description`   varchar(2000)
);

CREATE TABLE `subscriptions`
(
    `user_id`       integer,
    `topic_id`      integer,
    PRIMARY KEY (user_id, topic_id)
);


CREATE UNIQUE INDEX `users_index` ON `users` (`email`);

ALTER TABLE `posts` ADD FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`);
ALTER TABLE `posts` ADD FOREIGN KEY (`topic_id`) REFERENCES `topics` (`id`);

ALTER TABLE `comments` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
ALTER TABLE `comments` ADD FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`);

ALTER TABLE `subscriptions` ADD FOREIGN KEY (`topic_id`) REFERENCES `topics` (`id`);
ALTER TABLE `subscriptions` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
