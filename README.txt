AVANCEMENT :

Je n'ai pas réussi à implémenter Broadcast et à faire la programmation évènementielle par manque de temps et de compréhensions.

Néanmoins, j'ai des pistes de réflexion pour Broadcast.
Il faut vérifier que plusieurs threads peuvent se connecter à une même MessageQueue.
Pour cela, je créer deux classes task : client(envoyeur et receveur) et serveur.
- Pour l'envoyeur, on créer une nouvelle connexion et on synchronise la queue pour pas qu'il y ait plusieurs ajouts en même temps, on envoie le message et on supprime la connexion de la queue.
- Pour le receveur, on synchronise la liste des MessageQueues, on récupère la MessageQueue. Ensuite, on doit synchroniser quand on appelle la méthode receive() pour pas que plusieurs trends reçoit en même temps.
- Côté serveur, on peut différencier les différents types de clients en leurs attribuant une règle : les pairs sont ceux qui envoient et inversemment. 


MON TRAVAIL AU COURS DES SEMAINES :

- Première semaine : package v1
- Deuxième semaine : package v2
- Votre design et spécification : package v3

Vous trouverez les designs dans le dossier "doc".


LANCEMENT DE L'APPLICATION :

Pour tester l'application, il y a une classe qui se nomme "Test" qui va lancer deux tâches. Cette classe est présente sous chaque package.
