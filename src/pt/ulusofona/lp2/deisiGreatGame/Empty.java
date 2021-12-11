package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents a board title empty
 */
public class Empty extends Tile {

    //###########
    //ATTRIBUTES
    //###########

    /* Abyss id */
    protected int id;

    /*Abyss title*/
    protected String title;

    //################
    //Constructor
    //################

    public Empty(int id, String title)
    {
        this.id = id;
        this.title = title;
    }

    //################
    //Abstract Methods
    //################

    /*
    Get Title
     */
    protected String getTitle(){return "";}

    /*
    React to AbyssOrTool
    */
    protected int reactToAbyssOrTool(){return 0;}

    /*
    Return image for Empty
     */
    protected String getImagePng() {
        return null;
    }

}
