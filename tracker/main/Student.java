package tracker.main;

public class Student {

    //STUDENT ID FIELDS
    private int studentId = 10000;
    private String firstName = "";
    private String lastName = "";
    private String email = "";

    //POINT FIELDS
    private int[] coursePoints = new int[4];



    public Student(int studentId, String firstName, String lastName, String email) {
        this.studentId += studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean printPoints() {
        System.out.println(studentId + " points: Java=" + coursePoints[0] + "; DSA=" + coursePoints[1]
                + "; Databases=" + coursePoints[2] + "; Spring=" + coursePoints[3]);
        return true;
    }

    public void addPoints(int java, int dsa, int dataBases, int spring) {
        coursePoints[0] += java;
        coursePoints[1] += dsa;
        coursePoints[2] += dataBases;
        coursePoints[3] += spring;
    }


    public int[] getCoursePoints() {
        return coursePoints;
    }
}
