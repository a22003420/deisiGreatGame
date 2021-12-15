package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestGameManager {

    /*
    Check game for two players until one arrives at finish
     */
    @Test
    public void move2PlayersUntilOnePlayerWins()
    {
        GameManager game2 = new GameManager();
        String[][] board2 = new String[2][4];

        board2[0][0] = "23";
        board2[0][1] = "B";
        board2[0][2] = "C#;Java";
        board2[0][3] = "PURPLE";

        board2[1][0] = "2";
        board2[1][1] = "A";
        board2[1][2] = "Pyton;TypeScript";
        board2[1][3] = "BROWN";

        String[][] objects = new String[13][3];
        //Abyss Syntax
        objects[0][0] = "0";
        objects[0][1] = "0";
        objects[0][2] = "2";
        objects[1][0] = "0";
        objects[1][1] = "0";
        objects[1][2] = "3";
        //Abyss Logic
        objects[2][0] = "0";
        objects[2][1] = "1";
        objects[2][2] = "4";
        objects[3][0] = "0";
        objects[3][1] = "1";
        objects[3][2] = "5";
        //Abyss Exception
        objects[4][0] = "0";
        objects[4][1] = "2";
        objects[4][2] = "7";
        objects[5][0] = "0";
        objects[5][1] = "2";
        objects[5][2] = "8";
        //Abyss FileNotFound
        objects[6][0] = "0";
        objects[6][1] = "3";
        objects[6][2] = "10";
        objects[7][0] = "0";
        objects[7][1] = "3";
        objects[7][2] = "11";
        //Abyss Crash
        objects[8][0] = "0";
        objects[8][1] = "4";
        objects[8][2] = "12";
        //Abyss Duplicated
        objects[9][0] = "0";
        objects[9][1] = "5";
        objects[9][2] = "14";
        //Abyss Duplicated
        objects[10][0] = "0";
        objects[10][1] = "6";
        objects[10][2] = "17";
        //Blue Screen
        objects[11][0] = "0";
        objects[11][1] = "7";
        objects[11][2] = "19";
        //Cycle
        objects[12][0] = "0";
        objects[12][1] = "8";
        objects[12][2] = "22";

        game2.createInitialBoard(board2, 79, objects);
        List<Programmer> programmersList = game2.getProgrammers();
        Programmer programmerToTest;
        int nrPositionsToMove;

        //#####
        //TURN TO ABYSS SYNTAX - Goes back 1 position

        nrPositionsToMove =1;
        programmerToTest = game2.getCurrentPlayer();
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("CheckPlayer A", 2, programmerToTest.getId());
        assertEquals("CheckPlayerPosition A", 2, (int) programmerToTest.currentPosition());
        game2.reactToAbyssOrTool();
        assertEquals("CheckPlayerPosition A", 1, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO ABYSS SYNTAX - Goes back 1 position

        nrPositionsToMove =2;
        programmerToTest = game2.getCurrentPlayer();
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("CheckPlayer B", 23, programmerToTest.getId());
        assertEquals("CheckPlayerPosition B", 3, (int) programmerToTest.currentPosition());
        game2.reactToAbyssOrTool();
        assertEquals("CheckPlayerPosition B", 2, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO ABYSS LOGIC - Goes back n\2 positions

        nrPositionsToMove =3;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("CheckPlayer A", 2, programmerToTest.getId());
        assertEquals("CheckPlayerPosition A", 1, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("CheckPlayerPosition A", 4, (int) programmerToTest.currentPosition());
        assertEquals("Erro de lógica A", "Erro de lógica", game2.getTitle(programmerToTest.currentPosition()));
        assertEquals("PlayerPosition A Ultima", 1, (int)programmerToTest.previousPosition());
        game2.reactToAbyssOrTool();
        assertEquals("CheckPlayerPosition", 3, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO ABYSS LOGIC - Goes back n\2 positions

        nrPositionsToMove =2;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("CheckPlayer", 23, programmerToTest.getId());
        assertEquals("CheckPlayerPosition", 2, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("CheckPlayerPosition", 4, (int) programmerToTest.currentPosition());
        game2.reactToAbyssOrTool();
        assertEquals("CheckPlayerPosition", 3, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO ABYSS EXCEPTION  - Goes back 2 positions

        nrPositionsToMove =4;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("CheckPlayer", 2, programmerToTest.getId());
        assertEquals("CheckPlayerPosition", 3, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("CheckPlayerPosition", 7, (int) programmerToTest.currentPosition());
        assertEquals("Erro de Exception", "Exception", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("CheckPlayerPosition", 5, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO ABYSS EXCEPTION  - Goes back 2 positions

        nrPositionsToMove =5;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("CheckPlayer", 23, programmerToTest.getId());
        assertEquals("CheckPlayerPosition", 3, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("CheckPlayerPosition", 8, (int) programmerToTest.currentPosition());
        game2.reactToAbyssOrTool();
        assertEquals("CheckPlayerPosition", 6, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO FILE NOT FOUND - Goes back 3 positions

        nrPositionsToMove =6;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("CheckPlayer", 2, programmerToTest.getId());
        assertEquals("CheckPlayerPosition", 5, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("CheckPlayerPosition", 11, (int) programmerToTest.currentPosition());
        assertEquals("Erro File Not Found Exception", "File Not Found Exception", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("CheckPlayerPosition", 8, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO FILE NOT FOUND - Goes back 3 positions

        nrPositionsToMove =4;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("CheckPlayer", 23, programmerToTest.getId());
        assertEquals("CheckPlayerPosition", 6, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("CheckPlayerPosition", 10, (int) programmerToTest.currentPosition());
        game2.reactToAbyssOrTool();
        assertEquals("CheckPlayerPosition", 7, (int) programmerToTest.currentPosition());
        //#####################
        //Begin check File Not Found Exception - Goes back 3 positions

        //#####
        //TURN TO CRASH - returns to start

        nrPositionsToMove =4;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("Check Player A", 2, programmerToTest.getId());
        assertEquals("Check Player A Position Before Move", 8, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("Check Player A Position after Move", 12, (int) programmerToTest.currentPosition());
        assertEquals("Erro Crash", "Crash (aka Rebentanço)", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("Check Player A Position after react", 1, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =2;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("Check Player B", 23, programmerToTest.getId());
        assertEquals("Check Player B Position Before Move", 7, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("Check Player B Position after Move", 9, (int) programmerToTest.currentPosition());
        assertEquals("Tile Empty", "Casa Vazia", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("Check Player B Position after react", 9, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =5;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("Check Player A", 2, programmerToTest.getId());
        assertEquals("Check Player A Position Before Move", 1, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("Check Player A Position Before Move", 6, (int) programmerToTest.currentPosition());
        assertEquals("Check Player A Position Tile", "Casa Vazia", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("Check Player A Position after react", 6, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO DUPLICATED CODE - previous position

        nrPositionsToMove =5;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("Check Player B", 23, programmerToTest.getId());
        assertEquals("Check Player B Position Before Move", 9, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("Check Player B Position After Move", 14, (int) programmerToTest.currentPosition());
        assertEquals("Check Player B Position Tile", "Duplicated Code", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("Check Player B Position after react", 9, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO FILE NOT FOUND  - back 3 positions

        nrPositionsToMove =5;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("Check Player A", 2, programmerToTest.getId());
        assertEquals("Check Player A Position Before Move", 6, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("Check Player A Position Before Move", 11, (int) programmerToTest.currentPosition());
        assertEquals("Check Player A Position Tile", "File Not Found Exception", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("Check Player A Position after react", 8, (int) programmerToTest.currentPosition());
        assertEquals("Check Player A Position Tile after react", "Exception", game2.getTitle(programmerToTest.currentPosition()));

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =4;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("Check Player B", 23, programmerToTest.getId());
        assertEquals("Check Player B Position Before Move", 9, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("Check Player B Position After Move", 13, (int) programmerToTest.currentPosition());
        assertEquals("Check Player B Position Tile", "Casa Vazia", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("Check Player B Position after react", 13, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =5;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("Check Player A", 2, programmerToTest.getId());
        assertEquals("Check Player A Position Before Move", 8, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("Check Player A Position After Move", 13, (int) programmerToTest.currentPosition());
        assertEquals("Check Player A Position Tile", "Casa Vazia", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("Check Player A Position after react", 13, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =2;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("Check Player B", 23, programmerToTest.getId());
        assertEquals("Check Player B Position Before Move", 13, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("Check Player B Position After Move", 15, (int) programmerToTest.currentPosition());
        assertEquals("Check Player B Position Tile 15", "Casa Vazia", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("Check Player B Position after react", 15, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO SECONDARY EFFECTS - Goes back to previous to previous

        nrPositionsToMove =4;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("Check Player A", 2, programmerToTest.getId());
        assertEquals("Check Player A Position Before Move", 13, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("Check Player A Position Before Move", 17, (int) programmerToTest.currentPosition());
        assertEquals("Check Player A Position Tile", "Efeitos secundários", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("Check Player A Position after react", 8, (int) programmerToTest.currentPosition());
        assertEquals("Check Player A Position Tile after react", "Exception", game2.getTitle(programmerToTest.currentPosition()));
        assertEquals("Game is Over", false, game2.gameIsOver());

        //#####
        //TURN TO SECONDARY EFFECTS - Goes back to previous to previous

        nrPositionsToMove =2;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("Check Player B", 23, programmerToTest.getId());
        assertEquals("Check Player B Position Before Move", 15, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("Check Player B Position After Move", 17, (int) programmerToTest.currentPosition());
        assertEquals("Check Player B Position Tile", "Efeitos secundários", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("Check Player B Position after react", 13, (int) programmerToTest.currentPosition());
        assertEquals("Check Player B Position Tile", "Casa Vazia", game2.getTitle(programmerToTest.currentPosition()));
        assertEquals("Game is Over", false, game2.gameIsOver());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =5;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("Check Player A", 2, programmerToTest.getId());
        assertEquals("Check Player A Position Before Move", 8, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("Check Player A Position After Move", 13, (int) programmerToTest.currentPosition());
        assertEquals("Check Player A Position Tile", "Casa Vazia", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("Check Player A Position after react", 13, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =3;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("Check Player B", 23, programmerToTest.getId());
        assertEquals("Check Player B Position Before Move", 13, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("Check Player B Position After Move", 16, (int) programmerToTest.currentPosition());
        assertEquals("Check Player B Position Tile 15", "Casa Vazia", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("Check Player B Position after react", 16, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =5;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("Check Player A", 2, programmerToTest.getId());
        assertEquals("Check Player A Position Before Move", 13, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("Check Player A Position After Move", 18, (int) programmerToTest.currentPosition());
        assertEquals("Check Player A Position Tile", "Casa Vazia", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("Check Player A Position after react", 18, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =4;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("Check Player B", 23, programmerToTest.getId());
        assertEquals("Check Player B Position Before Move", 16, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("Check Player B Position After Move", 20, (int) programmerToTest.currentPosition());
        assertEquals("Check Player B Position Tile", "Casa Vazia", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("Check Player B Position after react", 20, (int) programmerToTest.currentPosition());

        //#####
        //TURN
        //#####
    }

    /*
    Check game for two players until Abyss Blue Screen on Death
     */
    @Test
    public void move2PlayersUntilAbyssBlueScreen()
    {
        GameManager game2 = new GameManager();
        String[][] board2 = new String[2][4];

        board2[0][0] = "23";
        board2[0][1] = "B";
        board2[0][2] = "C#;Java";
        board2[0][3] = "PURPLE";

        board2[1][0] = "2";
        board2[1][1] = "A";
        board2[1][2] = "Pyton;TypeScript";
        board2[1][3] = "BROWN";

        String[][] objects = new String[12][3];
        //Abyss Syntax
        objects[0][0] = "0";
        objects[0][1] = "0";
        objects[0][2] = "2";
        objects[1][0] = "0";
        objects[1][1] = "0";
        objects[1][2] = "3";
        //Abyss Logic
        objects[2][0] = "0";
        objects[2][1] = "1";
        objects[2][2] = "4";
        objects[3][0] = "0";
        objects[3][1] = "1";
        objects[3][2] = "5";
        //Abyss Exception
        objects[4][0] = "0";
        objects[4][1] = "2";
        objects[4][2] = "7";
        objects[5][0] = "0";
        objects[5][1] = "2";
        objects[5][2] = "8";
        //Abyss FileNotFound
        objects[6][0] = "0";
        objects[6][1] = "3";
        objects[6][2] = "10";
        objects[7][0] = "0";
        objects[7][1] = "3";
        objects[7][2] = "11";
        //Abyss Crash
        objects[8][0] = "0";
        objects[8][1] = "4";
        objects[8][2] = "12";
        //Abyss Duplicated
        objects[9][0] = "0";
        objects[9][1] = "5";
        objects[9][2] = "14";
        //Abyss Duplicated
        objects[10][0] = "0";
        objects[10][1] = "6";
        objects[10][2] = "17";
        //Blue Screen
        objects[11][0] = "0";
        objects[11][1] = "7";
        objects[11][2] = "19";


        game2.createInitialBoard(board2, 79, objects);

        List<Programmer> programmersList = game2.getProgrammers();
        Programmer programmerToTest;
        int nrPositionsToMove =3;

        //#####################
        //Begin check Abyss Syntax - Goes back 1 position
        nrPositionsToMove =1;
        programmerToTest = game2.getCurrentPlayer();
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("CheckPlayer A", 2, programmerToTest.getId());
        assertEquals("CheckPlayerPosition A", 2, (int) programmerToTest.currentPosition());
        game2.reactToAbyssOrTool();
        assertEquals("CheckPlayerPosition A", 1, (int) programmerToTest.currentPosition());

        nrPositionsToMove =2;
        programmerToTest = game2.getCurrentPlayer();
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("CheckPlayer B", 23, programmerToTest.getId());
        assertEquals("CheckPlayerPosition B", 3, (int) programmerToTest.currentPosition());
        game2.reactToAbyssOrTool();
        assertEquals("CheckPlayerPosition B", 2, (int) programmerToTest.currentPosition());
        //#####################
        //End check Abyss Syntax - Goes back 1 position

        //#####
        //TURN
        //#####

        //Begin check Abyss Logic - Goes back n\2 positions
        //#####################
        nrPositionsToMove =3;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("CheckPlayer A", 2, programmerToTest.getId());
        assertEquals("CheckPlayerPosition A", 1, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("CheckPlayerPosition A", 4, (int) programmerToTest.currentPosition());
        assertEquals("Erro de lógica A", "Erro de lógica", game2.getTitle(programmerToTest.currentPosition()));
        assertEquals("PlayerPosition A Ultima", 1, (int)programmerToTest.previousPosition());
        game2.reactToAbyssOrTool();
        assertEquals("CheckPlayerPosition", 3, (int) programmerToTest.currentPosition());

        nrPositionsToMove =2;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("CheckPlayer", 23, programmerToTest.getId());
        assertEquals("CheckPlayerPosition", 2, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("CheckPlayerPosition", 4, (int) programmerToTest.currentPosition());
        game2.reactToAbyssOrTool();
        assertEquals("CheckPlayerPosition", 3, (int) programmerToTest.currentPosition());
        //#####################
        //End check Abyss Logic - Goes back n\2 positions

        //#####
        //TURN
        //#####

        //Begin check Abyss Exception - Goes back 2 positions
        //#####################
        nrPositionsToMove =4;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("CheckPlayer", 2, programmerToTest.getId());
        assertEquals("CheckPlayerPosition", 3, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("CheckPlayerPosition", 7, (int) programmerToTest.currentPosition());
        assertEquals("Erro de Exception", "Exception", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("CheckPlayerPosition", 5, (int) programmerToTest.currentPosition());

        nrPositionsToMove =5;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("CheckPlayer", 23, programmerToTest.getId());
        assertEquals("CheckPlayerPosition", 3, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("CheckPlayerPosition", 8, (int) programmerToTest.currentPosition());
        game2.reactToAbyssOrTool();
        assertEquals("CheckPlayerPosition", 6, (int) programmerToTest.currentPosition());
        //#####################
        //End check Abyss Exception - Goes back 2 positions

        //#####
        //TURN
        //#####

        //Begin check File Not Found Exception - Goes back 3 positions
        //#####################
        nrPositionsToMove =6;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("CheckPlayer", 2, programmerToTest.getId());
        assertEquals("CheckPlayerPosition", 5, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("CheckPlayerPosition", 11, (int) programmerToTest.currentPosition());
        assertEquals("Erro File Not Found Exception", "File Not Found Exception", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("CheckPlayerPosition", 8, (int) programmerToTest.currentPosition());

        nrPositionsToMove =4;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("CheckPlayer", 23, programmerToTest.getId());
        assertEquals("CheckPlayerPosition", 6, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("CheckPlayerPosition", 10, (int) programmerToTest.currentPosition());
        game2.reactToAbyssOrTool();
        assertEquals("CheckPlayerPosition", 7, (int) programmerToTest.currentPosition());
        //#####################
        //Begin check File Not Found Exception - Goes back 3 positions

        //#####
        //TURN
        //#####

        //Begin Crash Exception - Goes back to start
        //#####################
        nrPositionsToMove =4;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("Check Player A", 2, programmerToTest.getId());
        assertEquals("Check Player A Position Before Move", 8, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("Check Player A Position after Move", 12, (int) programmerToTest.currentPosition());
        assertEquals("Erro Crash", "Crash (aka Rebentanço)", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("Check Player A Position after react", 1, (int) programmerToTest.currentPosition());

        nrPositionsToMove =2;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("Check Player B", 23, programmerToTest.getId());
        assertEquals("Check Player B Position Before Move", 7, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("Check Player B Position after Move", 9, (int) programmerToTest.currentPosition());
        assertEquals("Tile Empty", "Casa Vazia", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("Check Player B Position after react", 9, (int) programmerToTest.currentPosition());
        //#####################
        //Begin Crash Exception - Goes back to start

        //#####
        //TURN
        //#####

        //Begin Duplicated Exception - Goes back to previous
        //#####################
        nrPositionsToMove =5;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("Check Player A", 2, programmerToTest.getId());
        assertEquals("Check Player A Position Before Move", 1, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("Check Player A Position Before Move", 6, (int) programmerToTest.currentPosition());
        assertEquals("Check Player A Position Tile", "Casa Vazia", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("Check Player A Position after react", 6, (int) programmerToTest.currentPosition());

        nrPositionsToMove =5;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("Check Player B", 23, programmerToTest.getId());
        assertEquals("Check Player B Position Before Move", 9, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("Check Player B Position After Move", 14, (int) programmerToTest.currentPosition());
        assertEquals("Check Player B Position Tile", "Duplicated Code", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("Check Player B Position after react", 9, (int) programmerToTest.currentPosition());
        //#####################
        //Begin Begin Duplicated Exception - Goes back to previous

        //#####
        //TURNS TO CATCH UP
        //#####

        //Begin File Not Foud
        //#####################
        nrPositionsToMove =5;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("Check Player A", 2, programmerToTest.getId());
        assertEquals("Check Player A Position Before Move", 6, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("Check Player A Position Before Move", 11, (int) programmerToTest.currentPosition());
        assertEquals("Check Player A Position Tile", "File Not Found Exception", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("Check Player A Position after react", 8, (int) programmerToTest.currentPosition());
        assertEquals("Check Player A Position Tile after react", "Exception", game2.getTitle(programmerToTest.currentPosition()));

        //Begin Clean Move Player B
        //#####################
        nrPositionsToMove =4;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("Check Player B", 23, programmerToTest.getId());
        assertEquals("Check Player B Position Before Move", 9, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("Check Player B Position After Move", 13, (int) programmerToTest.currentPosition());
        assertEquals("Check Player B Position Tile", "Casa Vazia", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("Check Player B Position after react", 13, (int) programmerToTest.currentPosition());
        //#####################
        //End Clean Move Player B

        //Begin Clean Move Player A
        //#####################
        nrPositionsToMove =5;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("Check Player A", 2, programmerToTest.getId());
        assertEquals("Check Player A Position Before Move", 8, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("Check Player A Position After Move", 13, (int) programmerToTest.currentPosition());
        assertEquals("Check Player A Position Tile", "Casa Vazia", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("Check Player A Position after react", 13, (int) programmerToTest.currentPosition());
        //#####################
        //End Clean Move Player A

        //Begin Clean Move Player B
        //#####################
        nrPositionsToMove =2;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("Check Player B", 23, programmerToTest.getId());
        assertEquals("Check Player B Position Before Move", 13, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("Check Player B Position After Move", 15, (int) programmerToTest.currentPosition());
        assertEquals("Check Player B Position Tile 15", "Casa Vazia", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("Check Player B Position after react", 15, (int) programmerToTest.currentPosition());
        //#####################
        //End Clean Move Player B

        //#####
        //TURN
        //#####

        //Begin Secondary Effects Exception - Goes back to previous to previous
        //#####################

        nrPositionsToMove =4;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("Check Player A", 2, programmerToTest.getId());
        assertEquals("Check Player A Position Before Move", 13, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("Check Player A Position Before Move", 17, (int) programmerToTest.currentPosition());
        assertEquals("Check Player A Position Tile", "Efeitos secundários", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("Check Player A Position after react", 8, (int) programmerToTest.currentPosition());
        assertEquals("Check Player A Position Tile after react", "Exception", game2.getTitle(programmerToTest.currentPosition()));
        assertEquals("Game is Over", false, game2.gameIsOver());

        nrPositionsToMove =2;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("Check Player B", 23, programmerToTest.getId());
        assertEquals("Check Player B Position Before Move", 15, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("Check Player B Position After Move", 17, (int) programmerToTest.currentPosition());
        assertEquals("Check Player B Position Tile", "Efeitos secundários", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("Check Player B Position after react", 13, (int) programmerToTest.currentPosition());
        assertEquals("Check Player B Position Tile", "Casa Vazia", game2.getTitle(programmerToTest.currentPosition()));
        assertEquals("Game is Over", false, game2.gameIsOver());
        //End Secondary Effects - Goes back to previous previous
        //#####################

        //#####
        //TURNS TO CATCH UP
        //#####

        //Begin Clean Move Player A
        //#####################
        nrPositionsToMove =5;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("Check Player A", 2, programmerToTest.getId());
        assertEquals("Check Player A Position Before Move", 8, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("Check Player A Position After Move", 13, (int) programmerToTest.currentPosition());
        assertEquals("Check Player A Position Tile", "Casa Vazia", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("Check Player A Position after react", 13, (int) programmerToTest.currentPosition());
        //#####################
        //End Clean Move Player A

        //Begin Clean Move Player B
        //#####################
        nrPositionsToMove =3;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("Check Player B", 23, programmerToTest.getId());
        assertEquals("Check Player B Position Before Move", 13, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("Check Player B Position After Move", 16, (int) programmerToTest.currentPosition());
        assertEquals("Check Player B Position Tile 15", "Casa Vazia", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("Check Player B Position after react", 16, (int) programmerToTest.currentPosition());
        //#####################
        //End Clean Move Player B

        //#####
        //TURN
        //#####

        //Begin Abyss Blue Screen of Death
        //#####################
        nrPositionsToMove =6;
        programmerToTest = game2.getCurrentPlayer();
        assertEquals("Check Player A", 2, programmerToTest.getId());
        assertEquals("Check Player A Position Before Move", 13, (int) programmerToTest.currentPosition());
        game2.moveCurrentPlayer(nrPositionsToMove);
        assertEquals("Check Player A Position After Move", 19, (int) programmerToTest.currentPosition());
        assertEquals("Check Player A Position Tile", "Blue Screen of Death", game2.getTitle(programmerToTest.currentPosition()));
        game2.reactToAbyssOrTool();
        assertEquals("Check Player A Position after react", 19, (int) programmerToTest.currentPosition());
        assertEquals("Game is Over", true, game2.gameIsOver());
        assertEquals("Count Players not defeated", 1, game2.getProgrammers(false).size());
        //#####################
        //End Abyss Blue Screen of Death

        //#####
        //TURN
        //#####
    }
}
