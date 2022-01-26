// Assignment 2 19T1 COMP1511: Pokedex
// test_pokedex.c
//
// This file allows you to automatically test the functions you
// implement in pokedex.c.
//
// This program was written by MENGHUI WEI (z5082327)
// on 26-04-2019
//
// Version 1.0.0: Assignment released.
// Version 1.0.1: Added pointer check for the provided tests.
// NEW COMMENTS ARE IN UPPERCASE

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

#include "pokedex.h"


struct pokedex {
    struct pokenode *head;
    struct pokenode *current_selected;
};

struct pokenode {
    struct pokenode *next;
    Pokemon         pokemon;
    int             found;
    struct pokenode *evolve;
};

// Sample data on Bulbasaur, the Pokemon with pokemon_id 1.
#define BULBASAUR_ID 1
#define BULBASAUR_NAME "Bulbasaur"
#define BULBASAUR_HEIGHT 0.7
#define BULBASAUR_WEIGHT 6.9
#define BULBASAUR_FIRST_TYPE GRASS_TYPE
#define BULBASAUR_SECOND_TYPE POISON_TYPE

// Sample data on Ivysaur, the Pokemon with pokemon_id 2.
#define IVYSAUR_ID 2
#define IVYSAUR_NAME "Ivysaur"
#define IVYSAUR_HEIGHT 1.0
#define IVYSAUR_WEIGHT 13.0
#define IVYSAUR_FIRST_TYPE GRASS_TYPE
#define IVYSAUR_SECOND_TYPE POISON_TYPE


// ADDING TWO MORE POKEMON TO TEST
// 39 JIGGLYPUFF
// 151 MEW
#define JIGGLYPUFF_ID 39
#define JIGGLYPUFF_NAME "jigglypuff"
#define JIGGLYPUFF_HEIGHT 0.5
#define JIGGLYPUFF_WEIGHT 5.5
#define JIGGLYPUFF_FIRST_TYPE FAIRY_TYPE
#define JIGGLYPUFF_SECOND_TYPE NORMAL_TYPE

#define MEW_ID 151
#define MEW_NAME "mew"
#define MEW_HEIGHT 0.4
#define MEW_WEIGHT 4.0
#define MEW_FIRST_TYPE PSYCHIC_TYPE
#define MEW_SECOND_TYPE NONE_TYPE

// ADD ESPEON TO TEST GET_POKEMON_OF_TYPE FUNCTION
#define ESPEON_ID 196
#define ESPEON_NAME "espeon"
#define ESPEON_HEIGHT 0.9
#define ESPEON_WEIGHT 26.5
#define ESPEON_FIRST_TYPE PSYCHIC_TYPE
#define ESPEON_SECOND_TYPE NONE_TYPE

// TEST THE FUNCTIONS IN POKEDEX.C
static void test_new_pokedex(void);
static void test_add_pokemon(void);
static void test_get_found_pokemon(void);
static void test_next_pokemon(void);

static void test_detail_pokemon(void);
static void test_get_current_pokemon(void);
static void test_find_current_pokemon(void);
static void test_print_pokemon(void);

static void test_next_pokemon(void);
static void test_prev_pokemon(void);
static void test_change_current_pokemon(void);
static void test_remove_pokemon(void);

static void test_count_found_pokemon(void);
static void test_go_exploring(void);
static void test_count_total_pokemon(void);

static void test_add_pokemon_evolution(void);
static void test_show_evolutions(void);


static void test_get_pokemon_of_type(void);
static void test_search_pokemon(void);


// Helper functions for creating/comparing Pokemon.
// ADDING JIGGLYPUFF AND MEW
static Pokemon create_bulbasaur(void);
static Pokemon create_ivysaur(void);
static Pokemon create_jigglypuff(void);
static Pokemon create_mew(void);
static Pokemon create_espeon(void);
static int is_same_pokemon(Pokemon first, Pokemon second);
static int is_copied_pokemon(Pokemon first, Pokemon second);

int main(int argc, char *argv[]) {
    printf("Welcome to the COMP1511 Pokedex Tests!\n");

    printf("\n==================== Pokedex Tests ====================\n");

    test_new_pokedex();
    test_add_pokemon();
    test_next_pokemon();
    test_get_found_pokemon();
    
    // ADD NEW TEST FUNCTIONS
    // STAGE 1
    test_detail_pokemon();
    test_get_current_pokemon();
    test_find_current_pokemon();
    test_print_pokemon();
    // STAGE 2
    test_next_pokemon();
    test_prev_pokemon();
    test_change_current_pokemon();
    test_remove_pokemon();
    // STAGE 3
    test_count_found_pokemon();
    test_go_exploring();
    test_count_total_pokemon();
    // STAGE 4
    test_add_pokemon_evolution();
    test_show_evolutions();
    // STAGE 5
    test_get_pokemon_of_type();
    test_search_pokemon();

    printf("\nAll Pokedex tests passed, you are Awesome!\n");
}


////////////////////////////////////////////////////////////////////////
//                     Pokedex Test Functions                         //
////////////////////////////////////////////////////////////////////////

// `test_new_pokedex` checks whether the new_pokedex and destroy_pokedex
// functions work correctly, to the extent that it can.
//
// It does this by creating a new Pokedex, checking that it's not NULL,
// then calling destroy_pokedex.
//
// Note that it isn't possible to check whether destroy_pokedex has
// successfully destroyed/freed the Pokedex, so the best we can do is to
// call the function and make sure that it doesn't crash..
static void test_new_pokedex(void) {
    printf("\n>> Testing new_pokedex\n");

    printf("    ... Creating a new Pokedex\n");
    Pokedex pokedex = new_pokedex();

    printf("       --> Checking that the returned Pokedex is not NULL\n");
    assert(pokedex != NULL);

    printf("    ... Destroying the Pokedex\n");
    destroy_pokedex(pokedex);

    printf(">> Passed new_pokedex tests!\n");
}

// `test_add_pokemon` checks whether the add_pokemon function works
// correctly.
//
// It does this by creating the Pokemon Bulbasaur, Ivysaur and Jigglypuff
// (using the helper functions in this file and the provided code in pokemon.c)
// and calling add_pokemon to add it to the Pokedex.
//
// Some of the ways that you could extend these test would include:
//   - adding additional Pokemon other than just Bulbasaur,
//   - checking whether the currently selected Pokemon is correctly set,
//   - checking that functions such as `count_total_pokemon` return the
//     correct result after more Pokemon are added,
//   - ... and more!

