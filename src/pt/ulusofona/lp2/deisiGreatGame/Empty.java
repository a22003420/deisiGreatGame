package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents a board title empty
 */
public class Empty extends Tile {

    //###########
    //ATTRIBUTES
    //###########

    /*Abyss title*/
    protected String title;

    /*Abyss image*/
    protected String image;

    //################
    //Constructor
    //################

    public Empty(String title, String image)
    {
        this.title = title;
        this.image = image;
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
    Get Message
     */
    protected String showMessage() {
        return "aaaaaa";
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
    protected String reactToAbyssOrTool(){
        return null;
    }
}
