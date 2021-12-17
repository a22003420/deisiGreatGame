package pt.ulusofona.lp2.deisiGreatGame;
//imports
import java.util.List;

/*
Represents an Abyss of type Exception
 */
public class AbyssFileNotFoundException extends Abyss
{
    //################
    //Constructor

    protected AbyssFileNotFoundException(int id, String title, String image)
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
    React to Abyss File Not Found
    If not contains required tool, goes back 3 position
    */
    @Override
    protected String reactToAbyssOrTool(List<Programmer> programmers, Programmer currProgrammer, int boardSize) {

        String result = currProgrammer.useToolOnAbyss(this);
        String message;

        if(result.isBlank())
        {
            currProgrammer.move(boardSize, -3);
            message = "Azar!\nNão tinha uma Ferramenta\nVou retroceder 3 casas";
        }
        else
        {
            message = "Sortudo!\nTinha a Ferramenta: " + result + "\nUsei e safei-me";
        }
        return message;
    }
}
