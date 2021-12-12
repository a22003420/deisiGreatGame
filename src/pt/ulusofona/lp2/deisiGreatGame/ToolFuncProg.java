package pt.ulusofona.lp2.deisiGreatGame;
//imports
import java.util.List;

/*
Represents a Tool of type Functional Programming
 */
public class ToolFuncProg extends Tool {

    //################
    //Constructor
    //################

    public ToolFuncProg(int id, String title, String image, List<Abyss> abysses) {
        super(id, title, image,abysses);
    }

    //################
    //Methods
    //################

    /*
    Return title
     */
    @Override
    protected String getTitle() {
        return this.title;
    }

    /*
    Return image
     */
    @Override
    protected String getImagePng() {
        return this.image;
    }


    @Override
    protected String reactToAbyssOrTool(Programmer programmer) {
        return this.title;
    }
}
