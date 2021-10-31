package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Represents the Game board Manager
 */
public class GameManager {

    /*
    Max Id allowed for user
     */
    static final int maxId = 50;
    static final int minId = 50;

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
            if(id<minId || id>maxId)
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
            if(languages==null || languages.length()==0)
                return false;
            //Add to language
            ArrayList<String> languagesList = addToLanguageList(languages);

            //Color
            String color = playerInfo[row][3];
            //Validate parameter color length
            if(color ==null || color.length()>0)
                return false;
            if (!isValidColorValue(color)) {
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
                    if(programmer.getName() == name) {
                        return false;
                    }

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

        if(boardSize != programmerList.stream().count() * 2)
            return false;

        this.boardSize = boardSize;

        //Collections.sort(programmerList.);

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

        return "";

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

        ArrayList<Programmer> programmerList = new ArrayList<>();

        for (Programmer programmer: programmerList)
        {

        }
        return null;
    }

    /*
    Get current player Id
     */
    public int getCurrentPlayerID(){
        return 0;
    }

    /*
    Move player n positions
     */
    public boolean moveCurrentPlayer(int nrPositions){

        List<Programmer> programmerList = getProgrammers();



        //Integer nrOfPositions = throwDice();



        return false;

    }

    /*
    Check if game is over
     */
    public boolean gameIsOver(){



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
