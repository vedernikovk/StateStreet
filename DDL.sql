CREATE TABLE public.course
(
    id bigint NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT course_pkey PRIMARY KEY (id)
)


CREATE TABLE public.student
(
    id bigint NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT student_pkey PRIMARY KEY (id)
)

CREATE TABLE public.student_courses
(
    students_id bigint NOT NULL,
    courses_id bigint NOT NULL,
    CONSTRAINT student_courses_pkey PRIMARY KEY (students_id, courses_id),
    CONSTRAINT courses_fk FOREIGN KEY (courses_id)
        REFERENCES public.course (id)
		
    CONSTRAINT student_fk FOREIGN KEY (students_id)
        REFERENCES public.student (id) 
)
