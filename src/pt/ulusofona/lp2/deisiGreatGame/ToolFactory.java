package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;
import java.util.List;

/*
Represents a ToolFactory board tile
 */
public class ToolFactory extends Tile
{
    //###########
    //ATTRIBUTES
    //###########

    /* Abyss id */
    protected int id;

    //################
    //Constructor
    //################

    public ToolFactory(int id)
    {
        this.id = id;
    }

    /*
    Factory for Creating Tile of Type Abyss
     */
    public static ToolFactory returnToolFactory(int toolType)
    {
        /*
        switch (toolType)
        {
            case 0:
                List<Abyss> xxx = new ArrayList<>();

                return new ToolInheritance(toolType, "Herança", xxx);
            case 1:
                return new ToolFuncProg(toolType, "Programação funcional", new ArrayList<>());
            case 2:
                return new ToolUnitTest(toolType, "Testes unitários", new ArrayList<>());
            case 3:
                return new ToolExcHandler(toolType, "Tratamento de Excepções", new ArrayList<>());
            case 4:
                return new ToolIDE(toolType, "IDE", new ArrayList<>());
            case 5:
                List<Abyss> xxx = new ArrayList<>();

                return new ToolTeacher(toolType, "Ajuda Do Professor", new ArrayList<>());
        }

         */
        //if returns null there is something wrong!!!
        return null;
    }

}
