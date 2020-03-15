import java.util.*;
import java.io.*;

public class convention {

    static int N,M,C;
    static int[] arrivalTimes;

    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new FileReader("convention.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("convention.out"));
        N = in.nextInt();
        M = in.nextInt();
        C = in.nextInt();
        arrivalTimes = new int[N];
        for (int i = 0; i < N; i++)
            arrivalTimes[i] = in.nextInt();
        Arrays.sort(arrivalTimes);
        int low = 0, high = arrivalTimes[N-1] - arrivalTimes[0];
        while (low < high)
        {
            int mid = (low+high)/2;
            if (validTime(mid))
                high = mid;
            else
                low = mid + 1;
        }
        pw.println(low);
        pw.close();
    }

    public static boolean validTime(int time)
    {
        ArrayList<Integer> bus = new ArrayList<>();
        bus.add(arrivalTimes[0]);
        int busCount = 1;
        for (int i = 1; i < N; i++)
        {
            int cow = arrivalTimes[i];
            if (bus.size() == C)
            {
                busCount++;
                bus.clear();
                bus.add(cow);
            }
            else if (cow - bus.get(0) <= time)
                bus.add(cow);
            else if (cow - bus.get(0) > time)
            {
                busCount++;
                bus.clear();
                bus.add(cow);
            }
        }
        return busCount <= M;
    }
}
