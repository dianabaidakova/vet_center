package work.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import work.entity.User;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import work.entity.UserInfo;
import work.entity.VetService;
import work.service.UserInfoService;
import work.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);

    public UserController() {
        System.out.println("UserController()");
    }

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private work.service.VetService service;

    @RequestMapping("createUser")
    public ModelAndView createUser(@ModelAttribute User user) {
        logger.info("Creating User. Data: "+user);
        return new ModelAndView("usersForm");
    }

    @RequestMapping("table")
    public ModelAndView table() {
        logger.info("Table opened");
        return new ModelAndView("table");
    }

    @RequestMapping("autorization")
    public ModelAndView autorization() {
        logger.info("autorization page");
        return new ModelAndView("autorization");
    }

    @RequestMapping("service")
    public ModelAndView service() {
        logger.info("service page");
        return new ModelAndView("service");
    }

    @RequestMapping("registration")
    public ModelAndView registration() {
        logger.info("registration");
        return new ModelAndView("registration");
    }

    @RequestMapping("question")
    public ModelAndView question() {
        logger.info("question");
        return new ModelAndView("question");
    }

    @RequestMapping("ad")
    public ModelAndView ad() {
        logger.info("ad");
        return new ModelAndView("ad");
    }
    @RequestMapping("saveUser")
    public ModelAndView saveUser(@ModelAttribute("user") User user, @ModelAttribute("userInfo") UserInfo userInfo) {
        logger.info("Saving the User. Data : "+user);
        user.setId_role(1);
        userInfo.setLogin(user.getLogin());
        userService.createUser(user);
        userInfoService.createUserInfo(userInfo);

        List<VetService> services = service.getAllServices(user.getLogin());
        return new ModelAndView("ownPage", "services", services);
    }

    @RequestMapping("ownPage")
    public ModelAndView ownPage() {
        logger.info("ownPage");
        return new ModelAndView("ownPage");
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            model.addAttribute("message", "Hi " + principal.getName()
                    + "<br> You do not have permission to access this page!");
        } else {
            model.addAttribute("msg",
                    "You do not have permission to access this page!");
        }
        return "403";
    }

    @RequestMapping("/")
    public ModelAndView welcome() {
        logger.info("Welcome Page.");
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model ) {

        return "autorization";
    }

    @RequestMapping("getAllUsers")
    public ModelAndView getAllUsers() {
        logger.info("Getting the all Users.");
        List<User> userList = userService.getAllUsers();
        return new ModelAndView("userList", "userList", userList);
    }
}
