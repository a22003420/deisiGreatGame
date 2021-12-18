package pt.ulusofona.lp2.deisiGreatGame;
//imports
import java.util.ArrayList;
import java.util.List;

/*
Represents an Abyss of type Exception
 */
public class AbyssSegmentationFault extends Abyss
{
    //################
    //Constructor

    protected AbyssSegmentationFault(int id, String title, String image)
    {
        super(id, title, image);
    }

    //################
    //Methods

    /*
    Return title for Abyss Segmentation Fault
     */
    @Override
    protected String getTitle() {
        return this.title;
    }

    /*
    Return image for Abyss Segmentation Fault
     */
    @Override
    protected String getImagePng() {
        return this.image;
    }

    /*
    React to Abyss Segmentation Fault
    If two or more programmer are in current position, all go back 3 positions
     */
    @Override
    protected String reactToAbyssOrTool(List<Programmer> programmersInPosition, Programmer currProgrammer, int boardSize) {

        //is current programmer responsibility to check if contains a tool and use the tool
        String result = currProgrammer.useToolOnAbyss(this);

        if (result.isBlank()) {

            //retreat 3 position if two or more programmer are found on the same position
            if (programmersInPosition.size() > 1) {
                for (Programmer playerToMove : programmersInPosition) {
                    playerToMove.move(boardSize, -3);
                }
                return  "Azar!\nNão tinha uma Ferramenta\nVou recuar 3 casas";
            }
            else {
                return  "Sortudo!\nNão estava ninguém\nSafei-me";
            }
        }

        return "Sortudo!\nTinha a Ferramenta: " + result + "\nUsei e safei-me!";
    }
}
