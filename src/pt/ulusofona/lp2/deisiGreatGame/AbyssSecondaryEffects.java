package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Exception
 */
public class AbyssSecondaryEffects extends Abyss
{
    //################
    //Constructor
    //################
    protected AbyssSecondaryEffects(int id, String title,String image, String description,int position) {
        super(id,title,image,description,position);
    }

    //################
    //Methods
    //################

    @Override
    protected void applyEffects(Programmer programmer) {
        //go back to previous-1 position not tile (penultimo)
        {
            //go back to previous position
            if(!programmer.isLocked()){
                if(!programmer.checkTool(1)){
                    programmer.setLocked();
                    programmer.setBoardPosition(programmer.lastPosition2());
                    programmer.setUnlocked();
                }else{
                    programmer.removeTool(1);
                }
            }
        }
    }
}
