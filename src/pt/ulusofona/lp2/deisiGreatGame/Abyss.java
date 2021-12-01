package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss
 */
public abstract class Abyss
{
    private int id;
    private String title;

    //################
    //Constructor
    //################

    protected Abyss(int id, String title)
    {
        this.id = id;
        this.title = title;
    }

    //################
    //Public Methods
    //################

    protected abstract void applyBehaviour();
}
