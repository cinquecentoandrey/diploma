create schema if not exists fs_schema;
set schema 'fs_schema';
set search_path to 'fs_schema';

create sequence if not exists fs_schema.file_id_seq;
CREATE TABLE IF NOT EXISTS fs_schema.fs_file
(
    id            bigint       not null
        constraint FS_FILE_PK
            primary key
        default nextval('fs_schema.file_id_seq'),
    guid          VARCHAR(128) NOT NULL,
    original_name VARCHAR(255) NOT NULL,
    type          VARCHAR(255) NOT NULL,
    size          BIGINT       NOT NULL,
    path          VARCHAR(255) NOT NULL,
    hash          VARCHAR(255) NOT NULL,
    created       TIMESTAMP    NOT NULL,
    is_deleted    BOOLEAN,
    deleted_by    VARCHAR(256),
    deleted_date  TIMESTAMP,
    deleted_type  VARCHAR(64)
);
