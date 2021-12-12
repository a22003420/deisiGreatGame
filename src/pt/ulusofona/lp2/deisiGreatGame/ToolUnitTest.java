package pt.ulusofona.lp2.deisiGreatGame;
//imports
import java.util.List;

/*
Represents a Tool of type Unit Test
 */
public class ToolUnitTest extends Tool{

    //################
    //Constructor

    public ToolUnitTest(int id, String title, String image, List<Abyss> abysses) {
        super(id, title, image,abysses);
    }

    //################
    //Methods

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

    /*
    React to Tool
     */
    @Override
    protected String reactToAbyssOrTool(Programmer programmer, int boardSize) {
        return (programmer.addTool(this) ? "Sortudo!\nAdicionei a Ferramenta: " : "Azar!\nJá tinha a Ferramenta: ") + this.title;
    }
}
