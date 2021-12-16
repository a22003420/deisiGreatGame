package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Error Syntax
 */
public class AbyssSyntax extends Abyss
{
    //################
    //Constructor
    //################
    public AbyssSyntax(int id, String title,String image, String description,int position) {
        super(id,title,image,description,position);
    }

    //################
    //Methods
    //################

    @Override
    protected void applyEffects(Programmer programmer)
    {
        //go back 1 house
        if(!programmer.isLocked()){
            if(!programmer.checkTool(4) && !programmer.checkTool(5)){
                programmer.setLocked();
                programmer.setBoardPosition(programmer.getBoardPosition()-1);
                programmer.setUnlocked();
            }else{
                if(programmer.checkTool(4)){
                    programmer.removeTool(4);
                }else if(programmer.checkTool(5)){
                    programmer.removeTool(5);
                }
            }
        }


    }
}
