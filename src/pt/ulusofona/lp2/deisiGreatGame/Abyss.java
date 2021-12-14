package pt.ulusofona.lp2.deisiGreatGame;
//imports
import java.util.List;

/*
Represents a board tile of type Abstract Abyss
 */
public abstract class Abyss extends Tile
{
    //###########
    //ATTRIBUTES

    /*Abyss type id*/
    protected final int id;

    /*Abyss title*/
    protected final String title;

    /*Abyss image*/
    protected final String image;

    //################
    //Constructor

    public Abyss(int id, String title, String image)
    {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    //################
    //Factory

    /*
    Factory for creating abyss of a given type
     */
    public static Abyss createAbyss(int abyssTypeId)
    {
        return switch (abyssTypeId) {
            case 0 -> new AbyssSyntax(abyssTypeId, "Erro de sintaxe", "syntax.png");
            case 1 -> new AbyssLogic(abyssTypeId, "Erro de lógica", "logic.png");
            case 2 -> new AbyssException(abyssTypeId, "Exception", "exception.png");
            case 3 -> new AbyssFileNotFound(abyssTypeId, "File Not Found Exception", "file-not-found-exception.png");
            case 4 -> new AbyssCrash(abyssTypeId, "Crash (aka Rebentanço)", "crash.png");
            case 5 -> new AbyssDuplicatedCode(abyssTypeId, "Duplicated Code", "duplicated-code.png");
            case 6 -> new AbyssSecondaryEffects(abyssTypeId, "Efeitos secundários", "secondary-effects.png");
            case 7 -> new AbyssBlueScreenDeath(abyssTypeId, "Blue Screen of Death", "bsod.png");
            case 8 -> new AbyssInfiniteCycle(abyssTypeId, "Ciclo infinito", "infinite-loop.png");
            case 9 -> new AbyssSegmFault(abyssTypeId, "Segmentation Fault", "core-dumped.png");
            default -> null;
        };
    }

    //################
    //Abstract Methods

    /*
    Return Abyss Title
     */
    @Override
    abstract protected String getTitle();

    /*
    Return Abyss Image
     */
    @Override
    abstract protected String getImagePng();

    /*
    React to Abyss
    */
    @Override
    abstract protected String reactToAbyssOrTool(List<Programmer> programmers, Programmer programmer, int boardSize);

    //################
    //Other Methods

    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Abyss abyss = (Abyss) o;
        return id == abyss.id;
    }

    @Override
    public int hashCode(){
        return id;
    }
}
