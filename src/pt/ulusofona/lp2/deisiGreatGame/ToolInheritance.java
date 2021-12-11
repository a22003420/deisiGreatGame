package pt.ulusofona.lp2.deisiGreatGame;
//imports
import java.util.List;

/*
Represents a Tool of type Inheritance
 */
public class ToolInheritance extends Tool{

    //################
    //Constructor
    //################

    public ToolInheritance(int id, String title, String image, List<Abyss> abysses) {
        super(id, title, image,abysses);
    }

    //################
    //Methods
    //################

    @Override
    protected String getTitle() {
        return this.title;
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
        return this.image;
    }
}
