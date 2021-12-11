package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents a board title empty
 */
public class Empty extends Tile {

    //###########
    //ATTRIBUTES
    //###########

    /* Empty id */
    protected int id;

    /* Empty title */
    protected String title;

    /* Empty image */
    protected String image;

    //################
    //Constructor
    //################

    public Empty(int id, String title){
        this.id = id;
        this.title = title;
        this.image = null;
    }

    //################
    //Abstract Methods
    //################

    /*
    Get Title
     */
    protected String getTitle() {
        return this.title;
    }

    /*
    Return image for Empty
     */
    protected String getImagePng() {
        return this.image;
    }

    /*
    React to AbyssOrTool
    */
    protected int reactToAbyssOrTool(){return 0;}
}
