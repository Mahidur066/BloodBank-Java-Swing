public class BloodInventory {

    private BloodStock stock = new BloodStock();

    // Add Blood
    public void addBlood(String group, int unit) {
        stock.add(group, unit);
    }

    // Remove Blood
    public boolean removeBlood(String group, int unit) {
        return stock.remove(group, unit);
    }

    // Get Blood Stock for GUI
    public int getStock(String group) {
        return stock.get(group);
    }

    // View Blood Stock
    public void viewStock() {
        stock.viewStock();
    }
}
