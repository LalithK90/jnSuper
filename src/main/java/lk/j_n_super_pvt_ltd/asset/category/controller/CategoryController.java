package lk.j_n_super_pvt_ltd.asset.category.controller;

import lk.j_n_super_pvt_ltd.asset.category.entity.Category;
import lk.j_n_super_pvt_ltd.asset.category.service.CategoryService;
import lk.j_n_super_pvt_ltd.asset.common_asset.model.enums.LiveDead;
import lk.j_n_super_pvt_ltd.asset.item.entity.enums.MainCategory;
import lk.j_n_super_pvt_ltd.asset.item.entity.enums.Weight;
import lk.j_n_super_pvt_ltd.util.interfaces.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/category")
public  class CategoryController implements AbstractController<Category, Integer> {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    private String commonThings(Model model, Category category, Boolean addState) {
        model.addAttribute("mainCategories", MainCategory.values());
        model.addAttribute("category", category);
        model.addAttribute("addStatus", addState);
        return "category/addCategory";
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("categorys", categoryService.findAll().stream()
                .filter(x-> LiveDead.ACTIVE.equals(x.getLiveDead()))
                .collect(Collectors.toList()));
        return "category/category";
    }

    @Override
    public String findById(Integer id, Model model) {
        return null;
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        return commonThings(model, new Category(), true);
    }

    @PostMapping( value = {"/add", "/update"} )
    public String persist(Category category, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        Category name = null;

        if ( category.getName() != null && category.getId() == null ) {
            name = categoryService.findByName(category.getName());
        }
        if ( name != null ) {
            ObjectError error = new ObjectError("category",
                    "Their is Sub Category on same name . System message ");
            bindingResult.addError(error);
        }



        if ( bindingResult.hasErrors() ) {
            return commonThings(model, category, true);
        }

        categoryService.persist(category);
        return "redirect:/category";
    }

    @GetMapping( "/edit/{id}" )
    public String edit(@PathVariable Integer id, Model model) {
        return commonThings(model, categoryService.findById(id), false);
    }

    @GetMapping( "/delete/{id}" )
    public String delete(@PathVariable Integer id, Model model) {
        categoryService.delete(id);
        return "redirect:/category";
    }

    @GetMapping( "/{id}" )
    public String view(@PathVariable Integer id, Model model) {
        model.addAttribute("categoryDetail", categoryService.findById(id));
        return "category/category-detail";
    }
}