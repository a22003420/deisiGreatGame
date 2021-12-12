package pt.ulusofona.lp2.deisiGreatGame;

import java.util.List;

/*
Represents an Abyss of type Error Syntax
 */
public class AbyssSyntax extends Abyss
{
    //################
    //Constructor
    //################
    public AbyssSyntax(String title, String image)
    {
        super(title, image);
    }

    //################
    //Methods
    //################

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
    ???
     */
    @Override
    protected String reactToAbyssOrTool(Programmer programmer, int boardSize) {

        String result = programmer.ContainsToolForAbyss(this);
        String message = "";

        if(result!="")
        {
            programmer.move(boardSize, -1);
            message = "Azar!\nNão tinha uma Ferramenta!\nVou andar para trás uma casa";
        }
        else
        {
            message = "Sortudo!\nTinha a Ferramenta!\nUsei e safei-me";
        }

        return message;
    }
}
