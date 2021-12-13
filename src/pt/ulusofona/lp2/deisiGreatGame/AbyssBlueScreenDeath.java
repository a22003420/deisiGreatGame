package pt.ulusofona.lp2.deisiGreatGame;

import java.util.List;

/*
Represents an Abyss of type Exception
 */
public class AbyssBlueScreenDeath extends Abyss
{
    //################
    //Constructor

    protected AbyssBlueScreenDeath(String title, String image)
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
    React to Abyss
     */
    @Override
    protected String reactToAbyssOrTool(List<Programmer> programmers, Programmer programmer, int boardSize)
    {
        String result = programmer.ContainsToolForAbyss(this);
        String message = "";

        if(result.isBlank())
        {
            programmer.gameOver();
            message = "Já fui com os...\nMelhor sorte no próximo jogo";
        }
        else
        {
            message = "Sortudo!\nTinha a Ferramenta: " + title + "\nUsei e safei-me";
        }

        return message;
    }

}
