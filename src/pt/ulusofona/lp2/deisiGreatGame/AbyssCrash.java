package pt.ulusofona.lp2.deisiGreatGame;
//imports
import java.util.List;

/*
Represents an Abyss of type Exception
 */
public class AbyssCrash extends Abyss
{
    //################
    //Constructor

    protected AbyssCrash(int id, String title, String image)
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
    React to Abyss Crash
    If not contains required tool go back to start
    */
    @Override
    protected String reactToAbyssOrTool(List<Programmer> programmers, Programmer currProgrammer, int boardSize) {

        String result = currProgrammer.UseToolOnAbyss(this);
        String message;

        if(result.isBlank())
        {
            int currentPosition = currProgrammer.currentPosition();
            int positionsToMove = currentPosition-1;
            currProgrammer.move(boardSize, -positionsToMove);
            message = "Azar!\nNão tinha uma Ferramenta\nVou retroceder para o início";
        }
        else
        {
            message = "Sortudo!\nTinha a Ferramenta: " + result + "\nUsei e safei-me";
        }

        return message;
    }
}
