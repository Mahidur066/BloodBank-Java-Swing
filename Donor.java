public class Donor {
    private String name;
    private String id;
    private int age;
    private String bloodGroup;
    private String contact;

    public Donor(String name, String id, int age,
                 String bloodGroup, String contact) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return name + " | " + id + " | " +
                age + " | " + bloodGroup +
                " | " + contact;
    }
}
