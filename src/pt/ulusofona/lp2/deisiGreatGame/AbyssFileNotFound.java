package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Exception
 */
public class AbyssFileNotFound extends Abyss
{
    //################
    //Constructor
    //################
    protected AbyssFileNotFound(int id, String title, String image)
    {
        super(id,title, image);
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
    protected int reactToAbyssOrTool() {
        return 0;
    }

    /*
    @Override
    protected void applyEffects(Programmer programmer) {
        //go back 3 houses
        if(!programmer.isLocked()){
            if(!programmer.checkTool(3) && !programmer.checkTool(5)){
                programmer.setLocked();
                programmer.setBoardPosition(programmer.getBoardPosition()-3);
            }else{
                if(programmer.checkTool(3)){
                    programmer.removeTool(3);
                }else if(programmer.checkTool(5)){
                    programmer.removeTool(5);
                }
            }
        }
    }

     */
}
