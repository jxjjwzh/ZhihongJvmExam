package zhihong;

import java.util.Arrays;

public class JavaFinalTest {

    public static void main(String[] args) {
        String str = "haha";
        new Thread() {
            @Override
            public void run() {
                // 下面代码编译报错
//                str = "333";
                System.out.println(str);
            }
        }.start();

    }
}
