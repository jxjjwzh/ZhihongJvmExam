package test;

import java.lang.ref.WeakReference;

public class WeakRefTest {

    public static void main(String[] args) {
        Person strongRefPerson = new Person();
        strongRefPerson.mName = "Tom";
        WeakReference<Person> weakRefPerson = new WeakReference<>(strongRefPerson);

        System.out.println("Before gc");
        printPerson(weakRefPerson);

        System.gc();
        System.out.println("\nAfter gc");
        printPerson(weakRefPerson);

        //noinspection UnusedAssignment
        strongRefPerson = null;
        System.gc();
        System.out.println("\nAfter Strong clear and gc");
        printPerson(weakRefPerson);
        // 最后这个print打印出弱引用get为空，说明，弱引用的回收机制是，GC时发现无其他强引用的时候，弱引用将为空。
    }

    private static void printPerson(WeakReference<Person> weakRefPerson) {
        Person person = weakRefPerson.get();
        if (person!= null) {
            System.out.println("get person name is: " + person.mName);
        } else {
            System.out.println("get null!");
        }
    }

    static class Person {
        public String mName;
    }
}
