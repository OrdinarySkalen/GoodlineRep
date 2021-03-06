DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS RESOURCE;
DROP TABLE IF EXISTS ACCOUNTING;
CREATE TABLE USER (ID INT PRIMARY KEY, NAME VARCHAR(255), PASS VARCHAR(255), SALT VARCHAR(255));
CREATE TABLE RESOURCE (ID INT PRIMARY KEY, USER_ID INT, PATH VARCHAR(255), ROLE VARCHAR(255));
CREATE TABLE ACCOUNTING (RESOURCE_ID INT, DATE_START DATE, DATE_END DATE, VOLUME INT);
ALTER TABLE RESOURCE ADD  FOREIGN KEY (USER_ID) REFERENCES USER (ID);
ALTER TABLE ACCOUNTING ADD  FOREIGN KEY (RESOURCE_ID) REFERENCES RESOURCE (ID);
CREATE INDEX USER_ROLE_INDEX ON RESOURCE (USER_ID, ROLE);