static void test_add_pokemon(void) {
    printf("\n>> Testing add_pokemon\n");

    printf("    ... Creating a new Pokedex\n");
    Pokedex pokedex = new_pokedex();
    
    //CHECKING THE NEW POKEDEX
    printf("       --> Checking that the head of Pokedex is NULL\n");
    assert(pokedex->head == NULL);
    
    printf("       --> Checking that the current selected Pokemon"
            "of Pokedex is NULL\n");
    assert(pokedex->current_selected == NULL);
    
    // ADD BULBASAUR INTO NEW POKEDEX
    printf("    ... Creating Bulbasaur\n");
    Pokemon bulbasaur = create_bulbasaur();

    printf("    ... Adding Bulbasaur to the Pokedex\n");
    add_pokemon(pokedex, bulbasaur);
    
    // CHECKING THE POKEDEX AFTER BULBASAUR BEEN ADDED
    printf("       --> Checking that the head of Pokedex is not NULL\n");
    assert(pokedex->head != NULL);
    
    printf("       --> Checking that the current selected Pokemon of"
            "Pokedex is not NULL\n");
    assert(pokedex->current_selected != NULL);
    
    printf("       --> Checking that the current selected Pokemon of"
            "Pokedex is Bulbasaur\n");
    assert(is_same_pokemon(pokedex->current_selected->pokemon, bulbasaur));
    
    // ADD IVYSAUR INTO THE POKEDEX
    printf("    ... Creating Ivysaur\n");
    Pokemon ivysaur = create_ivysaur();

    printf("    ... Adding Ivysaur to the Pokedex\n");
    add_pokemon(pokedex, ivysaur);
    
    // CHECKING THE POKEDEX AFTER IVYSAUR BEEN ADDED
    printf("       --> Checking that the head of Pokedex is not NULL\n");
    assert(pokedex->head != NULL);
    
    printf("       --> Checking that the current selected Pokemon of Pokedex"
            "is not NULL\n");
    assert(pokedex->current_selected != NULL);
    
    printf("       --> Checking that the current selected Pokemon of Pokedex"
            "is Bulbasaur\n");
    assert(is_same_pokemon(pokedex->current_selected->pokemon, bulbasaur));

    printf("    ... Destroying the Pokedex\n");
    destroy_pokedex(pokedex);

    printf(">> Passed add_pokemon tests!\n");
}

// `test_next_pokemon` checks whether the next_pokemon function works
// correctly.
//
// It does this by creating three Pokemon: Bulbasaur, Ivysaur and Jigglypuff
// (using the helper functions in this file and the provided code in
// pokemon.c).
//
// It then adds these to the Pokedex, then checks that calling the
// next_pokemon function changes the currently selected Pokemon from
// Bulbasaur to Ivysaur, and then calling the next_pokemon function
// changes the currently selected Pokemon from Ivysaur to Jigglypuff.
//
// Some of the ways that you could extend these tests would include:
//   - adding even more Pokemon to the Pokedex,
//   - calling the next_pokemon function when there is no "next" Pokemon,
//   - calling the next_pokemon function when there are no Pokemon in
//     the Pokedex,
//   - ... and more!

static void test_next_pokemon(void) {
    printf("\n>> Testing next_pokemon\n");

    printf("    ... Creating a new Pokedex\n");
    Pokedex pokedex = new_pokedex();
    
    // CREATING THREE POKEMONS
    printf("    ... Creating Bulbasaur, Ivysaur and Jigglypuff\n");
    Pokemon bulbasaur = create_bulbasaur();
    Pokemon ivysaur = create_ivysaur();
    Pokemon jigglypuff = create_jigglypuff();
    
    // ADDING POKEMONS INTO THE POKEDEX
    printf("    ... Adding Bulbasaur, Ivysaur and Jigglypuff to the Pokedex\n");
    add_pokemon(pokedex, bulbasaur);
    add_pokemon(pokedex, ivysaur);
    add_pokemon(pokedex, jigglypuff);
    
    // CHECKING THE POKEDEX AFTER ADDING POKEMONS
    printf("       --> Checking that the current Pokemon is Bulbasaur\n");
    assert(is_same_pokemon(get_current_pokemon(pokedex), bulbasaur));

    printf("    ... Moving to the next pokemon\n");
    next_pokemon(pokedex);

    printf("       --> Checking that the current Pokemon is Ivysaur\n");
    assert(is_same_pokemon(get_current_pokemon(pokedex), ivysaur));

    printf("    ... Moving to the next pokemon\n");
    next_pokemon(pokedex);

    printf("       --> Checking that the current Pokemon is Jigglypuff\n");
    assert(is_same_pokemon(get_current_pokemon(pokedex), jigglypuff));

    printf("    ... Destroying the Pokedex\n");
    destroy_pokedex(pokedex);

    printf(">> Passed next_pokemon tests!\n");
}

// `test_get_found_pokemon` checks whether the get_found_pokemon
// function works correctly.
//
// It does this by creating two Pokemon: Bulbasaur and Ivysaur (using
// the helper functions in this file and the provided code in pokemon.c).
//
// It then adds these to the Pokedex, sets Bulbasaur to be found, and
// then calls the get_found_pokemon function to get all of the Pokemon
// which have been found (which should be just the one, Bulbasaur).
//
// Some of the ways that you could extend these tests would include:
//   - calling the get_found_pokemon function on an empty Pokedex,
//   - calling the get_found_pokemon function on a Pokedex where none of
//     the Pokemon have been found,
//   - checking that the Pokemon in the new Pokedex are in ascending
//     order of pokemon_id (regardless of the order that they appeared
//     in the original Pokedex),
//   - checking that the currently selected Pokemon in the returned
//     Pokedex has been set correctly,
//   - checking that the original Pokedex has not been modified,
//   - ... and more!
static void test_get_found_pokemon(void) {
    printf("\n>> Testing get_found_pokemon\n");

    printf("    ... Creating a new Pokedex\n");
    Pokedex pokedex = new_pokedex();

    printf("    ... Creating Bulbasaur and Ivysaur\n");
    Pokemon bulbasaur = create_bulbasaur();
    Pokemon ivysaur = create_ivysaur();

    printf("    ... Adding Bulbasaur and Ivysaur to the Pokedex\n");
    add_pokemon(pokedex, bulbasaur);
    add_pokemon(pokedex, ivysaur);

    printf("      --> Checking that the current Pokemon is Bulbasaur\n");
    assert(get_current_pokemon(pokedex) == bulbasaur);
    
    printf("    ... Setting Bulbasaur to be found\n");
    find_current_pokemon(pokedex);

    printf("    ... Getting all found Pokemon\n");
    Pokedex found_pokedex = get_found_pokemon(pokedex);

    printf("      --> Checking the correct Pokemon were copied and returned\n");
    assert(count_total_pokemon(found_pokedex) == 1);
    assert(count_found_pokemon(found_pokedex) == 1);
    assert(is_copied_pokemon(get_current_pokemon(found_pokedex), bulbasaur));

    printf("    ... Destroying both Pokedexes\n");
    destroy_pokedex(pokedex);
    destroy_pokedex(found_pokedex);

    printf(">> Passed get_found_pokemon tests!\n");
}

