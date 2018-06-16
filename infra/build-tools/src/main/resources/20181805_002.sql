CREATE TABLE personnel (
    id serial PRIMARY KEY,
    barangay character varying(255),
    city character varying(255),
    st_number character varying(255),
    zip_code character varying(255),
    birthday timestamp without time zone,
    date_hired timestamp without time zone,
    gwa double precision,
    first_name character varying(255),
    last_name character varying(255),
    middle_name character varying(255),
    suffix character varying(255),
    title character varying(255)
);

CREATE TABLE roles (
    role_id serial PRIMARY KEY,
    role character varying(255)
);

CREATE TABLE contact (
    contact_id serial PRIMARY KEY,
    contact_type character varying(255),
    contact_details character varying(255),
    personnel_id bigint REFERENCES personnel(id)
);

CREATE TABLE personnel_roles (
    id serial REFERENCES personnel(id),
    role_id serial REFERENCES roles(role_id),
    PRIMARY KEY (id, role_id)
);
