-- Таблица регистрации
CREATE TABLE IF NOT EXISTS Registers (
    id               BIGINT  PRIMARY KEY AUTO_INCREMENT,
    version          INTEGER NOT NULL,
    login            VARCHAR(12) NOT NULL,
    password         VARCHAR(100) NOT NULL,
    name             VARCHAR(12),
    email            VARCHAR(24),
    code             VARCHAR(12),
    is_active        BOOLEAN
);

CREATE INDEX IX_Register ON Registers (login, password);


-- Организация

CREATE TABLE IF NOT EXISTS Organization (
    id               BIGINT PRIMARY KEY AUTO_INCREMENT,
    version          INTEGER NOT NULL,
    name             VARCHAR(75) ,
    full_name        VARCHAR(200) ,
    inn              BIGINT NOT NULL,
    kpp              BIGINT NOT NULL,
    address          VARCHAR(100),
    phone            VARCHAR(15),
    is_active        BOOLEAN
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
    salary          INTEGER ,
    registration_date       DATE,

    citizenship_code  BIGINT,

    is_identified    BOOLEAN
);



CREATE INDEX IX_User_List1 ON Users (first_name, last_name, middle_name);
CREATE INDEX IX_User_List2 ON Users (position);
CREATE INDEX IX_User_Office_Id ON Users (office_id);
ALTER TABLE Users ADD FOREIGN KEY (office_id) REFERENCES Office(id);


-- Документы
-- User - Docs: M:M

CREATE TABLE IF NOT EXISTS Docs (
    code            INTEGER  PRIMARY KEY ,
    version         INTEGER NOT NULL,
    name            VARCHAR(100) NOT NULL
);

-- Для связи User и Docs

CREATE TABLE IF NOT EXISTS Docs_Users (
    id             BIGINT  PRIMARY KEY AUTO_INCREMENT,
    version         INTEGER NOT NULL,
    user_id        BIGINT,   /*FK - user*/
    doc_code       INTEGER,   /*FK - docs*/
    doc_date       DATE,
    doc_number     BIGINT
);
CREATE INDEX IX_DocsUsers_Users_Id ON Docs_Users (user_id);
CREATE INDEX IX_DocsUsers_Docs_Id ON Docs_Users (doc_code);
ALTER TABLE Docs_Users ADD FOREIGN KEY (user_id) REFERENCES Users(id);
ALTER TABLE Docs_Users ADD FOREIGN KEY (doc_code) REFERENCES Docs(code);


-- Гражданство

CREATE TABLE IF NOT EXISTS Citizenship (
    code                INTEGER PRIMARY KEY  AUTO_INCREMENT,
    version             INTEGER NOT NULL,
    name                VARCHAR(100) NOT NULL
);

-- Индекс на users
CREATE INDEX IX_Users_Citizenship_Id ON Users (citizenship_code);
ALTER TABLE Users ADD FOREIGN KEY (citizenship_code) REFERENCES Citizenship(code);