// Assignment 2 19T1 COMP1511: Pokedex
// pokedex.c
//
// This program was written by MENGHUI WEI (z5082327)
// on 24-04-2019
//
// Version 1.0.0: Assignment released.
// Version 1.0.1: Minor clarifications about `struct pokenode`.
// Version 1.1.0: Moved destroy_pokedex function to correct location.
// Version 1.1.1: Renamed "pokemon_id" to "id" in change_current_pokemon.
// NEW ADDED COMMENTS ARE IN UPPERCASE
// DELETE PLACEHOLDER COMMENTS

#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

#include "pokedex.h"

// Note you are not permitted to use arrays in struct pokedex,
// you must use a linked list.
//
// You are also not permitted to use arrays in any of the functions
// below.
//
// The only exception is that char arrays are permitted for
// search_pokemon and functions it may call, as well as the string
// returned by pokemon_name (from pokemon.h).
//
// You will need to add fields to struct pokedex.
// You may change or delete the head field.

struct pokedex {
    struct pokenode *head;
    struct pokenode *current_selected;
};

// You don't have to use the provided struct pokenode, you are free to
// make your own struct instead.
// If you use the provided struct pokenode, you will need to add fields
// to it to store other information.

struct pokenode {
    struct pokenode *next;
    Pokemon         pokemon;
    int             found;
    struct pokenode *evolve;
    
};

// You need to implement the following 20 functions.
// In other words, replace the lines calling fprintf & exit with your code.
// You can find descriptions of what each function should do in pokedex.h

Pokedex new_pokedex(void) {
    Pokedex new_pokedex = malloc(sizeof (struct pokedex));
    assert(new_pokedex != NULL);
    new_pokedex->head = NULL;
    new_pokedex->current_selected = NULL;
    return new_pokedex;
    
}

////////////////////////////////////////////////////////////////////////
//                         Stage 1 Functions                          //
////////////////////////////////////////////////////////////////////////

// ADDING POKEMONS INTO POKEDEX AND RETURN ERROR MESSAGE IF ADDING SAME POKEMON MORE THAN ONCE
void add_pokemon(Pokedex pokedex, Pokemon pokemon) {
    // ADDING THE FIRST POKEMON INTO THE POKEDEX
    if (pokedex->head == NULL) {
        pokedex->head = malloc(sizeof(struct pokenode));
        pokedex->head->pokemon = pokemon;
        pokedex->head->found = 0;
        pokedex->head->evolve = NULL;
        pokedex->head->next = NULL;
        // MEANWHILE SETTING THE CURRENT POKEMON TO THE FIRST ADDED ONE
        pokedex->current_selected = pokedex->head;
    } else {
        // ADDING MORE POKEMON INTO POKEDEX
        // USING FOR LOOP TO CHECK WHETHER THERE IS ALREADY A POKEMON
        // IN THE POKEDEX WITH THE SAME POKEMON ID WITH THIS POKEMON
        // IF THERE IS ONE, PRINT AN ERROR MESSAGE AND EXIT THE
        struct pokenode *p = pokedex->head;
        while (p->next != NULL) {
            if (pokemon_id(p->pokemon) == pokemon_id(pokemon)) {
                fprintf(stderr, "ERROR: this Pokemon has already been added to the Pokedex.Exiting...\n");
                exit(1);
            }
            p = p->next;
        }
        p->next = malloc(sizeof(struct pokenode));
        p->next->pokemon = pokemon;
        p->next->found = 0;
        p->next->evolve = NULL;
        p->next->next = NULL;
    }
}
// SHOWING THE DETAILS OF THE POKEMONS IN THE POKEDEX 
void detail_pokemon(Pokedex pokedex) {
    if (pokedex->current_selected != NULL){
        // FIRST IS TO GET THE CURRENT SELECTED POKEMON
        Pokemon pokemon = get_current_pokemon(pokedex);
        // PRINT POKEMON ID
        printf("Id: %03d\n", pokemon_id(pokemon));
        // IF THE POKEMON HAS NOT BEEN FOUND
        // THE DETAILS OF THIS POKEMON IS INVISIBLE TO THE USER
        if (pokedex->current_selected->found == 0) {
            // THE POKEMON NAME IS REPLACED BY *
            printf("Name: ");
            int i = 0;
            while (pokemon_name(pokemon)[i] != '\0') {
                printf("*");
                i++;
            }
            printf("\n");
            // THE REST OF THE DETAILS ARE REPLACED BY --
            printf("Height: --\n");
            printf("Weight: --\n");
            printf("Type: --\n");
        } else {
            // IF THE POKEMON HAS BEEN FOUND, ALL THE DETAILS OF 
            // THIS POKEMON IS VISIBLE TO THE USER NOW
            printf("Name: %s\n", pokemon_name(pokemon));
            printf("Height: %.1fm\n", pokemon_height(pokemon));
            printf("Weight: %.1fkg\n", pokemon_weight(pokemon));
            printf("Type:");
            // PRINT FIRST TYPE OF THIS POKEMON
            pokemon_type first_type = pokemon_first_type(pokemon);
            if (first_type != INVALID_TYPE && first_type != NONE_TYPE) {
                printf(" %s", pokemon_type_to_string(first_type));
            }
            // PRINT SECOND TYPE OF THIS POKEMON IF APPLICABLE
            pokemon_type second_type = pokemon_second_type(pokemon);
            if (second_type != INVALID_TYPE && second_type != NONE_TYPE) {
                printf(" %s", pokemon_type_to_string(second_type));
            } else {
            // IF T HE SECOND TYPE IS NOT APPLICABLE JUST ADD SPACE
                printf(" ");
            }
            printf("\n");
        } 
    }       
}

