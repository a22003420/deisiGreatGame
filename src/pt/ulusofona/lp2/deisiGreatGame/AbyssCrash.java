package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Exception
 */
public class AbyssCrash extends Abyss
{
    //################
    //Constructor
    //################

    protected AbyssCrash(String title, String image)
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


    @Override
    protected String reactToAbyssOrTool(Programmer programmer) {
        return title;

    }
}
