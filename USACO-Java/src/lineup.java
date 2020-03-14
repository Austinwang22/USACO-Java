import java.io.*;
import java.util.*;

public class lineup {

    static PrintWriter pw;
    static int N = 8,P = 8,count = 0;
    static int[] lineup;
    static int[][] permutations = new int[40320][8], constraints;
    static boolean[] visited;

    public static void main(String[] args) throws IOException
    {
        lineup = new int[N];
        visited = new boolean[N+1];
        permute(0);
        Scanner in = new Scanner(new FileReader("lineup.in"));
        pw = new PrintWriter(new FileWriter("lineup.out"));
        int k = in.nextInt();
        in.nextLine();
        constraints = new int[k][2];
        for (int i = 0; i < k; i++)
        {
            String line = in.nextLine();
            String cow1 = line.substring(0,line.indexOf(" "));
            String cow2 = line.substring(line.lastIndexOf(" ") + 1);
            //System.out.println(cow1 + " " + convert(cow1) + " " + cow2 + " " + convert(cow2));
            int[] pair = {convert(cow1),convert(cow2)};
            constraints[i] = pair;
        }
//        int[] check = {1,8,2,4,5,6,3,7};
//        System.out.println(check(check));
        for (int[] arr : permutations)
        {
            if (check(arr))
            {
                print(arr);
                pw.close();
                System.exit(0);
            }
        }
    }

    public static void print(int[] arr)
    {
        for (int i : arr)
        {
            if (i == 1)
                pw.println("Beatrice");
            else if (i == 2)
                pw.println("Belinda");
            else if (i==3)
                pw.println("Bella");
            else if (i==4)
                pw.println("Bessie");
            else if (i==5)
                pw.println("Betsy");
            else if (i==6)
                pw.println("Blue");
            else if (i==7)
                pw.println("Buttercup");
            else if (i==8)
                pw.println("Sue");
        }
    }

    public static boolean check(int[] lineup)
    {
        for (int[] pair : constraints)
        {
            //System.out.println(pair[0] + " " + pair[1]);
            int index1 = indexOf(lineup,pair[0]);
            int index2 = indexOf(lineup,pair[1]);
            //System.out.println(index1 + " " + index2);
            if (Math.abs(index1-index2) != 1)
                return false;
        }
        return true;
    }

    public static int convert(String name)
    {
        if (name.equals("Beatrice"))
            return 1;
        else if (name.equals("Belinda"))
            return 2;
        else if (name.equals("Bella"))
            return 3;
        else if (name.equals("Bessie"))
            return 4;
        else if (name.equals("Betsy"))
            return 5;
        else if (name.equals("Blue"))
            return 6;
        else if (name.equals("Buttercup"))
            return 7;
        return 8;
    }

    //start permute from position p
    public static void permute(int p)
    {
        if (p == P)
        {
            permutations[count] = copy(lineup);
            count++;
            return;
        }
        for (int c = 1; c <= N; c++)
        {
            if (!visited[c])
            {
                lineup[p] = c;
                visited[c] = true;
                permute(p+1);
                visited[c] = false;
            }
        }
    }

    public static int indexOf(int[] arr, int val)
    {
        for (int i = 0; i < 8; i++)
        {
            if (arr[i] == val)
                return i;
        }
        return -1;
    }

    public static int[] copy(int[] arr)
    {
        int[] result = new int[8];
        for (int i = 0; i < 8; i++)
            result[i] = arr[i];
        return result;
    }
}
