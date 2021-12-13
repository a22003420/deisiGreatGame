package pt.ulusofona.lp2.deisiGreatGame;

import java.awt.*;
import java.util.List;

/*
Represents an Abyss of type Exception
 */
public class AbyssSecondaryEffects extends Abyss
{
    //################
    //Constructor

    protected AbyssSecondaryEffects(String title, String image)
    {
        super(title, image);
    }

    //################
    //Methods

    /*
    Return title
     */
    @Override
    protected String getTitle() {
        return this.title;
    }

    /*
    Return image
     */
    @Override
    protected String getImagePng() {
        return this.image;
    }

    /*
    React to Abyss
    If not contains required tool go back to previous position
    */
    @Override
    protected String reactToAbyssOrTool(List<Programmer> programmers, Programmer programmer, int boardSize) {

        String result = programmer.ContainsToolForAbyss(this);
        String message = "";

        if(result.isBlank())
        {

        }
        else
        {
            message = "Sortudo!\nTinha a Ferramenta: " + title + "\nUsei e safei-me";
        }

        return message;
/*
    protected void reactToAbyssOrTool(Programmer programmer) {
        //go back to previous-1 position not tile (penultimo)
        {
            //go back to previous position
            if(!programmer.isLocked()){
                if(!programmer.ContainsTool()){
                    programmer.lock();
                    programmer.move();
                }else{
                    programmer.removeTool();
                }
            }
        }
    }

 */
    }
}
