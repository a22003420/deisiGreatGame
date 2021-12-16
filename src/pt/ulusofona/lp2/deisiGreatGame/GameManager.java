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

    public boolean createInitialBoard(String[][] playerInfo, int worldSize, String[][] abyssesAndTools){

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
                        description="Dia de sorte, tem uma tool Herança";
                        toolAbyss = new Tool(effectsID,"Herança","inheritance.png"
                                ,description,position);
                        break;
                    case 1:
                        description="Dia de sorte, tem uma tool Programação Funcional";
                        toolAbyss = new Tool(effectsID,"Programação Funcional","functional.png"
                                ,description,position);
                        break;

                    case 2:
                        description="Dia de sorte, tem uma tool Testes unitários";
                        toolAbyss = new Tool(effectsID,"Testes unitários","unit-tests.png"
                                ,description,position);
                        break;
                    case 3:
                        description="Dia de sorte, tem uma tool Tratamento de Excepções";
                        toolAbyss = new Tool(effectsID,"Tratamento de Excepções","exception.png"
                                ,description,position);
                        break;
                    case 4:
                        description="Dia de sorte, tem uma tool IDE";
                        toolAbyss = new Tool(effectsID,"IDE","IDE.png"
                                ,description,position);
                        break;
                    case 5:
                        description="Dia de sorte, tem uma tool Ajuda Do Professor";
                        toolAbyss = new Tool(effectsID,"Ajuda Do Professor","ajuda-professor.png"
                                ,description,position);
                        break;
                    default:
                        return false;
                }
            }
            else{
                switch (effectsID){
                    case 0:
                        description="Azar caiu num Abismo";
                        toolAbyss = new AbyssSyntax(effectsID,"Erro de sintaxe","syntax.png"
                                ,description,position);
                        break;
                    case 1:
                        description="Azar caiu num Abismo";
                        toolAbyss = new AbyssLogic(effectsID,"Erro de lógica ","logic.png"
                                ,description,position);
                        break;

                    case 2:
                        description="Azar caiu num Abismo";
                        toolAbyss = new AbyssException(effectsID,"Exception","exception.png"
                                ,description,position);
                        break;
                    case 3:
                        description="Azar caiu num Abismo";
                        toolAbyss = new AbyssFileNotFoundException(effectsID,"File Not Found Exception",
                                "file-not-found-exception.png"
                                ,description,position);
                        break;
                    case 4:
                        description="Azar caiu num Abismo";
                        toolAbyss = new AbyssCrash(effectsID,"Crash",""
                                ,description,position);
                        break;
                    case 5:
                        description="Azar caiu num Abismo";
                        toolAbyss = new AbyssDuplicatedCode(effectsID,"Duplicated Code ","crash.png"
                                ,description,position);
                        break;
                    case 6:
                        description="Azar caiu num Abismo";
                        toolAbyss = new AbyssSecondaryEffects(effectsID,"Efeitos secundários "
                                ,"secondary-effects.png"
                                ,description,position);
                        break;
                    case 7:
                        description="Azar caiu num Abismo";
                        toolAbyss = new AbyssBlueScreenOfDeath(effectsID,"Blue Screen of Death","bsod.png"
                                ,description,position);
                        break;
                    case 8:
                        description="Azar caiu num Abismo";
                        toolAbyss = new AbyssInfiniteCycle(effectsID,"Ciclo infinito","infinite-loop.png"
                                ,description,position);
                        break;
                    case 9:
                        description="Azar caiu num Abismo";
                        toolAbyss = new AbyssSegmentationFault(effectsID,"Segmentation Fault"
                                ,"core-dumped.png"
                                ,description,position);
                        break;

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

        if(position==1){
            return "start.png";
        }

        if(position==boardSize){
            return "glory.png";
        }

        for(ToolAbyss toolAbyss: toolsAndAbysses){
            if(toolAbyss.getPosition()==position){
                return toolAbyss.getImage();
            }
        }

        return null;
    }

    public String getTitle(int position){

        if(position<0 || position>boardSize){
            return null;
        }

        for(ToolAbyss toolAbyss: toolsAndAbysses){
            if(toolAbyss.getPosition()==position){
                return toolAbyss.getTitle();
            }
        }

        return null;
    }

    /*
    Get players
     */

    //Devolve uma lista agora com todos os objects Programmers
    public List<Programmer> getProgrammers(boolean includeDefeated)
    {
        List<Programmer> programmers = new ArrayList<>();
        for(Programmer programmer : this.programmers){
            if(includeDefeated){
                programmers.add(programmer);
            }
            else{
                if(programmer.inGame()){
                    programmers.add(programmer);
                }
            }
        }
            return programmers;
    }

    /*
    Get players on a given position
    If none found returns null
     */
    public List<Programmer> getProgrammers(int position){

        if(position==0 || position>boardSize || programmers == null){
            return null;
        }

        List<Programmer> programmerList = new ArrayList<>();
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
        List<Programmer> programmerArrayList = getProgrammers(false);

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
    Get Programmers Info
     */
    public String getProgrammersInfo(){
        //create concatenated programmers with ;
        StringBuilder strProgrammers = new StringBuilder();
        for (Programmer programmer : getProgrammers(false)) {
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

    public String reactToAbyssOrTool(){
        ToolAbyss selectedToolAbyss=null;
        for (ToolAbyss toolAbyss:toolsAndAbysses){
            if(toolAbyss.getPosition()==getCurrentPlayer().getBoardPosition()){
                toolAbyss.executeEffects(getProgrammers(getCurrentPlayer().getBoardPosition()));
                selectedToolAbyss=toolAbyss;
            }
        }

        addTurn();
        return selectedToolAbyss!=null ? selectedToolAbyss.getDescription():null;
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

        if(currentPlayer.inGame() && !currentPlayer.isLocked()) {

            //get current player position
            int currentPlayerPosition = currentPlayer.getBoardPosition();

            //get board dimension
            int boardSize = getBoardSize();

            //calculate new current player position
            int newPosition = currentPlayerPosition + nrPositions;

            //set position
            currentPlayer.setBoardPosition(newPosition > boardSize ? (boardSize - (newPosition - boardSize)) : newPosition);

            //add turn to game turns
             //addTurn(); para apagar


        return true;
        }
        return false;
    }

    /*
    Check if game is over
     */
    public boolean gameIsOver(){

        List<Programmer> programmerList = getProgrammers(false);

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
    public List<String> getGameResults() {

        List<String> resultList = new ArrayList<>();

        resultList.add("O GRANDE JOGO DO DEISI");
        resultList.add("");
        resultList.add("NR. DE TURNOS");

        List<Programmer> programmerList = getProgrammers(true);

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
        this.totalNrTurns++; // add
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
