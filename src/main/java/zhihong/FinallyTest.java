package zhihong;

class FinallyTest {

    public static void main(String[] args) {
        System.out.println("result of test1: "+ test1());
    }

    private static int test1() {
        int i = 1;
        try {
            System.out.println("try...");
            return i += 10;
        } catch (Exception e) {
            System.out.println("catch...");
        } finally {
            i++;
            System.out.println("finally...");
            System.out.println("i=" + i);
        }
        System.out.println("last return not execute...");
        return i;
    }


}