//                                   STAGE 1
////////////////////////////////TEST_DETAIL_POKEMON/////////////////////////////
//                                                                            //
// THIS TEST CHECKS WHETHER THE DETAILS_POKEMON FUNCTION WORKS CORRECTLY.     //
//                                                                            //
// IT DOES THIS BY CREATING THREE POKEMONS: BULBASAUR, IVYSAUR AND JIGGLYPUFF //
//                                                                            //
// IT THEN ADDED THESE POKEMONS TO THE POKEDEX AND SETS BULBASAUR TO BE FOUND //
// THEN CALLS THE DETAIL_POKEMON FUNCTION TO DISPLAY INFORMATION OF POKEMONS  //
//                                                                            //
////////////////////////////////////////////////////////////////////////////////
static void test_detail_pokemon(void) {
    printf("\n>> Testing detail_pokemon\n");

    printf("    ... Creating a new Pokedex\n");
    Pokedex pokedex = new_pokedex();

    printf("    ... Creating Bulbasaur, Ivysaur and Jigglypuff\n");
    Pokemon bulbasaur = create_bulbasaur();
    Pokemon ivysaur = create_ivysaur();
    Pokemon jigglypuff = create_jigglypuff();

    printf("    ... Adding Bulbasaur, Ivysaur and Jigglypuff to the Pokedex\n");
    add_pokemon(pokedex, bulbasaur);
    add_pokemon(pokedex, ivysaur);
    add_pokemon(pokedex, jigglypuff);

    printf("    ... Setting Bulbasaur to be found\n");
    find_current_pokemon(pokedex);

    printf("    ... Now print the detail of Bulbasaur\n");
    detail_pokemon(pokedex);

    printf("    ... Moving to the next pokemon\n");
    next_pokemon(pokedex);

    printf("    ... Now print the detail of Ivasaur\n");
    detail_pokemon(pokedex);

    printf("    ... Moving to the next pokemon\n");
    next_pokemon(pokedex);

    printf("    ... Now print the detail of Jigglypuff\n");
    detail_pokemon(pokedex);

    printf("    ... Destroying the Pokedex\n");
    destroy_pokedex(pokedex);

    printf(">> Passed detail_pokemon tests!\n");
}

/////////////////////////////TEST_GET_CURRENT_POKEMON///////////////////////////
//                                                                            //
// THIS TEST CHECK WHETHER THE GET_CURRENT_POKEMON FUNCTION WORKS CORRECTLY.  //
//                                                                            //
// IT DOES THIS BY CREATING THREE POKEMONS: BULBASAUR, IVYSAUR AND JIGGLYPUFF //
//                                                                            //
// IT THEN ADDED THESE POKEMONS TO THE POKEDEX AND SETS BULBASAUR TO BE FOUND //
// THEN CALLS THE GET_CURRENT_POKEMON FUNCTION TO CHECK THE CURRENT POKEMON   //
// ALSO CALL NEXT_POKEMON FUNCTION TO MOVE THE CURRENT POKEMON TO NEXT        //
//                                                                            //
////////////////////////////////////////////////////////////////////////////////
static void test_get_current_pokemon(void) {
    printf("\n>> Testing get_current_pokemon\n");

    printf("    ... Creating a new Pokedex\n");
    Pokedex pokedex = new_pokedex();

    printf("    ... Creating Bulbasaur, Ivysaur and Jigglypuff\n");
    Pokemon bulbasaur = create_bulbasaur();
    Pokemon ivysaur = create_ivysaur();
    Pokemon jigglypuff = create_jigglypuff();

    printf("    ... Adding Bulbasaur, Ivysaur and Jigglypuff to the Pokedex\n");
    add_pokemon(pokedex, bulbasaur);
    add_pokemon(pokedex, ivysaur);
    add_pokemon(pokedex, jigglypuff);

    Pokemon current_pokemon = get_current_pokemon(pokedex);
    printf("       --> Checking that the current Pokemon is Bulbasaur\n");
    assert(is_same_pokemon(current_pokemon, bulbasaur));

    printf("    ... Moving to the next pokemon\n");
    next_pokemon(pokedex);

    current_pokemon = get_current_pokemon(pokedex);
    printf("       --> Checking that the current Pokemon is Ivysaur\n");
    assert(is_same_pokemon(current_pokemon, ivysaur));
    
    printf("    ... Moving to the next pokemon\n");
    next_pokemon(pokedex);

    current_pokemon = get_current_pokemon(pokedex);
    printf("       --> Checking that the current Pokemon is Jigglypuff\n");
    assert(is_same_pokemon(current_pokemon, jigglypuff));
    
    printf("    ... Destroying the Pokedex\n");
    destroy_pokedex(pokedex);

    printf(">> Passed get_current_pokemon tests!\n");
}

//////////////////////////////TEST_FIND_CURRENT_POKEMON/////////////////////////
//                                                                            //
// THIS TEST CHECK WHETHER THE FIND_CURRENT_POKEMON WORKS CORRECTLY           //
//                                                                            //
// IT DOES THIS BY CREATING THREE POKEMONS: BULBASAUR, IVYSAUR AND JIGGLYPUFF //
// AND ADD THEM INTO POKEDEX                                                  //
//                                                                            //
// IT THEN SET BULBASAUR TO BE FIND AND THEN CHECK WHETHER THIS WORKS         //
//                                                                            //
////////////////////////////////////////////////////////////////////////////////
static void test_find_current_pokemon(void) {
    printf("\n>> Testing find_current_pokemon\n");

    printf("    ... Creating a new Pokedex\n");
    Pokedex pokedex = new_pokedex();

    printf("    ... Creating Bulbasaur, Ivysaur and Jigglypuff\n");
    Pokemon bulbasaur = create_bulbasaur();
    Pokemon ivysaur = create_ivysaur();
    Pokemon jigglypuff = create_jigglypuff();

    printf("    ... Adding Bulbasaur, Ivysaur and Jigglypuff to the Pokedex\n");
    add_pokemon(pokedex, bulbasaur);
    add_pokemon(pokedex, ivysaur);
    add_pokemon(pokedex, jigglypuff);

    printf("       --> Checking that the current Pokemon is not found\n");
    assert(pokedex->current_selected->found == 0);

    printf("    ... Setting Bulbasaur to be found\n");
    find_current_pokemon(pokedex);

    printf("       --> Checking that the current Pokemon is found\n");
    assert(pokedex->current_selected->found == 1);

    printf("    ... Destroying the Pokedex\n");
    destroy_pokedex(pokedex);

    printf(">> Passed find_current_pokemon tests!\n");
}

