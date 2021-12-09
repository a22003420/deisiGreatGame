package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import java.util.ArrayList;

/*
Represents a tool
Includes abysses where tool applies
 */
public abstract class Tool extends ToolAbyss
{
    //###########
    //ATTRIBUTES
    //###########

    // Estão no pai ToolAbyss (title, id)

    //################
    //Constructor
    //################

    public Tool(int id, String title,String image, String description) {
        super(id,title,image,description);
    }

    //################
    //Methods
    //################


    @Override
    protected void applyEffects(Programmer programmer) {
        programmer.addTool(this); //passa o objecto para dentro do addTool
    }
}
