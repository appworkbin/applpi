package com.avq.sample;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class DemoController {

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, AuthModel model) {
        model.addAttribute("name", name);
        return "greeting1";
    }
    
    @RequestMapping("/")
    public String home(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "home";
    }
    
    @RequestMapping("pages/index.html")
    public String index() {
        return "pages/index";
    }

    @RequestMapping("pages/blank.html")
    public String blank() {
        return "pages/blank";
    }
    
    @RequestMapping("pages/buttons.html")
    public String buttons() {
        return "pages/buttons";
    }    
    
    @RequestMapping("pages/flot.html")
    public String flot() {
        return "pages/flot";
    }    
    
    @RequestMapping("pages/forms.html")
    public String forms() {
        return "pages/forms";
    }    
    
    @RequestMapping("pages/grid.html")
    public String grid() {
        return "pages/grid";
    }    
    
    @RequestMapping("pages/icons.html")
    public String icons() {
        return "pages/icons";
    }
    
    @RequestMapping("pages/login.html")
    public String login() {
        return "pages/login";
    }
 
    @RequestMapping("pages/morris.html")
    public String morris() {
        return "pages/morris";
    }
 
    @RequestMapping("pages/notifications.html")
    public String notifications() {
        return "pages/notifications";
    }

 
    @RequestMapping("pages/panels-wells.html")
    public String panelsWells() {
        return "pages/panels-wells";
    }
 
    @RequestMapping("pages/tables.html")
    public String tables() {
        return "pages/tables";
    }
 
    @RequestMapping("pages/typography.html")
    public String typography() {
        return "pages/typography";
    }
 
   
    
    
}
