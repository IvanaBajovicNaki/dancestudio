
SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;


CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;


CREATE TABLE danceprogram (
    id integer NOT NULL,
    name character(200)
);


ALTER TABLE public.danceprogram OWNER TO igor.bajovic;

CREATE TABLE gymnastichall (
    id numeric NOT NULL,
    name character(200)
);


ALTER TABLE public.gymnastichall OWNER TO igor.bajovic;

CREATE TABLE lesonsprogram (
    id integer NOT NULL,
    name character(200)
);


ALTER TABLE public.lesonsprogram OWNER TO igor.bajovic;


CREATE TABLE mark (
    id integer NOT NULL,
    value integer
);


ALTER TABLE public.mark OWNER TO igor.bajovic;


CREATE TABLE member (
    id integer NOT NULL,
    name character(200),
    surname character(200),
    danceprogram_id integer,
    gymnastichall_id integer,
    lesonsnumbermonth integer,
    coachcommitment integer,
    fee real
);


ALTER TABLE public.member OWNER TO igor.bajovic;


CREATE TABLE trainerprogram (
    id integer NOT NULL,
    name character(200)
);


ALTER TABLE public.trainerprogram OWNER TO igor.bajovic;


ALTER TABLE ONLY lesonsprogram
    ADD CONSTRAINT lesonsprogram_pkey PRIMARY KEY (id);

ALTER TABLE ONLY mark
    ADD CONSTRAINT mark_pkey PRIMARY KEY (id);

ALTER TABLE ONLY danceprogram
    ADD CONSTRAINT pk_danceprogram PRIMARY KEY (id);


ALTER TABLE ONLY gymnastichall
    ADD CONSTRAINT pk_gymnastichall PRIMARY KEY (id);


ALTER TABLE ONLY member
    ADD CONSTRAINT pk_member PRIMARY KEY (id);


ALTER TABLE ONLY trainerprogram
    ADD CONSTRAINT trainerprogram_pkey PRIMARY KEY (id);


ALTER TABLE ONLY member
    ADD CONSTRAINT fk_danceprogram_member FOREIGN KEY (danceprogram_id) REFERENCES danceprogram(id);


ALTER TABLE ONLY member
    ADD CONSTRAINT fk_gymnastichall_member FOREIGN KEY (gymnastichall_id) REFERENCES gymnastichall(id);


INSERT INTO public.danceprogram VALUES
    (1, 'Salsa');
INSERT INTO public.danceprogram VALUES
    (2, 'Folklor');
INSERT INTO public.danceprogram VALUES
    (3, 'Hip-hop');

INSERT INTO public.gymnastichall VALUES
    (1, 'Stari DIF sala');
INSERT INTO public.gymnastichall VALUES
    (2, 'Akud Lola');
INSERT INTO public.gymnastichall VALUES
    (3, 'Teretana Gym');

INSERT INTO public.member VALUES
    (1, 'Ivana', 'Bajovic', 1, 1, 10, 20, null);
INSERT INTO public.member VALUES
    (2, 'Igor', 'Bajovic', 2, 2, 20, 50, null);
INSERT INTO public.member VALUES
    (3, 'Marija', 'Boljanac', 3, 3, 27, 75, null);


REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM igor.bajovic;
GRANT ALL ON SCHEMA public TO igor.bajovic;
GRANT ALL ON SCHEMA public TO PUBLIC;

