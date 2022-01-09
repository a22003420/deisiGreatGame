package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import java.util.*;
import java.util.stream.Collectors;

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
Player game status
*/
enum ProgrammerStatus
{
    INGAME("Em Jogo"), DEFEATED("Derrotado");
    final String status;

    ProgrammerStatus(String status) {
        this.status=status;
    }

    @Override
    public String toString() {
        return this.status;
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
    Identifies programmer game color
     */
    private final ProgrammerColor color;

    /*
    Identifies programmer status during the game
     */
    private ProgrammerStatus status;
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
        this.status = ProgrammerStatus.INGAME;
        this.positionsOnBoard = new ArrayList<>();

        //log start position
        int startPosition = 1;
        logNewTurnPosition(startPosition);


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

    /*
    Check if Programmer contains given language
     */
    public Boolean containsLanguage(String language){

        if(language.isEmpty()){
            return false;
        }

        return languages.contains(language);
    }

    /*
    Returns Programmer number of languages
     */
    public int numberOfLanguages(){
        return languages.size();
    }

    /*
    Updates Programmer positions on load game
     */
    public void setPositions(ArrayList<Integer> positionsOnBoard){
        this.positionsOnBoard = positionsOnBoard;
    }

    /*
    Returns Programmer positions on game
     */
    public ArrayList<Integer> getPositions(){
        return positionsOnBoard;
    }

    //#################
    //BEGIN METHODS: PROGRAMMER MOVE

    /*
    move player position on board
    logs new position or updates last position on reacting an abyss
    */
    public void move(Integer boardSize, Integer nrPositions, boolean isReactOnAbyss){

        //calculate new current player position
        int newPosition = currentPosition()+nrPositions;

        //check max allowed position
        int newPositionAfterCheck = newPosition > boardSize ? (boardSize - (newPosition - boardSize)) : newPosition;

        //check min allowed position
        if(newPositionAfterCheck<1) {
            newPositionAfterCheck = 1;
        }

        //update last log position
        if(isReactOnAbyss) {
            updateLastLogPosition(newPositionAfterCheck);
        }
        else{ //log new position
            logNewTurnPosition(newPositionAfterCheck);
        }
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
    public boolean inGame(){
        return status == ProgrammerStatus.INGAME;
    }

    /*
    Set player status to false
     */
    public void gameOver(){
        status=ProgrammerStatus.DEFEATED;
    }

    //END METHODS: PROGRAMMER LOCK, INGAME, GAMEOVER
    //#################

    //#################
    //BEGIN METHODS: PROGRAMMER POSITION

    /*
    Log new player position
    */
    public void logNewTurnPosition(int position){
        positionsOnBoard.add(position);
    }

    /*
    Update last log position after react to Abyss
    */
    public void updateLastLogPosition(int position){
        positionsOnBoard.set(positionsOnBoard.size()-1, position);
    }

    /*
    Return player last game position on board
    */
    public Integer currentPosition(){
        return positionsOnBoard.get(positionsOnBoard.size()-1);
    }

    /*
    Return Previous Turn Position
    */
    public Integer previousTurnPosition(int nrTurns){

        //number of logged positions
        int size = positionsOnBoard.size()-1;
        //nrTurns versus logged positions
        nrTurns = (size < nrTurns) ? nrTurns-1 : nrTurns;
        //index
        int index = Math.abs(nrTurns - size);

        return positionsOnBoard.get(index);
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

        //find programmer tools
        List<Tool> playerTools = this.tools;

        if(playerTools.size()==0) {
            return "";
        }

        for (Tool playerTool: playerTools)
        {
            //is Tool responsibility to check if a given tool is applied to an abyss
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
            return tools.add(tool);
        }

        return false;
    }

    /*
     Programmer contains tool
     */
    public boolean containsTool(Tool tool){
        return tools.contains(tool);
    }

    /*
     Return programmer tools custom string
     */
    public String showTools(){

        if(tools==null || tools.isEmpty()){
            return "No tools";
        }

        StringBuilder userTools = new StringBuilder();
        for (Tool tool:tools) {
            userTools.append(tool.getTitle());
            userTools.append("; ");
        }

        //remove right ;
        if(userTools.length()>0) {
            userTools.delete(userTools.length() - 2, userTools.length());
        }

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
        return id + " | " + name + " | " + currentPosition().toString() + " | " + showTools().toString() + " | " + showLanguages().toString() + " | " + showStatus();
    }

    /*
    Throw dice to calculate number of positions to move
    Result must be inside range [1,6]
    */
    public Integer throwDice(){
        return new Random().nextInt(6) + 1;
    }

    /*
    Return programmer custom string to save on file
    <id> | <nome> | <position> | <tools> | <languages> | <status>
     */
    public String getProgrammerDataToSaveOnFile(){

        if(languages==null || languages.isEmpty()){
            return "";
        }

        return id + ";" + name + ";" + getColor() + ";" + getLanguagesDataToSaveInFile() + ";" +
                getToolsDataToSaveInFile() + ";" + isLocked() + ";" + showStatus() + ";" +
                getPositionsDataToSaveInFile();
    }

    //#################
    //Private Methods
    //#################

    /*
    Returns player status
     */
    private String showStatus(){
        return status.toString();
    }

    /*
    Create string to show Programmer Languages
     */
    private StringBuilder showLanguages() {

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
        if(strLanguages.length()>0) {
            strLanguages.delete(strLanguages.length() - 2, strLanguages.length());
        }

        return strLanguages;
    }

    //##### HELPER METHODS FOR FILE DATA

    /*
    Create string for Programmer Languages to save on file
     */
    private String getLanguagesDataToSaveInFile() {

        if(languages==null || languages.isEmpty()){
            return "";
        }

        //create concatenated languages with ;
        StringBuilder strLanguages = new StringBuilder();
        for (String language : languages) {
            strLanguages.append(language);
            strLanguages.append("§");
        }

        //remove right#
        if(strLanguages.length()>0) {
            strLanguages.delete(strLanguages.length() - 1, strLanguages.length());
        }

        return strLanguages.toString();
    }

    /*
    Create string for Programmer Positions to save on file
     */
    private String getPositionsDataToSaveInFile(){
        if(positionsOnBoard==null || positionsOnBoard.isEmpty()){
            return "";
        }

        return positionsOnBoard.stream().map(String::valueOf).collect(Collectors.joining("§"));
    }

    /*
    Create string for Programmer tools ID to be used on file
     */
    private String getToolsDataToSaveInFile(){

        if(tools==null || tools.isEmpty()){
            return "";
        }

        StringBuilder userTools = new StringBuilder();
        for (Tool tool:tools) {
            userTools.append(tool.getId());
            userTools.append("§");
        }

        //remove right#
        if(userTools.length()>0) {
            userTools.delete(userTools.length() - 1, userTools.length());
        }

        return userTools.toString();
    }
}
