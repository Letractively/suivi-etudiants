--<ScriptOptions statementTerminator=";"/>
-- Suppression des tables si erreur --

-- Suppression des tables si erreur --
DROP TABLE UTILISATEUR;
DROP TABLE ETUDIANT_FORMATION;
DROP TABLE ETUDIANT_ENTREPRISE;
DROP TABLE FORMATION;
DROP TABLE TYPEFORMATION;
DROP TABLE ETUDIANT;
DROP TABLE ENTREPRISE;

-- Creation des tables --
CREATE TABLE UTILISATEUR
(
LOGIN VARCHAR(150) NOT NULL,
MOTDEPASSE VARCHAR(150) NOT NULL,
NIVEAU INTEGER
);

CREATE TABLE ETUDIANT
(
IDETUDIANT BIGINT NOT NULL,
NOM VARCHAR(30) NOT NULL,
PRENOM VARCHAR(30) NOT NULL,
SEXE CHAR(10) NOT NULL,
ADRESSE VARCHAR(150) NOT NULL,
CODEPOSTAL VARCHAR(6) NOT NULL,
TEL VARCHAR(30),
MAIL VARCHAR(30),
NATIONALITE VARCHAR(30)
);

CREATE TABLE ENTREPRISE
(
IDENTREPRISE BIGINT NOT NULL,
NOMENTREPRISE VARCHAR(150) NOT NULL,
RAISONSOCIALE VARCHAR(150),
SECTEURACTIVITE VARCHAR(150),
MAILENTREPRISE VARCHAR(150),
TELENTREPRISE VARCHAR(30)
);

CREATE TABLE ACTIVITE_PROFESSIONNELLE
(
IDACTIVITEPRO BIGINT NOT NULL,
IDETUDIANT BIGINT NOT NULL,
IDENTREPRISE BIGINT NOT NULL,
DATEDEBUT DATE,
DATEFIN DATE,
TYPECONTRAT VARCHAR(150),
POSTEOCCUPE VARCHAR(150),
DESCRIPTION VARCHAR(150),
COMMENTAIRE VARCHAR(150)
);

CREATE TABLE TYPEFORMATION
(
IDTYPEFORMATION BIGINT NOT NULL,
LIBELLETYPE VARCHAR(150) NOT NULL
);

CREATE TABLE FORMATION
(
IDFORMATION BIGINT NOT NULL,
IDTYPEFORMATION BIGINT NOT NULL,
NOMFORMATION VARCHAR(150) NOT NULL,
ETABLISSEMENT VARCHAR(150)
);

CREATE TABLE ETUDIANT_FORMATION
(
IDETUDIANT BIGINT NOT NULL,
IDFORMATION BIGINT NOT NULL,
ANNEE VARCHAR(4) NOT NULL
);

-- Ajout des contraintes pour chaque table --
ALTER TABLE UTILISATEUR ADD CONSTRAINT PK_UTILISATEUR PRIMARY KEY (LOGIN);
ALTER TABLE ETUDIANT ADD CONSTRAINT PK_ETUDIANT PRIMARY KEY (IDETUDIANT);
ALTER TABLE ENTREPRISE ADD CONSTRAINT PK_ENTREPRISE PRIMARY KEY (IDENTREPRISE);
ALTER TABLE TYPEFORMATION ADD CONSTRAINT PK_TYPEFORMATION PRIMARY KEY (IDTYPEFORMATION);
ALTER TABLE FORMATION ADD CONSTRAINT PK_FORMATION PRIMARY KEY (IDFORMATION);

ALTER TABLE ACTIVITE_PROFESSIONNELLE ADD CONSTRAINT PK_ACTIVITE_PROFESSIONNELLE PRIMARY KEY (IDACTIVITEPRO,IDETUDIANT,IDENTREPRISE);
ALTER TABLE ETUDIANT_FORMATION ADD CONSTRAINT PK_ETUDIANT_FORMATION PRIMARY KEY (IDETUDIANT,IDFORMATION,ANNEE);

ALTER TABLE ACTIVITE_PROFESSIONNELLE ADD CONSTRAINT FK_ACTIVITE_PROFESSIONNELLE_ETUDIANT FOREIGN KEY (IDETUDIANT) REFERENCES ETUDIANT (IDETUDIANT);
ALTER TABLE ACTIVITE_PROFESSIONNELLE ADD CONSTRAINT FK_ACTIVITE_PROFESSIONNELLE_ENTREPRISE FOREIGN KEY (IDENTREPRISE) REFERENCES  ENTREPRISE (IDENTREPRISE);

