CREATE TABLE profiles (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    bio TEXT,

    CONSTRAINT fk_profiles_user
        FOREIGN KEY (id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

INSERT INTO profiles (id, username, bio)
SELECT id, username, bio
FROM users;

ALTER TABLE users
DROP COLUMN username,
DROP COLUMN bio;