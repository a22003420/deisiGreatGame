package pt.ulusofona.lp2.deisiGreatGame;
//imports
import java.util.List;

/*
Represents an Abyss of type Error Syntax
 */
public class AbyssSyntax extends Abyss
{
    //################
    //Constructor

    public AbyssSyntax(int id, String title, String image)
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
    React to Abyss Syntax
    If not contains required tool go back 1 position
    */
    @Override
    protected String reactToAbyssOrTool(List<Programmer> programmersInPosition, Programmer currProgrammer, int boardSize) {

        //is current programmer responsibility to check if contains a tool and use the tool
        String result = currProgrammer.useToolOnAbyss(this);

        if(result.isBlank())
        {
            currProgrammer.move(boardSize, -1);
            return  "Azar!\nNão tinha uma Ferramenta\nVou retroceder 1 casa";
        }

        return  "Sortudo!\nTinha a Ferramenta: " + result + "\nUsei e safei-me!";
    }
}
