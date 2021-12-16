package pt.ulusofona.lp2.deisiGreatGame;
//imports
import java.util.HashMap;
import java.util.Map;

public class AbyssSingletonFactory {

    //#############
    //Attributes

    //Instance to allow Singleton
    private static AbyssSingletonFactory instance = null;

    //object to hold all the abysses to be placed on game tiles
    private static Map<Integer, Object> abysses = new HashMap<>();

    //#############
    //Constructor

    //private constructor for factory
    private AbyssSingletonFactory() {}

    //#############
    //Methods

    /*
    Method responsible for ensuring Singleton
     */
    public static AbyssSingletonFactory getInstance() {

        if (instance == null)
        {
            // create all Abysses to be used on game and to be associated with tools
            abysses.put(0, new AbyssSyntax(0, "Erro de sintaxe", "syntax.png"));
            abysses.put(1, new AbyssLogic(1, "Erro de lógica", "logic.png"));
            abysses.put(2, new AbyssException(2, "Exception", "exception.png"));
            abysses.put(3, new AbyssFileNotFound(3, "File Not Found Exception", "file-not-found-exception.png"));
            abysses.put(4, new AbyssCrash(4, "Crash (aka Rebentanço)", "crash.png"));
            abysses.put(5, new AbyssDuplicatedCode(5, "Duplicated Code", "duplicated-code.png"));
            abysses.put(6, new AbyssSecondaryEffects(6, "Efeitos secundários", "secondary-effects.png"));
            abysses.put(7, new AbyssBlueScreenDeath(7, "Blue Screen of Death", "bsod.png"));
            abysses.put(8, new AbyssInfiniteCycle(8, "Ciclo infinito", "infinite-loop.png"));
            abysses.put(9, new AbyssSegmFault(9, "Segmentation Fault", "core-dumped.png"));

            //create new instance of Abyss Factory
            instance =  new AbyssSingletonFactory();
        }

        //return existing instance of Abyss Factory
        return instance;
    }

    /*
    Returns an Abyss given its Type
     */
    public Abyss getAbyss(Integer type) {
        return (Abyss)abysses.get(type);
    }
}