// RETURN ERROR MESSAGE IF THE POKEDEX IS EMPTY
// UPDATE: EITHER SHOWING ERROR MESSAGE OR SHOWING NOTHING 
// AND ASK FOR NEXT COMMENT IS ACCEPTED NOW
Pokemon get_current_pokemon(Pokedex pokedex) {
    if (pokedex!= NULL || pokedex->current_selected != NULL) {
        return pokedex->current_selected->pokemon;
    }
    return 0;
}

// SET CURRENT SELECTED POKEMON TO BE FIND
// WHICH MEANS THE DETAILS OF THIS POKEMON IS NOW VISIBLE TO THE USER
// IF THE POKEDEX IS EMPTY, THIS FUNCTION DOES NOTHING
void find_current_pokemon(Pokedex pokedex) {
    if (pokedex != NULL && pokedex->current_selected != NULL) {
        pokedex->current_selected->found = 1;
    }
}

// PRINT A LIST OF POKEMON WHICH ONLY CONTAINS ID AND NAME
void print_pokemon(Pokedex pokedex) {
    if (pokedex != NULL) {
        struct pokenode *p = pokedex->head;
        while (p != NULL) {
            Pokemon pokemon = p->pokemon;
            // PUT AN ARROW IN FRONT OF THE CURRENT POKEMON
            if (pokedex->current_selected == p) {
                printf("--> ");
            } else {
                printf("    ");
            }
            printf("#%03d: ", pokemon_id(pokemon)); 
            // PRINT POKEMON NAME AS *, IF IT HAS NOT BEEN FOUND
            if (p->found == 0) {
                int i = 0;
                while (pokemon_name(pokemon)[i] != '\0') {
                    printf("*");
                    i++;
                }
            } else {
                printf("%s", pokemon_name(pokemon));
            }
            printf("\n");
            p = p->next;
        }
    }
}

////////////////////////////////////////////////////////////////////////
//                         Stage 2 Functions                          //
////////////////////////////////////////////////////////////////////////

// SET CURRENT POKEMON TO NEXT POKEMON IN POKEDEX
void next_pokemon(Pokedex pokedex) {
    if (pokedex != NULL && pokedex->head != NULL) {
        if (pokedex->current_selected->next != NULL) {
            pokedex->current_selected = pokedex->current_selected->next;
        }
    }
}

// SET CURRENT POKEMON TO PREVIOUS POKEMON 
void prev_pokemon(Pokedex pokedex) {
    if (pokedex != NULL && pokedex->head != NULL) {
        struct pokenode *p = pokedex->head;
        while (p != NULL) {
            if (p->next == pokedex->current_selected) {
                pokedex->current_selected = p;
                return;
            }
            p = p->next;
        }
    }
}

// MATCH THE INPUT POKEMON ID AND SET THIS POKEMON TO CURRENT POKEMON
void change_current_pokemon(Pokedex pokedex, int id) {
    if (pokedex != NULL && pokedex->head != NULL) {
        struct pokenode *p = pokedex->head;
        while (p != NULL) {
            if (pokemon_id(p->pokemon) == id) {
                pokedex->current_selected = p;
                return;
            }
            p = p->next;
        }
    }
}

