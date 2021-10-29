package test;

class MonitorTest4j {

    public static void main(String[] args) {
        /* 会报错，因为非静态内部类是在外部类加载的时候，未必被加载了。所以在外部类的静态函数中，不可以依赖非静态内部类。
         * 同理，非静态内部类中也不可以定义静态的函数和方法
         */
//        Person p = new Person();
        MonitorTest4j monitorTest4j = new MonitorTest4j();
        try {
            monitorTest4j.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class Person{
    }
}
