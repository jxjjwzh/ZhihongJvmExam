package zhihong;

import java.lang.annotation.Annotation;

@ZhihongAuthor("Hello World!111")
class AnnotationTest {

    public static void main(String[] args) {
        try {
            Annotation[] annotations = AnnotationTest.class.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof ZhihongAuthor) {
                    ZhihongAuthor zhihongAuthorAnnotation = (ZhihongAuthor)annotation;
                    System.out.println(zhihongAuthorAnnotation.value());
                }
            }

        } catch (Exception ignore) {}
    }
}
