import java.util.*;
import java.io.*;

public class whereami {

    public static int solve(String str)
    {
        int len = str.length();
        for (int i = 1; i < len; i++)
        {
            ArrayList<String> all = new ArrayList<String>();
            for (int j = 0; j < len - i + 1; j++)
            {
                String substring = str.substring(j,j+i);
                all.add(substring);
            }
            System.out.println();
            ArrayList<String> distinct = findDistinct(all);
            int allSize = all.size();
            int disSize = distinct.size();
            if (allSize == disSize)
            {
                return i;
            }
        }
        return -1;
    }

    public static ArrayList<String> findDistinct(ArrayList<String> list)
    {
        ArrayList<String> distinct = new ArrayList<String>();
        int size = list.size();
        for (int i = 0; i < size; i++)
        {
            if (distinct.indexOf(list.get(i)) == -1)
            {
                distinct.add(list.get(i));
            }
        }
        return distinct;
    }

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new BufferedReader(new FileReader("whereami.in")));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("whereami.out")));

        int len = sc.nextInt();
        String input = sc.next();

        pw.println(solve(input));
        pw.close();
        System.exit(0);
    }
}
