package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
public class Programmer {

    //###########
    //ATTRIBUTES

    /*
    Identifies programmer Id
     */
    private final int id;

    /*
    Identifies programmer name
     */
    private final String name;

    /*
    Identifies programmer preferred programming languages
     */
    private final List<String> languages;

    /*
    Identifies programmer current position on board game
     */
    private int positionOnBoard;

    /*
    Identifies programmer game color
     */
    private final ProgrammerColor color;

    /*
    Identifies programmer status during the game
     */
    private boolean status;
    /*

    Log all positions regarding the programmer
     */
    private ArrayList<Integer> positionsOnBoard;

    /*
    Check if the Programmer is locked or unlocked
     */
    private boolean locked;

    /*
    Identifies programmer tools
     */
    private List<Tool> tools;

    //################
    //Constructor

    /*
    Constructor for programmer:
    id: programmer id
    name: programmer name
    languageList: preferred programmer programming languages
    color: programmer color
     */
    Programmer(int id, String name, List<String> languageList, ProgrammerColor color){

        this.id = id;
        this.name = name;
        this.languages = languageList;
        this.color = color;
        this.status = true;
        //start position
        int startPosition = 1;
        this.positionOnBoard = 1;
        //positions
        ArrayList<Integer> positionsOnBoard = new ArrayList<>();
        positionsOnBoard.add(startPosition);
        this.positionsOnBoard = positionsOnBoard;
        //tool
        this.tools = new ArrayList<>();
    }

    //#################
    //Public Methods

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

    //#################
    //BEGIN METHODS: PROGRAMMER MOVE

    /*
     move player position on board
     */
    public void move(Integer boardSize, Integer nrPositions){

        //calculate new current player position
        int newPosition = currentPosition()+nrPositions;
        int newPositionAfterCheck;

        if(newPosition<=1) {
            newPositionAfterCheck = 1;
        }
        else {
            newPositionAfterCheck = newPosition > boardSize ? (boardSize - (newPosition - boardSize)) : newPosition;
        }

        setPosition(newPositionAfterCheck);
        logTurnPosition(newPositionAfterCheck);
    }

    //END METHODS: PROGRAMMER MOVE
    //#################

    //#################
    //BEGIN METHODS: PROGRAMMER LOCK, INGAME, GAMEOVER

    /*
     Check if Programmer is locked
     */
    public boolean isLocked(){
        return locked;
    }

    /*
     Lock Programmer
     */
    public void lock(){
        locked=true;
    }

    /*
     Unlock Programmer
     */
    public void unlock(){
        locked=false;
    }

    /*
     Return Programmer Status on Game
     */
    public boolean isInGame(){
        return status;
    }

    /*
    Set player status to false
     */
    public void gameOver(){
        status=false;
    }

    //END METHODS: PROGRAMMER LOCK, INGAME, GAMEOVER
    //#################

    //#################
    //BEGIN METHODS: PROGRAMMER POSITION

    /*
    Log player position
     */
    public void logTurnPosition(int position){

        if(position<1)
        {
            position = 1;
        }

        positionsOnBoard.add(position);
    }

    /*
    Set player position
     */
    public void setPosition(int position){

        if(position<1){
            position = 1;
        }

        positionOnBoard=position;
    }

    /*
     Return player game position on board
    */
    public Integer currentPosition() {
        return this.positionOnBoard;
    }

    /*
     Return player game position by turn

    public Integer getTurnPosition(int turn) {

        ArrayList<Integer> positions = this.positionsOnBoard;

        return this.positionOnBoard;
    }
    */

    /*
     Return Previous Position
     */
    public Integer previousPosition(){

        if(positionsOnBoard.size()==0){
            return 1;
        }

        return positionsOnBoard.get(positionsOnBoard.size()-2);
    }

    /*
     Return Previous Previous Position
     */
    public Integer previousPreviousPosition(){
        if(positionsOnBoard.size()==0){
            return 1;
        }

        if(positionsOnBoard.size()==1){
            return positionsOnBoard.get(0);
        }

        return positionsOnBoard.get(positionsOnBoard.size()-3);
    }


    //END METHODS: POSITION
    //#################

    //#################
    //BEGIN METHODS: TOOLS

    /*
     Check if programmer contains a tool for a given Abyss
     If true, removes tool from list of tools and returns used tool title, else returns empty
     */
    public String useToolOnAbyss(Abyss abyss){

        List<Tool> playerTools = this.tools;

        if(playerTools.size()==0) {
            return "";
        }

        for (Tool playerTool: playerTools)
        {
            if(playerTool.checkUseOfTool(abyss))
            {
                playerTools.remove(playerTool);
                return playerTool.getTitle();
            }
        }

        return "";
    }

    /*
     Add tool to programmer tools
     */
    public boolean addTool(Tool tool){

        if(!containsTool(tool)) {
            return this.tools.add(tool);
        }

        return false;
    }

    /*
     Programmer contains tool
     */
    public boolean containsTool(Tool tool){
        return this.tools.contains(tool);
    }

    /*
     Return programmer tools custom string
     */
    public String showTools(){

        if(tools==null || tools.isEmpty()){
            return "No tools";
        }

        StringBuilder userTools = new StringBuilder();
        for (Tool tools:tools) {
            userTools.append(tools.title);
            userTools.append("; ");
        }

        //remove right ;
        userTools.delete(userTools.length()-2,userTools.length());

        return userTools.toString();
    }

    //END METHODS: TOOLS
    //#################

    /*
    Return programmer custom string
    <id> | <nome> | <position> | <tools> | <languages> | <status>
     */
    @Override
    public String toString(){

        //order language list
        Collections.sort(languages);

        //create concatenated languages with ;
        StringBuilder strLanguages = new StringBuilder();
        for (String language : languages) {
            strLanguages.append(language);
            strLanguages.append(";");
            strLanguages.append(" ");
        }

        //remove right ;
        strLanguages.delete(strLanguages.length()-2,strLanguages.length());

        // remove right pipe when no tools are present
        /*String tools = showTools();
        if(tools.isEmpty()){
            tools = "";
        }
        else{
            tools= tools + " | ";
        }*/

        //concatenate and return final string
        return id + " | " + name + " | " + positionOnBoard + " | " + showTools() + " | " + strLanguages + " | " + showStatus();
    }

    /*
    Throw dice to calculate number of positions to move
    Result must be inside range [1,6]
    */
    public Integer throwDice(){
        Random rand = new Random();
        int min = 1;
        int max = 6;
        return rand.nextInt(max) + min;
    }

    //#################
    //Private Methods
    //#################

    /*
    Returns player status
     */
    private String showStatus(){
        return status ? "Em Jogo":"Derrotado";
    }

    /*
    Returns player tools string
    */
    private String programmerToolsToString(){

        //create concatenated languages with ;
        StringBuilder strLanguages = new StringBuilder();
        for (Tool tool : tools) {
            strLanguages.append(" ");
            strLanguages.append(tool.getTitle());
            strLanguages.append(";");
        }

        //concatenate and return final string
        return name + " : " + positionOnBoard + " | " + showTools() + " | " + strLanguages + " | " + showStatus();
    }
}
