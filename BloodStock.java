public class BloodStock {

    private int aPositive = 0;
    private int aNegative = 0;
    private int bPositive = 0;
    private int bNegative = 0;
    private int abPositive = 0;
    private int abNegative = 0;
    private int oPositive = 0;
    private int oNegative = 0;

    public void add(String group, int unit) {
        if (group.equalsIgnoreCase("A+"))
            aPositive += unit;
        else if (group.equalsIgnoreCase("A-"))
            aNegative += unit;
        else if (group.equalsIgnoreCase("B+"))
            bPositive += unit;
        else if (group.equalsIgnoreCase("B-"))
            bNegative += unit;
        else if (group.equalsIgnoreCase("AB+"))
            abPositive += unit;
        else if (group.equalsIgnoreCase("AB-"))
            abNegative += unit;
        else if (group.equalsIgnoreCase("O+"))
            oPositive += unit;
        else if (group.equalsIgnoreCase("O-"))
            oNegative += unit;
    }

    public boolean remove(String group, int unit) {
        if (group.equalsIgnoreCase("A+")) {
            if (aPositive >= unit) {
                aPositive -= unit;
                return true;
            }
        } else if (group.equalsIgnoreCase("A-")) {
            if (aNegative >= unit) {
                aNegative -= unit;
                return true;
            }
        } else if (group.equalsIgnoreCase("B+")) {
            if (bPositive >= unit) {
                bPositive -= unit;
                return true;
            }
        } else if (group.equalsIgnoreCase("B-")) {
            if (bNegative >= unit) {
                bNegative -= unit;
                return true;
            }
        } else if (group.equalsIgnoreCase("AB+")) {
            if (abPositive >= unit) {
                abPositive -= unit;
                return true;
            }
        } else if (group.equalsIgnoreCase("AB-")) {
            if (abNegative >= unit) {
                abNegative -= unit;
                return true;
            }
        } else if (group.equalsIgnoreCase("O+")) {
            if (oPositive >= unit) {
                oPositive -= unit;
                return true;
            }
        } else if (group.equalsIgnoreCase("O-")) {
            if (oNegative >= unit) {
                oNegative -= unit;
                return true;
            }
        }

        return false;
    }

    public int get(String group) {
        if (group.equalsIgnoreCase("A+"))
            return aPositive;
        else if (group.equalsIgnoreCase("A-"))
            return aNegative;
        else if (group.equalsIgnoreCase("B+"))
            return bPositive;
        else if (group.equalsIgnoreCase("B-"))
            return bNegative;
        else if (group.equalsIgnoreCase("AB+"))
            return abPositive;
        else if (group.equalsIgnoreCase("AB-"))
            return abNegative;
        else if (group.equalsIgnoreCase("O+"))
            return oPositive;
        else if (group.equalsIgnoreCase("O-"))
            return oNegative;

        return 0;
    }

    public void viewStock() {
        System.out.println("A+  : " + aPositive);
        System.out.println("A-  : " + aNegative);
        System.out.println("B+  : " + bPositive);
        System.out.println("B-  : " + bNegative);
        System.out.println("AB+ : " + abPositive);
        System.out.println("AB- : " + abNegative);
        System.out.println("O+  : " + oPositive);
        System.out.println("O-  : " + oNegative);
    }
}
