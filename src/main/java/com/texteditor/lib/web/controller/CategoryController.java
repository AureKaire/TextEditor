package com.texteditor.lib.web.controller;

import com.texteditor.lib.model.Category;
import com.texteditor.lib.service.CategoryService;
import com.texteditor.lib.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

  
    @RequestMapping("/categories")
    public String listCategories(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories",categories);
        return "category/index";
    }

    // Form for adding a new category
    @RequestMapping("categories/add")
    public String formNewCategory(Model model) {
        if(!model.containsAttribute("category")) {
            model.addAttribute("category",new Category());
        }
        model.addAttribute("action","/categories");
        model.addAttribute("heading","New Text");
        model.addAttribute("submit","Add");

        return "category/form";
    }

    // Form for editing an existing category
    @RequestMapping("categories/{categoryId}/edit")
    public String formEditCategory(@PathVariable Long categoryId, Model model) {
        if(!model.containsAttribute("category")) {
            model.addAttribute("category",categoryService.findById(categoryId));
        }
        model.addAttribute("action",String.format("/categories/%s",categoryId));
        model.addAttribute("heading","Edit Text");
        model.addAttribute("submit","Update");

        return "category/form";
    }

    // Update an existing category
    @RequestMapping(value = "/categories/{categoryId}", method = RequestMethod.POST)
    public String updateCategory(@Valid Category category, BindingResult result, RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category",result);
            redirectAttributes.addFlashAttribute("category",category);
            return String.format("redirect:/categories/%s/edit",category.getId());
        }

        categoryService.save(category);

        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Text successfully updated!", FlashMessage.Status.SUCCESS));
        return "redirect:/categories";
    }

    // Add a category
    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public String addCategory(@Valid Category category, BindingResult result, RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category",result);
            redirectAttributes.addFlashAttribute("category",category);
            return "redirect:/categories/add";
        }

        categoryService.save(category);
        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Text successfully added!", FlashMessage.Status.SUCCESS));
        return "redirect:/categories";
    }

    // Delete existing category
    @RequestMapping(value = "/categories/{categoryId}/delete", method = RequestMethod.POST)
    public String deleteCategory(@PathVariable Long categoryId, RedirectAttributes redirectAttributes) {
        Category cat = categoryService.findById(categoryId);
        categoryService.delete(cat);
        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Deleted!", FlashMessage.Status.SUCCESS));

        return "redirect:/categories";
    }
}
