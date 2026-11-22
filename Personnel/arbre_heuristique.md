Gestion du personnel des ligues (Authentification : password)
├── [c] Gérer le compte (ex. yvan)
│   ├── [l] Afficher l'employé
│   ├── [n] Changer le nom (Saisie : Nouveau nom)
│   ├── [p] Changer le prénom (Saisie : Nouveau prénom)
│   ├── [e] Changer le mail (Saisie : Nouveau mail)
│   ├── [x] Changer le password (Saisie : Nouveau password)
│   ├── [y] Changer la date d'arrivée (Saisie : aaaa-mm-jj)   ← si présente dans ton code
│   ├── [z] Changer la date de départ (Saisie : aaaa-mm-jj)   ← si présente dans ton code
│   └── [q] Back (Retour au menu principal)
│
├── [l] Gérer les ligues
│   ├── [l] Afficher les ligues
│   ├── [a] Ajouter une ligue (Saisie : nom)
│   ├── [e] Sélectionner une ligue (Liste numérotée : 1, 2, ...)
│   │   └── Éditer [Nom de la ligue]
│   │       ├── [l] Afficher la ligue (affiche le nom et « administrée par ... »)
│   │       ├── [e] Gérer les employés de [Nom de la ligue]
│   │       │   ├── [l] Afficher les employés
│   │       │   ├── [a] Ajouter un employé (Saisie : nom, prénom, mail, password)
│   │       │   ├── [e] Modifier un employé (Liste numérotée → menu « Gérer le compte »)
│   │       │   ├── [s] Supprimer un employé (Liste numérotée → suppression)
│   │       │   └── [q] Back (Retour à Éditer la ligue)
│   │       ├── [c] Changer d'administrateur (Liste numérotée des employés de la ligue)
│   │       │   └── Choix d'un employé → il devient administrateur → retour à Éditer la ligue
│   │       ├── [r] Renommer (Saisie : Nouveau nom)
│   │       ├── [d] Supprimer
│   │       └── [q] Back (Retour à Gérer les ligues)
│   └── [q] Back (Retour au menu principal)
│
└── [q] Quitter
    ├── [q] Quitter et enregistrer
    ├── [a] Quitter sans enregistrer
    └── [r] Back (Retour au menu principal)