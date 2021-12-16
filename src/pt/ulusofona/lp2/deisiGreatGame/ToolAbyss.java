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
    protected int id;

    /*Tool title*/
    protected String title;

    /*image*/
    protected String image;

    /*show text*/
    protected String description;

    /*Guarda posição do ToolAbyss*/
    protected int position;



    //################
    //Constructor
    //################

    public ToolAbyss(int id, String title,String image,String description,int position) {
        this.id =id;
        this.title = title;
        this.image =image;
        this.description=description;
        this.position =position;
    }

    //################
    //Methods
    //################

    public int getPosition() {
        return position;
    }

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
