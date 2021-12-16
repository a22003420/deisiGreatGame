package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestGameManager {

    /*
    Check if player moves given positions
    Checked
     */
    @Test
    public void moveCurrentPlayer01()
    {
        GameManager game1 = new GameManager();
        String[][] board1 = new String[2][4];

        board1[0][0] = "23";
        board1[0][1] = "Joao";
        board1[0][2] = "C#;Java";
        board1[0][3] = "PURPLE";

        board1[1][0] = "2";
        board1[1][1] = "Rui";
        board1[1][2] = "Pyton;TypeScript";
        board1[1][3] = "BROWN";

        game1.createInitialBoard(board1, 20);

        Programmer programmerToTest = game1.getCurrentPlayer();

        //generate positions to move
        int nrPositionsToMove = programmerToTest.throwDice();
        int currentPosition = programmerToTest.getBoardPosition();
        int mockNewPosition = currentPosition + nrPositionsToMove;

        game1.moveCurrentPlayer(nrPositionsToMove);

        assertTrue("CheckPlayerPosition",programmerToTest.getBoardPosition() == mockNewPosition);
    }

    /*
    Check if two programmers are moved
     */
    @Test
    public void moveCurrentPlayer02()
    {
        GameManager game2 = new GameManager();
        String[][] board2 = new String[2][4];

        board2[0][0] = "23";
        board2[0][1] = "Joao";
        board2[0][2] = "C#;Java";
        board2[0][3] = "PURPLE";

        board2[1][0] = "2";
        board2[1][1] = "Rui";
        board2[1][2] = "Pyton;TypeScript";
        board2[1][3] = "BROWN";

        game2.createInitialBoard(board2, 20);

        List<Programmer> programmersList = game2.getProgrammers(false);

        Programmer programmerToTest = game2.getCurrentPlayer();
        assertTrue("CheckPlayerPosition",programmerToTest.getBoardPosition() == 1);

        int nrPositionsToMove = 4;
        game2.moveCurrentPlayer(nrPositionsToMove);

        assertTrue("CheckPlayerPosition",programmerToTest.getBoardPosition() == 5);

        nrPositionsToMove = 2;
        programmerToTest = game2.getCurrentPlayer();
        game2.moveCurrentPlayer(nrPositionsToMove);

        assertTrue("CheckPlayerPosition",programmerToTest.getBoardPosition() == 3);
    }

    /*
    Check if two programmers are required to move more than the max board size
    */
    @Test
    public void moveCurrentPlayer03()
    {
        GameManager game3 = new GameManager();
        String[][] board3 = new String[4][4];

        board3[0][0] = "23";
        board3[0][1] = "Joao";
        board3[0][2] = "C#;Java";
        board3[0][3] = "PURPLE";

        board3[1][0] = "2";
        board3[1][1] = "Rui";
        board3[1][2] = "Pyton;TypeScript";
        board3[1][3] = "BROWN";

        board3[2][0] = "123";
        board3[2][1] = "Joaquim";
        board3[2][2] = "Pyton;TypeScript";
        board3[2][3] = "GREEN";

        board3[3][0] = "4";
        board3[3][1] = "Mata";
        board3[3][2] = "Pyton;TypeScript";
        board3[3][3] = "BLUE";

        game3.createInitialBoard(board3, 20);

        int nrPositionsToMove = 4;
        Programmer programmerToTest = game3.getCurrentPlayer();
        game3.moveCurrentPlayer(nrPositionsToMove);

        assertTrue("CheckPlayerPosition",programmerToTest.getBoardPosition() == 5);

        nrPositionsToMove = 4;
        programmerToTest = game3.getCurrentPlayer();
        game3.moveCurrentPlayer(nrPositionsToMove);

        assertTrue("CheckPlayerPosition",programmerToTest.getBoardPosition() == 5);

        System.out.println(game3.getProgrammers(5));
    }

    /*
    Check if Game is Over
     */
    @Test
    public void moveCurrentPlayer04(){

        GameManager game4 = new GameManager();
        String[][] board4 = new String[2][4];

        board4[0][0] = "23";
        board4[0][1] = "Joao";
        board4[0][2] = "C#;Java";
        board4[0][3] = "PURPLE";

        board4[1][0] = "2";
        board4[1][1] = "Rui";
        board4[1][2] = "Pyton;TypeScript";
        board4[1][3] = "BROWN";

        game4.createInitialBoard(board4, 4);

        int nrPositionsToMove = 3;
        Programmer programmerToTest = game4.getCurrentPlayer();
        game4.moveCurrentPlayer(nrPositionsToMove);

        assertTrue("Game is Over",game4.gameIsOver());


    }

    public void moveCurrentPlayer05(){

    }

    public void moveCurrentPlayer06(){

    }



}
