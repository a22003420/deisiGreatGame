package pt.ulusofona.lp2.deisiGreatGame;
//imports
import java.util.HashMap;
import java.util.Map;

public class AbyssSingletonFactory {

    //#############
    //Attributes

    //Instance to allow Singleton
    private static AbyssSingletonFactory abyssFactoryInstance = null;

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

        if (abyssFactoryInstance == null)
        {
            // create all Abysses to be used on game and to be associated with tools
            abysses.put(0, new AbyssSyntax(0, "Erro de sintaxe", "syntax.png"));
            abysses.put(1, new AbyssLogic(1, "Erro de lógica", "logic.png"));
            abysses.put(2, new AbyssException(2, "Exception", "exception.png"));
            abysses.put(3, new AbyssFileNotFoundException(3, "File Not Found Exception", "file-not-found-exception.png"));
            abysses.put(4, new AbyssCrash(4, "Crash (aka Rebentanço)", "crash.png"));
            abysses.put(5, new AbyssDuplicatedCode(5, "Duplicated Code", "duplicated-code.png"));
            abysses.put(6, new AbyssSecondaryEffects(6, "Efeitos secundários", "secondary-effects.png"));
            abysses.put(7, new AbyssBlueScreenOfDeath(7, "Blue Screen of Death", "bsod.png"));
            abysses.put(8, new AbyssInfiniteCycle(8, "Ciclo infinito", "infinite-loop.png"));
            abysses.put(9, new AbyssSegmentationFault(9, "Segmentation Fault", "core-dumped.png"));

            //create new instance of Abyss Factory
            abyssFactoryInstance =  new AbyssSingletonFactory();
        }

        //return existing instance of Abyss Factory
        return abyssFactoryInstance;
    }

    /*
    Returns an Abyss given its Type
     */
    public Abyss getAbyss(Integer type) {
        return (Abyss)abysses.get(type);
    }
}
