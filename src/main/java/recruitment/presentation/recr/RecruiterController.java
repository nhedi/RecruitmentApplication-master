package recruitment.presentation.recr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import recruitment.application.RecruiterService;
import sun.rmi.runtime.Log;
//import recruitment.domain.PersonDTO;


@Controller
@Scope("session")
public class RecruiterController {
    static final String DEFAULT_PAGE_URL = "/";
    static final String REGISTER_PAGE_URL = "register";
    static final String LOGIN_PAGE_URL = "login";
    static final String REDIRECT_LOGIN_PAGE_URL = "redirect-login";

    private static final String CURRENT_REG_OBJ_NAME = "currentRegistration";
    private static final String REGISTER_FORM_OBJ_NAME = "registerForm";
    private static final String LOGIN_FORM_OBJ_NAME = "loginForm";


    @Autowired
    private RecruiterService service;
    //private PersonDTO currentRole;

    @GetMapping(DEFAULT_PAGE_URL)
    public String showDefaultView() {
        return "redirect:" + REGISTER_PAGE_URL;
    }

    @GetMapping("/" + REGISTER_PAGE_URL)
    public String showRegisterPageView(RegisterForm registerForm) {
        return REGISTER_PAGE_URL;
    }

    @GetMapping("/" + LOGIN_PAGE_URL)
    public String showLoginPageView(Model model) { return showLoginPage(model, new LoginForm()); }

    private String showLoginPage(Model model, LoginForm loginForm) {
        if(loginForm != null) {
            model.addAttribute(LOGIN_FORM_OBJ_NAME, loginForm);
        }
        return LOGIN_PAGE_URL;
    }

    @PostMapping("/" + REGISTER_PAGE_URL)
    public String sendRegistration(@Valid RegisterForm registerForm, BindingResult bindingResult, Model model) {
        if(!bindingResult.hasErrors()) {

//            System.out.println(service.findRole(1));
//            System.out.println(service.findName(2));
//            System.out.println(service.findCompetence(2));
//            System.out.println(service.findAvailabilityByPid(2));
//            System.out.println(service.getExperienceByPid(2) + " ");

            if (!registerForm.getPassword().equals(registerForm.getConfirmPwd())) {
                bindingResult.rejectValue("confirmPwd", null, "Passwords do not match, try again.");

            } else {
                
                service.registerUser(registerForm.getFname(), registerForm.getLname(), registerForm.getEmail(),
                            registerForm.getSsn(), registerForm.getUsername(), registerForm.getPassword());

                System.out.println("***************************");
//                System.out.println(service.findName(3));
                LoginForm loginForm = new LoginForm();
                loginForm.setUsername(registerForm.getUsername());

                return showLoginPage(model, loginForm);
            }

        } else {

            if (service.checkUsername(registerForm.getUsername()) == true) {
                bindingResult.rejectValue("email", null, "There is already an account registered with that email");
            }
            if (service.checkSsn(registerForm.getSsn()) == true) {
                bindingResult.rejectValue("ssn", null, "There is already an account registered with that social security number");
            }
            if (!registerForm.getPassword().equals(registerForm.getConfirmPwd())) {
                bindingResult.rejectValue("confirmPwd", null, "Passwords do not match, try again.");
            }
        }

        return REGISTER_PAGE_URL;
    }

    @PostMapping("/" + LOGIN_PAGE_URL)
    public String sendLogin(@Valid LoginForm loginForm, BindingResult bindingResult, Model model) {
        if(!bindingResult.hasErrors()) {
            //kolla i databasen om användarnamn och lösenord matchar
        } else {
            if (service.checkUsername(loginForm.getUsername()) == false) {
                bindingResult.rejectValue("username", null, "There is no such username.");
            }
        }
//        if (bindingResult.hasErrors()) {
//            model.addAttribute(CURRENT_REG_OBJ_NAME, new RegisterForm());
//            return LOGIN_PAGE_URL;
//        }
//
//        String from = registerForm.getFrom();
//        double rate = registerForm.getRate();
//        String to = registerForm.getTo();
//        service.setNewRate(rate,from + "" + to);
//        currentConv.setNewRate(rate);
//        int count = service.countSum();
//        registerForm.setTotalCount(count);
//
//        if (currentConv == null) {
//            model.addAttribute(ExceptionHandlers.ERROR_TYPE_KEY, ExceptionHandlers.NO_CONVERSION_FOUND_FOR_UPDATE);
//            model.addAttribute(ExceptionHandlers.ERROR_INFO_KEY, ExceptionHandlers.NO_CONVERSION_FOUND_FOR_UPDATE_INFO);
//            return ExceptionHandlers.ERROR_PAGE_URL;
        //}
        return LOGIN_PAGE_URL;
    }

}