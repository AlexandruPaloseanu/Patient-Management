CREATE DATABASE PATIENT_MANAGEMENT;
USE PATIENT_MANAGEMENT;

CREATE TABLE USERS (
	USER_ID INT PRIMARY KEY AUTO_INCREMENT,
    USER_USERNAME VARCHAR(50) UNIQUE,
    USER_PASSWORD VARCHAR(50),
    USER_TYPE VARCHAR(20)
);

CREATE TABLE MEDICS (
	MEDIC_ID INT PRIMARY KEY AUTO_INCREMENT,
    LAST_NAME VARCHAR(50),
    FIRST_NAME VARCHAR(50),
    BIRTH_DATE DATE,
    SPECIALIZATION VARCHAR(100),
    USER_ID INT,-- FOREIGN KEY
    CONSTRAINT FK_MEDICS_USER_ID FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID)
);

CREATE TABLE PATIENTS (
	PATIENT_ID INT PRIMARY KEY AUTO_INCREMENT,
    LAST_NAME VARCHAR(50),
    FIRST_NAME VARCHAR(50),
    BIRTH_DATE DATE,
    USER_ID INT, -- FOREIGN KEY
	CONSTRAINT FK_PATIENTS_USER_ID FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID)
);

CREATE TABLE PATIENT_SHEETS (
	SHEET_ID INT PRIMARY KEY AUTO_INCREMENT,
    PATIENT_ID INT, -- FOREIGN KEY
    MEDIC_ID INT, -- FOREIGN KEY
    DIAGNOSIS VARCHAR(1000),
    TREATMENT VARCHAR(1000),
    CONSTRAINT FK_PATIENT_SHEETS_PATIENT_ID FOREIGN KEY (PATIENT_ID) REFERENCES PATIENTS (PATIENT_ID),
    CONSTRAINT FK_PATIENT_SHEETS_MEDIC_ID FOREIGN KEY (MEDIC_ID) REFERENCES MEDICS (MEDIC_ID)
);

CREATE TABLE APPOINTMENTS (
	APPOINTMENT_ID INT PRIMARY KEY AUTO_INCREMENT,
    PATIENT_ID INT, -- FOREIGN KEY
    MEDIC_ID INT, -- FOREIGN KEY
    APPOINTMENT_DATE DATE,
    APPOINTMENT_TIME TIME,
    APPOINTMENT_STATUS VARCHAR(50),
    CONSTRAINT FK_APPOINTMENTS_PATIENT_ID FOREIGN KEY (PATIENT_ID) REFERENCES PATIENTS (PATIENT_ID),
    CONSTRAINT FK_APPOINTMENTS_MEDIC_ID FOREIGN KEY (MEDIC_ID) REFERENCES MEDICS (MEDIC_ID)
);


-- INSERTS

-- USERS
INSERT INTO `patient_management`.`users` (`USER_ID`, `USER_USERNAME`, `USER_PASSWORD`, `USER_TYPE`) VALUES ('1', 'alexandru.paloseanu', 'alexandruNR1', 'MEDIC');
INSERT INTO `patient_management`.`users` (`USER_ID`, `USER_USERNAME`, `USER_PASSWORD`, `USER_TYPE`) VALUES ('2', 'marius.pop', 'haideU123', 'MEDIC');
INSERT INTO `patient_management`.`users` (`USER_ID`, `USER_USERNAME`, `USER_PASSWORD`, `USER_TYPE`) VALUES ('3', 'septimiu.soporan', 'parola1234', 'MEDIC');
INSERT INTO `patient_management`.`users` (`USER_ID`, `USER_USERNAME`, `USER_PASSWORD`, `USER_TYPE`) VALUES ('4', 'ecaterina.comanescu', 'm@k3Up', 'MEDIC');
INSERT INTO `patient_management`.`users` (`USER_ID`, `USER_USERNAME`, `USER_PASSWORD`, `USER_TYPE`) VALUES ('5', 'maria.moraru', 'bl3kK3t', 'MEDIC');

