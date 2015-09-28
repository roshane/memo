package ro.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.entity.Customer;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Roshane on 9/5/2015.
 */
@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String redirectToHome() {
        return "redirect:/home?menuActive=home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model, HttpServletRequest request) {
        model.addAttribute("customer", new Customer());
        logger.debug("request context path [{}]", request.getContextPath());
        return "index";
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public String addCustomer(@Valid Customer customer,
                              BindingResult bindingResult,
                              Model model) {
        logger.debug("customer data : [{}]", customer);
        model.addAttribute("customer", customer);
        if (bindingResult.hasErrors()) {
            logger.error("error [{}]", bindingResult.getAllErrors());
        } else {

        }
        return "index";
    }
}
