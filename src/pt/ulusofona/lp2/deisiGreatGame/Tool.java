package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import java.util.ArrayList;

/*
Represents a tool
Includes abysses where tool applies
 */
public abstract class Tool extends ToolAbyss
{
    //###########
    //ATTRIBUTES
    //###########

    // Estão no pai ToolAbyss (title, id)

    /* Abysses where Tool applies */
    private ArrayList<Abyss> abysses;

    //################
    //Constructor
    //################

    public Tool(int id, String title, ArrayList<Abyss> abysses,String image, String description) {
        super(id,title,image,description);
        this.abysses = abysses;
    }

    //################
    //Methods
    //################
}
