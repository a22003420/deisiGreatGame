package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import java.util.ArrayList;

/*
Represents an Abyss
 */
public abstract class Abyss
{
    //###########
    //ATTRIBUTES
    //###########

    private int id;
    private String title;
    private ArrayList<Tool> tools;

    //################
    //Constructor
    //################

    public Abyss(int id, String title)
    {
        this.id = id;
        this.title = title;
    }

    public Abyss(int id, String title, ArrayList<Tool> tools)
    {
        this.id = id;
        this.title = title;
        this.tools = tools;
    }

    //################
    //Abstract Methods
    //################

    protected abstract void applyPenalty();
}
