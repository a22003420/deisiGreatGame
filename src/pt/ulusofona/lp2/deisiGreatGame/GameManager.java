package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
Represents the Game board Manager
 */
public class GameManager {

    /*
    Range Id allowed for gammer
     */
    static final int MAXID = 50;
    static final int MINID = 1;

    /*
    Board size
     */
    private int boardSize;

    /*
    Total number of turns
     */
    private int totalNrTurns;

    /*
    Gamers on game
     */
    private ArrayList<Programmer> programmers;

    //###########
    //REQUIRED Constructor
    public GameManager(){

    }

    //###########
    //PUBLIC METHODS

    /*
    Creates game initial board
     */
    public boolean createInitialBoard(String[][] playerInfo, int boardSize)
    {
        //check null value
        if(playerInfo==null) {
            return false;
        }

        //check number of players and board size before validation
        int numberOfPlayers = playerInfo.length;
        if(numberOfPlayers<2 || numberOfPlayers>4 || boardSize<numberOfPlayers* 2){
            return false;
        }

        //list of programmers/players to fill
        ArrayList<Programmer> programmerList = new ArrayList<>();

        //iterate over matrix
        //each row represents a player
        int playerRow;
        for (playerRow = 0; playerRow < playerInfo.length; playerRow++)
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

            //validate min and max id
            if(id<MINID || id>MAXID)
            {
                return false;
            }

            //###
            //Begin:Name
            String name = playerInfo[playerRow][1];
            if(name == null || name.length()==0) {
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
            String color = playerInfo[playerRow][3];
            if(color == null || !isValidColorValue(color)) {
                return false;
            }
            PlayerColor enumColor = PlayerColor.valueOf(color);
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

            //Create programmer and add to programmer list
            programmerList.add(new Programmer(id, name, languagesList, enumColor));
        }

        //count nr of Players
        ++playerRow;

        //check programmer list size
        //Board Size must have at least two positions for each player
        if((playerRow<2 || playerRow>4) || (boardSize < playerRow*2)){
            return false;
        }

        //set BoardSize
        setBoardSize(boardSize);

        //sort programmer by Id and set game players
        setProgrammerList(programmerList);

        return true ;
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

        if(position==boardSize){
            return "glory.png";
        }

        return null;
    }

    /*
    Get players
     */
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

        ArrayList<Programmer> programmerArrayList = new ArrayList<>();
        for (Programmer programmer: programmers)
        {
            if(programmer.getId() == position)
            {
                programmerArrayList.add(programmer);
            }
        }

        if(programmerArrayList.size() == 0)
            return null;

        return programmerArrayList;
    }

    /*
    Get current player ID
     */
    public int getCurrentPlayerID()
    {
        List<Programmer> programmerList = getProgrammers();
        if (programmerList==null){
            return 0;
        }

        int index = (getNrTurns() % programmerList.size());

        return programmerList.get(index > 0 ? --index : index).getId();
    }

    /*
    Move current player n positions
     */
    public boolean moveCurrentPlayer(int nrPositions){

        //check number positions range
        if(nrPositions<1 || nrPositions>6) {
            return false;
        }

        //get current player id
        int currentPlayerId = getCurrentPlayerID();
        if(currentPlayerId==0){
            return false;
        }

        //get programmers List
        List<Programmer> programmerList = getProgrammers();

        //find programmer for currentPlayerId
        int index;
        int nrOfPlayers = programmerList.size();
        for (index = 0; index<nrOfPlayers; index++)
        {
            Programmer programmer = programmerList.get(index);
            if(programmer.getId() == currentPlayerId)
            {
                int position = programmer.getBoardPosition();
                int newPosition = position+nrPositions;

                //check if new position is greater than boardSize
                if(newPosition>boardSize)
                {
                    newPosition = boardSize - (newPosition - boardSize);
                }

                //Set current programmer player new Position
                programmer.setBoardPosition(newPosition);

                break;
            }
        }

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
        else {
            resultList.add(Integer.toString(totalNrTurns));
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
    Get About
     */
    public JPanel getAuthorsPanel(){
        return null;
    }

    //################
    //PRIVATE METHODS

    /*
    Get total number of turns played
     */
    private int getNrTurns(){
        return totalNrTurns;
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
    private void setProgrammerList(ArrayList<Programmer> programmerList)
    {
        //order list ascending by id ascending
        programmerList.sort(Comparator.comparing(Programmer::getId));
        //fill attribute
        this.programmers=programmerList;
    }

    /*
    Convert Language String to ArrayList<String>
     */
    private ArrayList<String> fillLanguageList(String languages) {

        if(languages == null || languages.length()==0)
            return null;

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
        return languagesList == null ? null : languagesList;
    }

    /*
    Validate if color is valid, exist on enumerator
     */
    private boolean isValidColorValue(String color)
    {
        PlayerColor[] programmerColorArr = PlayerColor.values();
        for (PlayerColor colorOnEnum : programmerColorArr){
            if(colorOnEnum.toString().equals(color)) {
                return true;
            }
        }
        return false;
    }

}
