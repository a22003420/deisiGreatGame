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

    protected AbyssSegmFault(int id, String title)
    {
        super(id,title);
    }

    //################
    //Methods
    //################

    @Override
    protected String getTitle() {
        return null;
    }

    @Override
    protected int reactToAbyssOrTool() {
        return 0;
    }

    /*
    Return image
     */
    @Override
    protected String getImagePng() {
        return "null";
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
    protected void applyEffects(Programmer programmer) {
        //se tiver mais que uma pessoa, ele anda 3 para trás houses neste caso
        //go back 3 houses
        if (!programmer.isLocked()) {
            programmer.setLocked();
            programmer.setBoardPosition(programmer.getBoardPosition() - 3);
        }
    }
    */
}
