import java.util.*;
import java.io.*;

public class cowdance {
    static int N, T;
    static int[] times;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new FileReader("cowdance.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("cowdance.out"));
        N = in.nextInt();
        T = in.nextInt();
        times = new int[N];
        for (int i = 0; i < N; i++)
            times[i] = in.nextInt();
        int low = 1, high = N;
        while (low < high)
        {
            int mid = (low + high) / 2;
            if (canFinishInTime(mid))
                high = mid;
            else
                low = mid + 1;
        }
        pw.println(low);
        pw.close();
    }

    public static boolean canFinishInTime(int k)
    {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int clock = 0;
        for (int i = 0; i < k; i++)
            pq.add(times[i] + clock);
        int nextCow = k;
        while (!pq.isEmpty())
        {
            clock = pq.remove();
            if (clock > T)
                return false;
            if (nextCow < N) {
                pq.add(times[nextCow] + clock);
                nextCow++;
            }
        }
        return true;
    }
}
