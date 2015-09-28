package ro.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.entity.Category;
import ro.entity.MemoItem;

/**
 * Created by Roshane on 9/11/2015.
 */
@Controller(value = "AddItemController")
public class AddItemController {

    private static final Logger LOGGER= LoggerFactory.getLogger(AddItemController.class);
    @RequestMapping(value = "/add")
    public String addItem(Model model) {
        model.addAttribute("memoItem", new MemoItem());
        model.addAttribute("memoCategory", Category.values());
        return "add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addItem(Model model,MemoItem memoItem, BindingResult bindingResult){
        System.out.println(memoItem);
        LOGGER.debug("post item [{}]",memoItem);
        return "redirect:/home";
    }
}
