import java.util.*;
import java.io.*;

public class triangles {
    static long answer = 0, C = 1000000007;
    static TreeMap<Integer, ArrayList<Coordinates>> xCoordsAll, yCoordsAll;
    static void sumAreas(ArrayList<Coordinates> pointList, int index) {
        Coordinates v = pointList.get(index);
        int yCoord = v.yCoord;
        int xCoord = v.xCoord;
        long additive = findLen(yCoordsAll.get(yCoord), xCoord);
        long heightAdd = findHeight(xCoordsAll.get(xCoord), yCoord);
        answer += additive*heightAdd;
        while (answer > C)
            answer -= C;
    }
    static void coordinatesChange(Coordinates coords) {
        if (yCoordsAll.containsKey(coords.yCoord))
            yCoordsAll.get(coords.yCoord).add(coords);
        else {
            ArrayList<Coordinates> pointList = new ArrayList<Coordinates>();
            pointList.add(coords);
            yCoordsAll.put(coords.yCoord, pointList);
        }
        if (xCoordsAll.containsKey(coords.xCoord)) {
            ArrayList<Coordinates> listX = xCoordsAll.get(coords.xCoord);
            listX.add(coords);
        }
        else {
            ArrayList<Coordinates> pointList = new ArrayList<Coordinates>();
            pointList.add(coords);
            xCoordsAll.put(coords.xCoord, pointList);
        }
    }
    static int findHeight(ArrayList<Coordinates> pointList, int y) {
        int h = 0;
        for (int i = 0; i < pointList.size(); i++)
            h += Math.abs(pointList.get(i).getY() - y);
        return h;
    }
    static int findLen(ArrayList<Coordinates> pointList, int x) {
        int len = 0;
        for (int i = 0; i < pointList.size(); i++)
            len += Math.abs(pointList.get(i).getX() - x);
        return len;
    }
    public static void main(String[] args) throws IOException {
        xCoordsAll = new TreeMap<Integer, ArrayList<Coordinates>>();
        yCoordsAll = new TreeMap<Integer, ArrayList<Coordinates>>();
        BufferedReader br = new BufferedReader(new FileReader("triangles.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        for(int x = 1; x <= N; x++) {
            st = new StringTokenizer(br.readLine());
            int coordX = Integer.parseInt(st.nextToken());
            int coordY = Integer.parseInt(st.nextToken());
            coordinatesChange(new Coordinates(coordX,coordY));
        }
        Set set = xCoordsAll.entrySet();
        for(Object mE:set) {
            Map.Entry M = (Map.Entry) mE;
            ArrayList<Coordinates> pointList = (ArrayList<Coordinates>) M.getValue();
            if(pointList.size() != 1) {
                for(int i = 0; i < pointList.size(); i++)
                    sumAreas(pointList, i);
            }
        }
        PrintWriter pw = new PrintWriter(new FileWriter("triangles.out"));
        pw.println(answer);
        pw.close();
    }
}
class Coordinates {
    int xCoord,yCoord;
    public boolean equals(Object o) {
        Coordinates other = (Coordinates) o;
        return xCoord == other.xCoord && yCoord == other.yCoord;
    }
    public int getX(){return xCoord;}
    public int getY(){return yCoord;}
    public Coordinates (int x, int y) {
        xCoord = x;
        yCoord = y;
    }
}