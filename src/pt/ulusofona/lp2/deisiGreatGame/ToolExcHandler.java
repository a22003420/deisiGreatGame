package pt.ulusofona.lp2.deisiGreatGame;
//imports
import java.util.List;

/*
Represents a Tool of type Exception Handler
 */
public class ToolExcHandler extends Tool
{
    //################
    //Constructor

    public ToolExcHandler(int id, String title, String image, List<Abyss> abysses) {
        super(id, title, image, abysses);
    }

    //################
    //Methods

    /*
    Return title to Tool Exception Handler
     */
    @Override
    protected String getTitle() {
        return this.title;
    }

    /*
    Return image to Tool Exception Handler
     */
    @Override
    protected String getImagePng() {
        return this.image;
    }

    /*
    React to Tool Exception Handler
     */
    @Override
    protected String reactToAbyssOrTool(List<Programmer> programmers, Programmer programmer, int boardSize)
    {
        return (programmer.addTool(this) ? "Sortudo!\nRecohi a Ferramenta: " : "Azar!\nJá tinha a Ferramenta: ") + this.title;
    }
}
