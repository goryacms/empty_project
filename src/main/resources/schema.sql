-- Таблица регистрации
CREATE TABLE IF NOT EXISTS Registers (
    id               BIGINT  PRIMARY KEY AUTO_INCREMENT,
    version          INTEGER NOT NULL,
    login            VARCHAR(10) NOT NULL,
    password         VARCHAR(16) NOT NULL,
    name             VARCHAR(12)
);

CREATE INDEX IX_Register ON Registers (login, password);


-- Организация

CREATE TABLE IF NOT EXISTS Organization (
    id               BIGINT PRIMARY KEY AUTO_INCREMENT,
    version          INTEGER NOT NULL,
    name             VARCHAR(75) NOT NULL,
    full_name        VARCHAR(200),
    inn              BIGINT NOT NULL,
    kpp              BIGINT NOT NULL,
    address          VARCHAR(100),
    phone            VARCHAR(15),
    is_active        BOOLEAN NOT NULL
);

CREATE INDEX IX_Organization_List1 ON Organization (name, inn, is_active);

-- Офис.
-- Organization - Office: 1:М

CREATE TABLE IF NOT EXISTS Office (
    id               BIGINT  PRIMARY KEY AUTO_INCREMENT,
    version          INTEGER NOT NULL,
    organization_id  BIGINT,
    name             VARCHAR(75) NOT NULL,
    address          VARCHAR(100),
    phone            VARCHAR(15),
    is_active        BOOLEAN NOT NULL
);

CREATE INDEX IX_Office_Organization_Id ON Office (organization_id);
ALTER TABLE Office ADD FOREIGN KEY (organization_id) REFERENCES Organization(id);

-- Пользователь
-- Office - User: 1:М

CREATE TABLE IF NOT EXISTS Users (
    id              BIGINT  PRIMARY KEY AUTO_INCREMENT,
    version         INTEGER NOT NULL,
    office_id       BIGINT,
    first_name      VARCHAR(50) NOT NULL,
    last_name       VARCHAR(50) NOT NULL,
    middle_name     VARCHAR(50),
    position        VARCHAR(50),
    phone           VARCHAR(15),
    age             INTEGER ,

    citizenship_id  BIGINT,
/*
    doc_code        VARCHAR(5),
    doc_number      INTEGER,
    doc_date        DATE,
    doc_name        VARCHAR(50),

    citizenship_code INTEGER,
    citizenship_name VARCHAR(50),
*/
    is_identified    BOOLEAN NOT NULL
);

CREATE INDEX IX_User_List1 ON Users (first_name, last_name, middle_name);
CREATE INDEX IX_User_List2 ON Users (position);
CREATE INDEX IX_User_Office_Id ON Users (office_id);
ALTER TABLE Users ADD FOREIGN KEY (office_id) REFERENCES Office(id);

CREATE INDEX IX_Users_Citizenship_Id ON Users (citizenship_id);
ALTER TABLE Users ADD FOREIGN KEY (citizenship_id) REFERENCES Citizenship(id);

-- Документы
-- User - Docs: M:M

CREATE TABLE IF NOT EXISTS Docs (
    code            INTEGER  PRIMARY KEY ,
    version         INTEGER NOT NULL,
    name            VARCHAR(100) NOT NULL
);

-- Для связи User и Docs

CREATE TABLE IF NOT EXISTS DocsUsers (
    id             BIGINT  PRIMARY KEY AUTO_INCREMENT,
    user_id        BIGINT,   /*FK - user*/
    doc_code       INTEGER,   /*FK - docs*/
    doc_date       DATE,
    doc_number     INTEGER
);
CREATE INDEX IX_DocsUsers_Users_Id ON DocsUsers (user_id);
CREATE INDEX IX_DocsUsers_Docs_Id ON DocsUsers (doc_code);
ALTER TABLE DocsUsers ADD FOREIGN KEY (user_id) REFERENCES Users(id);
ALTER TABLE DocsUsers ADD FOREIGN KEY (doc_code) REFERENCES Docs(code);

-- Страны

CREATE TABLE IF NOT EXISTS Countries (
    code       INTEGER  PRIMARY KEY ,
    version    INTEGER NOT NULL,
    name       VARCHAR(100) NOT NULL
);

-- Гражданство
-- Countries - Citizenship: 1:1

CREATE TABLE IF NOT EXISTS Citizenship (
    id    INTEGER  PRIMARY KEY  AUTO_INCREMENT,
    version             INTEGER NOT NULL,
    countries_code      INTEGER,
    name                VARCHAR(100) NOT NULL /*Российское, украинское и т.д.*/
);

CREATE INDEX IX_Citizenship_Countries_Id ON Citizenship (countries_code);
ALTER TABLE Citizenship ADD FOREIGN KEY (countries_code) REFERENCES Countries(code);

-- Для связи User и Citizenship

/* -- Реализовывать связь многие-ко-многим ???
CREATE TABLE IF NOT EXISTS CitizenshipUsers (
    id                     BIGINT  PRIMARY KEY AUTO_INCREMENT,
    user_id                BIGINT,
    citizenship_code       INTEGER
);
CREATE INDEX IX_CitizenshipUsers_Users_Id ON CitizenshipUsers (user_id);
CREATE INDEX IX_CitizenshipUsers_Docs_Id ON CitizenshipUsers (citizenship_code);
ALTER TABLE CitizenshipUsers ADD FOREIGN KEY (user_id) REFERENCES Users(id);
ALTER TABLE CitizenshipUsers ADD FOREIGN KEY (citizenship_code) REFERENCES Citizenship(citizenship_code);*/







/*-------------------------------------------------*/

CREATE TABLE IF NOT EXISTS Person (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    house_id   INTEGER,
    age        INTEGER  NOT NULL
);

CREATE TABLE IF NOT EXISTS House (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER NOT NULL,
    address    VARCHAR(50) NOT NULL
);

CREATE INDEX IX_Person_House_Id ON Person (house_id);
ALTER TABLE Person ADD FOREIGN KEY (house_id) REFERENCES House(id);
