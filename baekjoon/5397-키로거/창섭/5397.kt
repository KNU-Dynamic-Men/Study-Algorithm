import java.lang.StringBuilder
import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val T = readLine().toInt()
    for(i in 0 until T){
        val tc:String = readLine()
        var node = LinkedList<Char>()
        var iter = node.listIterator()
        for (j in 0..tc.length-1){
            var index = tc.get(j)
            if(index == '<'){
                if(iter.hasPrevious()){
                    iter.previous()
                }
            }
            else if(index=='>') {
                if(iter.hasNext()){
                    iter.next()
                }
            }
            else if(index=='-'){
                if(iter.hasPrevious()){
                    iter.previous()
                    iter.remove()
                }
            }
            else{
                iter.add(index)
            }
        }
        var sb = StringBuilder()
        for(n in node){
            sb.append(n)
        }
        println(sb)
    }
}