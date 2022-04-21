import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class MaxHeapDriver {
    public static <T> void main(String[] args) throws IOException {
        //creating the output file
        PrintStream fileOut = new PrintStream("output.txt");
        System.setOut(fileOut);



        //reading the sorted file and putting it into an array
        
        int[] sorted = readFile("data_sorted.txt");

        //SEQUENTIAL - SORTED
        //creating the heap for the sorted integers
        MaxHeap<Integer> sortedEntriesSequential = new MaxHeap<Integer>(sorted.length);
        //finding the number of swaps
        int swap = 0;
        for(int i = 0; i < sorted.length; i++) {
            swap += sortedEntriesSequential.add(sorted[i]);
        }

        //printing out the first 10 integers after sorting
        System.out.print("Heap built using sequential insertions (sorted): ");
        printTenIntegers(sortedEntriesSequential);

        //printing out the number of swaps
        System.out.print("Number of swaps in the heap creation: " + swap +"\n");
        System.out.print("Heap after 10 removals: ");
        printTenIntegers(sortedEntriesSequential);
        System.out.println();







        //SEQUENTIAL - RANDOM

        int[] random = readFile("data_random.txt");

        //creating the heap for the random integers - sequential
        MaxHeap<Integer> randomEntriesSequential = new MaxHeap<Integer>(random.length);
        //finding out how much was swapped
        swap = 0;
        for(int i = 0; i < random.length; i++) {
            swap += randomEntriesSequential.add(random[i]);
        }

        //printing out the first 10 integers after sorting
        System.out.print("Heap built using sequential insertions (random): ");
        printTenIntegers(randomEntriesSequential);

        //printing out the number of swaps
        System.out.println("Number of swaps in the heap creation: " + swap);
        printTenIntegers(randomEntriesSequential);
        System.out.println();






        //OPTIMAL - SORTED
        ArrayList<Integer> sortedOptimal = readFileIntoList("data_sorted.txt");

        MaxHeap<Integer> sortedEntriesOptimal = new MaxHeap<Integer>(sortedOptimal);
        //printing out the first 10 integers
        System.out.print("Heap built using optimal method (sorted): ");
        printTenIntegers(sortedEntriesOptimal);

        //printing out number of swaps
        System.out.println("Number of swaps in the heap creation: " + sortedEntriesOptimal.getSwaps());

        //printing out after 10 removals
        System.out.print("Heap after 10 removals: ");
        printTenIntegers(sortedEntriesOptimal);

        System.out.println();







        //OPTIMAL - RANDOM
        ArrayList<Integer> randomOptimal = readFileIntoList("data_random.txt");

        MaxHeap<Integer> randomEntriesOptimal = new MaxHeap<Integer>(randomOptimal);
        //printing out the first 10 integers
        System.out.print("Heap built using optimal method (random): ");
        printTenIntegers(randomEntriesOptimal);

        //printing out number of swaps
        System.out.println("Number of swaps in the heap creation: " + randomEntriesOptimal.getSwaps());

        //printing out after 10 removals
        System.out.print("Heap after 10 removals: ");
        printTenIntegers(randomEntriesOptimal);

        System.out.println();


        
    }

    public static void printTenIntegers(MaxHeap<Integer> entries) {
        for(int i = 0; i < 10; i++){
            System.out.print(entries.removeMax() + ",");
        }
        System.out.println("...");
    }


    public static int[] readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        int[] entries = new int[100];
        String line = br.readLine();
        int i = 0;
        while(line != null) {
            entries[i] = Integer.parseInt(line);
            line = br.readLine();
            i++;
        }
        br.close();
        return entries;
    }

    public static ArrayList<Integer> readFileIntoList(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data_sorted.txt"));
        ArrayList<Integer> entries = new ArrayList<Integer>();
        String line = br.readLine();
        while(line != null) {
            entries.add(Integer.parseInt(line));
            line = br.readLine();
        }
        br.close();
        return entries;
    }
}
