# Gestion de projet

L'équipe était composée de deux étudiants. Nous avons mis en place divers outils tel que : Git, Trello et Drive.

En terme de gestion de projet Git, la branche `dev` à servie au développement de l'application. 
Lorsque cela était jugé nécessaire, une branche `evol` pouvait être utilisée et ensuite détruite après merge sur `dev`. 
Une fois stabilisée, une version a été merge sur la branche	 `master`.  


## Suivi et cohérence

Sur Trello, un Kamban a été mis en place et mit à jour au cours des travaux. Nous avons convenu de réunions quasi hebdomadaires avec l'encadrant au cours desquelles nous avons rendu compte de l'avancement et posé les questions nécessaires à l'aboutissement du projet, aussi bien du point de vue de la compréhension du sujet que de la définition du périmètre d'intervention. 

Des rapports hebdomadaires et de réunions ont été établit. La documentation a été rédigée au fur et à mesure de l'avancement. 


## Conception

L'architecture générale ainsi que les protocoles de communications ont été définit et conçu d'un commun accord. L'un de nous s’est occupé de la génération de code et de la définition du model alors que l'autre ces attelé à la conception, l'implémentation des parser de transducer et de table de décoration et la gestion des erreurs. La définition des formats de données JSON ont été définit selon la compréhension mutuelle du sujet et de la thèse. 

Chacun des parties ces chargées de l'implémentation des tests unitaires, de performances et de la documentation associée aux modules dont il était responsable.


## Méthodes de travail, volonté commune et cohérence

Protocoles de rédaction de documentations et d'écriture de code sources ont été établit afin d'assurer la cohérence globale du projet. 

Nous avons souhaité assurer une cohérence du point du vue de l'utilisateur. Par exemple, l'un de nous ces documenté d'avantage sur les concepts clés des calculs et du model propre à la génération de code. L'autre ces attelé à la définition des JSON et ceux sans avoir connaissance de la conception du modèle afin de ne pas être influencé par celle-ci. Nous avons ensuite échangé pour améliorer les deux parties. 

Un fonctionnement similaire a été mis en place lors de la rédaction des tests. Celui ayant travaillé sur le parsing des données à rédiger les fichiers JSON d'entrées alors que l'autre a fourni une version objet suivant le modèle attendu. La confrontation a permis de constater un certain nombre d'incohérences et d'erreur et a abouti à une amélioration de la conception général, de la génération de code ainsi qu'a une correction d'erreurs. 

Nous avons eu une réelle volonté de créer un projet très modulaire avec divers composants totalement indépendants les uns des autres, facilitant ainsi la maintenance mais aussi les éventuels remplacements ou évolutions souhaités. Le projet a été conçu dans la perspective d'évolutions futures. 

Ces choix de gestion ont permis un suivit général, régulier et précis. Cela à permis de le mener à bien et de répondre aux échéances. Les rapports hebdomadaires ainsi qu'un accès au Trello de l'équipe ont offert à l'encadrant une vision de l'avancement et de l'orientation du projet. La philosophie et volonté commune ont assurées une cohérence générale du projet.

[Retour](../README.md)
