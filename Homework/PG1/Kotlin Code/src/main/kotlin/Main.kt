/*

Tylor J. Hanshaw
This is the Main file that holds the main() function that adds to and prints the Linked List
An important function in this file is the rejoin(), which rejoins the array of linked lists after being split and
mildly sorted

All code has been translated from C++ to Kotlin

*/

fun main() {
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

fun rejoin(arr: Array<LLN?>, wh: Int, l: LLN?): LLN? {
    var newWh = wh // this is so I can change the value of 'wh'
    if(wh < 0) newWh = arr.size - 1
    if(arr[newWh] == null) return l
    val temp: LLN?; var newL = l // this is so I can change the value of 'l'
    if(arr[newWh]!!.getNext() != null) {
        temp = arr[newWh]?.getNext()
        arr[newWh]!!.setNext(newL)
        newL = arr[newWh]
        arr[newWh] = temp
    }
    else {
        arr[newWh]!!.setNext(newL)
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