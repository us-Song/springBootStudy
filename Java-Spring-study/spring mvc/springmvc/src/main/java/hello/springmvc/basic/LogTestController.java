package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
//    private final Logger log = LoggerFactory.getLogger(getClass()); -> @Slfj4 로 대체

    @GetMapping("/log-test")
    public String logTest() {

        String name = "Spring";
        //log.trace(" info log=" + name); // 설정이 info라서 출력은 안되지만 + 연산이 실행돼서 불필요한 자원 소모가 일어나기 때문에 이렇게 쓰면안됨

        log.trace("trace log={}", name);
        log.debug("trace log={}", name);
        log.info(" info log={}", name);
        log.warn(" info log={}", name);
        log.error(" info log={}", name);
        return "ok";
    }
}
