import java.util.*;
import java.io.*;

public class bcount {
    static int N, Q;
    static int[] h, g, j;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new FileReader("bcount.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("bcount.out"));
        N = in.nextInt();
        Q = in.nextInt();
        h = new int[N+1];
        g = new int[N+1];
        j = new int[N+1];
        int firstBreed = in.nextInt();
        if (firstBreed == 1)
            h[1] = 1;
        else if (firstBreed == 2)
            g[1] = 1;
        else
            j[1] = 1;
        //System.out.println(h[1] + " " + g[1] + " " + j[1]);
        for (int i = 2; i <= N; i++)
        {
            int breed = in.nextInt();
            if (breed == 1) {
                h[i] = 1 + h[i - 1];
                g[i] = g[i-1];
                j[i] = j[i-1];
            }
            else if (breed == 2) {
                g[i] = 1 + g[i - 1];
                h[i] = h[i-1];
                j[i] = j[i-1];
            }
            else if (breed == 3) {
                j[i] = 1 + j[i - 1];
                g[i] = g[i-1];
                h[i] = h[i-1];
            }
            //ystem.out.println(h[i] + " " + g[i] + " " + j[i]);
        }
        for (int i = 0; i < Q; i++)
        {
            int first = in.nextInt();
            int second = in.nextInt();
            int holstein = h[second] - h[first-1];
            int guernsey = g[second] - g[first-1];
            int jersey = j[second] - j[first-1];
            pw.println(holstein + " " + guernsey + " " + jersey);
        }
        pw.close();
    }
}
