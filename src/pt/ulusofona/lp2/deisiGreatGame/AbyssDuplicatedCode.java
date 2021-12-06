package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Exception
 */
public class AbyssDuplicatedCode extends Abyss
{
    //################
    //Constructor
    //################
    protected AbyssDuplicatedCode(int id, String title) {
        super(id, title);
    }

    //################
    //Methods
    //################

    @Override
    protected void applyPenalty(Programmer programmer) {
        //go back to previous tile
    }
}
