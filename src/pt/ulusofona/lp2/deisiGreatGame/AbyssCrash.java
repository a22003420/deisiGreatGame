package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Exception
 */
public class AbyssCrash extends Abyss
{
    //################
    //Constructor
    //################

    protected AbyssCrash(int id, String title,String image, String description,int position) {
        super(id,title,image,description,position);
    }

    //################
    //Methods
    //################

    @Override
    protected void applyEffects(Programmer programmer) {
        //go back to start (quadrado 1)
        if(!programmer.isLocked()){
            programmer.setLocked();
            programmer.setBoardPosition(1);
            programmer.setUnlocked();
        }
    }
}
