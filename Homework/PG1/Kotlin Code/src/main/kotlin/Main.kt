/*

    Tylor J. Hanshaw

    This is the Main.kt file where most of the programs' execution is done, including the rejoin() method
    No header files are needed in Kotlin

*/

// One thing of note here, in Kotlin there is no "new" keyword - you can simply create an instance of a class without "new"
    fun main(args: Array<String>) {
        val list = LL(null)
        while(true) {
            print("Enter a value: ")
            val input = readLine()
            if(input == "") break
            list.add(input!!)
        }
        list.shellSort()
        list.print()
    }

// Translated rejoin() method from the C++ code
// Most of this is similar to split() from the C++ code, but I did add more temporary variables to compensate for function parameters being of type val
// Also changed some variables from being of type var to val, seeing as one of the variables doesn't change through the method
// Also you can omit the "length" parameter from this method call since you can call "Array<T>.size" instead in Kotlin
fun rejoin(arr: Array<LLN?>, wh: Int, l: LLN?): LLN? {
    var newWh = wh
    if(wh < 0) newWh = arr.size - 1
    if(arr[newWh] == null) return l
    val temp: LLN?; var newL = l // this is so I can change the value of 'l'
    if(arr[newWh]!!.next != null) {
        temp = arr[newWh]?.next
        arr[newWh]!!.next = newL
        newL = arr[newWh]
        arr[newWh] = temp
    }
    else {
        arr[newWh]!!.next = newL
        newL = arr[newWh]
        arr[newWh] = null
    }
    newL = rejoin(arr, newWh - 1, newL)
    return newL

    /*
	 rejoin takes the lists in arr (whose length is len) and appends them to
	 the beginning of list l.
	 wh indicates the position within arr the element that needs to be the
	 first one attached to l.
	*/
}