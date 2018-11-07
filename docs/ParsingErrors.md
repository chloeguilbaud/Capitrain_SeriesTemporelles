# Erreur de parsing

## Transducteur

Au moment du parsing du fichier JSON contenant le transducteur, plusieurs erreur peuvent survenir. En voici une liste exaustive : 
- `MISSING_PROPERTY_IN_SEED_TRANSDUCER`: Expecting seed transducer element but missing in JSON file
- `MISSING_PROPERTY_IN_ARC`: Expecting elements in arc field : from, to, operator, letter
- `INVALID_INIT_STATE`: The given init state for the transducer is not registered in the state list
- `INVALID_FROM_STATE_IN_ARC`: The given state "from" of an arc is not registered in the state list
- `INVALID_TO_STATE_IN_ARC`: The given state "to" of an arc is not registered in the state list
- `INVALID_ARC_SEMANTIC_LETTER`: The given semantic letter in arc is invalid
- `INVALID_ARC_OPERATOR`: The given operator in arc is invalid

Plusieurs de ces erreurs peuvent être remontées en même temps et n'empêche pas le processus de parsing contrairement à celle
qui suivent et qui provoque l'arrêt du processus : 
- `UNKNOWN_ERROR`: Erreur de parsing iconnue
- `UNRECOGNIZED_PROPERTY`: Unexpected JSON element in file 
- `JSON_MAPPING_EXCEPTION`: One of the JSON element has not the right type
- `FILE_NOT_FOUND`: File not found

## Table de décoration