package vn.iostar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import vn.iostar.entity.Category;
import vn.iostar.reponsitory.CategoryReponsitory;

public interface CategoryService {

	void deleteById(Long id);

	long count();

	Optional<Category> findById(Long id);

	List<Category> findAll();

	<S extends Category> S save(S entity);

	Page<Category> findAll(Pageable pageable);

	List<Category> findAll(Sort sort);

	Optional<Category> findByName(String name);

	Page<Category> findByNameContaining(String name, Pageable pageable);

	

}
