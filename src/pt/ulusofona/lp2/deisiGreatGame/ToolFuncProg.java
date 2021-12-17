package pt.ulusofona.lp2.deisiGreatGame;
//imports
import java.util.List;

/*
Represents a Tool of type Functional Programming
 */
public class ToolFuncProg extends Tool {

    //################
    //Constructor

    public ToolFuncProg(int id, String title, String image, List<Abyss> abysses) {
        super(id, title, image,abysses);
    }

    //################
    //Methods

    /*
    Return title Tool Functional Programming
     */
    @Override
    protected String getTitle() {
        return this.title;
    }

    /*
    Return image Tool Functional Programming
     */
    @Override
    protected String getImagePng() {
        return this.image;
    }

    /*
    React to Tool Functional Programming
     */
    @Override
    protected String reactToAbyssOrTool(List<Programmer> programmers, Programmer currProgrammer, int boardSize) {
        return (currProgrammer.addTool(this) ? "Sortudo!\nRecohi a Ferramenta: "
                : "Azar!\nJá tinha a Ferramenta: ") + this.title;
    }
}
