package pt.ulusofona.lp2.deisiGreatGame;
//imports
import java.util.List;

/*
Represents an Abyss of type Blue Screen of Death
 */
public class AbyssBlueScreenDeath extends Abyss
{
    //################
    //Constructor

    protected AbyssBlueScreenDeath(int id, String title, String image)
    {
        super(id, title, image);
    }

    //################
    //Methods

    /*
    Return title to Abyss Blue Screen of Death
     */
    @Override
    protected String getTitle() {
        return this.title;
    }

    /*
    Return image to Abyss Blue Screen of Death
     */
    @Override
    protected String getImagePng() {
        return this.image;
    }

    /*
    React to Abyss Blue Screen of Death
     */
    @Override
    protected String reactToAbyssOrTool(List<Programmer> programmers, Programmer currProgrammer, int boardSize)
    {
        String result = currProgrammer.UseToolOnAbyss(this);
        String message;

        if(result.isBlank())
        {
            currProgrammer.gameOver();
            message = "Já fui com os...\nMelhor sorte no próximo jogo";
        }
        else
        {
            message = "Sortudo!\nTinha a Ferramenta: " + result + "\nUsei e safei-me";
        }

        return message;
    }
}
