import java.util.Scanner;

public class Student {
    private String stdName;
    private int totalSub;

    public Student(String stdName, int totalSub) {
        this.stdName = stdName;
        this.totalSub = totalSub;
    }

    public double outOfMarks(Scanner sc) {
        System.out.println("Enter out of marks");
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public static String inputName(Scanner sc) {
        while (true) {
            System.out.print("Enter student name: ");
            String name = sc.nextLine().trim();
            if (!name.isEmpty()) {
                return name;
            } else {
                System.out.println("Name cannot be empty. Please enter a valid name.");
            }
        }
    }

    public static int inputSub(Scanner sc) {
        System.out.println("\nEnter how many subjects");
        while (true) {
            try {
                int subs = Integer.parseInt(sc.nextLine());
                if (subs > 0) {
                    return subs;
                } else {
                    System.out.println("Zero(0) or negative value. Please enter positive value.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public double getMarks(Scanner sc) {
        System.out.println("\nEnter Marks of subjects");
        System.out.println("-----------------------------");
        double totalMarks = 0;

        for (int i = 1; i <= totalSub; i++) {
            System.out.println("Enter marks for subject " + i + ":");
            while (true) {
                try {
                    double marks = Double.parseDouble(sc.nextLine());
                    totalMarks += marks;
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter numeric value.");
                }
            }
        }

        return totalMarks;
    }

    public double averageCal(double totalM) {
        if (totalSub <= 0) {
            throw new IllegalArgumentException("totalSub must be greater than zero");
        }
        return totalM / totalSub;
    }

    public void gradeAssign(double calculatedAvg, double outofmarks, double totalMarks) {
        double percentage = (totalMarks / outofmarks) * 100;

        System.out.println("-----------------------------");
        System.out.println("Name: " + stdName);
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Marks: " + calculatedAvg);
        System.out.println("Percentage: " + percentage);

        if (percentage >= 90) {
            System.out.println("Grade: A");
        } else if (percentage >= 80) {
            System.out.println("Grade: B");
        } else if (percentage >= 70) {
            System.out.println("Grade: C");
        } else if (percentage >= 60) {
            System.out.println("Grade: D");
        } else if (percentage >= 36) {
            System.out.println("Grade: E");
        } else {
            System.out.println("Grade: F (Failed)");
        }

        System.out.println("-----------------------------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String stdName = inputName(sc);
        int totalSub = inputSub(sc);

        Student student = new Student(stdName, totalSub);
        double totalMarks = student.getMarks(sc);
        double averageMarks = student.averageCal(totalMarks);
        double outOfMarks = student.outOfMarks(sc);

        student.gradeAssign(averageMarks, outOfMarks, totalMarks);

        sc.close();
    }
}
