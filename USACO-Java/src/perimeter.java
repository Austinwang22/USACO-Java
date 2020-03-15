import java.util.*;
import java.io.*;

public class perimeter {

    static int N, area;
    static char[][] grid;
    static boolean[][] visited,region;

    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new FileReader("perimeter.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("perimeter.out"));
        N = in.nextInt();
        grid = new char[N+1][N+1];
        visited = new boolean[N+1][N+1];
        region = new boolean[N+1][N+1];
        for (int i = 1; i <= N; i++)
        {
            char[] row = new char[N+1];
            String line = in.next();
            for (int j = 1; j <= N; j++)
                row[j] = line.charAt(j-1);
            grid[i] = row;
        }
        int maxArea = 0;
        int maxPerimeter = 0;
        for (int r = 1; r <= N; r++)
        {
            for (int c = 1; c <= N; c++)
            {
                if (!visited[r][c] && grid[r][c] == '#')
                {
                    area = 0;
                    region = new boolean[N+1][N+1];
                    floodfill(r,c);
                    if (area > maxArea)
                    {
                        maxArea = area;
                        maxPerimeter = totalPerimeter();
                    }
                    else if (area == maxArea)
                        maxPerimeter = Math.max(maxPerimeter,totalPerimeter());
                }
            }
        }
        pw.println(maxArea + " " + maxPerimeter);
        pw.close();
    }

    public static void floodfill(int r, int c)
    {
        if (visited[r][c])
            return;
        visited[r][c] = true;
        region[r][c] = true;
        area++;
        if (c-1 >= 0 && !visited[r][c-1] && grid[r][c-1] == '#') {
            floodfill(r, c - 1);
        }
        if (c+1 <= N && !visited[r][c+1] && grid[r][c+1] == '#') {
            floodfill(r, c + 1);
        }
        if (r-1 >= 0 && !visited[r-1][c] && grid[r-1][c] == '#') {
            floodfill(r - 1, c);
        }
        if (r+1 <= N && !visited[r+1][c] && grid[r+1][c] == '#') {
            floodfill(r + 1, c);
        }
    }

    public static int totalPerimeter()
    {
        int answer = 0;
        for (int r = 1; r <= N; r++)
        {
            for (int c = 1; c <= N; c++)
            {
                if (region[r][c])
                    answer += findPerimeter(r,c);
            }
        }
        return answer;
    }

    public static int findPerimeter(int r, int c)
    {
        int answer = 0;
        if (r - 1 == 0)
            answer++;
        else if (grid[r-1][c] == '.')
            answer++;
        if (c - 1 == 0)
            answer++;
        else if (grid[r][c-1] == '.')
            answer++;
        if (r + 1 > N)
            answer++;
        else if (grid[r+1][c] == '.')
            answer++;
        if (c + 1 > N)
            answer++;
        else if (grid[r][c+1] == '.')
            answer++;
        return answer;
    }
}