/////////////////////////////TEST_PRINT_POKEMON/////////////////////////////////
//                                                                            //
// THIS TEST CHECKS WHETHER PRINT_POKEMON FUNCTION WORKS CORRECTLY            //
//                                                                            //
// IT DOES THIS BY CREATING THREE POKEMONS: BULBASAUR, IVYSAUR AND JIGGLYPUFF //
// AND ADD THEM INTO POKEDEX                                                  //
//                                                                            //
// IT THEN SET BULBASAUR TO BE FIND AND PRINT DETAILS OF IT                   // 
// AND MOVE TO NEXT POKEMON SET IT TO BE FOUND AND PRINT DETAILS OF IT        //
//                                                                            //
////////////////////////////////////////////////////////////////////////////////
static void test_print_pokemon(void) {
    printf("\n>> Testing print_pokemon\n");

    printf("    ... Creating a new Pokedex\n");
    Pokedex pokedex = new_pokedex();

    printf("    ... Creating Bulbasaur, Ivysaur and Jigglypuff\n");
    Pokemon bulbasaur = create_bulbasaur();
    Pokemon ivysaur = create_ivysaur();
    Pokemon jigglypuff = create_jigglypuff();

    printf("    ... Adding Bulbasaur, Ivysaur and Jigglypuff to the Pokedex\n");
    add_pokemon(pokedex, bulbasaur);
    add_pokemon(pokedex, ivysaur);
    add_pokemon(pokedex, jigglypuff);

    printf("    ... Setting Bulbasaur to be found\n");
    find_current_pokemon(pokedex);

    printf("    ... Print Pokedex\n");
    print_pokemon(pokedex);

    printf("    ... Moving to the next pokemon\n");
    next_pokemon(pokedex);

    printf("    ... Print Pokedex\n");
    print_pokemon(pokedex);

    printf("    ... Setting Ivysaur to be found\n");
    find_current_pokemon(pokedex);

    printf("    ... Print Pokedex\n");
    print_pokemon(pokedex);
    
    printf("    ... Moving to the next pokemon\n");
    next_pokemon(pokedex);

    printf("    ... Print Pokedex\n");
    print_pokemon(pokedex);

    printf("    ... Setting Jigglypuff to be found\n");
    find_current_pokemon(pokedex);

    printf("    ... Print Pokedex\n");
    print_pokemon(pokedex);
    
    printf("    ... Destroying the Pokedex\n");
    destroy_pokedex(pokedex);

    printf(">> Passed print_pokemon tests!\n");
}
//                                 STAGE 2
///////////////////////////////TEST_PREV_POKEMON////////////////////////////////
//                                                                            //
// THIS TEST CHECK WHETHER PREV_POKEMON WORKS CORRECTLY                       //
//                                                                            //
// IT DOES THIS BY CREATING THREE POKEMONS: BULBASAUR, IVYSAUR AND JIGGLYPUFF //
// AND ADD THEM INTO POKEDEX                                                  //
//                                                                            //
// IT THEN SET JIGGLYPUFF AS CURRENT POKEMON                                  //
// AND CALLING PREV_POKEMON TO CHECK THE PREVIOUS POKEMON                     //
//                                                                            //
////////////////////////////////////////////////////////////////////////////////
static void test_prev_pokemon(void) {
    printf("\n>> Testing prev_pokemon\n");

    printf("    ... Creating a new Pokedex\n");
    Pokedex pokedex = new_pokedex();

    printf("    ... Creating Bulbasaur, Ivysaur and Jigglypuff\n");
    Pokemon bulbasaur = create_bulbasaur();
    Pokemon ivysaur = create_ivysaur();
    Pokemon jigglypuff = create_jigglypuff();

    printf("    ... Adding Bulbasaur, Ivysaur and Jigglypuff to the Pokedex\n");
    add_pokemon(pokedex, bulbasaur);
    add_pokemon(pokedex, ivysaur);
    add_pokemon(pokedex, jigglypuff);

    printf("    ... Set current Pokemon to Jigglypuff\n");
    next_pokemon(pokedex);
    next_pokemon(pokedex);

    printf("       --> Checking that the current Pokemon is Jigglypuff\n");
    assert(is_same_pokemon(get_current_pokemon(pokedex), jigglypuff));

    printf("    ... Moving to the previous pokemon\n");
    prev_pokemon(pokedex);

    printf("       --> Checking that the prev Pokemon is Ivysaur\n");
    assert(is_same_pokemon(get_current_pokemon(pokedex), ivysaur));

    printf("    ... Moving to the previous pokemon\n");
    prev_pokemon(pokedex);

    printf("       --> Checking that the prev Pokemon is Bulbasaur\n");
    assert(is_same_pokemon(get_current_pokemon(pokedex), bulbasaur));

    printf("    ... Destroying the Pokedex\n");
    destroy_pokedex(pokedex);

    printf(">> Passed prev_pokemon tests!\n");
}

/////////////////////////TEST_CHANGE_CURRENT_POKEMON////////////////////////////
//                                                                            //
// THIS TEST CHECK WHETHER CHANGE_CURRENT_POKEMON FUNCTION WORKS CORRECTLY    //
//                                                                            //
// IT DOES THIS BY CREATING THREE POKEMONS: BULBASAUR, IVYSAUR AND JIGGLYPUFF //
// AND ADD THEM INTO POKEDEX                                                  //
//                                                                            //
// IT THEN SET BULBASAUR AS CURRENT POKEMON AND CALLING CHANGE_CURRENT_POKEMON//
// AND CHANGE IT TO JIGGLYPUFF AND CHECK THE CURRENT POKEMON IS JIGGLYPUFF    //
//                                                                            // 
// ALSO TEST THE FUNCTION BY PUTTING AN INVALID POKEMON ID                    //
//                                                                            //
////////////////////////////////////////////////////////////////////////////////
static void test_change_current_pokemon(void) {
    printf("\n>> Testing change_current_pokemon\n");

    printf("    ... Creating a new Pokedex\n");
    Pokedex pokedex = new_pokedex();

    printf("    ... Creating Bulbasaur, Ivysaur and Jigglypuff\n");
    Pokemon bulbasaur = create_bulbasaur();
    Pokemon ivysaur = create_ivysaur();
    Pokemon jigglypuff = create_jigglypuff();

    printf("    ... Adding Bulbasaur, Ivysaur and Jigglypuff to the Pokedex\n");
    add_pokemon(pokedex, bulbasaur);
    add_pokemon(pokedex, ivysaur);
    add_pokemon(pokedex, jigglypuff);

    printf("       --> Checking that the current Pokemon is Bulbasaur\n");
    assert(is_same_pokemon(get_current_pokemon(pokedex), bulbasaur));

    printf("    ... Change current Pokemon to Jigglypuff (id: 25)\n");
    change_current_pokemon(pokedex, 39);

    printf("       --> Checking that the current Pokemon is Jigglypuff\n");
    assert(is_same_pokemon(get_current_pokemon(pokedex), jigglypuff));

    printf("    ... Change current Pokemon to invalid id (id: 55)\n");
    change_current_pokemon(pokedex, 55);

    printf("       --> Checking that the current Pokemon is Jigglypuff\n");
    assert(is_same_pokemon(get_current_pokemon(pokedex), jigglypuff));

    printf("    ... Destroying the Pokedex\n");
    destroy_pokedex(pokedex);

    printf(">> Passed get_current_pokemon tests!\n");
}

