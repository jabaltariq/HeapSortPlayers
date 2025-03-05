import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

    static final String FILE_NAME = "players.txt";

    /**
     * the purpose of the heapSort method is to put an array into a heap. By
     * enforcing heap property integrity, it will become ordered when read and
     * inserted back into the array
     * @param pa
     */
    public static void heapSort(Player[] pa) {
        PQHeap heap = new PQHeap();
        System.out.println("heapsort started");
        for (int i = 0; i < pa.length; i++) {
            heap.add(pa[i]);
        }

        System.out.println("part 2");
        for (int i = 0; i < pa.length; i++) {
            pa[i] = heap.getHighestScorePlayer();
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        FileReader fr;
        String l1;
        int l2;
        PQHeap heap = new PQHeap();
        Player[] playerArray = new Player[1000];
        Player addPlayer = new Player();
        int count = 0;
        try {
            fr = new FileReader(FILE_NAME);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        }

        Scanner fstream = new Scanner(fr);

        /**
         * takes in data from players.txt file, creates a player, then writes it to the playerArray
         */
        while (fstream.hasNextLine()) {
            l1 = fstream.nextLine();
            l2 = Integer.parseInt(fstream.nextLine());
            addPlayer = new Player(l1, l2);
            playerArray[count] = addPlayer;
            count++;
        }

        heapSort(playerArray);

        for (int i = 0; i < playerArray.length; i++) {
            System.out.println((i + 1) + ": " + playerArray[i].getName() + ", " + playerArray[i].getScore());
        }



        // code to ensure copy and clone constructors are working.
        PQHeap baseHeap = new PQHeap();
        for (int i = 0; i < 10; i++) {
            baseHeap.add(new Player("Test", i));
        }

        PQHeap copyConstructor = new PQHeap(baseHeap);

        PQHeap clonedConstructor = baseHeap.createClone();

        System.out.println("Base heap");
        baseHeap.printFirstTen();
        System.out.println("Heap produced with copy constructor");
        copyConstructor.printFirstTen();
        System.out.println("Heap produced with clone constructor");
        clonedConstructor.printFirstTen();

    }
}