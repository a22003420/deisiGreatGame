package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Exception
 */
public class AbyssBlueScreenDeath extends Abyss
{
    //################
    //Constructor
    //################

    protected AbyssBlueScreenDeath(String title, String image)
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


    protected String reactToAbyssOrTool() {
        return title;

        /*
        //loses game
        if(!programmer.isLocked()){
            programmer.lock();
            programmer.gameOver();
        }
        */
    }

}
