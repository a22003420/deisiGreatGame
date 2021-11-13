package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;

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
    private List<Programmer> programmers;

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
        List<Programmer> programmerList = new ArrayList<>();

        //iterate over matrix
        for (int row = 0; row < playerInfo.length; row++)
        {
            //Id
            int id;
            try{
                id = Integer.parseInt(playerInfo[row][0]);
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
            String name = playerInfo[row][1];
            if(name == null || name.length()==0) {
                return false;
            }
            //End:Name
            //###

            //###
            //Begin: Programming Languages
            String languagesS = playerInfo[row][2]; //C#;Java;....
            if(languagesS==null || languagesS.length()==0){
                return false;
            }

            //Convert Language String to ArrayList<String>
            ArrayList<String> languagesList = convertLanguageStringToArrayList(languagesS);
            //End: Programming Languages
            //###

            //####
            //Begin: Color
            String color = playerInfo[row][3];
            if(color == null || !isValidColorValue(color)) {
                return false;
            }
            ProgrammerColor enumColor = ProgrammerColor.valueOf(color);
            //End: Color
            //####

            //validation on second iteration
            if(row>1)
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

            //Create programmer and to programmer list
            programmerList.add(new Programmer(id, name, languagesList, enumColor));
        }

        //count nr of Players
        int programmerCount = programmerList.size();

        //check programmer list size
        if(programmerCount<2 || programmerCount>4){
            return false;
        }

        // Board Size must have at least two positions for each player
        if(boardSize < programmerCount*2){
            return false;
        }

        //set BoardSize
        setBoardSize(boardSize);

        //set programmers
        setProgrammerList(programmerList);

        while (!gameIsOver())
        {
            Programmer programmer = getCurrentPlayer();
            programmer.play();
            moveCurrentPlayer(programmer.getNrPositionsToMove());
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

        if(position==boardSize){
            return "glory.png";
        }

        return null;
    }

    /*
    Get players
     */
    public List<Programmer> getProgrammers(){
        return this.programmers;
    }

    /*
    Get players on a given position
     */
    public ArrayList getProgrammers(int position){

        if(position==0 || position>boardSize){
            return null;
        }

        return new ArrayList(programmers.stream().filter(c -> c.getBoardPosition()==position)
                .collect(toList()));
    }

    /*
    Get current player ID
     */
    public int getCurrentPlayerID(){

        List<Programmer> programmerList = getProgrammers();
        /*
        if (programmerList==null){
            return 0;
        }
        */

        for(Programmer programmer:programmerList){
            if(programmer.isCurrentPlayer()){
                return programmer.getId();
            }
        }

        return 0;
    }

    /*
    Get current player
     */
    public Programmer getCurrentPlayer(){

        List<Programmer> programmerList = getProgrammers();

        for(Programmer programmer:programmerList){
            if(programmer.isCurrentPlayer()){
                return programmer;
            }
        }

        return null;
    }

    /*
    Move player n positions
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
                programmer.setPosition(newPosition);
                //set current programmer not current player
                programmer.setCurrentPlayer(false);

                //Increment turn on game turns
                addTurn();

                break;
            }
        }

        //calculate next player
        if(++index==nrOfPlayers){
            index=0;
        }

        //get next player
        Programmer programmer = programmerList.get(index);
        //set next player new current player
        programmer.setCurrentPlayer(true);

        return true;
    }

    /*
    Check if game is over
     */
    public boolean gameIsOver(){

        List<Programmer> programmerList = getProgrammers();

        /*
        if (programmerList==null || programmerList.size()==0){
            return false;
        }
        */

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
    Add Turn to total turns
     */
    private void addTurn()
    {
        this.totalNrTurns +=1;
    }

    /*
    Set Programmer List
     */
    private void setProgrammerList(List<Programmer> programmerList)
    {
        //order list ascending by id
        programmerList.sort(Comparator.comparing(Programmer::getId));

        //fetch first programmer from list
        Programmer programmer = programmerList.get(0);

        //set first programmer as current player
        programmer.setCurrentPlayer(true);

        this.programmers=programmerList;
    }

    /*
    Convert Language String to ArrayList<String>
     */
    private ArrayList<String> convertLanguageStringToArrayList(String languages) {

        //split string by ";"
        String[] languagesArr = languages.split(";");

        //fill list of languages
        ArrayList<String> languagesList = new ArrayList<>();
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
    Validate if programmer color is valid
     */
    private boolean isValidColorValue(String color)
    {
        ProgrammerColor[] programmerColorArr = ProgrammerColor.values();
        for (ProgrammerColor colorOnEnum : programmerColorArr){
            if(colorOnEnum.toString().equals(color)) {
                return true;
            }
        }
        return false;
    }



}
