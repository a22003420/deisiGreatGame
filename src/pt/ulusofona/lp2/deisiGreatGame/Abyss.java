package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import java.util.ArrayList;

/*
Represents an Abyss
 */
public abstract class Abyss extends ToolAbyss
{
    //###########
    //ATTRIBUTES
    //###########

    // Estão no pai ToolAbyss

    //################
    //Constructor
    //################

    public Abyss(int id, String title,String image, String description)
    {
        super(id,title,image,description);
    }

    //################
    //Abstract Methods
    //################

    protected abstract void applyEffects(Programmer programmer);


}
