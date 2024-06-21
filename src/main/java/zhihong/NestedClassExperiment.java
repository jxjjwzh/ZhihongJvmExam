package zhihong;

public class NestedClassExperiment {

    public static void main(String[] args) {
        NestedClassExperiment nestedClass = new NestedClassExperiment();
        Inner in = nestedClass.new Inner(); // 注意这里在静态域中，创建内部类对象的方式。
    }

    final class Inner {

    }
}
