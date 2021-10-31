package zhihong;

import java.util.LinkedHashMap;

class LinkedHashMapTest {

    public static void main(String[] args) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16, 0.75f, true);
        linkedHashMap.put("1", "Tony");
        linkedHashMap.put("2", "Martin");
        linkedHashMap.put("3", "Pony");
        System.out.println(linkedHashMap);

        linkedHashMap.get("2");
        System.out.println(linkedHashMap);
    }
}
