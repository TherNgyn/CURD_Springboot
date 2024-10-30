package vn.iostar.reponsitory;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import vn.iostar.entity.Category;

// Chỉ có thêm, xóa, sửa cho CRUDRepository

// T là entity

// ID là kiểu dữ liệu của khóa chính
public interface CategoryReponsitory extends JpaRepository<Category, Long> {
    
    // Trường name viết thường, sang đây viết hoa
    /* @Query() */
    Optional<Category> findByName(String name);
    Page<Category> findByNameContaining(String name, Pageable pageable);
}