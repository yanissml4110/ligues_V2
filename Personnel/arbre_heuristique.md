Gestion du personnel des ligues (password :  toor)

├── [c] Gérer le compte root
│   ├── [l] Afficher l'employé
│   ├── [n] Changer le nom (Saisie : Nouveau nom)
│   ├── [p] Changer le prénom (Saisie : Nouveau prénom)
│   ├── [e] Changer le mail (Saisie : Nouveau mail)
│   ├── [x] Changer le password (Saisie : Nouveau password)
│   ├── [y] Changer la date d'arrivée (Saisie : aaaa-mm-jj, vide = aucune)
│   │       ├── Format invalide → message d'erreur, la date est redemandée
│   │       └── Date dans le futur ou après la date de départ → message d'erreur, date inchangée
│   ├── [z] Changer la date de départ (Saisie : aaaa-mm-jj, vide = aucune)
│   │       ├── Format invalide → message d'erreur, la date est redemandée
│   │       └── Date avant la date d'arrivée → message d'erreur, date inchangée
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
│   │       │   ├── [a] Ajouter un employé
│   │       │   │       (Saisie : nom, prénom, mail, password,
│   │       │   │        date d'arrivée aaaa-mm-jj, date de départ aaaa-mm-jj)
│   │       │   │       ├── Format de date invalide → message d'erreur
│   │       │   │       └── Départ avant arrivée, ou arrivée dans le futur
│   │       │   │           → message d'erreur, l'employé n'est pas créé
│   │       │   ├── [s] Sélectionner un employé (Liste numérotée : 1, 2, ...)
│   │       │   │   └── Employé : [Nom]
│   │       │   │       ├── [l] Afficher l'employé
│   │       │   │       ├── [c] Gérer le compte [Nom]
│   │       │   │       │   ├── [l] Afficher l'employé
│   │       │   │       │   ├── [n] Changer le nom
│   │       │   │       │   ├── [p] Changer le prénom
│   │       │   │       │   ├── [e] Changer le mail
│   │       │   │       │   ├── [x] Changer le password
│   │       │   │       │   ├── [y] Changer la date d'arrivée
│   │       │   │       │   ├── [z] Changer la date de départ
│   │       │   │       │   └── [q] Back (Retour à Employé)
│   │       │   │       ├── [d] Supprimer l'employé
│   │       │   │       └── [q] Back (Retour à Gérer les employés)
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