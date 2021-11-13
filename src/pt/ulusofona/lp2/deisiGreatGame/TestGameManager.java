package pt.ulusofona.lp2.deisiGreatGame;
//Imports
import org.junit.Test;
import static org.junit.Assert.*;

public class TestGameManager {
    @Test
    public void test01(){

        GameManager game = new GameManager();

        String[][] board = new String[2][4];
        board[0][0] = "23";
        board[0][1] = "Joao";
        board[0][2] = "C#;Java";
        board[0][3] = "PURPLE";

        board[1][0] = "2";
        board[1][1] = "Rui";
        board[1][2] = "Pyton;TypeScript";
        board[1][3] = "BROWN";


        game.createInitialBoard(board, 20);

        //game.moveCurrentPlayer(4);

        int i = game.getCurrentPlayerID();
        assertTrue("GetCurrentPlayerId_Ok",i>0);

    }

    @Test
    public void test02(){




    }

    /*@Test
    public void test03Depositar(){

        ContaBancaria conta = new ContaBancaria (20);
        conta.depositar(100);
        long saldoEsperado = 120;
        long saldoObtido = conta.getSaldo();
        assertEquals(saldoEsperado, saldoObtido);


    }*/



}
