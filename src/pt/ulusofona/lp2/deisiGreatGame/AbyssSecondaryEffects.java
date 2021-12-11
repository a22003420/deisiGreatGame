package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Exception
 */
public class AbyssSecondaryEffects extends Abyss
{
    //################
    //Constructor
    //################

    protected AbyssSecondaryEffects(int id, String title, String image)
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
        //go back to previous position not tile (penultimo)
    }

 */
}
