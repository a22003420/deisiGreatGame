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
    protected int toolTypeId;

    //################
    //Constructor
    //################

    public ToolFactory(int toolTypeId)
    {
        this.toolTypeId = toolTypeId;
    }

    //################
    //Factory
    //################

    /*
    Factory for Creating a type of tool
     */
    public static Tool fabricateTool(int toolTypeId)
    {
        List<Abyss> abysses = new ArrayList<>();

        switch (toolTypeId)
        {
            case 0:
                //TODO: Load chosen Abyss
                Abyss duplicated = Abyss.createAbyss(5);
                abysses.add(duplicated);
                return new ToolInheritance(toolTypeId, "Herança", "inheritance.png", abysses);
            case 1:
                //TODO: Load chosen Abyss
                return new ToolFuncProg(toolTypeId, "Programação funcional", "functional.png", abysses);
            case 2:
                //TODO: Load chosen Abyss
                return new ToolUnitTest(toolTypeId, "Testes unitários", "unit-tests.png", abysses);
            case 3:
                //TODO: Load chosen Abyss
                return new ToolExcHandler(toolTypeId, "Tratamento de Excepções", "exception.png", abysses);
            case 4:
                //TODO: Load chosen Abyss
                return new ToolIDE(toolTypeId, "IDE", "IDE.png", abysses);
            case 5:
                //TODO: Load chosen Abyss
                return new ToolTeacher(toolTypeId, "Ajuda Do Professor", "ajuda-professor.png", abysses);
        }

        //if returns null there is something wrong!!!
        return null;
    }

    /*
    Return Produced Tool Title
    */
    protected String getTitle(){
        Tool tool = fabricateTool(this.toolTypeId);

        if(tool == null) {
            return "";
        }

        return tool.getTitle();
    }

    /*
    Return Produced Tool Image
     */
    protected String getImagePng(){
        Tool tool = fabricateTool(this.toolTypeId);

        if(tool==null) {
            return "";
        }

        return tool.getImagePng();
    }

    /*
    React to Tool on Tile
    */
    @Override
    protected String reactToAbyssOrTool()
    {
        Tool tool = fabricateTool(this.toolTypeId);
        return tool.reactToAbyssOrTool();
    }
}
