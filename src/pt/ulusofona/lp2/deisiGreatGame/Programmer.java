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
    Identifies if programmer is current player
     */
    private boolean current;

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
    Programmer(int id, String name, List<String> languageList, ProgrammerColor color)
    {
        this.id = id;
        this.name = name;
        this.languages = languageList;
        this.color = color;
        this.positionOnBoard = 1;
        this.status = true;
        this.positionsOnBoard = new ArrayList<>();
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
    public void move(Integer boardSize, Integer nrPositions)
    {
        //calculate new current player position
        int newPosition = currentPosition()+nrPositions;
        int positionCheck = 0;

        if(newPosition>1) {
            positionCheck = newPosition > boardSize ? (boardSize - (newPosition - boardSize)) : newPosition;
        }
        else {
            positionCheck = 1;
        }

        this.positionOnBoard=positionCheck;
        logNewPosition(positionCheck);
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
        return status;
    }

    /*
     coloca o status a false
     */
    public void gameOver(){
        status=false;
    }

    //END METHODS: PROGRAMMER LOCK, INGAME, GAMEOVER
    //#################

    //#################
    //BEGIN METHODS: PROGRAMMER POSITION

    public void setCurrent(){
        current = true;
    }

    public boolean isCurrent() {
        return current;
    }

    /*
    Add new Position
     */
    public void logNewPosition (int position){
        positionsOnBoard.add(position);
    }

    /*
     Return player game position on board
    */
    public Integer currentPosition() {
        return this.positionOnBoard;
    }

    /*
     Return Previous Position
     */
    public Integer previousPosition(){

        if(positionsOnBoard.size()==0){
            return 1;
        }

        return positionsOnBoard.get(positionsOnBoard.size()-1);
    }

    /*
     Return Previous Previous Position
     */
    public Integer previousPreviousPosition(){
        if(positionsOnBoard.size()==0){
            return 1;
        }

        if(positionsOnBoard.size()==1){
            return positionsOnBoard.get(1);
        }

        return positionsOnBoard.get(positionsOnBoard.size()-2);
    }

    /*
     Return Previous Previous Previous Position
     */
    public Integer previousPreviousPreviousPosition(){
        if(positionsOnBoard.size()==0){
            return 1;
        }

        if(positionsOnBoard.size()==1){
            return positionsOnBoard.get(1);
        }

        if(positionsOnBoard.size()==2){
            return positionsOnBoard.get(2);
        }

        return positionsOnBoard.get(positionsOnBoard.size()-3);
    }

    //END METHODS: POSITION
    //#################

    //#################
    //BEGIN METHODS: TOOLS

    /*
     Check if programmer contains a given tool
     */
    private boolean ContainsTool(Tool tool){
        boolean containsTool = tools.contains(tool);
        return containsTool;
    }

    /*
     Check if programmer contains a tool for a given Abyss
     If true, removes tool from list of tools and returns used tool title
     */
    public String UseToolOnAbyss(Abyss abyss)
    {
        String result="";

        if(tools.size()==0)
            return result;

        Tool foundTool = null;
        for (Tool tool: tools)
        {
            if(tool.abysses.contains(abyss))
            {
                foundTool = tool;
                break;
            }
        }

        if(foundTool!=null)
        {
            result = foundTool.title;
            tools.remove(foundTool);
        }

        return result;
    }

    /*
     Add tool to programmer tools
     */
    public boolean addTool(Tool tool){

        if(tool==null){
            return false;
        }

        if(!ContainsTool(tool)) {
            return tools.add(tool);
        }

        return false;
    }

    /*
     Remove tool from programmer tools
     */
    public boolean removeTool(Tool tool){

        if(tool==null){
            return false;
        }

        if(ContainsTool(tool)) {
            return tools.remove(tool);
        }

        return false;
    }

    /*
     Return programmer tools custom string
     */
    public String showTools()
    {
        if(tools==null || tools.isEmpty()){
            return "No tools";
        }

        StringBuilder userTools = new StringBuilder();
        for (Tool tools:tools) {
            userTools.append(tools.title);
            userTools.append(";");
        }

        //remove right ;
        userTools.delete(userTools.length()-1,userTools.length());

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
            strLanguages.append(" ");
            strLanguages.append(language);
            strLanguages.append(";");
        }

        //remove right ;
        strLanguages.delete(strLanguages.length()-1,strLanguages.length());

        //concatenate and return final string
        return id + "| " + name + " | " + positionOnBoard + " | " + showTools() + " |" + strLanguages + " | " + showStatus();
    }

    /*
    Throw dice to calculate number of positions to move
    Result must be inside range [1,6]
    */
    public Integer throwDice()
    {
        Random rand = new Random();
        int min = 1;
        int max = 6;
        return rand.nextInt(max) + min;
    }

    //#################
    //Private Methods
    //#################

    // Se true retorna "Em Jogo", caso contrário "Derrotado"
    private String showStatus(){
        return status ? "Em Jogo":"Derrotado";
    }

    /*

     */
    private String programmerToolstoString(){

        //create concatenated languages with ;
        StringBuilder strLanguages = new StringBuilder();
        for (Tool tool : tools) {
            strLanguages.append(" ");
            strLanguages.append(tool.getTitle());
            strLanguages.append(";");
        }

        //concatenate and return final string
        return name + " : " + positionOnBoard + " | " + showTools() + " |" + strLanguages + " | " + showStatus();
    }

}
