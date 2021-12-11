package pt.ulusofona.lp2.deisiGreatGame;
//imports
import java.util.ArrayList;
import java.util.List;

/*
Represents a ToolFactory in a board tile
 */
public class ToolFactory extends Tile
{
    //###########
    //ATTRIBUTES
    //###########

    /* Tool id to Produce*/
    protected int idType;

    //################
    //Constructor
    //################

    public ToolFactory(int idType)
    {
        this.idType = idType;
    }

    //################
    //Factory
    //################

    /*
    Factory for Creating the type of tool
     */
    public static Tool fabricateTool(int toolType)
    {
        switch (toolType)
        {
            case 0:
                //TODO: Load chosen Abyss
                List<Abyss> xxx = new ArrayList<>();
                return new ToolInheritance(toolType, "Herança", xxx);
            case 1:
                //TODO: Load chosen Abyss
                return new ToolFuncProg(toolType, "Programação funcional", new ArrayList<>());
            case 2:
                //TODO: Load chosen Abyss
                return new ToolUnitTest(toolType, "Testes unitários", new ArrayList<>());
            case 3:
                //TODO: Load chosen Abyss
                return new ToolExcHandler(toolType, "Tratamento de Excepções", new ArrayList<>());
            case 4:
                //TODO: Load chosen Abyss
                return new ToolIDE(toolType, "IDE", new ArrayList<>());
            case 5:
                //TODO: Load chosen Abyss
                return new ToolTeacher(toolType, "Ajuda Do Professor", new ArrayList<>());
        }

        //if returns null there is something wrong!!!
        return null;
    }

    /*
    Return Produced Tool Title
    */
    protected String getTitle(){
        Tool tool = fabricateTool(this.idType);

        if(tool == null) {
            return "";
        }

        return tool.getTitle();
    }

    /*
    React to Tool on Tile
    */
    protected int reactToAbyssOrTool(){
        Tool tool = fabricateTool(this.idType);

        if(tool==null) {
            return 0;
        }

        return 1;
    }

    /*
    Return Produced Tool Image
     */
    protected String getImagePng(){
        Tool tool = fabricateTool(this.idType);

        if(tool==null) {
            return "";
        }

        return tool.getImagePng();
    }
}
