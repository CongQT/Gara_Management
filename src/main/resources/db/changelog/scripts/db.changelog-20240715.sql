-- liquibase formatted sql

-- changeset congqt:20240715.123400.01

CREATE TABLE revinfo
(
    id             BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    REVTSTMP       BIGINT NULL,
    request_time   DATETIME NULL,
    request_id     VARCHAR(64) NULL,
    user_id        INT NULL,
    request_method VARCHAR(64) NULL,
    request_uri    VARCHAR(255) NULL
);

-- changeset congqt:20240715.130000.02

CREATE TABLE role
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    code       VARCHAR(64)                        NOT NULL UNIQUE,
    name       VARCHAR(255)                       NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by INT NULL,
    updated_by INT NULL,
    deleted_at DATETIME NULL
);

CREATE TABLE role_aud
(
    id         INT    NOT NULL,
    REV        BIGINT NOT NULL,
    REVTYPE    INT    NOT NULL,
    code       VARCHAR(64),
    name       VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME,
    created_by INT,
    updated_by INT,
    deleted_at DATETIME,
    PRIMARY KEY (id, REV)
);

INSERT INTO role (id, `code`, name)
VALUES (1, 'MANAGER', 'Manager');

INSERT INTO role (id, `code`, name)
VALUES (2, 'WAREHOUSE', 'Warehouse');

INSERT INTO role (id, `code`, name)
VALUES (3, 'TECHNICAL', 'Technical');

INSERT INTO role (id, `code`, name)
VALUES (4, 'CASHIER', 'Cashier');

INSERT INTO role (id, `code`, name)
VALUES (5, 'CLIENT', 'Client');


CREATE TABLE user
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(255)                       NOT NULL,
    password   VARCHAR(255)                       NOT NULL,
    role_id    INT,
    email      VARCHAR(255),
    full_name  VARCHAR(255),
    phone      VARCHAR(64),
    address    VARCHAR(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by INT NULL,
    updated_by INT NULL,
    deleted_at DATETIME NULL,
    CONSTRAINT fk_user_role_id
        FOREIGN KEY (role_id) REFERENCES role (id)
);

CREATE TABLE user_aud
(
    id         INT          NOT NULL,
    REV        BIGINT       NOT NULL,
    REVTYPE    INT          NOT NULL,
    username   VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    role_id    INT,
    email      VARCHAR(255),
    full_name  VARCHAR(255),
    phone      VARCHAR(64),
    address    VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME,
    created_by INT,
    updated_by INT,
    deleted_at DATETIME,
    PRIMARY KEY (id, REV)
);

CREATE TABLE appointment
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    status     VARCHAR(255)                       NOT NULL,
    date       DATETIME                           NOT NULL,
    user_id  INT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by INT NULL,
    updated_by INT NULL,
    deleted_at DATETIME NULL,
    CONSTRAINT fk_appointment_user_id
        FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE appointment_aud
(
    id         INT          NOT NULL,
    REV        BIGINT       NOT NULL,
    REVTYPE    INT          NOT NULL,
    status     VARCHAR(255) NOT NULL,
    date       DATETIME     NOT NULL,
    user_id  INT,
    created_at DATETIME,
    updated_at DATETIME,
    created_by INT,
    updated_by INT,
    deleted_at DATETIME,
    PRIMARY KEY (id, REV)
);

-- changeset congqt:20240715.150000.03

CREATE TABLE service
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255)                       NOT NULL,
    price DOUBLE NOT NULL,
    description TEXT,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by  INT NULL,
    updated_by  INT NULL,
    deleted_at  DATETIME NULL
);

CREATE TABLE service_aud
(
    id          INT          NOT NULL,
    REV         BIGINT       NOT NULL,
    REVTYPE     INT          NOT NULL,
    name        VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    description TEXT,
    created_at  DATETIME,
    updated_at  DATETIME,
    created_by  INT,
    updated_by  INT,
    deleted_at  DATETIME,
    PRIMARY KEY (id, REV)
);