INSERT INTO `patient_management`.`users` (`USER_ID`, `USER_USERNAME`, `USER_PASSWORD`, `USER_TYPE`) VALUES ('6', 'calin.popescu', 'nuStiuCineSunt', 'PATIENT');
INSERT INTO `patient_management`.`users` (`USER_ID`, `USER_USERNAME`, `USER_PASSWORD`, `USER_TYPE`) VALUES ('7', 'tudor.campean', 'hahaSuntProgramator', 'PATIENT');
INSERT INTO `patient_management`.`users` (`USER_ID`, `USER_USERNAME`, `USER_PASSWORD`, `USER_TYPE`) VALUES ('8', 'alexandru.filip', 'dacaNuFacSalaMor', 'PATIENT');
INSERT INTO `patient_management`.`users` (`USER_ID`, `USER_USERNAME`, `USER_PASSWORD`, `USER_TYPE`) VALUES ('9', 'andreea.boie', '12445654', 'PATIENT');
INSERT INTO `patient_management`.`users` (`USER_ID`, `USER_USERNAME`, `USER_PASSWORD`, `USER_TYPE`) VALUES ('10', 'alexandra.cosbuc', 'undeEsteMD', 'PATIENT');
INSERT INTO `patient_management`.`USERS` (`USER_ID`, `USER_USERNAME`, `USER_PASSWORD`, `USER_TYPE`) VALUES ('11', 'andrei.florinescu', 'testPass16', 'PATIENT');
INSERT INTO `patient_management`.`USERS` (`USER_ID`, `USER_USERNAME`, `USER_PASSWORD`, `USER_TYPE`) VALUES ('12', 'violeta.floria', 'testPass15', 'PATIENT');
INSERT INTO `patient_management`.`USERS` (`USER_ID`, `USER_USERNAME`, `USER_PASSWORD`, `USER_TYPE`) VALUES ('13', 'carmen.eugen', 'testPass14', 'PATIENT');
INSERT INTO `patient_management`.`USERS` (`USER_ID`, `USER_USERNAME`, `USER_PASSWORD`, `USER_TYPE`) VALUES ('14', 'sorina.matei', 'testPass13', 'PATIENT');
INSERT INTO `patient_management`.`USERS` (`USER_ID`, `USER_USERNAME`, `USER_PASSWORD`, `USER_TYPE`) VALUES ('15', 'valeriu.moldovan', 'testPass12', 'PATIENT');
INSERT INTO `patient_management`.`USERS` (`USER_ID`, `USER_USERNAME`, `USER_PASSWORD`, `USER_TYPE`) VALUES ('16', 'elena.broasca', 'testPass11', 'PATIENT');
INSERT INTO `patient_management`.`USERS` (`USER_ID`, `USER_USERNAME`, `USER_PASSWORD`, `USER_TYPE`) VALUES ('17', 'mircea.flueras', 'testPass9', 'PATIENT');
INSERT INTO `patient_management`.`USERS` (`USER_ID`, `USER_USERNAME`, `USER_PASSWORD`, `USER_TYPE`) VALUES ('18', 'viorel.patrana', 'testPass8', 'PATIENT');
INSERT INTO `patient_management`.`USERS` (`USER_ID`, `USER_USERNAME`, `USER_PASSWORD`, `USER_TYPE`) VALUES ('19', 'ilie.diaconu', 'testPass7', 'PATIENT');
INSERT INTO `patient_management`.`USERS` (`USER_ID`, `USER_USERNAME`, `USER_PASSWORD`, `USER_TYPE`) VALUES ('20', 'gabriel.diaconu', 'testPass6', 'PATIENT');
INSERT INTO `patient_management`.`USERS` (`USER_ID`, `USER_USERNAME`, `USER_PASSWORD`, `USER_TYPE`) VALUES ('21', 'aristide.briand', 'testPass5', 'PATIENT');
INSERT INTO `patient_management`.`USERS` (`USER_ID`, `USER_USERNAME`, `USER_PASSWORD`, `USER_TYPE`) VALUES ('22', 'nicolas.sarkozy', 'testPass4', 'PATIENT');
INSERT INTO `patient_management`.`USERS` (`USER_ID`, `USER_USERNAME`, `USER_PASSWORD`, `USER_TYPE`) VALUES ('23', 'ryuji.nakamura', 'testPass3', 'PATIENT');
INSERT INTO `patient_management`.`USERS` (`USER_ID`, `USER_USERNAME`, `USER_PASSWORD`, `USER_TYPE`) VALUES ('24', 'joe.joestar', 'testPass2', 'PATIENT');
INSERT INTO `patient_management`.`USERS` (`USER_ID`, `USER_USERNAME`, `USER_PASSWORD`, `USER_TYPE`) VALUES ('25', 'donald.trump', 'testPass1', 'PATIENT');


