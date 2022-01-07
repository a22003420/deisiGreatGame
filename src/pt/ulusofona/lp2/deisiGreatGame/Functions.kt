package pt.ulusofona.lp2.deisiGreatGame

enum class CommandType{ GET, POST }

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

fun router() : (CommandType) -> (GameManager, List<String>) -> String? = ::selectCommand


fun getPlayer(list: List<Programmer>, name: String) : String {
    val programmer = list.filter { name in it.name }
    if(programmer.isNotEmpty()) {
        programmer.forEach { return it.toString() }
    }
    return "Inexistent player"
}

fun getPlayersByLanguage(list: List<Programmer>, language: String) : String {
    return if(list.filter { it.ContainsLanguages(language) }.isNullOrEmpty()) ""
    else (list.filter { it.ContainsLanguages(language) }.joinToString(",") { it.name })
}







/*
fun execGetMostUsedPositions(list: List<Casa>, max: Int) : String {
    val positions =
            list.sortedWith { c1, c2 -> c1.count - c2.count }
                    .reversed()
                    .take(max)

    return positions.joinToString("\n", "", ""){ "${it.position}:${it.count}"}
}

 */



fun getPolyglots(list: List<Programmer>) : String {
    val programmers = list.filter { programmer->programmer.NumberOfLanguages() > 1 }
            .sortedWith { p1, p2 -> p1.NumberOfLanguages() - p2.NumberOfLanguages() }
    return programmers.joinToString("\n","","") { "${it.getName()}:${it.NumberOfLanguages()}" }
}

fun getMostUsedPositions(list: List<Tile>, max: Int) : String {
    return ""
}

fun getMostUsedAbysses(list: List<ToolFactory>, max: Int) : String {
    return ""
}


fun postMove(gameManager:GameManager, nrSpace: Int) : String {

    gameManager.moveCurrentPlayer(nrSpace)

    return gameManager.reactToAbyssOrTool() ?: "OK"

}

fun postAbyss(gameManager:GameManager, type: Int, position: Int) : String {
    var tile = gameManager.getTile(position)
    return if(tile!=null){
        "Position is occupied"
    }else{
        //Initialize all Abyss for the Game
        val abyssFactory = AbyssSingletonFactory.getInstance()
        tile=abyssFactory.getAbyss(type)
        "OK"
    }
}

fun main() {

}