package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Exception
 */
public class AbyssBlueScreenDeath extends Abyss
{
    //################
    //Constructor
    //################

    protected AbyssBlueScreenDeath(int id, String title, String image)
    {
        super(id,title, image);
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
    protected int reactToAbyssOrTool() {
        return 0;
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
