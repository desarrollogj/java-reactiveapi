DROP TABLE IF EXISTS Items;

CREATE TABLE Items (
                       id BIGINT AUTO_INCREMENT  PRIMARY KEY,
                       name varchar2(255) NOT NULL,
                       long_description varchar2(255) NOT NULL,
                       amount DECIMAL(10) NOT NULL
);
