package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Exception
 */
public class AbyssBlueScreenOfDeath extends Abyss
{
    //################
    //Constructor
    //################

    protected AbyssBlueScreenOfDeath(int id, String title,String image, String description)
    {
        super(id,title,image,description);
    }

    //################
    //Methods
    //################

    @Override
    protected void applyEffects(Programmer programmer) {
        //loses game

    }
}
