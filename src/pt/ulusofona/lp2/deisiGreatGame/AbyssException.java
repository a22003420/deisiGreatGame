package pt.ulusofona.lp2.deisiGreatGame;
//imports
import java.util.List;

/*
Represents an Abyss of type Exception
 */
public class AbyssException extends Abyss
{
    //################
    //Constructor

    protected AbyssException(int id, String title, String image)
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
    React to Abyss Exception
    If not contains required tool go back 2 positions
    */
    @Override
    protected String reactToAbyssOrTool(List<Programmer> programmers, Programmer currProgrammer, int boardSize) {

        //is current programmer responsibility to check if contains a tool and use the tool
        String result = currProgrammer.useToolOnAbyss(this);

        if(result.isBlank())
        {
            currProgrammer.move(boardSize, -2);
            return "Azar!\nNão tinha uma Ferramenta\nVou retroceder duas casa";
        }

        return  "Sortudo!\nTinha a Ferramenta: " + result + "\nUsei e safei-me";
    }
}
