package Game_Parts;
import Types.Color;
import Types.ID;

public class Card 
{
    public Color color;
    public ID type;

    public Card(Color color, ID type)
    {
        this.color = color;
        this.type = type;
    }
}
