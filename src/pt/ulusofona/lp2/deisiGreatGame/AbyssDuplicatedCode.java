package pt.ulusofona.lp2.deisiGreatGame;
//imports
import java.util.List;

/*
Represents an Abyss of type Exception
 */
public class AbyssDuplicatedCode extends Abyss
{
    //################
    //Constructor

    protected AbyssDuplicatedCode(String title, String image)
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
    React to Abyss Duplicated Code
    If not contains required tool go back to previous position
    */
    @Override
    protected String reactToAbyssOrTool(List<Programmer> programmers, Programmer programmer, int boardSize) {

        String result = programmer.UseToolOnAbyss(this);
        String message;

        if(result.isBlank())
        {
            int previousPosition = programmer.previousPreviousPosition();
            int currentPosition = programmer.currentPosition();
            int positionsToMove = currentPosition - previousPosition;
            programmer.move(boardSize, positionsToMove);
            message = "Azar!\nNão tinha uma Ferramenta\nVou regresar à posição anterior";
        }
        else
        {
            message = "Sortudo!\nTinha a Ferramenta: " + result + "\nUsei e safei-me";
        }

        return message;
    }
}
