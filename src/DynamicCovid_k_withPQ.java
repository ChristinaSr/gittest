import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DynamicCovid_k_withPQ {
    public static void main(String[] args) {
        try {
            Scanner input;
            // ask from user to type the k
            System.out.println("Type a positive integer number (k) to see the (k) cities with the largest number of covid cases per 50.000 citizens.");
            Scanner sc = new Scanner(System.in);
            int k = sc.nextInt();

            input = new Scanner(new File("C:\\Users\\user\\Desktop\\p3190194\\covid.txt"));

            System.out.println("The " + k + " cities with the most coronavirus cases per 50.000 citizens are:");
            PQ city_priority_queue = new PQ(2 * k); // initializing the queue with max capacity 2k

            int count_k = 0;
            while (input.hasNext()) {
                City city = new City();
                city.setID(input.nextInt());    // read id
                city.setName(input.next());     // read name
                city.setPopulation(input.nextInt());    // read population
                city.setCovidCases(input.nextInt());    // read covid cases
                city_priority_queue.insert(city);     // add object city in the priority queue

                if (count_k == 2) {
                    City max = city_priority_queue.getMax();
                    System.out.println(max.getName()+" "+city_priority_queue.size());
                    city_priority_queue.remove(city_priority_queue.getMax().getID());
                    System.out.println(city_priority_queue.size());
                    count_k = 0;
                }
                count_k++;
            }

            for (int i=0; i<k; i++) {
                System.out.println(city_priority_queue.getMax().getName());
            }
            input.close();
        }
        catch (FileNotFoundException e) { System.out.println("File not found"); }
    }
}
