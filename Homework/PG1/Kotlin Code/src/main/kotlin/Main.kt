/*



*/

fun main() {
    var list = LL(null)
    while(true) {
        print("Enter a value: ")
        var input = readLine()
        if(input == "") break
        list.add(input!!)
    }
    list.shellSort()
    list.print()
}

fun rejoin(arr: Array<LLN?>, wh: Int, l: LLN?): LLN? {
    var newWh = wh
    if(wh < 0) newWh = arr.size - 1
    if(arr[newWh] == null) return l
    var temp: LLN?; var newL = l // this is so I can change the value of 'l'
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