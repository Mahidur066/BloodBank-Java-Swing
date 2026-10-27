import java.util.ArrayList;

public class DonorList {
    private ArrayList<Donor> donors = new ArrayList<>();

    public void addDonor(Donor donor) {
        donors.add(donor);
    }

    public String getAllDonors() {
        String text = "----- DONOR LIST -----\n";

        if (donors.isEmpty()) {
            text += "No donors added yet.\n";
        } else {
            for (Donor donor : donors) {
                text += donor + "\n";
            }
        }

        return text;
    }
}
