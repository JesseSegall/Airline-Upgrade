import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UI {
    Map<String, Passenger> ssnPassenger = new HashMap<>(); //Store ssn and reference to passenger
    PriorityQueue<Passenger> upgradeHeap = new PriorityQueue<Passenger>(new PassengerComparator()); //creates the heap that will be used to determine the priority of passengers

    public void printMenu() {

        System.out.println("\nMenu:\n");
        System.out.println("1. Request Upgrade");
        System.out.println("2. Request Cancellation");
        System.out.println("3. Show Upgrade List");
        System.out.println("4. Quit");

    }

    public static void main(String[] args) {
        UI ui = new UI();
        Scanner stdIn = new Scanner(System.in);
        boolean running = true;
        while (running) {
            ui.printMenu();
            String userOption = stdIn.nextLine();
            String confNum = "";
            switch (userOption) {
                case "1":
                    System.out.println("Enter your name:");
                    String name = stdIn.nextLine();
                    System.out.println("Enter your flier status: ");
                    String priority = stdIn.nextLine();

                    if (!Passenger.validatePriority(priority)) { // checks to see if user has entered a valid priority
                        System.out.println("Invalid Priority Please Choose Silver, Gold, Platinum, or Super");
                        break;
                    }


                    System.out.println("Enter your 9 digit Social Security Number including hyphens xxx-xx-xxxx");
                    String ssn = stdIn.nextLine();
                    if (!isValidSSN(ssn)) {
                        System.out.println("Invalid SSN");
                        break;
                    }
                    confNum = ui.requestUpgrade(ssn, name, priority);// putting name and ssn into respective hashmaps
                    System.out.println("Thank you! Your confirmation number is: " + confNum);


                    break;
                case "2":
                    System.out.println("Enter your 9 digit Social Security Number including hyphens xxx-xx-xxxx");
                    ssn = stdIn.nextLine();
                    if (!isValidSSN(ssn)) {
                        System.out.println("Invalid SSN");
                        break;
                    }
                    System.out.println("Enter your confirmation number: ");
                    confNum = stdIn.nextLine();
                    if (ui.requestCancellation(ssn, confNum)) {
                        System.out.println("Cancellation Successful");
                    }
                    break;
                case "3":
                    System.out.println("Enter total number of upgrades available"); // enter number to determine how big the list of k-highest priority fliers is
                    int numUpgradesAvailable = Integer.parseInt(stdIn.nextLine());
                    Iterator iterator = ui.upgradeHeap.iterator();
                    for (int i = 0; i < Math.min(numUpgradesAvailable,ui.upgradeHeap.size()); i++) { //iterates through x number of times where x is the number of upgrades available, min will return the difference so it wont return null if the heap is small
                        System.out.println(ui.upgradeHeap.poll()); //removes the head and returns it which will be the highest priority
                    }
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Menu Option - please input a number between 1 and 4");
                    break;
            }

        }
    }


    private String requestUpgrade(String ssn, String name, String priority) {
        Random random = new Random();
        String confNum = String.valueOf(10000 + random.nextInt(90000));
        Passenger passenger = new Passenger(ssn, name, priority, confNum);
        upgradeHeap.add(passenger); // adds passenger to heap
        ssnPassenger.put(ssn, passenger); // maps ssn with passenger reference into a hash map to make deletion easier
        return confNum; // returns only confNum so that the user can save their confirmation number somewhere in case they need to cancel
    }

    private boolean requestCancellation(String ssn, String confNum) {
        Passenger passenger = ssnPassenger.get(ssn);
        if (!passenger.getConfNum().equalsIgnoreCase(confNum)) {
            System.out.println("Incorrect confirmation number entered.");
            return false;
        }
        upgradeHeap.remove(passenger);
        ssnPassenger.remove(ssn);
        return true;
    }

    // Source: https://www.geeksforgeeks.org/how-to-validate-ssn-social-security-number-using-regular-expression/
    public static boolean isValidSSN(String str) {
        // Regex to check SSN
        // (Social Security Number).
        String regex = "^(?!666|000|9\\d{2})\\d{3}"
                + "-(?!00)\\d{2}-"
                + "(?!0{4})\\d{4}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the string is empty
        // return false
        if (str == null) {
            return false;
        }

        // Pattern class contains matcher()
        //  method to find matching between
        //  given string and regular expression.
        Matcher m = p.matcher(str);

        // Return if the string
        // matched the ReGex
        return m.matches();
    }
}

