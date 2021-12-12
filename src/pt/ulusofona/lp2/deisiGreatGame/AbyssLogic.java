package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Error Logic
 */
public class AbyssLogic extends Abyss
{
    //################
    //Constructor
    //################
    public AbyssLogic(String title, String image)
    {
        super(title, image);
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
    protected String reactToAbyssOrTool(Programmer programmer, int boardSize) {
        return title;

    }

    /*
    @Override
    protected void applyEffects(Programmer programmer)
    {
        //go back (numero do dado\2) house
        if(!programmer.isLocked()){
            if(!programmer.checkTool(3) && !programmer.checkTool(5)){
                programmer.setLocked();
                programmer.setBoardPosition(programmer.throwDice()/2);
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
