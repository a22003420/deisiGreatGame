package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import java.util.List;

/*
Represents a tool
Includes abysses where tool applies
 */
public abstract class Tool
{
    //###########
    //ATTRIBUTES

    /*Tool id */
    protected final int id;

    /*Tool title*/
    protected final String title;

    /*Tool image*/
    protected final String image;

    /*Tool Abysses*/
    protected final List<Abyss> abysses;

    //################
    //Constructor

    public Tool(int id, String title, String image, List<Abyss> abysses) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.abysses = abysses;
    }

    //################
    //Abstract Methods

    /*
    Return Title Tool
    */
    abstract protected String getTitle();

    /*
    Return image Tool
     */
    abstract protected String getImagePng();

    /*
    React to Tool
    */
    abstract protected String reactToAbyssOrTool(List<Programmer> programmers, Programmer currProgrammer, int boardSize);

    //################
    //Non Abstract Methods

    /*
    Check if Tool can be used for the given abyss
     */
    public Boolean checkUseOfTool(Abyss abyss){
        return this.abysses.contains(abyss);
    }

    /*
    Return Tool Type ID
     */
    public int getId(){
        return id;
    }
}
