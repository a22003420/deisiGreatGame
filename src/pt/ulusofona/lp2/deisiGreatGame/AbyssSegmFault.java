package pt.ulusofona.lp2.deisiGreatGame;
//imports
import java.util.ArrayList;
import java.util.List;

/*
Represents an Abyss of type Exception
 */
public class AbyssSegmFault extends Abyss
{
    //################
    //Constructor

    protected AbyssSegmFault(int id, String title, String image)
    {
        super(id, title, image);
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
    React to Abyss Segmentation Fault
    If two or more programmer are in current position, all go back 3 positions
     */
    @Override
    protected String reactToAbyssOrTool(List<Programmer> programmers, Programmer currProgrammer, int boardSize) {

        String result = currProgrammer.UseToolOnAbyss(this);
        String message;

        if (result.isBlank()) {
            //get programmer position
            int currProgrammerPosition = currProgrammer.currentPosition();

            //list to store found programmers
            List<Programmer> programmerList = new ArrayList<>();

            //check if other programmers are in the same position
            for (Programmer programmerCheck : programmers) {
                if (programmerCheck.currentPosition() == currProgrammerPosition) {
                    programmerList.add(currProgrammer);
                }
            }

            //retreat 3 position if two or more programmer are found on the same position
            if (programmerList.size() > 1) {
                for (Programmer programmerToMove : programmerList) {
                    programmerToMove.move(boardSize, -3);
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
