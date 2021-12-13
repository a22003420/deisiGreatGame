package pt.ulusofona.lp2.deisiGreatGame;

import java.util.List;

/*
Represents an Abyss of type Exception
 */
public class AbyssFileNotFound extends Abyss
{
    //################
    //Constructor

    protected AbyssFileNotFound(String title, String image)
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
    React to Abyss File Not Found
    If not contains required tool, goes back 3 position
    */
    @Override
    protected String reactToAbyssOrTool(List<Programmer> programmers, Programmer programmer, int boardSize) {

        String result = programmer.UseToolOnAbyss(this);
        String message;

        if(result.isBlank())
        {
            programmer.move(boardSize, -3);
            message = "Azar!\nNão tinha uma Ferramenta\nVou retroceder 3 casas";
        }
        else
        {
            message = "Sortudo!\nTinha a Ferramenta: " + result + "\nUsei e safei-me";
        }
        return message;
    }
}
