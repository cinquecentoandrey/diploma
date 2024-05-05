create schema if not exists news_schema;
set schema 'news_schema';
set search_path to 'news_schema';

create sequence if not exists news_schema.news_id_seq;
CREATE TABLE IF NOT EXISTS news_schema.news
(
    id            bigint       not null
        constraint NEWS_PK
            primary key
        default nextval('news_schema.news_id_seq'),
    header VARCHAR(255) NOT NULL,
    body          VARCHAR(5192) NOT NULL,
    tag           VARCHAR(32)   NOT NULL,
    created       TIMESTAMP     NOT NULL,
    created_by    VARCHAR(256),
    updated       TIMESTAMP     NOT NULL,
    updated_by    VARCHAR(256)
);
