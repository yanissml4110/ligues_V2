CREATE TABLE LIGUE (
    id_ligue INT AUTO_INCREMENT,
    nom VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_ligue)
);

CREATE TABLE ROLE (
    id_role INT AUTO_INCREMENT,
    libelle_role VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_role)
);

CREATE TABLE EMPLOYE (
    id_employe INT AUTO_INCREMENT,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    mail VARCHAR(255) NOT NULL,
    mot_de_passe VARCHAR(255) NOT NULL,
    date_arrivee DATE,
    date_depart DATE,
    id_ligue INT,
    id_role INT NOT NULL,

    PRIMARY KEY (id_employe),

    FOREIGN KEY (id_ligue) REFERENCES LIGUE(id_ligue),
    FOREIGN KEY (id_role) REFERENCES ROLE(id_role)
);