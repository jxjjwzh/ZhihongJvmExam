package zhihong;

import java.util.ArrayList;
import java.util.Arrays;

class ArrayListTest {

    public static void main(String[] args) {
        testListSize();
    }

    private static void testInit() {
        ArrayList<String> testList = new ArrayList<>(3);
        System.out.println(testList);
    }

    private static void testListSize() {
        ArrayList<String> testList = new ArrayList<>();
        // 以下两行都报错：Exception in thread "main" java.lang.IndexOutOfBoundsException: Index: 3, Size: 0
//        testList.add(3, "333");
//        testList.set(3, "333");

        // 添加测试数据
        testList.add("0");
        testList.add("1");
        testList.add("2");
        testList.add("4");

        testList.add(3, "3");

        // 移除所有的"2"
//        removeAllValue(testList, "2");
        System.out.println(testList);
    }

    private static void removeAllValue(ArrayList<String> list, String removeTarget) {
        int size = list.size();
        // 重点：从后往前遍历，保证元素移除后，待遍历部分下标不变
        for (int i = size-1; i >= 0; i--) {
            if (removeTarget.equals(list.get(i))) {
                list.remove(i);
            }
        }
    }

    private static void testArray() {
        // 创建一个二维数组
        int[][] array2d = new int[3][3];
        System.out.println(Arrays.deepToString(array2d));
    }
}