// REMOVE CURRENTLY SELECTED POKEMON FROM THE POKEDEX
void remove_pokemon(Pokedex pokedex) {
    if (pokedex != NULL && pokedex->head != NULL) {
        struct pokenode *p = pokedex->head;
        struct pokenode *to_remove = pokedex->current_selected;
        // IF THE REMOVED POKEMON WAS AT THE FRONT OF THE POKEDE        
        if (pokedex->head == pokedex->current_selected) {
            pokedex->head = pokedex->head->next;
            pokedex->current_selected = pokedex->head;
        } else {
            // FIND THE POKEMON BEFRORE CURRENT POKEMO
            while (p->next != NULL && p->next != pokedex->current_selected) {
                p = p->next;
            }
            // IF THE REMOVED POKEMON WAS AT THE END OF THE POKDEDEX
            if (pokedex->current_selected->next == NULL) {
                pokedex->current_selected = p;
            } else {
                pokedex->current_selected = pokedex->current_selected->next;
            }
            p->next = p->next->next;
        }
        destroy_pokemon(to_remove->pokemon);
        free(to_remove);
        // IF THE REMOVED POKEMON IS THE ONLY ONE IN THE POKEDEX
        if (pokedex->head == NULL) {
            pokedex->current_selected = NULL;
        }
    }
}

// DESTROY A GIVEN POKEDEX AND FREE ALL THE ASSOCIATED MEMORY
void destroy_pokedex(Pokedex pokedex) {            
    struct pokenode *p = pokedex->head;
    struct pokenode *curr = NULL;
    while (p != NULL) {
        curr = p;
        p = p->next;
        destroy_pokemon(curr->pokemon);
        free(curr);            
    }
    free(pokedex); 
}

////////////////////////////////////////////////////////////////////////
//                         Stage 3 Functions                          //
////////////////////////////////////////////////////////////////////////

// SET ENCOUNTERED POKEMON TO BE FOUND
void go_exploring(Pokedex pokedex, int seed, int factor, int how_many) {
    srand(seed);
    int id;
    int encounter = 0;
    if (pokedex != NULL && pokedex->head != NULL) {
        for (encounter = 0; encounter <= how_many; encounter++) {
            id = rand() % factor;
            struct pokenode *p = pokedex->head;
            struct pokenode *pokemon = NULL;
            // FIND THE POKEMON ACCORDING TO THE POKEMON ID
            while (p != NULL) {
                if (pokemon_id(p->pokemon) == id) {
                    pokemon = p;
                }
                p = p->next;
            }
             // THEN SET THIS POKEMON TO BE FOUND
            if (pokemon != NULL) {
                pokemon->found = 1;
            }
        }
    } else {
        fprintf(stderr, "ERROR: there is no Pokemon in Pokedex.Exiting...\n");
        exit(1);
    }
}

// return THE NUMBER OF POKEMONS IN THE POKEDEX WHICH HAVE BEEN FOUND
int count_found_pokemon(Pokedex pokedex) {
    int counter = 0;
    if (pokedex != NULL && pokedex->head != NULL) {
        struct pokenode *p = pokedex->head;
        while (p != NULL) {
            if (p->found == 1) {
                counter++;
            }
            p = p->next;
        }
    }
    return counter;
}

// RETURN THE NUMBER OF ALL THE POKEMONS INSIDE POKEDEX 
int count_total_pokemon(Pokedex pokedex) {
    int counter = 0;
    if (pokedex != NULL && pokedex->head != NULL) {
        struct pokenode *p = pokedex->head;
        while (p != NULL) {
            counter++;
            p = p->next;
        }
    }
    return counter;
}

////////////////////////////////////////////////////////////////////////
//                         Stage 4 Functions                          //
////////////////////////////////////////////////////////////////////////

