package zhihong;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class ThreadTest {

    public static void main(String[] args) {
        System.out.println("run 1");
        startAnimation("1");

        System.out.println("run 2");
        startAnimation("2");
    }

    private static void startAnimation(String target) {
        AnimationWrapper wrapper = new AnimationWrapper();
        ArrayList<String> list = new ArrayList<>();
        list.add(target);
        Runnable finishCallback = () -> {

        };
        wrapper.onCreateAnimation(finishCallback);
    }


}

class AnimationWrapper {

    public void onCreateAnimation(Runnable finishCallback) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Executors.newSingleThreadExecutor().execute(finishCallback);
    }
}