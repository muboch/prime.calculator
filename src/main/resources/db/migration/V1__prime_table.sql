-- start writing your steps here

CREATE SEQUENCE hibernate_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
-- Table: "PERSONNELS"
-- DROP TABLE "PERSONNELS";
CREATE TABLE PERSONNELS
(
  per_pk bigint NOT NULL,
  nom character varying(256),
  prenom character varying(256),
  adresse character varying(256)
)
WITH (
  OIDS=FALSE
);
