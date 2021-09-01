/*

    Tylor J. Hanshaw

    This is the LLN.kt file that holds the code for the LinkedListNode class
    As you can tell all getter and setter methods are missing, we don't need them in these neck of the woods!

    **One thing I do want to bring up is the "return if()" statement down below, on lines 61-66**

*/

class LLN(var data: String,var next: LLN? = null) {

    fun print() {
        println(data)
        if(next != null) next!!.print()
    }

    fun ct(): Int {
        if(next == null) return 1
        return 1 + next!!.ct()
    }

    // Translated split() method from the C++ code
    // Most of this is basically the same, minus the null safety checking and the getter and setter methods
    // But I did have to add some temporary variables, since function parameters are of type val by default they are non-mutable
    fun split(arr: Array<LLN?>, wh: Int) {
        val temp: LLN?
        if(wh < arr.size && arr[wh] == null) {
            arr[wh] = this
            temp = next
            next = null
            temp?.split(arr, wh + 1)
        }
        else {
            var newWh = wh
            if(newWh == arr.size) newWh = 0
            temp = next
            next = arr[newWh]
            arr[newWh] = this
            temp?.split(arr, newWh + 1)
        }

        /*
         split takes the linked headed by "this" and splits it into an array arr
         (whose length is len).  wh indicates the position at which the first
         node in the list should go.
        */
    }

    // Translated mildsort() method from the C++ code
    // Most of this is basically the same, minus the null safety checking and the getter and setter methods
    // **One thing oe here is the "return if()" statement, if the statement is true it will return whatever is in the last line of the statement**
    // **Otherwise it will return whatever is after the "else" statement**
    fun mildSort(): LLN {

        val temp2: LLN?
        if(next == null) return this
        val temp1: LLN = next!!.mildSort()
        next = temp1
        return if(data < next!!.data) { // <--------
            temp2 = next!!.next
            temp1.next = this
            next = temp2
            temp1
        } else this

        /*
         mildsort sorts the "this" list and returns the new head.
         mildsort assumes that no node is more than one position out of place
         and uses this assumption to run very fast.
        */
    }
}