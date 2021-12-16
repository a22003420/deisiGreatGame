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
