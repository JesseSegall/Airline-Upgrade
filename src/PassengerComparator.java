import java.util.Comparator;

public class PassengerComparator implements Comparator<Passenger> {

    @Override
    public int compare(Passenger p1, Passenger p2) {
        String priority1 = p1.getPriority();
        String priority2 = p2.getPriority();
        if (priority1.equalsIgnoreCase(priority2)) {
            return 0;
        }
        for (int i = 0; i < Passenger.PRIORITY_LIST.length; i++) {
            String currentPriority = Passenger.PRIORITY_LIST[i];
            if (currentPriority.equalsIgnoreCase(priority1)) {
                return 1;
            }
            if (currentPriority.equalsIgnoreCase(priority2)) {
                return -1;
            }
        }


        throw new IllegalArgumentException("Illegal priority");


    }
}
