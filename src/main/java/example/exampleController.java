package example;

import org.springframework.web.bind.annotation.GetMapping;

public class exampleController {


    @GetMapping("/")
    public String index() {

        return "redirect:/guestbook/list";
    }

}
