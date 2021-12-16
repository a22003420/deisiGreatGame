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

    /*
    Tool type Id to Produce
     */
    protected final int toolTypeId;

    //################
    //Constructor

    public ToolFactory(int toolTypeId){
        this.toolTypeId = toolTypeId;
    }

    //################
    //Factory

    /*
    Factory for Creating a type of tool
     */
    public static Tool fabricateTool(int toolTypeId)
    {
        //list to store abysses for each tool
        List<Abyss> abysses = new ArrayList<>();

        //call abyss factory to return required abysses
        AbyssSingletonFactory abyssFactory = AbyssSingletonFactory.getInstance();

        switch (toolTypeId)
        {
            case 0:
                Abyss duplicated = abyssFactory.getAbyss(5);
                abysses.add(duplicated);
                return new ToolInheritance(toolTypeId, "Herança", "inheritance.png", abysses);
            case 1:
                Abyss secEffects = abyssFactory.getAbyss(6);
                abysses.add(secEffects);
                Abyss infinite = abyssFactory.getAbyss(8);
                abysses.add(infinite);
                return new ToolFuncProg(toolTypeId, "Programação funcional", "functional.png", abysses);
            case 2:
                Abyss logic = abyssFactory.getAbyss(1);
                abysses.add(logic);
                return new ToolUnitTest(toolTypeId, "Testes unitários", "unit-tests.png", abysses);
            case 3:
                Abyss exc = abyssFactory.getAbyss(2);
                abysses.add(exc);
                Abyss fileNotFound = abyssFactory.getAbyss(3);
                abysses.add(fileNotFound);
                return new ToolExcHandler(toolTypeId, "Tratamento de Excepções", "catch.png", abysses);
            case 4:
                Abyss syntax = abyssFactory.getAbyss(0);
                abysses.add(syntax);
                return new ToolIDE(toolTypeId, "IDE", "IDE.png", abysses);
            case 5:
                Abyss syntax1 = abyssFactory.getAbyss(0);
                abysses.add(syntax1);
                Abyss logic1 = abyssFactory.getAbyss(1);
                abysses.add(logic1);
                Abyss exc1 = abyssFactory.getAbyss(2);
                abysses.add(exc1);
                Abyss fileNotFound1 = abyssFactory.getAbyss(3);
                abysses.add(fileNotFound1);
                return new ToolTeacher(toolTypeId, "Ajuda Do Professor", "ajuda-professor.png", abysses);
        }

        //if returns null there is something wrong!!!
        return null;
    }

    //################
    //Other Methods

    /*
    Return Produced Tool Title
    */
    @Override
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
    @Override
    protected String getImagePng(){
        Tool tool = fabricateTool(this.toolTypeId);

        if(tool==null) {
            return "";
        }

        return tool.getImagePng();
    }

    /*
    React to Tool Factory
    */
    @Override
    protected String reactToAbyssOrTool(List<Programmer> programmers, Programmer currProgrammer, int boardSize)
    {
        Tool tool = fabricateTool(this.toolTypeId);

        if(tool==null)
            return null;

        return tool.reactToAbyssOrTool(programmers, currProgrammer, boardSize);
    }
}
