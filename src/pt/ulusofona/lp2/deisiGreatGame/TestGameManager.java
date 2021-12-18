package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import org.junit.Test;
import static org.junit.Assert.*;

public class TestGameManager{

    public static final String CURRENT_PLAYER_ID = "Current Player Id";
    public static final String CURRENT_PLAYER_POSITION = "Current Player Position";
    public static final String CURRENT_PLAYER_POSITION_AFTER_MOVE = "Current Player Position after move";
    public static final String CURRENT_PLAYER_POSITION_AFTER_REACT = "Current Player Position after react";

    private GameManager getGameManager() {
        return new GameManager();
    }
    private void reactToTitle(GameManager game) {
        game.reactToAbyssOrTool();
    }
    private boolean move(GameManager game, int nrPositionsToMove){
        return game.moveCurrentPlayer(nrPositionsToMove);
    }

    /*
    Check game for two players, on first turn move to Abyss File Not Found
    Set board size 26
     */
    @Test
    public void moveToDuplicatedOnStart()
    {
        GameManager game = getGameManager();
        String[][] board = new String[2][4];
        board[0][0] = "23";
        board[0][1] = "B";
        board[0][2] = "C#;Java";
        board[0][3] = "PURPLE";
        board[1][0] = "2";
        board[1][1] = "A";
        board[1][2] = "Pyton;TypeScript";
        board[1][3] = "BROWN";

        //Abyss
        String[][] objects = new String[2][3];
        //Abyss: File Not Found
        objects[0][0] = "0";
        objects[0][1] = "5";
        objects[0][2] = "2";

        game.createInitialBoard(board, 26, objects);
        Programmer currentPlayer;

        //#####
        //TURN TO ABYSS: Duplicated on Start

        int nrPositionsToMove =1;
        currentPlayer = game.getCurrentPlayer();
        boolean success = move(game, nrPositionsToMove);
        assertEquals("Success Mode", true, success);
        assertEquals(CURRENT_PLAYER_ID, 2, game.getCurrentPlayerID());
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 2, (int) currentPlayer.currentPosition());
        assertEquals("Tile Image", "duplicated-code.png", game.getImagePng(currentPlayer.currentPosition()));
        assertEquals("Tile Title", "Duplicated Code", game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 1, (int) currentPlayer.currentPosition());
        assertEquals(CURRENT_PLAYER_ID, 23, game.getCurrentPlayerID());
    }

    /*
    Check game for two players, on first turn move to Abyss Secondary Effects
    Set board size 26
     */
    @Test
    public void moveToSecondaryEffectsOnStart()
    {
        GameManager game = getGameManager();
        String[][] board = new String[2][4];
        board[0][0] = "23";
        board[0][1] = "B";
        board[0][2] = "C#;Java";
        board[0][3] = "PURPLE";
        board[1][0] = "2";
        board[1][1] = "A";
        board[1][2] = "Pyton;TypeScript";
        board[1][3] = "BROWN";

        //Abyss
        String[][] objects = new String[1][3];
        //Abyss: Secondary Effects
        objects[0][0] = "0";
        objects[0][1] = "6";
        objects[0][2] = "7";

        game.createInitialBoard(board, 26, objects);
        Programmer currentPlayer;
        int nrPositionsToMove=0;
        //#####
        //TURN TO ABYSS: Secondary Effects

        nrPositionsToMove =6;
        currentPlayer = game.getCurrentPlayer();
        assertEquals("Success Move", true, move(game, nrPositionsToMove));
        assertEquals(CURRENT_PLAYER_ID, 2, game.getCurrentPlayerID());
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 7, (int) currentPlayer.currentPosition());
        assertEquals("Tile Image", "secondary-effects.png", game.getImagePng(currentPlayer.currentPosition()));
        assertEquals("Tile Title", "Efeitos secundários", game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 1, (int) currentPlayer.currentPosition());
        assertEquals(CURRENT_PLAYER_ID, 23, game.getCurrentPlayerID());

        nrPositionsToMove =1;
        currentPlayer = game.getCurrentPlayer();
        assertEquals("Success Mode", true, move(game, nrPositionsToMove));
        assertEquals(CURRENT_PLAYER_ID, 23, game.getCurrentPlayerID());
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 2, (int) currentPlayer.currentPosition());
        assertEquals("Tile Image", null, game.getImagePng(currentPlayer.currentPosition()));
        assertEquals("Tile Title", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 2, (int) currentPlayer.currentPosition());
        assertEquals(CURRENT_PLAYER_ID, 2, game.getCurrentPlayerID());
    }

    /*
    Check game for two players, on first turn move to Abyss File Not Found
    Set board size 26
     */
    @Test
    public void moveToFileNotFoundOnStart()
    {
        GameManager game = getGameManager();
        String[][] board = new String[2][4];
        board[0][0] = "23";
        board[0][1] = "B";
        board[0][2] = "C#;Java";
        board[0][3] = "PURPLE";
        board[1][0] = "2";
        board[1][1] = "A";
        board[1][2] = "Pyton;TypeScript";
        board[1][3] = "BROWN";

        //Abyss
        String[][] objects = new String[2][3];
        //Abyss: File Not Found
        objects[0][0] = "0";
        objects[0][1] = "3";
        objects[0][2] = "2";

        game.createInitialBoard(board, 26, objects);
        Programmer currentPlayer;

        //#####
        //TURN TO ABYSS: File Not Found on Start

        int nrPositionsToMove =1;
        currentPlayer = game.getCurrentPlayer();
        boolean success = move(game, nrPositionsToMove);
        assertEquals("Success Mode", true, success);
        assertEquals(CURRENT_PLAYER_ID, 2, game.getCurrentPlayerID());
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 2, (int) currentPlayer.currentPosition());
        assertEquals("Tile Image", "file-not-found-exception.png", game.getImagePng(currentPlayer.currentPosition()));
        assertEquals("Tile Title", "File Not Found Exception", game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 1, (int) currentPlayer.currentPosition());
        assertEquals(CURRENT_PLAYER_ID, 23, game.getCurrentPlayerID());
    }

    /*
    Check game for two players for Infinite Cycle
    Set board size 26
     */
    @Test
    public void move2CollectTools()
    {
        GameManager game = getGameManager();
        String[][] board = new String[2][4];
        board[0][0] = "23";
        board[0][1] = "B";
        board[0][2] = "C#;Java";
        board[0][3] = "PURPLE";
        board[1][0] = "2";
        board[1][1] = "A";
        board[1][2] = "Pyton;TypeScript";
        board[1][3] = "BROWN";

        //Abyss
        String[][] objects = new String[14][3];
        //Tool Herança
        objects[0][0] = "1";
        objects[0][1] = "0";
        objects[0][2] = "2";

        objects[1][0] = "1";
        objects[1][1] = "0";
        objects[1][2] = "4";

        //Tool Functional Programming
        objects[2][0] = "1";
        objects[2][1] = "1";
        objects[2][2] = "5";

        objects[3][0] = "1";
        objects[3][1] = "1";
        objects[3][2] = "7";

        //Tool Unit Teste
        objects[4][0] = "1";
        objects[4][1] = "2";
        objects[4][2] = "8";

        //Tool Tratamento Excepcoes
        objects[5][0] = "1";
        objects[5][1] = "3";
        objects[5][2] = "10";

        //Tool IDE
        objects[6][0] = "1";
        objects[6][1] = "4";
        objects[6][2] = "11";

        //Tool Teacher
        objects[7][0] = "1";
        objects[7][1] = "5";
        objects[7][2] = "12";

        //Abyss Syntax
        objects[8][0] = "0";
        objects[8][1] = "0";
        objects[8][2] = "14";

        game.createInitialBoard(board, 26, objects);
        Programmer currentPlayer;
        int nrPositionsToMove;

        //#####
        //TURN TO TOOL INHERITANCE

        nrPositionsToMove =1;
        currentPlayer = game.getCurrentPlayer();
        boolean success = move(game, nrPositionsToMove);
        assertEquals("Success Mode", true, success);
        assertEquals(CURRENT_PLAYER_ID, 2, game.getCurrentPlayerID());
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 2, (int) currentPlayer.currentPosition());
        assertEquals("Tile Image", "inheritance.png", game.getImagePng(currentPlayer.currentPosition()));
        assertEquals("Tile Title", "Herança", game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =2;
        currentPlayer = game.getCurrentPlayer();
        move(game, nrPositionsToMove);
        assertEquals("Success Mode", true, success);
        assertEquals(CURRENT_PLAYER_ID, 23, game.getCurrentPlayerID());
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 3, (int) currentPlayer.currentPosition());
        assertEquals("Tile Image", null, game.getImagePng(currentPlayer.currentPosition()));
        assertEquals("Tile Title", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =2;
        currentPlayer = game.getCurrentPlayer();
        move(game, nrPositionsToMove);
        assertEquals("Success Mode", true, success);
        assertEquals(CURRENT_PLAYER_ID, 2, game.getCurrentPlayerID());
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 4, (int) currentPlayer.currentPosition());
        assertEquals("Tile Image", "inheritance.png", game.getImagePng(currentPlayer.currentPosition()));
        assertEquals("Tile Title", "Herança", game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals("Tile Title", "Herança", game.getTitle(currentPlayer.currentPosition()));

        //#####
        //TURN TO Funcional Programming

        nrPositionsToMove =2;
        currentPlayer = game.getCurrentPlayer();
        move(game, nrPositionsToMove);
        assertEquals("Success Mode", true, success);
        assertEquals(CURRENT_PLAYER_ID, 23, game.getCurrentPlayerID());
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 5, (int) currentPlayer.currentPosition());
        assertEquals("Tile Image", "functional.png", game.getImagePng(currentPlayer.currentPosition()));
        assertEquals("Tile Title", "Programação Funcional", game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);

        //#####
        //TURN TO Tool Unit Tests

        nrPositionsToMove =4;
        currentPlayer = game.getCurrentPlayer();
        move(game, nrPositionsToMove);
        assertEquals("Success Mode", true, success);
        assertEquals(CURRENT_PLAYER_ID, 2, game.getCurrentPlayerID());
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 8, (int) currentPlayer.currentPosition());
        assertEquals("Tile Image", "unit-tests.png", game.getImagePng(currentPlayer.currentPosition()));
        assertEquals("Tile Title", "Testes unitários", game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);

        //#####
        //TURN TO Tratamento de Excepções

        nrPositionsToMove =5;
        currentPlayer = game.getCurrentPlayer();
        move(game, nrPositionsToMove);
        assertEquals("Success Mode", true, success);
        assertEquals(CURRENT_PLAYER_ID, 23, game.getCurrentPlayerID());
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 10, (int) currentPlayer.currentPosition());
        assertEquals("Tile Image", "catch.png", game.getImagePng(currentPlayer.currentPosition()));
        assertEquals("Tile Title", "Tratamento de Excepções", game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);

        //#####
        //TURN TO IDE

        nrPositionsToMove =3;
        currentPlayer = game.getCurrentPlayer();
        move(game, nrPositionsToMove);
        assertEquals("Success Mode", true, success);
        assertEquals(CURRENT_PLAYER_ID, 2, game.getCurrentPlayerID());
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 11, (int) currentPlayer.currentPosition());
        assertEquals("Tile Image", "IDE.png", game.getImagePng(currentPlayer.currentPosition()));
        assertEquals("Tile Title", "IDE", game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);

        //#####
        //TURN TO TEACHER

        nrPositionsToMove =2;
        currentPlayer = game.getCurrentPlayer();
        move(game, nrPositionsToMove);
        assertEquals("Success Mode", true, success);
        assertEquals(CURRENT_PLAYER_ID, 23, game.getCurrentPlayerID());
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 12, (int) currentPlayer.currentPosition());
        assertEquals("Tile Image", "ajuda-professor.png", game.getImagePng(currentPlayer.currentPosition()));
        assertEquals("Tile Title", "Ajuda Do Professor", game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);

        //#####
        //TURN TO: Abyss Syntax - Goes back 1 position

        nrPositionsToMove =3;
        Programmer programmerToTest = game.getCurrentPlayer();
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_ID, 2, programmerToTest.getId());
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 14, (int) programmerToTest.currentPosition());
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 14, (int) programmerToTest.currentPosition());

    }


    /*
    Check game for two players for Infinite Cycle
    Set board size 26
     */
    @Test
    public void move2PlayersFinish()
    {
        GameManager game = getGameManager();
        String[][] board = new String[2][4];
        board[0][0] = "23";
        board[0][1] = "B";
        board[0][2] = "C#;Java";
        board[0][3] = "PURPLE";
        board[1][0] = "2";
        board[1][1] = "A";
        board[1][2] = "Pyton;TypeScript";
        board[1][3] = "BROWN";

        //Abyss
        String[][] objects = new String[14][3];
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
        //Segmentation
        objects[13][0] = "0";
        objects[13][1] = "9";
        objects[13][2] = "24";

        game.createInitialBoard(board, 26, objects);
        Programmer currentPlayer;
        int nrPositionsToMove;

        //#####
        //TURN TO ABYSS SYNTAX - Goes back 1 position

        nrPositionsToMove =1;
        currentPlayer = game.getCurrentPlayer();
        boolean success = move(game, nrPositionsToMove);
        assertEquals("Success Mode", true, success);
        assertEquals(CURRENT_PLAYER_ID, 2, game.getCurrentPlayerID());
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 2, (int) currentPlayer.currentPosition());
        assertEquals("Tile Image", "syntax.png", game.getImagePng(currentPlayer.currentPosition()));
        assertEquals("Tile Title", "Erro de sintaxe", game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 1, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO ABYSS SYNTAX - Goes back 1 position

        nrPositionsToMove =2;
        currentPlayer = game.getCurrentPlayer();
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 3, (int) currentPlayer.currentPosition());
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 2, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO ABYSS LOGIC - Goes back n\2 positions

        nrPositionsToMove =3;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 1, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 4, (int) currentPlayer.currentPosition());
        assertEquals("Tile Title", "Erro de lógica", game.getTitle(currentPlayer.currentPosition()));
        assertEquals("Title Image", "logic.png", game.getImagePng(currentPlayer.currentPosition()));
        assertEquals("Check Player Previous Position", 1, (int)currentPlayer.previousTurnPosition(1));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 3, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO ABYSS LOGIC - Goes back n\2 positions

        nrPositionsToMove =2;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 2, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 4, (int) currentPlayer.currentPosition());
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 3, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO ABYSS EXCEPTION  - Goes back 2 positions

        nrPositionsToMove =4;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 3, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 7, (int) currentPlayer.currentPosition());
        assertEquals("Tile Title", "Exception", game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 5, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO ABYSS EXCEPTION  - Goes back 2 positions

        nrPositionsToMove =5;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 3, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 8, (int) currentPlayer.currentPosition());
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 6, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO FILE NOT FOUND - Goes back 3 positions

        nrPositionsToMove =6;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 5, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 11, (int) currentPlayer.currentPosition());
        assertEquals("Tile Title", "File Not Found Exception", game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 8, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO FILE NOT FOUND - Goes back 3 positions

        nrPositionsToMove =4;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 6, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 10, (int) currentPlayer.currentPosition());
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 7, (int) currentPlayer.currentPosition());
        //#####################
        //Begin check File Not Found Exception - Goes back 3 positions

        //#####
        //TURN TO CRASH - returns to start

        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 8, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 12, (int) currentPlayer.currentPosition());
        assertEquals("Tile Title", "Crash (aka Rebentanço)", game.getTitle(currentPlayer.currentPosition()));
        assertEquals("Title Image", "crash.png", game.getImagePng(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 1, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =2;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 7, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 9, (int) currentPlayer.currentPosition());
        assertEquals("Tile Title", null, game.getTitle(currentPlayer.currentPosition()));
        assertEquals("Tile Image", null, game.getImagePng(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 9, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =5;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 1, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 6, (int) currentPlayer.currentPosition());
        assertEquals("Tile Title", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 6, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO Duplicated Code - returns to previous position

        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 9, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 14, (int) currentPlayer.currentPosition());
        assertEquals("Tile Title", "Duplicated Code", game.getTitle(currentPlayer.currentPosition()));
        assertEquals("Tile Image", "duplicated-code.png", game.getImagePng(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 9, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO File Not Found  - back 3 positions

        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 6, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 11, (int) currentPlayer.currentPosition());
        assertEquals("Tile Title", "File Not Found Exception", game.getTitle(currentPlayer.currentPosition()));
        assertEquals("Title Image", "file-not-found-exception.png", game.getImagePng(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 8, (int) currentPlayer.currentPosition());
        assertEquals("Tile Title", "Exception", game.getTitle(currentPlayer.currentPosition()));

        //#####
        //TURN TO Empty

        nrPositionsToMove =4;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 9, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 13, (int) currentPlayer.currentPosition());
        assertEquals("Tile Title", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 13, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =5;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 8, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 13, (int) currentPlayer.currentPosition());
        assertEquals("Tile Title", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 13, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =2;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 13, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 15, (int) currentPlayer.currentPosition());
        assertEquals("Tile Title", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 15, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO SECONDARY EFFECTS - Goes back to previous to previous

        nrPositionsToMove =4;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 13, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 17, (int) currentPlayer.currentPosition());
        assertEquals("Tile Title", "Efeitos secundários", game.getTitle(currentPlayer.currentPosition()));
        assertEquals("Title Image", "secondary-effects.png", game.getImagePng(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 8, (int) currentPlayer.currentPosition());
        assertEquals("Tile Title", "Exception", game.getTitle(currentPlayer.currentPosition()));
        assertFalse("Game is Over", game.gameIsOver());

        //#####
        //TURN TO SECONDARY EFFECTS - Goes back to previous to previous

        nrPositionsToMove =2;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 15, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 17, (int) currentPlayer.currentPosition());
        assertEquals("Tile Title", "Efeitos secundários", game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 13, (int) currentPlayer.currentPosition());
        assertEquals("Tile Title", null, game.getTitle(currentPlayer.currentPosition()));
        assertFalse("Game is Over", game.gameIsOver());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =5;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 8, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 13, (int) currentPlayer.currentPosition());
        assertEquals("Tile Title", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 13, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =3;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 13, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 16, (int) currentPlayer.currentPosition());
        assertEquals("Tile Title", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 16, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =5;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 13, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 18, (int) currentPlayer.currentPosition());
        assertEquals("Tile Title", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 18, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =4;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 16, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 20, (int) currentPlayer.currentPosition());
        assertEquals("Tile Title", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 20, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO SEGMENTATION - no player exist on position

        nrPositionsToMove =6;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 18, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 24, (int) currentPlayer.currentPosition());
        assertEquals("Tile Title", "Segmentation Fault", game.getTitle(currentPlayer.currentPosition()));
        assertEquals("Tile Image", "core-dumped.png", game.getImagePng(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 24, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO SEGMENTATION - 1 player already exist on position - all players go back 3 positions

        nrPositionsToMove =4;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 20, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 24, (int) currentPlayer.currentPosition());
        assertEquals("Tile Title", "Segmentation Fault", game.getTitle(currentPlayer.currentPosition()));
        assertEquals("Tile Image", "core-dumped.png", game.getImagePng(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 21, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO FINISH

        nrPositionsToMove =5;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 21, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 26, (int) currentPlayer.currentPosition());
        reactToTitle(game);
        assertTrue("Is Game Over", game.gameIsOver());

    }

    /*
    Check game for two players for Infinite Cycle
     */
    @Test
    public void move2PlayersTestSegmentation()
    {
        GameManager game = getGameManager();
        String[][] board = new String[2][4];
        board[0][0] = "23";
        board[0][1] = "B";
        board[0][2] = "C#;Java";
        board[0][3] = "PURPLE";
        board[1][0] = "2";
        board[1][1] = "A";
        board[1][2] = "Pyton;TypeScript";
        board[1][3] = "BROWN";

        //Abyss
        String[][] objects = new String[14][3];
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
        //Segmentation
        objects[13][0] = "0";
        objects[13][1] = "9";
        objects[13][2] = "24";

        game.createInitialBoard(board, 79, objects);
        Programmer currentPlayer;
        int nrPositionsToMove = 1;

        //#####
        //TURN TO ABYSS SYNTAX - Goes back 1 position

        currentPlayer = game.getCurrentPlayer();
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_ID, 2, game.getCurrentPlayerID());
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 2, (int) currentPlayer.currentPosition());
        assertEquals("Erro de Syntax - Image", "syntax.png", game.getImagePng(currentPlayer.currentPosition()));
        assertEquals("Erro de Syntax - Title", "Erro de sintaxe", game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 1, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO ABYSS SYNTAX - Goes back 1 position

        nrPositionsToMove =2;
        currentPlayer = game.getCurrentPlayer();
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 3, (int) currentPlayer.currentPosition());
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 2, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO ABYSS LOGIC - Goes back n\2 positions

        nrPositionsToMove =3;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 1, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 4, (int) currentPlayer.currentPosition());
        assertEquals("Erro de lógica - Title", "Erro de lógica", game.getTitle(currentPlayer.currentPosition()));
        assertEquals("Erro de lógica - Image", "logic.png", game.getImagePng(currentPlayer.currentPosition()));
        assertEquals("Check Player Previous Position", 1, (int)currentPlayer.previousTurnPosition(1));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 3, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO ABYSS LOGIC - Goes back n\2 positions

        nrPositionsToMove =2;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 2, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 4, (int) currentPlayer.currentPosition());
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 3, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO ABYSS EXCEPTION  - Goes back 2 positions

        nrPositionsToMove =4;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 3, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 7, (int) currentPlayer.currentPosition());
        assertEquals("Erro de Exception", "Exception", game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 5, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO: Abyss Exception  - Goes back 2 positions

        nrPositionsToMove =5;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 3, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 8, (int) currentPlayer.currentPosition());
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 6, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO FILE NOT FOUND - Goes back 3 positions

        nrPositionsToMove =6;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 5, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 11, (int) currentPlayer.currentPosition());
        assertEquals("Erro File Not Found Exception", "File Not Found Exception", game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 8, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO FILE NOT FOUND - Goes back 3 positions

        nrPositionsToMove =4;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 6, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 10, (int) currentPlayer.currentPosition());
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 7, (int) currentPlayer.currentPosition());
        //#####################
        //Begin check File Not Found Exception - Goes back 3 positions

        //#####
        //TURN TO CRASH - returns to start

        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 8, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 12, (int) currentPlayer.currentPosition());
        assertEquals("Erro Crash", "Crash (aka Rebentanço)", game.getTitle(currentPlayer.currentPosition()));
        assertEquals("Erro Crash", "crash.png", game.getImagePng(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 1, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =2;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 7, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 9, (int) currentPlayer.currentPosition());
        assertNull("Tile Empty", game.getTitle(currentPlayer.currentPosition()));
        assertNull("Tile Empty", game.getImagePng(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 9, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =5;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 1, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 6, (int) currentPlayer.currentPosition());
        assertEquals("CURRENT_PLAYER_ID Position Tile", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 6, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO Duplicated Code - returns to previous position

        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 9, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 14, (int) currentPlayer.currentPosition());
        assertEquals("Check Player B Position Tile", "Duplicated Code", game.getTitle(currentPlayer.currentPosition()));
        assertEquals("Check Player B Position Tile", "duplicated-code.png", game.getImagePng(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 9, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO File Not Found  - back 3 positions

        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 6, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 11, (int) currentPlayer.currentPosition());
        assertEquals("CURRENT_PLAYER_ID Position Tile", "File Not Found Exception", game.getTitle(currentPlayer.currentPosition()));
        assertEquals("Check Player B Position Tile", "file-not-found-exception.png", game.getImagePng(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 8, (int) currentPlayer.currentPosition());
        assertEquals("CURRENT_PLAYER_ID Position Tile after react", "Exception", game.getTitle(currentPlayer.currentPosition()));

        //#####
        //TURN TO Empty

        nrPositionsToMove =4;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 9, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 13, (int) currentPlayer.currentPosition());
        assertEquals("Check Player B Position Tile", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 13, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =5;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 8, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 13, (int) currentPlayer.currentPosition());
        assertEquals("CURRENT_PLAYER_ID Position Tile", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 13, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =2;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 13, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 15, (int) currentPlayer.currentPosition());
        assertEquals("Check Player B Position Tile 15", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 15, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO SECONDARY EFFECTS - Goes back to previous to previous

        nrPositionsToMove =4;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 13, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 17, (int) currentPlayer.currentPosition());
        assertEquals("CURRENT_PLAYER_ID Position Tile", "Efeitos secundários", game.getTitle(currentPlayer.currentPosition()));
        assertEquals("CURRENT_PLAYER_ID Position Tile", "secondary-effects.png", game.getImagePng(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 8, (int) currentPlayer.currentPosition());
        assertEquals("CURRENT_PLAYER_ID Position Tile after react", "Exception", game.getTitle(currentPlayer.currentPosition()));
        assertFalse("Game is Over", game.gameIsOver());

        //#####
        //TURN TO SECONDARY EFFECTS - Goes back to previous to previous

        nrPositionsToMove =2;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 15, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 17, (int) currentPlayer.currentPosition());
        assertEquals("Check Player B Position Tile", "Efeitos secundários", game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 13, (int) currentPlayer.currentPosition());
        assertEquals("Check Player B Position Tile", null, game.getTitle(currentPlayer.currentPosition()));
        assertFalse("Game is Over", game.gameIsOver());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =5;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 8, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 13, (int) currentPlayer.currentPosition());
        assertEquals("CURRENT_PLAYER_ID Position Tile", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 13, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =3;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 13, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 16, (int) currentPlayer.currentPosition());
        assertEquals("Check Player B Position Tile 15", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 16, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =5;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 13, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 18, (int) currentPlayer.currentPosition());
        assertEquals("CURRENT_PLAYER_ID Position Tile", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 18, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =4;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 16, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 20, (int) currentPlayer.currentPosition());
        assertEquals("Check Player B Position Tile", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 20, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO SEGMENTATION - no player exist on position

        nrPositionsToMove =6;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 18, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 24, (int) currentPlayer.currentPosition());
        assertEquals("CURRENT_PLAYER_ID Position Tile 123", "Segmentation Fault", game.getTitle(currentPlayer.currentPosition()));
        assertEquals("CURRENT_PLAYER_ID Position Tile", "core-dumped.png", game.getImagePng(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 24, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO SEGMENTATION - 1 player already exist on position

        nrPositionsToMove =4;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 20, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 24, (int) currentPlayer.currentPosition());
        assertEquals("Check Player B Position Tile 123", "Segmentation Fault", game.getTitle(currentPlayer.currentPosition()));
        assertEquals("Check Player B Position Tile", "core-dumped.png", game.getImagePng(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 21, (int) currentPlayer.currentPosition());
    }

    /*
    Check game for two players for Abyss Infinite Cycle
     */
    @Test
    public void move2PlayersTestInfiniteCycle()
    {
        GameManager game = getGameManager();
        String[][] board = new String[2][4];
        board[0][0] = "23";
        board[0][1] = "B";
        board[0][2] = "C#;Java";
        board[0][3] = "PURPLE";
        board[1][0] = "2";
        board[1][1] = "A";
        board[1][2] = "Pyton;TypeScript";
        board[1][3] = "BROWN";

        //Abyss
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

        game.createInitialBoard(board, 79, objects);
        Programmer currentPlayer;
        int nrPositionsToMove;

        //#####
        //TURN TO ABYSS SYNTAX - Goes back 1 position

        nrPositionsToMove =1;
        currentPlayer = game.getCurrentPlayer();
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 2, (int) currentPlayer.currentPosition());
        assertEquals("Erro de Syntax - Image", "syntax.png", game.getImagePng(currentPlayer.currentPosition()));
        assertEquals("Erro de Syntax - Title", "Erro de sintaxe", game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 1, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO ABYSS SYNTAX - Goes back 1 position

        nrPositionsToMove =2;
        currentPlayer = game.getCurrentPlayer();
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 3, (int) currentPlayer.currentPosition());
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 2, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO ABYSS LOGIC - Goes back n\2 positions

        nrPositionsToMove =3;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 1, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 4, (int) currentPlayer.currentPosition());
        assertEquals("Erro de lógica - Title", "Erro de lógica", game.getTitle(currentPlayer.currentPosition()));
        assertEquals("Erro de lógica - Image", "logic.png", game.getImagePng(currentPlayer.currentPosition()));
        assertEquals("Check Player Previous Position", 1, (int)currentPlayer.previousTurnPosition(1));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 3, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO ABYSS LOGIC - Goes back n\2 positions

        nrPositionsToMove =2;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 2, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 4, (int) currentPlayer.currentPosition());
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 3, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO ABYSS EXCEPTION  - Goes back 2 positions

        nrPositionsToMove =4;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 3, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 7, (int) currentPlayer.currentPosition());
        assertEquals("Erro de Exception", "Exception", game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 5, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO ABYSS EXCEPTION  - Goes back 2 positions

        nrPositionsToMove =5;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 3, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 8, (int) currentPlayer.currentPosition());
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 6, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO FILE NOT FOUND - Goes back 3 positions

        nrPositionsToMove =6;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 5, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 11, (int) currentPlayer.currentPosition());
        assertEquals("Erro File Not Found Exception", "File Not Found Exception", game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 8, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO FILE NOT FOUND - Goes back 3 positions

        nrPositionsToMove =4;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 6, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 10, (int) currentPlayer.currentPosition());
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 7, (int) currentPlayer.currentPosition());
        //#####################
        //Begin check File Not Found Exception - Goes back 3 positions

        //#####
        //TURN TO CRASH - returns to start

        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 8, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 12, (int) currentPlayer.currentPosition());
        assertEquals("Erro Crash", "Crash (aka Rebentanço)", game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 1, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =2;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 7, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 9, (int) currentPlayer.currentPosition());
        assertEquals("Tile Empty", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 9, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =5;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 1, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 6, (int) currentPlayer.currentPosition());
        assertEquals("CURRENT_PLAYER_ID Position Tile", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 6, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO Duplicated Code - returns to previous position

        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 9, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 14, (int) currentPlayer.currentPosition());
        assertEquals("Check Player B Position Tile", "Duplicated Code", game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 9, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO File Not Found  - back 3 positions

        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 6, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 11, (int) currentPlayer.currentPosition());
        assertEquals("CURRENT_PLAYER_ID Position Tile", "File Not Found Exception", game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 8, (int) currentPlayer.currentPosition());
        assertEquals("CURRENT_PLAYER_ID Position Tile after react", "Exception", game.getTitle(currentPlayer.currentPosition()));

        //#####
        //TURN TO Empty

        nrPositionsToMove =4;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 9, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 13, (int) currentPlayer.currentPosition());
        assertEquals("Check Player B Position Tile", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 13, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =5;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 8, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 13, (int) currentPlayer.currentPosition());
        assertEquals("CURRENT_PLAYER_ID Position Tile", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 13, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =2;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 13, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 15, (int) currentPlayer.currentPosition());
        assertEquals("Check Player B Position Tile 15", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 15, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO SECONDARY EFFECTS - Goes back to previous to previous

        nrPositionsToMove =4;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 13, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 17, (int) currentPlayer.currentPosition());
        assertEquals("CURRENT_PLAYER_ID Position Tile", "Efeitos secundários", game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 8, (int) currentPlayer.currentPosition());
        assertEquals("CURRENT_PLAYER_ID Position Tile after react", "Exception", game.getTitle(currentPlayer.currentPosition()));
        assertFalse("Game is Over", game.gameIsOver());

        //#####
        //TURN TO SECONDARY EFFECTS - Goes back to previous to previous

        nrPositionsToMove =2;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 15, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 17, (int) currentPlayer.currentPosition());
        assertEquals("Check Player B Position Tile", "Efeitos secundários", game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 13, (int) currentPlayer.currentPosition());
        assertEquals("Check Player B Position Tile", null, game.getTitle(currentPlayer.currentPosition()));
        assertFalse("Game is Over", game.gameIsOver());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =5;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 8, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 13, (int) currentPlayer.currentPosition());
        assertEquals("CURRENT_PLAYER_ID Position Tile", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 13, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =3;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 13, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 16, (int) currentPlayer.currentPosition());
        assertEquals("Check Player B Position Tile 15", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 16, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =5;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 13, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 18, (int) currentPlayer.currentPosition());
        assertEquals("CURRENT_PLAYER_ID Position Tile", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 18, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =4;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 16, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 20, (int) currentPlayer.currentPosition());
        assertEquals("Check Player B Position Tile", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 20, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO INFINITE CYCLE - Lock current Player

        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 18, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 22, (int) currentPlayer.currentPosition());
        assertEquals("CURRENT_PLAYER_ID Position Tile", "Ciclo infinito", game.getTitle(currentPlayer.currentPosition()));
        assertEquals("CURRENT_PLAYER_ID Position Tile", "infinite-loop.png", game.getImagePng(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 22, (int) currentPlayer.currentPosition());
        assertTrue("CURRENT_PLAYER_ID Is Locked", currentPlayer.isLocked());

        //#####
        //TURN TO EMPTY

        nrPositionsToMove =1;
        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 20, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 21, (int) currentPlayer.currentPosition());
        assertEquals("Check Player B Position Tile", null, game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 21, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO: for locked player

        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 22, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 22, (int) currentPlayer.currentPosition());
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 22, (int) currentPlayer.currentPosition());

        //#####
        //TURN TO: Infinite Cycle - Lock current Player, Unlocks existing players

        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 21, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 22, (int) currentPlayer.currentPosition());
        assertEquals("Check Player B Position Tile", "Ciclo infinito", game.getTitle(currentPlayer.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 22, (int) currentPlayer.currentPosition());
        assertTrue("Check Player B Is Locked", currentPlayer.isLocked());

        //#####
        //TURN TO: Empty after player was unlocked

        currentPlayer = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, currentPlayer.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 22, (int) currentPlayer.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 23, (int) currentPlayer.currentPosition());
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 23, (int) currentPlayer.currentPosition());
    }

    /*
    Check game for two players for Abyss Blue Screen on Death
     */
    @Test
    public void move2PlayersUntilAbyssBlueScreen()
    {
        GameManager game = getGameManager();
        String[][] playersData = new String[2][4];
        playersData[0][0] = "23";
        playersData[0][1] = "B";
        playersData[0][2] = "C#;Java";
        playersData[0][3] = "PURPLE";
        playersData[1][0] = "2";
        playersData[1][1] = "A";
        playersData[1][2] = "Pyton;TypeScript";
        playersData[1][3] = "BROWN";

        String[][] objectsData = new String[12][3];
        //Abyss Syntax
        objectsData[0][0] = "0";
        objectsData[0][1] = "0";
        objectsData[0][2] = "2";
        objectsData[1][0] = "0";
        objectsData[1][1] = "0";
        objectsData[1][2] = "3";
        //Abyss Logic
        objectsData[2][0] = "0";
        objectsData[2][1] = "1";
        objectsData[2][2] = "4";
        objectsData[3][0] = "0";
        objectsData[3][1] = "1";
        objectsData[3][2] = "5";
        //Abyss Exception
        objectsData[4][0] = "0";
        objectsData[4][1] = "2";
        objectsData[4][2] = "7";
        objectsData[5][0] = "0";
        objectsData[5][1] = "2";
        objectsData[5][2] = "8";
        //Abyss FileNotFound
        objectsData[6][0] = "0";
        objectsData[6][1] = "3";
        objectsData[6][2] = "10";
        objectsData[7][0] = "0";
        objectsData[7][1] = "3";
        objectsData[7][2] = "11";
        //Abyss Crash
        objectsData[8][0] = "0";
        objectsData[8][1] = "4";
        objectsData[8][2] = "12";
        //Abyss Duplicated
        objectsData[9][0] = "0";
        objectsData[9][1] = "5";
        objectsData[9][2] = "14";
        //Abyss Duplicated
        objectsData[10][0] = "0";
        objectsData[10][1] = "6";
        objectsData[10][2] = "17";
        //Blue Screen
        objectsData[11][0] = "0";
        objectsData[11][1] = "7";
        objectsData[11][2] = "19";

        game.createInitialBoard(playersData, 79, objectsData);

        Programmer programmerToTest;
        int nrPositionsToMove;

        //#####
        //TURN TO: Abyss Syntax - Goes back 1 position

        nrPositionsToMove =1;
        programmerToTest = game.getCurrentPlayer();
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_ID, 2, programmerToTest.getId());
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 2, (int) programmerToTest.currentPosition());
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 1, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO: Abyss Syntax - Goes back 1 position

        nrPositionsToMove =2;
        programmerToTest = game.getCurrentPlayer();
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_ID, 23, programmerToTest.getId());
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 3, (int) programmerToTest.currentPosition());
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 2, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO: Abyss Logic - Goes back n\2 positions

        nrPositionsToMove =3;
        programmerToTest = game.getCurrentPlayer();
        assertEquals("Check Player", 2, programmerToTest.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 1, (int) programmerToTest.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 4, (int) programmerToTest.currentPosition());
        assertEquals("Abyss: Erro de Lógica - Title", "Erro de lógica", game.getTitle(programmerToTest.currentPosition()));
        assertEquals("Abyss: Erro de Lógica - Image", "logic.png", game.getImagePng(programmerToTest.currentPosition()));
        assertEquals("Player Position A Ultima", 1, (int)programmerToTest.previousTurnPosition(1));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION, 3, (int) programmerToTest.currentPosition());

        //#####
        //TURN to Abyss Logic - Goes back n\2 positions

        nrPositionsToMove =2;
        programmerToTest = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, programmerToTest.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 2, (int) programmerToTest.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 4, (int) programmerToTest.currentPosition());
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 3, (int) programmerToTest.currentPosition());

        //#####
        //TURN to Abyss Exception - Goes back 2 positions

        nrPositionsToMove =4;
        programmerToTest = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, programmerToTest.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 3, (int) programmerToTest.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 7, (int) programmerToTest.currentPosition());
        assertEquals("Abyss: Erro de Exception - Title", "Exception", game.getTitle(programmerToTest.currentPosition()));
        assertEquals("Abyss: Erro de Exception - Image", "exception.png", game.getImagePng(programmerToTest.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 5, (int) programmerToTest.currentPosition());

        //#####
        //TURN to Abyss Exception - Goes back 2 positions

        nrPositionsToMove =5;
        programmerToTest = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, programmerToTest.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 3, (int) programmerToTest.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 8, (int) programmerToTest.currentPosition());
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 6, (int) programmerToTest.currentPosition());

        //#####
        //TURN to File Not Found Exception - Goes back 3 positions

        nrPositionsToMove =6;
        programmerToTest = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, programmerToTest.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 5, (int) programmerToTest.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 11, (int) programmerToTest.currentPosition());
        assertEquals("Erro File Not Found Exception", "File Not Found Exception", game.getTitle(programmerToTest.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 8, (int) programmerToTest.currentPosition());

        //#####
        //TURN to File Not Found Exception - Goes back 3 positions

        nrPositionsToMove =4;
        programmerToTest = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, programmerToTest.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 6, (int) programmerToTest.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 10, (int) programmerToTest.currentPosition());
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 7, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO Crash Exception - Goes back to start

        programmerToTest = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, programmerToTest.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 8, (int) programmerToTest.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 12, (int) programmerToTest.currentPosition());
        assertEquals("Erro Crash", "Crash (aka Rebentanço)", game.getTitle(programmerToTest.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 1, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO Empty

        nrPositionsToMove =2;
        programmerToTest = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, programmerToTest.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 7, (int) programmerToTest.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 9, (int) programmerToTest.currentPosition());
        assertEquals("Tile Empty", null, game.getTitle(programmerToTest.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 9, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO Empty

        nrPositionsToMove =5;
        programmerToTest = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, programmerToTest.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 1, (int) programmerToTest.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 6, (int) programmerToTest.currentPosition());
        assertEquals("CURRENT_PLAYER_ID Position Tile", null, game.getTitle(programmerToTest.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 6, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO File Not Found Exception

        programmerToTest = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, programmerToTest.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 9, (int) programmerToTest.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 14, (int) programmerToTest.currentPosition());
        assertEquals("Check Player B Position Tile", "Duplicated Code", game.getTitle(programmerToTest.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 9, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO File Not Found Exception

        programmerToTest = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, programmerToTest.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 6, (int) programmerToTest.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 11, (int) programmerToTest.currentPosition());
        assertEquals("CURRENT_PLAYER_ID Position Tile", "File Not Found Exception", game.getTitle(programmerToTest.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 8, (int) programmerToTest.currentPosition());
        assertEquals("CURRENT_PLAYER_ID Position Tile after react", "Exception", game.getTitle(programmerToTest.currentPosition()));

        //#####
        //TURN TO Empty

        nrPositionsToMove =4;
        programmerToTest = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, programmerToTest.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 9, (int) programmerToTest.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 13, (int) programmerToTest.currentPosition());
        assertEquals("Check Player B Position Tile", null, game.getTitle(programmerToTest.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 13, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO Empty

        nrPositionsToMove =5;
        programmerToTest = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, programmerToTest.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 8, (int) programmerToTest.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 13, (int) programmerToTest.currentPosition());
        assertEquals("CURRENT_PLAYER_ID Position Tile", null, game.getTitle(programmerToTest.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 13, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO Empty

        nrPositionsToMove =2;
        programmerToTest = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, programmerToTest.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 13, (int) programmerToTest.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 15, (int) programmerToTest.currentPosition());
        assertEquals("Check Player B Position Tile 15", null, game.getTitle(programmerToTest.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 15, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO Empty

        nrPositionsToMove =4;
        programmerToTest = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, programmerToTest.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 13, (int) programmerToTest.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 17, (int) programmerToTest.currentPosition());
        assertEquals("CURRENT_PLAYER_ID Position Tile", "Efeitos secundários", game.getTitle(programmerToTest.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 8, (int) programmerToTest.currentPosition());
        assertEquals("CURRENT_PLAYER_ID Position Tile after react", "Exception", game.getTitle(programmerToTest.currentPosition()));
        assertFalse("Game is Over", game.gameIsOver());

        //#####
        //TURN TO Empty

        nrPositionsToMove =2;
        programmerToTest = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, programmerToTest.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 15, (int) programmerToTest.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 17, (int) programmerToTest.currentPosition());
        assertEquals("Check Player B Position Tile", "Efeitos secundários", game.getTitle(programmerToTest.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 13, (int) programmerToTest.currentPosition());
        assertEquals("Check Player B Position Tile", null, game.getTitle(programmerToTest.currentPosition()));
        assertFalse("Game is Over", game.gameIsOver());

        //#####
        //TURN TO Empty

        nrPositionsToMove =5;
        programmerToTest = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, programmerToTest.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 8, (int) programmerToTest.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 13, (int) programmerToTest.currentPosition());
        assertEquals("CURRENT_PLAYER_ID Position Tile", null, game.getTitle(programmerToTest.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION, 13, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO Empty

        nrPositionsToMove =3;
        programmerToTest = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 23, programmerToTest.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 13, (int) programmerToTest.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 16, (int) programmerToTest.currentPosition());
        assertEquals("Check Player B Position Tile 15", null, game.getTitle(programmerToTest.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 16, (int) programmerToTest.currentPosition());

        //#####
        //TURN TO Abyss Blue Screen of Death

        nrPositionsToMove =6;
        programmerToTest = game.getCurrentPlayer();
        assertEquals(CURRENT_PLAYER_ID, 2, programmerToTest.getId());
        assertEquals(CURRENT_PLAYER_POSITION, 13, (int) programmerToTest.currentPosition());
        move(game, nrPositionsToMove);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_MOVE, 19, (int) programmerToTest.currentPosition());
        assertEquals("Abyss Blue Screen of Death - Image", "bsod.png", game.getImagePng(programmerToTest.currentPosition()));
        assertEquals("Abyss Blue Screen of Death - Tile", "Blue Screen of Death", game.getTitle(programmerToTest.currentPosition()));
        reactToTitle(game);
        assertEquals(CURRENT_PLAYER_POSITION_AFTER_REACT, 19, (int) programmerToTest.currentPosition());
        assertTrue("Game is Over", game.gameIsOver());
        assertEquals("Count Players not defeated", 1, game.getProgrammers(false).size());
    }
}
