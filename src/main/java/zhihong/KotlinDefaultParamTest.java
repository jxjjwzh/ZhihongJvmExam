package zhihong;

import zhihong.kotlin.DefaultParams;

public class KotlinDefaultParamTest {
    public static void main(String[] args) {
        DefaultParams.INSTANCE.methodOneParam(1); // 一定要填入参，不支持重载
    }
}
