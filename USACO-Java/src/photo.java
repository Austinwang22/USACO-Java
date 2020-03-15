import java.util.*;
import java.io.*;

public class photo {

    static int N,K;
    static int[][] enemies;
    static TreeSet<Integer> photo;

    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new FileReader("photo.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("photo.out"));
        N = in.nextInt();
        K = in.nextInt();
        enemies = new int[K][2];
        photo = new TreeSet<>();
        for (int i = 0; i < K; i++)
        {
            int cow1 = in.nextInt();
            int cow2 = in.nextInt();
            int[] pair = {cow1,cow2};
            Arrays.sort(pair);
            enemies[i] = pair;
        }
        java.util.Arrays.sort(enemies, new java.util.Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return Double.compare(a[1], b[1]);
            }
        });
        photo.add(1);
        for (int[] pair : enemies)
        {
            int low = pair[0];
            int high = pair[1];
            if (photo.floor(low) == photo.floor(high))
                photo.add(high);
        }
        pw.println(photo.size());
        pw.close();
    }
}
