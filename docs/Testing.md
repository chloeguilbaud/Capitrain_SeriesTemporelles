# Testing

Sur l'ensemble de l'application, des tests ont été réalisés avec `JUnit`.
Les tests sont situés dans `src/test`.

Concernant les tests sur le code **généré**, une [partie de cette documentation](GeneratedCode.md) y est dédiée.

## Taux de couverture

Les taux de couvertures décrit dans la table suivante illustrent la qualité
du code. En moyenne, les tests couvrent 83% du code et en garantissent
le bon fonctionnement. Au total 63 tests ont été établit.

|           Modules         |     Classes    |   Méthodes  | Lignes      |
| :---                      | :---:          | :---:       | :---:       |
| app                       |       100%     |    100%     |    84%      |
| generator                 |       66%      |    47%      |    53%      |
| generated                 |       100%     |    90%      |    94%      |
| manager                   |       100%     |    84%      |    91%      |
| parser                    |       100%     |    86%      |    88%      |
| parser.decoration.table   |       100%     |    82%      |    84%      |
| parser.seed.transducer    |       100%     |    90%      |    94%      | 
| *MOYENNE*                 |       *95%*    |    *83%*    |    *83%*    | 


## Package Generator

Situés dans `src/test/java/generator`.

Les tests effectués sont les suivants :
- Génération de code JAVA pour le Transducteur `PEAK` et la table `FOOTPRINT`
- Génération de code JAVA pour le Transducteur `PEAK` et la table `FEATURE`

## Package Language.Java

Situés dans `src/test/java/language/java`.

Les tests effectués sont les suivants :
- Levée de l'erreur `JAVA_ELEMENT_UNKNOW_ELEMENT_SUBCLASS`
- Levée de l'erreur `JAVA_GUARD_PARAMETER_NOT_AN_AFFECTATION`
- Levée de l'erreur `JAVA_UPDATE_PARAMETER_NOT_AN_AFFECTATION`

## Package Parser

Situés dans `src/test/java/parser`

Pour la table de décoration, les tests effectués sont les suivants :
- Levée de l'erreur `UNRECOGNIZED_PROPERTY`
- Levée de l'erreur `JSON_MAPPING_EXCEPTION`
- Levée de l'erreur `FILE_NOT_FOUND`
- Levée de l'erreur `INITIALISATION_REGISTER_VALUE_VARIABLE_UNEXPECTED_INDEX`
- Levée de l'erreur `INITIALISATION_REGISTER_VALUE_VARIABLE_MISSING_NAME`
- Levée de l'erreur `INITIALISATION_RETURN_VARIABLE_MISSING_NAME`
- Levée de l'erreur `INITIALISATION_RETURN_VARIABLE_MISSING_VALUE`
- Levée de l'erreur `INITIALISATION_RETURN_VARIABLE_MISSING_INDEX`
- Levée de l'erreur `MISSING_REGISTER_NAME`
- Levée de l'erreur `MISSING_REGISTER_VALUE`
- Levée de l'erreur `BOTH_REGISTER_FUNCTION_AND_VARIABLE_IN_VALUE`
- Levée de l'erreur `BOTH_RETURN_FUNCTION_AND_VARIABLE_IN_VALUE`
- Levée de l'erreur `INITIALISATION_VALUE_FUNCTION_MISSING_NAME`
- Levée de l'erreur `FUNCTION_MISSING_NAME`
- Levée de l'erreur `FUNCTION_INVALID_PARAMETER_TYPE`
- Levée de l'erreur `BOTH_RETURN_FUNCTION_AND_VARIABLE_IN_VALUE`
- Levée de l'erreur `BOTH_FUNCTION_AND_VARIABLE_IN_VALUE`
- Levée de l'erreur `INSTRUCTION_MISSING_SEMANTIC_LETTER`
- Levée de l'erreur `INSTRUCTION_INVALID_SEMANTIC_LETTER`
- Levée de l'erreur `VARIABLE_VALUE_MISSING`
- Levée de l'erreur `VARIABLE_VALUE_MISSING_NAME`
- Levée de l'erreur `VARIABLE_MISSING_NAME`
- Levée de l'erreur `VARIABLE_INVALID_INDEX`
- Levée de l'erreur `FUNCTION_INVALID_PARAMETER_TYPE`
- Levée de l'erreur `FUNCTION_INVALID_PARAMETER_TYPE`
- Levée de l'erreur `FUNCTION_PARAMETER_MISSING_VALUE_IN_OPERATION`
- Variable avec un index négatif
- Modèle
- Parsing général

Pour le transducteur, les tests effectués sont les suivants :
- Levée de l'erreur `UNRECOGNIZED_PROPERTY`
- Levée de l'erreur `MISSING_PROPERTY_BEFORE`
- Levée de l'erreur `MISSING_PROPERTY_AFTER`
- Levée de l'erreur `INVALID_INIT_STATE`
- Levée de l'erreur `INVALID_FROM_STATE_IN_ARC`
- Levée de l'erreur `INVALID_TO_STATE_IN_ARC`
- Levée de l'erreur `INVALID_ARC_SEMANTIC_LETTER`
- Levée de l'erreur `INVALID_ARC_OPERATOR`
- Levée de l'erreur `INVALID_TO_STATE_IN_ARC`
- Levée de l'erreur `MISSING_PROPERTY_IN_SEED_TRANSDUCER`
- Levée de l'erreur `JSON_MAPPING_EXCEPTION`
- Levée de l'erreur `FILE_NOT_FOUND`
- Parsing général
- Etats manquants

[Retour](../README.md)
