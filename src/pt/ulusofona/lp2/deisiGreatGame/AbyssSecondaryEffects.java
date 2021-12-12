package pt.ulusofona.lp2.deisiGreatGame;

import java.awt.*;

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
    */
    @Override
    protected String reactToAbyssOrTool(Programmer programmer, int boardSize) {
        return title;

    }

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
