package pt.ulusofona.lp2.deisiGreatGame;

/*
Exception Type
*/
enum ExceptionType{
    GAME, BOARD, PLAYER, TOOL, ABYSS;
}

/*
Custom Exception for Invalid Initial Board
 */
public class InvalidInitialBoardException extends Exception {

    //###########
    //Attributes

    private String message;
    private ExceptionType type;
    private int typeId;

    //###########
    //Constructor

    /*
    Constructor for common Exception
     */
    public InvalidInitialBoardException(String message, ExceptionType type) {
        this.message = message;
        this.type = type;
    }

    /*
    Constructor for Abyss and Tool Exception
     */
    public InvalidInitialBoardException(String message, ExceptionType type, int typeId) {
        this.message = message;
        this.type = type;
        this.typeId = typeId;
    }

    //###########
    //METHODS

    @Override
    public String getMessage() {
        return message;
    }

    /*
    Is Exception to type Abyss
     */
    public boolean isInvalidAbyss(){
        return (type == ExceptionType.ABYSS);
    }

    /*
    Is Exception to type Tool
     */
    public boolean isInvalidTool(){
        return (type == ExceptionType.TOOL);
    }

    /*
    Returns Abyss or Tool exception type id
     */
    public int getTypeId(){
        return typeId;
    }

}
