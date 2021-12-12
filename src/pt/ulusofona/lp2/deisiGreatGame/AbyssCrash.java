package pt.ulusofona.lp2.deisiGreatGame;

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
    React to Abyss
    */
    @Override
    protected String reactToAbyssOrTool(Programmer programmer, int boardSize) {

        String result = programmer.ContainsToolForAbyss(this);
        int positions = programmer.getBoardPosition();
        String message = "";

        if(result!="")
        {
            programmer.move(boardSize, positions);
            message = "Azar!\nNão tinha uma Ferramenta!\nVou voltar ao início";
        }
        else
        {
            message = "Sortudo!\nTinha a Ferramenta!\nUsei e safei-me";
        }

        return message;

    }
}
