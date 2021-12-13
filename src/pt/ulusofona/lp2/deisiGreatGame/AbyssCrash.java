package pt.ulusofona.lp2.deisiGreatGame;

import java.util.List;

/*
Represents an Abyss of type Exception
 */
public class AbyssCrash extends Abyss
{
    //################
    //Constructor

    protected AbyssCrash(String title, String image)
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
    React to Abyss Crash
    If not contains required tool go back to start
    */
    @Override
    protected String reactToAbyssOrTool(List<Programmer> programmers, Programmer programmer, int boardSize) {

        String result = programmer.UseToolOnAbyss(this);
        String message;

        if(result.isBlank())
        {
            int currentPosition = programmer.currentPosition();
            int positionsToMove = 1 - currentPosition;
            programmer.move(boardSize, positionsToMove);
            message = "Azar!\nNão tinha uma Ferramenta\nVou retroceder para o início";
        }
        else
        {
            message = "Sortudo!\nTinha a Ferramenta: " + result + "\nUsei e safei-me";
        }

        return message;
    }
}
