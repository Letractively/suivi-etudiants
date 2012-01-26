-- PostgreSQL

/*
 * Ajouter le .jar "postgresql-9.1-901.jdbc4" (selon la version de postgre) dans glassfish/lib 
 * afin que le serveur puisse localiser la classe de la source de donn�es pour �tablir la liaison */

-- Configuration des Pools de connexions JDBC :
-- Type de ressource : javax.sql.ConnectionPoolDataSource
-- Nom de classe de la source de donn�es : org.postgresql.ds.PGPoolingDataSource

-- Configuration des propri�t�s du DataSource (dans Glassfish) :
-- Nom du pool : Si_etu
-- User : postgres (par d�faut)
-- Password : root (par d�faut)
-- ServerName : localhost
-- databaseName : gestionEtu
-- PortNumber : 5432

-- Schema public (par defaut)

-- Suppression des contraintes
/*alter table ETUDIANT_ENTREPRISE drop constraint FK_ETUDIANT_ENTREPRISE_ETUDIANT;
alter table ETUDIANT_ENTREPRISE drop constraint FK_ETUDIANT_ENTREPRISE_ENTREPRISE;
alter table ETUDIANT_FORMATION drop constraint FK_ETUDIANT_FORMATION_FORMATION;
alter table ETUDIANT_FORMATION drop constraint FK_ETUDIANT_FORMATION_ETUDIANT;
alter table FORMATION drop constraint FK_FORMATION_ETABLISSEMENT;*/

-- Suppression des tables
drop table ETUDIANT_ENTREPRISE;
drop table ETUDIANT_FORMATION;
drop table ENTREPRISE;
drop table FORMATION;
drop table ETABLISSEMENT;
drop table ETUDIANT;
drop table UTILISATEUR;
DROP table SEQUENCE;

CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50), SEQ_COUNT DECIMAL(15));
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0);

-- UPDATE SEQUENCE SET SEQ_COUNT = 3;

-- Creation des tables
create table ENTREPRISE (ENTREPRISE_ID int not null, adresse varchar(70), adresseSuite varchar(70), codePostal varchar(70), pays varchar(70), ville varchar(70), mail varchar(70), mailAutre varchar(70), tel varchar(70), telAutre varchar(70), nom varchar(70), raisonsociale varchar(70), secteuractivite varchar(70), siret varchar(70), primary key (ENTREPRISE_ID));
create table ETABLISSEMENT (ETABLISSEMENT_ID int not null, adresse varchar(70), adresseSuite varchar(70), codePostal varchar(70), pays varchar(70), ville varchar(70), mail varchar(70), mailAutre varchar(70), tel varchar(70), telAutre varchar(70), nom varchar(70), typeEtab varchar(70), primary key (ETABLISSEMENT_ID));
create table ETUDIANT (ETUDIANT_ID int not null, adresse varchar(70), adresseSuite varchar(70), codePostal varchar(70), pays varchar(70), ville varchar(70), mail varchar(70), mailAutre varchar(70), tel varchar(70), telAutre varchar(70), nom varchar(70), prenom varchar(70), primary key (ETUDIANT_ID));
create table ETUDIANT_ENTREPRISE (datedebut date not null, ENTREPRISE_ID int not null, ETUDIANT_ID int not null, commentaire varchar(255), datefin date, description varchar(255), posteoccupe varchar(70), typecontrat varchar(70), primary key (datedebut, ENTREPRISE_ID, ETUDIANT_ID));
create table ETUDIANT_FORMATION (ETUDIANT_ID int not null, FORMATION_ID int not null, datedebut date, datefin date, description varchar(255), resultat varchar(70), primary key (ETUDIANT_ID, FORMATION_ID, datedebut));
create table FORMATION (FORMATION_ID int not null, libelle varchar(70), libelleCourt varchar(70), etablissement_ETABLISSEMENT_ID int, primary key (FORMATION_ID));
create table UTILISATEUR (id int not null, login varchar(70), motDePasse varchar(70), niveau varchar(70), nomComplet varchar(70), mail varchar(70), primary key (id));

