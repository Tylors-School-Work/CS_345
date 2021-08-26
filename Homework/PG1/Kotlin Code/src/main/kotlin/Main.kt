

class LLN(private var data: String,private var next: LLN? = null) {

    fun getData(): String { return data }

    fun getNext(): LLN? { return next }

    fun setNext(n: LLN) { next = n }

    fun print() {
        println(data)
        if(next != null) next?.print()
    }

    fun ct(): Int {
        if(next == null) return 1
        return 1 + next?.ct()!!
    }

    fun split(arr: Array<LLN>, wh: Int) { // not including length, may not need in Kotlin
        // Not sure if Array<LLN> is correct or not

        /*
         split takes the linked headed by "this" and splits it into an array arr
         (whose length is len).  wh indicates the position at which the first
         node in the list should go.
        */
    }

    fun mildSort(): LLN {
        /*
         mildsort sorts the "this" list and returns the new head.
         mildsort assumes that no node is more than one position out of place
         and uses this assumption to run very fast.
        */
        return LLN("", null) // placeholder, to ward off errors
    }
}
class LL(private var head: LLN?) {

    fun print() {
        println("*****")
        if(head != null) head?.print()
        println("*****")
    }

    fun ct(): Int {
        if(head == null) return 0
        return head?.ct()!!
    }

    fun add(d: String) { head = LLN(d, head) }

    // this function has some crude ways of getting around for() loops in the c++ code
    // I want to come back to this section and see if I can touch it up
    // and find better ways to handle everything
    // look for the while() loops that use variables and decrement them
    fun shellSort() {
        var c = ct()
        if(c < 2) return
        var diff = c - 1
        while(diff >= 1) {
            var tdiff = diff
            while(tdiff % 2 == 0) tdiff /= 2
            while(tdiff % 3 == 0) tdiff /= 3
            if(tdiff > 1) continue
            var arr = emptyArray<LLN>() // need an array of Nodes here
            /* I don't think I actually need to initialize the array from above, in c++ you do tho
            var i = 0
            while(i < diff) {
                arr[i] = null
            }
            */
            head?.split(arr, 0) // not including length, may not need in Kotlin
            for(i in 0..diff) arr[i] = arr[i].mildSort()
            head = rejoin(arr, (c - 1 + diff) % diff, null) // not including length, may not need in Kotlin
            diff-- // this may need to be placed in more than one spot depending on when the loop restarts
        }
    }
}

fun main() {
    var list = LL(null) // may want to change the constructor, may not need one
    while(true) {
        var input = readLine()
        if(input == "") break
        list.add(input!!)
    }
    list.shellSort()
    list.print()
}

fun rejoin(arr: Array<LLN>, wh: Int, l: LLN?): LLN { // not including length, may not need in Kotlin
    /*
	 rejoin takes the lists in arr (whose length is len) and appends them to
	 the beginning of list l.
	 wh indicates the position within arr the element that needs to be the
	 first one attached to l.
	*/
    return LLN("", null) // placeholder, to ward off errors
}