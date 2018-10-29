USE Locations;
GO

-- suppression des précédentes valeurs entrées dans la base de données

DELETE FROM lignesFic;
DELETE FROM articles;
DELETE FROM grilleTarifs;
DELETE FROM tarifs;
DELETE FROM gammes;
DELETE FROM categories;
DELETE FROM fiches;
DELETE FROM clients;

/*L'ordre d'insertion des lignes dépend de l'intégrité référentielle*/

-- clients

INSERT INTO clients VALUES(14, 'Boutaud', 'Sabine', 'Rue des platanes', '75002', 'Paris');

-- fiches

INSERT INTO fiches VALUES(1001, 14, GETDATE()-15, DATEADD(day,-13,GETDATE()), 'SO');

-- gammes

INSERT INTO gammes VALUES('PR', 'Matériel Professionnel');
INSERT INTO gammes VALUES('HG', 'Haut de gamme');
INSERT INTO gammes VALUES('EG', 'Entrée de gamme');

-- categories

INSERT INTO categories VALUES('FOA', 'Ski de fond alternatif');
INSERT INTO categories VALUES('FOP', 'Ski de fond patineur');

-- tarifs

INSERT INTO tarifs VALUES('T1', 'Base', 10);
INSERT INTO tarifs VALUES('T4', 'Argent', 30);
INSERT INTO tarifs VALUES('T6', 'Platine', 90);

-- grilleTarifs

INSERT INTO grilleTarifs VALUES('EG', 'FOA', 'T1');
INSERT INTO grilleTarifs VALUES('HG', 'FOP', 'T4');
INSERT INTO grilleTarifs VALUES('PR', 'FOP', 'T6');

-- articles

INSERT INTO articles VALUES('F05', 'Fischer Cruiser', 'EG', 'FOA');
INSERT INTO articles VALUES('F50', 'Fischer SOSSkating VASA', 'HG', 'FOP');
INSERT INTO articles VALUES('F60', 'Fischer RCS CARBOLITE Skating', 'PR', 'FOP');

-- lignesFic

INSERT INTO lignesFic VALUES(1001, 1, 'F05', GETDATE()-15, GETDATE()-13);
INSERT INTO lignesFic VALUES(1001, 2, 'F50', GETDATE()-15, GETDATE()-14);
INSERT INTO lignesFic VALUES(1001, 3, 'F60', GETDATE()-13, DATEADD(HH,6,GETDATE()-13));


