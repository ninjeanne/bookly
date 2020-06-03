package dhbw.online.bookly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class VueController {
    @RequestMapping(value = "/user")
    public String redirectApiUser() {
        return "forward:/";
    }

    @RequestMapping(value = "/bookeditor")
    public String redirectApiBookEditor() {
        return "forward:/";
    }

    @RequestMapping(value = "/pageeditor")
    public String redirectApiPageEditor() {
        return "forward:/";
    }

    @RequestMapping(value = "/page")
    public String redirectApiPage() {
        return "forward:/";
    }

    @RequestMapping(value = "/book")
    public String redirectApiBook() {
        return "forward:/";
    }

    @RequestMapping(value = "/about")
    public String redirectApiabout() {
        return "forward:/";
    }

    @RequestMapping(value = "/termsofservice")
    public String redirectApiterms() {
        return "forward:/";
    }

    @RequestMapping(value = "/help")
    public String redirectApihelp() {
        return "forward:/";
    }
}