// POKEMON WITH FROM_ID EVOLVE INTO POKEMON WITH TO_ID
void add_pokemon_evolution(Pokedex pokedex, int from_id, int to_id) {
    if (pokedex != NULL && pokedex->head != NULL) {
        if (from_id == to_id) {
            fprintf(stderr, "ERROR: two Pokemon IDs are equal.Exiting...\n");
            exit(1);
        }
        struct pokenode *p_from = NULL;
        struct pokenode *p_to = NULL;
        struct pokenode *p = pokedex->head;
        
         // FIND THE EVOLUTION FROM ONE POKEMON TO OTHER
        while(p != NULL) {
            if (pokemon_id(p->pokemon) == from_id) {
                p_from = p;
            }
            if (pokemon_id(p->pokemon) == to_id) {
                p_to = p;
            }
            p = p->next;
        }
        // PRINT ERROR MESSAGE IF THERE IS NO FROM_ID OR TO_ID 
        if (p_from == NULL){
            fprintf(stderr, "ERROR: no from_id.Exiting...\n");
            exit(1);
        }
        if (p_to == NULL) {
            fprintf(stderr, "ERROR: no to_id.Exiting...\n");
            exit(1);    
        }
        // THEN SET THE EVOLUTION
        p_from->evolve = p_to;
    }
}
// SHOW THE EVOLUTION OF CURRENTLY SELECTED POKEMON
void show_evolutions(Pokedex pokedex) {
    if (pokedex != NULL && pokedex->head != NULL) {
        struct pokenode *p = pokedex->current_selected;
        while (p != NULL) {
            if (p != pokedex->current_selected) {
                printf(" --> ");
            }
            // PRINT FIRST POKEMON
            Pokemon pokemon = p->pokemon;
            // PRINT DETAILS OF THIS POKEMON
            printf("#%03d ", pokemon_id(pokemon));
            if (p->found) {
                printf("%s", pokemon_name(pokemon));
                printf("[");
                pokemon_type first_type = pokemon_first_type(pokemon);
                if (first_type != INVALID_TYPE && first_type != NONE_TYPE) {
                    printf("%s", pokemon_type_to_string(first_type));
                }
                pokemon_type second_type = pokemon_second_type(pokemon);
                if (second_type != INVALID_TYPE && first_type != NONE_TYPE) {
                    printf(", %s", pokemon_type_to_string(second_type));
                } else {
                    printf(" ");
                }
                printf("]");
            } else {
                printf("???? [????]");
            }
            p = p->evolve;
        }
        printf("\n");
    }
}

// RETURN THE POKEMON ID OF THE POKEMON 
// THAT CURRENTLY SELECTED POKEMON EVOLVE INTO
int get_next_evolution(Pokedex pokedex) {
    if (pokedex != NULL && pokedex->head != NULL) {
        struct pokenode *p = pokedex->current_selected;
        // IF THE POKEMON DOES NOT EVOLVE
        if (p->evolve == NULL) {
            return DOES_NOT_EVOLVE;
        }
        
        struct pokenode *evolve = p->evolve;
        return pokemon_id(evolve->pokemon);
    } else {
        fprintf(stderr, "ERROR: Pokedex is empty.Exiting...\n");
        exit(1);
    }
}

////////////////////////////////////////////////////////////////////////
//                         Stage 5 Functions                          //
////////////////////////////////////////////////////////////////////////

// CREATE A NEW POKEDEX WITH SPECIFIC TYPE OF THE POKEMON
Pokedex get_pokemon_of_type(Pokedex pokedex, pokemon_type type) {
    if (type == NONE_TYPE || type == INVALID_TYPE || type == MAX_TYPE) {
        fprintf(stderr, "ERROR: type is invalid.Exiting...\n");
        exit(1);
    }
    // CREATE A NEW POKEDEX
    Pokedex new_pokedex = malloc(sizeof (struct pokedex));
    assert(new_pokedex != NULL);
    new_pokedex->head = NULL;
    new_pokedex->current_selected = NULL;

    struct pokenode *p = pokedex->head;
    while (p != NULL) {
        // ONLY ADD POKEMONS THAT HAVE BEEN FOUND INTO THIS POKEDEX
        if (p->found == 1) {
            Pokemon pokemon = p->pokemon;
            // GET THE POKEMON TYPES
            pokemon_type first_type = pokemon_first_type(pokemon);
            pokemon_type second_type = pokemon_second_type(pokemon);
            // MATCH TYPES
            if (first_type == type || second_type == type) {
                Pokemon new_pokemon = clone_pokemon(pokemon);
                add_pokemon(new_pokedex, new_pokemon);
            }
        }
        p = p->next;
    }
    // SET THE STATUS OF THIS POKEDEX TO FOUND
    p = new_pokedex->head;
    while (p != NULL) {
        p->found = 1;
        p = p->next;
    }
    
    return new_pokedex;   
}

