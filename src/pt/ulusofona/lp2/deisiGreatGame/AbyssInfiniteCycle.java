package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Exception
 */
public class AbyssInfiniteCycle extends Abyss
{
    //################
    //Constructor
    //################
    protected AbyssInfiniteCycle(int id, String title)
    {
        super(id,title);
    }

    //################
    //Methods
    //################

    @Override
    protected String getTitle() {
        return null;
    }

    @Override
    protected int reactToAbyssOrTool() {
        return 0;
    }

    /*
    Return image
     */
    @Override
    protected String getImagePng() {
        return "infinite-loop.png";
    }
/*
    @Override
    protected void applyEffects(Programmer programmer) {
        // fica preso na casa até que alguém o substitua/venha salvar;
        // Não pode ter ferramentas nem estar locked;

    }

 */
}
