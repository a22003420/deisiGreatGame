package pt.ulusofona.lp2.deisiGreatGame;
//imports
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
Represents the Game board Manager
 */
public class GameManager {

    //###########
    //ATTRIBUTES

    /*
    Players on game
     */
    private List<Programmer> programmers;

    /*
    Tiles on game
     */
    private ArrayList<Tile> tiles;

    /*
    Total number of turns
     */
    private int totalNrTurns;

    //###########
    //REQUIRED Constructor

    public GameManager(){
        reiniciar();
    }

    //###########
    //PUBLIC METHODS
    //###########

    //###########
    //BEGIN PUBLIC METHODS: BOARD

    /*
    Creates initial board
     */
    public boolean createInitialBoard(String[][] playerInfo, int worldSize){
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
            List<String> languagesList = fillLanguageList(playerInfo[playerRow][2]);
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

        //sort programmer by ID and set game players
        setProgrammerList(programmerList);

        return true;
    }

    /*
    Creates initial board. includes: Empty, Tool Factory and Abyss Tiles
     */
    public boolean createInitialBoard(String[][] playerInfo, int worldSize, String[][] abyssesAndTools){
        boolean success = createInitialBoard(playerInfo,  worldSize);
        if(!success) {
            return false;
        }

        this.tiles = new ArrayList<>();

        //######
        //Create and fill Game Tile
        int tileRow;
        for (tileRow = 0; tileRow <= worldSize; tileRow++)
        {
            tiles.add(new Empty("Casa Vazia", "blank.png"));
        }

        //fill Objects
        for (String[] abyssesAndTool : abyssesAndTools)
        {
            //#######
            //validate position
            int tilePosition;
            try {
                tilePosition = Integer.parseInt(abyssesAndTool[2]);
            } catch (Exception e) {
                return false;
            }
            if (tilePosition > worldSize) {
                return false;
            }

            //#######
            //validate Object Type
            int typeObjectId; //0 - Abyss; 1 - Tool
            try {
                typeObjectId = Integer.parseInt(abyssesAndTool[0]);
            } catch (Exception e) {
                return false;
            }
            if (typeObjectId > 1) {
                return false;
            }

            //#######
            //validate Object Abyss SubType and ToolFactory
            int subTypeObject;
            try {
                subTypeObject = Integer.parseInt(abyssesAndTool[1]);
            } catch (Exception e) {
                return false;
            }

            //Initialize all Abyss for the Game
            AbyssSingletonFactory abyssFactory = AbyssSingletonFactory.getInstance();
            //Initialize all Tool Factory Types for the Game
            ToolFactorySingletonFactory toolFactoryFactory = ToolFactorySingletonFactory.getInstance();

            //Fill Tile with object
            switch (typeObjectId) {
                case 0: //Abyss
                    tiles.set(tilePosition, abyssFactory.getAbyss(subTypeObject));
                    break;
                case 1: //Tool Factory
                    tiles.set(tilePosition, toolFactoryFactory.getToolFactory(subTypeObject));
                    break;
            }
        }

        return true;
    }

    /*
    Get Tile Title
     */
    public String getTitle(int position){
        return getTile(position).getTitle();
    }

    /*
    Get tile image
     */
    public String getImagePng(int position){

        //image for start
        if(position==1){
            return "start.png";
        }

        //image for finish
        if(position==getBoardSize()){
            return "glory.png";
        }

        //return other tile images: Empty, ToolFactory and Abyss
        return getTile(position).getImagePng();
    }

    /*
    Reaction to Abyss Title or Tool Factory
     */
    public String reactToAbyssOrTool(){

        //get current player
        Programmer currentPlayer = getCurrentPlayer();

        //verify tile
        Tile tile = tiles.get(currentPlayer.currentPosition());

        //add turn to game turns
        addTurn();

        return tile.reactToAbyssOrTool(getProgrammers(), currentPlayer, getBoardSize());
    }

    /*
    Check if game is over
     */
    public boolean gameIsOver(){

        List<Programmer> programmerList = getProgrammers();

        boolean isGameOver = false;

        //check if there is programmer on final position
        for (Programmer programmer:programmerList) {
            if(programmer.currentPosition()== getBoardSize()){
                isGameOver = true;
                break;
            }
        }

        if(!isGameOver) {
            //check if there is only one programmer in game
            isGameOver = programmerList.stream().filter(Programmer::isInGame).count() == 1;
        }

        if(isGameOver) {
            addTurn();
        }

        return isGameOver;
    }