CREATE TABLE accessory
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255)                       NOT NULL,
    price DOUBLE NOT NULL,
    description TEXT,
    quantity    INT,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by  INT NULL,
    updated_by  INT NULL,
    deleted_at  DATETIME NULL
);

CREATE TABLE accessory_aud
(
    id          INT          NOT NULL,
    REV         BIGINT       NOT NULL,
    REVTYPE     INT          NOT NULL,
    name        VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    description TEXT,
    quantity    INT,
    created_at  DATETIME,
    updated_at  DATETIME,
    created_by  INT,
    updated_by  INT,
    deleted_at  DATETIME,
    PRIMARY KEY (id, REV)
);

CREATE TABLE orders
(
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    client_id          INT                                NOT NULL,
    staff_id INT                                NOT NULL,
    date               DATETIME,
    created_at         DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at         DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by         INT NULL,
    updated_by         INT NULL,
    deleted_at         DATETIME NULL,
    CONSTRAINT fk_orders_client_id
        FOREIGN KEY (client_id) REFERENCES user (id),
    CONSTRAINT fk_orders_staff_id
        FOREIGN KEY (staff_id) REFERENCES user (id)
);

CREATE TABLE orders_aud
(
    id                 INT    NOT NULL,
    REV                BIGINT NOT NULL,
    REVTYPE            INT    NOT NULL,
    client_id          INT                                NOT NULL,
    staff_id INT                                NOT NULL,
    date               DATETIME,
    created_at         DATETIME,
    updated_at         DATETIME,
    created_by         INT,
    updated_by         INT,
    deleted_at         DATETIME,
    PRIMARY KEY (id, REV)
);

CREATE TABLE orders_service
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    service_id INT                                NOT NULL,
    orders_id  INT                                NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by INT NULL,
    updated_by INT NULL,
    deleted_at DATETIME NULL,
    CONSTRAINT fk_orders_service_service_id
        FOREIGN KEY (service_id) REFERENCES service (id),
    CONSTRAINT fk_orders_service_orders_id
        FOREIGN KEY (orders_id) REFERENCES orders (id)
);

CREATE TABLE orders_service_aud
(
    id         INT    NOT NULL,
    REV        BIGINT NOT NULL,
    REVTYPE    INT    NOT NULL,
    service_id INT    NOT NULL,
    orders_id  INT    NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    created_by INT,
    updated_by INT,
    deleted_at DATETIME,
    PRIMARY KEY (id, REV)
);

CREATE TABLE orders_accessory
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    accessory_id INT                                NOT NULL,
    orders_id    INT                                NOT NULL,
    quantity     INT,
    created_at   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by   INT NULL,
    updated_by   INT NULL,
    deleted_at   DATETIME NULL,
    CONSTRAINT fk_orders_accessory_accessory_id
        FOREIGN KEY (accessory_id) REFERENCES accessory (id),
    CONSTRAINT fk_orders_accessory_orders_id
        FOREIGN KEY (orders_id) REFERENCES orders (id)
);

CREATE TABLE orders_accessory_aud
(
    id           INT    NOT NULL,
    REV          BIGINT NOT NULL,
    REVTYPE      INT    NOT NULL,
    accessory_id INT    NOT NULL,
    orders_id    INT    NOT NULL,
    quantity     INT,
    created_at   DATETIME,
    updated_at   DATETIME,
    created_by   INT,
    updated_by   INT,
    deleted_at   DATETIME,
    PRIMARY KEY (id, REV)
);

CREATE TABLE bill
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    total_amount DOUBLE NOT NULL,
    note       TEXT,
    date       DATETIME,
    client_id  INT,
    orders_id  INT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by INT NULL,
    updated_by INT NULL,
    deleted_at DATETIME NULL,
    CONSTRAINT fk_bill_client_id
        FOREIGN KEY (client_id) REFERENCES user (id),
    CONSTRAINT fk_bill_orders_id
        FOREIGN KEY (orders_id) REFERENCES orders (id)
);

