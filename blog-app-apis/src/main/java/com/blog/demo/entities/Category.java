package com.blog.demo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter

public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int categoryId;
	
	@NotNull @NotBlank
	@Size(min = 3, max = 20, message = "Category length must be between 3 to 20 characters")
	private String category;
	
	@NotNull @NotBlank
	@Size(min = 5, max = 200, message = "Description length must be between 5 to 200 characters")
	@Column(name = "description")
	private String categoryDescription;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	List<Post> posts = new ArrayList<>();
	
	

}
