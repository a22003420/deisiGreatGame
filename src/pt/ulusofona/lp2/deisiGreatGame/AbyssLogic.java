package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Error Logic
 */
public class AbyssLogic extends Abyss
{
    //################
    //Constructor
    //################
    public AbyssLogic(int id, String title)
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

    /*
    Return image
     */
    @Override
    protected String getImagePng() {
        return "logic.png";
    }

    /*
    @Override
    protected void applyEffects(Programmer programmer)
    {
        //go back (numero do dado\2) house
    }
     */
}
