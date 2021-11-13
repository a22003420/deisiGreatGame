package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestGameManager {

    static GameManager game = new GameManager();
    static String[][] board = new String[2][4];
    /*
    Check if programmer is moved with given positions
     */
    @Test
    public void moveCurrentPlayer01()
    {
        board[0][0] = "23";
        board[0][1] = "Joao";
        board[0][2] = "C#;Java";
        board[0][3] = "PURPLE";

        board[1][0] = "2";
        board[1][1] = "Rui";
        board[1][2] = "Pyton;TypeScript";
        board[1][3] = "BROWN";

        game.createInitialBoard(board, 20);
        int id = game.getCurrentPlayerID();

        ArrayList<Programmer> programmers = game.getProgrammers();
        Programmer programmerToTest = null;
        for (Programmer programmer: programmers)
        {
            if(programmer.getId() == id)
            {
                programmerToTest = programmer;
            }
        }

        int nrPositionsToMove = 4;
        game.moveCurrentPlayer(nrPositionsToMove);

        assertTrue("CheckFirtsPlayerPosition",programmerToTest.getBoardPosition() == 5);
    }

    /*
    Check if two programmers are moved
     */
    @Test
    public void moveCurrentPlayer02()
    {
        board[0][0] = "23";
        board[0][1] = "Joao";
        board[0][2] = "C#;Java";
        board[0][3] = "PURPLE";

        board[1][0] = "2";
        board[1][1] = "Rui";
        board[1][2] = "Pyton;TypeScript";
        board[1][3] = "BROWN";

        game.createInitialBoard(board, 20);

        int id = game.getCurrentPlayerID();

        ArrayList<Programmer> programmers = game.getProgrammers();
        Programmer programmer1ToTest = null;
        for (Programmer programmer: programmers)
        {
            if(programmer.getId() == id)
            {
                programmer1ToTest = programmer;
            }
        }

        int nrPositionsToMove = 4;
        game.moveCurrentPlayer(nrPositionsToMove);

        //check second programmer
        id = game.getCurrentPlayerID();
        Programmer programmer2ToTest = null;
        for (Programmer programmer: programmers)
        {
            if(programmer.getId() == id)
            {
                programmer2ToTest = programmer;
            }
        }

        nrPositionsToMove = 1;
        game.moveCurrentPlayer(nrPositionsToMove);

        assertTrue("CheckFirtsPlayerPosition",programmer1ToTest.getBoardPosition() == 5);
        assertTrue("CheckSecondPlayerPosition",programmer2ToTest.getBoardPosition() == 2);

    }

    @Test
    public void moveCurrentPlayer03()
    {
        board[0][0] = "23";
        board[0][1] = "Joao";
        board[0][2] = "C#;Java";
        board[0][3] = "PURPLE";

        board[1][0] = "2";
        board[1][1] = "Rui";
        board[1][2] = "Pyton;TypeScript";
        board[1][3] = "BROWN";

        game.createInitialBoard(board, 4);
        int playerId2 = game.getCurrentPlayerID();

        ArrayList<Programmer> programmers = game.getProgrammers();
        Programmer programmerToTest = null;
        for (Programmer programmer: programmers)
        {
            if(programmer.getId() == playerId2)
            {
                programmerToTest = programmer;
            }
        }

        int nrPositionsToMove = 4;
        game.moveCurrentPlayer(nrPositionsToMove);

        assertTrue("CheckFirtsPlayerPosition",programmerToTest.getBoardPosition() == 1);
    }

    @Test
    public void moveCurrentPlayer04(){




    }
}
