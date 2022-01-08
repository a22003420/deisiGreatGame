package pt.ulusofona.lp2.deisiGreatGame

import java.util.ArrayList

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
        "MOST_USED_POSITIONS" -> getMostUsedPositions(list,1)
        "MOST_USED_ABYSSES" -> getMostUsedAbysses(list,1)
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

/*

 */
fun getPlayer(list: List<Programmer>, name: String) : String {
    val programmer = list.filter { name in it.name }
    if(programmer.isNotEmpty()) {
        programmer.forEach { return it.toString() }
    }
    return "Inexistent player"
}

/*

 */
fun getPlayersByLanguage(list: List<Programmer>, language: String) : String {
    return if(list.filter { it.containsLanguage(language) }.isNullOrEmpty()) ""
    else (list.filter { it.containsLanguage(language) }.joinToString(",") { it.name })
}

/*

 */
fun getPolyglots(list: List<Programmer>) : String {
    val programmers = list.filter { programmer->programmer.numberOfLanguages() > 1 }
            .sortedWith { p1, p2 -> p1.numberOfLanguages() - p2.numberOfLanguages() }
    return programmers.joinToString("\n","","") { "${it.getName()}:${it.numberOfLanguages()}" }
}

/*
get Most Used Tile Positions
 */
fun getMostUsedPositions(listProgrammer: List<Programmer>, max: Int) : String {

    //return all programmers positions in one list
    val listPositions = mostUsedTiles(listProgrammer)

    //create Map to store position and number of ocurrences for each position
    val frequencyMap: MutableMap<Int, Int> = HashMap()
    for (positionInList in listPositions)
    {
        var count = frequencyMap[positionInList]
        if (count == null) {
            count = 0
        }
        frequencyMap[positionInList] = count + 1
    }

    //return max elements sorted descending by number of occurrences
    return frequencyMap.toList()
        .sortedBy { (key, value) -> value }.reversed()
        .take(max)
        .joinToString("\n", "", "") { "${it.first}:${it.second}" }
}

fun getMostUsedAbysses(list: List<Programmer>, maximo: Int) : String {
    /*
    val positions =
    list.sortedWith { c1, c2 -> c1.count - c2.count }
        .reversed()
        .take(maximo)

    return positions.joinToString("\n", "", ""){ "${it.position}:${it.count}"}
    */

    return "";
}

/*
Helper function to return all programmers positions
 */
private fun mostUsedTiles(listProgrammer: List<Programmer>): MutableList<Int> {
    //list to store all programmers positions in game
    val listPositions = mutableListOf<Int>()
    //join all programmer positions in one list
    listProgrammer.forEach { listPositions.addAll(it.positions) }
    return listPositions
}

fun postMove(gameManager:GameManager, nrSpace: Int) : String {
    gameManager.moveCurrentPlayer(nrSpace)
    return gameManager.reactToAbyssOrTool() ?: "OK"
}

fun postAbyss(gameManager: GameManager, type: Int, position: Int): String {
    var tile = gameManager.getTile(position)
    return if (tile.title == "Casa Vazia") {
        //Initialize all Abyss for the Game
        val abyssFactory = AbyssSingletonFactory.getInstance()
        tile = abyssFactory.getAbyss(type)
        "OK"
    } else {
        "Position is occupied"
    }
}

fun main() {



}