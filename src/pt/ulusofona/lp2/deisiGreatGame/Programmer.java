package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import java.util.ArrayList;
import java.util.Collections;

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
        return cor;
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
    Identifies programmer status on game
     */
    private boolean status;

    /*
    Identifies programmer game color
     */
    private ProgrammerColor color;

    //################
    //Constructor
    //################
    /*
    public Programmer() {

    }
    */

    Programmer(int id, String name, ArrayList<String> languageList, ProgrammerColor color)
    {
        this.id = id;
        this.name = name;
        this.languages = languageList;
        this.color = color;
        this.positionOnBoard = 1;
        this.status = true;
    }

    public Programmer() {

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
    Programmer play
     */
    public Boolean play()
    {
        return true;
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
        strLanguages.delete(strLanguages.length()-1,strLanguages.length());
        String aux;
        if(status){
            aux="Em Jogo";
        }else{
            aux="Derrotado";
        }

        //concatenate and return final string
        return id + " | " + name + " | " + positionOnBoard + " |" + strLanguages + " | " + aux;
    }

    /*
    Returns player game board position
     */
    public int getBoardPosition() {
        return this.positionOnBoard;
    }

    /*
    Set player game board position
     */
    public void setBoardPosition(int newPosition) {
        this.positionOnBoard=newPosition;
    }
}
