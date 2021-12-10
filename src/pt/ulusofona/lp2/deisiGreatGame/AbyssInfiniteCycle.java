package pt.ulusofona.lp2.deisiGreatGame;

/*
Represents an Abyss of type Exception
 */
public class AbyssInfiniteCycle extends Abyss
{
    //################
    //Constructor
    //################
    protected AbyssInfiniteCycle(int id, String title,String image, String description)
    {
        super(id,title,image,description);
    }

    //################
    //Methods
    //################

    @Override
    protected void applyEffects(Programmer programmer) {
        // fica preso na casa até que alguém o substitua/venha salvar;
        // Não pode ter ferramentas nem estar locked;

    }
}
