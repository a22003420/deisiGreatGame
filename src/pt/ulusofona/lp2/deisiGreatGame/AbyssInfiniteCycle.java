package pt.ulusofona.lp2.deisiGreatGame;

import java.util.List;

/*
Represents an Abyss of type Exception
 */
public class AbyssInfiniteCycle extends Abyss
{
    //################
    //Constructor
    //################
    protected AbyssInfiniteCycle(String title, String image)
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

    /*@Override
    public void executeEffects(List<Programmer> programmers){
        if(programmers.size()>1){
        }

        if(programmers.size()>1){
            for (Programmer programmer: programmers) {
                applyEffects(programmer);
            }
        }
    }*/

    @Override
    protected String reactToAbyssOrTool(Programmer programmer) {
        return title;

    }

    /*
    @Override
    protected void applyEffects(Programmer programmer) {
        // fica preso na casa até que alguém o substitua/venha salvar;
        // Não pode ter ferramentas nem estar locked;
        if (!programmer.isLocked()) {
            if (!programmer.checkTool(0)) {
                programmer.setLocked();
                programmer.setBoardPosition(programmer.getBoardPosition());
            }

        }

    }

 */
}