// CREATE A NEW POKEDEX WHICH CONTAINS ONLY THE FOUND POKEMON
// FROM THE ORIGINAL POKEDEX 
Pokedex get_found_pokemon(Pokedex pokedex) {
    // CREATE A NEW POKEDEX
    Pokedex new_pokedex = malloc(sizeof (struct pokedex));
    assert(new_pokedex != NULL);
    new_pokedex->head = NULL;
    new_pokedex->current_selected = NULL;

    struct pokenode *p = pokedex->head;
    struct pokenode *p_new = new_pokedex->head;
    while (p != NULL) {
        // ONLY ADD POKEMONS THAT HAVE BEEN FOUND INTO THIS POKEDEX
        if (p->found == 1) {
            Pokemon pokemon = p->pokemon;
            struct pokenode *new_pokenode = malloc(sizeof(struct pokenode));
            new_pokenode->pokemon = clone_pokemon(pokemon);
            new_pokenode->found = 0;
            new_pokenode->evolve = NULL;
            new_pokenode->next = NULL;
            // ADD FIRST POKEMON
            if (new_pokedex->head == NULL) {
                new_pokedex->head = new_pokenode;
            // POKEMONS APPEAR IN THIS POKEDEX IN ASCENDING ORDER
            // ALSO MEANS THE PRINTED LIST HAS DESCENDING ORDER OF POKEMONS
            // THE FIRST POKEMON ID SHOULD BE GREATER THAN SECOND AND SO ON
            } else if (pokemon_id(new_pokedex->head->pokemon) > pokemon_id(pokemon)) {
                new_pokenode->next = new_pokedex->head;
                new_pokedex->head = new_pokenode;
            } else {
                p_new = new_pokedex->head;
                while (p_new->next != NULL && pokemon_id(p_new->next->pokemon) < pokemon_id(pokemon)) {
                    p_new = p_new->next;
                }
                new_pokenode->next = p_new->next;
                p_new->next = new_pokenode;
            }
        }
        p = p->next;
    }
    // SET CURRENT POKEMON TO THE FIRST POKEMON
    new_pokedex->current_selected = new_pokedex->head;
    // SET THE STATUS OF POKEDEX TO FOUND
    p = new_pokedex->head;
    while (p != NULL) {
        p->found = 1;
        p = p->next;
    }
    
    return new_pokedex;
    
}

// CREATE A NEW POKEDEX CONTAINING ONLY THE POKEMON FROM ORIGINAL POKEDEX WHICH
// HAVE THE GIVEN STRING APPEARING IN ITS NAME
Pokedex search_pokemon(Pokedex pokedex, char *text) {
    // CREATE A NEW POKEDEX   
    Pokedex new_pokedex = malloc(sizeof (struct pokedex));
    assert(new_pokedex != NULL);
    new_pokedex->head = NULL;
    new_pokedex->current_selected = NULL;

    struct pokenode *p = pokedex->head;
    while (p != NULL) {
        // ONLY ADD POKEMONS THAT HAVE BEEN FOUND INTO THIS POKEDEX
        if (p->found == 1) {
            Pokemon pokemon = p->pokemon;
            // GET POKEMON NAME
            char *name = pokemon_name(pokemon);
            int found = 0;
            int i = 0;
            int j;
            // search each character to match
            while (name[i] != '\0') {
                j = 0;
                int not_match = 0;
                while (name[i + j] != '\0' && text[j] != '\0') {
                    char left = name[i + j];
                    char right = text[j];
                    // CONVERT TO LOWERCASE
                    if (left >= 'A' && left <= 'Z') {
                        left = left - 'A' + 'a';
                    }
                    if (right >= 'A' && right <= 'Z') {
                        right = right - 'A' + 'a';
                    }
                    if (left != right) {
                        not_match = 1;
                    }
                    j++;
                }
                // MATCHING THE NAME
                if (not_match == 0 && text[j] == '\0') {
                    found = 1;
                }
                i++;
            }
            // ADD THIS POKEMON TO NEW POKEDEX
            if (found) {
                Pokemon new_pokemon = clone_pokemon(pokemon);
                add_pokemon(new_pokedex, new_pokemon);
            }
        }
        p = p->next;
    }
    // SET CURRENTLY POKEMON TO THE FIRST POKEMON 
    new_pokedex->current_selected = new_pokedex->head;
    // SET THE STATUS OF POKEDEX TO FOUND
    p = new_pokedex->head;
    while (p != NULL) {
        p->found = 1;
        p = p->next;
    }
    
    return new_pokedex;
    
}


