package tracker.util;

import tracker.main.Student;

import java.util.ArrayList;


public class Statistics {
    private final int[] REQUIRED_POINTS_FOR_COMPLETION = {600, 400, 480, 550};

    private int[] totalPoints = new int[4];
    private int[] totalTests = new int[4];
    private int[] totalEnrolled = new int[4];
    private final PointsComparator pointsComparator = new PointsComparator();





    public void updateStatisticsWithPoints(int[] input) {
        int[] inputWithoutId = {input[1], input[2], input[3], input[4]};
        for (int i = 0; i < 4; i++) {
            if (inputWithoutId[i] > 0) {
                totalPoints[i] += inputWithoutId[i];
                totalTests[i]++;
            }
        }
    }


    public void printGeneralStatistics(ArrayList<Student> students) {
        System.out.println("Type the name of a course to see details or 'back' to quit:");
            calculateAndPrintPopularity(students);
            calculateAndPrintActivity();
            calculateAndPrintDifficulty();
    }

    public void getSpecificStatistics(String course, ArrayList<Student> students) {
        int index;
        String courseName = "";
        if (course.equals("java")) {
            index = 0;
            courseName = "Java";
        }
        else if (course.equals("dsa")) {
            index = 1;
            courseName = "DSA";
        }
        else if (course.equals("databases")) {
            index = 2;
            courseName = "Databases";
        }
        else {
            index = 3;
            courseName = "Spring";
        }

        pointsComparator.setIndex(index);
        students.sort(pointsComparator);
        System.out.println(courseName);
        System.out.println("id     points completed");
        for (Student student : students) {
            if (student.getCoursePoints()[index] != 0)
            System.out.println(student.getStudentId() + " " + student.getCoursePoints()[index] + "\t" +
                    String.format("%.1f", calculatePercentageOfCompletion(student.getCoursePoints()[index], index)) + "%");
        }
    }


    private void calculateAndPrintDifficulty() {
        int[] arrayOfDifficulty = new int[4];
        double[] preciseResult = new double[4];
        for (int i = 0; i < 4; i++) {
            preciseResult[i] = totalTests[i] != 0 ? 1.0 * totalPoints[i] / totalTests[i] : 0;
        }
        for (int i = 0; i < 4; i++) {
            arrayOfDifficulty[i] = (int) (preciseResult[i] * 100);
        }
        mostAndLeastPrint(arrayOfDifficulty, "Easiest course: ", "Hardest course: ");
    }

    private void calculateAndPrintActivity() {
        mostAndLeastPrint(totalTests, "Highest activity: ", "Lowest activity: ");
    }

    private void calculateAndPrintPopularity(ArrayList<Student> students) {
        for (int i = 0; i < totalEnrolled.length; i++) {
            totalEnrolled[i] = 0;
        }
        for (Student student : students) {
            if (student.getCoursePoints()[0] != 0) totalEnrolled[0]++;
            if (student.getCoursePoints()[1] != 0) totalEnrolled[1]++;
            if (student.getCoursePoints()[2] != 0) totalEnrolled[2]++;
            if (student.getCoursePoints()[3] != 0) totalEnrolled[3]++;
        }
        mostAndLeastPrint(totalEnrolled, "Most popular: ", "Least popular: ");
    }

    private void mostAndLeastPrint(int[] array, String most, String least) {
        int largestNumber = checkLargest(array);
        int lowestNumber = checkLowest(array);
        String mostPopularCourse = "";
        String leastPopularCourse = "";
        if (largestNumber == 0) {
            mostPopularCourse = "n/a";
            leastPopularCourse = "n/a";
        }
        else {
            mostPopularCourse = concatenateCourses(largestNumber, array);
            leastPopularCourse = concatenateCourses(lowestNumber, array);
            if (largestNumber == lowestNumber) leastPopularCourse = "n/a";
        }
        System.out.println(most + mostPopularCourse);
        System.out.println(least + leastPopularCourse);
    }

    private int checkLowest(int[] array) {
        int result = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < result) result = array[i];
        }
        return result;
    }
    private int checkLargest(int[] array) {
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > result) result = array[i];
        }
        return result;
    }
    private String concatenateCourses(int number, int[] array) {
        String result = "";
        if (array[0] == number) result += "Java";
        if (array[1] == number) result += " DSA";
        if (array[2] == number) result += " Databases";
        if (array[3] == number) result += " Spring";
        result = result.trim();
        return result.trim().replaceAll(" ", ", ");
    }

    private double calculatePercentageOfCompletion(int points, int index) {
        double result = 1.0 * points / REQUIRED_POINTS_FOR_COMPLETION[index] * 100;
        return result;
    }
}