    /*
    Get game statistics
     */
    public List<String> getGameResults() {

        List<String> resultList = new ArrayList<>();

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
        programmerList.sort(Comparator.comparing(Programmer::currentPosition).reversed());

        int index;
        int nrOfPlayers = programmerList.size();
        for (index = 0; index<nrOfPlayers; index++)
        {
            Programmer programmer = programmerList.get(index);

            Integer position = programmer.currentPosition();
            if(index==0 && position!=getBoardSize()){
                return resultList;
            }

            if(position==getBoardSize())
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
                resultList.add(programmer.getName() + " " + programmer.currentPosition());
            }
        }
        return resultList;
    }

    //END PUBLIC METHODS: BOARD
    //###########

    //###########
    //BEGIN PUBLIC METHODS: PROGRAMMERS

    /*
    Get all Programmers or only those not defeated
     */
    public List<Programmer> getProgrammers(boolean includeDefeated){
        if(programmers==null) {
            return null;
        }

        return includeDefeated ? this.programmers : programmers.stream().collect(Collectors.filtering(Programmer::isInGame, Collectors.toList()));
    }

    /*
    Get all Programmers ignoring state and locked
     */
    public List<Programmer> getProgrammers(){
        return this.programmers==null ? new ArrayList<>() : this.programmers;
    }

    /*
    Get Programmers on a given position
    If none found returns null
     */
    public List<Programmer> getProgrammers(int position){

        if(position==0 || position>getBoardSize() || programmers == null){
            return null;
        }

        ArrayList<Programmer> programmerList = new ArrayList<>();
        for (Programmer programmer: programmers)
        {
            if(programmer.currentPosition() == position)
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
    Get current Programmer ID
     */
    public int getCurrentPlayerID(){
        return getCurrentPlayer().getId();
    }

    /*
    Get current Programmer
    */
    public Programmer getCurrentPlayer(){

        //fetch programmer list
        List<Programmer> programmers = getProgrammers();

        //calculate number of players
        int nrPlayers = programmers.size();

        //nr turns
        int nrTurns = getNrTurns();

        //calculate current player index
        int index = nrTurns % nrPlayers;

        //return current player
        return programmers.get(index);
    }

    /*
    Move current Player n positions
     */
    public boolean moveCurrentPlayer(int nrPositions){

        //check number positions range
        if(nrPositions<1 || nrPositions>6) {
            return false;
        }

        //check if current player is locked
        if(getCurrentPlayer().isLocked()){
            return false;
        }

        //send message to programmer to move
        getCurrentPlayer().move(getBoardSize(), nrPositions);

        return true;
    }

    /*
    Get Programmers Info 
     */
    public String getProgrammersInfo(){

        //create concatenated programmers with ;
        StringBuilder strProgrammers = new StringBuilder();
        for (Programmer programmer : programmers) {
            strProgrammers.append(programmer.getName());
            strProgrammers.append(" : ");
            strProgrammers.append(programmer.showTools());
            strProgrammers.append(" | ");
        }

        //remove right |
        strProgrammers.delete(strProgrammers.length()-3,strProgrammers.length());

        //return final string
        return strProgrammers.toString();
    }

    //END PUBLIC METHODS: PROGRAMMERS
    //###########

    /*
    Get About
     */
    public JPanel getAuthorsPanel() {
        JPanel newPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("                 ---João Matos & Rui Fazendeiro---");
        newPanel.add(label, BorderLayout.CENTER);
        return newPanel;
    }

    //################
    //PRIVATE METHODS
    //################

    /*
    Reset current game
     */
    private void reiniciar(){
        tiles=new ArrayList<>();
        programmers = new ArrayList<>();
        totalNrTurns = 0;
    }

    /*
    Validate number of Players: [2,4]
    */
    private boolean isValidNrPlayers(int nrOfPlayers){
        return (nrOfPlayers>1 && nrOfPlayers<5);
    }

    /*
    Validate Board Size: >=nrOfPlayers* 2
    */
    private boolean isValidBoardSize(int boardSize, int nrOfPlayers){
        return (boardSize>=nrOfPlayers* 2);
    }

    /*
    Returns Board Size: number of tiles
    Subtract 1 unit to skip index
     */
    private int getBoardSize(){
        return this.tiles.size()-1;
    }

    /*
    Returns total number of turns played
     */
    private int getNrTurns(){
        return this.totalNrTurns;
    }

    /*
    Returns title in a given position
    */
    private Tile getTile(int position){
        return tiles.get(position);
    }

    /*
    Add turn to total turns
     */
    private void addTurn(){
        this.totalNrTurns +=1;
    }

    /*
    Orders Programmer List by Id Ascending
    Set Programmer List
     */
    private void setProgrammerList(ArrayList<Programmer> programmers){
        //order list by id ascending
        programmers.sort(Comparator.comparing(Programmer::getId));

        //fill programmers
        this.programmers=programmers;
    }

    /*
    Convert Language String to ArrayList<String>
     */
    private List<String> fillLanguageList(String languages) {

        if(languages == null || languages.length()==0){
            return null;
        }

        //create list of languages
        List<String> languagesList = new ArrayList<>();

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
    private boolean isValidColorValue(String color){
        ProgrammerColor[] programmerColorArr = ProgrammerColor.values();
        for (ProgrammerColor colorOnEnum : programmerColorArr){
            if(colorOnEnum.name().equals(color)) {
                return true;
            }
        }
        return false;
    }
}
