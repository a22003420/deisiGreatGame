package pt.ulusofona.lp2.deisiGreatGame;
//imports
import java.util.List;

/*
Represents an Abyss of type Exception
 */
public class AbyssSecondaryEffects extends Abyss
{
    //################
    //Constructor

    protected AbyssSecondaryEffects(int id, String title, String image)
    {
        super(id, title, image);
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
    protected String reactToAbyssOrTool(List<Programmer> programmers, Programmer currProgrammer, int boardSize) {

        String result = currProgrammer.UseToolOnAbyss(this);
        String message;

        if(result.isBlank())
        {
            int currentPosition = currProgrammer.currentPosition();
            int previousPreviousPosition = currProgrammer.previousPreviousPosition();
            int positionsToMove = currentPosition - previousPreviousPosition;
            currProgrammer.move(boardSize, -positionsToMove);
            message = "Azar!\nNão tinha uma Ferramenta\nVou retroceder " + positionsToMove;
        }
        else{
            message = "Sortudo!\nTinha a Ferramenta: " + result + "\nUsei e safei-me";
        }

        return message;
    }
}
