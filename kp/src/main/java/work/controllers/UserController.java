package work.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import work.entity.*;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import work.entity.VetService;
import work.service.*;

import java.security.Principal;
import java.util.ArrayList;
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
    private work.service.VetService vetService;

    @Autowired
    private AdService adService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private BDserviceManage bdservice;

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

        List<Bdservice> servList = bdservice.getAllBDServices();
        return new ModelAndView("table", "servList", servList);
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

    @RequestMapping("tableAd")
    public ModelAndView tableAd() {
        logger.info("tableAd");

        List<Ad> adList = adService.getAllAds();
        return new ModelAndView("tableAd", "adList", adList);
    }

    @RequestMapping("tableQuestion")
    public ModelAndView tableQuestion() {
        logger.info("tableQuestion");

        List<Question> questionList = questionService.getAllQuestions();
        return new ModelAndView("tableQuestion", "questionList", questionList);

    }

    @RequestMapping("index")
    public ModelAndView index() {
        logger.info("index");
        return new ModelAndView("index");
    }

    @RequestMapping("searchService")
    public ModelAndView searchEmployee(@RequestParam("searchName") String searchName) {
        logger.info("Searching the Service. Find by: "+searchName);

        List<Bdservice> list = bdservice.getAllBDServices(searchName);
        List<Bdservice> servList=new ArrayList<Bdservice>();

        for (Bdservice bd:list) {
            if (bd.getName().contains(searchName.toLowerCase())) servList.add(bd);
        }
        if (servList.isEmpty()) servList=list;
        return new ModelAndView("table", "servList", servList);
    }

    @RequestMapping("saveUser")
    public ModelAndView saveUser(@ModelAttribute("user") User user, @ModelAttribute("userInfo") UserInfo userInfo) {
        logger.info("Saving the User. Data : "+user);
        user.setId_role(2);
        userInfo.setLogin(user.getLogin());
        userService.createUser(user);
        userInfoService.createUserInfo(userInfo);

        List<VetService> services = service.getAllServices(user.getLogin());
        return new ModelAndView("ownPage", "services", services);
    }

    @RequestMapping("ownPage")
    public ModelAndView ownPage(Model model, Principal principal) {
        logger.info("ownPage");
        List<VetService> services = service.getAllServices(principal.getName());
        return new ModelAndView("ownPage", "services", services);
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            model.addAttribute("message", "Hi, " + principal.getName()
                    + "!<br> Произошла ошибка доступа. <br> Вернитесь на главную страницу и авторизуйтесь  " +
                     "<br>через вкладку \"Личный кабинет\" :)");
        } else {
            model.addAttribute("msg",
                    "<br> Произошла ошибка доступа. <br> Вернитесь на главную страницу и авторизуйтесь  " +
                            "<br>через вкладку \"Личный кабинет\" :)");
        }
        return "403";
    }

    @RequestMapping("/")
    public ModelAndView welcome() {
        logger.info("Welcome Page.");
        return new ModelAndView("index");
    }

    @RequestMapping("/page-not-found")
    public ModelAndView error404() {
        logger.info("Admin Page.");
        return new ModelAndView("page-not-found");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model ) {
        return "autorization";
    }

    @RequestMapping(value = "/adminInfo", method = RequestMethod.GET)
    public ModelAndView adminPage(Model model) {
        List<VetService> services = service.getAllServices();
        return new ModelAndView("adminInfo", "services", services);
    }

    @RequestMapping("getAllUsers")
    public ModelAndView getAllUsers() {
        logger.info("Getting the all Users.");
        List<User> userList = userService.getAllUsers();
        return new ModelAndView("userList", "userList", userList);
    }
}
