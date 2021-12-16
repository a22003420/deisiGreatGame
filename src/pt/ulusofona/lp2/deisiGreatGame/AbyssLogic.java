package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Error Logic
 */
public class AbyssLogic extends Abyss
{
    //################
    //Constructor
    //################
    public AbyssLogic(int id, String title,String image, String description,int position) {
        super(id,title,image,description,position);
    }

    //################
    //Methods
    //################

    @Override
    protected void applyEffects(Programmer programmer)
    {
        //go back (numero do dado\2) house
        if(!programmer.isLocked()){
            if(!programmer.checkTool(2) && !programmer.checkTool(5)){
                programmer.setLocked();
                int diceValue= programmer.getBoardPosition()- programmer.lastPosition();
                programmer.setBoardPosition(diceValue/2);
                programmer.setUnlocked();
            }else{
                if(programmer.checkTool(2)){
                    programmer.removeTool(2);
                }else if(programmer.checkTool(5)){
                    programmer.removeTool(5);
                }
            }
        }
    }
}
