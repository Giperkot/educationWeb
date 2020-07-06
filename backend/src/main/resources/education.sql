--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.12
-- Dumped by pg_dump version 9.5.12

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: course; Type: TABLE; Schema: public; Owner: unit_dev
--

CREATE TABLE public.course (
    id bigint NOT NULL,
    title character varying(255) NOT NULL,
    specialization_id bigint NOT NULL,
    description text
);


ALTER TABLE public.course OWNER TO unit_dev;

--
-- Name: course_id_seq; Type: SEQUENCE; Schema: public; Owner: unit_dev
--

CREATE SEQUENCE public.course_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.course_id_seq OWNER TO unit_dev;

--
-- Name: course_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: unit_dev
--

ALTER SEQUENCE public.course_id_seq OWNED BY public.course.id;


--
-- Name: question; Type: TABLE; Schema: public; Owner: unit_dev
--

CREATE TABLE public.question (
    id bigint NOT NULL,
    title character varying(255) NOT NULL,
    course_id bigint NOT NULL,
    description text
);


ALTER TABLE public.question OWNER TO unit_dev;

--
-- Name: question_id_seq; Type: SEQUENCE; Schema: public; Owner: unit_dev
--

CREATE SEQUENCE public.question_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.question_id_seq OWNER TO unit_dev;

--
-- Name: question_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: unit_dev
--

ALTER SEQUENCE public.question_id_seq OWNED BY public.question.id;


--
-- Name: sms; Type: TABLE; Schema: public; Owner: unit_dev
--

CREATE TABLE public.sms (
    id bigint NOT NULL,
    create_time timestamp without time zone NOT NULL,
    update_time timestamp without time zone NOT NULL,
    status character varying(20) NOT NULL,
    phone_number text NOT NULL,
    text text NOT NULL,
    server_id text,
    server_name text,
    error_sending text
);


ALTER TABLE public.sms OWNER TO unit_dev;

--
-- Name: sms_id_seq; Type: SEQUENCE; Schema: public; Owner: unit_dev
--

CREATE SEQUENCE public.sms_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sms_id_seq OWNER TO unit_dev;

--
-- Name: sms_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: unit_dev
--

ALTER SEQUENCE public.sms_id_seq OWNED BY public.sms.id;


--
-- Name: specialization; Type: TABLE; Schema: public; Owner: unit_dev
--

CREATE TABLE public.specialization (
    id bigint NOT NULL,
    title character varying(255) NOT NULL,
    description text,
    state boolean,
    timing integer,
    lang text
);


ALTER TABLE public.specialization OWNER TO unit_dev;

--
-- Name: specialization_id_seq; Type: SEQUENCE; Schema: public; Owner: unit_dev
--

CREATE SEQUENCE public.specialization_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.specialization_id_seq OWNER TO unit_dev;

--
-- Name: specialization_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: unit_dev
--

ALTER SEQUENCE public.specialization_id_seq OWNED BY public.specialization.id;


--
-- Name: spring_session; Type: TABLE; Schema: public; Owner: unit_dev
--

CREATE TABLE public.spring_session (
    primary_id character(36) NOT NULL,
    session_id character(36) NOT NULL,
    creation_time bigint NOT NULL,
    last_access_time bigint NOT NULL,
    max_inactive_interval integer NOT NULL,
    expiry_time bigint NOT NULL,
    principal_name character varying(100),
    user_id uuid
);


ALTER TABLE public.spring_session OWNER TO unit_dev;

--
-- Name: spring_session_attributes; Type: TABLE; Schema: public; Owner: unit_dev
--

CREATE TABLE public.spring_session_attributes (
    session_primary_id character(36) NOT NULL,
    attribute_name character varying(200) NOT NULL,
    attribute_bytes bytea NOT NULL
);


ALTER TABLE public.spring_session_attributes OWNER TO unit_dev;

--
-- Name: test; Type: TABLE; Schema: public; Owner: unit_dev
--

CREATE TABLE public.test (
    id bigint NOT NULL,
    title character varying(255) NOT NULL,
    course_id bigint NOT NULL,
    description text
);


ALTER TABLE public.test OWNER TO unit_dev;

--
-- Name: test_id_seq; Type: SEQUENCE; Schema: public; Owner: unit_dev
--

CREATE SEQUENCE public.test_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.test_id_seq OWNER TO unit_dev;

--
-- Name: test_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: unit_dev
--

ALTER SEQUENCE public.test_id_seq OWNED BY public.test.id;


--
-- Name: user_token; Type: TABLE; Schema: public; Owner: unit_dev
--

