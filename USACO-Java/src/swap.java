import java.util.*;
import java.io.*;

public class swap {
    static TreeMap<Integer, int[]> precomputedList = new TreeMap<>();
    static int numCows,numPairs,numMoves;
    static int[] firstOrder;
    static int[] order;
    static int[][] allPairs;
    static void firstMove() {
        for (int x = 0; x < numPairs; x++) {
            int high = allPairs[x][1];
            int low = allPairs[x][0];
            rev(low,high);
        }
        int[] added = getArray(order);
        precomputedList.put(1, added);
    }
    static void allOtherMoves(int t, int num)
    {
        int[] l = new int[numCows+1];
        int[] p = precomputedList.get(num);
        for (int i = 1; i <= numCows; i++) {
            l[i] = order[p[i]];
        }
        order = l;
        int[] added = getArray(order);
        precomputedList.put(t+num, added);
    }
    static void rev(int low, int high) {
        for (int l = low, h = high; l < h; l++, h--) {
            int temp = order[l];
            order[l] = order[h];
            order[h] = temp;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("swap.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        numCows = Integer.parseInt(st.nextToken());
        numPairs = Integer.parseInt(st.nextToken());
        numMoves = Integer.parseInt(st.nextToken());
        allPairs = new int[numPairs][2];
        firstOrder = new int[numCows+1];
        order = new int[numCows+1];
        for (int x = 1; x <= numPairs; x++) {
            StringTokenizer newLines = new StringTokenizer(br.readLine());
            int lower = Integer.parseInt(newLines.nextToken());
            int higher = Integer.parseInt(newLines.nextToken());
            int[] pair = {lower,higher};
            allPairs[x-1] = pair;
        }
        for (int x = 1; x <= numCows; x++) {
            order[x] = x;
        }
        firstOrder = getArray(order);
        firstMove();
        int currMove = 1;
        while (currMove < numMoves) {
            int orderIndex = precomputedList.floorKey(numMoves-currMove);
            allOtherMoves(currMove, orderIndex);
            currMove += orderIndex;
            System.out.println(orderIndex);
        }
        PrintWriter writer = new PrintWriter(new FileWriter("swap.out"));
        for (int x = 1; x <= numCows; x++) {
            writer.println(order[x]);
        }
        writer.close();
    }
    static int[] getArray(int[] array) {
        int len = array.length;
        int[] copiedArray = new int[len];
        for (int i = 0; i < len; i++)
            copiedArray[i] = array[i];
        return copiedArray;
    }
}
