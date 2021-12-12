package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Error Syntax
 */
public class AbyssSyntax extends Abyss
{
    //################
    //Constructor
    //################
    public AbyssSyntax(String title, String image)
    {
        super(title, image);
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

    /*
    ???
     */
    @Override
    protected String reactToAbyssOrTool() {
        return title;

    }
}
