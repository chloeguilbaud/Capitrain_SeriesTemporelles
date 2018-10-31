# Utilisation

## Syntaxe du Seed Transducer

Le Seed Transducer est formalisé sous forme d'un objet JSON décrivant : 
- `seed_template`
    - `name` : nom
    - `init_state`
    - `states` : liste des états
    - `arcs` : liste des arcs
        - `from` : état de départ
        - `to` : état d'arrivé
        - `operator`: opérateur 
        - `letter` : lettre de l'alphabet syntaxique

### Exemple 
```json
[
 "seed_template": {
  "name": "bump_on_decreasing_sequence",
  "init_state": "s",
  "states": ["s", "r", "t", "u", "v"],
  "arcs": [
    {
     "from": "s", 
     "to": "s",
     "operator": "leq",
     "letter": "out"
    },
    {
     "from": "s", 
     "to": "r",
     "operator": "gt",
     "letter": "out"
    }, 
    {
     "from": "r", 
     "to": "s",
     "operator": "leq",
     "letter": "out"
    },
    {
     "from": "r", 
     "to": "t",
     "operator": "gt",
     "letter": "out"
    },
    {
     "from": "t", 
     "to": "t",
     "operator": "gt",
     "letter": "out"
    },
    {
     "from": "t", 
     "to": "s",
     "operator": "eq",
     "letter": "out"
    },
    {
     "from": "t", 
     "to": "u",
     "operator": "lt",
     "letter": "maybe_b"
    },
    {
     "from": "u", 
     "to": "s",
     "operator": "leq",
     "letter": "out_r"
    },
    {
     "from": "u", 
     "to": "v",
     "operator": "gt",
     "letter": "maybe_b"
    },
    {
     "from": "v", 
     "to": "s",
     "operator": "leq",
     "letter": "out_r"
    },
    {
     "from": "v", 
     "to": "t",
     "operator": "gt",
     "letter": "found_end"
    }
  ]
 } 
]
```