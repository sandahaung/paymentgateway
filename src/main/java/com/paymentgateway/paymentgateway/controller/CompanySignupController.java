package com.paymentgateway.paymentgateway.controller;

import com.paymentgateway.paymentgateway.domain.Company;
import com.paymentgateway.paymentgateway.domain.ConfirmationHash;
import com.paymentgateway.paymentgateway.service.CompanyService;
import com.paymentgateway.paymentgateway.service.ConfirmationHashService;
import com.paymentgateway.paymentgateway.utils.Mailer;
import com.paymentgateway.paymentgateway.utils.RandomStringGenerator;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/company")
public class CompanySignupController {
    
    @Resource(name = "randomStringGenerator")
    private RandomStringGenerator randomStringGenerator;
    @Resource(name = "companyService")
    private CompanyService companyService;
    @Resource(name = "confirmationHashService")
    private ConfirmationHashService confirmationHashService;
    @Resource(name = "mailer")
    private Mailer mailer;
    
    @RequestMapping(value = "/companies/add", method = RequestMethod.POST)
    public String handleCreateCompany(@ModelAttribute("companyAttribute") Company company, HttpServletRequest request) {
        companyService.add(company);
        String baseUrl = String.format("%s://%s:%d/paymentgateway/",request.getScheme(),  request.getServerName(), request.getServerPort());
        String randomString = randomStringGenerator.nextRandomString();
        String confirmUrl = baseUrl + "company/confirm/" + randomString;
        ConfirmationHash confirmationHash = new ConfirmationHash();
        confirmationHash.setType(1);
        confirmationHash.setHash(randomString);
        confirmationHashService.add(confirmationHash);
        mailer.sendMail("sandahaung@gmail.com", company.getCompanyEmail(), "Confirm Email", confirmUrl);
        //return "confirmationsent";
        return "addedpage";
    }
    
}
