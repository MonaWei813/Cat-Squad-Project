// ASSIGNMENT 1 19T1 COMP1511 COCO
//
// THIS PROGRAM IS WRITTEN BY MENGHUI WEI (z5082327)
// START FROM 26-03-2019
// THE CODE SHOULD BE SELF-EXPLAINED
// HOWEVER I HAVE ADDED NUMBERS OF COMMENTS
// WHICH IS TO EXPLIAN SOME OF THE STRATEGIES FOR PLAYING THE CARDS
// 
//

#include <stdio.h>
#include <assert.h>
#include <stdbool.h>

#define ACTION_PLAYER_NAME    0
#define ACTION_DISCARD        1
#define ACTION_PLAY_CARD      2

#define N_CARDS              40
#define N_CARDS_INITIAL_HAND 10
#define N_PLAYERS             4
#define N_CARDS_DISCARDED     3

#define CARD_MIN             10
#define CARD_MAX             49

// IF YOU NEED MORE #defines ADD THEM HERE
#define DOUGLAS              42
#define PRIME_SIZE           11

void print_player_name(void);
void choose_discards(void);
void choose_card_to_play(void);

// ADD PROTOTYPES FOR YOUR FUNCTIONS HERE
void scan_array(int a[], int size);
void print_array(int a[], int size);
void discard_cards(int a[], int size);
int is_prime(int n, int primes[]);
int have_prime(int a[], int size, int primes[]);
int have_douglas(int a[], int size);
int is_coco(int a, int b);

// DO NOT CHANGE THE MAIN FUNCTION

int main(void) {

    int which_action = 0;
    scanf("%d", &which_action);
    if (which_action == ACTION_PLAYER_NAME) {
        print_player_name();
    } else if (which_action == ACTION_DISCARD) {
        choose_discards();
    } else if (which_action == ACTION_PLAY_CARD) {
        choose_card_to_play();
    }

    return 0;
}

// 0-DISPLAY PLAYER NAME
void print_player_name(void) {

    // CHANGE THIS PRINTF TO YOUR DESIRED PLAYER NAME

    printf("Bill Cipher 101\n");

}

// 1-DISPPLAY THREE CARDS WHICH ARE CHOSEN TO
// GIVE TO THE PLAYER OF NEXT TABLE POSITION
// TO CHOOSE WHICH THREE CARDS TO BE DISCARD
// I WILL NEED TO INTRODUCE THE BASIC STRATEGY TO PLAY THIS GAME
// WHICH CAN MINIMIZE THE CHANCE THAT PLAYER MIGHT GET PENALTY POINT
// BASIC STRATEGY: AVOID WINNING ANY ROUND IN THIS GAME
// WINNING ROUND IS NOT ALWAYS A NEGATUVE THING, BUT IN ORDER TO SIMPLIFY
// THE STRATEGY, I CHOOSE TO AVOID WINNING ANY ROUND IF POSSIBLE
void choose_discards(void) {

   int cards_in_hand[N_CARDS_INITIAL_HAND];
   scan_array(cards_in_hand, N_CARDS_INITIAL_HAND);
   discard_cards(cards_in_hand, N_CARDS_INITIAL_HAND);
   printf("\n");

}

