import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Covid_k {

    public static void main(String[] args) {
        Scanner input;
        DoublyLinkedList<City> city_list = new DoublyLinkedList<>();
        try {
            input = new Scanner( new File( "C:\\Users\\user\\Desktop\\p3190194\\covid.txt" ) );

            while (input.hasNext()) {
                City city = new City();
                city.setID(input.nextInt());    // read id
                city.setName(input.next());     // read name
                city.setPopulation(input.nextInt());    // read population
                city.setCovidCases(input.nextInt());    // read covid cases
                city_list.addLast(city);     // add object city in the doubly linked list that has been created for project 1.
            }                               //  Now, it has also a method called getItem which is getting as a parameter the index of the element (in our case, object) we want to access.

            input.close();
            // checking if ID, population and CovidCases are larger than zero
            for (int i=0; i<city_list.size(); i++) {
                if (city_list.getItem(i).getID()<=0 || city_list.getItem(i).getPopulation()<=0 || city_list.getItem(i).getCovidCases()<=0) {
                    System.out.println("Invalid data.");
                    System.exit(0);     // exit system
                }
            }

            // ask from user to type the k
            System.out.println("Type a positive integer number (k) to see the (k) cities with the largest number of covid cases per 50.000 citizens.");
            Scanner sc = new Scanner(System.in);
            int k = sc.nextInt();

            // if k is larger than the number of cities, exit system
            if (k > city_list.size()) {
                System.out.println("The number you typed is larger than the number of cities.");
                System.exit(0);
            }

            HeapSort.sort(city_list);    // sorting the list

            // printing the results
            System.out.println("The top " + k + " cities are: ");
            for (int i=0; i<k; i++) {
                System.out.println(city_list.getItem(i).getName());
            }

        }
        catch (FileNotFoundException e) { System.out.println("File not found"); }
    }
}
