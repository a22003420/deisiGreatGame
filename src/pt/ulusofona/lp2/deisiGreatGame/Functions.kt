package pt.ulusofona.lp2.deisiGreatGame

enum class CommandType{ GET, POST }

fun selectCommand(type: CommandType) : (GameManager, List<String>) -> String? {
    return when(type) {
        CommandType.GET -> ::getCommand
        CommandType.POST -> ::postCommand
    }
}

fun getCommand(gameManager: GameManager, args: List<String>): String? {
    val programmers = gameManager.getProgrammers(true)

    return when(args[0]) {
        "PLAYER" -> getPlayer(programmers, args[1])
        "PLAYERS_BY_LANGUAGE" -> getPlayersByLanguage(programmers, args[1])
        "POLYGLOTS" -> getPolyglots(programmers)
        "MOST_USED_POSITIONS" -> getMostUsedPositions(programmers, args[1].toInt())
        "MOST_USED_ABYSSES" -> getMostUsedAbysses(programmers, gameManager.tiles, args[1].toInt())
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
    return if(programmers.none { it.containsLanguage(language) }) ""
    else (programmers.filter { it.containsLanguage(language) }.joinToString(",") { it.name })
}

/*
get most polyglot players
 */
fun getPolyglots(programmers: List<Programmer>) : String {
    val foundProgrammers = programmers.filter { programmer->programmer.numberOfLanguages() > 1 }
            .sortedWith { p1, p2 -> p1.numberOfLanguages() - p2.numberOfLanguages() }
    return foundProgrammers.joinToString("\n","","") { "${it.name}:${it.numberOfLanguages()}" }
}

/*
get Most Tile hits statistics
 */
fun getMostUsedPositions(programmers: List<Programmer>, max: Int) : String {

    //return all programmers positions statistics in one list
    var positionsStatistics = mostUsedTiles(programmers)

    return positionsStatistics
        .filter { it !=2 }
        .groupingBy { it }
        .eachCount()
        .toList()
        .sortedByDescending { it.second }
        .take(max)
        .joinToString("\n", "", "") { "${it.first}:${it.second}" }
}

/*
get Most Abysses hits
 */
fun getMostUsedAbysses(programmers: List<Programmer>, tiles: List<Tile>, max: Int) : String {

    //return all programmers positions in one list
    val positionsStatistics = mostUsedTiles(programmers)

    //create Map to store abyss and number of occurrences for each abyss
    val frequencyMap: MutableMap<String, Int> = HashMap()

    //iterate on all positions to find if position is an abyss
    //use maxCounter to check if number of occurrences are satisfied
    var maxCounter = max
    for (position in positionsStatistics)
    {
        //check if tile is an abyss
        val tile = tiles[position]
        if(!tile.isAbyss){
            continue
        }

        //get tile for abyss
        val abyssDsg = tiles[position].title

        //fill frequency map
        if (frequencyMap.containsKey(abyssDsg)) {
            val counter = frequencyMap[abyssDsg]
            if (counter != null) {
                frequencyMap[abyssDsg] = counter + 1
            }
        } else {
            frequencyMap[abyssDsg] = 1
        }

        maxCounter--
    }

    //check if number of occurrences are satisfied
    while (maxCounter>0)
    {
        //iterate on tiles
        for (tile in tiles)
        {
            //check if file is an abyss
            if(!tile.isAbyss) {
                continue
            }

            //get abyss tile title
            val tileTile = tile.title
            //check if abyss tile title is already inside frequency map
            if (!frequencyMap.containsKey(tileTile)) {
                frequencyMap[tileTile] = 0
                maxCounter--
                if(maxCounter==0)
                {
                    break
                }
            }
        }
    }

    //return max abysses sorted descending by number of occurrences
    return frequencyMap.toList()
        .sortedBy { (_, value) -> value }.reversed()
        .take(max)
        .joinToString("\n", "", "") {"${it.first}:${it.second}"}
}

/*
Helper function to return all programmers positions statistics
Skip first index position
 */
private fun mostUsedTiles(programmers: List<Programmer>): MutableList<Int> {

    //list to store all programmers positions statistics in game
    val positionsStatistics = mutableListOf<Int>()

    //join all programmer positions statistics in one list excluding position 1
    programmers.
        forEach { it -> positionsStatistics.addAll(it.positionsStatistics.
        filterNot { it==1 }) }

    return positionsStatistics
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
    val tile = gameManager.getTile(position)
    val tiles = gameManager.tiles

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

fun main() {
} */