package MiniProject;

import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.*;
import java.text.DecimalFormat;

public class BabyNames {
    private void totalBirths(FileResource res) {
        CSVParser parser = res.getCSVParser(false);
        int totalNames = 0, femaleNames = 0, maleNames = 0;
        for (CSVRecord record: parser) {
            totalNames++;
            if (record.get(1).equals("M")) {
                maleNames++;
            } else {
                femaleNames++;
            }
        }
        System.out.println("Total Names: " + totalNames);
        System.out.println("Female Names: " + femaleNames);
        System.out.println("Male Names: " + maleNames);
    }

    public void testTotalBirths() {
        FileResource res = new FileResource("data/yob1905.csv");
        totalBirths(res);
    }


    private int getRank(int year, String name, String gender) {
        int rank = 0;
        FileResource res = new FileResource("data/yob" + year + ".csv");
        CSVParser parser = res.getCSVParser(false);
        for (CSVRecord record: parser) {
            if (record.get(1).equals(gender)) {
                rank++;
                if (record.get(0).equals(name)) {
                    break;
                }
            }
        }
        if (rank == 0) {
            return -1;
        } else {
            return rank;
        }
    }

    public void testGetRank() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter year");
        int year = sc.nextInt();

        sc.nextLine();
        System.out.println("enter name");
        String name = sc.nextLine();

        System.out.println("enter gender");
        String gender = sc.nextLine();

        System.out.println(getRank(year, name, gender));
    }

    private String getName(int year, int rank, String gender) {
        FileResource res = new FileResource("data/yob" + year + ".csv");
        CSVParser parser = res.getCSVParser(false);
        String name = "";
        for (CSVRecord record: parser) {
            if (record.get(1).equals(gender) && --rank == 0) {
                name = record.get(0);
                break;
            }
        }
        if (name == "") {
            return "NO NAME";
        } else {
            return name;
        }
    }

    public void testGetName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter year");
        int year = sc.nextInt();

        System.out.println("enter rank");
        int rank = sc.nextInt();

        sc.nextLine();
        System.out.println("enter gender");
        String gender = sc.nextLine();
        System.out.println(getName(year, rank, gender));
    }

    private String whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        return newName;
    }

    public void testWhatIsNameInYear() {
        Scanner sc = new Scanner(System.in);

        System.out.println("enter name");
        String name = sc.nextLine();

        System.out.println("enter year");
        int year = sc.nextInt();

        System.out.println("enter new year");
        int newYear = sc.nextInt();

        sc.nextLine();
        System.out.println("enter gender");
        String gender = sc.nextLine();
        String newName = whatIsNameInYear(name, year, newYear, gender);
        if (gender.equals("M")) {
            System.out.println(name + " born in " + year + " would be " + newName + " if he was born in " + newYear + ".");
        } else {
            System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear + ".");
        }
    }

    private int yearOfHighestRank(String name, String gender) {
        int highestRank = Integer.MAX_VALUE;
        int yearOfHighestRank = 0;
        for (int year = 1880; year <= 2014; year++) {
            int rank = getRank(year, name, gender);
            if (rank != -1 && rank < highestRank) {
                highestRank = rank;
                yearOfHighestRank = year;
            }
        }
        if (yearOfHighestRank == 0) {
            return -1;
        } else {
            return yearOfHighestRank;
        }
    }

    public void testYearOfHighestRank() {
        Scanner sc = new Scanner(System.in);

        System.out.println("enter name");
        String name = sc.nextLine();

        System.out.println("enter gender");
        String gender = sc.nextLine();

        System.out.println(yearOfHighestRank(name, gender));
    }

    private double getAverageRank(String name, String gender) {
        int filesHavingNameAndGender = 0;
        double totalSumOfTheRanks = 0;
        for (int year = 1880; year <= 2014; year++) {
            int rank = getRank(year, name, gender);
            if (rank != -1) {
                filesHavingNameAndGender++;
                totalSumOfTheRanks += rank;
            }
        }

        if (filesHavingNameAndGender == 0) {
            return -1;
        } else {
            double answer = totalSumOfTheRanks / filesHavingNameAndGender;
            DecimalFormat df = new DecimalFormat("0.00");
            String formate = df.format(answer);
            answer = Double.parseDouble(formate);
            return answer;
        }
    }

    public void testGetAverageRank() {
        Scanner sc = new Scanner(System.in);

        System.out.println("enter name");
        String name = sc.nextLine();

        System.out.println("enter gender");
        String gender = sc.nextLine();

        System.out.println(getAverageRank(name, gender));
    }

    private int getTotalBirthsRankedHigher(int year, String name, String gender) {
        FileResource res = new FileResource("data/yob" + year + ".csv");
        CSVParser parser = res.getCSVParser(false);
        int totalBirths = 0;
        for (CSVRecord record: parser) {
            if (record.get(1).equals(gender)) {
                if (record.get(0).equals(name)) {
                    break;
                }
                totalBirths += Integer.parseInt(record.get(2));
            }
        }
        return totalBirths;
    }

    public void testGetTotalBirthsRankedHigher() {
        Scanner sc = new Scanner(System.in);

        System.out.println("enter year");
        int year = sc.nextInt();

        System.out.println("enter name");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.println("enter gender");
        String gender = sc.nextLine();

        System.out.println(getTotalBirthsRankedHigher(year, name, gender));

    }
}
