package com.treepal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HEController {
    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }
}
