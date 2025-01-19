Comme convenu, tu trouveras ci-dessous l'énoncé du Test + mes conseils, 

Énoncé



Description du besoin


Une société cherche une solution pour numéroter ses inscrits d'une façon unique: 


Cette numérotation sera construite par concaténation des 4 critères suivants :

- le nom de l'inscrit

- le prénom de l'inscrit

- sa date de naissance

- un compteur incrémenté de 1 à chaque nouvel inscrit, avec une valeur initiale configurée.


Ces critères peuvent avoir un ordre différent.

Ces critères peuvent avoir des configurations différentes :

- le nom de l'inscrit : une longueur définie avec un potentiel préfixe / suffixe.

- le prénom de l'inscrit : une longueur définie avec un potentiel préfixe / suffixe.

- la date de naissance : format de date (YYYY) avec un potentiel préfixe / suffixe.

- le compteur formaté





Test à réaliser


Développer une application en JAVA qui permet 

- de configurer les critères de numérotation par WebService

- de générer un numéro selon les données d'un inscrit (nom / prénom / date de naissance / compteur) par WebService




Cas de test 


Exemple 1:

pour les critères de numérotation

- Les 3 premières lettres du prénom avec un suffixe - et ordre 1

- Le 4 premières lettres du nom avec un suffixe _ et ordre 2

- la date de naissance formatée en YYYY et ordre 3

- le compteur avec la valeur initiale 10, le préfixe C et formaté sur 5 chiffres et ordre 4


le résultat de la génération avec Marc PASSAU né le 24/04/1974 sera MAR-PASS_1974C00011


Exemple 2:

pour le critères de numérotation

- Les 3 premières lettres du prénom avec un suffixe - et ordre 2

- Le 4 premières lettres du nom avec un suffixe _ et ordre 1

- la date de naissance formatée en YYYY,le préfixe N et ordre 4

- le compteur avec la valeur initiale 7, le préfixe C et formaté sur 5 chiffres et ordre 3


le résultat de la génération avec Isaac ANTOINE né le 24/04/1992 sera ANTO_ISA-C00008N1992




Bonus 


- Ajouter un front (angular ou react ou autre) pour tester les deux apis

- faire une solution micro-service 


---- 

Mes conseils,

C'est une application à créer. 
L'idée est d'analyser ta réflexion autour de l'architecture, des bonnes pratiques du développement SOLID, 
les tests unitaires et la documentation. 

Il faut que tu m'envoies un lien GitHub via un mail, je ferais suivre au client. 
(Si jamais tu veux faire une vidéo avec une démo, ça serait Top) 

Le test est attendu pour dimanche 19/01 chez le client. 

Je me tiens à ta disposition au besoin, 
Excellente journée, 
Bien à toi, 
