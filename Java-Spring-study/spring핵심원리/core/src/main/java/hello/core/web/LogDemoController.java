package hello.core.web;

import hello.core.AppConfig;
import hello.core.AutoAppConfig;
import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;

    private final MyLogger myLogger;

    /*
    @Autowired
    private MyLogger myLogger;

    or

    @Autowired
      public LogDemoController(LogDemoService logDemoService, MyLogger myLogger) {
        this.logDemoService = logDemoService;
        this.myLogger = myLogger;
    }
    --> 이거를 @RequiredArgsConstructor 이거로 대체 ( 이 어노테이션이 알아서 다 주입해줌)
    */

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
//        ApplicationContext ac=new AnnotationConfigApplicationContext(AutoAppConfig.class);
//        MyLogger myLogger1 = ac.getBean("myLogger", MyLogger.class); --> proxy가 조회됨
        String requestURL = request.getRequestURL().toString();
        System.out.println("myLogger = " + myLogger.getClass());//스프링에서 등록한 객체가 조회됨
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