// 2-PLAY CARDS ACCORDING TO STRATEGIES
// FIRST WE NEED TO LET THE PLAYER MAKE LEGAL MOVE
// SINCE ILLEGAL MOVES CAN RESULT IN PENALTY POINTS
void choose_card_to_play(void) {

    int primes[] = {11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47};

    // ADD CODE TO READ THE FIRST THREE NUMBERS WHICH ARE:
    // NUMBER OF CARDS IN YOUR HAND,  NUMBER OF CARDS PLAYED THIS ROUND
    // TABLE POSITION
    int first_line_argument[3];
	scan_array(first_line_argument, 3);
	int number_cards_in_hands = first_line_argument[0];
	int number_of_cards_played = first_line_argument[1];
	int table_position = first_line_argument[2];

    // ADD CODE TO READ THE CARDS OF YOUR HAND INTO AN ARRAY
    // YOU WILL NEED TO USE A WHILE LOOP AND SCANF
	int cards_in_hand[number_cards_in_hands];
	scan_array(cards_in_hand, number_cards_in_hands);

    // ADD CODE TO READ THE CARDS PREVIOUSLY PLAYED THIS ROUND INTO AN ARRAY
    // YOU WILL NEED TO USE A WHILE LOOP AND SCANF
	int cards_played[number_of_cards_played + 1];
	scan_array(cards_played, number_of_cards_played);

    // ADD CODE TO READ THE CARDS PLAYED IN THE PREVIOUS ROUNDS INTO AN ARRAY
    // YOU WILL NEED TO USE A WHILE LOOP AND SCANF
    int number_of_cards_played_previous = N_PLAYERS * (N_CARDS_INITIAL_HAND
        - number_cards_in_hands);
    int cards_played_previous_rounds[number_of_cards_played_previous + 1];
	scan_array(cards_played_previous_rounds, number_of_cards_played_previous);

    // THE CARD THAT BEEN GIVE OUT(DISCARDED CARDS)
    int discard_cards[N_CARDS_DISCARDED];
    scan_array(discard_cards, N_CARDS_DISCARDED);

    // CARDS RECEIVED
    int cards_received[N_CARDS_DISCARDED];
	scan_array(cards_received, N_CARDS_DISCARDED);
    // THOUGH I HAVE SOME STRATEGIES HERE
    // I AM NOT ABLE TO IMPLEMENT ALL OF THEM INTO THE GAME
    // HERE IS THE LOGIC CONTROL PART
   	// THE STRATEGIES FOR PLAYING CARD IS DIFFERENT
    // WITH DIFFERENT PLAYING ORDER
    // SINCE THE PRIME CANNONT BE THE FIRST CARD TO BE PLAYED IN A ROUND
    // IF THERE IS NO PRIME CARD BEEN PLAYED IN PREVIOUSE ROUND
    // THE STRATEGY FOR PEOPLE WHO NEED TO PLAY THE FIRST CARD IN A ROUND
    // WILL HAVE TO CONSIDER TO AVOID ILLEGAL MOVE
    // AND MEANWHILE AVOID GETTING THE PENALTY POINT BY WINNING THE ROUND
    //
    // (FOR SIMPLICITY, NUMBER MEANS THE NUMBER ON THE CARD)
    ///////////////////////////////////////////////////////////////////////////
    //
    // IF PLAYING FIRST CARD:
    //                  DID PRIME APPEARS IN PREVIOUS ROUNDS?
    //                       |                        |
    //                      YES                       NO
    //                       |                        |
    //               PLAY THE SMALLEST              PLAY SMALLEST
    //               PRIME                          NON PRIME CARD
    //                       |
    //                      ELSE
    //                       |
    //               PLAY THE SMALLEST CARD
    ///////////////////////////////////////////////////////////////////////////


    if (number_of_cards_played == 0) {
        // IF THE PRIME CARD IS ALLOWED TO BE PALYED IN THIS ROUND
        if (have_prime(cards_played_previous_rounds,
            number_of_cards_played_previous, primes)) {

            printf("%d\n", cards_in_hand[0]);

		} else {

            int i;
            for (i = 0; i < number_cards_in_hands; i++ ) {

                if (!is_prime(cards_in_hand[i], primes)) {

                    printf("%d\n", cards_in_hand[i]);
                    return;
                }
		    }

            printf("%d\n", cards_in_hand[0]);

        }
    }

    ///////////////////////////////////////////////////////////////////////////
    //
    // IF PLAYING SECOND, THIRD OR LAST CARD:
    //
    //                      IS THE FIRST CARD PRIME?
    //                      |                     |
    //                     YES                    NO
    //                      |                     |
    //       DO YOU HAVE PRIME CARD?             DO YOU HAVE COCO?
    //       |                     |              |             |
    //      YES                    NO            YES ---------- NO
    //       |                     |                    |
    // PLAY THE LARGEST       DO YOU HAVE 42?  (TO BE SPECIFIED UNDER [COCO])
    ///PRIME SMALLER           |          |
    // THAN THE LARGEST       YES         NO
    // OF PLAYED CARDS         |           |
    //      |              PLAY 42     PLAY LARGEST
    //     ELSE                          CARD IN HAND
    //      |
    // PLAY SMALLEST PRIME IN HAND
    //
    ///////////////////////////////////////////////////////////////////////////
    //
    //                          DO YOU HAVE COCO?
    //                         |                |
    //                        YES               NO
    //                         |                |
    //            DO YOU HAVE 42?               DO YOU HAVE 42?
    //           |             |                |            |
    //           YES            NO              YES           NO
    //            |             |                |             |
    //  IS 42 SMALLER         PLAY LARGEST     PLAY 42      PLAY LARGEST
    //  THAN ANY OF           COCO SMALLER                  CARD IN HAND
    //  THE PLAYED CARD?      THAN THE LARGEST
    //   |       |            OF PLAYED CARD
    //  YES      NO                  |
    //   |        |                 ELSE
    //  PLAY    PLAY LARGEST         |
    //  42      COCO SMALLER        PLAY SMALLEST
    //          THAN THE LARGEST    COCO IN HAND
    //          OF PLAYED CARD
    //            |
    //          PLAY SMALLEST COCO IN HAND
    //
    ///////////////////////////////////////////////////////////////////////////

    else {
        // IF THE FIRST CARD IS PRIME
        if (is_prime(cards_played[0],primes)) {

            int j;
			for (j = 0; j < number_cards_in_hands; j++) {

                if (is_prime(cards_in_hand[j], primes)) {

                    printf("%d\n", cards_in_hand[j]);
					return;
					
				} else if (!have_prime(cards_in_hand, number_cards_in_hands,
                        primes)) {

                        int n = number_cards_in_hands - 1;
                        printf("%d\n", cards_in_hand[n]);
                        return;  
                                                       
		        } else if (have_douglas(cards_in_hand,
                    number_cards_in_hands)) {
                    
                    printf("%d\n", DOUGLAS);
                    return;
                    
                }
            }
        } else {
		// IF THE FIRST CARD IS NOT PRIME
            int k;
            for (k = 0; k < number_cards_in_hands; k++) {
				
				if (is_coco(cards_in_hand[k], cards_played[0])) {
				
					printf("%d\n", cards_in_hand[k]);
					return;
					
				}


            }

            printf("%d\n", cards_in_hand[number_cards_in_hands - 1]);
            
		}
		
		printf("\n");
		
	}



}


