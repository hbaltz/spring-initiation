CREATE TABLE IF NOT EXISTS Content (
    id SERIAL PRIMARY KEY ,
    title varchar(255) NOT NULL,
    description text,
    status VARCHAR(20) NOT NULL,
    content_type VARCHAR(50) NOT NULL,
    date_created TIMESTAMP NOT NULL DEFAULT now(),
    date_updated TIMESTAMP,
    url VARCHAR(255)
);

INSERT INTO
    Content(title,description,status,content_type)
VALUES
    ('Test', 'test desc', 'IN_PROGRESS', 'ARTICLE')
;