package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Exception
 */
public class AbyssCrash extends Abyss
{
    //################
    //Constructor
    //################

    protected AbyssCrash(int id, String title) {
        super(id, title);
    }

    //################
    //Methods
    //################

    @Override
    protected void applyPenalty(Programmer programmer) {
        //go back to start (tile 1)
    }
}
