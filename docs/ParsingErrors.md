# Erreurs de parsing

## Transducteur

Au moment du parsing du fichier JSON contenant le transducteur, plusieurs erreurs peuvent survenir. En voici une liste exaustive : 
- `MISSING_PROPERTY_IN_SEED_TRANSDUCER`: 
    - Expecting seed transducer element but missing in JSON file
    - Seed Transducer manquant dans le fichier JSON
- `INVALID_INIT_STATE`: 
    - The given init state for the transducer is not registered in the state list
    - L'état initial fourni n'est pas renseigné dans la liste des états possibles
- `MISSING_PROPERTY_IN_ARC`: 
    - Expecting elements in arc field : from, to, operator, letter
    - L'un des élément attendu dans le champ arc est manquant (from, to, operator, letter) 
- `INVALID_FROM_STATE_IN_ARC`: 
    - The given state "from" of an arc is not registered in the state list
    - L'Etat "from" de l'un des arcs n'est pas présent dans la liste des états possibles
- `INVALID_TO_STATE_IN_ARC`: 
    - The given state "to" of an arc is not registered in the state list
    - L'Etat "to" de l'un des arcs n'est pas présent dans la liste des états possibles
- `INVALID_ARC_SEMANTIC_LETTER`: 
    - The given semantic letter in arc is invalid
    - La lettre sémantique fournie pour l'un des arcs est invalide
- `INVALID_ARC_OPERATOR`: 
    - The given operator in arc is invalid
    - L'opérateur fourni à l'un des arcs est invalid

Plusieurs de ces erreurs peuvent être remontées en même temps et n'empêchent pas le processus de parsing contrairement à celles
qui suivent et qui provoquent l'arrêt du processus : 
- `UNKNOWN_ERROR`:
    - Unknown parsing error 
    - Erreur de parsing iconnue
- `UNRECOGNIZED_PROPERTY`: 
    - Unexpected JSON element in file
    - Element JSON inattendu 
- `JSON_MAPPING_EXCEPTION`: 
    - One of the JSON element has not the right type
    - L'un des éléments JSON ne présente pas le bon type attendu
- `FILE_NOT_FOUND`: 
    - File not found
    - Fichier non trouvé

## Table de décoration