-- Creation des contraintes
alter table ETUDIANT_ENTREPRISE add constraint FK_ETUDIANT_ENTREPRISE_ETUDIANT foreign key (ETUDIANT_ID) references ETUDIANT;
alter table ETUDIANT_ENTREPRISE add constraint FK_ETUDIANT_ENTREPRISE_ENTREPRISE foreign key (ENTREPRISE_ID) references ENTREPRISE;
alter table ETUDIANT_FORMATION add constraint FK_ETUDIANT_FORMATION_FORMATION foreign key (FORMATION_ID) references FORMATION;
alter table ETUDIANT_FORMATION add constraint FK_ETUDIANT_FORMATION_ETUDIANT foreign key (ETUDIANT_ID) references ETUDIANT;
alter table FORMATION add constraint FK_FORMATION_ETABLISSEMENT foreign key (etablissement_ETABLISSEMENT_ID) references ETABLISSEMENT;

-- Ajout de donnees de test
INSERT INTO UTILISATEUR VALUES (1,'alex','534b44a19bf18d20b71ecc4eb77c572f',1,'alex');
INSERT INTO UTILISATEUR VALUES (2,'romain','5026bc63b5418ffdb54f238db245ec01',1,'romain');
INSERT INTO UTILISATEUR VALUES (3,'gerardo','4024fb06e1423da90b80f0274e8e4476',2,'gerardo');

INSERT INTO ENTREPRISE VALUES (1,'16 rue de la place','','62300','France','Lens','contact@gfi.fr','','0608','0321','GFI','SA','SSII','15456456985474');
INSERT INTO ENTREPRISE VALUES (2,'16 rue des eaux','','62300','France','Lens','contact@norsys.fr','','0608','0321','Norsys','SAS','SSII','15456412568545');
INSERT INTO ENTREPRISE VALUES (3,'16 rue des maux','','62000','France','Arras','contact@logica.fr','','0608','0321','Logica','SARL','SSII','15456412658545');

INSERT INTO ETUDIANT VALUES (1,'16 rue des lilas','','62300','France','Lens','toto@t.fr','','0608','0321','BAVIER','Romain');
INSERT INTO ETUDIANT VALUES (2,'16 rue du chardon','','62000','France','Arras','toto@t.fr','','0608','0321','HANOCQ','Alexandre');
INSERT INTO ETUDIANT VALUES (3,'16 boulevard jean-jaur�s','','62000','France','Arras','toto@t.fr','','0608','0321','MORIVAL','Romain');

INSERT INTO ETABLISSEMENT VALUES (1,'1 rue de l''Universite','','62300','France','Lens','','','','','IUT Lens','IUT');
INSERT INTO ETABLISSEMENT VALUES (2,'22 route du puits','','62000','France','Arras','','','','','Lycee Guy Mollet','Lycee');

INSERT INTO FORMATION VALUES (1,'Gestion des Entreprises et Administrations','DUT GEA',1);
INSERT INTO FORMATION VALUES (2,'Information de Gestion','BTS IG',2);
INSERT INTO FORMATION VALUES (3,'Licence Professionnelle Securite Informatique','LP SIL Securite',1);

INSERT INTO ETUDIANT_FORMATION VALUES (3,1,'2011-09-01','2012-06-30','','Correct');
INSERT INTO ETUDIANT_FORMATION VALUES (1,2,'2007-09-01','2009-06-30','','Passable');
INSERT INTO ETUDIANT_FORMATION VALUES (2,3,'2009-09-01','2011-06-30','','Tres bien');

INSERT INTO ETUDIANT_ENTREPRISE VALUES ('2012-05-01',1,1,'Aucun','2012-06-01','Analyse les besoins du client et met en place le developpement','Analyste Programmeur','CDI');
INSERT INTO ETUDIANT_ENTREPRISE VALUES ('2011-06-01',2,1,'Aucun','2012-08-01','Analyse les besoins du client et met en place le developpement','Ingenieur Etudes et Developpement','INTERIM');
INSERT INTO ETUDIANT_ENTREPRISE VALUES ('2011-06-01',2,2,'Aucun','2013-08-01','Met en place le reseau de l''entreprise','Ingenieur Systemes et Reseaux','STAGE');
INSERT INTO ETUDIANT_ENTREPRISE VALUES ('2011-06-01',1,3,'Aucun','2014-08-01','Developpe des applications J2EE','Developpeur J2EE','STAGE');