import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

class CallStudent {

    public static List<String> nameList = Collections.synchronizedList(new ArrayList<>());

    public static void main (String[] args) {
        Teacher teacher = new Teacher();

        runThreadAdd("Tony", teacher);
        runThreadAdd("Pony", teacher);
        runThreadAdd("Allen", teacher);

        teacher.start();

    }

    private static void runThreadAdd(final String studentName, final Teacher teacher) {
        new Thread(() -> {
            Student student = new Student(studentName);
            student.callTeacher4Name(teacher);
        }).start();
    }
}

class Teacher {

    private final LinkedBlockingQueue<String> mQueue = new LinkedBlockingQueue<>();

    public void start() {
        while (true) {
            try {
                CallStudent.nameList.add(mQueue.take());
                System.out.println("Current name List is: " + CallStudent.nameList);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void submitName(String name) {
        mQueue.add(name);
    }
}

class Student {
    private final String mName;

    public Student(String name) {
        mName = name;
    }

    public void callTeacher4Name(Teacher teacher) {
        teacher.submitName(mName);
    }
}