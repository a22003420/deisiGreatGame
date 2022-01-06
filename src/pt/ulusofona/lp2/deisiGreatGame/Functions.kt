package pt.ulusofona.lp2.deisiGreatGame

enum class CommandType{ GET, POST }

fun router() : (CommandType) -> (GameManager, List<String>) -> String? = ::selectCommand

/*
enum class CommandType{ GET, POST }

//fun f1(x: Int, y: Int) = x + y
//fun f2(x: Int, y: Int) = 2 * x + y
//fun f3(x: Int, y: Int) = x - y
/*fun router(commandType: CommandType) : Function2<Int,Int,Int> {

    when (commandType) {
        CommandType.GET -> return ::f1
        CommandType.POST -> return ::f2
        else -> return ::f3
    }

}
*/
 */

fun selectCommand(type: CommandType) : (GameManager, List<String>) -> String? {
    return when(type) {
        CommandType.GET -> ::getCommand
        CommandType.POST -> ::postCommand
    }
}

fun getCommand(gameManager: GameManager, args: List<String>): String? {
    val list = gameManager.getProgrammers(true)

    return when(args[0]) {
        "PLAYER" -> getPlayer(list, args[1])
        "PLAYERS_BY_LANGUAGE" -> getPlayersByLanguage(list, args[1])
        "POLYGLOTS" -> getPolyglots(list)
        "MOST_USED_POSITIONS" -> null
        "MOST_USED_ABYSSES" -> null
        else -> null
    }
}

fun postCommand(gameManager: GameManager, args: List<String>): String? {
    return when(args[0]) {
        "MOVE" -> postMove(gameManager, args[1].toInt())
        "ABYSS" -> postAbyss(gameManager, args[1].toInt(), args[2].toInt())
        else -> null
    }
}

fun getPlayer(list: List<Programmer>, name: String) : String {
    val player = list.filter { name in it.name }
    if(player.isNotEmpty()) {
        player.forEach { return it.toString() }
    }
    return "Inexistent player"
}

fun getPlayersByLanguage(list: List<Programmer>, lang: String) : String {

    return ""
}

fun getPolyglots(list: List<Programmer>) : String {
        return ""
}

fun getMostUsedPositions(list: List<Tile>, max: Int) : String {
    return ""
}

fun getMostUsedAbysses(list: List<ToolFactory>, max: Int) : String {
    return ""
}


fun postMove(gameManager:GameManager, nrSpace: Int) : String {

    gameManager.moveCurrentPlayer(nrSpace)

    //return gameManager.reactToAbyssOrTool() ?: "OK"

    return "OK";
}

fun postAbyss(gameManager:GameManager, type: Int, pos: Int) : String {
    return gameManager.reactToAbyssOrTool()
}

fun main() {

}