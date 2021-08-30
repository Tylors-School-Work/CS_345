/*

Tylor J. Hanshaw
This is the Linked List Node file that holds the LLN class along with all of its member functions
Some LLN functions of note are split() and mildsort()

All code has been translated from C++ to Kotlin

*/

// Come back and remove 'private' from the constructors

// Also remove the getter and setter functions to, I'll have to rework my code a bit in
//  split() and mildsort(), but it shouldn't be a huge issue

class LLN(private var data: String,private var next: LLN? = null) {

    fun getData(): String { return data }

    fun getNext(): LLN? { return next }

    fun setNext(n: LLN?) { next = n }

    fun print() {
        println(data)
        if(next != null) next!!.print()
    }

    fun ct(): Int {
        if(next == null) return 1
        return 1 + next!!.ct()
    }

    fun split(arr: Array<LLN?>, wh: Int) {
        val temp: LLN?
        if(wh < arr.size && arr[wh] == null) {
            arr[wh] = this
            temp = getNext()
            setNext(null)
            temp?.split(arr, wh + 1)
        }
        else {
            var newWh = wh
            if(newWh == arr.size) newWh = 0
            temp = next
            setNext(arr[newWh])
            arr[newWh] = this
            temp?.split(arr, newWh + 1)
        }

        /*
         split takes the linked headed by "this" and splits it into an array arr
         (whose length is len).  wh indicates the position at which the first
         node in the list should go.
        */
    }

    fun mildSort(): LLN {

        val temp2: LLN?
        if(next == null) return this
        val temp1: LLN = next!!.mildSort()
        this.setNext(temp1)
        return if(data < next!!.data) { // interesting if() here, return if() will return the last line in the body
            temp2 = next!!.getNext()
            temp1.setNext(this)
            setNext(temp2)
            temp1
        } else this

        /*
         mildsort sorts the "this" list and returns the new head.
         mildsort assumes that no node is more than one position out of place
         and uses this assumption to run very fast.
        */
    }
}