////////////////////////////TEST_REMOVE_POKEMON/////////////////////////////////
//                                                                            //
// THIS TEST CHECK WHETHER REMOVE_POKEMON FUNCTION WORKS CORRECTLY            //
//                                                                            //
// IT DOES THIS BY CREATING THREE POKEMONS: BULBASAUR, IVYSAUR AND JIGGLYPUFF //
// AND ADD THEM INTO POKEDEX                                                  //
//                                                                            //
// IT THEN SET BULBASAUR AS CURRENT POKEMON AND CHANGE IT TO JIGGLYPUFF AND   //
// DELETE JIGGLYPUFF                                                          //
//                                                                            //
// AFTER THAT CHECK THE CURRENT POKEMON IS IVYSAUR THEN SET CURRENT POKEMON   //
// TO BULBALSAUR AND DELETE IT AND CHECK THE CURRENT POKEMON IS IVYSAUR       //
// THEN DELETE IT                                                             //
//                                                                            //
// FINALLY, CHECK WHETHER POKEDEX IS EMPTY                                    //
//                                                                            //
////////////////////////////////////////////////////////////////////////////////
static void test_remove_pokemon(void) {
    printf("\n>> Testing remove_pokemon\n");

    printf("    ... Creating a new Pokedex\n");
    Pokedex pokedex = new_pokedex();

    printf("    ... Creating Bulbasaur, Ivysaur and Jigglypuff\n");
    Pokemon bulbasaur = create_bulbasaur();
    Pokemon ivysaur = create_ivysaur();
    Pokemon jigglypuff = create_jigglypuff();

    printf("    ... Adding Bulbasaur, Ivysaur and Jigglypuff to the Pokedex\n");
    add_pokemon(pokedex, bulbasaur);
    add_pokemon(pokedex, ivysaur);
    add_pokemon(pokedex, jigglypuff);

    printf("       --> Checking that the current Pokemon is Bulbasaur\n");
    assert(is_same_pokemon(get_current_pokemon(pokedex), bulbasaur));

    printf("       --> Change current Pokemon to Jigglypuff\n");
    change_current_pokemon(pokedex, 39);

    printf("       --> Checking that the current Pokemon is Jigglypuff\n");
    assert(is_same_pokemon(get_current_pokemon(pokedex), jigglypuff));

    printf("    ... Remove Jigglypuff\n");
    remove_pokemon(pokedex);

    printf("       --> Checking that the current Pokemon is Ivysaur\n");
    assert(is_same_pokemon(get_current_pokemon(pokedex), ivysaur));

    printf("       --> Change current Pokemon to Bulbasaur\n");
    change_current_pokemon(pokedex, 1);

    printf("       --> Checking that the current Pokemon is Bulbasaur\n");
    assert(is_same_pokemon(get_current_pokemon(pokedex), bulbasaur));

    printf("    ... Remove Bulbasaur\n");
    remove_pokemon(pokedex);

    printf("       --> Checking that the current Pokemon is Ivysaur\n");
    assert(is_same_pokemon(get_current_pokemon(pokedex), ivysaur));

    printf("    ... Remove Ivysaur\n");
    remove_pokemon(pokedex);

    printf("       --> Checking that the current Pokemon is NULL\n");
    assert(pokedex->current_selected == NULL);

    printf("    ... Destroying the Pokedex\n");
    destroy_pokedex(pokedex);

    printf(">> Passed remove_pokemon tests!\n");
}
//                                  STAGE 3
///////////////////////////TEST_COUNT_FOUND_POKEMON/////////////////////////////
//                                                                            //
// THIS TEST CHECKS WHETHER COUNT_FOUND_POKEMON FUNCTION WORKS CORRECTLY      //
//                                                                            //
// IT DOES THIS BY CREATING THREE POKEMONS: BULBASAUR, IVYSAUR , JIGGLYPUFF   //
// AND MEW AND ADD THEM INTO POKEDEX                                          //
//                                                                            //
// IT THEN CHECK COUNT OF THE POKEMON HAS BEEN FOUND IS 0                     //
//                                                                            //
// AND THEN CALLING FIND_CURRENT_POKEMON TO FIND EACH POKEMON                 //
// AND CHECK THE COUNT OF FOUND POKEMON AFTERWARD                             //
//                                                                            //
////////////////////////////////////////////////////////////////////////////////
static void test_count_found_pokemon(void) {
    printf("\n>> Testing count_found_Pokemon\n");

    printf("    ... Creating a new Pokedex\n");
    Pokedex pokedex = new_pokedex();

    printf("    ... Creating Bulbasaur, Ivysaur and Jigglypuff\n");
    Pokemon bulbasaur = create_bulbasaur();
    Pokemon ivysaur = create_ivysaur();
    Pokemon jigglypuff = create_jigglypuff();
    Pokemon mew = create_mew();

    printf("    ... Adding Bulbasaur, Ivysaur and Jigglypuff to the Pokedex\n");
    add_pokemon(pokedex, bulbasaur);
    add_pokemon(pokedex, ivysaur);
    add_pokemon(pokedex, jigglypuff);
    add_pokemon(pokedex, mew);
    
    printf("       --> Checking that the number of found Pokemon is 0\n");
    assert(count_found_pokemon(pokedex) == 0);

    printf("    ... Found Bulbasaur\n");
    find_current_pokemon(pokedex);

    printf("       --> Checking that the number of found Pokemon is 1\n");
    assert(count_found_pokemon(pokedex) == 1);

    printf("    ... Found Ivysaur\n");
    next_pokemon(pokedex);
    find_current_pokemon(pokedex);

    printf("       --> Checking that the number of found Pokemon is 2\n");
    assert(count_found_pokemon(pokedex) == 2);
    
    printf("    ... Found Jigglypuff\n");
    next_pokemon(pokedex);
    find_current_pokemon(pokedex);

    printf("       --> Checking that the number of found Pokemon is 3\n");
    assert(count_found_pokemon(pokedex) == 3);
    
    printf("    ... Found Mew\n");
    next_pokemon(pokedex);
    find_current_pokemon(pokedex);

    printf("       --> Checking that the number of found Pokemon is 4\n");
    assert(count_found_pokemon(pokedex) == 4);

    printf("    ... Destroying the Pokedex\n");
    destroy_pokedex(pokedex);

    printf(">> Passed count_found_Pokemon tests!\n");
}

