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

    protected AbyssDuplicatedCode(int id, String title, String image)
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
    React to Abyss Duplicated Code
    If not contains required tool go back to previous position
    */
    @Override
    protected String reactToAbyssOrTool(List<Programmer> programmersInPosition, Programmer currProgrammer, int boardSize) {

        //is current programmer responsibility to check if contains a tool and use the tool
        String result = currProgrammer.useToolOnAbyss(this);

        if(result.isBlank())
        {
            int previousPosition = currProgrammer.previousTurnPosition(1);
            int currentPosition = currProgrammer.currentPosition();
            int positionsToMove = currentPosition - previousPosition;
            currProgrammer.move(boardSize, previousPosition<currentPosition ? -positionsToMove : positionsToMove);
            return "Azar!\nNão tinha uma Ferramenta\nVou regresar à posição anterior";
        }

        return  "Sortudo!\nTinha a Ferramenta: " + result + "\nUsei e safei-me!";
    }
}
