package com.example.testbackendmid.controller;

import com.example.testbackendmid.model.Product;
import com.example.testbackendmid.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public ModelAndView findAll(){
    ModelAndView modelAndView = new ModelAndView("list");
modelAndView.addObject("list", productRepository.findAll());
    return modelAndView;
    }

    @GetMapping("/info/{id}")
    public ModelAndView showInfo(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("info");
        modelAndView.addObject("item", productRepository.findById(id).get());
        return modelAndView;
    }
    @GetMapping("/add")
    public String showFormAdd() {


        return "create";
    }
    @PostMapping("/add")
    public String add(Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("item", productRepository.findById(id).get());
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        productRepository.deleteById(id);
        return modelAndView;
    }
    @GetMapping("/search")
    public ModelAndView search(String keyword){
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("list", productRepository.findAllByNameContainingIgnoreCase(keyword));
        return modelAndView;
    }

}
