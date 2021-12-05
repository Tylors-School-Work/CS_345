/*

    Tylor J. Hanshaw

    This is the LL.kt file that holds the code for the LinkedList class
    This class file relies on the LLN.kt file to work properly

*/

class LL(var head: LLN?) {

    fun print() {
        println("*****")
        head?.print()
        println("*****")
    }

    fun ct(): Int {
        if(head == null) return 0
        return head?.ct()!!
    }

    // DEBUGGING METHOD
    fun printList(arr: Array<LLN?>) {
        for(i in arr.indices) {
            if(arr[i]?.next != null) {
                var t = arr[i]
                while(t != null) {
                    print("${t.data} -> ")
                    t = t.next
                }
            }
            else println(arr[i]?.data)
        }
    }

    fun add(data: String) {
        head = LLN(data, head)
    }

    // this function has some crude ways of getting around for() loops in the c++ code
    // I want to come back to this section and see if I can touch it up
    // and find better ways to handle everything
    // look for the while() loops that use variables and decrement them
    fun shellSort() {
        val c = ct()
        if(c < 2) return
        var diff = c - 1
        while(diff >= 1) {
            var tdiff = diff
            while(tdiff % 2 == 0) tdiff /= 2
            while(tdiff % 3 == 0) tdiff /= 3
            if(tdiff > 1) { diff--; continue }
            val arr: Array<LLN?> = arrayOfNulls(diff) // this is an array of 'null' of size diff, and will hold Nodes
            head?.split(arr, 0)
            for(i in 0 until diff) arr[i] = arr[i]?.mildSort()
            head = rejoin(arr, (c - 1 + diff) % diff, null)
            diff--
        }
    }
}