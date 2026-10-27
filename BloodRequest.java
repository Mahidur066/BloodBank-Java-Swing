public class BloodRequest {

    private String patientName;
    private String bloodGroup;
    private int unit;

    public BloodRequest(String patientName, String bloodGroup) {
        this.patientName = patientName;
        this.bloodGroup = bloodGroup;
        this.unit = 1;
    }

    public BloodRequest(String patientName, String bloodGroup, int unit) {
        this.patientName = patientName;
        this.bloodGroup = bloodGroup;
        this.unit = unit;
    }

    public void showRequest() {
        System.out.println(patientName + " needs " + unit +
                " unit(s) of " + bloodGroup);
    }

    @Override
    public String toString() {
        return patientName + " needs " + unit +
                " unit(s) of " + bloodGroup;
    }
}
