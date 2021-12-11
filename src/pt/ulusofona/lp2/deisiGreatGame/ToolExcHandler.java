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
    //################

    public ToolExcHandler(int id, String title, List<Abyss> abysses) {
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

    @Override
    protected String getImagePng() {
        return "bsod.png";
    }
}
