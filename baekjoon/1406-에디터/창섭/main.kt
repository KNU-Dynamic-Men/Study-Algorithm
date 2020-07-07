import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val word = br.readLine()
    val T = Integer.parseInt(br.readLine())
    val e = LinkedList<String>()
    for (i in 0..word.length-1){
        e.add(word.get(i).toString())
    }
    var iter = e.listIterator(e.size)
    for(i in 1..T){
        var tc = br.readLine()
        if(tc == "L"){
            if(iter.hasPrevious()){
                iter.previous()
            }
        }
        else if(tc == "D"){
            if(iter.hasNext()){
                iter.next()
            }
        }
        else if(tc == "B"){
            if(iter.hasPrevious()){
                iter.previous()
                iter.remove()
            }
        }
        else{
            iter.add(tc.get(2).toString())
        }
    }
    var sb = StringBuilder()
    for (i in e){
        sb.append(i)
    }
    print(sb)
}