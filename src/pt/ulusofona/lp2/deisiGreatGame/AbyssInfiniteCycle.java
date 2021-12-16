package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;
import java.util.List;

/*
Represents an Abyss of type Exception
 */
public class AbyssInfiniteCycle extends Abyss
{

    //###########
    //ATTRIBUTES
    //###########

    /*
    Guarda o unico Jogador que fica preso neste abismo
     */

    Programmer trap;



    //################
    //Constructor
    //################
    protected AbyssInfiniteCycle(int id, String title,String image, String description,int position) {
        super(id,title,image,description,position);
    }

    //################
    //Methods
    //################


    @Override
    protected void applyEffects(Programmer programmer) {
        // fica preso na casa até que alguém o substitua/venha salvar;
        // Não pode ter ferramentas nem estar locked;
        if (!programmer.isLocked()) {
            if (!programmer.checkTool(1)) {
                programmer.setLocked();
              trap.setUnlocked();
              trap=programmer;
            }else{
                programmer.removeTool(1);
            }
        }
    }
}
