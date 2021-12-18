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

    public AbyssLogic(int id, String title, String image)
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
    React to Abyss Logic
    If not contains required tool go back to previous position
    */
    @Override
    protected String reactToAbyssOrTool(List<Programmer> programmers, Programmer currProgrammer, int boardSize) {

        //is current programmer responsibility to check if contains a tool and use the tool
        String result = currProgrammer.useToolOnAbyss(this);

        if(result.isBlank())
        {
            int currentPosition = currProgrammer.currentPosition();
            int previousPosition = currProgrammer.previousPosition();
            int movedPositions = currentPosition - previousPosition;
            int positionsToMove = movedPositions / 2;
            currProgrammer.move(boardSize, -positionsToMove);
            if(positionsToMove==0) {
                return  "Sortudo!\nNão tinha uma Ferramenta\nMas não preciso retroceder";
            }

            return  "Azar!\nNão tinha uma Ferramenta\nVou retroceder " + positionsToMove;
        }

        return "Sortudo!\nTinha a Ferramenta: " + result + "\nUsei e safei-me!";
    }
}
