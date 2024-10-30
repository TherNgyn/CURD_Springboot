package vn.iostar.model;

import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryModel implements Serializable {

    private static final long serialVersionUID = 6000171644351270487L;

    @Id
    private Long id;

    @NotEmpty(message = "khong duoc bo trong")
    private String name;

    private String images;
    private int status;
	private boolean isEdit = false;
	
}