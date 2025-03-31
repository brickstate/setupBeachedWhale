package Game_Logic;

import Game_Parts.Card;
import Game_Parts.Hand;


import java.util.Scanner;

public class Game 
{
    Scanner kb = new Scanner(System.in);
    int num_players;
    int player_turn = 0;
    Hand hands[];


    public Game()
    {
       num_players = player_setup();
       hands = new Hand[num_players];

       for(int i = 0; i < num_players; i++)
       {
            hands[i] = new Hand();
       }

        game_loop();
    }    

    public int player_setup()
    {
        System.out.println("How many player? Two minimum, four max.");

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

    public void game_loop()
    {
        while(true)
        {
            System.out.printf("Player %d's turn\n", player_turn + 1);
            System.out.printf("Player %d's cards\n", player_turn + 1);
            for(int i = 0; i < hands[player_turn].hand.size(); i++)
            {
                System.out.printf("Card %d:\n Color: %s\n", i + 1, hands[player_turn].hand.get(i).color);
                System.out.printf("Card Type: %s\n", hands[player_turn].hand.get(i).type);
            }
            System.out.println("What card do you want to play? Please select the number printed");
            int card_played = kb.nextInt();

            player_turn = (player_turn + 1) % hands.length;
        }
    }
}
