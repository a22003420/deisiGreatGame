package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Error Syntax
 */
public class AbyssSyntax extends Abyss
{
    //################
    //Constructor
    //################
    public AbyssSyntax(int id, String title)
    {
        super(id,title);
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
        return "syntax.png";
    }
}
