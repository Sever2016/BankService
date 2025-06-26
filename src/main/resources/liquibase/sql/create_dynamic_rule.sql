--liquibase formatted sql

--changeset romarsh:1
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

--changeset romarsh:2
CREATE TABLE dynamic_recommendation_rule (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    product_id UUID NOT NULL,
    product_text TEXT NOT NULL
);

--changeset romarsh:3
CREATE TABLE dynamic_recommendation_query (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    query_type VARCHAR(100) NOT NULL,
    arguments TEXT NOT NULL, -- Храним JSON-массив строк, например '["CREDIT"]'
    negate BOOLEAN NOT NULL,
    rule_id UUID NOT NULL,
    CONSTRAINT fk_rule FOREIGN KEY (rule_id) REFERENCES dynamic_recommendation_rule(id) ON DELETE CASCADE
);



