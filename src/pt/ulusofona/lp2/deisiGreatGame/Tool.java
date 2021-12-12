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

    /*Tool image*/
    protected String image;

    /*Tool Abysses*/
    protected List<Abyss> abysses;

    //################
    //Constructor
    //################

    public Tool(int id, String title, String image, List<Abyss> abysses) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.abysses = abysses;
    }

    //################
    //Methods
    //################

    /*
    Return Title for Tile
    */
    abstract protected String getTitle();

    /*
    Return image for Tile
     */
    abstract protected String getImagePng();

    /*
    React to AbyssOrTool
    */
    abstract protected String reactToAbyssOrTool();
}
