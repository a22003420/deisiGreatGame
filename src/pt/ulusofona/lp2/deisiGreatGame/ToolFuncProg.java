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

    public ToolFuncProg(int id, String title, List<Abyss> abysses) {
        super(id, title, abysses);
    }

    //################
    //Methods
    //################

    @Override
    protected String getTitle() {
        return null;
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
        return "bsod.png";
    }
}