-- PATIENTS

INSERT INTO `patient_management`.`patients` (`PATIENT_ID`, `LAST_NAME`, `FIRST_NAME`, `BIRTH_DATE`, `USER_ID`) VALUES ('1', 'Popescu', 'Calin', '1996-10-10', '6');
INSERT INTO `patient_management`.`patients` (`PATIENT_ID`, `LAST_NAME`, `FIRST_NAME`, `BIRTH_DATE`, `USER_ID`) VALUES ('2', 'Campean', 'Tudor', '1997-11-28', '7');
INSERT INTO `patient_management`.`patients` (`PATIENT_ID`, `LAST_NAME`, `FIRST_NAME`, `BIRTH_DATE`, `USER_ID`) VALUES ('3', 'Filip', 'Alexandru', '1997-07-11', '8');
INSERT INTO `patient_management`.`patients` (`PATIENT_ID`, `LAST_NAME`, `FIRST_NAME`, `BIRTH_DATE`, `USER_ID`) VALUES ('4', 'Boie', 'Andreea', '1997-12-08', '9');
INSERT INTO `patient_management`.`patients` (`PATIENT_ID`, `LAST_NAME`, `FIRST_NAME`, `BIRTH_DATE`, `USER_ID`) VALUES ('5', 'Cosbuc', 'Alexandra', '1997-12-03', '10');
INSERT INTO `patient_management`.`PATIENTS` (`PATIENT_ID`, `LAST_NAME`, `FIRST_NAME`, `BIRTH_DATE`, `USER_ID`) VALUES ('6', 'Florinescu', 'Andrei', '1990-10-03', '11');
INSERT INTO `patient_management`.`PATIENTS` (`PATIENT_ID`, `LAST_NAME`, `FIRST_NAME`, `BIRTH_DATE`, `USER_ID`) VALUES ('7', 'Floria', 'Violeta', '1989-11-05', '12');
INSERT INTO `patient_management`.`PATIENTS` (`PATIENT_ID`, `LAST_NAME`, `FIRST_NAME`, `BIRTH_DATE`, `USER_ID`) VALUES ('8', 'Eugen', 'Carmen', '1987-05-05', '13');
INSERT INTO `patient_management`.`PATIENTS` (`PATIENT_ID`, `LAST_NAME`, `FIRST_NAME`, `BIRTH_DATE`, `USER_ID`) VALUES ('9', 'Matei', 'Sorina', '1987-05-05', '14');
INSERT INTO `patient_management`.`PATIENTS` (`PATIENT_ID`, `LAST_NAME`, `FIRST_NAME`, `BIRTH_DATE`, `USER_ID`) VALUES ('10', 'Moldovan', 'Valeriu', '1987-05-05', '15');
INSERT INTO `patient_management`.`PATIENTS` (`PATIENT_ID`, `LAST_NAME`, `FIRST_NAME`, `BIRTH_DATE`, `USER_ID`) VALUES ('11', 'Broasca', 'Elena', '1987-05-05', '16');
INSERT INTO `patient_management`.`PATIENTS` (`PATIENT_ID`, `LAST_NAME`, `FIRST_NAME`, `BIRTH_DATE`, `USER_ID`) VALUES ('12', 'Flueras', 'Mircea', '1987-05-05', '17');
INSERT INTO `patient_management`.`PATIENTS` (`PATIENT_ID`, `LAST_NAME`, `FIRST_NAME`, `BIRTH_DATE`, `USER_ID`) VALUES ('13', 'Patrana', 'Viorel', '1987-05-05', '18');
INSERT INTO `patient_management`.`PATIENTS` (`PATIENT_ID`, `LAST_NAME`, `FIRST_NAME`, `BIRTH_DATE`, `USER_ID`) VALUES ('14', 'Diaconu', 'Ilie', '1987-05-05', '19');
INSERT INTO `patient_management`.`PATIENTS` (`PATIENT_ID`, `LAST_NAME`, `FIRST_NAME`, `BIRTH_DATE`, `USER_ID`) VALUES ('15', 'Diaconu', 'Gabriel', '1987-05-05', '20');
INSERT INTO `patient_management`.`PATIENTS` (`PATIENT_ID`, `LAST_NAME`, `FIRST_NAME`, `BIRTH_DATE`, `USER_ID`) VALUES ('16', 'Briand', 'Aristide', '1987-05-05', '21');
INSERT INTO `patient_management`.`PATIENTS` (`PATIENT_ID`, `LAST_NAME`, `FIRST_NAME`, `BIRTH_DATE`, `USER_ID`) VALUES ('17', 'Sarkozy', 'Nicolas', '1987-05-05', '22');
INSERT INTO `patient_management`.`PATIENTS` (`PATIENT_ID`, `LAST_NAME`, `FIRST_NAME`, `BIRTH_DATE`, `USER_ID`) VALUES ('18', 'Nakamura', 'Ryuji', '1987-05-05', '23');
INSERT INTO `patient_management`.`PATIENTS` (`PATIENT_ID`, `LAST_NAME`, `FIRST_NAME`, `BIRTH_DATE`, `USER_ID`) VALUES ('19', 'Joestar', 'Joe', '1987-05-05', '24');
INSERT INTO `patient_management`.`PATIENTS` (`PATIENT_ID`, `LAST_NAME`, `FIRST_NAME`, `BIRTH_DATE`, `USER_ID`) VALUES ('20', 'Trump', 'Donald', '1987-05-05', '25');


