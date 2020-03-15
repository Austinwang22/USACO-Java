import java.util.*;
import java.io.*;

public class msched {

    static int N;
    static Cow[] cows;

    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new FileReader("msched.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("msched.out"));
        N = in.nextInt();
        cows = new Cow[N];
        for (int i = 0; i < N; i++)
        {
            int g = in.nextInt();
            int d = in.nextInt();
            cows[i] = new Cow(g,d);
        }
        Arrays.sort(cows, new ComparatorDeadline());
        int time = cows[N-1].deadline;
        int gallons = 0;
        int cur = N - 1;
        PriorityQueue<Cow> pq = new PriorityQueue<>();
        while (time >= 1)
        {
            while (cur >= 0 && cows[cur].deadline >= time)
            {
                pq.add(cows[cur]);
                cur--;
            }
            if (!pq.isEmpty())
                gallons += pq.remove().gallons;
            time--;
        }
        pw.println(gallons);
        pw.close();
    }
}

class Cow implements Comparable{

    public int gallons;
    public int deadline;

    public Cow(int g, int d)
    {
        gallons = g;
        deadline = d;
    }

    @Override
    public int compareTo(Object o) {
        return ((Cow)o).gallons - this.gallons;
    }
}

class ComparatorDeadline implements Comparator<Cow> {
    public int compare(Cow c1, Cow c2)
    {
        return c1.deadline - c2.deadline;
    }
}
