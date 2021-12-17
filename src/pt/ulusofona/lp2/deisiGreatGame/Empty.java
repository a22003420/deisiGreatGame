package pt.ulusofona.lp2.deisiGreatGame;
//imports
import java.util.List;

/*
Represents a board title empty
 */
public class Empty extends Tile {

    //###########
    //ATTRIBUTES

    /*Empty title*/
    protected final String title;

    /*Empty image*/
    protected final String image;

    //################
    //Constructor

    public Empty(String title, String image)
    {
        this.title = title;
        this.image = image;
    }

    //################
    //Methods

    /*
    Get Title
     */
    @Override
    protected String getTitle() {
        return null;
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
    protected String reactToAbyssOrTool(List<Programmer> programmers, Programmer programmer, int boardSize){
        return null;
    }
}
