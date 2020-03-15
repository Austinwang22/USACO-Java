import java.util.*;
import java.io.*;

public class fenceplan {
    static int maxX,maxY,minX,minY;
    static int[] x;
    static int[] y;
    static ArrayList<Integer>[] friends;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new FileReader("fenceplan.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("fenceplan.out"));
        int N = in.nextInt();
        int M = in.nextInt();
        visited = new boolean[N+1];
        x = new int[N+1];
        y = new int[N+1];
        friends = new ArrayList[N+1];
        for (int i = 1; i <= N; i++)
        {
            friends[i] = new ArrayList<Integer>();
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }
        for (int i = 1; i <= M; i++)
        {
            int cow1 = in.nextInt();
            int cow2 = in.nextInt();
            friends[cow1].add(cow2);
            friends[cow2].add(cow1);
        }
        //floodfill
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++)
        {
            if (!visited[i])
            {
                minX = maxX = x[i];
                minY = maxY = y[i];
                floodfill(i);
                int perimeter = 2 * (maxX - minX + maxY - minY);
                answer = Math.min(answer,perimeter);
            }
        }
        System.out.println(answer);
        pw.println(answer);
        pw.close();
    }

    public static void floodfill(int c)
    {
        visited[c] = true;
        for (int cow : friends[c])
        {
            if (!visited[cow])
            {
                maxX = Math.max(maxX,x[cow]);
                maxY = Math.max(maxY,y[cow]);
                minX = Math.min(minX,x[cow]);
                minY = Math.min(minY,y[cow]);
                floodfill(cow);
            }
        }
    }
}
