package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Exception
 */
public class AbyssFileNotFoundException extends Abyss
{
    //################
    //Constructor
    //################
    protected AbyssFileNotFoundException(int id, String title) {
        super(id, title);
    }

    //################
    //Methods
    //################

    @Override
    protected void applyPenalty(Programmer programmer) {
        //go back 3 houses
    }
}
