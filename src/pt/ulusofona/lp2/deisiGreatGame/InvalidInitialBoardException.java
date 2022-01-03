package pt.ulusofona.lp2.deisiGreatGame;

/*
Custom Exception for Invalid Initial Board
 */
public class InvalidInitialBoardException extends Exception {

    //###########
    //Attributes

    String message;

    //###########
    //Constructor

    public InvalidInitialBoardException(String message) {
        this.message = message;
    }

    //###########
    //METHODS

    @Override
    public String getMessage() {
        return message;
    }
}