Au moment du parsing du fichier JSON contenant une table de décoration, plusieurs erreurs peuvent survenir. En voici une liste exaustive :
- Erreur survenant au moment de l'initialisation (parse de `name`, `registers`, `returns`) 
    - `INITIALISATION_VALUE_FUNCTION_MISSING_NAME` : 
        - Function missing name in {register or return} n°{index}
        - Nom de fonction non fournit dans un `registre` ou `return`
    - REGISTERS
        - `MISSING_REGISTER_NAME` :
            - Expected register name in register n°{index} 
            - Aucun nom n'est renseigné pour l'un des registres
        - `MISSING_REGISTER_VALUE`:
            - Expected register value in register n°{index} 
            - Aucune valeur n'est renseignée pour l'un des registres     
        - `INITIALISATION_REGISTER_VALUE_VARIABLE_MISSING_NAME` :
            - No name given to variable in register n°{index} 
            - Aucun nom n'est renseigné pour l'une des valeurs d'initilisation donnée à un registre
        - `INITIALISATION_REGISTER_VALUE_VARIABLE_UNEXPECTED_INDEX` : 
            - Declaration in initialisation section should not have index field for variable {variableName}
            - Un registre ne doit pas présenter d'index
        - `FUNCTION_UNEXPECTED_PARAMETER_IN_INITIALISATION` :
            - Functions in register or returns should not have parameters, in function {functionName}
            - La définition d'une fonction dans un registre ne doit pas comprendre de paramètre 
        - `BOTH_REGISTER_FUNCTION_AND_VARIABLE_IN_VALUE`:
            - Both function and variable declaration in register value n°{index} 
            - L'une des valeurs donnée à un registre comprend une référence a une fonction et a une variable
    - RETURNS
        - `INITIALISATION_RETURN_VARIABLE_MISSING_NAME` :
            - No name given in return n°{index} 
            - Nom manquant pour l'un des return
        - `INITIALISATION_RETURN_VARIABLE_MISSING_INDEX` :
            - No index given in return n°{index} for variable {variableName} 
            - Index manquant pour l'un des return 
        - `INITIALISATION_RETURN_VARIABLE_MISSING_VALUE` :
            - No value given in return n°{index} 
            - Valeur d'initialisation manquante pour l'un des return
        - `BOTH_RETURN_FUNCTION_AND_VARIABLE_IN_VALUE` :
            - Both function and variable declaration in return statement value n°{index} 
            - Fonction et variable données en valeur d'initialisation de l'un des return
    - TABLE
        - `INSTRUCTION_MISSING_SEMANTIC_LETTER` : 
            - Semantic letter missing in table element n°{index}
            - Aucune lettre sémantique fournit pour l'une des instructions de la table
        - `INSTRUCTION_INVALID_SEMANTIC_LETTER` :
            -  Invalid given semantic letter in table line n°{lineIndex}
                - Expected: {semanticLettersPossibilities}
                - Actual: {givenSemanticLetter}
            - Une des lettre sémantique fournit est invalide et ne fait pas partie de la liste des possibles 
        - `BOTH_FUNCTION_AND_VARIABLE_IN_VALUE` :
            - Both function and variable declaration in value in {valueName} for semantic letter "{semanticLetter}"
            - Fonction et variable données en valeur d'affectation
        - FONCTIONS
            - `FUNCTION_MISSING_NAME` : 
                - Function element in JSON, missing name for semantic letter "{semanticLetter}" in {tableColumn}
                - Aucune référence par nom à une des fonctions connues
            - `FUNCTION_INVALID_PARAMETER_TYPE` : 
                - Invalid parameter type given to function {functionName} in semantic letter "{semanticLetter}" in {tableColumn}
                - L'un des paramètres founit en entré à une fonction est de type invalid
            - `FUNCTION_PARAMETER_MISSING_VALUE_IN_OPERATION` : 
                - A parameter given to function {functionName} is missing a value on the side of an operation (-, +, / or x) for semantic letter "{semanticLetter}" (given parameter: {givenParameter})
                - Un paramètre indexé mal formaté a été fournit en entré d'une fonction
        - VARIABLES
            - `VARIABLE_MISSING_NAME` : 
                - Missing "{variableName}" in "variable" in JSON in {tableColumn} n°{lineIndex} for semantic letter "{semanticLetter}"
                - Aucun nom fournit pour une variable
            - `VARIABLE_VALUE_MISSING_NAME` : 
                - Missing "value" field in JSON in {tableColumn} at semantic letter "{semanticLetter}"
                - Aucune nom valeur fournit pour l'une des letttres sémantiques
            - `VARIABLE_VALUE_MISSING` : 
                - "value" field missing in JSON for semantic letter in {tableColumn} for semantic letter "{semanticLetter}"
                - Aucune valeur fournit pour l'une des letttres sémantiques
            - `VARIABLE_INVALID_INDEX` : 
                - Invalid index given for variable {variableName} for semantic letter "{semanticLetter}" in {tableColumn}
                - Index de varaible fournit invalid
    
Ces erreurs peuvent être remontées en même temps et n'empêche pas le processus de parsing contrairement à celle
qui suivent et qui provoque l'arrêt du processus : 
- `UNKNOWN_ERROR`:
    - Unknown parsing error 
    - Erreur de parsing iconnue
- `UNRECOGNIZED_PROPERTY`: 
    - Unexpected JSON element in file
    - Element JSON inattendu 
- `JSON_MAPPING_EXCEPTION`: 
    - One of the JSON element has not the right type
    - L'un des éléments JSON ne présente pas le bon type attendu
- `FILE_NOT_FOUND`: 
    - File not found
    - Fichier non trouvé

[Retour](../README.md)