//////////////////////////////TEST_GO_EXPLORING/////////////////////////////////
//                                                                            //
// THIS TEST CHECK WHETHER GO_EXPLORING FUNCTION WORKS CORRECTLY              //
//                                                                            //
// IT DOES THIS BY CREATING THREE POKEMONS: BULBASAUR, IVYSAUR AND JIGGLYPUFF //
// AND ADD THEM INTO POKEDEX                                                  //
//                                                                            //
// AND IT CALLS THE GO_EXPLORING FUNCTION TO EXPLORE POKEMONS                 //
//                                                                            //
////////////////////////////////////////////////////////////////////////////////
static void test_go_exploring(void) {
    printf("\n>> Testing go_exploring\n");

    printf("    ... Creating a new Pokedex\n");
    Pokedex pokedex = new_pokedex();

    printf("    ... Creating Bulbasaur, Ivysaur and Jigglypuff\n");
    Pokemon bulbasaur = create_bulbasaur();
    Pokemon ivysaur = create_ivysaur();
    Pokemon jigglypuff = create_jigglypuff();

    printf("    ... Adding Bulbasaur, Ivysaur and Jigglypuff to the Pokedex\n");
    add_pokemon(pokedex, bulbasaur);
    add_pokemon(pokedex, ivysaur);
    add_pokemon(pokedex, jigglypuff);

    printf("       --> Checking that the number of found Pokemon is 0\n");
    assert(count_found_pokemon(pokedex) == 0);

    printf("    ... Explore one pokemon.\n");
    go_exploring(pokedex, 0, 4, 1);

    printf("       --> Checking that the number of found Pokemon is 1\n");
    assert(count_found_pokemon(pokedex) == 1);

    printf("    ... Destroying the Pokedex\n");
    destroy_pokedex(pokedex);

    printf(">> Passed go_exploring tests!\n");
}

/////////////////////////TEST_COUNT_TOTAL_POKEMON///////////////////////////////
//                                                                            //
// THIS TEST CHECK WHETHER COUNT_TOTAL_POKEMON FUNCTION WORKS CORRECTLY       //
//                                                                            //
// IT DOES THIS BY CREATING THREE POKEMONS: BULBASAUR, IVYSAUR , JIGGLYPUFF   //
// AND MEW THEN CHECK THE POKEDEX IS EMPTY BEFORE ADDING ANY POKEMON          //
//                                                                            //
// ADDING EACH POKEMON TO THE POKEDEX AND CHECK THE COUNT OF TOTAL            //
// POKEMON EACH TIME AFTER ONE POKEMON BEEN ADDED                             //
//                                                                            //
////////////////////////////////////////////////////////////////////////////////
static void test_count_total_pokemon(void) {
    printf("\n>> Testing count_found_Pokemon\n");

    printf("    ... Creating a new Pokedex\n");
    Pokedex pokedex = new_pokedex();

    printf("    ... Creating Bulbasaur, Ivysaur and Jigglypuff\n");
    Pokemon bulbasaur = create_bulbasaur();
    Pokemon ivysaur = create_ivysaur();
    Pokemon jigglypuff = create_jigglypuff();
    Pokemon mew = create_mew();

    printf("       --> Checking that the number of total Pokemon is 0\n");
    assert(count_total_pokemon(pokedex) == 0);

    printf("    ... Adding Bulbasaur to the Pokedex\n");
    add_pokemon(pokedex, bulbasaur);

    printf("       --> Checking that the number of total Pokemon is 1\n");
    assert(count_total_pokemon(pokedex) == 1);

    printf("    ... Adding Ivysaur to the Pokedex\n");
    add_pokemon(pokedex, ivysaur);

    printf("       --> Checking that the number of total Pokemon is 2\n");
    assert(count_total_pokemon(pokedex) == 2);

    printf("    ... Adding Jigglypuff to the Pokedex\n");
    add_pokemon(pokedex, jigglypuff);

    printf("       --> Checking that the number of total Pokemon is 3\n");
    assert(count_total_pokemon(pokedex) == 3);
    
     printf("    ... Adding Mew to the Pokedex\n");
    add_pokemon(pokedex, mew);

    printf("       --> Checking that the number of total Pokemon is 4\n");
    assert(count_total_pokemon(pokedex) == 4);

    printf("    ... Destroying the Pokedex\n");
    destroy_pokedex(pokedex);

    printf(">> Passed count_total_Pokemon tests!\n");
}

//                                  STAGE 4
//////////////////////////TEST_ADD_POKEMON_EVOLUTION////////////////////////////
//                                                                            //
// THIS TEST CHECK WHETHER ADD_POKEMON_EVOLUTION FUNCTION WORKS CORRECTLY     //
// (WE CAN ADD ANY POKEMON INTO THE EVOLUTION)                                //
//                                                                            //
// IT DOES THIS BY CREATING THREE POKEMONS: BULBASAUR, IVYSAUR AND JIGGLYPUFF //
// AND ADD THEM INTO POKEDEX                                                  //
//                                                                            //
// AND IT CALLS THE ADD_POKEMON_EVOLUTION AND EVOLVE BULBASAUR TO IVTYSAUSAUR // 
// AND CHECK IT IS NOT EVOLVE FROM SAME POKEMON                               //
//                                                                            //
// AFTER THAT EVOLVE IVTSAUR TO JIGGLYPUFF AND CHECK IT IS NOT THE SAME       // 
//                                                                            //
////////////////////////////////////////////////////////////////////////////////
static void test_add_pokemon_evolution(void) {
    printf("\n>> Testing add_pokemon_evolution\n");

    printf("    ... Creating a new Pokedex\n");
    Pokedex pokedex = new_pokedex();

    printf("    ... Creating Bulbasaur, Ivysaur and Jigglypuff\n");
    Pokemon bulbasaur = create_bulbasaur();
    Pokemon ivysaur = create_ivysaur();
    Pokemon jigglypuff = create_jigglypuff();

    printf("    ... Adding Bulbasaur, Ivysaur and Jigglypuff to the Pokedex\n");
    add_pokemon(pokedex, bulbasaur);
    add_pokemon(pokedex, ivysaur);
    add_pokemon(pokedex, jigglypuff);

    printf("    ... Adding the evolution from Bulbasaur to Ivysaur\n");
    add_pokemon_evolution(pokedex, 1, 2);

    printf("       --> Checking the evolution\n");
    assert(is_same_pokemon(pokedex->head->evolve->pokemon, ivysaur));

    printf("    ... Adding the evolution from Ivysaur to Jigglypuff\n");
    add_pokemon_evolution(pokedex, 2, 39);
    printf("       --> Checking the evolution\n");
    assert(is_same_pokemon(pokedex->head->next->evolve->pokemon, jigglypuff));

    printf("    ... Destroying the Pokedex\n");
    destroy_pokedex(pokedex);

    printf(">> Passed add_pokemon_evolution tests!\n");
}

