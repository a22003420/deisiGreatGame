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
    Begin position is 1
     */
    private Integer position = 1;

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

    /*
    Identifies the number of moves
     */
    private Integer nrMoves;

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

        /*
        GameManager game = new GameManager();
        game.moveCurrentPlayer(nrPosition);
        */

        return false;
    }

    /*
    is Programmer CurrentPlayer
     */
    public Boolean isCurrentPlayer()
    {
        return currentProgrammer;
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

    /*
    Returns gamer position
     */
    public Integer getPosition() {
        return position;
    }

    /*
    Set game position
     */
    public void setPosition(Integer newPosition) {
        this.position=newPosition;
    }

    /*
    Set current player
     */
    public void setCurrentPlayer() {
        this.currentProgrammer=true;
    }

    /*
    Add nr Moves
     */
    public void addNrMoves() {
        this.nrMoves++;
    }

    /*
    Get Number of turns
     */
    public int getNrTurns() {
        return nrMoves;
    }
}
