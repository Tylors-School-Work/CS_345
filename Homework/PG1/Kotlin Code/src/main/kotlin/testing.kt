

fun main() {
    var arr: Array<Int?> = arrayOfNulls(5)
    arr[0] = 2


    for(i in arr.indices) println(arr[i])
}