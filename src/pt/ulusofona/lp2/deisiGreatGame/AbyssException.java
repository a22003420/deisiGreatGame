package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Exception
 */
public class AbyssException extends Abyss
{
    //################
    //Constructor
    //################
    protected AbyssException(int id, String title)
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

    @Override
    protected String getImagePng() {
        return "exception.png";
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
