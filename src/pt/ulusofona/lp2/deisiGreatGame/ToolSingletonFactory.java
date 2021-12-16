package pt.ulusofona.lp2.deisiGreatGame;
//imports
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToolSingletonFactory
{
    //#############
    //Attributes

    //Instance to allow Singleton
    private static ToolSingletonFactory instance = null;

    //object to hold all the tools to be used on game
    private static Map<Integer, Object> tools = new HashMap<>();

    //#############
    //Constructor

    //private constructor for factory
    private ToolSingletonFactory() {}

    //#############
    //Methods

    /*
    Method responsible for ensuring Singleton
     */
    public static ToolSingletonFactory getInstance() {

        if (instance == null)
        {
            //call abyss factory to return required abysses
            AbyssSingletonFactory abyssFactory = AbyssSingletonFactory.getInstance();

            // create all Abysses to be used on game and to be associated with tools
            //list to store abysses for each tool
            List<Abyss> abyssesToolInheritance = new ArrayList<>();
            abyssesToolInheritance.add(abyssFactory.getAbyss(5));
            tools.put(0, new ToolInheritance(0, "Herança", "inheritance.png", abyssesToolInheritance));

            List<Abyss> abyssesToolFuncProg = new ArrayList<>();
            abyssesToolFuncProg.add(abyssFactory.getAbyss(6));
            abyssesToolFuncProg.add(abyssFactory.getAbyss(8));
            tools.put(1, new ToolFuncProg(1, "Programação Funcional", "functional.png", abyssesToolFuncProg));

            List<Abyss> abyssesToolUnitTest = new ArrayList<>();
            abyssesToolUnitTest.add(abyssFactory.getAbyss(1));
            tools.put(2, new ToolUnitTest(2, "Testes unitários", "unit-tests.png", abyssesToolUnitTest));

            List<Abyss> abyssesToolExcHandler = new ArrayList<>();
            abyssesToolExcHandler.add(abyssFactory.getAbyss(2));
            abyssesToolExcHandler.add(abyssFactory.getAbyss(3));
            tools.put(3, new ToolExcHandler(3, "Tratamento de Excepções", "catch.png", abyssesToolExcHandler));

            List<Abyss> abyssesToolIDE = new ArrayList<>();
            abyssesToolIDE.add(abyssFactory.getAbyss(0));
            tools.put(4, new ToolIDE(4, "IDE", "IDE.png", abyssesToolIDE));

            List<Abyss> abyssesToolTeacher = new ArrayList<>();
            abyssesToolTeacher.add(abyssFactory.getAbyss(0));
            abyssesToolTeacher.add(abyssFactory.getAbyss(1));
            abyssesToolTeacher.add(abyssFactory.getAbyss(2));
            abyssesToolTeacher.add(abyssFactory.getAbyss(3));
            tools.put(5, new ToolTeacher(5, "Ajuda Do Professor", "ajuda-professor.png", abyssesToolTeacher));

            //create new instance of Tool Factory
            instance =  new ToolSingletonFactory();
        }

        //return existing instance of Tool Factory
        return instance;
    }

    /*
    Returns a Tool given its Type
     */
    public Tool getTool(Integer type) {
        return (Tool)tools.get(type);
    }


}
