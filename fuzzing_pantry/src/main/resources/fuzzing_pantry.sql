PGDMP                     	    {            fuzzing_pantry    15.4    15.4     7           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            8           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            9           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            :           1262    16401    fuzzing_pantry    DATABASE     p   CREATE DATABASE fuzzing_pantry WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'C';
    DROP DATABASE fuzzing_pantry;
                fuzzing    false            �            1259    16438    architecture    TABLE     g   CREATE TABLE public.architecture (
    id integer NOT NULL,
    name character varying(50) NOT NULL
);
     DROP TABLE public.architecture;
       public         heap    fuzzing    false            �            1259    16431    artifact    TABLE     s   CREATE TABLE public.artifact (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    link text
);
    DROP TABLE public.artifact;
       public         heap    fuzzing    false            �            1259    16407    crash    TABLE     2  CREATE TABLE public.crash (
    id integer NOT NULL,
    target_name character(1) NOT NULL,
    status_id integer NOT NULL,
    stacktrace_id integer NOT NULL,
    artifact_id integer,
    first_reproduce_date timestamp with time zone NOT NULL,
    last_reproduce_date timestamp with time zone NOT NULL
);
    DROP TABLE public.crash;
       public         heap    fuzzing    false            �            1259    16443    crash_architecture    TABLE     p   CREATE TABLE public.crash_architecture (
    crash_id integer NOT NULL,
    architecture_id integer NOT NULL
);
 &   DROP TABLE public.crash_architecture;
       public         heap    fuzzing    false            �            1259    16446    crash_issue    TABLE     P   CREATE TABLE public.crash_issue (
    crash_id integer,
    issue_id integer
);
    DROP TABLE public.crash_issue;
       public         heap    fuzzing    false            �            1259    16449    issue    TABLE     g   CREATE TABLE public.issue (
    id integer NOT NULL,
    name text NOT NULL,
    link text NOT NULL
);
    DROP TABLE public.issue;
       public         heap    fuzzing    false            �            1259    16417 
   stacktrace    TABLE     U   CREATE TABLE public.stacktrace (
    id integer NOT NULL,
    trace text NOT NULL
);
    DROP TABLE public.stacktrace;
       public         heap    fuzzing    false            �            1259    16412    status    TABLE     Y   CREATE TABLE public.status (
    id integer NOT NULL,
    name character(50) NOT NULL
);
    DROP TABLE public.status;
       public         heap    fuzzing    false            �            1259    16424    user    TABLE     �   CREATE TABLE public."user" (
    id integer NOT NULL,
    username character varying(50) NOT NULL,
    password text NOT NULL
);
    DROP TABLE public."user";
       public         heap    fuzzing    false            1          0    16438    architecture 
   TABLE DATA           0   COPY public.architecture (id, name) FROM stdin;
    public          fuzzing    false    219   �       0          0    16431    artifact 
   TABLE DATA           2   COPY public.artifact (id, name, link) FROM stdin;
    public          fuzzing    false    218   �       ,          0    16407    crash 
   TABLE DATA           �   COPY public.crash (id, target_name, status_id, stacktrace_id, artifact_id, first_reproduce_date, last_reproduce_date) FROM stdin;
    public          fuzzing    false    214   �       2          0    16443    crash_architecture 
   TABLE DATA           G   COPY public.crash_architecture (crash_id, architecture_id) FROM stdin;
    public          fuzzing    false    220          3          0    16446    crash_issue 
   TABLE DATA           9   COPY public.crash_issue (crash_id, issue_id) FROM stdin;
    public          fuzzing    false    221   .       4          0    16449    issue 
   TABLE DATA           /   COPY public.issue (id, name, link) FROM stdin;
    public          fuzzing    false    222   K       .          0    16417 
   stacktrace 
   TABLE DATA           /   COPY public.stacktrace (id, trace) FROM stdin;
    public          fuzzing    false    216   h       -          0    16412    status 
   TABLE DATA           *   COPY public.status (id, name) FROM stdin;
    public          fuzzing    false    215   �       /          0    16424    user 
   TABLE DATA           8   COPY public."user" (id, username, password) FROM stdin;
    public          fuzzing    false    217   �       �           2606    16442    architecture architecture_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.architecture
    ADD CONSTRAINT architecture_pkey PRIMARY KEY (id, name);
 H   ALTER TABLE ONLY public.architecture DROP CONSTRAINT architecture_pkey;
       public            fuzzing    false    219    219            �           2606    16437    artifact artifact_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.artifact
    ADD CONSTRAINT artifact_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.artifact DROP CONSTRAINT artifact_pkey;
       public            fuzzing    false    218            �           2606    16411    crash crash_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.crash
    ADD CONSTRAINT crash_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.crash DROP CONSTRAINT crash_pkey;
       public            fuzzing    false    214            �           2606    16455    issue issue_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.issue
    ADD CONSTRAINT issue_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.issue DROP CONSTRAINT issue_pkey;
       public            fuzzing    false    222            �           2606    16423    stacktrace stacktrace_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.stacktrace
    ADD CONSTRAINT stacktrace_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.stacktrace DROP CONSTRAINT stacktrace_pkey;
       public            fuzzing    false    216            �           2606    16416    status status_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.status
    ADD CONSTRAINT status_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.status DROP CONSTRAINT status_pkey;
       public            fuzzing    false    215            �           2606    16430    user user_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            fuzzing    false    217            1      x�3�L�M13�2�L,��1z\\\ 6�x      0      x������ � �      ,      x������ � �      2      x������ � �      3      x������ � �      4      x������ � �      .      x������ � �      -   (   x�3��K-W pq�礐�ɘ3�,�(1=�M1z\\\ ��      /      x������ � �     