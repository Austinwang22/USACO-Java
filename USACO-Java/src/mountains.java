import java.awt.*;
import java.util.*;
import java.io.*;

public class mountains {

    static int N;
    static Mountain[] mountains;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("mountains.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("mountains.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        mountains = new Mountain[N];
        for (int i = 0; i < N; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            mountains[i] = new Mountain(x,y);
        }
        Arrays.sort(mountains);
        int count = N;    
        for (int i = 0; i < N; i++)
        {
            Mountain check = mountains[i];
            for (int j = N-1; j >= 0; j--)
            {
                Mountain compare = mountains[j];
                if (!check.equals(compare) && !check(check,compare))
                {
                    count--;
                    break;
                }
            }
        }
        pw.println(count);
        pw.close();
    }

    public static boolean check(Mountain m1, Mountain m2)
    {
        boolean check1 = m1.x - m1.y >= m2.x - m2.y;
        boolean check2 = m1.x + m1.y <= m2.x + m2.y;
        return !(check1 && check2);
    }
}

class Mountain implements Comparable{

    public int x, y;

    public Mountain(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Object o) {
        return x - ((Mountain)o).x;
    }
}
