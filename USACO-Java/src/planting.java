import java.util.*;
import java.io.*;

public class planting {

    static int N;
    static ArrayList<Integer>[] adj;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("planting.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("planting.out"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        adj = new ArrayList[N+1];
        for (int i = 1; i <= N; i++)
            adj[i] = new ArrayList<>();
        for (int i = 0; i < N - 1; i++)
        {
            st = new StringTokenizer(in.readLine());
            int field1 = Integer.parseInt(st.nextToken());
            int field2 = Integer.parseInt(st.nextToken());
            adj[field1].add(field2);
            adj[field2].add(field1);
        }
        int max = 0;
        for (int i = 1; i <= N; i++)
        {
            int degree = adj[i].size();
            max = Math.max(max,degree);
        }
        pw.println(max+1);
        pw.close();
    }
}
