package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Exception
 */
public class AbyssFileNotFound extends Abyss
{
    //################
    //Constructor
    //################
    protected AbyssFileNotFound(int id, String title)
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
        return "file-not-found-exception.png";
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