/////////////////////////////TEST_SHOW_EVOLUTION////////////////////////////////
//                                                                            //
// THIS TEST CHECKS WHETHER SHOW_EVOLUTION FUNCTION WORKS CORRECTLY           //
//                                                                            //
// IT DOES THIS BY CREATING THREE POKEMONS: BULBASAUR, IVYSAUR AND JIGGLYPUFF //
// AND ADD THEM INTO POKEDEX                                                  //
//                                                                            //
// AND CALLS ADD_POKEMON_EVOLVE TO LET THEM EVOVLE AND SHOW THE EVOLUTION     //
//                                                                            //
// AFTER THAT SET BULBASAUR AND IVYSAUR TO BE FOUND AND SHOW THE EVOLUTION    //
//                                                                            //
////////////////////////////////////////////////////////////////////////////////
static void test_show_evolutions(void) {
    printf("\n>> Testing show_evolutions\n");

    printf("    ... Creating a new Pokedex\n");
    Pokedex pokedex = new_pokedex();

    printf("    ... Creating Bulbasaur, Ivysaur and Jigglypuff\n");
    Pokemon bulbasaur = create_bulbasaur();
    Pokemon ivysaur = create_ivysaur();
    Pokemon jigglypuff = create_jigglypuff();

    printf("    ... Adding Bulbasaur, Ivysaur and Jigglypuff to the Pokedex\n");
    add_pokemon(pokedex, bulbasaur);
    add_pokemon(pokedex, ivysaur);
    add_pokemon(pokedex, jigglypuff);

    printf("    ... Show the evolution\n");
    show_evolutions(pokedex);

    printf("    ... Adding the evolution from Bulbasaur to Ivysaur\n");
    add_pokemon_evolution(pokedex, 1, 2);

    printf("    ... Show the evolution\n");
    show_evolutions(pokedex);

    printf("    ... Adding the evolution from Ivysaur to Jigglypuff\n");
    add_pokemon_evolution(pokedex, 2, 39);

    printf("    ... Show the evolution\n");
    show_evolutions(pokedex);

    printf("    ... Found the Bulbasaur\n");
    find_current_pokemon(pokedex);

    printf("    ... Show the evolution\n");
    show_evolutions(pokedex);

    printf("    ... Found the Ivysaur\n");
    next_pokemon(pokedex);
    find_current_pokemon(pokedex);
    prev_pokemon(pokedex);

    printf("    ... Show the evolution\n");
    show_evolutions(pokedex);

    printf("    ... Destroying the Pokedex\n");
    destroy_pokedex(pokedex);

    printf(">> Passed show_evolutions tests!\n");
}

////////////////////////////TEST_GET_NEXT_EVOLUTION/////////////////////////////
//                                                                            //
// THIS TEST CHECKS WHETHER GET_NEXT_EVOLUTION FUNCTION WORKS CORRECTLY       //
//                                                                            //
// IT DOES THIS BY CREATING TWO POKEMONS: BULBASAUR, IVYSAUR AND ADD THEM     //
// INTO POKEDEX                                                               //  
//                                                                            //
// AFTER THIS FIRST CHECK BULBALSAUR IS NOT EVOLVED                           //
// THEN CALLS ADD_POKEMON_EVOLUTION TO EVOLVE BULBASAUR INTO IVYSAUR          //
//                                                                            // 
// AND CHECK THE EVOLUTION OF BULBASAUR                                       //
//                                                                            //
////////////////////////////////////////////////////////////////////////////////
static void test_get_next_evolution(void) {
    printf("\n>> Testing get_next_evolution\n");

    printf("    ... Creating a new Pokedex\n");
    Pokedex pokedex = new_pokedex();

    printf("    ... Creating Bulbasaur and Ivysaur\n");
    Pokemon bulbasaur = create_bulbasaur();
    Pokemon ivysaur = create_ivysaur();
    

    printf("    ... Adding Bulbasaur and Ivysaur to the Pokedex\n");
    add_pokemon(pokedex, bulbasaur);
    add_pokemon(pokedex, ivysaur);
    

    printf("       --> Checking the evolution of Bulbasaur\n");
    assert(get_next_evolution(pokedex) == DOES_NOT_EVOLVE);

    printf("    ... Adding the evolution from Bulbasaur to Ivysaur\n");
    add_pokemon_evolution(pokedex, 1, 2);

    printf("       --> Checking the evolution of Bulbasaur\n");
    assert(get_next_evolution(pokedex) == 2);

    printf("    ... Destroying the Pokedex\n");
    destroy_pokedex(pokedex);

    printf(">> Passed get_next_evolution tests!\n");
}

//                                 STAGE 5
//////////////////////////TEST_GET_POKEMON_OF_TYPE//////////////////////////////
//                                                                            //
// THIS TEST CHECK WHETHER GET_POKEMON_OF_TYPE FUNCTION WORKS CORRECTLY       //
//                                                                            //
// IT DOES THIS BY CREATING FIVE POKEMONS: BULBASAUR, IVYSAUR, JIGGLYPUFF, MEW//
// AND ESPEON THEN ADD ALL OF THEM INTO THE POKEDEX                           //
//                                                                            //
// CHECK CURRENT POKEMON IS BULBASAUR AND SET EACH POKEMON TO BE FOUND        //
// THEN GET POKEMON FOR GRASS_TYPE AND PSYCHIC_TYPE                           //
//                                                                            //
// AFTER THAT CHECK WHETHER THE NUMBER OF POKEMON IN EACH TYPE IS CORRECT     //
//                                                                            //
////////////////////////////////////////////////////////////////////////////////
static void test_get_pokemon_of_type(void) {
    printf("\n>> Testing get_pokemon_of_type\n");

    printf("    ... Creating a new Pokedex\n");
    Pokedex pokedex = new_pokedex();

    printf("    ... Creating Bulbasaur and Ivysaur\n");
    Pokemon bulbasaur = create_bulbasaur();
    Pokemon ivysaur = create_ivysaur();
    Pokemon jigglypuff = create_jigglypuff();
    Pokemon mew = create_mew();
    Pokemon espeon = create_espeon();

    printf("    ... Adding Bulbasaur, Ivysaur, Jigglypuff and mew to the Pokedex\n");
    add_pokemon(pokedex, bulbasaur);
    add_pokemon(pokedex, ivysaur);
    add_pokemon(pokedex, jigglypuff);
    add_pokemon(pokedex, mew);
    add_pokemon(pokedex, espeon);
    printf("       --> Checking that the current Pokemon is Bulbasaur\n");
    assert(get_current_pokemon(pokedex) == bulbasaur);

    printf("    ... Setting Bulbasaur to be found\n");
    find_current_pokemon(pokedex);

    printf("    ... Setting Ivysaur to be found\n");
    next_pokemon(pokedex);
    find_current_pokemon(pokedex);

    printf("    ... Setting Jigglypuff to be found\n");
    next_pokemon(pokedex);
    find_current_pokemon(pokedex);

    printf("    ... Setting Mew to be found\n");
    next_pokemon(pokedex);
    find_current_pokemon(pokedex);

    printf("    ... Setting Espeon to be found\n");
    next_pokemon(pokedex);
    find_current_pokemon(pokedex);
    
    printf("    ... Getting all type psychic Pokemon\n");
    Pokedex psychic_pokedex = get_pokemon_of_type(pokedex, PSYCHIC_TYPE);

    printf("       --> Checking the correct Pokemon were copied and returned\n");
    assert(count_total_pokemon(psychic_pokedex) == 2);
    assert(count_found_pokemon(psychic_pokedex) == 2);
    assert(is_copied_pokemon(get_current_pokemon(psychic_pokedex), mew));

    printf("    ... Getting all type grass Pokemon\n");
    Pokedex grass_pokedex = get_pokemon_of_type(pokedex, GRASS_TYPE);

    printf("       --> Checking the correct Pokemon were copied and returned\n");
    assert(count_total_pokemon(grass_pokedex) == 2);
    assert(count_found_pokemon(grass_pokedex) == 2);
    assert(is_copied_pokemon(get_current_pokemon(grass_pokedex), bulbasaur));

    printf("    ... Destroying the Pokedexes\n");
    destroy_pokedex(pokedex);
    destroy_pokedex(psychic_pokedex);
    destroy_pokedex(grass_pokedex);

    printf(">> Passed get_pokemon_of_type tests!\n");
}

