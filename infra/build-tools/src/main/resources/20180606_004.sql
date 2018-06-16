CREATE TABLE project (
    project_id serial PRIMARY KEY,
    project character varying(255),
    start_date timestamp without time zone,
    end_date timestamp without time zone
);

CREATE TABLE project_personnel (
    project_id serial REFERENCES project(project_id),
    id serial REFERENCES personnel(id),
    PRIMARY KEY (project_id, id)
);
