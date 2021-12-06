package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import java.util.ArrayList;

/*
Represents a tool
Includes abysses where tool applies
 */
public class Tool
{
    //###########
    //ATTRIBUTES
    //###########

    /* Tool id */
    private int id;

    /*Tool title*/
    private String title;

    /* Abysses where Tool applies */
    private ArrayList<Abyss> abysses;

    //################
    //Constructor
    //################

    public Tool(int id, String title, ArrayList<Abyss> abysses) {
        this.id =id;
        this.title = title;
        this.abysses = abysses;
    }

    //################
    //Methods
    //################
}
