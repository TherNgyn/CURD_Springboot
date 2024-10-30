package vn.iostar.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import jakarta.validation.Valid;
import vn.iostar.entity.Category;
import vn.iostar.model.CategoryModel;
import vn.iostar.service.CategoryService;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {
	@Autowired 
	CategoryService categoryService;
	
	@RequestMapping("")
	public String all(Model model, Pageable pageable) {
		Page<Category> categoryPage = categoryService.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("name")));
        model.addAttribute("categoryPage", categoryPage);
			
		return "admin/category/list";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		CategoryModel cateModel = new CategoryModel();
		cateModel.setEdit(false);
		model.addAttribute("category", cateModel);
		return "admin/category/add";
	}
	@PostMapping("/save")
	public ModelAndView saveOrUpdate(ModelMap model,
			@Valid @ModelAttribute("category") CategoryModel cateModel, BindingResult result) {
		if(result.hasErrors()) {
			return new ModelAndView("admin/category/add");
		}
		Category entity = new Category();
		// Từ Model sang entity
		BeanUtils.copyProperties(cateModel, entity);
		// gọi hàm save trong ser
		categoryService.save(entity);
		// message
		String mess ="";
		if(cateModel.isEdit()==true) {
			mess = "Category is edited";
		}
		else {
			mess = "Category is saved";
		}
		model.addAttribute("message", mess);
		// redirect về URL controller
		return new ModelAndView("forward:/admin/categories", model);
	}
	@GetMapping("/edit/{id}")
	public ModelAndView edit(ModelMap model, @PathVariable("id") Long categoryId) {
		Optional<Category> optCategory = categoryService.findById(categoryId);
		CategoryModel cateModel = new CategoryModel();
		if(optCategory.isPresent()) {
			Category entity = optCategory.get();
			BeanUtils.copyProperties(entity, cateModel);
			cateModel.setEdit(true);
			model.addAttribute("category", cateModel);
			return new ModelAndView("admin/category/add", model);
			
		}
		model.addAttribute("message", "Category is not existed");
		return new ModelAndView("forward:/admin/categories", model);
	}
	@GetMapping("/delete/{id}")
	public ModelAndView delete(ModelMap model, @PathVariable("id") Long categoryId) {
		categoryService.deleteById(categoryId);
		model.addAttribute("message", "category is deleted");
		return new ModelAndView("redirect:/admin/categories", model);
	}

	@GetMapping("/searchpaginated")
	public String search(ModelMap model,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int count = (int) categoryService.count();
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(3);
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
		Page<Category> resultPage = null;
		if (StringUtils.hasText(name)) {
			resultPage = categoryService.findByNameContaining(name, pageable);
			model.addAttribute("name", name);
		} else {
			resultPage = categoryService.findAll(pageable);
		}
		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);
			if (totalPages > count) {
				if (end == totalPages)
					start = end - count;
				else if (start == 1)
					end = start + count;
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("categoryPage", resultPage);
		return "admin/category/list";
	}
	
}
