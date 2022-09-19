import kotlin.random.Random
import kotlin.system.exitProcess

fun main() {
    val clientMoney = slotAnnotation()
    val stake = stakeChoise()
    startGame(clientMoney,stake)
}
fun slotAnnotation(): Int? {
    println("Hello, welcome to NaebalovoSLot! Please insert your last money:")
    val clientMoney = readLine()
    isInteger(clientMoney)
    println("Your bankroll is $clientMoney$")
    return clientMoney?.toInt()
}
fun isInteger(s: String?): Boolean {
    return try {
        s?.toInt()
        true
    } catch (e: NumberFormatException) {
        println("ERROR!1!!!! WRITE VALUE PLEASE, NOT LETTERS OR WORDS")
        exitProcess(0)
    }
}
fun stakeChoise(): Int {
    val stakes = listOf(5,10,25,50)
    println("Choose your stake $stakes, right a number from 1 to 4")
    val s = readLine()
    isInteger(s)
    var stake = 0
    when (s?.toInt()) {
        1 -> stake = stakes[0]
        2 -> stake = stakes[1]
        3 -> stake = stakes[2]
        4 -> stake = stakes[3]
        else -> {
            println("Unknown command, please choose your stake with numbers")
            stakeChoise()
        }
    }
    println("Your stake is $stake$")
    return stake
}
fun depositGame(): Int {
    println("Please, write you deposit value")
    val deposit = readLine()
    isInteger(deposit)
    if (deposit == null || deposit.toInt() == 0) {
        println("Deposit don't permit, plz call to bank")
        exitProcess(0)
    }else {
        println("Congrats, your deposit $deposit are accepted!!!")
        return deposit.toInt()
    }
}
fun withdrawGame(): Int {
    println("Please, write you withdraw value")
    val draw = readLine()
    isInteger(draw)
    return if (draw == null || draw.toInt() == 0) {
        println("Withdraw don't permit, plz call to bank")
        exitProcess(0)
    }else
        draw.toInt()
}
fun startGame(valet:Int?, stake:Int) {

    var remainingMoney = valet?.minus(stake)
    println("To start game write 's',for check your balance write 'b'" +
            " for exit write 'e'")
    println("For Deposit, write 'd', for withdraw, write 'w' ")
    when (readLine()) {
        "s" -> println("Your game was started!")
        "b" -> {
            if (remainingMoney != null) {
                println("Your balance is ${remainingMoney + stake}$")
                startGame((remainingMoney+stake),stake)
            }
        }
        "e" -> {
            println("Goodbye! see you later")
            exitProcess(0)
        }
        "d" -> {
            val dep = depositGame()
            if (remainingMoney != null) {
                remainingMoney += dep
                startGame(remainingMoney+stake,stake)
            }
        }
        "w" -> {
            val draw = withdrawGame()
            if ((remainingMoney != null) && ((remainingMoney + stake - draw) >= 0)) {
                println("Congrats, your draw $draw are accepted!!!")
                remainingMoney -= draw
                startGame(remainingMoney + stake, stake)
            }else{
                println("Your withdraw cannot permit, not enough balance")
                if (remainingMoney != null) {
                    startGame(remainingMoney + stake, stake)
                }
            }
        }
        else-> {
            println("Unknown command")
            startGame(valet,stake)
        }
    }
    println("Your remaining money is $remainingMoney$")
    println("Vroom,vrooom,vroom")
    Thread.sleep(2000)
    val number = Random.nextInt(10,99)
    println("---$number---")
    Thread.sleep(1000)
    when(number){
        11,22,33,44,55,66,88,99 -> {
            println("Congrats, your win ${stake * 8}$")
            if (remainingMoney != null) {
                remainingMoney += stake * 8
            }
        }
        77 -> {
            println("JACKPOT, your win ${stake*200}")
            if (remainingMoney != null) {
                remainingMoney += stake * 200
            }
        }
        else -> println("No price, TRY AGAIN")
    }
    println("Remaining money is $remainingMoney$")
    if (remainingMoney != null && remainingMoney > 0)
        startGame(remainingMoney,stake)
    else println("Ti vse proebal, spasibo")
    exitProcess(0)
}
