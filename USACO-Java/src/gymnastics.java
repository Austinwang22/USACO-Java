import java.util.*;
import java.io.*;

public class gymnastics {

    public static void main(String[] args) throws IOException
    {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gymnastics.out")));
        Scanner sc = new Scanner(new BufferedReader(new FileReader("gymnastics.in")));
        int sessions = sc.nextInt();
        int cows = sc.nextInt();
        ArrayList<int[]> allPairs = new ArrayList<int[]>();
        int[][] grid = new int[sessions][cows];
        for (int i = 1; i <= cows; i++)
        {
            for (int j = 1; j <= cows; j++)
            {
                if (i != j)
                {
                    int[] p = {i,j};
                    allPairs.add(p);
                }
            }
        }
        for (int row = 0; row < sessions; row++)
            for (int col = 0; col < cows; col++)
                grid[row][col] = sc.nextInt();
        int len = allPairs.size();
        int numPairs = 0;
        for (int i = 0; i < len; i++)
        {
            int[] pair = allPairs.get(i);
            int first = pair[0];
            int second = pair[1];
            if (validate(first,second,grid))
                numPairs++;
        }
        pw.print(numPairs);
        pw.close();
        System.exit(0);
    }

    public static int indexOf(int num, int[] arr)
    {
        int len = arr.length;
        int index = -1;
        for (int i = 0; i < len; i++)
            if (arr[i] == num)
                index = i;
        return index;
    }

    public static boolean validate(int cow1,int cow2, int[][] arr)
    {
        for (int[] array : arr)
        {
            int index1 = indexOf(cow1,array);
            int index2 = indexOf(cow2,array);
            if (index2 < index1)
                return false;
        }
        return true;
    }
}
