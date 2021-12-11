package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import java.util.ArrayList;
import java.util.List;

/*
Represents a tool
Includes abysses where tool applies
 */
public abstract class Tool
{
    //###########
    //ATTRIBUTES
    //###########

    /*Tool id */
    protected int id;

    /*Tool title*/
    protected String title;

    /*Tool Abysses*/
    protected List<Abyss> abysses;

    //################
    //Constructor
    //################

    public Tool(int id, String title, List<Abyss> abysses) {
        this.id = id;
        this.title = title;
        this.abysses = abysses;
    }

    //################
    //Methods
    //################

    /*
Get Title
 */
    abstract protected String getTitle();

    /*
    React to AbyssOrTool
    */
    abstract protected int reactToAbyssOrTool();

    /*
    Return image for Abyss
     */
    abstract protected String getImagePng();
}