CREATE TABLE bill_aud
(
    id         INT    NOT NULL,
    REV        BIGINT NOT NULL,
    REVTYPE    INT    NOT NULL,
    total_amount DOUBLE NOT NULL,
    note       TEXT,
    date       DATETIME,
    client_id  INT,
    orders_id  INT,
    created_at DATETIME,
    updated_at DATETIME,
    created_by INT,
    updated_by INT,
    deleted_at DATETIME,
    PRIMARY KEY (id, REV)
);

-- changeset congqt:20240715.160000.04

CREATE TABLE supplier
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    email      VARCHAR(255)                       NOT NULL,
    name       VARCHAR(255)                       NOT NULL,
    phone      VARCHAR(64)                        NOT NULL,
    address    VARCHAR(255)                       NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by INT NULL,
    updated_by INT NULL,
    deleted_at DATETIME NULL
);

CREATE TABLE supplier_aud
(
    id         INT          NOT NULL,
    REV        BIGINT       NOT NULL,
    REVTYPE    INT          NOT NULL,
    email      VARCHAR(255) NOT NULL,
    name       VARCHAR(255) NOT NULL,
    phone      VARCHAR(64)  NOT NULL,
    address    VARCHAR(255) NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    created_by INT,
    updated_by INT,
    deleted_at DATETIME,
    PRIMARY KEY (id, REV)
);

CREATE TABLE import_invoice
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    total_amount DOUBLE NOT NULL,
    note        TEXT,
    date        DATETIME,
    supplier_id INT,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by  INT NULL,
    updated_by  INT NULL,
    deleted_at  DATETIME NULL,
    CONSTRAINT fk_import_invoice_supplier_id
        FOREIGN KEY (supplier_id) REFERENCES supplier (id)
);

CREATE TABLE import_invoice_aud
(
    id          INT    NOT NULL,
    REV         BIGINT NOT NULL,
    REVTYPE     INT    NOT NULL,
    total_amount DOUBLE NOT NULL,
    note        TEXT,
    date        DATETIME,
    supplier_id INT,
    created_at  DATETIME,
    updated_at  DATETIME,
    created_by  INT,
    updated_by  INT,
    deleted_at  DATETIME,
    PRIMARY KEY (id, REV)
);

CREATE TABLE import_invoice_accessory
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    import_price DOUBLE NOT NULL,
    quantity          INT,
    import_invoice_id INT,
    accessory_id      INT,
    created_at        DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at        DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by        INT NULL,
    updated_by        INT NULL,
    deleted_at        DATETIME NULL,
    CONSTRAINT fk_import_invoice_accessory_import_invoice_id
        FOREIGN KEY (import_invoice_id) REFERENCES import_invoice (id),
    CONSTRAINT fk_import_invoice_accessory_accessory_id
        FOREIGN KEY (accessory_id) REFERENCES accessory (id)
);

CREATE TABLE import_invoice_accessory_aud
(
    id                INT    NOT NULL,
    REV               BIGINT NOT NULL,
    REVTYPE           INT    NOT NULL,
    import_price DOUBLE NOT NULL,
    quantity          INT,
    import_invoice_id INT,
    accessory_id      INT,
    created_at        DATETIME,
    updated_at        DATETIME,
    created_by        INT,
    updated_by        INT,
    deleted_at        DATETIME,
    PRIMARY KEY (id, REV)
);

-- changeset congqt:20240715.200000.05

ALTER TABLE appointment
    ADD COLUMN note TEXT AFTER status;

ALTER TABLE appointment_aud
    ADD COLUMN note TEXT AFTER status;

-- changeset congqt:20240715.210000.05

ALTER TABLE import_invoice
    ADD COLUMN status VARCHAR(255) AFTER note;

ALTER TABLE import_invoice_aud
    ADD COLUMN status VARCHAR(255) AFTER note;

ALTER TABLE accessory
    ADD COLUMN supplier_id INT AFTER name;

ALTER TABLE accessory_aud
    ADD COLUMN supplier_id INT AFTER name;

ALTER TABLE accessory
  ADD CONSTRAINT fk_accessory_supplier_id
    FOREIGN KEY (supplier_id) REFERENCES supplier (id);

