--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2014-10-03 03:12:42

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 176 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1976 (class 0 OID 0)
-- Dependencies: 176
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 170 (class 1259 OID 16395)
-- Name: danceprogram; Type: TABLE; Schema: public; Owner: ivana; Tablespace: 
--

CREATE TABLE danceprogram (
    id integer NOT NULL,
    name character(200)
);


ALTER TABLE public.danceprogram OWNER TO ivana;

--
-- TOC entry 171 (class 1259 OID 16398)
-- Name: gymnastichall; Type: TABLE; Schema: public; Owner: ivana; Tablespace: 
--

CREATE TABLE gymnastichall (
    id numeric NOT NULL,
    name character(200)
);


ALTER TABLE public.gymnastichall OWNER TO ivana;

--
-- TOC entry 174 (class 1259 OID 16429)
-- Name: lesonsprogram; Type: TABLE; Schema: public; Owner: ivana; Tablespace: 
--

CREATE TABLE lesonsprogram (
    id integer NOT NULL,
    name character(200)
);


ALTER TABLE public.lesonsprogram OWNER TO ivana;

--
-- TOC entry 173 (class 1259 OID 16423)
-- Name: mark; Type: TABLE; Schema: public; Owner: ivana; Tablespace: 
--

CREATE TABLE mark (
    id integer NOT NULL,
    value integer
);


ALTER TABLE public.mark OWNER TO ivana;

--
-- TOC entry 172 (class 1259 OID 16404)
-- Name: member; Type: TABLE; Schema: public; Owner: ivana; Tablespace: 
--

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


ALTER TABLE public.member OWNER TO ivana;

--
-- TOC entry 175 (class 1259 OID 16438)
-- Name: trainerprogram; Type: TABLE; Schema: public; Owner: ivana; Tablespace: 
--

CREATE TABLE trainerprogram (
    id integer NOT NULL,
    name character(200)
);


ALTER TABLE public.trainerprogram OWNER TO ivana;

--
-- TOC entry 1963 (class 0 OID 16395)
-- Dependencies: 170
-- Data for Name: danceprogram; Type: TABLE DATA; Schema: public; Owner: ivana
--

COPY danceprogram (id, name) FROM stdin;
1	Salsa                                                                                                                                                                                                   
3	Hip-hop                                                                                                                                                                                                 
4	Folklor                                                                                                                                                                                                 
\.


--
-- TOC entry 1964 (class 0 OID 16398)
-- Dependencies: 171
-- Data for Name: gymnastichall; Type: TABLE DATA; Schema: public; Owner: ivana
--

COPY gymnastichall (id, name) FROM stdin;
1	DIF sala                                                                                                                                                                                                
2	Akud "Lola"                                                                                                                                                                                             
3	Teretana Gym                                                                                                                                                                                            
\.


--
-- TOC entry 1967 (class 0 OID 16429)
-- Dependencies: 174
-- Data for Name: lesonsprogram; Type: TABLE DATA; Schema: public; Owner: ivana
--

COPY lesonsprogram (id, name) FROM stdin;
1	Six lesons per month                                                                                                                                                                                    
2	Ten lesons per month                                                                                                                                                                                    
3	Twelve lesons per month                                                                                                                                                                                 
4	Twenty lesons per month                                                                                                                                                                                 
5	Every day                                                                                                                                                                                               
\.


--
-- TOC entry 1966 (class 0 OID 16423)
-- Dependencies: 173
-- Data for Name: mark; Type: TABLE DATA; Schema: public; Owner: ivana
--

COPY mark (id, value) FROM stdin;
1	1
2	2
3	3
4	4
5	5
\.


--
-- TOC entry 1965 (class 0 OID 16404)
-- Dependencies: 172
-- Data for Name: member; Type: TABLE DATA; Schema: public; Owner: ivana
--

COPY member (id, name, surname, danceprogram_id, gymnastichall_id, lesonsnumbermonth, coachcommitment, fee) FROM stdin;
1	Ivana                                                                                                                                                                                                   	Bajovic                                                                                                                                                                                                 	3	1	30	90	5500
2	Marija                                                                                                                                                                                                  	Simic                                                                                                                                                                                                   	4	2	12	5	1000
3	Igor                                                                                                                                                                                                    	Milic                                                                                                                                                                                                   	3	3	12	50	\N
\.


--
-- TOC entry 1968 (class 0 OID 16438)
-- Dependencies: 175
-- Data for Name: trainerprogram; Type: TABLE DATA; Schema: public; Owner: ivana
--

COPY trainerprogram (id, name) FROM stdin;
1	Low                                                                                                                                                                                                     
2	Middle                                                                                                                                                                                                  
3	High                                                                                                                                                                                                    
\.


--
-- TOC entry 1851 (class 2606 OID 16433)
-- Name: lesonsprogram_pkey; Type: CONSTRAINT; Schema: public; Owner: ivana; Tablespace: 
--

ALTER TABLE ONLY lesonsprogram
    ADD CONSTRAINT lesonsprogram_pkey PRIMARY KEY (id);


--
-- TOC entry 1849 (class 2606 OID 16427)
-- Name: mark_pkey; Type: CONSTRAINT; Schema: public; Owner: ivana; Tablespace: 
--

ALTER TABLE ONLY mark
    ADD CONSTRAINT mark_pkey PRIMARY KEY (id);


--
-- TOC entry 1843 (class 2606 OID 16408)
-- Name: pk_danceprogram; Type: CONSTRAINT; Schema: public; Owner: ivana; Tablespace: 
--

ALTER TABLE ONLY danceprogram
    ADD CONSTRAINT pk_danceprogram PRIMARY KEY (id);


--
-- TOC entry 1845 (class 2606 OID 16410)
-- Name: pk_gymnastichall; Type: CONSTRAINT; Schema: public; Owner: ivana; Tablespace: 
--

ALTER TABLE ONLY gymnastichall
    ADD CONSTRAINT pk_gymnastichall PRIMARY KEY (id);


--
-- TOC entry 1847 (class 2606 OID 16412)
-- Name: pk_member; Type: CONSTRAINT; Schema: public; Owner: ivana; Tablespace: 
--

ALTER TABLE ONLY member
    ADD CONSTRAINT pk_member PRIMARY KEY (id);


--
-- TOC entry 1853 (class 2606 OID 16442)
-- Name: trainerprogram_pkey; Type: CONSTRAINT; Schema: public; Owner: ivana; Tablespace: 
--

ALTER TABLE ONLY trainerprogram
    ADD CONSTRAINT trainerprogram_pkey PRIMARY KEY (id);


--
-- TOC entry 1854 (class 2606 OID 16413)
-- Name: fk_danceprogram_member; Type: FK CONSTRAINT; Schema: public; Owner: ivana
--

ALTER TABLE ONLY member
    ADD CONSTRAINT fk_danceprogram_member FOREIGN KEY (danceprogram_id) REFERENCES danceprogram(id);


--
-- TOC entry 1855 (class 2606 OID 16418)
-- Name: fk_gymnastichall_member; Type: FK CONSTRAINT; Schema: public; Owner: ivana
--

ALTER TABLE ONLY member
    ADD CONSTRAINT fk_gymnastichall_member FOREIGN KEY (gymnastichall_id) REFERENCES gymnastichall(id);


--
-- TOC entry 1975 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: ivana
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM ivana;
GRANT ALL ON SCHEMA public TO ivana;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-10-03 03:12:42

--
-- PostgreSQL database dump complete
--

