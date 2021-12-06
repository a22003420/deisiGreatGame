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

    //################
    //Constructor
    //################

    public Abyss(int id, String title)
    {
        this.id = id;
        this.title = title;
    }

    //################
    //Abstract Methods
    //################

    protected abstract void applyPenalty(Programmer programmer);
}
