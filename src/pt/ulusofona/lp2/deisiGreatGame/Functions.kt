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
    val tiles = gameManager.tiles;

    return when(args[0]) {
        "PLAYER" -> getPlayer(list, args[1])
        "PLAYERS_BY_LANGUAGE" -> getPlayersByLanguage(list, args[1])
        "POLYGLOTS" -> getPolyglots(list)
        "MOST_USED_POSITIONS" -> getMostUsedPositions(list, args[1].toInt())
        "MOST_USED_ABYSSES" -> getMostUsedAbysses(list, tiles, args[1].toInt())
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

/*
Router
 */
fun router() : (CommandType) -> (GameManager, List<String>) -> String? = ::selectCommand

/*
Get player by name
 */
fun getPlayer(programmers: List<Programmer>, name: String) : String {
    val programmer = programmers.filter { name in it.name }
    if(programmer.isNotEmpty()) {
        programmer.forEach { return it.toString() }
    }
    return "Inexistent player"
}

/*
get players by language
 */
fun getPlayersByLanguage(programmers: List<Programmer>, language: String) : String {
    return if(programmers.filter { it.containsLanguage(language) }.isNullOrEmpty()) ""
    else (programmers.filter { it.containsLanguage(language) }.joinToString(",") { it.name })
}

/*
get most polyglot players
 */
fun getPolyglots(programmers: List<Programmer>) : String {
    val foundProgrammers = programmers.filter { programmer->programmer.numberOfLanguages() > 1 }
            .sortedWith { p1, p2 -> p1.numberOfLanguages() - p2.numberOfLanguages() }
    return foundProgrammers.joinToString("\n","","") { "${it.getName()}:${it.numberOfLanguages()}" }
}

/*
get Most Tile hits
 */
fun getMostUsedPositions(programmers: List<Programmer>, max: Int) : String {

    //return all programmers positions in one list
    val positions = mostUsedTiles(programmers)

    //create Map to store position and number of occurrences for each position
    val frequencyMap: MutableMap<Int, Int> = HashMap()
    for (position in positions)
    {
        var count = frequencyMap[position]
        if (count == null) {
            count = 0
        }
        frequencyMap[position] = count + 1
    }

    var xxx = frequencyMap.toList()
        .sortedBy { (key, value) -> value }.reversed()
        .take(max)
        .joinToString("\n", "", "") { "${it.first}:${it.second}" }

    //return max elements sorted descending by number of occurrences
    return frequencyMap.toList()
        .sortedBy { (key, value) -> value }.reversed()
        .take(max)
        .joinToString("\n", "", "") { "${it.first}:${it.second}" }
}

/*
get Most Abysses hits
 */
fun getMostUsedAbysses(programmers: List<Programmer>, tiles: ArrayList<Tile>, maximo: Int) : String {

    //return all programmers positions in one list
    val positions = mostUsedTiles(programmers)

    //create Map to store position and number of ocurrences for each abyss
    val frequencyMap: MutableMap<String, Int> = HashMap()

    //iterate on all positions
    for (position in positions)
    {
        //val tile =
    }

    return "";
}

/*
Helper function to return all programmers positions
Drop first position
 */
private fun mostUsedTiles(programmers: List<Programmer>): MutableList<Int> {
    //list to store all programmers positions in game
    val listPositions = mutableListOf<Int>()
    //join all programmer positions in one list
    programmers.forEach { listPositions.addAll(it.positions.drop(1)) }
    return listPositions
}

/*
Move programmer n tiles
 */
fun postMove(gameManager:GameManager, nrSpace: Int) : String {
    gameManager.moveCurrentPlayer(nrSpace)
    return gameManager.reactToAbyssOrTool() ?: "OK"
}

/*
Post Abyss on Tile
 */
fun postAbyss(gameManager: GameManager, type: Int, position: Int): String {
    var tile = gameManager.getTile(position)
    var tiles = gameManager.tiles;

    return if (tile.title == null) {
        //Initialize all Abyss for the Game
        val abyssFactory = AbyssSingletonFactory.getInstance()
        tiles[position] = abyssFactory.getAbyss(type)
        "OK"
    } else {
        "Position is occupied"
    }
}

/*
Main function
 */
fun main() {
}