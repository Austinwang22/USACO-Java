import java.util.*;
import java.io.*;

public class homework {
    static int N;
    static int[] scores;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new FileReader("homework.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("homework.out"));
        N = in.nextInt();
        scores = new int[N+1];
        for (int i = 1; i <= N; i++)
            scores[i] = in.nextInt();
        ArrayList<Integer> kSet = new ArrayList<>();
        kSet.add(N-2);
        double highestAvg = Math.max(scores[N-1], scores[N]);
        long suffixSum = scores[N-1] + scores[N];
        int suffixMin = Math.min(scores[N-1],scores[N]);
        for (int k = N - 3; k >= 1; k--)
        {
            suffixMin = Math.min(scores[k+1],suffixMin);
            suffixSum += scores[k+1];
            double avg = ((double)suffixSum - suffixMin) / (N-k-1);
            if (avg == highestAvg)
                kSet.add(k);
            else if (avg > highestAvg)
            {
                kSet.clear();
                kSet.add(k);
                highestAvg = avg;
            }
        }
        Object[] answer = kSet.toArray();
        Arrays.sort(answer);
        for (Object k : answer)
            pw.println(k);
        pw.close();
    }
}
