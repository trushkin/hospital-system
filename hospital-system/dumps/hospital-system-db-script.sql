CREATE TABLE IF NOT EXISTS company_user
(
    id    int8         NOT NULL GENERATED BY DEFAULT AS IDENTITY,

    login varchar(255) NOT NULL,

    CONSTRAINT company_user_pkey PRIMARY KEY (id),
    CONSTRAINT company_user_uniq_login UNIQUE (login)
);


CREATE TABLE IF NOT EXISTS patient_profile
(
    id         int8         NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    first_name varchar(255) NULL,
    last_name  varchar(255) NULL,
    status_id  int2         NOT NULL,

    CONSTRAINT patient_profile_pkey PRIMARY KEY (id)

);
CREATE TABLE IF NOT EXISTS patient_guid
(
    id                 int8         NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    old_client_guid    varchar(255) NOT NULL,
    patient_profile_id int8,

    CONSTRAINT patient_guid_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS patient_note
(
    id                       int8          NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    created_date_time        timestamp     NOT NULL,
    last_modified_date_time  timestamp     NOT NULL,
    created_by_user_id       int8          NULL,
    last_modified_by_user_id int8          NULL,
    note                     varchar(4000) NULL,
    patient_id               int8          NOT NULL,

    CONSTRAINT patient_note_pkey PRIMARY KEY (id)
);
ALTER TABLE patient_note
    ADD CONSTRAINT fk_pat_note_to_modifyed_user FOREIGN KEY (last_modified_by_user_id) REFERENCES company_user (id);
ALTER TABLE patient_note
    ADD CONSTRAINT fk_pat_note_to_created_user FOREIGN KEY (created_by_user_id) REFERENCES company_user (id);
ALTER TABLE patient_note
    ADD CONSTRAINT fk_pat_note_to_patient FOREIGN KEY (patient_id) REFERENCES patient_profile (id);
ALTER TABLE patient_guid
    ADD CONSTRAINT fk_pat_guid_to_pat_note FOREIGN KEY (patient_profile_id) REFERENCES patient_note (id);