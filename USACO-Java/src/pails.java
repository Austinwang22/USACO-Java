import java.io.*;
import java.util.*;

public class pails {

    static int X,Y,K,M,minDiff,pailOne,pailTwo;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new FileReader("pails.in"));
        PrintWriter out = new PrintWriter(new FileWriter("pails.out"));
        X = in.nextInt();
        Y = in.nextInt();
        K = in.nextInt();
        M = in.nextInt();
        minDiff = Integer.MAX_VALUE;
        visited = new boolean[K+1][X+1][Y+1];
        dfs(0);
        out.println(minDiff);
        out.close();
    }

    public static void dfs(int step)
    {
        if (step > K)
            return;
        if (visited[step][pailOne][pailTwo])
            return;
        minDiff = Math.min(minDiff,Math.abs(M-pailOne-pailTwo));
        visited[step][pailOne][pailTwo] = true;
        int currOne = pailOne;
        int currTwo = pailTwo;
        for (int i = 1; i <= 6; i++)
        {
            pailOne = currOne;
            pailTwo = currTwo;
            operation(i);
            dfs(step+1);
        }
    }

    public static void operation(int op)
    {
        if (op == 1)
            pailOne = X;
        else if (op == 2)
            pailTwo = Y;
        else if (op == 3)
            pailOne = 0;
        else if (op == 4)
            pailTwo = 0;
        else if (op == 5) //pour one into two
        {
            int oneToTwo = Math.min(pailOne,Y-pailTwo);
            pailOne -= oneToTwo;
            pailTwo += oneToTwo;
        }
        else if (op == 6)
        {
            int twoToOne = Math.min(pailTwo,X-pailOne);
            pailOne += twoToOne;
            pailTwo -= twoToOne;
        }
    }
}
