package recruitment.presentation.conv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import recruitment.application.RecruiterService;
//import recruitment.domain.PersonDTO;


@Controller
@Scope("session")
public class RecruiterController {
    static final String DEFAULT_PAGE_URL = "/";
    static final String REGISTER_PAGE_URL = "register";
    static final String UPDATE_RATE_URL = "update-rate";

    private static final String CURRENT_REG_OBJ_NAME = "currentRegistration";
    private static final String REGISTER_FORM_OBJ_NAME = "registerForm";

    private String conversionResult;

    @Autowired
    private RecruiterService service;
    //private PersonDTO currentRole;

    @GetMapping(DEFAULT_PAGE_URL)
    public String showDefaultView() {
        return "redirect:" + REGISTER_PAGE_URL;
    }

    @GetMapping("/" + REGISTER_PAGE_URL)
    public String showCurrencySelectionView(RegisterForm registerForm) {
        return REGISTER_PAGE_URL;
    }

    private String showConversionResultPage(Model model) {
//        if (currentRole != null) {
//            model.addAttribute(CURRENT_REG_OBJ_NAME, currentRole);
//        }
        return REGISTER_PAGE_URL;
    }

    @PostMapping("/" + REGISTER_PAGE_URL)
    public String findExchangeRate(@Valid RegisterForm registerForm, BindingResult bindingResult, Model model) {

//    System.out.println(service.findRole(1));
//    System.out.println(service.findName(2));
//
//    System.out.println(service.findCompetence(2));
//    System.out.println(service.findAvailabilityByPid(2));
//    System.out.println(service.getExperienceByPid(2) + " ");

    boolean result = true;

    if(registerForm.getPassword().equals(registerForm.getConfirmPwd())) {
        if(service.checkUsername(registerForm.getUsername()) == true) {
            bindingResult.rejectValue("username", null, "There is already an accound registred with that username");
            result = false;
        }
        if(service.checkEmail(registerForm.getEmail()) == true) {
            bindingResult.rejectValue("email", null, "There is already an accound registred with that email");
            result = false;
        }
        if(service.checkSsn(registerForm.getSsn()) == true) {
            bindingResult.rejectValue("ssn", null, "There is already an accound registreed with that social security number");
            result = false;
        }
        if(result) {
            System.out.println("passed all tests");
            service.registerUser(registerForm.getFname(), registerForm.getLname(), registerForm.getEmail(),
                    registerForm.getSsn(), registerForm.getUsername(), registerForm.getPassword());
        }
        }
        //System.out.println(service.findName(3));



//        if (bindingResult.hasErrors()) {
//            model.addAttribute(REGISTER_FORM_OBJ_NAME, new RegisterForm());
//            return REGISTER_PAGE_URL;
//        }
//        String from = registerForm.getFrom();
//        int amount = registerForm.getNumber();
//
//        String to = registerForm.getTo();
//        currentRole = service.findRole(from + "" + to);
//        double rate = currentRole.getRate();
//        registerForm.setConversionResult(rate * amount);
//        conversionResult = registerForm.getConversionResult();
//        int newCount = currentRole.getCount() + 1;
//        service.setNewCount(newCount, from + "" + to);
//
//        if (currentRole == null) {
//            model.addAttribute(ExceptionHandlers.ERROR_TYPE_KEY, ExceptionHandlers.NO_CONVERSION_FOUND);
//            model.addAttribute(ExceptionHandlers.ERROR_INFO_KEY, ExceptionHandlers.NO_CONVERSION_FOUND_INFO);
//            return ExceptionHandlers.ERROR_PAGE_URL;
//        }
        return showConversionResultPage(model);
    }

    @GetMapping("/" + UPDATE_RATE_URL)
    public String showUpdateRateView(RegisterForm registerForm) {
//        int count = service.countSum();
//        registerForm.setTotalCount(count);
        return UPDATE_RATE_URL;
    }

    private String showUpdateRateResultPage(Model model) {
//        if (currentConv != null) {
//            model.addAttribute(CURRENT_REG_OBJ_NAME, currentConv);
//        }
        return UPDATE_RATE_URL;
    }

    @PostMapping("/" + UPDATE_RATE_URL)
    public String setExchangeRate(@Valid RegisterForm registerForm, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute(CURRENT_REG_OBJ_NAME, new RegisterForm());
//            return UPDATE_RATE_URL;
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
        return showUpdateRateResultPage(model);
    }

}