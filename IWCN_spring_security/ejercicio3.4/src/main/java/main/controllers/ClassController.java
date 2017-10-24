package main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import main.model.Product;
import main.services.ProductServiceDB;

@Controller
public class ClassController {
	
	@Autowired
	private ProductServiceDB productService;
	
	@RequestMapping("/")
    public String welcome() {
        return "welcome";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
	
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping("/home")
	public String index() {			
		return "home";
	}
	
    @Secured({"ROLE_ADMIN"})
	@RequestMapping("/addProduct")
	public String addProduct() {			
		return "addProduct";
	}
	
    @Secured({"ROLE_ADMIN"})
	@RequestMapping("/add")
	public String add(Product product) {
		this.productService.add(product);
		return "home";
	}
	
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("products", this.productService.findAll());
		return "list";
	}
	
	@RequestMapping("/show")
	public String show(@RequestParam int numProduct, Model model) {
		Product product = this.productService.get(numProduct);
		model.addAttribute("product", product);
		return "show";
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/delete")
	public String delete(@RequestParam int numProduct, Model model) {
		this.productService.remove(numProduct);
		model.addAttribute("products", this.productService.findAll());
		return "list";
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/editProduct")
	public String editProduct (@RequestParam int numProduct, Model model) {
		Product product = this.productService.get(numProduct);
		model.addAttribute("product", product);
		return "editProduct";
	}

	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/edit")
	public String edit(Product product, Model model) {
		this.productService.add(product);
		model.addAttribute("products", this.productService.findAll());
		return "list";
	}
}