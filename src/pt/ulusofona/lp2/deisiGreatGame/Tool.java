package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import java.util.ArrayList;

/*
Represents a Tool
Enables to skip penalty for set abysses
 */
public class Tool
{
    //###########
    //ATTRIBUTES
    //###########

    private int id;
    private String title;

    /*
    Abysses where tool applies
     */
    //private ArrayList<Abyss> abysses;

    //################
    //Constructor
    //################

    public Tool(int id, String title) {
        this.id =id;
        this.title = title;
    }

    /*public Tool(int id, String title, ArrayList<Abyss> abysses) {
        this.id =id;
        this.title = title;
        this.abysses = abysses;
    }*/
}