//////////////////////////////TEST_SEARCH_POKEMON//////////////////////////////
//                                                                           //
// THIS TEST CHECKS WHETHER SEARCH_POKEMON FUNCTION WORKS CORRECTLY          //
//                                                                           //
// IT DOES THIS BY CREATING THREE POKEMONS: BULBASAUR, IVYSAUR , JIGGLYPUFF  //
// AND MEW AND ADD THEM INTO POKEDEX                                         //
//                                                                           //
// THEN SEARCH "SAUR" AND CHECK THE NUMBER OF POKEMON WHOSE NAME             //
// CONTAIN THIS TEXT IS 2                                                    //
//                                                                           //
///////////////////////////////////////////////////////////////////////////////
static void test_search_pokemon(void) {
    printf("\n>> Testing get_pokemon_of_type\n");

        printf("    ... Creating a new Pokedex\n");
        Pokedex pokedex = new_pokedex();

        printf("    ... Creating Bulbasaur, Ivysaur, Jigglypuff and Mew\n");
        Pokemon bulbasaur = create_bulbasaur();
        Pokemon ivysaur = create_ivysaur();
        Pokemon jigglypuff = create_jigglypuff();
        Pokemon mew = create_mew();

        printf("    ... Adding Bulbasaur, Ivysaur, Jigglypuff and mew"
                 "to the Pokedex\n");
        add_pokemon(pokedex, bulbasaur);
        add_pokemon(pokedex, ivysaur);
        add_pokemon(pokedex, jigglypuff);
        add_pokemon(pokedex, mew);

        printf("       --> Checking that the current Pokemon is Bulbasaur\n");
        assert(get_current_pokemon(pokedex) == bulbasaur);

        printf("    ... Setting Ivysaur to be found\n");
        find_current_pokemon(pokedex);

        printf("    ... Setting Bulbasaur to be found\n");
        next_pokemon(pokedex);
        find_current_pokemon(pokedex);

        printf("    ... Setting Jigglypuff to be found\n");
        next_pokemon(pokedex);
        find_current_pokemon(pokedex);

        printf("    ... Setting Mew to be found\n");
        next_pokemon(pokedex);
        find_current_pokemon(pokedex);

        Pokedex search_pokedex = search_pokemon(pokedex, "saur");

        printf("  --> Checking the correct Pokemon were copied and returned\n");
        assert(count_total_pokemon(search_pokedex) == 2);
        assert(count_found_pokemon(search_pokedex) == 2);
        assert(is_copied_pokemon(get_current_pokemon(search_pokedex),
        bulbasaur));

        printf("    ... Destroying both Pokedexes\n");
        destroy_pokedex(pokedex);
        destroy_pokedex(search_pokedex);

        printf(">> Passed get_pokemon_of_type tests!\n");
}

////////////////////////////////////////////////////////////////////////
//                     Helper Functions                               //
////////////////////////////////////////////////////////////////////////

// Helper function to create Bulbasaur for testing purposes.
static Pokemon create_bulbasaur(void) {
    Pokemon pokemon = new_pokemon(
            BULBASAUR_ID, BULBASAUR_NAME,
            BULBASAUR_HEIGHT, BULBASAUR_WEIGHT,
            BULBASAUR_FIRST_TYPE,
            BULBASAUR_SECOND_TYPE
    );
    return pokemon;
}

// Helper function to create Ivysaur for testing purposes.
static Pokemon create_ivysaur(void) {
    Pokemon pokemon = new_pokemon(
            IVYSAUR_ID, IVYSAUR_NAME,
            IVYSAUR_HEIGHT, IVYSAUR_WEIGHT,
            IVYSAUR_FIRST_TYPE,
            IVYSAUR_SECOND_TYPE
    );
    return pokemon;
}



// Helper function to compare whether two Pokemon are the same.
// This checks that the two pointers contain the same address, i.e.
// they are both pointing to the same pokemon struct in memory.
//
// Pokemon ivysaur = new_pokemon(0, 'ivysaur', 1.0, 13.0, GRASS_TYPE, POISON_TYPE)
// Pokemon also_ivysaur = ivysaur
// is_same_pokemon(ivysaur, also_ivysaur) == TRUE
static int is_same_pokemon(Pokemon first, Pokemon second) {
    return first == second;
}

// Helper function to compare whether one Pokemon is a *copy* of
// another, based on whether their attributes match (e.g. pokemon_id,
// height, weight, etc).
// 
// It also checks that the pointers do *not* match -- i.e. that the
// pointers aren't both pointing to the same pokemon struct in memory.
// If the pointers both contain the same address, then the second
// Pokemon is not a *copy* of the first Pokemon.
// 
// This function doesn't (yet) check that the Pokemon's names match
// (but perhaps you could add that check yourself...).

static int is_copied_pokemon(Pokemon first, Pokemon second) {
    return (pokemon_id(first) == pokemon_id(second))
    &&  (first != second)
    &&  (pokemon_height(first) == pokemon_height(second))
    &&  (pokemon_weight(first) == pokemon_weight(second))
    &&  (pokemon_first_type(first) == pokemon_first_type(second))
    &&  (pokemon_second_type(first) == pokemon_second_type(second));
}

// HELPER FUNCTION TO CREATE JIGGLYPUFF FOT TESTING PURPOSES
static Pokemon create_jigglypuff(void) {
    Pokemon pokemon = new_pokemon(
            JIGGLYPUFF_ID, JIGGLYPUFF_NAME,
            JIGGLYPUFF_HEIGHT, JIGGLYPUFF_WEIGHT,
            JIGGLYPUFF_FIRST_TYPE,
            JIGGLYPUFF_SECOND_TYPE
    );
    return pokemon;
}

// HELPER FUNCTION TO CREATE MEW FOT TESTING PURPOSES
static Pokemon create_mew(void) {
    Pokemon pokemon = new_pokemon(
            MEW_ID, MEW_NAME,
            MEW_HEIGHT, MEW_WEIGHT,
            MEW_FIRST_TYPE,
            MEW_SECOND_TYPE
    );
    return pokemon;
}

// HELPER FUNCTION TO CREATE ESPEON FOT TESTING PURPOSES
static Pokemon create_espeon(void) {
    Pokemon pokemon = new_pokemon(
            ESPEON_ID, ESPEON_NAME,
            ESPEON_HEIGHT, ESPEON_WEIGHT,
            ESPEON_FIRST_TYPE,
            ESPEON_SECOND_TYPE
    );
    return pokemon;
}


