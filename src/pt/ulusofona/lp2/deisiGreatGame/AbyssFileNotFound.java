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
    React to Abyss
    If not contains required tool go back to 1 position
    */
    @Override
    protected String reactToAbyssOrTool(List<Programmer> programmers, Programmer programmer, int boardSize) {

        String result = programmer.ContainsToolForAbyss(this);
        String message = "";

        if(result.isBlank())
        {
            programmer.move(boardSize, -1);
            message = "Azar!\nNão tinha uma Ferramenta\nVou andar para trás três casa";
        }
        else
        {
            message = "Sortudo!\nTinha a Ferramenta: " + title + "\nUsei e safei-me";
        }

        return message;

    }

    /*
    @Override
    protected void applyEffects(Programmer programmer) {
        //go back 3 houses
        if(!programmer.isLocked()){
            if(!programmer.checkTool(3) && !programmer.checkTool(5)){
                programmer.setLocked();
                programmer.setBoardPosition(programmer.getBoardPosition()-3);
            }else{
                if(programmer.checkTool(3)){
                    programmer.removeTool(3);
                }else if(programmer.checkTool(5)){
                    programmer.removeTool(5);
                }
            }
        }
    }

     */
}
