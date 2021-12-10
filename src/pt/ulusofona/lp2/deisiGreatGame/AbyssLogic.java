package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Error Logic
 */
public class AbyssLogic extends Abyss
{
    //################
    //Constructor
    //################
    public AbyssLogic(int id, String title,String image, String description)
    {
        super(id,title,image,description);
    }

    //################
    //Methods
    //################

    @Override
    protected void applyEffects(Programmer programmer)
    {
        //go back (numero do dado\2) house
    }
}
