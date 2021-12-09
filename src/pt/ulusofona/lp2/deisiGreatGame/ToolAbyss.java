package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import java.util.ArrayList;
import java.util.List;

/*
Represents a tool
Includes abysses where tool applies
 */
public abstract class ToolAbyss
{
    //###########
    //ATTRIBUTES
    //###########

    /* Tool id */
    private int id;

    /*Tool title*/
    private String title;

    /*image*/
    private String image;

    /*show text*/
    private String description;



    //################
    //Constructor
    //################

    public ToolAbyss(int id, String title,String image,String description) {
        this.id =id;
        this.title = title;
        this.image =image;
        this.description=description;
    }

    //################
    //Methods
    //################


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return title;
    }

    protected abstract void applyEffects(Programmer programmer);

    public void executeEffects(List<Programmer> programmers){
        for (Programmer programmer: programmers) {
            applyEffects(programmer);
        }
    }

}
