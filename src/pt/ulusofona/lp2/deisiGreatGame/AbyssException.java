package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Exception
 */
public class AbyssException extends Abyss
{
    //################
    //Constructor
    //################
    protected AbyssException(int id, String title, String image)
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
    protected void reactToAbyssOrTool(Programmer programmer) {
    }

    /*
    @Override
    protected void applyEffects(Programmer programmer) {
        //go back 2 houses
        if(!programmer.isLocked()){
            if(!programmer.checkTool(3) && !programmer.checkTool(5)){
                programmer.setLocked();
                programmer.setBoardPosition(programmer.getBoardPosition()-2);
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
