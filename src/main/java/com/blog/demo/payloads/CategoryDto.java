package com.blog.demo.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@ToString
public class CategoryDto {

	private Long id;
	private String category;
	private String categoryDescription;

	public CategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryDto(Long id, String category, String categoryDescription) {
		super();
		this.id = id;
		this.category = category;
		this.categoryDescription = categoryDescription;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	@Override
	public String toString() {
		return "CategoryDto [id=" + id + ", category=" + category + ", categoryDescription=" + categoryDescription
				+ "]";
	}

}
