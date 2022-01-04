package pt.ulusofona.lp2.deisiGreatGame;

/*
Exception Type
*/
enum ExceptionType
{
    GAME("Jogo"), BOARD("Tabuleiro"), PLAYER("Jogador"), TOOL("Ferramenta"), ABYSS("Abismo");
    final String type;

    ExceptionType(String type) {
        this.type=type;
    }

    @Override
    public String toString() {
        return this.type;
    }
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

    public InvalidInitialBoardException(String message, ExceptionType type) {
        this.message = message;
        this.type = type;
    }

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
    Is Exception Abyss
     */
    public boolean isInvalidAbyss(){
        return (type == ExceptionType.ABYSS);
    }

    /*
    Is Exception Tool
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
