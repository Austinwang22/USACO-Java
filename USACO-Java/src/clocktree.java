import java.util.*;
import java.io.*;

public class clocktree {
    static ArrayList<Room> rooms;
    static int numRooms;
    static class Room {
        public ArrayList<Integer> connected;
        public int roomNumber,distance;
        public Room(int r, int d)
        {
            connected = new ArrayList<>();
            roomNumber = r;
            distance = d;
        }
    }
    static int findDistance(Room r, Room p) {
        int num = 0;
        ArrayList<Integer> connected = r.connected;
        for (int room : connected) {
            if (p != null && p.roomNumber == room)
                continue;
            num += findDistance(rooms.get(room), r);
        }
        return (12 - (12 - r.distance + num) % 12) % 12;
    }
    public static void main(String[] args) throws IOException {
        rooms = new ArrayList<>();
        rooms.add(new Room(-1,-1));
        int answer = 0;
        BufferedReader reader = new BufferedReader(new FileReader("clocktree.in"));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        numRooms = Integer.parseInt(tokenizer.nextToken());
        tokenizer = new StringTokenizer(reader.readLine());
        for (int x = 0; x < numRooms; x++) {
            int distance = 12 - Integer.parseInt(tokenizer.nextToken());
            Room newNode = new Room(x+1,distance);
            rooms.add(newNode);
        }
        for (int x = 1; x <= numRooms - 1; x++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int firstRoom = Integer.parseInt(tokenizer.nextToken());
            int secondRoom = Integer.parseInt(tokenizer.nextToken());
            rooms.get(firstRoom).connected.add(secondRoom);
            rooms.get(secondRoom).connected.add(firstRoom);
        }
        for (int x = 1; x <= numRooms; x++) {
            int distance = findDistance(rooms.get(x), null);
            if (distance == 11 || distance == 0)
                answer++;
        }
        PrintWriter writer = new PrintWriter(new FileWriter("clocktree.out"));
        writer.println(answer);
        writer.close();
    }
}
