import java.util.*;
import java.io.*;

public class hps {

    static int N;
    static int[] games;
    static int[][] prefixCount;
    static final int HOOF = 0, PAPER = 1, SCISSORS = 2;

    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new FileReader("hps.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("hps.out"));
        N = in.nextInt();
        games = new int[N+1];
        for (int i = 1; i <= N; i++)
        {
            char play = in.next().charAt(0);
            if (play == 'H')
                games[i] = HOOF;
            else if (play == 'P')
                games[i] = PAPER;
            else if (play == 'S')
                games[i] = SCISSORS;
        }
        prefixCount = new int[N+1][3];
        calculate(HOOF);
        calculate(PAPER);
        calculate(SCISSORS);
        int maxDubs = 0;
        for (int split = 1; split < N; split++)
        {
            for (int i = 0; i <= 2; i++)
            {
                for (int j = 0; j <= 2; j++)
                {
                    int first = prefixCount[split][i];
                    int second = prefixCount[N][j] - prefixCount[split][j];
                    maxDubs = Math.max(maxDubs,first+second);
                }
            }
        }
        pw.println(maxDubs);
        pw.close();
    }

    public static void calculate(int bessie)
    {
        for (int game = 1; game <= N; game++)
        {
            if (bessie == HOOF && games[game] == SCISSORS ||
                    bessie == PAPER && games[game] == HOOF ||
                    bessie == SCISSORS && games[game] == PAPER)
                prefixCount[game][bessie] = prefixCount[game-1][bessie] + 1;
            else
                prefixCount[game][bessie] = prefixCount[game-1][bessie];
        }
    }
}
