package pt.ulusofona.lp2.deisiGreatGame;
//imports
import java.util.List;

/*
Represents an Abyss of type Exception
 */
public class AbyssInfiniteCycle extends Abyss
{
    //################
    //Constructor

    protected AbyssInfiniteCycle(int id, String title, String image)
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
    React to Abyss Infinite Cycle
    If contains required tool nothing happens
    If other programmer is on the same position, unlock all others, and current programmer is locked
    */
    @Override
    protected String reactToAbyssOrTool(List<Programmer> programmers, Programmer currProgrammer, int boardSize) {

        String result = currProgrammer.UseToolOnAbyss(this);
        String message;

        if(result.isBlank())
        {
            message = "Azar!\nNão tinha uma Ferramenta\nFiquei bloqueado";

            //get programmer position
            int currProgrammerPosition = currProgrammer.currentPosition();

            //check if other programmers are in the same position
            //if found lock programmer
            for (Programmer programmerCheck: programmers){
                if(programmerCheck.currentPosition() == currProgrammerPosition){
                   programmerCheck.unlock();
                }
            }

            //lock current programmer
            currProgrammer.lock();
        }
        else
        {
            message = "Sortudo!\nTinha a Ferramenta: " + result + "\nUsei e safei-me";
        }

        return message;
    }
}
