package tracker.util;

import tracker.main.Student;

import java.util.ArrayList;

public class NotificationService {
    private final int[] REQUIRED_POINTS_FOR_COMPLETION = {600, 400, 480, 550};
    private final String[] COURSE_NAMES = {"Java", "DSA", "Databases", "Spring"};


    public void notify(ArrayList<Student> students) {
        int amountOfStudentsForNotification = getAmountOfStudentsForNotification(students);
        System.out.println("Total " + amountOfStudentsForNotification + " students have been notified.");
    }

    private int getAmountOfStudentsForNotification(ArrayList<Student> students) {
        int studentsToBeNotified = 0;
        for (Student student : students) {
            int studentNotified = 0;
            for (int i = 0; i < 4; i++) {
                if (student.getCoursePoints()[i] >= REQUIRED_POINTS_FOR_COMPLETION[i]) {
                    printNotification(student, COURSE_NAMES[i]);
                    studentNotified = 1;
                    student.getCoursePoints()[i] = 0;
                }
            }
            studentsToBeNotified += studentNotified;
        }
        return studentsToBeNotified;
    }

    private void printNotification(Student student, String courseName) {
        System.out.println("To: " + student.getEmail());
        System.out.println("Re: Your Learning Progress");
        System.out.println("Hello, " + student.getFirstName() + " " + student.getLastName() +
                "! You have accomplished our " + courseName + " course!");
    }
}
