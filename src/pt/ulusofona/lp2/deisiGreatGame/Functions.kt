package pt.ulusofona.lp2.deisiGreatGame

/*
Enumerator for Command Type
 */
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

}*/

fun router(commandType: CommandType) : Function2<GameManager,List<String>,String?> {

    when (commandType) {
        CommandType.GET -> return ::getPlayer
        CommandType.POST -> return ::getPlayer
        else -> return ::getPlayer
    }

}

/*
Returns player with first name equals given name
 */
fun getPlayer(manager: GameManager, args: List<String>): String?{
    return "ola"
}

/*
Returns players with a given language
 */
fun getPlayerByLanguage(){

}

/*

 */
fun getPolyglots(){

}

/*
Return
 */
fun getMostUsedPosition(){

}

/*
Return
 */
fun getMostUsedPositionAbysses(){

}

/*
Move player given positions
 */
fun postMove(){

}

/*
Places Abyss on given tile
 */
fun postAbysses(){

}

// <GET|POST> <COMMAND_NAME> [PARAMETRO_1] [PARAMETRO_2] …

    fun main(){


        val list: List<String> = listOf("x", "y", "z")


        val routerFn = router(CommandType.GET)
        val result = routerFn.invoke(GameManager(),list)

    }



/*
val routerFn = router()
val commandGetFn = routerFn.invoke(CommandType.GET)
val result = commandGetFn.invoke(manager, listOf("PLAYER", "Joshua"))
 */