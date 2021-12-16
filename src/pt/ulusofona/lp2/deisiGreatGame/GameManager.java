package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
Represents the Game board Manager
 */
public class GameManager {

    //###########
    //ATTRIBUTES
    //###########


    /*
    Board size
     */
    private int boardSize;

    /*
    Total number of turns for player
     */
    private int totalNrTurns;

    /*
    Players on game
     */
    private ArrayList<Programmer> programmers;

    /*
    Guarda Ferramentas e Abismos num arrayList
     */

    private ArrayList<ToolAbyss> toolsAndAbysses;


    //###########
    //REQUIRED Constructor
    //###########

    public GameManager(){
        reiniciar();
    }

    //###########
    //PUBLIC METHODS
    //###########

    /*
    Creates game initial board
     */

    public boolean createInitialBoard(String[][] playerInfo, int worldSize)
    {
        reiniciar();

        //check null value
        if(playerInfo==null) {
            return false;
        }

        //calculate nr of players
        int numberOfPlayers = playerInfo.length;

        //check number of players
        if (!isValidNrPlayers(numberOfPlayers)) {
            return false;
        }

        //check board size
        if (!isValidBoardSize(worldSize, numberOfPlayers)) {
            return false;
        }

        //list of programmers/players to fill
        ArrayList<Programmer> programmerList = new ArrayList<>();

        //iterate over matrix
        //each row represents a player
        int playerRow;
        for (playerRow = 0; playerRow < numberOfPlayers; playerRow++)
        {
            //Id
            int id;
            try{
                id = Integer.parseInt(playerInfo[playerRow][0]);
            }
            catch (Exception e)
            {
                return false;
            }

            //validate min id
            if(id<1)
            {
                return false;
            }

            //###
            //Begin:Name
            String name = playerInfo[playerRow][1];
            if(name == null || name.isEmpty()) {
                return false;
            }
            //End:Name
            //###

            //###
            //Begin: Programming Languages (prevent duplicates and convert to ArrayList<String>
            ArrayList<String> languagesList = fillLanguageList(playerInfo[playerRow][2]);
            //End: Programming Languages
            //###

            //####
            //Begin: Color
            String color = playerInfo[playerRow][3].toUpperCase();
            if(!isValidColorValue(color)) {
                return false;
            }
            ProgrammerColor enumColor = ProgrammerColor.valueOf(color.toUpperCase());
            //End: Color
            //####

            //validation values on second iteration
            if(playerRow>0)
            {
                for (Programmer programmer : programmerList)
                {
                    //validate unique player id
                    if(programmer.getId() == id) {
                        return false;
                    }

                    //validate unique player color
                    if(programmer.getColor().equals(enumColor)) {
                        return false;
                    }
                }
            }

            //Create new programmer and add to programmer list
            programmerList.add(new Programmer(id, name, languagesList, enumColor));
        }

        //set BoardSize
        setBoardSize(worldSize);

        //sort programmer by ID and set game players
        setProgrammerList(programmerList);

        return true;
    }

