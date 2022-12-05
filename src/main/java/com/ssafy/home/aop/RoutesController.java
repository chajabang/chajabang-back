package com.ssafy.home.aop;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RoutesController implements ErrorController {
    private final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    public String redirectRoot() {
        return "forward:/";
    }

}
