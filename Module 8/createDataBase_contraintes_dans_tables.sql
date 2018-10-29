/*création de la base de données*/
/*
USE master;
GO


CREATE DATABASE Locations;
GO
*/


USE Locations;
GO


/*création des tables*/
CREATE TABLE clients(
    noCli       NUMERIC(6)      CONSTRAINT pk_clients PRIMARY KEY,
    nom         VARCHAR(30)     NOT NULL,
    prenom      VARCHAR(30)     ,
    adresse     VARCHAR(120)    ,
    cpo         CHAR(5)         NOT NULL
                                CONSTRAINT ck_clients_cpo CHECK(convert(numeric(5),cpo) BETWEEN 1000 AND 95999),
    ville       VARCHAR(80)     not null DEFAULT 'Nantes'
);

CREATE TABLE fiches(
    noFic       NUMERIC(6)      CONSTRAINT pk_fiches PRIMARY KEY,
    noCli       NUMERIC(6)      NOT NULL,
    dateCrea    DATETIME        CONSTRAINT df_fiches_dateCrea DEFAULT GETDATE() NOT NULL    ,
    datePaye    DATETIME        ,
    etat        CHAR(2)         NOT NULL DEFAULT 'EC' 
                                CONSTRAINT ck_fiches_etat CHECK(etat IN('EC', 'RE', 'SO')),
    CONSTRAINT ck_fiches_dates CHECK(datePaye is null or datePaye > dateCrea),
    CONSTRAINT ck_fiches_datePaye_etat CHECK((datePaye IS NULL AND etat <> 'SO')
                                        OR (datePaye IS NOT NULL AND etat = 'SO'))
);

CREATE TABLE gammes(
    codeGam     CHAR(5)         CONSTRAINT pk_gammes PRIMARY KEY,
    libelle     VARCHAR(30)     NOT NULL
                                CONSTRAINT un_gammes_libelle UNIQUE
);

CREATE TABLE categories(
    codeCate    CHAR(5)         CONSTRAINT pk_categories PRIMARY KEY,
    libelle     VARCHAR(30)     NOT NULL
                                CONSTRAINT un_categories_libelle UNIQUE
);

CREATE TABLE tarifs(
    codeTarif   CHAR(5)         CONSTRAINT pk_tarifs PRIMARY KEY,
    libelle     VARCHAR(30)     NOT NULL
                                CONSTRAINT un_tarifs_libelle UNIQUE,
    prixJour    NUMERIC(10,2)   NOT NULL
                                CONSTRAINT ck_tarifs_prixJour CHECK(prixJour >= 0)
);

CREATE TABLE grilleTarifs(
    codeGam     CHAR(5),
    codeCate    CHAR(5),
    codeTarif   CHAR(5)         NOT NULL,
    CONSTRAINT pk_grilleTarifs PRIMARY KEY (codeGam, codeCate)
);

CREATE TABLE articles(
    refart      CHAR(3)         CONSTRAINT pk_articles PRIMARY KEY,
    designation VARCHAR(80)     NOT NULL,
    codeGam     CHAR(5)         NOT NULL,
    codeCate    CHAR(5)         NOT NULL
);

CREATE TABLE lignesFic(
    noFic       NUMERIC(6),
    noLig       NUMERIC(3),
    refart      CHAR(3)         NOT NULL,
    depart      DATETIME        DEFAULT GETDATE() NOT NULL,
    retour      DATETIME        NULL,
    CONSTRAINT pk_lignesFic PRIMARY KEY(noFic, noLig),
    CONSTRAINT ck_lignesFic_dates CHECK(retour>depart)
);
GO

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