    boolean createInitialBoard(String[][] playerInfo, int worldSize, String[][] abyssesAndTools){

        if(!createInitialBoard(playerInfo,worldSize)){
            return false;
        }
        for (String[]linhas: abyssesAndTools) {
            // se for abismo é false (0), caso contrário true (1), ferramenta;
            boolean type="1".equals(linhas[0]);
            int effectsID, position;
            try {
                effectsID=Integer.parseInt(linhas[1]);

                position=Integer.parseInt(linhas[2]);
                if(position<=1 || position>=worldSize){
                    return false;
                }

            }catch(NumberFormatException e){
                return false;
            }
            ToolAbyss toolAbyss;
            String description;
            if(type){
                switch (effectsID){
                    case 0:
                        description="";
                        toolAbyss = new Tool(effectsID,"Herança","inheritance.png"
                                ,description,position);
                        break;
                    case 1:
                        description="";
                        toolAbyss = new Tool(effectsID,"",""
                                ,description,position);
                        break;

                    case 2:
                        description="";
                        toolAbyss = new Tool(effectsID,"",""
                                ,description,position);
                        break;
                    case 3:
                        description="";
                        toolAbyss = new Tool(effectsID,"",""
                                ,description,position);
                        break;
                    case 4:
                        description="";
                        toolAbyss = new Tool(effectsID,"",""
                                ,description,position);
                        break;
                    case 5:
                        description="";
                        toolAbyss = new Tool(effectsID,"",""
                                ,description,position);
                        break;
                    default:
                        return false;
                }
            }
            else{
                switch (effectsID){
                    case 0:
                        description="";
                        toolAbyss = new AbyssSyntax(effectsID,"","g"
                                ,description,position);
                        break;
                    case 1:
                        description="";
                        toolAbyss = new AbyssLogic(effectsID,"",""
                                ,description,position);
                        break;

                    case 2:
                        description="";
                        toolAbyss = new AbyssSyntax(effectsID,"",""
                                ,description,position);
                        break;
                    case 3:
                        description="";
                        toolAbyss = new AbyssSyntax(effectsID,"",""
                                ,description,position);
                        break;
                    case 4:
                        description="";
                        toolAbyss = new AbyssSyntax(effectsID,"",""
                                ,description,position);
                        break;
                    case 5:
                        description="";
                        toolAbyss = new AbyssSyntax(effectsID,"",""
                                ,description,position);
                        break;
                        // missing case 6
                        // missing case 7
                        // missing case 8
                        // missing case 9
                    default:
                        return false;
                }
            }
            toolsAndAbysses.add(toolAbyss);
        }

        return true;
    }



    /*
    Set Board Size
     */
    public void setBoardSize(int boardSize) {
        this.boardSize=boardSize;
    }

    /*
    Get player image for given position
     */
    public String getImagePng(int position){

        if(position<0 || position>boardSize){
            return null;
        }

        if(position==boardSize){
            return "glory.png";
        }



        return null;
    }

    /*
    Get players
     */

    //Devolve uma lista agora com todos os objects Programmers
    public ArrayList<Programmer> getProgrammers()
    {
        return this.programmers==null ? new ArrayList<>() : this.programmers;
    }

    /*
    Get players on a given position
    If none found returns null
     */
    public ArrayList<Programmer> getProgrammers(int position){

        if(position==0 || position>boardSize || programmers == null){
            return null;
        }

        ArrayList<Programmer> programmerList = new ArrayList<>();
        for (Programmer programmer: programmers)
        {
            if(programmer.getBoardPosition() == position)
            {
                programmerList.add(programmer);
            }
        }

        if(programmerList.size() == 0){
            return null;
        }

        return programmerList;
    }

    /*
    Get current player ID
     */
    public int getCurrentPlayerID()
    {
        return getCurrentPlayer().getId();
    }

    /*
    Get current player
     */
    public Programmer getCurrentPlayer()
    {
        //fetch programmer list
        ArrayList<Programmer> programmerArrayList = getProgrammers();

        //calculate number of players
        int nrPlayers = programmerArrayList.size();

        //nr turns
        int nrTurns = getNrTurns();

        //calculate current player index
        int index = nrTurns % nrPlayers;

        //return current player
        return programmerArrayList.get(index);
    }

    /*
    Move current player given positions
     */

    // Alterar
    public boolean moveCurrentPlayer(int nrPositions)
    {
        //check number positions range
        if(nrPositions<1 || nrPositions>6) {
            return false;
        }

        //get current player
        Programmer currentPlayer = getCurrentPlayer();
        //get current player position
        int currentPlayerPosition = currentPlayer.getBoardPosition();

        //get board dimension
        int boardSize = getBoardSize();

        //calculate new current player position
        int newPosition = currentPlayerPosition+nrPositions;

        //set position
        currentPlayer.setBoardPosition(newPosition>boardSize ? (boardSize-(newPosition-boardSize)) : newPosition);

        //add turn to game turns
        addTurn();

        return true;
    }

