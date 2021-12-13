package pt.ulusofona.lp2.deisiGreatGame;

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
    React to Abyss Secondary Effects
    If not contains required tool go back to previous previous position
    */
    @Override
    protected String reactToAbyssOrTool(List<Programmer> programmers, Programmer programmer, int boardSize) {

        String result = programmer.UseToolOnAbyss(this);
        String message;

        if(result.isBlank())
        {
            int currentPosition = programmer.currentPosition();
            int previousPreviousPosition = programmer.previousPreviousPosition();
            int positionsToMove = currentPosition - previousPreviousPosition;
            programmer.move(boardSize, positionsToMove);
            message = "Azar!\nNão tinha uma Ferramenta\nVou retroceder " + positionsToMove;
        }
        else{
            message = "Sortudo!\nTinha a Ferramenta: " + result + "\nUsei e safei-me";
        }

        return message;
    }
}
