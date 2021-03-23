package Part_2_HowMany_Finding_Multiple_Occurrences;

public class Part2 {
    public int howMany(String stringa, String stringb) {
        int totalOccurrences = 0;
        int start = 0;
        while (true) {
            int index = stringb.indexOf(stringa, start);
            if (index == -1) {
                break;
            }
            totalOccurrences++;
            start = index + stringa.length();
        }
        return totalOccurrences;
    }

    public void testHowMany() {
        String stringa = "GAA";
        String stringb = "ATGAACGAATTGAATC";
        System.out.println("Total occurences of " + stringa + " in " + stringb + " = " + howMany(stringa, stringb));
    }
}
