package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Exception
 */
public class AbyssFileNotFoundException extends Abyss
{
    //################
    //Constructor
    //################
    protected AbyssFileNotFoundException(int id, String title,String image, String description,int position) {
        super(id,title,image,description,position);
    }

    //################
    //Methods
    //################

    @Override
    protected void applyEffects(Programmer programmer) {
        //go back 3 houses
        if(!programmer.isLocked()){
            if(!programmer.checkTool(3) && !programmer.checkTool(5)){
                programmer.setLocked();
                programmer.setBoardPosition(programmer.getBoardPosition()-3);
                programmer.setUnlocked();
            }else{
                if(programmer.checkTool(3)){
                    programmer.removeTool(3);
                }else if(programmer.checkTool(5)){
                    programmer.removeTool(5);
                }
            }
        }
    }
}
