package hello.itemservice.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.FieldError;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.ObjectError;

import static org.assertj.core.api.Assertions.*;

public class MessageCodesResolverTest {

    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void messageCodesResolverObject() {
        //객체 오류의 경우 2가지 생성
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
        }
        assertThat(messageCodes).containsExactly("required.item", "required");
    }

    @Test
    void messageCodesResolverField() {
        //필드 오류의 경우 4가지 생성
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);
        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
        }

        assertThat(messageCodes).containsExactly("required.item.itemName", "required.itemName", "required.java.lang.String", "required");
//        bindingResult.rejectValue("itemName", "required"); -> 이 코드를 쓰면 내부에서 스프링이 리졸버를 사용해서 메시지 코드들을 생성
        // ->  리졸버가 생성       String[] messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);
//        new FieldError("item", "itemName", null, false, messageCodes, null, null) -> 생성한 메시지 코드를 보관
        // 뷰에서 메시키 코드들을 순서대로 탐색하여 사용함
    }
}
