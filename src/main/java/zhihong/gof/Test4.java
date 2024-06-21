package zhihong.gof;

class Father {

    private int a ;
    private int b;

    public int opear(){
        return a + b;
    }


    public void accept(Execute exe){
        exe.method(this);
    }
}

class Son1 extends Father{

}
class Son2 extends Father{

}

class Execute {

//    public void method(Father father){
//        System.out.println("This is Father's method");
//    }
//
//    public void method(Son1 son){
//        System.out.println("This is Son1's method");
//    }
//
//    public void method(Son2 son){
//        System.out.println("This is Son2's method");
//    }

    public void method(Father people) {
        if (people instanceof Son1) {
            System.out.println("This is Son1's method");
        } else if (people instanceof Son2) {
            System.out.println("This is Son2's method");
        } else if (people != null) {
            System.out.println("This is Father's method");
        }
    }
}

public class Test4 {
    public static void main(String[] args){
        Father father = new Father();
        Son1 s1 = new Son1();
        Son2 s2 = new Son2();

        Execute exe = new Execute();
        father.accept(exe);
        s1.accept(exe);
        s2.accept(exe);
    }
}
