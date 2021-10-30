package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import java.util.ArrayList;
import java.util.Collections;

/*
Programmer color
 */
enum ProgrammerColor {
    Red, Green, Blue
}

/*
Programmer Status
 */
enum ProgrammerStatus
{
    Run {
        public String toString() {
            return "Em Jogo";
        }
    },
    Exit {
        public String toString() {
            return "Derrotado";
        }
    }
}

/*
Represents a programmer
 */
public class Programmer
{
    private Integer id;
    private String name;
    private ProgrammerColor color;
    private ArrayList<String> languages;
    private Integer position;
    private ProgrammerStatus status;

    /*
    Constructor
     */
    Programmer(Integer id, String name, ProgrammerColor color, ArrayList<String> languageList, Integer position)
    {
        this.id = id;
        this.name = name;
        this.color = color;
        this.languages = languageList;
        this.position = position;
    }

    /*
    Returns programmer Id
     */
    int getId(){
        return id;
    }

    /*
    Returns programmer Name
     */
    String getName(){
        return name;
    }

    /*
    Returns programmer Color
     */
    ProgrammerColor getColor(){
        return color;
    }

    /*
    Returns programmer string
     */
    @Override
    public String toString(){

        //order language list
        Collections.sort(languages);

        //create concatenated languages with ;
        StringBuilder strLanguages = new StringBuilder();
        for (String language : languages) {
            strLanguages.append(language);
            strLanguages.append("; ");
        }

        //concatenate and return final string
        return id.toString() + " | " + name + " | " + position.toString() + " | " + strLanguages + " | " + status;
    }
}
