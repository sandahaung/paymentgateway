package com.paymentgateway.paymentgateway.controller;

import com.paymentgateway.paymentgateway.domain.Company;
import com.paymentgateway.paymentgateway.service.CompanyService;
import java.util.List;
import javax.annotation.Resource;
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
     * Adds a new company by delegating the processing to CompanyService. Displays
     * a confirmation JSP page
     *
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/companies/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("companyAttribute") Company company) {        

        // The "companyAttribute" model has been passed to the controller from the JSP
        // We use the name "companyAttribute" because the JSP uses that name

        // Call CompanyService to do the actual adding
        companyService.add(company);

        // This will resolve to /WEB-INF/jsp/addedpage.jsp
        return "addedpage";
    }

    /**
     * Deletes an existing company by delegating the processing to CompanyService.
     * Displays a confirmation JSP page
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
}