package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Exception
 */
public class AbyssSecondaryEffects extends Abyss
{
    //################
    //Constructor
    //################

    protected AbyssSecondaryEffects(int id, String title)
    {
        super(id,title);
    }

    //################
    //Methods
    //################

    @Override
    protected String getTitle() {
        return null;
    }

    @Override
    protected int reactToAbyssOrTool() {
        return 0;
    }

    @Override
    protected String getImagePng() {
        return "secondary-effects.png";
    }
/*
    @Override
    protected void applyEffects(Programmer programmer) {
        //go back to previous position not tile (penultimo)
    }

 */
}
