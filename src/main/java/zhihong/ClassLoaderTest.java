package zhihong;

public class ClassLoaderTest {

    public static void main(String[] args) {
        System.out.println("output: " + Son.factor);
    }

    class GrandPa {

        static {
            System.out.println("I'm grandpa -static");
        }
    }

    class Father extends GrandPa {
        public static int factor = 25;

        static {
            System.out.println("I'm father -static");
        }
    }

    class Son extends Father {
        //        public static int factor = 5; // 如果没有这行，那么下面的static不执行
        // 儿子类调用父类的静态成员，不用对儿子类做类初始化
        static {
            System.out.println("I'm son -static");
        }
    }
}