CREATE TABLE public.user_token (
    id bigint NOT NULL,
    create_time timestamp without time zone NOT NULL,
    update_time timestamp without time zone NOT NULL,
    status character varying(255),
    telephone_code character varying(20) NOT NULL,
    session_id uuid NOT NULL
);


ALTER TABLE public.user_token OWNER TO unit_dev;

--
-- Name: TABLE user_token; Type: COMMENT; Schema: public; Owner: unit_dev
--

COMMENT ON TABLE public.user_token IS 'Таблица с телефонными кодами';


--
-- Name: user_token_id_seq; Type: SEQUENCE; Schema: public; Owner: unit_dev
--

CREATE SEQUENCE public.user_token_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_token_id_seq OWNER TO unit_dev;

--
-- Name: user_token_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: unit_dev
--

ALTER SEQUENCE public.user_token_id_seq OWNED BY public.user_token.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: unit_dev
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    create_time timestamp without time zone NOT NULL,
    update_time timestamp without time zone NOT NULL,
    status character varying(255) NOT NULL,
    login character varying(255) NOT NULL,
    password character varying(255),
    salt character varying(255),
    first_name text,
    last_name text,
    middle_name text
);


ALTER TABLE public.users OWNER TO unit_dev;

--
-- Name: TABLE users; Type: COMMENT; Schema: public; Owner: unit_dev
--

COMMENT ON TABLE public.users IS 'Таблица с пользователями';


--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: unit_dev
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO unit_dev;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: unit_dev
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: unit_dev
--

