import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList<Character> list = new LinkedList<Character>();

        String input = br.readLine();
        int count = Integer.parseInt(br.readLine());

        for (int i = 0; i < input.length(); i++) {
            list.add(input.charAt(i));
        }

        ListIterator<Character> it = list.listIterator(input.length());

        for (int i = 0; i < count; i++) {
            String comm = br.readLine();

            if(comm.startsWith("L")) {
                if(it.hasPrevious()) {
                    it.previous();
                }
            } else if (comm.startsWith("D")) {
                if(it.hasNext()) {
                    it.next();
                }
            } else if (comm.startsWith("B")) {
                if(it.hasPrevious()) {
                    it.previous();
                    it.remove();
                }
            } else if (comm.startsWith("P")) {
                it.add(comm.charAt(2));
            }
        }

        StringBuilder sb = new StringBuilder();
        for(char t : list) {
            sb.append(t);
        }
        System.out.print(sb.toString());

    }
}