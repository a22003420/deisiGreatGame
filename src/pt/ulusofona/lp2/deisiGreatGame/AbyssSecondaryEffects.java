package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Exception
 */
public class AbyssSecondaryEffects extends Abyss
{
    //################
    //Constructor
    //################
    protected AbyssSecondaryEffects(int id, String title,String image, String description)
    {
        super(id,title,image,description);
    }

    //################
    //Methods
    //################

    @Override
    protected void applyEffects(Programmer programmer) {
        //go back to previous position not tile (penultimo)
    }
}
