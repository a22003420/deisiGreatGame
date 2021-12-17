package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Exception
 */
public class AbyssBlueScreenOfDeath extends Abyss
{
    //################
    //Constructor
    //################

    protected AbyssBlueScreenOfDeath(int id, String title,String image, String description,int position) {
        super(id,title,image,description,position);
    }

    //################
    //Methods
    //################

    @Override
    protected void applyEffects(Programmer programmer) {
        //loses game
        if(!programmer.isLocked()){
            programmer.setLocked();
            programmer.gameOver();
            programmer.setUnlocked();
        }

    }
}
