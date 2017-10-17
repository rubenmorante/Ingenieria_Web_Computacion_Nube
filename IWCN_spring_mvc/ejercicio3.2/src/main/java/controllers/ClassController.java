package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import services.Product;
import services.ProductService;

@Controller
public class ClassController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/")
	public String tablon(Model model) {			
		return "index";
	}
	
	@RequestMapping("/add")
	public String add(Product product) {
		this.productService.add(product);
		return "index";
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("products", this.productService.getList());	
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
		model.addAttribute("products", this.productService.getList());
		return "list";
	}

}