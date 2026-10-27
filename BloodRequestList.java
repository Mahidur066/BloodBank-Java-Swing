import java.util.ArrayList;

public class BloodRequestList {
    private ArrayList<BloodRequest> requests = new ArrayList<>();

    public void addRequest(BloodRequest request) {
        requests.add(request);
    }

    public String getAllRequests() {
        String text = "----- BLOOD REQUESTS -----\n";

        if (requests.isEmpty()) {
            text += "No requests yet.\n";
        } else {
            for (BloodRequest request : requests) {
                text += request + "\n";
            }
        }

        return text;
    }
}
