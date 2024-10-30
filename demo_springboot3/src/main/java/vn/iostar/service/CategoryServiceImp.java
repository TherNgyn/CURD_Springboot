package vn.iostar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import vn.iostar.entity.Category;
import vn.iostar.reponsitory.CategoryReponsitory;

@Service
public class CategoryServiceImp implements CategoryService {
	
	@Override
	public Page<Category> findByNameContaining(String name, Pageable pageable) {
		return categoryReponsitory.findByNameContaining(name, pageable);
	}

	@Autowired
	CategoryReponsitory categoryReponsitory;
	
	public CategoryServiceImp(CategoryReponsitory categoryReponsitory) {
		this.categoryReponsitory = categoryReponsitory;
	}

	@Override
	public Optional<Category> findByName(String name) {
		return categoryReponsitory.findByName(name);
	}

	@Override
	public List<Category> findAll(Sort sort) {
		return categoryReponsitory.findAll(sort);
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		return categoryReponsitory.findAll(pageable);
	}

	@Override
	public <S extends Category> S save(S entity) {
		return categoryReponsitory.save(entity);
	}

	@Override
	public List<Category> findAll() {
		return categoryReponsitory.findAll();
	}

	@Override
	public Optional<Category> findById(Long id) {
		return categoryReponsitory.findById(id);
	}

	@Override
	public long count() {
		return categoryReponsitory.count();
	}

	@Override
	public void deleteById(Long id) {
		categoryReponsitory.deleteById(id);
	}
}
