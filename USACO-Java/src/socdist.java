import java.util.*;
import java.io.*;

public class socdist {

    static int N,M;
    static int[][] intervals;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("socdist.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("socdist.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        intervals = new int[M][2];
        for (int i = 0; i < M; i++)
        {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int[] pair = {start,end};
            intervals[i] = pair;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int low = 1, high = intervals[M-1][1];
        int answer = -1;
        while (low <= high)
        {
            int mid = (low+high)/2;
            if (isValid(mid))
            {
                answer = mid;
                low = mid + 1;
            }
            else
                high = mid - 1;
        }
        pw.println(answer);
        pw.close();
    }

    public static boolean isValid(int distance)
    {
        int count = 1;
        int lastCow = intervals[0][0];
        for (int[] pair : intervals)
        {
            while (Math.max(lastCow+distance,pair[0]) <= pair[1])
            {
                lastCow = Math.max(lastCow+distance,pair[0]);
                count++;
                if (count >= N)
                    return true;
            }
            if (count >= N)
                break;
        }
        return count >= N;
    }
}
