package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/*
Player game color
 */
enum PlayerColor {
    PURPLE, GREEN, BROWN, BLUE
}

/*
Player game Status
 */
enum PlayerStatus
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
    private Integer positionOnBoard;

    /*
    Identifies programmer status on game
     */
    private PlayerStatus status;

    /*
    Identifies programmer game color
     */
    private PlayerColor color;

    //################
    //Constructor
    //################
    Programmer(Integer id, String name, ArrayList<String> languageList, PlayerColor color)
    {
        this.id = id;
        this.name = name;
        this.languages = languageList;
        this.color = color;
        this.positionOnBoard = 1;
        this.status = PlayerStatus.Run;
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
    public PlayerColor getColor(){
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
            strLanguages.append(language);
            strLanguages.append("; ");
        }

        //concatenate and return final string
        return id.toString() + " | " + name + " | " + positionOnBoard.toString() + " | " + strLanguages + " | " + status;
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

    //################
    //PRIVATE METHODS

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
}
