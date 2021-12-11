package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents a board tile of type Abstract Abyss
 */
public abstract class Abyss extends Tile
{
    //###########
    //ATTRIBUTES
    //###########

    /* Abyss id */
    protected int id;

    /*Abyss title*/
    protected String title;

    /*Abyss title*/
    protected String image;

    //################
    //Constructor
    //################

    public Abyss(int id, String title)
    {
        this.id = id;
        this.title = title;
    }

    //################
    //Factory
    //################

    /*
    Factory for Creating Type Abyss
     */
    public static Abyss createAbyss(int abyssType)
    {
        switch (abyssType)
        {
            case 0:
                return new AbyssSyntax(abyssType, "Erro de sintaxe");
            case 1:
                return new AbyssLogic(abyssType, "Erro de lógica");
            case 2:
                return new AbyssException(abyssType, "Exception");
            case 3:
                return new AbyssFileNotFound(abyssType, "File Not Found Exception");
            case 4:
                return new AbyssCrash(abyssType, "Crash (aka Rebentanço)");
            case 5:
                return new AbyssDuplicatedCode(abyssType, "Duplicated Code");
            case 6:
                return new AbyssSecondaryEffects(abyssType, "Efeitos secundários");
            case 7:
                return new AbyssBlueScreenDeath(abyssType, "Blue Screen of Death");
            case 8:
                return new AbyssInfiniteCycle(abyssType, "Ciclo infinito");
            case 9:
                return new AbyssSegmFault(abyssType, "Segmentation Fault");
        }

        //if returns null there is something wrong!!!
        return null;
    }

    //################
    //Abstract Methods
    //################

    /*
    Get Title
     */
    abstract protected String getTitle();

    /*
    React to AbyssOrTool
    */
    abstract protected int reactToAbyssOrTool();

    /*
    Return image for Abyss
     */
    abstract protected String getImagePng();
}