ALTER TABLE ETUDIANT_FORMATION ADD CONSTRAINT FK_ETUDIANT_FORMATION_ETUDIANT FOREIGN KEY (IDETUDIANT) REFERENCES ETUDIANT (IDETUDIANT);
ALTER TABLE ETUDIANT_FORMATION ADD CONSTRAINT FK_ETUDIANT_FORMATION_FORMATION FOREIGN KEY (IDFORMATION) REFERENCES FORMATION (IDFORMATION);

ALTER TABLE FORMATION ADD CONSTRAINT FK_FORMATION_TYPE_FORMATION FOREIGN KEY (IDTYPEFORMATION) REFERENCES TYPEFORMATION (IDTYPEFORMATION);

INSERT INTO UTILISATEUR (LOGIN,MOTDEPASSE,NIVEAU) VALUES ('alex','534b44a19bf18d20b71ecc4eb77c572f',2);
INSERT INTO UTILISATEUR (LOGIN,MOTDEPASSE,NIVEAU) VALUES ('romain','5026bc63b5418ffdb54f238db245ec01',1);
INSERT INTO UTILISATEUR (LOGIN,MOTDEPASSE,NIVEAU) VALUES ('gerardo','4024fb06e1423da90b80f0274e8e4476',1);

INSERT INTO ETUDIANT (ID,NOM,PRENOM,SEXE,ADRESSE,CODEPOSTAL,TEL,MAIL) VALUES (1,'Durand','Charles','M','56 rue des lilas','62000','0321698541','C.Durand@yahoo.fr');
INSERT INTO ETUDIANT (ID,NOM,PRENOM,SEXE,ADRESSE,CODEPOSTAL,TEL,MAIL) VALUES (2,'Dupont','Jean','M','12 rue du houx','62000','0321456323','J.Dupont@free.fr');
INSERT INTO ETUDIANT (ID,NOM,PRENOM,SEXE,ADRESSE,CODEPOSTAL,TEL,MAIL) VALUES (3,'Jom','Patrick','M','2 avenue de la liberte','75000','0123658541','P.Jom@hotmail.fr');
INSERT INTO ETUDIANT (ID,NOM,PRENOM,SEXE,ADRESSE,CODEPOSTAL,TEL,MAIL) VALUES (4,'Eah','Julie','F','88 rue de la mairie','59000','0321857412','J.Eah@Laposte.net');

INSERT INTO ENTREPRISE (ID,NOM,RAISONSOCIALE,SECTEURACTIVITE,MAIL,TEL) VALUES (1,'AUCHAN','SARL','COMMERCE','test@mail.fr','toto');
INSERT INTO ENTREPRISE (ID,NOM,RAISONSOCIALE,SECTEURACTIVITE,MAIL,TEL) VALUES (2,'RENAULT','SA','AUTOMOBILE','test@mail.fr','toto');

--INSERT INTO ETUDIANT_FORMATION (IDETUDIANT,IDFORMATION,ANNEE) VALUES (1,1,'2009');
--INSERT INTO ETUDIANT_FORMATION (IDETUDIANT,IDFORMATION,ANNEE) VALUES (1,2,'2011');
--INSERT INTO ETUDIANT_FORMATION (IDETUDIANT,IDFORMATION,ANNEE) VALUES (2,2,'2010');
--INSERT INTO ETUDIANT_FORMATION (IDETUDIANT,IDFORMATION,ANNEE) VALUES (3,2,'2002');
--INSERT INTO ETUDIANT_FORMATION (IDETUDIANT,IDFORMATION,ANNEE) VALUES (4,1,'2011');

-- INSERT INTO ETUDIANT_ENTREPRISE (ID,IDETUDIANT,IDPROFESSION) VALUES (1,1);  --
--INSERT INTO ETUDIANT_ENTREPRISE(ID,IDETUDIANT,IDPROFESSION) VALUES (2,1); --
--INSERT INTO ETUDIANT_ENTREPRISE (ID,IDETUDIANT,IDPROFESSION) VALUES (3,1);  --
--INSERT INTO ETUDIANT_ENTREPRISE (ID,IDETUDIANT,IDPROFESSION) VALUES (4,2);  --
