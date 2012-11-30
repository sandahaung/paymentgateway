package com.paymentgateway.paymentgateway.controller;

import com.paymentgateway.paymentgateway.domain.Company;
import com.paymentgateway.paymentgateway.domain.ConfirmationHash;
import com.paymentgateway.paymentgateway.service.CompanyService;
import com.paymentgateway.paymentgateway.service.ConfirmationHashService;
import com.paymentgateway.paymentgateway.utils.Mailer;
import com.paymentgateway.paymentgateway.utils.NameValidation;
import com.paymentgateway.paymentgateway.utils.RandomStringGenerator;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Handles and retrieves company request
 */
@Controller
@RequestMapping("/company")
public class CompanyController {

    @Resource(name = "companyService")
    private CompanyService companyService;
    @Resource(name = "randomStringGenerator")
    private RandomStringGenerator randomStringGenerator;
    @Resource(name = "confirmationHashService")
    private ConfirmationHashService confirmationHashService;
    @Resource(name = "mailer")
    private Mailer mailer;

    /**
     * Handles and retrieves all companies and show them in a JSP page
     *
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/companies", method = RequestMethod.GET)
    public String getCompanies(Model model) {

        // Retrieve all companies by delegating the call to CompanyService
        List<Company> companies = companyService.getAll();

        // Attach companies to the Model
        model.addAttribute("companies", companies);

        // This will resolve to /WEB-INF/jsp/companiespage.jsp
        return "companiespage";
    }

    /**
     * Retrieves the add page
     *
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/companies/add", method = RequestMethod.GET)
    public String getAdd(Model model) {

        // Create new Company and add to model
        // This is the formBackingOBject
        model.addAttribute("companyAttribute", new Company());

        // This will resolve to /WEB-INF/jsp/addpage.jsp
        return "addpage";
    }

    /**
     * Adds a new company by delegating the processing to CompanyService.
     * Displays a confirmation JSP page
     *
     * @return the name of the JSP page
     */
    //@RequestMapping(value = "/companies/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("companyAttribute") Company company) {

        // The "companyAttribute" model has been passed to the controller from the JSP
        // We use the name "companyAttribute" because the JSP uses that name

        // Call CompanyService to do the actual adding
        companyService.add(company);

        // This will resolve to /WEB-INF/jsp/addedpage.jsp
        return "addedpage";
    }

    @RequestMapping(value = "/companies/add", method = RequestMethod.POST)
    public String handleCreateCompany(@ModelAttribute("companyAttribute") Company company, HttpServletRequest request) {
        companyService.add(company);
        String baseUrl = String.format("%s://%s:%d/paymentgateway/", request.getScheme(), request.getServerName(), request.getServerPort());
        String randomString = randomStringGenerator.nextRandomString();
        String confirmUrl = baseUrl + "company/confirm/" + randomString;
        ConfirmationHash confirmationHash = new ConfirmationHash();
        confirmationHash.setType(1);
        confirmationHash.setIdToConfirm(company.getId());
        confirmationHash.setHash(randomString);
        confirmationHashService.add(confirmationHash);
        mailer.sendMail("sandahaung@gmail.com", company.getCompanyEmail(), "Confirm Email", confirmUrl);
        //return "confirmationsent";
        return "addedpage";
    }

    /**
     * Deletes an existing company by delegating the processing to
     * CompanyService. Displays a confirmation JSP page
     *
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/companies/delete/id/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id,
            Model model) {

        // Call CompanyService to do the actual deleting
        companyService.delete(id);

        // Add id reference to Model
        model.addAttribute("id", id);

        // This will resolve to /WEB-INF/jsp/deletedpage.jsp
        return "deletedpage";
    }

    /**
     * Retrieves the edit page
     *
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/companies/edit/id/{id}", method = RequestMethod.GET)
    public String getEdit(@PathVariable("id") Long id,
            Model model) {
        // Retrieve existing Company and add to model
        // This is the formBackingOBject
        model.addAttribute("companyAttribute", companyService.get(id));

        // This will resolve to /WEB-INF/jsp/editpage.jsp
        return "editpage";
    }

    /**
     * Edits an existing company by delegating the processing to CompanyService.
     * Displays a confirmation JSP page
     *
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/companies/edit/id/{id}", method = RequestMethod.POST)
    public String saveEdit(@ModelAttribute("companyAttribute") Company company,
            @PathVariable("id") Long id,
            Model model) {
        // The "companyAttribute" model has been passed to the controller from the JSP
        // We use the name "companyAttribute" because the JSP uses that name

        // We manually assign the id because we disabled it in the JSP page
        // When a field is disabled it will not be included in the ModelAttribute
        company.setId(id);

        // Delegate to CompanyService for editing
        companyService.edit(company);

        // Add id reference to Model
        model.addAttribute("id", id);

        // This will resolve to /WEB-INF/jsp/editedpage.jsp
        return "editedpage";
    }
    
    @RequestMapping(value = "/confirm/{confirmHash}", method = RequestMethod.GET)
    public String handleConfirm(Model model,
            @PathVariable("confirmHash") String confirmHash) {        

        // Delegate to ConfirmationHashService for editing
        ConfirmationHash hashToVerify = confirmationHashService.getByHash(confirmHash);
        
        if (hashToVerify == null)
            model.addAttribute("confirmationMessage", "Sorry. User's email cannot be verified.");
        else if (hashToVerify.getType() == 1) {
            Company companyToConfirm = companyService.get(hashToVerify.getIdToConfirm());
            companyToConfirm.setEnabled(true);
            companyService.update(companyToConfirm);
            confirmationHashService.delete(hashToVerify.getId());
            model.addAttribute("confirmationMessage", "Company's email has been verified.");            
        }

        // This will resolve to /WEB-INF/jsp/confirmedpage.jsp
        return "confirmedpage";
    }
    
    @RequestMapping(value = "/verify/username/{username}", method = RequestMethod.GET)
    public @ResponseBody NameValidation verifyUsernameAjax(@PathVariable("username") String username) {
        username = username.trim();
        ConfirmationHash hash = new ConfirmationHash();
        hash.setHash(username + new java.util.Date());
        hash.setIdToConfirm(-1);
        hash.setType(-1);
        confirmationHashService.add(hash);
        NameValidation nameValidation = new NameValidation();
        if (username.isEmpty()) {
            nameValidation.setExists(true);
            nameValidation.setMessage("Blank usernames cannot be used");
            return nameValidation;
        }
        Company company = companyService.getByUsername(username);
        if (company != null) {
            nameValidation.setExists(true);
            nameValidation.setMessage("The username exists");
        } else {
            nameValidation.setExists(false);
            nameValidation.setMessage("The username can be used");
        }        
        return nameValidation;
    }
}