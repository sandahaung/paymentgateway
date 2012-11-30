package com.paymentgateway.paymentgateway.controller;

import com.paymentgateway.paymentgateway.domain.Customer;
import com.paymentgateway.paymentgateway.service.CustomerService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles and retrieves customer request
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    
    @Resource(name = "customerService")
    private CustomerService customerService;

    /**
     * Handles and retrieves all customers and show them in a JSP page
     *
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String getCustomers(Model model) { 
                
        // Retrieve all customers by delegating the call to CustomerService
        List<Customer> customers = customerService.getAll();
        
        // Attach companies to the Model
        model.addAttribute("customers", customers);

        // This will resolve to /WEB-INF/jsp/companiespage.jsp
        return "customerspage";
    }

    /**
     * Retrieves the add page
     *
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/customers/add", method = RequestMethod.GET)
    public String getAdd(Model model) {        

        // Create new Customer and add to model
        // This is the formBackingOBject
        model.addAttribute("customerAttribute", new Customer());

        // This will resolve to /WEB-INF/jsp/addpagecustomer.jsp
        return "addpagecustomer";
    }

    /**
     * Adds a new customer by delegating the processing to CustomerService. Displays
     * a confirmation JSP page
     *
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/customers/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("customerAttribute") Customer customer) {        

        // The "customerAttribute" model has been passed to the controller from the JSP
        // We use the name "ccustomerAttribute" because the JSP uses that name

        // Call CustomerService to do the actual adding
        customerService.add(customer);

        // This will resolve to /WEB-INF/jsp/addedpagecustomer.jsp
        return "addedpagecustomer";
    }

    /**
     * Deletes an existing company by delegating the processing to CustomerService.
     * Displays a confirmation JSP page
     *
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/customers/delete/id/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id,
            Model model) {        

        // Call CustomerService to do the actual deleting
        customerService.delete(id);

        // Add id reference to Model
        model.addAttribute("id", id);

        // This will resolve to /WEB-INF/jsp/deletedpagecustomer.jsp
        return "deletedpagecustomer";
    }

    /**
     * Retrieves the edit page
     *
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/customers/edit/id/{id}", method = RequestMethod.GET)
    public String getEdit(@PathVariable("id") Long id,
            Model model) {      
        // Retrieve existing Customer and add to model
        // This is the formBackingOBject
        model.addAttribute("customerAttribute", customerService.get(id));

        // This will resolve to /WEB-INF/jsp/editpagecustomer.jsp
        return "editpagecustomer";
    }

    /**
     * Edits an existing customer by delegating the processing to CustomerService.
     * Displays a confirmation JSP page
     *
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/customers/edit/id/{id}", method = RequestMethod.POST)
    public String saveEdit(@ModelAttribute("customerAttribute") Customer customer,
            @PathVariable("id") Long id,
            Model model) {       
        // The "customerAttribute" model has been passed to the controller from the JSP
        // We use the name "customerAttribute" because the JSP uses that name

        // We manually assign the id because we disabled it in the JSP page
        // When a field is disabled it will not be included in the ModelAttribute
        customer.setId(id);

        // Delegate to CustomerService for editing
        customerService.edit(customer);

        // Add id reference to Model
        model.addAttribute("id", id);

        // This will resolve to /WEB-INF/jsp/editedpagecustomer.jsp
        return "editedpagecustomer";
    }
}
