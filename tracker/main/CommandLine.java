package tracker.main;

import tracker.util.NotificationService;
import tracker.util.Statistics;
import tracker.util.TextRecognition;

import java.util.ArrayList;
import java.util.Scanner;

public class CommandLine {
    private final Scanner scanner = new Scanner(System.in);
    private final TextRecognition textRecognition = new TextRecognition();
    private String input = "";
    private boolean programExit = false;
    private ArrayList<Student> listOfStudents = new ArrayList<>();
    private Statistics statistics = new Statistics();
    private NotificationService notificationService = new NotificationService();



    public void startProgram() {
        System.out.println("Learning Progress Tracker\n");
        while (!programExit) {
            System.out.println("Enter one of the commands(add students, add points,\n" +
                    "list, find, statistics, notify, exit):");
            scanInput();
            switch (input) {
                case "exit": programExit = true; break;
                case "add students": addStudentsCommand(); break;
                case "add points": addPointsCommand(); break;
                case "list": listCommand(); break;
                case "find": findCommand(); break;
                case "statistics": statisticsCommand(); break;
                case "notify": notifyCommand(); break;
                case "back":
                    System.out.println("Enter 'exit' to exit the program."); break;
                default: System.out.println("Error: unknown command!");
            }
        }
        System.out.println("Bye!");
    }






    //               COMMANDS FOR MENU                  //
    private void notifyCommand() {
        notificationService.notify(listOfStudents);
    }

    private void statisticsCommand() {
        statistics.printGeneralStatistics(listOfStudents);
        while (true) {
            scanInput();
            if (input.equals("back")) break;
            else if (textRecognition.checkStatistics(input)) statistics.getSpecificStatistics(input, listOfStudents);
            }
        }

    private void addPointsCommand() {
        while (true) {
            System.out.println("Enter an id and points or 'back' to return:" +
                    "(format should be: 'student id' 'java' 'dsa' 'databases' 'spring')");
            scanInput();
            if (input.equals("back")) break;
            if (textRecognition.checkAddPoints(input)) {
                System.out.println("Points updated.");
                addPoints();
            }
        }
    }

    private void findCommand() {
        while (true) {
            System.out.println("Enter an id or 'back' to return:");
            scanInput();
            if (input.equals("back")) break;
            if (textRecognition.checkIfNumber(input)) {
                int lookingFor = Integer.valueOf(input);
                boolean foundStudent = false;
                for (Student student : listOfStudents) {
                    if (lookingFor == student.getStudentId()) {
                        foundStudent = student.printPoints();
                        break;
                    }
                }
                if (!foundStudent) System.out.println("No student is found for id=" + lookingFor);
            }
            else System.out.println("Incorrect value. Type a number:");
        }
    }

    private void addStudentsCommand() {
        int addedStudents = 0;
        while (true) {
            System.out.println("Enter student credentials or 'back' to return:\n" +
                    "(format should be: 'first name' 'last name' 'email address'");
            input = scanner.nextLine();
            if (input.toLowerCase().equals("back")) break;
            else {
                if (textRecognition.checkAddStudentCorrectness(input)) {
                    if (checkEmailUniqueness(textRecognition.getEmail())) {
                        addedStudents++;
                        listOfStudents.add(new Student(listOfStudents.size(), textRecognition.getFirstName(),
                                textRecognition.getLastName(), textRecognition.getEmail()));
                        System.out.println("The student has been added.");
                    }
                    else {
                        System.out.println("This email is already taken.");
                    }
                }
            }
        }
        System.out.println("Total " + addedStudents + " students have been added.");
    }

    private void listCommand() {
        if (listOfStudents.isEmpty()) System.out.println("No students found.");
        else {
            System.out.println("Students:");
            listOfStudents.forEach(System.out::println);
//            for (int i = 0; i < listOfStudents.size(); i++) {
//                System.out.println(listOfStudents.get(i).getStudentId());
//            }
        }
    }
    //               COMMANDS FOR MENU                  //






    //                OTHER USEFUL METHODS               //
    private void addPoints() {
        String[] addPointsInput = input.trim().split(" ");
        int[] arrayPointsInput = new int[addPointsInput.length];
        if (!textRecognition.checkIfNumber(addPointsInput[0])) {
            System.out.println("No student is found for id=" + addPointsInput[0]);
        }
        else {
            for (int i = 0; i < addPointsInput.length; i++) {
                arrayPointsInput[i] = Integer.valueOf(addPointsInput[i]);
            }
            if (checkPointsId(arrayPointsInput[0]) != null) {
                checkPointsId(arrayPointsInput[0]).addPoints(arrayPointsInput[1],
                        arrayPointsInput[2], arrayPointsInput[3], arrayPointsInput[4]);
                statistics.updateStatisticsWithPoints(arrayPointsInput);
            } else {
                System.out.println("No student is found for id=" + addPointsInput[0]);
            }
        }
    }

    private Student checkPointsId(int id) {
        for (Student student : listOfStudents) {
            if (id == student.getStudentId()) return student;
        }
        return null;
    }

    private void scanInput() {
        while (true) {
            input = scanner.nextLine().toLowerCase();
            if (!input.trim().equals("")) break;
            else System.out.println("No input.");
        }
    }

    private boolean checkEmailUniqueness(String email) {
        for (int i = 0; i < listOfStudents.size(); i++) {
            if (listOfStudents.get(i).getEmail().equals(email)) return false;
        }
        return true;
    }
}
