package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;



/*
Programmer game color
 */
enum ProgrammerColor {
    PURPLE, GREEN, BROWN, BLUE
}

/*
Programmer game Status
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
    /*
    Identifies programmer id
     */
    private Integer id;
    /*
    Identifies programmer name
     */
    private String name;
    /*
    Identifies programmer preferred programming languages
     */
    private ArrayList<String> languages;
    /*
    Identifies programmer current position on board game
     */
    private Integer position;
    /*
    Identifies programmer status on game: "Em Jogo"; Derrotado
     */
    private ProgrammerStatus status;

    /*
    Identifies programmer game color
     */
    private ProgrammerColor color;

    /*
    Identifies if programmer is current player
     */
    private Boolean currentProgrammer;

    //################
    //Constructor
    //################
    Programmer(Integer id, String name, ArrayList<String> languageList, ProgrammerColor color)
    {
        this.id = id;
        this.name = name;
        this.languages = languageList;
        this.color = color;
        this.position = 0;
    }

    //#################
    //Methods
    //#################

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
    Throw dice to calculate number of positions to move
    Result must be [1,6]
     */
    private Integer throwDice()
    {
        Random rand = new Random();
        Integer min = 1;
        Integer max = 6;
        return rand.nextInt(max) + min;
    }

    /*
    Throw dice to calculate number of positions to move
    Result must be [1,50]
    */
    private Integer generateProgrammerId()
    {
        //Random rand = new Random();
        //return rand.nextInt(MAXID) + minId;
        return 0;
    }

    /*
    Programmer play
     */
    public Boolean play()
    {
        Integer nrPosition = throwDice();

        return false;
    }

    /*
    Returns programmer string
    <id> | <nome> | <position> | <languages> | <status>
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
