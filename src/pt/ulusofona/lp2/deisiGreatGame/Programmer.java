package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
    private ArrayList<String> languages;

    /*
    Identifies programmer tools
     */
    private HashMap<Integer,ToolAbyss> tools;

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
    private ArrayList<Integer> savedPositions;
    /*
    Check if the Programmer is locked or unlocked
     */
    private boolean locked;




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
    Programmer(int id, String name, ArrayList<String> languageList, ProgrammerColor color)
    {
        this.id = id;
        this.name = name;
        this.languages = languageList;
        this.color = color;
        this.positionOnBoard = 1;
        this.status = true;
        this.savedPositions = new ArrayList<>();
        this.tools = new HashMap<>();
        this.locked = false;
    }

    public Programmer() {
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
        savedPositions.add(position);
    }
    /*
     Retorna ultima posição guardada
     */
    public Integer lastPosition (){
        return savedPositions.get(savedPositions.size()-1);
    }
    /*
     Retorna penúltima posição guardada
     */
    public Integer lastPosition2 (){
        return savedPositions.get(savedPositions.size()-2);
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
     retorna o locked status
     */
    public boolean isLocked(){
        return locked;
    }
    /*
     coloca o locked a true
     */
    public void setLocked(){
        locked=true;
    }
    /*
     coloca o locked a false
     */
    public void setUnlocked(){
        locked=false;
    }
    /*
     checka se o jogador tem a ferramenta especifica
     */
    public boolean checkTool(int toolID){
        return tools.containsKey(toolID);
    }
    /*
     adiciona ferramenta ao Hashmap (chave (id tool), valor(obj ToolAbyss))
     */
    public void addTool(ToolAbyss tool){
        tools.put(tool.getId(),tool);
    }
    /*
     remove ferramenta ao Hashmap
     */
    public void removeTool(int toolID){
        tools.remove(toolID);
    }

    /*
     Return tools custom string
     */

    public String showTools(){
        if(tools==null || tools.isEmpty()){
            return "No tools";
        }
        StringBuilder userTools = new StringBuilder();
        for (ToolAbyss toolsAbyss:tools.values()) {
            userTools.append(toolsAbyss.toString());
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
        Integer min = 1;
        Integer max = 6;
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
