import java.util.*;
class Student {
    private int id;
    private String name;
    private double marks;

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }
    public int getId()        { return id; }
    public String getName()   { return name; }
    public double getMarks()  { return marks; }
    public void setName(String name)   { this.name = name; }
    public void setMarks(double marks) { this.marks = marks; }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %-15s | Marks: %.2f", id, name, marks);
    }
}
public class StudentManagement {

    private static final List<Student> students = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = getInt("Choose an option: ");

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> exitApp();
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
    private static void addStudent() {
        int id       = getInt("Enter ID: ");
        sc.nextLine();         
        System.out.print("Enter Name: ");
        String name  = sc.nextLine();
        double marks = getDouble("Enter Marks: ");

        students.add(new Student(id, name, marks));
        System.out.println("Student added successfully.\n");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.\n");
            return;
        }
        System.out.println("\n------ Student List ------");
        students.forEach(System.out::println);
        System.out.println();
    }

    private static void updateStudent() {
        int id = getInt("Enter ID to update: ");
        Student s = findById(id);

        if (s == null) {
            System.out.println("Student not found.\n");
            return;
        }
        sc.nextLine();         
        System.out.print("Enter new Name: ");
        s.setName(sc.nextLine());
        s.setMarks(getDouble("Enter new Marks: "));
        System.out.println("Student updated.\n");
    }

    private static void deleteStudent() {
        int id = getInt("Enter ID to delete: ");
        Student s = findById(id);

        if (s != null && students.remove(s)) {
            System.out.println("Student deleted.\n");
        } else {
            System.out.println("Student not found.\n");
        }
    }
    private static void printMenu() {
        System.out.println("""
                === Student Record Management ===
                1. Add Student
                2. View Students
                3. Update Student
                4. Delete Student
                5. Exit
                """);
    }

    private static Student findById(int id) {
        return students.stream()
                       .filter(s -> s.getId() == id)
                       .findFirst()
                       .orElse(null);
    }

    private static int getInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                sc.nextLine();
            }
        }
    }

    private static double getDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                sc.nextLine();
            }
        }
    }

    private static void exitApp() {
        sc.close();
        System.out.println("Exiting...");
        System.exit(0);
    }
}