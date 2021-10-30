package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import javax.swing.*;
import java.util.ArrayList;

/*
Represents a Game Manager
 */
public class GameManager {

    int boardSize;
    ArrayList<Programmer> programmers;

    /*

     */
    public boolean createInitialBoard(String[][] playerInfo, int boardSize) {

        if(boardSize<1)
        {
            return false;
        }

        this.boardSize = boardSize;

        return false;
    }

    /*

     */
    public String getImagePng(int position){

        return "";

    }

    /*

     */
    public ArrayList<Programmer> getProgrammers(){
        return this.programmers;
    }

    /*

     */
    public ArrayList<Programmer> getProgrammers(int position){

        ArrayList<Programmer> programmerList = new ArrayList<>();

        for (Programmer programmer: programmerList)
        {

        }
        return null;
    }

    /*

     */
    public int getCurrentPlayerID(){
        return 0;
    }

    /*

     */
    public boolean moveCurrentPlayer(int nrPositions){

        return false;

    }

    /*

     */
    public boolean gameIsOver(){
        return false;

    }

    /*

     */
    public ArrayList<String> getGameResults(){
        return null;
    }

    /*

     */
    public JPanel getAuthorsPanel(){
        return null;
    }
}
