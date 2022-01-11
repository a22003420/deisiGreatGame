package pt.ulusofona.lp2.deisiGreatGame;
//imports
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

        Tile emptyTile = createTileEmpty();
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
                AbyssSingletonFactory abyssFactory = getAbyssSingletonFactory();
                //Initialize all Tool Factory Types for the Game
                ToolFactorySingletonFactory toolFactoryFactory = getToolFactorySingletonFactory();

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
    Get Programmers for a given position
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
    Get all Programmers or only those not defeated for a given position
    If none is found returns null
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
            for (Programmer ignored : programmers)
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

        //calculate current player
        //inside getCurrentPlayer is checked if player is inGame
        Programmer currentPlayer = getCurrentPlayer();

        //check if current player is locked
        if(currentPlayer.isLocked()){
            return false;
        }

        //send message to programmer to move
        currentPlayer.move(getBoardSize(), nrPositions, false);

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
    Returns all Tiles
     */
    public ArrayList<Tile> getTiles(){
        return tiles;
    }

    /*
    Returns title for a given position
    */
    public Tile getTile(int position){
        return tiles.get(position);
    }

    /*
    Set Object on Tile
     */
    public void setTileObject(int position, Tile tile)
    {
        tiles.set(position, tile);
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

        //check file
        if (file == null || file.length() == 0){
            return false;
        }

        try {

            //fetch all lines in file
            List<String> lines = Files.readAllLines(Paths.get(file.getPath()));
            if(lines.size()!=9){
                return false;
            }

            //####### BEGIN GAME DATA

            //line 1 returns: nr of tiles and nr of turns
            String lineGameData = lines.get(1);
            String[] gameDataArr = lineGameData.split("\\|");
            if(gameDataArr.length != 2){
                return false;
            }

            //validate board size
            String savedBoardSizeS = gameDataArr[0];
            if(savedBoardSizeS.isEmpty()){
                return false;
            }

            int savedBoardSize;
            try {
                savedBoardSize = Integer.parseInt(savedBoardSizeS);
            } catch (Exception e) {
                return false;
            }
            if(savedBoardSize==0){
                return false;
            }

            String nrTurnsS = gameDataArr[1];
            if(nrTurnsS.isEmpty()){
                return false;
            }

            int nrTurns;
            try {
                nrTurns = Integer.parseInt(nrTurnsS);
            } catch (Exception e) {
                return false;
            }
            //######## END GAME DATA

            //####### BEGIN GAME DATA TILES

            //line 4 returns: board tiles
            String tilesGameData = lines.get(4);
            String[] tilesGameDataArr = tilesGameData.split("\\|");
            if(tilesGameDataArr.length != savedBoardSize+1){
                return false;
            }

            ArrayList<Tile> tiles = new ArrayList<>();

            //create Empty Tile
            Tile emptyTile = createTileEmpty();
            //Abyss for the Game
            AbyssSingletonFactory abyssFactory = getAbyssSingletonFactory();
            //Tool Factory Types for the Game
            ToolFactorySingletonFactory toolFactoryFactory = getToolFactorySingletonFactory();

            //fetch individual tiles: Type;SubType;Position
            for (String tile: tilesGameDataArr)
            {
                if(tile.trim().isEmpty()){
                    tiles.add(emptyTile);
                    continue;
                }

                //[Type;SubType
                String[] tileDataArr = tile.split(";");

                //#######
                //validate Object Type
                int typeObjectId; //0 - Abyss; 1 - Tool
                try {
                    typeObjectId = Integer.parseInt(tileDataArr[0]);
                } catch (Exception e) {
                    return false;
                }
                //only 0 - Abyss or 1 - Tool are allowed
                if (typeObjectId < 0 || typeObjectId > 1) {
                    return false;
                }

                //#######
                //validate Object Abyss Type and Tool Type is numeric
                int subTypeObject;
                try {
                    subTypeObject = Integer.parseInt(tileDataArr[1]);
                } catch (Exception e) {
                    return false;
                }

                //#######
                //validate Abyss Type and Tool Type
                //fills tile
                switch (typeObjectId) {
                    case 0 -> { //only Abyss Type [0-9] are allowed
                        if (subTypeObject < 0 || subTypeObject > 9) {
                            return false;
                        }
                        tiles.add(abyssFactory.getAbyss(subTypeObject));
                    }
                    case 1 -> { //only Tool Type [0-5] are allowed
                        if (subTypeObject < 0 || subTypeObject > 5) {
                            return false;
                        }
                        tiles.add(toolFactoryFactory.getToolFactory(subTypeObject));
                    }
                }
            }
            //####### END GAME DATA TILES

            //####### BEGIN GAME PROGRAMMERS

            //line 7 returns: programmers data

            String programmersGameData = lines.get(7);
            String[] programmersGameDataArr = programmersGameData.split("\\|");
            int nrProgrammers = programmersGameDataArr.length;

            //validate nr programmers
            if(!isValidNrPlayers(nrProgrammers)){
                return false;
            }

            //validate board size
            if(!isValidBoardSize(savedBoardSize, nrProgrammers)){
                return false;
            }

            //list of saved programmers to load
            ArrayList<Programmer> savedProgrammers = new ArrayList<>();

            //Tool singleton factory for add tool to saved programmer
            ToolSingletonFactory toolFactory = ToolSingletonFactory.getInstance();

            //fetch individual programmer data:
            for (String programmer: programmersGameDataArr)
            {
                //[Id];[Name];[Color];[Languages];[Tools];[Lock];[Status];[Position];[Statistics]

                String[] programmerArr = programmer.split(";");
                if(programmerArr.length!=9){
                    return false;
                }

                //### start saved Programmer Id
                int savedProgrammerId;
                try {
                    savedProgrammerId = Integer.parseInt(programmerArr[0]);
                } catch (Exception e) {
                    return false;
                }
                if(savedProgrammerId==0){
                    return false;
                }
                //### end saved Programmer Id

                //### start saved programmer name
                String programmerName = programmerArr[1];
                if(programmerName.length()==0){
                    return false;
                }
                //### end saved programmer name

                //### start saved programmer languages
                String savedLanguages = programmerArr[3];
                if(savedLanguages.length()==0){
                    return false;
                }
                //replace in string deliminator § by ; to reuse method
                String replaceString=savedLanguages.replace('§',';');
                //fill languages
                List<String> languages = fillLanguageList(replaceString);
                if(languages==null){
                    return false;
                }
                //### end saved programmer languages

                //### start saved programmer color
                String color = programmerArr[2];
                if(isValidColorValue(color)){
                    return false;
                }
                ProgrammerColor programmerColor = ProgrammerColor.valueOf(color.toUpperCase());
                //### end saved programmer color

                //create saved programmer
                Programmer savedProgrammer = new Programmer(savedProgrammerId, programmerName, languages,
                        programmerColor);

                //### start Saved Programmer Tools
                String savedTools = programmerArr[4];
                if(savedTools.length()>0) {
                    String[] savedProgrammerToolArr = programmerArr[4].split("§");
                    for (String toolID : savedProgrammerToolArr) {
                        int programmerSavedTool = Integer.parseInt(toolID);
                        savedProgrammer.addTool(toolFactory.getTool(programmerSavedTool));
                    }
                }
                //### End Saved Programmer Tools

                //### start Saved Programmer Lock
                String savedProgrammerLock = programmerArr[5];
                if(savedProgrammerLock.isEmpty()){
                    return false;
                }
                switch (savedProgrammerLock)
                {
                    case "true":
                        savedProgrammer.lock();
                        break;
                    case "false":
                        savedProgrammer.unlock();
                        break;
                    default:
                        return false;
                }
                //### End Saved Programmer Lock

                //### start Saved Programmer Status
                String savedProgrammerStatus = programmerArr[6];
                if(savedProgrammerStatus.isEmpty()){
                    return false;
                }
                if ("Derrotado".equals(savedProgrammerStatus)) {
                    savedProgrammer.gameOver();
                }
                //### End Saved Programmer Status

                //### start Saved Programmer Positions
                String savedPositions = programmerArr[7];
                String[] positions = savedPositions.split("§");
                ArrayList<Integer> programmerPositions = new ArrayList<>();
                for (String position: positions){
                    programmerPositions.add(Integer.parseInt(position));
                }
                savedProgrammer.setPositions(programmerPositions);
                //### End Saved Programmer Positions

                //### start Saved Programmer Positions
                String savedStatisticsPositions = programmerArr[8];
                String[] positionsStatistic = savedStatisticsPositions.split("§");
                ArrayList<Integer> programmerStatistics = new ArrayList<>();
                for (String statistic: positionsStatistic){
                    programmerStatistics.add(Integer.parseInt(statistic));
                }
                savedProgrammer.setPositionsStatistics(programmerStatistics);
                //### End Saved Programmer Positions

                //add saved programmer to saved programmer list
                savedProgrammers.add(savedProgrammer);
            }

            //####### END GAME PROGRAMMERS

            //reset existing game
            reiniciar();

            //load saved game values
            this.totalNrTurns = nrTurns;

            //sort programmer by ID and set game players
            setProgrammerList(savedProgrammers);

            this.tiles = tiles;
        } catch(IOException e) {
            return false;
        }

        return true;
    }

    /*
    save file game
    */
    public boolean saveGame(File file){

        //check file
        if(file == null || file.getName().length()==0){
            return false;
        }

        FileWriter filewriter = null;

        //final check for game data
        String gameData = getGameDataToSaveOnFile();
        if(gameData.isEmpty()){
            return false;
        }

        //final check for tiles data
        String tilesData = getTitlesToSaveOnFile();
        if(tilesData.isEmpty()){
            return false;
        }

        //final check for programmers data
        String playersData = getProgrammersToSaveOnFile();
        if(playersData.isEmpty()){
            return false;
        }

        try {

            filewriter = new FileWriter(file);

            //Begin Game Data
            filewriter.write("#BEGIN GAME DATA [Board Size];[Nr of Turns]\n");
            filewriter.write(gameData);
            filewriter.write("\n#END GAME DATA");
            //End Game Data

            //Begin Game Tiles Data
            filewriter.write("\n#BEGIN GAME TILES DATA [[] - Empty | 0 - Tools | 1 - Abyss]\n");
            filewriter.write(tilesData);
            filewriter.write("\n#END GAME TILES DATA");
            //End Game Tiles Data

            //Begin Player Data
            filewriter.write("\n#BEGIN PLAYERS DATA\n");
            filewriter.write(playersData);
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

    //Factory

    /*
    Returns a singleton instance of Tool Factory
    */
    private ToolFactorySingletonFactory getToolFactorySingletonFactory(){
        return ToolFactorySingletonFactory.getInstance();
    }

    /*
    Returns a singleton instance of Abyss Factory
     */
    private AbyssSingletonFactory getAbyssSingletonFactory() {
        return AbyssSingletonFactory.getInstance();
    }

    /*
    Creates an empty title
     */
    private Tile createTileEmpty() {
        return new Empty("Casa Vazia", "blank.png");
    }

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
    Get Game Data to Save on File
     */
    private String getGameDataToSaveOnFile(){

        int bordSize = getBoardSize();
        if(bordSize==0){
            return "";
        }

        //check if programmers exist on game
        if(programmers==null || programmers.isEmpty()){
            return "";
        }

        //get number of players
        int nrPlayers = programmers.size();

        if(!isValidNrPlayers(nrPlayers) || !isValidBoardSize(bordSize, nrPlayers)){
            return "";
        }

        return getBoardSize() + "|" + getNrTurns();
    }

    /*
    Get Tiles on Board to Save on File
     */
    private String getTitlesToSaveOnFile(){
        if(tiles==null || tiles.isEmpty()){
            return "";
        }

        StringBuilder sblAbyssAndTools = new StringBuilder();

        //get Tiles on board
        for (Tile tile: tiles)
        {
            if(tile!=null) {
                sblAbyssAndTools.append(tile.stringToSaveOnFile());
                sblAbyssAndTools.append("|");
            }
        }

        //remove right;
        if(sblAbyssAndTools.length()>0) {
            sblAbyssAndTools.delete(sblAbyssAndTools.length() - 1, sblAbyssAndTools.length());
        }

        return sblAbyssAndTools.toString();
    }

    /*
    Get Programmers Data to Save on File
     */
    private String getProgrammersToSaveOnFile(){
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

            sblPlayers.append(programmerData);
            sblPlayers.append("|");
        }

        //remove right;
        if(sblPlayers.length()>0) {
            sblPlayers.delete(sblPlayers.length() - 1, sblPlayers.length());
        }

        return sblPlayers.toString();
    }
}
