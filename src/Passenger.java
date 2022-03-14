public class Passenger {
    String ssn;
    String name;
    String priority;
    String confNum;


    public static final String[] PRIORITY_LIST = {"Silver", "Gold", "Platinum", "Super"};

    public static boolean validatePriority(String priority) {
        for (int i = 0; i < PRIORITY_LIST.length; i++) {
            if (priority.equalsIgnoreCase(PRIORITY_LIST[i])) {
                return true;
            }
        }
        return false;
    }

    public Passenger(String ssn, String name, String priority, String confNum) {
        this.ssn = ssn;
        this.name = name;
        this.priority = priority;
        this.confNum = confNum;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getConfNum() {
        return confNum;
    }

    public void setConfNum(String confNum) {
        this.confNum = confNum;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "ssn='" + ssn + '\'' +
                ", name='" + name + '\'' +
                ", priority='" + priority + '\'' +
                ", confNum='" + confNum + '\'' +
                '}';
    }
}
