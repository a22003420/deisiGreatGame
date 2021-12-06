package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Exception
 */
public class AbyssSegmentationFault extends Abyss
{
    //################
    //Constructor
    //################
    protected AbyssSegmentationFault(int id, String title) {
        super(id, title);
    }

    //################
    //Methods
    //################

    @Override
    protected void applyPenalty(Programmer programmer) {

    }
}
