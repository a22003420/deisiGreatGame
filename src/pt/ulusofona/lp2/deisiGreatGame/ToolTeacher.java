package pt.ulusofona.lp2.deisiGreatGame;
//imports
import java.util.List;

/*
Represents a Tool of type Teacher Help
 */
public class ToolTeacher extends Tool {

    //################
    //Constructor

    public ToolTeacher(int id, String title, String image, List<Abyss> abysses) {
        super(id, title, image,abysses);
    }

    //################
    //Methods

    /*
    Return title Tool Teacher Help
     */
    @Override
    protected String getTitle() {
        return this.title;
    }

    /*
    Return image Tool Teacher Help
     */
    @Override
    protected String getImagePng() {
        return this.image;
    }

    /*
    React to Tool Teacher Help
     */
    @Override
    protected String reactToAbyssOrTool(List<Programmer> programmers, Programmer currProgrammer, int boardSize) {
        //is current programmer responsibility to check if contains a tool and add to tools
        return (currProgrammer.addTool(this) ? "Sortudo!\nRecohi a Ferramenta: " : "Azar!\nJá tinha a Ferramenta: ") + this.title;
    }
}