// ADD YOUR FUNCTIONS HERE

void scan_array(int a[], int size) {

    int i;
    for(i = 0; i < size; i++) {

        scanf("%d", &a[i]);

    }
}

void print_array(int a[], int size) {

    int i;
	for (i = 0; i < size; i++) {

        printf("%d", a[i]);

    }

}

void discard_cards(int a[], int size) {

    int i;
    // Since the cards in hand are stored in an array in ascending order
    // So just need to print out the cards in last three indices
    int first_choice = N_CARDS_INITIAL_HAND - N_CARDS_DISCARDED;
    for (i = first_choice; i < size; i++) {

        printf("%d ", a[i]);

    }

}



int is_prime(int n, int primes[]) {

    int i;
    for (i = 0; i < PRIME_SIZE; i++) {

        if (n == primes[i]) {

            return 1;

        }

    }

    return 0;
}

int have_prime(int a[], int size, int primes[]) {

    int i, j;
    for (i = 0; i < size; i++) {

        for (j = 0; j < PRIME_SIZE; j++) {

            if (a[i] == primes[j]) {

                return 1;

            }

        }

    }
    return 0;
}

int have_douglas(int a[], int size){

    int i;
    for (i = 0; i < size; i++) {

        if (a[i] == DOUGLAS) {

            return 1;
        }

    }
    return 0;
}

int is_coco(int a, int b) {

    int n = (a > b) ? b : a;
    int i;
    for (i = 2; i < n; i++) {

        if ((a % i == 0) && (b % i == 0)) {

            return 1;

        }
    }

    return 0;

}

