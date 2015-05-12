-- Table: users

CREATE TABLE users
(
  users_pk bigint NOT NULL,
  name character varying(128) NOT NULL,
  pw character varying(96) NOT NULL, -- from the hash computation
  CONSTRAINT users_pkey PRIMARY KEY (users_pk)
);

CREATE INDEX "IX_users_name"
  ON users
  USING btree
  (name COLLATE pg_catalog."default");

-- the password is the hashed value of 'password' (see PasswordHash.java)
INSERT INTO users(users_pk, name, pw)
    VALUES (nextval('hibernate_sequence'), 'admin', 'e5ce3c8ac97ee444ecd2505c022aaabb00eefb788ed525ea12d63178f4efbd20e6fbdac58fc2d8b169321acc1b00120a');
