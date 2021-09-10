import kotlin.coroutines.coroutineContext

fun countByTwo(items: Array<Int>) {
    for(i in items.indices) if(items[i] % 2 == 0) println(items[i])
}

fun pickIndex(items: Array<Int>, index: Int) {
    for(i in items.indices) if(i == index) println(items[i - 1])
}

fun printBack(items: Array<Int>) {
    for(i in items.size - 1 downTo 0) println(items[i])
}

fun printSides(items: IntArray) {
    val mid = if(items.size % 2 == 0) items.size / 2 else (0 + items.size) / 2
    println(mid)
    print("Right side of array:  ")
    for(i in 0 until mid) print("${items[i]} ")
    println()
    print("Left side of array:  ")
    for(i in mid until items.size) print("${items[i]} ")
}

fun multEvens(items: IntArray) : Int {
    var mult = 1
    for(i in 1 until items.size step 2) {
       println(items[i])
        mult *= items[i]
    }
    return mult
}

fun multOdds(items: IntArray) : Int {
    var mult = 1
    for(i in 2 until items.size step 3) {
        println(items[i])
        mult *= items[i]
    }
    return mult
}

fun lambdaFun(s: Int, say: String,  func: (input: Int, str: String) -> Unit?) {
    func(s, say)
}

fun wut(input: Int, str: String) {
    for(i in 1..input) println(str)
}

fun main() {
    val str1 = "1234"
    if(findDuplicates(str1)) println("There are duplicates!")
    else println("There are no duplicates..")

}

fun findDuplicates(str: String): Boolean {
    var counter = -1
    for(i in str.indices) {
        for(j in str.indices) {
            if(str[i] == str[j]) counter++
        }
        if(counter > 0) return true
        counter = -1
    }
    return false
}

// Coroutine practice
fun task1() {
    print("Hello ")
}

suspend fun task2() {
    print("World!")
}