package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents a board title empty
 */
public class Empty extends Tile {

    //###########
    //ATTRIBUTES
    //###########

    /*Abyss title*/
    protected final String title;

    /*Abyss image*/
    protected final String image;

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
    @Override
    protected String getTitle() {
        return this.title;
    }

    /*
    Return image for Empty
     */
    @Override
    protected String getImagePng() {
        return this.image;
    }

    /*
    React to AbyssOrTool
    */
    @Override
    protected String reactToAbyssOrTool(Programmer programmer, int boardSize){
        return null;
    }
}
