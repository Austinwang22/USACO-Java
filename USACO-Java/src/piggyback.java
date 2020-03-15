import java.util.*;
import java.io.*;

public class piggyback {
    static int B,E,P,N,M;
    static ArrayList<Integer>[] adjacencyList;
    static int[] distance1, distance2, distanceMP;
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new FileReader("piggyback.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("piggyback.out"));
        B = in.nextInt();
        E = in.nextInt();
        P = in.nextInt();
        N = in.nextInt();
        M = in.nextInt();
        adjacencyList = new ArrayList[N+1];
        distance1 = new int[N+1];
        distance2 = new int[N+1];
        distanceMP = new int[N+1];
        for (int i = 1; i <= N; i++)
            adjacencyList[i] = new ArrayList<>();
        for (int i = 0; i < M; i++)
        {
            int field1 = in.nextInt();
            int field2 = in.nextInt();
            adjacencyList[field1].add(field2);
            adjacencyList[field2].add(field1);
        }
        bfs(1,distance1);
        bfs(2,distance2);
        bfs(N,distanceMP);
        int min = Integer.MAX_VALUE;
        for (int mp = 1; mp <= N; mp++)
            min = Math.min(min,B*distance1[mp] + E*distance2[mp] + P*distanceMP[mp]);
        pw.println(min);
        pw.close();
    }

    public static void bfs(int from, int[] distance)
    {
        LinkedList<Integer> q = new LinkedList<>();
        Arrays.fill(distance, -1);
        distance[from] = 0;
        q.add(from);
        while (!q.isEmpty())
        {
            int currField = q.removeFirst();
            ArrayList<Integer> connectedFields = adjacencyList[currField];
            for (int field : connectedFields) {
                if (distance[field] == -1)
                {
                    q.add(field);
                    distance[field] = distance[currField] + 1;
                }
            }
        }
    }
}