    /*
    Check if game is over
     */
    public boolean gameIsOver(){

        List<Programmer> programmerList = getProgrammers();

        for (Programmer programmer:programmerList) {
            if(programmer.getBoardPosition()==boardSize){
                return true;
            }
        }
        return false;
    }

    /*
    Get game statistics
     */
    // Agora devolve uma lista de Strings
    public ArrayList<String> getGameResults() {

        ArrayList<String> resultList = new ArrayList<>();

        resultList.add("O GRANDE JOGO DO DEISI");
        resultList.add("");
        resultList.add("NR. DE TURNOS");

        List<Programmer> programmerList = getProgrammers();

        if (programmerList==null || programmerList.size()==0){
            resultList.add("0");
            return resultList;
        }
        else
        {
            //hack: solve
            resultList.add(Integer.toString(totalNrTurns+1));
        }

        //Order programmers descending by Position
        programmerList.sort(Comparator.comparing(Programmer::getBoardPosition).reversed());

        int index;
        int nrOfPlayers = programmerList.size();
        for (index = 0; index<nrOfPlayers; index++)
        {
            Programmer programmer = programmerList.get(index);

            Integer position = programmer.getBoardPosition();
            if(index==0 && position!=boardSize){
                return resultList;
            }

            if(position==boardSize)
            {
                resultList.add("");
                resultList.add("VENCEDOR");
                resultList.add(programmer.getName());
                if( nrOfPlayers>1)
                {
                    resultList.add("");
                    resultList.add("RESTANTES");
                }
            }
            else
            {
                resultList.add(programmer.getName() + " " + programmer.getBoardPosition());
            }
        }
        return resultList;
    }

    /*
    Get Authors
     */
    public JPanel getAuthorsPanel() {
        JPanel newPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("                 ---João Matos & Rui Fazendeiro---");
        newPanel.add(label, BorderLayout.CENTER);
        return newPanel;
    }

    //################
    //PRIVATE METHODS

    /*
    Reset current game
     */
    private void reiniciar()
    {
        boardSize=0;
        programmers = new ArrayList<>();
        toolsAndAbysses = new ArrayList<>();
        totalNrTurns = 0;
    }

    /*
    Validate number of Players: [2,4]
    */
    private boolean isValidNrPlayers(int nrOfPlayers) {

        return (nrOfPlayers>1 && nrOfPlayers<5);
    }

    /*
    Validate Board Size: >=nrOfPlayers* 2
    */
    private boolean isValidBoardSize(int boardSize, int nrOfPlayers) {

        return (boardSize>=nrOfPlayers* 2);
    }

    /*
    Returns Board Size
     */
    private int getBoardSize() {
        return this.boardSize;
    }

    /*
    Returns total number of turns played
     */
    private int getNrTurns(){
        return this.totalNrTurns;
    }

    /*
    Add turn to total turns
     */
    private void addTurn()
    {
        this.totalNrTurns +=1;
    }

    /*
    Orders Programmer List by Id Ascending
    Set Programmer List
     */
    private void setProgrammerList(ArrayList<Programmer> programmers)
    {
        //order list by id ascending
        programmers.sort(Comparator.comparing(Programmer::getId));
        //fill programmers
        this.programmers=programmers;
    }

    /*
    Convert Language String to ArrayList<String>
     */
    private ArrayList<String> fillLanguageList(String languages) {

        if(languages == null || languages.length()==0){
            return null;
        }

        //create list of languages
        ArrayList<String> languagesList = new ArrayList<>();

        //split string by ";"
        String[] languagesArr = languages.split(";");

        for (String language: languagesArr)
        {
            //avoid duplicates
            if (!languagesList.contains(language)) {
                languagesList.add(language);
            }
        }
        return languagesList;
    }

    /*
    Validate if color exist on enumerator color
    Validation is made by using uppercase
     */
    private boolean isValidColorValue(String color)
    {
        ProgrammerColor[] programmerColorArr = ProgrammerColor.values();
        for (ProgrammerColor colorOnEnum : programmerColorArr){
            if(colorOnEnum.name().equals(color)) {
                return true;
            }
        }
        return false;
    }
}
