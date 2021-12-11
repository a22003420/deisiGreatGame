package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Exception
 */
public class AbyssBlueScreenDeath extends Abyss
{
    //################
    //Constructor
    //################

    protected AbyssBlueScreenDeath(int id, String title)
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

    @Override
    protected String getImagePng() {
        return "bsod.png";
    }

    /*
    @Override
    protected void applyEffects(Programmer programmer) {
        //loses game
        if(!programmer.isLocked()){
            programmer.setLocked();
            programmer.gameOver();
        }

    }
    */
}
