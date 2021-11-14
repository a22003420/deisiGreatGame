package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TestGameManager {

    /*
    Check if player moves given positions
     */
    @Test
    public void moveCurrentPlayer01()
    {
        /*GameManager game1 = new GameManager();
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

        int nrPositionsToMove = 4;
        game1.moveCurrentPlayer(nrPositionsToMove);

        assertTrue("CheckFirtsPlayerPosition",programmerToTest.getBoardPosition() == 5);*/
    }

    /*
    Check if two programmers are moved
     */
    @Test
    public void moveCurrentPlayer02()
    {
        /*GameManager game2 = new GameManager();
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

        int nrPositionsToMove = 4;
        Programmer programmerToTest = game2.getCurrentPlayer();
        game2.moveCurrentPlayer(nrPositionsToMove);

        assertTrue("CheckPlayerPosition",programmerToTest.getBoardPosition() == 5);

        nrPositionsToMove = 2;
        programmerToTest = game2.getCurrentPlayer();
        game2.moveCurrentPlayer(nrPositionsToMove);

        assertTrue("CheckPlayerPosition",programmerToTest.getBoardPosition() == 3);*/
    }

    @Test
    public void moveCurrentPlayer03()
    {
        /*GameManager game3 = new GameManager();
        String[][] board3 = new String[2][4];

        board3[0][0] = "23";
        board3[0][1] = "Joao";
        board3[0][2] = "C#;Java";
        board3[0][3] = "PURPLE";

        board3[1][0] = "2";
        board3[1][1] = "Rui";
        board3[1][2] = "Pyton;TypeScript";
        board3[1][3] = "BROWN";

        game3.createInitialBoard(board3, 4);

        int nrPositionsToMove = 4;
        Programmer programmerToTest = game3.getCurrentPlayer();
        game3.moveCurrentPlayer(nrPositionsToMove);

        assertTrue("CheckPlayerPosition",programmerToTest.getBoardPosition() == 5);

        nrPositionsToMove = 4;
        programmerToTest = game3.getCurrentPlayer();
        game3.moveCurrentPlayer(nrPositionsToMove);

        assertTrue("CheckPlayerPosition",programmerToTest.getBoardPosition() == 2);*/
    }

    @Test
    public void moveCurrentPlayer04(){




    }
}
