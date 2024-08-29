package hello.exception.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            if (ex instanceof IllegalArgumentException) {
                log.info("IllegalArgumentException resolver to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return new ModelAndView(); //예외를 여기서 먹어버리고 정상 리턴하니까 was에서는 정상으로 인식함, 서블릿에서 에러 페이지만 찾음
                //기존에는 에러가 was까지 도착해서 was부터 다시 서블릿 불러서 후속처리 시작해서 되게 복잡햇는데 여기서 이렇게 에러를 먹어버리면 바로 서블릿에서 빠르게 처리
            }
        } catch (IOException e) {
            log.error("resolver ex", e);
        }

        return null;
    }
}
