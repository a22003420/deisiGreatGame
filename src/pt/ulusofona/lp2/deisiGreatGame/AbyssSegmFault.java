package pt.ulusofona.lp2.deisiGreatGame;

import java.util.List;

/*
Represents an Abyss of type Exception
 */
public class AbyssSegmFault extends Abyss
{
    //################
    //Constructor
    //################

    protected AbyssSegmFault(String title, String image)
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

    @Override
    protected String reactToAbyssOrTool(Programmer programmer, int boardSize) {
        return title;

    }

    /*
    @Override
    public void executeEffects(List<Programmer> programmers){
        if(programmers.size()>1){
            for (Programmer programmer: programmers) {
                applyEffects(programmer);
            }
        }
    }

    @Override
    protected void reactToAbyssOrTool(Programmer programmer) {
        //se tiver mais que uma pessoa, ele anda 3 para trás houses neste caso
        //go back 3 houses
        if (!programmer.isLocked()) {
            programmer.lock();
            programmer.setBoardPosition(programmer.getBoardPosition() - 3);
        }
    }*/

}
