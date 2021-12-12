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
        List<Abyss> abysses = new ArrayList<>();

        switch (toolType)
        {
            case 0:
                //TODO: Load chosen Abyss
                Abyss duplicated = Abyss.createAbyss(5);
                abysses.add(duplicated);
                return new ToolInheritance(toolType, "Herança", "inheritance.png", abysses);
            case 1:
                //TODO: Load chosen Abyss
                return new ToolFuncProg(toolType, "Programação funcional", "functional.png", abysses);
            case 2:
                //TODO: Load chosen Abyss
                return new ToolUnitTest(toolType, "Testes unitários", "unit-tests.png", abysses);
            case 3:
                //TODO: Load chosen Abyss
                return new ToolExcHandler(toolType, "Tratamento de Excepções", "exception.png", abysses);
            case 4:
                //TODO: Load chosen Abyss
                return new ToolIDE(toolType, "IDE", "IDE.png", abysses);
            case 5:
                //TODO: Load chosen Abyss
                return new ToolTeacher(toolType, "Ajuda Do Professor", "ajuda-professor.png", abysses);
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
    protected void reactToAbyssOrTool(Programmer programmer){
        Tool tool = fabricateTool(this.idType);


        if(!programmer.ContainsTool(tool)){
           programmer.addTool(tool);
        }


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
