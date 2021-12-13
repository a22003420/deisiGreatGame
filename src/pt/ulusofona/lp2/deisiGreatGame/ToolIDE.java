package pt.ulusofona.lp2.deisiGreatGame;
//imports
import java.util.List;

/*
Represents a Tool of type IDE
 */
public class ToolIDE extends Tool {

    //################
    //Constructor

    public ToolIDE(int id, String title, String image, List<Abyss> abysses) {
        super(id, title, image,abysses);
    }

    //################
    //Methods

    /*
    Return title Tool IDE
     */
    @Override
    protected String getTitle() {
        return this.title;
    }

    /*
    Return image Tool IDE
     */
    @Override
    protected String getImagePng() {
        return this.image;
    }

    /*
    React to Tool IDE
     */
    @Override
    protected String reactToAbyssOrTool(List<Programmer> programmers, Programmer programmer, int boardSize) {
        return (programmer.addTool(this) ? "Sortudo!\nRecohi a Ferramenta: " : "Azar!\nJá tinha a Ferramenta: ") + this.title;
    }
}
