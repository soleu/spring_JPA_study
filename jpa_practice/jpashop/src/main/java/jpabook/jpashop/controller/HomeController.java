package jpabook.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {
    //    Logger log = LoggerFactory.getLogger(); //@Slf4j
    @RequestMapping("/")
    public String home() {
        log.info("home controller");
        return "home";
    }
}
