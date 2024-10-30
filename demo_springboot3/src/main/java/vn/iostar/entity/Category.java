package vn.iostar.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="category")
public class Category implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8803536966776451311L; //so dinh danh
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="categoryid")
	private Long id;
	@Column(name="categoryname", columnDefinition = "nvarchar(50)")
	@NotEmpty(message = "khong duoc bo trong")
	private String name;
	@Column(name="images", columnDefinition = "nvarchar(500)")
	private String images;
	private int status;
	
	
}
