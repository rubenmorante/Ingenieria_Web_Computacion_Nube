package main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import main.objects.Product;
import main.services.ProductService;

@Controller
public class ClassController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/")
	public String index() {			
		return "index";
	}
	
	@RequestMapping("/addProduct")
	public String addProduct() {			
		return "addProduct";
	}
	
	@RequestMapping("/add")
	public String add(Product product) {
		this.productService.add(product);
		return "index";
	}
	
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
	
	@RequestMapping("/delete")
	public String delete(@RequestParam int numProduct, Model model) {
		this.productService.remove(numProduct);
		model.addAttribute("products", this.productService.findAll());
		return "list";
	}
	
	@RequestMapping("/editProduct")
	public String editProduct (@RequestParam int numProduct, Model model) {
		Product product = this.productService.get(numProduct);
		model.addAttribute("product", product);
		return "editProduct";
	}

	@RequestMapping("/edit")
	public String edit(Product product, Model model) {
		this.productService.add(product);
		model.addAttribute("products", this.productService.findAll());
		return "list";
	}
}