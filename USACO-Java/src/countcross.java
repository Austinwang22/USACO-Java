import java.util.*;
import java.io.*;

public class countcross {

    static int N,K,R;
    static HashSet<String>[][] graph;
    static boolean[][] visited,cowLocs;

    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new FileReader("countcross.in"));
        PrintWriter pw = new PrintWriter((new FileWriter("countcross.out")));
        N = in.nextInt();
        K = in.nextInt();
        R = in.nextInt();
        graph = new HashSet[N+1][N+1];
        visited = new boolean[N+1][N+1];
        cowLocs = new boolean[N+1][N+1];
        for (int i = 1; i <= N; i++)
        {
            for (int j = 1; j <= N; j++)
                graph[i][j] = new HashSet<>();
        }
        for (int i = 0; i < R; i++)
        {
            int x1 = in.nextInt();
            int y1 = in.nextInt();
            int x2 = in.nextInt();
            int y2 = in.nextInt();
            graph[x1][y1].add(x2 + " " + y2);
            graph[x2][y2].add(x1 + " " + y1);
        }
        for (int i = 0; i < K; i++)
        {
            int x = in.nextInt();
            int y = in.nextInt();
            cowLocs[x][y] = true;
        }
        int count = 0;
        for (int r = 1; r <= N; r++)
        {
            for (int c = 1; c <= N; c++)
            {
                if (cowLocs[r][c])
                {
                    visited = new boolean[N+1][N+1];
                    floodfill(r,c);
                    count += count();
                }
            }
        }
        pw.println(count/2);
        pw.close();
    }

    public static void floodfill(int r, int c)
    {
        if (visited[r][c])
            return;
        visited[r][c] = true;
        HashSet<String> roads = graph[r][c];
        String up = (r-1) + " " + c;
        String down = (r+1) + " " + c;
        String right = r + " " + (c+1);
        String left = r + " " + (c-1);
        //System.out.println(up + " " + left);
        if (r-1 > 0 && !roads.contains(up)) {
            floodfill(r - 1, c);
        }
        if (r+1 <= N && !roads.contains(down)) {
            //System.out.println(r+1 + " " + c);
            floodfill(r + 1, c);
        }
        if (c-1 > 0 && !roads.contains(left))
            floodfill(r,c-1);
        if (c+1 <= N && !roads.contains(right))
            floodfill(r,c+1);
    }

    public static int count()
    {
        int count = 0;
        for (int r = 1; r <= N; r++)
        {
            for (int c = 1; c <= N; c++)
            {
                if (!visited[r][c] && cowLocs[r][c])
                    count++;
            }
        }
        return count;
    }
}
