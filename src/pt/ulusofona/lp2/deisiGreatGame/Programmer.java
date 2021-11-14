package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/*
Player game color
*/
enum ProgrammerColor
{
    PURPLE("Purple"), GREEN("Green"), BROWN("Brown"), BLUE("Blue");
    final String cor;

    ProgrammerColor(String cor) {
        this.cor=cor;
    }

    @Override
    public String toString() {
        return this.cor;
    }
}

/*
Represents a programmer
 */
public class Programmer
{
    //###########
    //ATTRIBUTES

    /*
    Identifies programmer Id
     */
    private int id;

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
    private int positionOnBoard;

    /*
    Identifies programmer game color
     */
    private ProgrammerColor color;

    //################
    //Constructor
    //################

    /*
    Constructor por programmer:
    id: programmer id
    name: programmer name
    languageList: preferred programmer programming languages
    color: programmer color
     */
    Programmer(int id, String name, ArrayList<String> languageList, ProgrammerColor color)
    {
        this.id = id;
        this.name = name;
        this.languages = languageList;
        this.color = color;
        this.positionOnBoard = 1;
    }

    //#################
    //Public Methods
    //#################

    /*
    Return programmer Id
     */
    public int getId(){
        return this.id;
    }

    /*
    Return programmer Name
     */
    public String getName(){
        return this.name;
    }

    /*
    Return programmer Color
     */
    public ProgrammerColor getColor(){
        return this.color;
    }

    /*
    Return programmer custom string
    <id> | <nome> | <position> | <languages> | <status>
     */
    @Override
    public String toString(){

        //order language list
        Collections.sort(languages);

        //create concatenated languages with ;
        StringBuilder strLanguages = new StringBuilder();
        for (String language : languages) {
            strLanguages.append(" ");
            strLanguages.append(language);
            strLanguages.append(";");
        }

        //remove right ;
        strLanguages.delete(strLanguages.length()-1,strLanguages.length());

        //concatenate and return final string
        return id + " | " + name + " | " + positionOnBoard + " |" + strLanguages + " | " + "Em Jogo";
    }

    /*
    Return player game position on board
     */
    public Integer getBoardPosition() {
        return this.positionOnBoard;
    }

    /*
    Set player game position on board
     */
    public void setBoardPosition(Integer newPosition) {
        this.positionOnBoard=newPosition;
    }

    //#################
    //Private Methods
    //#################

    /*
    Throw dice to calculate number of positions to move
    Result must be [1,6]
     */
    public Integer throwDice()
    {
        Random rand = new Random();
        Integer min = 1;
        Integer max = 6;
        return rand.nextInt(max) + min;
    }
}
