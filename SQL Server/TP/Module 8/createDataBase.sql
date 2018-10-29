/*création de la base de données*/

USE master;
GO


CREATE DATABASE Locations;
GO


USE Locations;
GO


/*création des tables*/
CREATE TABLE clients(
    noCli       NUMERIC(6)      NOT NULL,
    nom         VARCHAR(30)     NOT NULL,
    prenom      VARCHAR(30)     NULL,
    adresse     VARCHAR(120)    NULL,
    cpo         CHAR(5)         NOT NULL,
    ville       VARCHAR(80)     NOT NULL DEFAULT 'Nantes'
);


CREATE TABLE fiches(
    noFic       NUMERIC(6)      not null,
    noCli       NUMERIC(6)      NOT NULL,
    dateCrea    DATETIME        not null DEFAULT GETDATE(),
    datePaye    DATETIME        ,
    etat        CHAR(2)         not null DEFAULT 'EC'
);

CREATE TABLE gammes(
    codeGam     CHAR(5)         not null,
    libelle     VARCHAR(30)     NOT NULL
                                
);

CREATE TABLE categories(
    codeCate    CHAR(5)         not null,
    libelle     VARCHAR(30)     NOT NULL
);

CREATE TABLE tarifs(
    codeTarif   CHAR(5)         not null,
    libelle     VARCHAR(30)     NOT NULL,
    prixJour    NUMERIC(10,2)   NOT NULL
);

CREATE TABLE grilleTarifs(
    codeGam     CHAR(5) not null,
    codeCate    CHAR(5) not null,
    codeTarif   CHAR(5) NOT NULL
);

CREATE TABLE articles(
    refart      CHAR(3)         not null,
    designation VARCHAR(80)     NOT NULL,
    codeGam     CHAR(5)         NOT NULL,
    codeCate    CHAR(5)         NOT NULL
);

CREATE TABLE lignesFic(
    noFic       NUMERIC(6)  not null,
    noLig       NUMERIC(3) not null,
    refart      CHAR(3)         NOT NULL,
    depart      DATETIME        DEFAULT GETDATE() NOT NULL,
    retour      DATETIME        
);
GO

/* Création des clefs primaires */

alter table clients add constraint pk_clients PRIMARY KEY (NoCli);

alter table fiches add constraint pk_fiches primary key (nofic);

alter table gammes add constraint pk_gammes primary key (codegam);

alter table categories add constraint pk_categories primary key (codecate);

alter table tarifs add constraint pk_tarifs  primary key (codetarif);

alter table grilletarifs add CONSTRAINT pk_grilleTarifs PRIMARY KEY (codeGam, codeCate);

alter table articles add constraint pk_articles primary key (refart);

alter table lignesfic add constraint pk_lignesFic PRIMARY KEY(noFic, noLig);





/*mise en place de l'intégrité référentielle*/
ALTER TABLE fiches  ADD 
    CONSTRAINT fk_fiches_clients  FOREIGN KEY (noCli) REFERENCES clients(noCli) ON DELETE CASCADE;

ALTER TABLE grilleTarifs  ADD 
    CONSTRAINT fk_grilleTarifs_gammes  FOREIGN KEY (codeGam) REFERENCES gammes(codeGam);
ALTER TABLE grilleTarifs  ADD
    CONSTRAINT fk_grilleTarifs_categories FOREIGN KEY (codeCate) REFERENCES categories(codeCate);
ALTER TABLE grilleTarifs  ADD
    CONSTRAINT fk_grilleTarifs_tarifs FOREIGN KEY (codeTarif) REFERENCES tarifs(codeTarif);

ALTER TABLE articles  ADD 
    CONSTRAINT fk_articles_grilleTarifs FOREIGN KEY (codeGam, codeCate)
                                    REFERENCES grilleTarifs(codeGam, codeCate);
    
ALTER TABLE lignesFic  ADD 
    CONSTRAINT fk_lignesFic_fiches FOREIGN KEY (noFic) REFERENCES fiches(noFic) ON DELETE CASCADE;
ALTER TABLE lignesFic  ADD
    CONSTRAINT fk_lignesFic_articles FOREIGN KEY (refart) REFERENCES articles(refart);

/* création des autres contraintes */

alter table clients add CONSTRAINT ck_clients_cpo CHECK(convert(numeric(5),cpo) BETWEEN 1000 AND 95999);

alter table fiches add CONSTRAINT ck_fiches_etat CHECK(etat IN('EC', 'RE', 'SO'));

alter table fiches add CONSTRAINT ck_fiches_dates CHECK(datePaye is null or datePaye > dateCrea);

alter table fiches add CONSTRAINT ck_fiches_datePaye_etat 
    CHECK((datePaye IS NULL AND etat <> 'SO') OR (datePaye IS NOT NULL AND etat = 'SO'));
    
alter table gammes add CONSTRAINT un_gammes_libelle UNIQUE (libelle);    

alter table categories add CONSTRAINT un_categories_libelle UNIQUE (libelle);

alter table tarifs add CONSTRAINT un_tarifs_libelle UNIQUE (libelle);

alter table tarifs add CONSTRAINT ck_tarifs_prixJour CHECK(prixJour >= 0);

alter table lignesfic  add CONSTRAINT ck_lignesFic_dates CHECK(retour>depart);