ALTER TABLE ONLY public.course ALTER COLUMN id SET DEFAULT nextval('public.course_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: unit_dev
--

ALTER TABLE ONLY public.question ALTER COLUMN id SET DEFAULT nextval('public.question_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: unit_dev
--

ALTER TABLE ONLY public.sms ALTER COLUMN id SET DEFAULT nextval('public.sms_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: unit_dev
--

ALTER TABLE ONLY public.specialization ALTER COLUMN id SET DEFAULT nextval('public.specialization_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: unit_dev
--

ALTER TABLE ONLY public.test ALTER COLUMN id SET DEFAULT nextval('public.test_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: unit_dev
--

ALTER TABLE ONLY public.user_token ALTER COLUMN id SET DEFAULT nextval('public.user_token_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: unit_dev
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: course; Type: TABLE DATA; Schema: public; Owner: unit_dev
--

COPY public.course (id, title, specialization_id, description) FROM stdin;
11	Важные слова в ПНИПУ	10	Университет, столовая, ГПФ, комплекс, строяк
12	Семья	10	Мама, папа, брат, сестра, тетя, дядя
13	Животные	10	Корова, петух, кот, собака, мышь
51	Important words in PNRPU	50	University, canteen, GPF, complex, building
52	Family	50	Mom, dad, brother, sister, aunt, uncle
53	Animals	50	Cow, cock, cat, dog, mouse
21	Как вызвать скорую?	20	Куда обратиться,если тебе или какому-то человеку стало плохо.
22	Аптека.	20	Где купить лекарства?
23	Продуктовый магазин.	20	Где купить продукты питания? \r\n\r\n\r\n\r\n\r\n
61	How to call an ambulance?	60	Where to turn if you or some person became ill.
62	Pharmacy.	60	Where to buy medicine?
63	Grocery store.	60	Where to buy food?
31	Карта города - 2GIS	30	Приложение для поиска магазинов, аптек, остановок общественного транспорта и т.д.
71	City Map - 2GIS	70	Application for searching stores
42	Медведь	40	Популярный памятник
43	Памятник Пушкину	40	Популярный памятник
41	Пермяк соленые уши	40	Популярный памятник
81	Permyak salty ears	80	Popular monument
82	Bear	80	Popular monument
83	Monument to Pushkin	80	Popular monument
\.


--
-- Name: course_id_seq; Type: SEQUENCE SET; Schema: public; Owner: unit_dev
--

SELECT pg_catalog.setval('public.course_id_seq', 1, false);


--
-- Data for Name: question; Type: TABLE DATA; Schema: public; Owner: unit_dev
--

COPY public.question (id, title, course_id, description) FROM stdin;
1	Вопрос 1	1	Как перевести на русский "Hello"?
2	Вопрос 2	1	Как перевести с русского на английский "столовая"?
3	Question 1	2	How to translate "Hello" into Russian?
4	Question 2	2	How to translate from Russian into English "canteen"?
\.


--
-- Name: question_id_seq; Type: SEQUENCE SET; Schema: public; Owner: unit_dev
--

SELECT pg_catalog.setval('public.question_id_seq', 1, false);


--
-- Data for Name: sms; Type: TABLE DATA; Schema: public; Owner: unit_dev
--

COPY public.sms (id, create_time, update_time, status, phone_number, text, server_id, server_name, error_sending) FROM stdin;
\.


--
-- Name: sms_id_seq; Type: SEQUENCE SET; Schema: public; Owner: unit_dev
--

SELECT pg_catalog.setval('public.sms_id_seq', 1, false);


--
-- Data for Name: specialization; Type: TABLE DATA; Schema: public; Owner: unit_dev
--

COPY public.specialization (id, title, description, state, timing, lang) FROM stdin;
10	Русский язык	Базовая и специальная лексика	t	72	RU
20	Жизнь в российском городе	Бытовые вопросы: как проехать в общественном транспорте, купить еду, лекарства. Куда сходить развлечься.	\N	22	RU
30	Полезные программы и сервисы	Где найти карту города? Как ей пользоваться? Что можно найти? Что посмотреть?	\N	11	RU
40	Гид по городу	Достопремичательности и памятники.	\N	20	RU
50	Russian language	Basic and special vocabulary	\N	72	EN
60	Life in a Russian city	Household questions: how to get in public transport	\N	22	EN
70	Useful programs and services	Where to find a city map? How to use it? What can be found? What to see?	\N	11	EN
80	City guide	Landmarks and monuments.	\N	20	EN
\.


--
-- Name: specialization_id_seq; Type: SEQUENCE SET; Schema: public; Owner: unit_dev
--

SELECT pg_catalog.setval('public.specialization_id_seq', 1, false);


--
-- Data for Name: spring_session; Type: TABLE DATA; Schema: public; Owner: unit_dev
--

COPY public.spring_session (primary_id, session_id, creation_time, last_access_time, max_inactive_interval, expiry_time, principal_name, user_id) FROM stdin;
\.


--
-- Data for Name: spring_session_attributes; Type: TABLE DATA; Schema: public; Owner: unit_dev
--

COPY public.spring_session_attributes (session_primary_id, attribute_name, attribute_bytes) FROM stdin;
\.


--
-- Data for Name: test; Type: TABLE DATA; Schema: public; Owner: unit_dev
--

COPY public.test (id, title, course_id, description) FROM stdin;
1	Русский язык	11	Основные слова для ПНИПУ
2	Russian language	51	Key words for PNRPU
\.


--
-- Name: test_id_seq; Type: SEQUENCE SET; Schema: public; Owner: unit_dev
--

SELECT pg_catalog.setval('public.test_id_seq', 1, false);


--
-- Data for Name: user_token; Type: TABLE DATA; Schema: public; Owner: unit_dev
--

COPY public.user_token (id, create_time, update_time, status, telephone_code, session_id) FROM stdin;
\.


--
-- Name: user_token_id_seq; Type: SEQUENCE SET; Schema: public; Owner: unit_dev
--

SELECT pg_catalog.setval('public.user_token_id_seq', 1, false);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: unit_dev
--

COPY public.users (id, create_time, update_time, status, login, password, salt, first_name, last_name, middle_name) FROM stdin;
\.


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: unit_dev
--

SELECT pg_catalog.setval('public.users_id_seq', 1, false);


--
-- Name: course_pkey; Type: CONSTRAINT; Schema: public; Owner: unit_dev
--

ALTER TABLE ONLY public.course
    ADD CONSTRAINT course_pkey PRIMARY KEY (id);


--
-- Name: question_pkey; Type: CONSTRAINT; Schema: public; Owner: unit_dev
--

ALTER TABLE ONLY public.question
    ADD CONSTRAINT question_pkey PRIMARY KEY (id);


--
-- Name: specialization_pkey; Type: CONSTRAINT; Schema: public; Owner: unit_dev
--

ALTER TABLE ONLY public.specialization
    ADD CONSTRAINT specialization_pkey PRIMARY KEY (id);


--
-- Name: test_pkey; Type: CONSTRAINT; Schema: public; Owner: unit_dev
--

ALTER TABLE ONLY public.test
    ADD CONSTRAINT test_pkey PRIMARY KEY (id);


--
-- Name: course_specialization_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: unit_dev
--

ALTER TABLE ONLY public.course
    ADD CONSTRAINT course_specialization_id_fkey FOREIGN KEY (specialization_id) REFERENCES public.specialization(id);


--
-- Name: question_course_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: unit_dev
--

ALTER TABLE ONLY public.question
    ADD CONSTRAINT question_course_id_fkey FOREIGN KEY (course_id) REFERENCES public.test(id);


--
-- Name: test_course_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: unit_dev
--

ALTER TABLE ONLY public.test
    ADD CONSTRAINT test_course_id_fkey FOREIGN KEY (course_id) REFERENCES public.course(id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

