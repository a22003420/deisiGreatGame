package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents a board tile of type Abstract Abyss
 */
public abstract class Abyss extends Tile
{
    //###########
    //ATTRIBUTES
    //###########

    /*Abyss title*/
    protected String title;

    /*Abyss image*/
    protected String image;

    //################
    //Constructor
    //################

    public Abyss(String title, String image)
    {
        this.title = title;
        this.image = image;
    }

    //################
    //Factory
    //################

    /*
    Factory for creating abyss of a given type
     */
    public static Abyss createAbyss(int abyssTypeId)
    {
        switch (abyssTypeId)
        {
            case 0:
                return new AbyssSyntax("Erro de sintaxe","syntax.png");
            case 1:
                return new AbyssLogic("Erro de lógica", "logic.png");
            case 2:
                return new AbyssException("Exception", "exception.png");
            case 3:
                return new AbyssFileNotFound("File Not Found Exception", "file-not-found-exception.png");
            case 4:
                return new AbyssCrash("Crash (aka Rebentanço)", "crash.png");
            case 5:
                return new AbyssDuplicatedCode("Duplicated Code", "duplicated-code.png");
            case 6:
                return new AbyssSecondaryEffects("Efeitos secundários", "secondary-effects.png");
            case 7:
                return new AbyssBlueScreenDeath("Blue Screen of Death", "bsod.png");
            case 8:
                return new AbyssInfiniteCycle("Ciclo infinito", "infinite-loop.png");
            case 9:
                return new AbyssSegmFault("Segmentation Fault", "core-dumped.png");
        }

        //if returns null there is something wrong!!!
        return null;
    }

    //################
    //Abstract Methods
    //################

    /*
    Return Abyss Title
     */
    abstract protected String getTitle();

    /*
    Return Abyss Image
     */
    abstract protected String getImagePng();

    /*
    React to Abyss
    */
    abstract protected String reactToAbyssOrTool(Programmer programmer);


}
