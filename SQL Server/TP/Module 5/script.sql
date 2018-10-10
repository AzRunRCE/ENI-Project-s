
drop table CLIENTS
drop table FICHES 
drop table LIGNESFIC
drop table ARTICLES
drop table GRILLETARIFS
drop table TARIFS
drop table GAMMES
drop table CATEGORIES

create table CLIENTS(
	 NOCLI int NOT NULL,
	 NOM varchar(30) NOT NULL,
	 PRENOM varchar(30) NOT NULL,
	 ADRESSE varchar(120),
	 CPO char(5) NOT NULL,
	 VILLE varchar(80) NOT NULL DEFAULT 'Nantes'
); 


create table FICHES(
	 NOFIC decimal(6) NOT NULL,
	 NOCLI decimal(6) NOT NULL,
	 DATECREA datetime NOT NULL,
	 DATEPAYE datetime,
	 ETAT char(2) NOT NULL DEFAULT 'EC'
); 

create table LIGNESFIC(
	 NOFIC decimal(6) NOT NULL,
	 NOLIG decimal(6) NOT NULL,
	 REFART char(8) NOT NULL,
	 DEPART datetime NOT NULL DEFAULT GETDATE(),
	 RETOUR datetime
); 

create table ARTICLES(
	 REFART char(8) NOT NULL,
	 DESIGNATION varchar(80) NOT NULL,
	 CODEGAM char(5) NOT NULL,
	 CODECATE char(5) NOT NULL
);

create table GRILLETARIFS(
	 CODEGAM char(5) NOT NULL,
	 CODECATE char(5) NOT NULL,
	 CODETARIF char(5) NOT NULL
);

create table TARIFS(
	 CODETARIF char(5) NOT NULL,
	 LIBELLE varchar(30) NOT NULL,
	 PRIXJOUR decimal(5,2) NOT NULL
);

create table GAMMES(
	 CODEGAM char(5) NOT NULL,
	 LIBELLE varchar(30) NOT NULL
);

create table CATEGORIES(
	 CODECATE char(5) NOT NULL,
	 LIBELLE varchar(30) NOT NULL
);

-- Contraintes de type clé primaire

alter table CLIENTS add constraint CLIENTS_PK primary key (NOCLI)
alter table FICHES add constraint FICHES_PK primary key (NOFIC)
alter table LIGNESFIC add constraint LIGNESFIC_PK primary key (NOFIC,NOLIG)
alter table GRILLETARIFS add constraint GRILLETARIFS_PK primary key (CODEGAM,CODECATE)
alter table TARIFS add constraint TARIFS_PK primary key (CODETARIF)
alter table GAMMES add constraint GAMMES_PK primary key (CODEGAM)
alter table CATEGORIES add constraint CATEGORIES_PK primary key (CODECATE)



-- Contraintes d’unicité

alter table CATEGORIES add constraint CATEGORIES_UK UNIQUE (LIBELLE)
alter table GAMMES add constraint GAMMES_UK UNIQUE (LIBELLE)
alter table TARIFS add constraint TARIFS_UK UNIQUE (LIBELLE)


-- Contraintes de validité
alter table CLIENTS add constraint CPO_CHK check(CPO > '01000' and  CPO < '95999');
alter table TARIFS add constraint POSITIVE_PRICE_CHK check(PRIXJOUR > 0);
alter table FICHES add constraint FICHES_CHK check(etat == 'EC' or etat == 'RE' OR etat == 'SO');
alter table FICHES add constraint FICHES_DATEPAYE_CHK check(DATEPAYE is null or DATEPAYE > DATECREA);
alter table FICHELIGNES add constraint FICHELIGNES_RETOUR_CHK check(RETOUR is null or RETOUR  > DEPART);
alter table FICHESadd constraint FICHES_DATEPAYE_ETAT_CHK check((ETAT == 'SO' and DATEPAYE is not null) OR (ETAT != 'SO' and DATEPAYE is null));






