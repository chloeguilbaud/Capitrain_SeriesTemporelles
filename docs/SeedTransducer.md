# Syntaxe du Seed Transducer

## Format
Le Seed Transducer est formalisé sous forme d'un objet JSON décrivant : 
    - `name` : nom
    - `init_state` : état initial
    - `states` : liste des états
    - `arcs` : liste des arcs
        - `from` : état de départ
        - `to` : état d'arrivé
        - `arcOperator`: opérateur 
        - `letter` : lettre de l'alphabet syntaxique

### Opérateurs
Le champs `arcOperator` peut prendre les valeurs suivantes :
- `eq`
- `lt`
- `gt`
- `leq`
- `geq`

### Lettres sémantiques
Le champs `letter` peut prendre les valeurs suivantes :
- `found`
- `found(end)`
- `maybe(before)`
- `maybe(after)`
- `out(reset)`
- `in`
- `out(after)`
- `out`


## Exemple 
```json
{
    "name": "bump_on_decreasing_sequence",
    "init_state": "s",
    "states": ["s", "r", "t", "u", "v"],
    "arcs": [
        {
            "from": "s", 
            "to": "s",
            "arcOperator": "leq",
            "letter": "out"
        },
        {
            "from": "s", 
            "to": "r",
            "arcOperator": "gt",
            "letter": "out"
        }, 
        {
            "from": "r", 
            "to": "s",
            "arcOperator": "leq",
            "letter": "out"
        },
        {
            "from": "r", 
            "to": "t",
            "arcOperator": "gt",
            "letter": "out"
        },
        {
            "from": "t", 
            "to": "t",
            "arcOperator": "gt",
            "letter": "out"
        },
        {
            "from": "t", 
            "to": "s",
            "arcOperator": "eq",
            "letter": "out"
        },
        {
            "from": "t", 
            "to": "u",
            "arcOperator": "lt",
            "letter": "maybe_b"
        },
        {
            "from": "u", 
            "to": "s",
            "arcOperator": "leq",
            "letter": "out_r"
        },
        {
            "from": "u", 
            "to": "v",
            "arcOperator": "gt",
            "letter": "maybe_b"
        },
        {
            "from": "v", 
            "to": "s",
            "arcOperator": "leq",
            "letter": "out_r"
        },
        {
            "from": "v", 
            "to": "t",
            "arcOperator": "gt",
            "letter": "found_end"
        }
    ]
}
```