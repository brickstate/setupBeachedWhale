package Game_Parts;
 
import java.util.ArrayList;
import java.util.Collections;
 
import Types.Color;
import Types.Value;
 
public class Deck 
{
    private int cardNum = 108;
    private ArrayList<Card> deck;
    private ArrayList<Card> discard;
 
    public Deck() 
    {
        deck = new ArrayList<Card>(108);
        discard = new ArrayList<Card>(108); // empty at first
 
        // Add number cards
        for (Color color : Color.values()) 
        {
            if (color != Color.WILD) 
            {  // Skip WILD for number cards
                // Add one Zero card for each color
                deck.add(new Card(color, Value.ZERO));
                 
                // Add two of each number card 1-9 for each color
                for (Value id : new Value[] {Value.ONE, Value.TWO, Value.THREE, Value.FOUR, Value.FIVE, 
                    Value.SIX, Value.SEVEN, Value.EIGHT, Value.NINE}) 
                {
                    deck.add(new Card(color, id));
                    deck.add(new Card(color, id));
                }
                 
                 // Add two of each special colored card (SKIP, REVERSE, PLUSTWO)
                for (Value value : new Value[] {Value.SKIP, Value.REVERSE, Value.PLUSTWO}) 
                {
                    deck.add(new Card(color, value));
                    deck.add(new Card(color, value));
                }
            }
        }
         
        // Add wild cards (4 COLORSWITCH and 4 PLUSFOUR)
        for (int i = 0; i < 4; i++) 
        {
            deck.add(new Card(Color.WILD, Value.COLORSWITCH));
            deck.add(new Card(Color.WILD, Value.PLUSFOUR));
        }
 
        shuffle();

        // Draw a card from the pile and put in discard pile
        // This is the starting card
        discard.add(drawCard());
    }
 
    public Card drawCard() 
    {
        Card c = deck.remove(cardNum - 1);
        cardNum -= 1;
        return c;
    }

    public void playCard(Card c) {
        discard.add(c);
    }
 
    public void Card(Card c) 
    {
        discard.add(c);
    }
 
    public int getNumOfCard() 
    {
        return deck.size();
    }
 
     public Card[] drawMultipleCards(int count) 
    {
        Card[] s = new Card[count];
        for (int i = 0; i < count; i ++) 
        {
            s[i] = drawCard();
        }
        return s;
    }
 
    public void resetDeck()
    {
        if (isEmpty()) 
        {
            deck = discard;
        }
        else 
        {
            System.out.println("ERROR: Can not reset deck because deck is not empty!");
            System.out.println("Deck Size: " + deck.size());
        }
    }
 
     /*
      * gets the top card of the deck 
      */
    public Card peekDeck() 
    {
        return deck.get(cardNum - 1);
    }
 
     /*
      * gets the top card of the discard pile
      */
    public Card peekDiscard() 
    {
        return discard.get(discard.size() - 1);
    }
 
    public boolean isEmpty() 
    {
        return deck.size() == 0;
    }
 
    public void shuffle() 
    {
        Collections.shuffle(deck);
    }
 
    public void printDeck() 
    {
        for (Card c : deck) 
        {
            System.out.println("Color: " + c.color + "\t" + "Value: " + c.value);
        }
    }
 
    public void printDiscard() 
    {
        for (Card c : discard) 
        {
            System.out.println("Color: " + c.color + "\t" + "Value: " + c.value);
        }
    }
 }