package tracker.util;

import tracker.main.Student;

import java.util.Comparator;

class PointsComparator implements Comparator<Student> {
    private int index = 0;

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int compare(Student s1, Student s2) {
        if (s1.getCoursePoints()[index] == s2.getCoursePoints()[index])
            return 0;
        else if (s1.getCoursePoints()[index] > s2.getCoursePoints()[index])
            return -1;
        else
            return 1;
    }
}