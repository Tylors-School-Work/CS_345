

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

    fun split(arr: Array<LLN>, wh: Int) {
        // this may be wrong
        // Not sure if Array<LLN> is correct or not

        /*
         split takes the linked headed by "this" and splits it into an array arr
         (whose length is len).  wh indicates the position at which the first
         node in the list should go.
        */
    }

    fun mildSort() {
        /*
         mildsort sorts the "this" list and returns the new head.
         mildsort assumes that no node is more than one position out of place
         and uses this assumption to run very fast.
        */
    }
}
class LL(var head: LLN?) {

    fun print() {
        println("*****")
        if(head != null) head?.print()
        println("*****")
    }

    fun add(d: String) {
        head = LLN(d, head)
    }

    fun ct(): Int {
        if(head == null) return 0
        return head?.ct()!!
    }
}