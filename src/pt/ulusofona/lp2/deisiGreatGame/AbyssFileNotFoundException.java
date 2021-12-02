package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Exception
 */
public class AbyssFileNotFoundException extends Abyss
{
    protected AbyssFileNotFoundException(int id, String title) {
        super(id, title);
    }

    @Override
    protected void applyPenalty() {

    }
}
