package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Exception
 */
public class AbyssBlueScreenOfDeath extends Abyss
{
    //################
    //Constructor
    //################

    protected AbyssBlueScreenOfDeath(int id, String title) {
        super(id, title);
    }

    //################
    //Methods
    //################

    @Override
    protected void applyPenalty(Programmer programmer) {
        //loses game

    }
}
