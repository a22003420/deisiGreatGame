package pt.ulusofona.lp2.deisiGreatGame;
//imports
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
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
    public void createInitialBoard(String[][] playerInfo, int worldSize) throws InvalidInitialBoardException{
        createInitialBoard(playerInfo, worldSize, null);
    }


    /*
    Creates initial board. includes: Empty, Tool Factory Tile and Abyss Tiles
     */
    public void createInitialBoard(String[][] playerInfo, int worldSize, String[][] abyssesAndTools)
            throws InvalidInitialBoardException{

        reiniciar();

        //check null value
        if(playerInfo==null){
            throw new InvalidInitialBoardException("No players info", ExceptionType.GAME);
        }

        //calculate nr of players
        int numberOfPlayers = playerInfo.length;

        //check number of players
        if (!isValidNrPlayers(numberOfPlayers)){
            throw new InvalidInitialBoardException("Invalid number of players", ExceptionType.BOARD);
        }

        //check board size
        if (!isValidBoardSize(worldSize, numberOfPlayers)){
            throw new InvalidInitialBoardException("Invalid board size", ExceptionType.BOARD);
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
                throw new InvalidInitialBoardException("Invalid player Id", ExceptionType.PLAYER);
            }

            //validate min id
            if(id<1){
                throw new InvalidInitialBoardException("Invalid player Id", ExceptionType.PLAYER);
            }

            //###
            //Begin:Name
            String name = playerInfo[playerRow][1];
            if(name == null || name.isEmpty()){
                throw new InvalidInitialBoardException("Invalid player name", ExceptionType.PLAYER);
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
                throw new InvalidInitialBoardException("Invalid player color", ExceptionType.PLAYER);
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
                    if(programmer.getId() == id){
                        throw new InvalidInitialBoardException("Duplicated player Id", ExceptionType.PLAYER);
                    }

                    //validate unique player color
                    if(programmer.getColor().equals(enumColor)){
                        throw new InvalidInitialBoardException("Duplicated player color", ExceptionType.PLAYER);
                    }
                }
            }

            //Create new programmer and add to programmer list
            programmerList.add(new Programmer(id, name, languagesList, enumColor));
        }

        //sort programmer by ID and set game players
        setProgrammerList(programmerList);

        //######
        //Create and fill all tiles
        this.tiles = new ArrayList<>();

        Tile emptyTile = new Empty("Casa Vazia", "blank.png");
        int tileRow;
        for (tileRow = 0; tileRow <= worldSize; tileRow++){
            tiles.add(emptyTile);
        }

        if(abyssesAndTools!=null) {
            //fill tile with Objects
            for (String[] abyssesAndTool : abyssesAndTools) {
                //#######
                //validate position
                int tilePosition;
                try {
                    tilePosition = Integer.parseInt(abyssesAndTool[2]);
                } catch (Exception e) {
                    throw new InvalidInitialBoardException("Invalid tile position", ExceptionType.BOARD);
                }

                if (tilePosition > worldSize) {
                    throw new InvalidInitialBoardException("Invalid tile position", ExceptionType.BOARD);
                }

                //#######
                //validate Object Type
                int typeObjectId; //0 - Abyss; 1 - Tool
                try {
                    typeObjectId = Integer.parseInt(abyssesAndTool[0]);
                } catch (Exception e) {
                    throw new InvalidInitialBoardException("Invalid tile type", ExceptionType.BOARD);
                }
                //only 0 - Abyss or 1 - Tool are allowed
                if (typeObjectId < 0 || typeObjectId > 1) {
                    throw new InvalidInitialBoardException("Invalid tile type", ExceptionType.BOARD);
                }

                //#######
                //validate Object Abyss Type and Tool Type is numeric
                int subTypeObject;
                try {
                    subTypeObject = Integer.parseInt(abyssesAndTool[1]);
                } catch (Exception e) {
                    throw new InvalidInitialBoardException("Invalid Abyss or Tool Type", ExceptionType.BOARD, 0);
                }

                //Initialize all Abyss for the Game
                AbyssSingletonFactory abyssFactory = AbyssSingletonFactory.getInstance();
                //Initialize all Tool Factory Types for the Game
                ToolFactorySingletonFactory toolFactoryFactory = ToolFactorySingletonFactory.getInstance();

                //#######
                //validate Abyss Type and Tool Type
                //fills tile
                switch (typeObjectId) {
                    case 0 -> { //only Abyss Type [0-9] are allowed
                        if (subTypeObject < 0 || subTypeObject > 9) {
                            throw new InvalidInitialBoardException("Invalid Abyss", ExceptionType.ABYSS, typeObjectId);
                        }
                        tiles.set(tilePosition, abyssFactory.getAbyss(subTypeObject));
                    }
                    case 1 -> { //only Tool Type [0-5] are allowed
                        if (subTypeObject < 0 || subTypeObject > 5) {
                            throw new InvalidInitialBoardException("Invalid Tool", ExceptionType.TOOL, typeObjectId);
                        }
                        tiles.set(tilePosition, toolFactoryFactory.getToolFactory(subTypeObject));
                    }
                }
            }
        }
    }

    /*
    Get Tile title for a given position
     */
    public String getTitle(int position){
        return getTile(position).getTitle();
    }

    /*
    Get tile image for a given position
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

        //Title needs to know who is current player
        Programmer currentPlayer = getCurrentPlayer();
        int position = currentPlayer.currentPosition();
        Tile tile =  tiles.get(position);

        //Tile needs to know all programmers in game that exist on this tile
        List<Programmer> programmers = getProgrammers(position, false);

        //add turn to game turns
        addTurn();

        //react to tile
        return tile.reactToAbyssOrTool(programmers, currentPlayer, getBoardSize());
    }

    /*
    Check if game is over
     */
    public boolean gameIsOver(){

        List<Programmer> programmerList = getProgrammers(true);

        boolean isGameOver = false;

        //check if there is a programmer on finish  position
        for (Programmer programmer:programmerList)
        {
            if(programmer.currentPosition()== getBoardSize())
            {
                isGameOver = true;
                break;
            }
        }

        if(!isGameOver)
        {
            //check if there is only one programmer in game
            isGameOver = programmerList.stream().filter(Programmer::inGame).count() == 1;
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

        List<Programmer> programmerList = getProgrammers(false);

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

        //list to store all loser programmers
        List<Programmer> loserProgrammerList = new ArrayList<>();

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
                loserProgrammerList.add(programmer);
            }
        }

        //order by Position Descending
        if(loserProgrammerList.size()>2)
        {
            //first name comparator
            Comparator<Programmer> compareByPosition = Comparator.comparing( Programmer::currentPosition ).reversed();

            //last name comparator
            Comparator<Programmer> compareByName = Comparator.comparing( Programmer::getName );

            //Compare by first name and then last name (multiple fields)
            Comparator<Programmer> compareByPositionAndName = compareByPosition.thenComparing(compareByName);

            //Use Comparator
            loserProgrammerList.sort(compareByPositionAndName);
        }

        for (Programmer loserProgrammer: loserProgrammerList)
        {
            resultList.add(loserProgrammer.getName() + " " + loserProgrammer.currentPosition());
        }

        return resultList;
    }

    //END PUBLIC METHODS: BOARD
    //###########

    //###########
    //BEGIN PUBLIC METHODS: PROGRAMMERS

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
    Get all Programmers or only those not defeated
     */
    public List<Programmer> getProgrammers(boolean includeDefeated){

        if(programmers==null) {
            return null;
        }

        return includeDefeated ? this.programmers : programmers.stream().collect(Collectors.filtering(Programmer::inGame, Collectors.toList()));
    }

    /*
    Get Programmers in game on a given position
    If none found returns null
     */
    public List<Programmer> getProgrammers(int position, boolean includeDefeated){

        if(position==0 || position>getBoardSize() || programmers == null){
            return null;
        }

        List<Programmer> programmerList = getProgrammers(position);
        if(programmerList.size()==0) {
            return new ArrayList<>();
        }

        return programmerList.stream().collect(Collectors.filtering(Programmer::inGame, Collectors.toList()));
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
        List<Programmer> programmers = getProgrammers(true);

        //calculate number of players
        int nrPlayers = programmers.size();

        //nr turns
        int nrTurns = getNrTurns();

        //calculate current player index
        int index = nrTurns % nrPlayers;

        //return current player if in game or return next player in game
        Programmer currentProgrammer = programmers.get(index);
        if(!currentProgrammer.inGame())
        {
            for (Programmer progammer: programmers)
            {
                index ++;
                if(index+1 > programmers.size())
                {
                    index = 0;
                }

                currentProgrammer = programmers.get(index);
                if(currentProgrammer.inGame())
                {
                    return currentProgrammer;
                }
            }
        }

        return currentProgrammer;
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
        if(getCurrentPlayer().isLocked() || !getCurrentPlayer().inGame()){
            return false;
        }

        //send message to programmer to move
        getCurrentPlayer().move(getBoardSize(), nrPositions, false);

        return true;
    }

    /*
    Get Programmers Info
    */
    public String getProgrammersInfo(){

        List<Programmer> programmerInGameList =  getProgrammers(false);

        //create concatenated programmers with ;
        StringBuilder strProgrammers = new StringBuilder();
        for (Programmer programmer : programmerInGameList) {
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
    Returns title in a given position
    */
    public Tile getTile(int position){
        return tiles.get(position);
    }

    /*
    Get About
    */
    public JPanel getAuthorsPanel() {
        JPanel newPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("                 ---João Matos & Rui Fazendeiro---");
        newPanel.add(label, BorderLayout.CENTER);
        return newPanel;
    }

    //###########
    //BEGIN PUBLIC METHODS: FILE

    /*
    load file game
    */
    public boolean loadGame(File file) {

        try {

            if (file.length() == 0){
                return false;
            }

            Scanner reader = new Scanner(file);
            while(reader.hasNext()) {
                String line = reader.next();
            }
        }
        catch(FileNotFoundException e) {
            return false;
        }

        return true;
    }

    /*
    save file game
    */
    public boolean saveGame(File file){

        FileWriter filewriter = null;

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String gameData = getGameDataToSaveOnFile();
        if(gameData.isEmpty()){
            return false;
        }

        String playersData = getProgrammersDataToSaveOnFile();
        if(playersData.isEmpty()){
            return false;
        }

        try {

            filewriter = new FileWriter(file);
            //Writing date time to the beginning of file
            filewriter.write(date.format(dateFormat));

            //Begin Game Data
            filewriter.write("\n#BEGIN GAME DATA [Board Size]§[Nr of Turns]§[0 - Tools and 1 - Abyss]\n");
            filewriter.write(getGameDataToSaveOnFile());
            filewriter.write("\n#END GAME DATA");
            //End Game Data

            //Begin Player Data
            filewriter.write("\n#BEGIN PLAYERS DATA\n");
            filewriter.write(getProgrammersDataToSaveOnFile());
            filewriter.write("\n#END PLAYERS DATA");
            //Ends Player Data

            //Closing the stream
            filewriter.close();
        }
        catch(Exception e) {
            return false;
        }
        finally {

            try {
                if (filewriter != null) {
                    filewriter.close();
                }
            } catch (Exception e) {
                return false;
            }
        }

        return true;
    }

    //END PUBLIC METHODS: FILE
    //###########

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
    Add turn to total turns
    */
    private void addTurn(){
        this.totalNrTurns +=1;
    }

    /*
    Orders Programmers by Id Ascending
    Set Programmer List
    */
    private void setProgrammerList(ArrayList<Programmer> programmers){

        //order by id ascending
        programmers.sort(Comparator.comparing(Programmer::getId));

        //set programmers
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

    //###### TO USE IN FILE

    /*

     */
    private String getGameDataToSaveOnFile(){

        int bordSize = getBoardSize();
        if(bordSize==0){
            return "";
        }

        if(programmers==null || programmers.isEmpty()){
            return "";
        }

        int nrPlayers = programmers.size();

        if(!isValidNrPlayers(nrPlayers) || !isValidBoardSize(bordSize, nrPlayers) || getTitlesOnBoardDataToSaveOnFile().isEmpty()){
            return "";
        }

        return getBoardSize() + "§" + getNrTurns() + "§" + getTitlesOnBoardDataToSaveOnFile();
    }

    /*

     */
    private String getTitlesOnBoardDataToSaveOnFile()
    {
        if(tiles==null || tiles.isEmpty()){
            return "";
        }

        StringBuilder sblAbyssAndTools = new StringBuilder();

        //get Abyss or Tool Factory Tiles on board
        int position = 0;
        for (Tile tile: tiles)
        {
            if(tile!=null) {
                sblAbyssAndTools.append(tile);
                sblAbyssAndTools.append("#");
                sblAbyssAndTools.append(position);
                sblAbyssAndTools.append(";");
            }
            position++;
        }

        //remove right;
        if(sblAbyssAndTools.length()>0) {
            sblAbyssAndTools.delete(sblAbyssAndTools.length() - 1, sblAbyssAndTools.length());
        }

        return sblAbyssAndTools.toString();
    }

    /*

     */
    private String getProgrammersDataToSaveOnFile()
    {
        if(programmers==null || programmers.isEmpty()){
            return "";
        }

        StringBuilder sblPlayers = new StringBuilder();

        //get Abyss or Tool Factory Tiles on board
        for (Programmer programmer: programmers)
        {
            String programmerData = programmer.getProgrammerDataToSaveOnFile();
            if(programmerData.isEmpty()){
                return "";
            }

            sblPlayers.append(programmer.getProgrammerDataToSaveOnFile());
            sblPlayers.append("§");
        }

        //remove right;
        if(sblPlayers.length()>0) {
            sblPlayers.delete(sblPlayers.length() - 1, sblPlayers.length());
        }

        return sblPlayers.toString();
    }
}
