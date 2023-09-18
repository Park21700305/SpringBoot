package com.park.springboot.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hi")
    public String niceToMeetYou(Model model){
        model.addAttribute("username","재유");
        return "greetings"; //templates/greetings.mustache -> 브라우저로 전송!
    }
   @GetMapping("/bye")
    public String goodbye(Model model){
        model.addAttribute("nickname","재유");
        return "goodbye";
   }

}
