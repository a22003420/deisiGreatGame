package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Exception
 */
public class AbyssDuplicatedCode extends Abyss
{
    //################
    //Constructor
    //################
    protected AbyssDuplicatedCode(int id, String title,String image, String description,int position) {
        super(id,title,image,description,position);
    }

    //################
    //Methods
    //################

    @Override
    protected void applyEffects(Programmer programmer) {
        //go back to previous position
        if(!programmer.isLocked()){
            if(!programmer.checkTool(0)){
                programmer.setLocked();
                programmer.setBoardPosition(programmer.lastPosition());
                programmer.setUnlocked();
            }else{
                programmer.removeTool(0);
            }
        }
    }
}
