package pt.ulusofona.lp2.deisiGreatGame;
//imports
import java.util.List;

/*
Represents an Abyss of type Blue Screen of Death
 */
public class AbyssBlueScreenOfDeath extends Abyss
{
    //################
    //Constructor

    protected AbyssBlueScreenOfDeath(int id, String title, String image)
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
        //is current programmer responsibility to check if contains a tool and use the tool
        String result = currProgrammer.useToolOnAbyss(this);

        if(result.isBlank())
        {
            currProgrammer.gameOver();
            return "Já fui com os...\nMelhor sorte no próximo jogo";
        }

        return "Sortudo!\nTinha a Ferramenta: " + result + "\nUsei e safei-me";
    }
}
