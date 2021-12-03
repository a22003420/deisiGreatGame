package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Exception
 */
public class AbyssSecondaryEffects extends Abyss
{
    //################
    //Constructor
    //################
    protected AbyssSecondaryEffects(int id, String title) {
        super(id, title);
    }

    //################
    //Methods
    //################

    @Override
    protected void applyPenalty(Programmer programmer) {
        //go back to previous previous tile
    }
}
