package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import java.util.ArrayList;
import java.util.Collections;

/*
Player set game color
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
        return cor;
    }
}

/*
Represents a programmer
 */
public class Programmer
{
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
    Returns programmer Id
     */
    public int getId(){
        return this.id;
    }

    /*
    Returns programmer Name
     */
    public String getName(){
        return this.name;
    }

    /*
    Returns programmer Color
     */
    public ProgrammerColor getColor(){
        return this.color;
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
            strLanguages.append(" ");
            strLanguages.append(language);
            strLanguages.append(";");
        }

        //remove right ;
        strLanguages.delete(strLanguages.length()-1,strLanguages.length());

        //System.out.println(id + " | " + name + " | " + positionOnBoard + " |" + strLanguages + " | " + "Em Jogo");

        //concatenate and return final string
        return id + " | " + name + " | " + positionOnBoard + " |" + strLanguages + " | " + "Em Jogo";
    }

    /*
    Returns player game board position
     */
    public Integer getBoardPosition() {
        return this.positionOnBoard;
    }

    /*
    Set player game board position
     */
    public void setBoardPosition(Integer newPosition) {
        this.positionOnBoard=newPosition;
    }
}