-- MEDICS

INSERT INTO `PATIENT_MANAGEMENT`.`MEDICS` (`MEDIC_ID`, `LAST_NAME`, `FIRST_NAME`, `BIRTH_DATE`, `SPECIALIZATION`, `USER_ID`) VALUES ('1', 'Paloseanu', 'Alexandru', '1997-05-28', 'Heart Transplant', '1');
INSERT INTO `PATIENT_MANAGEMENT`.`MEDICS` (`MEDIC_ID`, `LAST_NAME`, `FIRST_NAME`, `BIRTH_DATE`, `SPECIALIZATION`, `USER_ID`) VALUES ('2', 'Pop', 'Marius', '1997-06-10', 'Osteology', '2');
INSERT INTO `PATIENT_MANAGEMENT`.`MEDICS` (`MEDIC_ID`, `LAST_NAME`, `FIRST_NAME`, `BIRTH_DATE`, `SPECIALIZATION`, `USER_ID`) VALUES ('3', 'Soporan', 'Septimiu', '1997-01-15', 'Dermatology', '3');
INSERT INTO `PATIENT_MANAGEMENT`.`MEDICS` (`MEDIC_ID`, `LAST_NAME`, `FIRST_NAME`, `BIRTH_DATE`, `SPECIALIZATION`, `USER_ID`) VALUES ('4', 'Comanescu', 'Ecaterina', '1997-08-20', 'Immunology', '4');
INSERT INTO `PATIENT_MANAGEMENT`.`MEDICS` (`MEDIC_ID`, `LAST_NAME`, `FIRST_NAME`, `BIRTH_DATE`, `SPECIALIZATION`, `USER_ID`) VALUES ('5', 'Moraru', 'Maria', '1997-11-30', 'Pulmonary', '5');


