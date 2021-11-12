package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

/*
Represents the Game board Manager
 */
public class GameManager {

    /*
    Max Id allowed for user
     */
    static final int MAXID = 50;
    static final int MINID = 1;

    /*
    Board size
     */
    private int boardSize;

    /*
    Gamers on game
     */
    private List<Programmer> programmers;

    //Constructor
    public GameManager() {
    }

    /*
    Creates game initial board
    Validates data to cre
     */
    public boolean createInitialBoard(String[][] playerInfo, int boardSize)
    {
        //check null value
        if(playerInfo==null) {
            return false;
        }

        //check number of players
        Integer numberOfPlayers = playerInfo.length;
        if(numberOfPlayers<2 || numberOfPlayers>4){
            return false;
        }

        //check board size
        if(boardSize<numberOfPlayers* 2){
            return false;
        }

        //list of programmers/players
        List<Programmer> programmerList = new ArrayList<>();

        //iterate over matrix
        for (int row = 0; row < playerInfo.length; row++)
        {
            //Id
            Integer id = 0;
            try{
                id = Integer.parseInt(playerInfo[row][0]);
            }
            catch (Exception e)
            {
                return false;
            }

            //validate max id
            if(id<MINID || id>MAXID)
            {
                return false;
            }

            //Name
            String name = playerInfo[row][1];

            //Validate Name
            if(name == null || name.length()==0) {
                return false;
            }

            //Programming Languages
            String languages = playerInfo[row][2]; //C#;Java;....
            //Validate languages length
            if(languages==null || languages.length()==0){
                return false;
            }

            //Add to language
            ArrayList<String> languagesList = addToLanguageList(languages);

            //Color
            String color = playerInfo[row][3];
            //Validate parameter color length
            if(color ==null || color.length()==0 || !isValidColorValue(color)) {
                return false;
            }

            ProgrammerColor enumColor = ProgrammerColor.valueOf(color);

            //validation on second iteration
            if(row>1)
            {
                for (Programmer programmer : programmerList)
                {
                    //validate unique id
                    if(programmer.getId() == id) {
                        return false;
                    }

                    //validate unique name
                    //if(programmer.getName() == name) {
                    //return false;
                    //}

                    //validate unique color
                    if(programmer.getColor().equals(enumColor)) {
                        return false;
                    }
                }
            }

            //Instantiate programmer
            Programmer programmer = new Programmer(id, name, languagesList, enumColor);
            programmerList.add(programmer);
        }

        if(programmerList.size()<2 || programmerList.size()>4)       {
            return false;
        }

        // Board Size two positions for each player
        if(boardSize >= programmerList.size()*2){
            return false;
        }

        //set boardsize
        this.boardSize = boardSize;

        //Order programmers by name
        programmerList.sort(Comparator.comparing(Programmer::getName));
        this.programmers = programmerList;

        return false;
    }

    /*
    Add to language list
     */
    private ArrayList<String> addToLanguageList(String languages) {
        String[] languagesArr = languages.split(";");
        ArrayList<String> languagesList = new ArrayList<>();
        for (String language: languagesArr)
        {
            if (!languagesList.contains(language)) {
                languagesList.add(language);
            }
        }
        return languagesList;
    }

    /*
    Validate color value
     */
    private boolean isValidColorValue(String color)
    {
        boolean returnExistColor = false;

        //Validate parameter color value
        ProgrammerColor[] programmerColorArr = ProgrammerColor.values();
        for (ProgrammerColor colorOnEnum : programmerColorArr) {
            if (colorOnEnum.equals(color)) {
                returnExistColor = true;
                if(returnExistColor) {
                    break;
                }
            }
        }
        return returnExistColor;
    }

    /*
    Get player image for given position
     */
    public String getImagePng(int position){

        String image = null;

        if(position>boardSize){
            return image;
        }

        if(position==boardSize){
            image="glory.png";
        }

        return image;
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
    public ArrayList<Programmer> getProgrammers(int position){

        if(position>boardSize){
            return null;
        }

        List<Programmer> programmerOnPositionList = programmers.stream().filter(c -> c.getPosition()==position)
                .collect(toList());

        return new ArrayList(programmerOnPositionList);
    }

    /*
    Get current player Id
     */
    public int getCurrentPlayerID(){

        List<Programmer> programmerList = getProgrammers();

        if (programmerList==null || programmerList.size()==0){
            return 0;
        }

        for(Programmer programmer:programmerList){
            if(programmer.isCurrentPlayer()){
                return programmer.getId();
            }
        }

        return 0;
    }

    /*
    Move player n positions
     */
    public boolean moveCurrentPlayer(int nrPositions){

        if(nrPositions<1 || nrPositions>6) {
            return false;
        }

        List<Programmer> programmerList = getProgrammers();
        if (programmerList==null || programmerList.size()==0){
            return false;
        }

        //current player
        int index;
        int nrOfPlayers = programmerList.size();
        for (index = 0; index<nrOfPlayers; index++)
        {
            Programmer programmer = programmerList.get(index);

            if(programmer.isCurrentPlayer())
            {
                Integer position = programmer.getPosition();
                Integer newPosition = position+nrPositions;

                //TODO: Check BoardSize

                programmer.setPosition(newPosition);

                break;

            }
        }

        //check if exist next player
        if(++index>nrOfPlayers){
            index=0;
        }

        //get next player
        Programmer programmer = programmerList.get(index);
        programmer.isCurrentPlayer();

        return true;
    }

    /*
    Check if game is over
     */
    public boolean gameIsOver(){

        List<Programmer> programmerList = getProgrammers();

        if (programmerList==null || programmerList.size()==0){
            return false;
        }

        for (Programmer programmer:programmerList) {
            if(programmer.getPosition()==boardSize){
                return true;
            }
        }
        return false;
    }

    /**
    Get game statistics
     */
    public ArrayList<String> getGameResults(){
        return null;
    }

    /*
    Get About
     */
    public JPanel getAuthorsPanel(){
        return null;
    }
}
