package pt.ulusofona.lp2.deisiGreatGame;
//imports
import java.util.HashMap;
import java.util.Map;

public class ToolFactorySingletonFactory {

    //#############
    //Attributes

    //Instance to allow Singleton
    private static ToolFactorySingletonFactory toolFactoryFactoryInstance = null;

    //object to hold all the Tool Factory to be placed on game tiles
    private static Map<Integer, Object> toolFactoryList = new HashMap<>();

    //#############
    //Constructor

    //private constructor for factory
    private ToolFactorySingletonFactory() {}

    //#############
    //Methods

    /*
    Method responsible for ensuring Singleton
     */
    public static ToolFactorySingletonFactory getInstance() {

        if (toolFactoryFactoryInstance == null)
        {
            // create all Abysses to be used on game and to be associated with tools
            toolFactoryList.put(0, new ToolFactory(0));
            toolFactoryList.put(1, new ToolFactory(1));
            toolFactoryList.put(2, new ToolFactory(2));
            toolFactoryList.put(3, new ToolFactory(3));
            toolFactoryList.put(4, new ToolFactory(4));
            toolFactoryList.put(5, new ToolFactory(5));

            //create new instance of Abyss Factory
            toolFactoryFactoryInstance =  new ToolFactorySingletonFactory();
        }

        //return existing instance of Abyss Factory
        return toolFactoryFactoryInstance;
    }

    /*
    Returns a Tool Factory given its Type
     */
    public ToolFactory getToolFactory(Integer type) {
        return (ToolFactory)toolFactoryList.get(type);
    }
}
