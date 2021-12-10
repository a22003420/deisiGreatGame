package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Exception
 */
public class AbyssDuplicatedCode extends Abyss
{
    //################
    //Constructor
    //################
    protected AbyssDuplicatedCode(int id, String title,String image, String description)
    {
        super(id,title,image,description);
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
            }else{
                programmer.removeTool(0);
            }
        }
    }
}
