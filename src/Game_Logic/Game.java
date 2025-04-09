package Game_Logic;

import Game_Parts.Card;
import Game_Parts.Deck;
import Game_Parts.Hand;
import Types.Color;
import Types.Value;

import java.util.Scanner;

public class Game 
{
    Scanner kb = new Scanner(System.in);
    int num_players;
    int player_turn = 0;
    Hand hands[];
    Card topCard;
    Deck deck;
    Boolean plusTwoPlayed = false;
    Boolean plusFourPlayed = false;
    int direction = 1;

    /*
     * Game constructor. Initializes the number of players, and starts the main
     * game loop.
     */
    public Game()
    {
       num_players = playerSetup();
       hands = new Hand[num_players];

       for(int i = 0; i < num_players; i++)
       {
            hands[i] = new Hand();
       }

       deck = new Deck(); // intialize deck
       
       gameLoop(); // start game 
    }    

    /*
     * 
     */
    public int playerSetup()
    {
        System.out.println("How many players? Two minimum, four max.");

        while(true)
        {
            int num_of_players = this.kb.nextInt();

            if(num_of_players < 2 || num_of_players > 4)
            {
                System.out.println("Invalid input. Please try again.");
            }
            else
            {
                return num_of_players;
            }
        }
    }

    public void updateTopCard()
    {
        topCard = deck.peekDiscard();
    }

    public Boolean is_Valid(Card card)
    {   
        if(card.value == topCard.value || card.color == topCard.color)
        {
            return true;
        }
        else if(card.value == Value.COLORSWITCH || card.color == Color.WILD)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Boolean handIsValid(Hand player_hand)
    {
        Boolean anyIsInvalid = false;

        for(int i = 0; i < hands[player_turn].hand.size(); i++)
        {
            if(is_Valid(player_hand.hand.get(i)))
            {
                anyIsInvalid = anyIsInvalid | true;
            }
        }

        return anyIsInvalid;
    }

    public void gameLoop()
    {
        updateTopCard(); // Initialize top card

        while(true)
        {
            System.out.printf("Top card color: %s\n", topCard.color);
            System.out.printf("Top card type: %s\n", topCard.value);
            if(plusTwoPlayed)
            {
                //Draw two cards
                hands[player_turn].addCards(deck.drawMultipleCards(2));
                plusTwoPlayed = false;
            }
            else if(plusFourPlayed)
            {
                //Draw four cards
                hands[player_turn].addCards(deck.drawMultipleCards(4));
                plusFourPlayed = false;
            }

            System.out.printf("Player %d's turn\n", player_turn + 1);
            System.out.printf("Player %d's cards\n", player_turn + 1);

            while (!handIsValid(hands[player_turn]))
            {
                //Draw cards until a valid card can be played
                hands[player_turn].addCard(deck.drawCard());
            }

            for(int i = 0; i < hands[player_turn].hand.size(); i++)
            {
                System.out.printf("Card %d:\n Color: %s\n", i + 1, hands[player_turn].hand.get(i).color);
                System.out.printf(" Card Type: %s\n", hands[player_turn].hand.get(i).value);
            }

            System.out.println("What card do you want to play? Please select the number printed");
            int num_played = kb.nextInt() - 1;
            Boolean index_valid = num_played >= 0 && num_played < hands[player_turn].hand.size();
            Card card_played = hands[player_turn].hand.get(num_played);

            while(!index_valid || !is_Valid(card_played))
            {
                System.out.println("Invalid card. Please enter again.");
                num_played = kb.nextInt() - 1;
            }

            System.out.printf("Card played color: %s\n", card_played.color);
            System.out.printf("Card played value: %s\n", card_played.value);
            hands[player_turn].hand.remove(num_played);

            if(hands[player_turn].hand.size() == 0)
            {
                System.out.printf("Player %d wins!\n", player_turn);
                break;
            }

            if(card_played.value == Value.SKIP)
            {
                updateTopCard();
                player_turn = (player_turn + direction + hands.length + 1) % hands.length;
            }
            else if(card_played.value == Value.REVERSE)
            {
                updateTopCard();
                player_turn = (player_turn + direction + hands.length) % hands.length;
            }
            else if(card_played.value == Value.COLORSWITCH)
            {
                Boolean colorIsValid = false;

                System.out.println("Please choose a color. The options are blue, yellow, red, green");
                String colorChosen = kb.nextLine();
                colorChosen.toLowerCase();

                while(!colorIsValid)
                {
                    if (colorChosen.equals("blue") || colorChosen.equals("yellow") ||
                        colorChosen.equals("red") || colorChosen.equals("green"))
                    {
                        colorIsValid = true;
                    }
                    else
                    {
                        System.out.println("Invalid color. Please try again.");
                        colorChosen = kb.nextLine().toLowerCase(); // update and lowercase again
                    }
                }
                
                if(colorChosen.equals("blue"))
                {
                    updateTopCard();
                }
                else if(colorChosen.equals("yellow"))
                {
                    updateTopCard();
                }
                else if(colorChosen.equals("red"))
                {
                    updateTopCard();
                }
                else if(colorChosen.equals("green"))
                {
                    updateTopCard();
                }

                player_turn = (player_turn + direction + hands.length) % hands.length;
            }
            else if(card_played.value == Value.PLUSFOUR)
            {
                Boolean colorIsValid = false;

                System.out.println("Please choose a color. The options are blue, yellow, red, green");
                String colorChosen = kb.nextLine();
                colorChosen.toLowerCase();

                while(!colorIsValid)
                {
                    if (colorChosen.equals("blue") || colorChosen.equals("yellow") ||
                        colorChosen.equals("red") || colorChosen.equals("green"))
                    {
                        colorIsValid = true;
                    }
                    else
                    {
                        System.out.println("Invalid color. Please try again.");
                        colorChosen = kb.nextLine().toLowerCase(); // update and lowercase again
                    }
                }
                
                if(colorChosen.equals("blue"))
                {
                    updateTopCard();
                }
                else if(colorChosen.equals("yellow"))
                {
                    updateTopCard();
                }
                else if(colorChosen.equals("red"))
                {
                    updateTopCard();
                }
                else if(colorChosen.equals("green"))
                {
                    updateTopCard();
                }
                
                plusFourPlayed = true;

                player_turn = (player_turn + direction + hands.length) % hands.length;
            }
            else if(card_played.value == Value.PLUSTWO)
            {
                plusTwoPlayed = true;
                updateTopCard();
                player_turn = (player_turn + direction + hands.length) % hands.length;
            }
            else
            {
                updateTopCard();
                player_turn = (player_turn + direction + hands.length) % hands.length;
            }
        }
    }
}
