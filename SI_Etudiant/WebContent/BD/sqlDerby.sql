drop table ETUDIANT_ENTREPRISE;
drop table ETUDIANT_FORMATION;
drop table ENTREPRISE.
drop table ETABLISSEMENT;
drop table FORMATION;
drop table ETUDIANT;
drop table UTILISATEUR;


CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50), SEQ_COUNT DECIMAL(15));
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0);


create table ENTREPRISE
(
ENTREPRISE_ID BIGINT not null,
adresse varchar(70),
adresseSuite varchar(70),
codePostal varchar(70),
pays varchar(70),
ville varchar(70),
mail varchar(70),
mailAutre varchar(70),
tel varchar(70),
telAutre varchar(70),
nom varchar(70),
raisonsociale varchar(70),
secteuractivite varchar(70),
siret varchar(70),
primary key (ENTREPRISE_ID)
);

INSERT INTO ENTREPRISE VALUES (1,'16 RUE','','62300','fr','LENS','toto@t.fr','','0608','0321','GFI','sa','info','154564');
INSERT INTO ENTREPRISE VALUES (1,'16 RUE','','62300','fr','LENS','toto@t.fr','','0608','0321','Norsys','sa','info','154564');
INSERT INTO ENTREPRISE VALUES (1,'16 RUE','','62300','fr','LENS','toto@t.fr','','0608','0321','logica','sa','info','154564');

create table ETABLISSEMENT (
ETABLISSEMENT_ID BIGINT not null,
adresse varchar(70),
adresseSuite varchar(70),
codePostal varchar(70),
pays varchar(70),
ville varchar(70),
mail varchar(70),
mailAutre varchar(70),
tel varchar(70),
telAutre varchar(70),
nom varchar(70),
typeE varchar(70),
primary key (ETABLISSEMENT_ID));

create table ETUDIANT (
ETUDIANT_ID BIGINT  not null,
adresse varchar(70),
adresseSuite varchar(70),
codePostal varchar(70),
pays varchar(70),
ville varchar(70),
mail varchar(70),
mailAutre varchar(70),
tel varchar(70),
telAutre varchar(70),
nom varchar(70),
prenom varchar(70),
primary key (ETUDIANT_ID));

create table ETUDIANT_ENTREPRISE
(
ID BIGINT not null,
ENTREPRISE_ID BIGINT not null,
ETUDIANT_ID BIGINT not null,
datedebut date,
datefin date,
description varchar(255),
posteoccupe varchar(70),
typecontrat varchar(70),
primary key (ENTREPRISE_ID, ETUDIANT_ID)
);

create table ETUDIANT_FORMATION (
ETUDIANT_ID  BIGINT not null,
FORMATION_ID BIGINT not null,
datedebut date,
datefin date,
description varchar(255),
resultat varchar(70),
primary key (ETUDIANT_ID, FORMATION_ID));

create table FORMATION
(
FORMATION_ID BIGINT not null,
libelle varchar(70),
libelleCourt varchar(70),
ETABLISSEMENT_ID BIGINT,
primary key (FORMATION_ID));

create table UTILISATEUR
(
id BIGINT not null,
login varchar(70),
motDePasse varchar(70),
niveau BIGINT,
nomComplet varchar(70),
primary key (id)
);

alter table ETUDIANT_ENTREPRISE add constraint FK9479645E102F4471 foreign key (ETUDIANT_ID) references ETUDIANT(ETUDIANT_ID);
alter table ETUDIANT_ENTREPRISE add constraint FK9479645EDE8E3651 foreign key (ENTREPRISE_ID) references ENTREPRISE(ENTREPRISE_ID);
alter table ETUDIANT_FORMATION add constraint FK1AC7716E10E34EC3 foreign key (FORMATION_ID) references FORMATION(FORMATION_ID);
alter table ETUDIANT_FORMATION add constraint FK1AC7716E102F4471 foreign key (ETUDIANT_ID) references ETUDIANT(ETUDIANT_ID);
alter table FORMATION add constraint FK121298919D85AF8C foreign key (ETABLISSEMENT_ID) references ETABLISSEMENT(ETABLISSEMENT_ID);


INSERT INTO UTILISATEUR VALUES (1,'alex','534b44a19bf18d20b71ecc4eb77c572f','admin','alex');
INSERT INTO UTILISATEUR VALUES (2,'romain','5026bc63b5418ffdb54f238db245ec01','admin','romain');
INSERT INTO UTILISATEUR VALUES (3,'gerardo','4024fb06e1423da90b80f0274e8e4476','admin','alex');

INSERT INTO ENTREPRISE VALUES (1,'alex','534b44a19bf18d20b71ecc4eb77c572f','admin','alex');
INSERT INTO ENTREPRISE VALUES (2,'romain','5026bc63b5418ffdb54f238db245ec01','admin','romain');
INSERT INTO ENTREPRISE VALUES (3,'gerardo','4024fb06e1423da90b80f0274e8e4476','admin','alex');

INSERT INTO ENTREPRISE VALUES (1,'16 RUE','','62300','fr','LENS','toto@t.fr','','0608','0321','GFI','sa','info','154564');
INSERT INTO ENTREPRISE VALUES (2,'16 RUE','','62300','fr','LENS','toto@t.fr','','0608','0321','Norsys','sa','info','154564');
INSERT INTO ENTREPRISE VALUES (3,'16 RUE','','62300','fr','LENS','toto@t.fr','','0608','0321','logica','sa','info','154564');

INSERT INTO ETUDIANT VALUES (1,'16 RUE','','62300','fr','LENS','toto@t.fr','','0608','0321','Romain','BAVIER');
INSERT INTO ETUDIANT VALUES (2,'16 RUE','','62300','fr','LENS','toto@t.fr','','0608','0321','Alex ','HANOCK');
INSERT INTO ETUDIANT VALUES (3,'16 RUE','','62300','fr','LENS','toto@t.fr','','0608','0321','Romain','MORIVAL');

