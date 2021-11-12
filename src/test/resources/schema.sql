set search_path = wormhole;

DROP TABLE IF EXISTS reqRecord;
CREATE TABLE reqRecord (
   id bigserial NOT NULL,
   url character varying NOT NULL,
   ip character varying NOT NULL,
   duration int NOT NULL,
   os character varying NOT NULL,
   device character varying NOT NULL,
   browser character varying NOT NULL,
   status character varying NOT NULL,
   createAt timestamp with time zone NOT NULL,
   updateAt timestamp with time zone NOT NULL,
   PRIMARY KEY (id)
);