-- PATIENT SHEETS

INSERT INTO `patient_management`.`patient_sheets` (`SHEET_ID`, `PATIENT_ID`, `MEDIC_ID`, `DIAGNOSIS`, `TREATMENT`) VALUES ('1', '1', '5', 'Stomache Ache', 'Pills');
INSERT INTO `patient_management`.`patient_sheets` (`SHEET_ID`, `PATIENT_ID`, `MEDIC_ID`, `DIAGNOSIS`, `TREATMENT`) VALUES ('2', '2', '4', 'Headache', 'Rest');
INSERT INTO `patient_management`.`patient_sheets` (`SHEET_ID`, `PATIENT_ID`, `MEDIC_ID`, `DIAGNOSIS`, `TREATMENT`) VALUES ('3', '3', '3', 'Acne', 'Cream');
INSERT INTO `patient_management`.`patient_sheets` (`SHEET_ID`, `PATIENT_ID`, `MEDIC_ID`, `DIAGNOSIS`, `TREATMENT`) VALUES ('4', '4', '2', 'Flat foot', 'Foot support');
INSERT INTO `patient_management`.`patient_sheets` (`SHEET_ID`, `PATIENT_ID`, `MEDIC_ID`, `DIAGNOSIS`, `TREATMENT`) VALUES ('5', '5', '1', 'Broken Arm', 'Cast + rest');

-- APPOINTMENTS

INSERT INTO `patient_management`.`appointments` (`APPOINTMENT_ID`, `PATIENT_ID`, `MEDIC_ID`, `APPOINTMENT_DATE`, `APPOINTMENT_TIME`, `APPOINTMENT_STATUS`) VALUES ('1', '1', '5', '2020-12-20', '10:00', 'TRUE');
INSERT INTO `patient_management`.`appointments` (`APPOINTMENT_ID`, `PATIENT_ID`, `MEDIC_ID`, `APPOINTMENT_DATE`, `APPOINTMENT_TIME`, `APPOINTMENT_STATUS`) VALUES ('2', '2', '4', '2020-12-21', '12:00', 'TRUE');
INSERT INTO `patient_management`.`appointments` (`APPOINTMENT_ID`, `PATIENT_ID`, `MEDIC_ID`, `APPOINTMENT_DATE`, `APPOINTMENT_TIME`, `APPOINTMENT_STATUS`) VALUES ('3', '3', '3', '2021-01-10', '14:00', 'FALSE');
INSERT INTO `patient_management`.`appointments` (`APPOINTMENT_ID`, `PATIENT_ID`, `MEDIC_ID`, `APPOINTMENT_DATE`, `APPOINTMENT_TIME`, `APPOINTMENT_STATUS`) VALUES ('4', '4', '2', '2021-01-15', '16:00', 'FALSE');
INSERT INTO `patient_management`.`appointments` (`APPOINTMENT_ID`, `PATIENT_ID`, `MEDIC_ID`, `APPOINTMENT_DATE`, `APPOINTMENT_TIME`, `APPOINTMENT_STATUS`) VALUES ('5', '5', '1', '2021-02-01', '18:00', 'FALSE');
INSERT INTO `patient_management`.`APPOINTMENTS` (`APPOINTMENT_ID`, `PATIENT_ID`, `MEDIC_ID`, `APPOINTMENT_DATE`, `APPOINTMENT_TIME`, `APPOINTMENT_STATUS`) VALUES ('6', '2', '3', '2021-02-15', '18:00:00', 'TRUE');


-- EXTRA
















