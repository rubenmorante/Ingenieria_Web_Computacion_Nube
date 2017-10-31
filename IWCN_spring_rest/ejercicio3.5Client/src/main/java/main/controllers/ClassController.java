package main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import main.model.Product;
import main.services.DataService;

@Controller
public class ClassController implements ErrorController {
	
	/** Service Element*/
	@Autowired
	private DataService dataService;
	
	/** Denied capture */
	@RequestMapping(value = "/denied")
    public String denied() {
        return "denied";
    }
	
	/** Error capture */
	@RequestMapping(value = "/error")
    public String error() {
        return "error";
    }
	
	/** template Welcome */
	@RequestMapping(value = "/")
    public String welcome() {
        return "welcome";
    }

	/** template Login */
    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }
	
    /** template Home */
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(value = "/home")
	public String home() {			
		return "home";
	}
	
    /** template Add Product */
    @Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/addProduct")
	public String addProduct() {			
		return "addProduct";
	}
	
    /** form add Product */
    @Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/add")
	public String add(Product product) {
		this.dataService.add(product);
		return "home";
	}
	
    /** template List */
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(value = "/list")
	public String list(Model model) {
		model.addAttribute("products", this.dataService.findAll());
		return "list";
	}
	
    /** template Show */
	@RequestMapping(value = "/show/{numProduct}")
	public String show(@PathVariable int numProduct, Model model) {
		Product product = this.dataService.get(numProduct);
		model.addAttribute("product", product);
		return "show";
	}
	
	/** template delete */
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/delete/{numProduct}")
	public String delete(@PathVariable int numProduct, Model model) {
		this.dataService.remove(numProduct);
		model.addAttribute("products", this.dataService.findAll());
		return "list";
	}
	
	/** template Edit Product */
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/editProduct/{numProduct}")
	public String editProduct (@PathVariable int numProduct, Model model) {
		Product product = this.dataService.get(numProduct);
		model.addAttribute("product", product);
		return "editProduct";
	}

	/** form Edit Product */
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/edit")
	public String edit(Product product, Model model) {
		this.dataService.add(product);
		model.addAttribute("products", this.dataService.findAll());
		return "list";
	}

	/** override template Error */
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}
}