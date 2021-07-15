--
-- PostgreSQL database dump
--

-- Dumped from database version 13.3
-- Dumped by pg_dump version 13.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: databasechangelog; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.databasechangelog (
    id character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    filename character varying(255) NOT NULL,
    dateexecuted timestamp without time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) NOT NULL,
    md5sum character varying(35),
    description character varying(255),
    comments character varying(255),
    tag character varying(255),
    liquibase character varying(20),
    contexts character varying(255),
    labels character varying(255),
    deployment_id character varying(10)
);


ALTER TABLE public.databasechangelog OWNER TO postgres;

--
-- Name: databasechangeloglock; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);


ALTER TABLE public.databasechangeloglock OWNER TO postgres;

--
-- Name: posts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.posts (
    id_post bigint NOT NULL,
    content character varying NOT NULL,
    created timestamp with time zone,
    updated timestamp with time zone
);


ALTER TABLE public.posts OWNER TO postgres;

--
-- Name: regions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.regions (
    id_region bigint NOT NULL,
    name character varying NOT NULL
);


ALTER TABLE public.regions OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id_user bigint NOT NULL,
    first_name character varying NOT NULL,
    last_name character varying NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Data for Name: databasechangelog; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) FROM stdin;
1.2	EGOR	dbchangelog-1.2.xml	2021-07-15 22:08:22.998786	2	EXECUTED	\N	empty		\N	4.4.0	\N	\N	6361702715
1.2	E (generated)	dbchangelog-1.2.xml	2021-07-15 22:17:09.44758	3	EXECUTED	\N	delete tableName=sometable		\N	4.4.0	\N	\N	6362229156
1.1	E (generated)	dbchangelog-1.1.xml	2021-07-15 22:00:23.982134	1	EXECUTED	\N	createTable tableName=sometable		\N	4.4.0	\N	\N	6361223680
1.1	E (generated)	dbchangelog-1.15.xml	2021-07-15 22:37:06.91547	4	EXECUTED	\N	createTable tableName=sometable		\N	4.4.0	\N	\N	6363426633
1.2	E	dbchangelog-1.2.xml	2021-07-15 22:49:52.615985	5	EXECUTED	\N	dropTable tableName=sometable		\N	4.4.0	\N	\N	6364192275
\.


--
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
1	f	\N	\N
\.


--
-- Data for Name: posts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.posts (id_post, content, created, updated) FROM stdin;
7	pikabu	2021-07-07 14:54:03.326818+07	2021-07-07 14:55:01.203994+07
8	uhiuh	2021-07-07 15:19:58.526779+07	\N
\.


--
-- Data for Name: regions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.regions (id_region, name) FROM stdin;
7	othi
8	hj
32	DF
234	tret
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id_user, first_name, last_name) FROM stdin;
7	Frank	Columbo
\.


--
-- Name: databasechangeloglock databasechangeloglock_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id);


--
-- Name: posts id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.posts
    ADD CONSTRAINT id UNIQUE (id_post) INCLUDE (id_post);


--
-- Name: regions idregion; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.regions
    ADD CONSTRAINT idregion UNIQUE (id_region) INCLUDE (id_region);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id_user);


--
-- Name: posts io; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.posts
    ADD CONSTRAINT io FOREIGN KEY (id_post) REFERENCES public.regions(id_region) NOT VALID;


--
-- PostgreSQL database dump complete
--

