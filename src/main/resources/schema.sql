CREATE TABLE TASK(
  ID BIGSERIAL PRIMARY KEY,
  NAME VARCHAR(16) NOT NULL,
  DESCRIPTION VARCHAR(32),
  STATUS BOOLEAN
);