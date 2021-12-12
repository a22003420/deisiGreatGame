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

    /*Abyss image*/
    protected String image;

    //################
    //Constructor
    //################

    public Abyss(int id, String title, String image)
    {
        this.id = id;
        this.title = title;
        this.image = image;
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
                return new AbyssSyntax(abyssType, "Erro de sintaxe","syntax.png");
            case 1:
                return new AbyssLogic(abyssType, "Erro de lógica", "logic.png");
            case 2:
                return new AbyssException(abyssType, "Exception", "exception.png");
            case 3:
                return new AbyssFileNotFound(abyssType, "File " +
                        "Not Found Exception", "file-not-found-exception.png");
            case 4:
                return new AbyssCrash(abyssType, "Crash (aka Rebentanço)", "crash.png");
            case 5:
                return new AbyssDuplicatedCode(abyssType, "Duplicated Code", "duplicated-code.png");
            case 6:
                return new AbyssSecondaryEffects(abyssType, "Efeitos secundários", "secondary-effects.png");
            case 7:
                return new AbyssBlueScreenDeath(abyssType, "Blue Screen of Death", "bsod.png");
            case 8:
                return new AbyssInfiniteCycle(abyssType, "Ciclo infinito", "infinite-loop.png");
            case 9:
                return new AbyssSegmFault(abyssType, "Segmentation Fault", "core-dumped.jpg");
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
    abstract protected void reactToAbyssOrTool(Programmer programmer);

    /*
    Return image for Abyss
     */
    abstract protected String getImagePng();
}
