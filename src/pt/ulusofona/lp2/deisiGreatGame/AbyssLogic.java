package pt.ulusofona.lp2.deisiGreatGame;
//imports
import java.util.List;

/*
Represents an Abyss of type Error Logic
 */
public class AbyssLogic extends Abyss
{
    //################
    //Constructor

    public AbyssLogic(String title, String image)
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
    React to Abyss Logic
    If not contains required tool go back to previous position
    */
    @Override
    protected String reactToAbyssOrTool(List<Programmer> programmers, Programmer programmer, int boardSize) {

        String result = programmer.UseToolOnAbyss(this);
        String message;

        if(result.isBlank())
        {
            int currentPosition = programmer.currentPosition();
            int previousPosition = programmer.previousPosition();
            int movedPositions = currentPosition - previousPosition;
            int positionsToMove = movedPositions % 2;
            programmer.move(boardSize, positionsToMove);
            message = "Azar!\nNão tinha uma Ferramenta\nVou retroceder " + positionsToMove;
        }
        else{
            message = "Sortudo!\nTinha a Ferramenta: " + result + "\nUsei e safei-me";
        }

        return message;
    }
}
