# Conclusion

La conception du projet a assuré une architecture complètement modulaire et évoluable par un découpage en quatre composants
majeurs : 
- `generator`
- `manager`
- `model`
- `parser`

Chaque module est complètement indépendant des autres et définit un protocol de communication stricte matérialisé
par les points d'entrées :
- `generator` : `GeneratorManager.generateCode()`
- `manager` : `Manager.process()`
- `model` : `DecorationTable` et `SeedTransducer` 
- `parser` : `DecorationTableParser.parse()` et `SeedTransducer.parse()`

Les modules `manager`, `generator` et `parser` renvoient tous des objets contenant le résultat du process
mais aussi les éventuelles erreurs survenus. Ainsi, le module utilisateur du service disposent de tout les éléments
nécessaire à son processus et est en mesure de les traîter à sa guisse.

Le module `manager` constitue le point d'entré principal et liant du système. A partir de ce module il est possible 
de brancher tout type d'interface. Ici le choix a été fait de crée une application (`App`). Il est aussi 
tout a fait possible de crée une interface graphique. Celle-ci pourrait se connecter directement au manager. Une fois le 
processus de génération terminée, elle dispose des éléments résultats mais aussi des éventuelles erreurs 
qu'elle peux alors traiter et afficher au niveau de l'interface utilisateur.

L'ajout d'un générateur de language est simple et quasiment automatique et transparent pour le développeur 
d'un nouveau module de génération de code (`GeneratorAvailableLanguages`). De la même façon, il dispose des éléments 
résultat du parsing et en maîtrise les enjeux.

La gestion des erreurs dans chacun des modules assure un fonctionnement mâitrisé sur l'ensemble du système 
et est garant de la qualité du contenu produit. 

La couverture du code à 83%, via 63 tests unitaires automatisés, garanti la qualité du code sources ainsi que 
celle du code généré.

Les tests de performances mit en place sur le code généré, sur des échantillions de séries temporelles 
comprenant 10 à 5 millions d'éléments, sont très satisfaisant. Une revu de code a démontrée que le résultat 
est optimum. L'algoritme présentant une compléxité linéraire.

Ce projet a consitué une initiation à la cherche et a été très enrichissante. Organisation et Communication
ont été indispensable pour le mener à bien et en assurer le bon fonctionnement, et ceux dans le respect des 
échéances imposées malgré la charge importante. Combiné à la compréhension, à l'étude de thèses 
et à la collaboration avec un enseignant chercheur, ce projet a été riche tant du point de vue technique
que de celui de la gestion de projet.   