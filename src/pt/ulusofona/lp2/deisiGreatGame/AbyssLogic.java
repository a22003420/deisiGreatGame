package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Error Logic
 */
public class AbyssLogic extends Abyss
{
    //################
    //Constructor
    //################
    public AbyssLogic(int id, String title) {
        super(id, title);
    }

    //################
    //Methods
    //################

    @Override
    protected void applyPenalty(Programmer programmer)
    {
        //go back (positions\2) house
    }
}
