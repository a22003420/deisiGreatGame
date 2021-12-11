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
public class Programmer
{
    //###########
    //ATTRIBUTES
    //###########

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
    private List<String> languages;

    /*
    Identifies programmer current position on board game
     */
    private int positionOnBoard;

    /*
    Identifies programmer game color
     */
    private ProgrammerColor color;

    /*
    Identifies programmer status during the game
     */
    private boolean status;
    /*

    Save all positions regarding the programmer
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
    //################

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

    /*
     Adiciona posição no ArrayList
     */
    public void addPosition (int position){
        positionsOnBoard.add(position);
    }

    /*
     Retorna ultima posição guardada
     */
    public Integer lastPosition (){
        return positionsOnBoard.get(positionsOnBoard.size()-1);
    }

    /*
     Retorna penúltima posição guardada
     */
    public Integer lastPosition2 (){
        return positionsOnBoard.get(positionsOnBoard.size()-2);
    }

    /*
     Retorna estado atual do jogador
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
     Check if programmer contains a tool
     */
    public boolean ContainsTool(Tool tool){
        return tools.contains(tool);
    }

    /*
     Add tool to programmer tools
     */
    public void addTool(Tool tool){
        if(!ContainsTool(tool)) {
            tools.add(tool);
        }
    }

    /*
     Remove tool from programmer tools
     */
    public void removeTool(Tool tool){
        if(!ContainsTool(tool)) {
            tools.remove(tool);
        }
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
        userTools.delete(userTools.length()-1,userTools.length());

        return userTools.toString();
    }

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

        // Missing for to tools

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

}
