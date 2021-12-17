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
    protected String reactToAbyssOrTool(List<Programmer> programmers, Programmer currProgrammer, int boardSize) {

        String result = currProgrammer.useToolOnAbyss(this);
        String message;

        if (result.isBlank()) {
            //get programmer position
            int currentPlayerPosition = currProgrammer.currentPosition();

            //list to store found programmers
            List<Programmer> playerToMoveList = new ArrayList<>();

            //check if other programmers are in the same position
            for (Programmer playerToCheck : programmers) {
                if (playerToCheck.currentPosition() == currentPlayerPosition) {
                    playerToMoveList.add(playerToCheck);
                }
            }

            //retreat 3 position if two or more programmer are found on the same position
            if (playerToMoveList.size() > 1) {
                for (Programmer playerToMove : playerToMoveList) {
                    playerToMove.move(boardSize, -3);
                }
                message = "Azar!\nNão tinha uma Ferramenta\nVou recuar 3 casas";
            }
            else {
                message = "Sortudo!\nNão estava ninguém\nSafei-me";
            }
        } else {
            message = "Sortudo!\nTinha a Ferramenta: " + result + "\nUsei e safei-me";
        }

        return message;
    }
}
