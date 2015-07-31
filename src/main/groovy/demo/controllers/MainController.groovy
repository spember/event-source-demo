package demo.controllers

import groovy.transform.CompileStatic
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

/**
 * @author Steve Pember
 */
@CompileStatic
@Controller
class MainController {

    @RequestMapping("/")
    ModelAndView home() {
        new ModelAndView('home')
    }
}
