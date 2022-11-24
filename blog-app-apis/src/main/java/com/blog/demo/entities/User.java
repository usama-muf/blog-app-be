package com.blog.demo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank @NotNull
	@Size(min = 5, message = "UserName must be Min of 5 characters")
	@Size(max = 20, message = "UserName must be Maximum of 20 characters")
	private String name;
	
	@Email(message = "Invalid Email Id")
	@Size(min = 5, message = "Email must be min of 5 characters")
	private String email;
	
	@NotBlank @NotNull 
	@Size(min = 8, message = "Password must be Min of 8 characters")
	@Size(max = 20, message = "Password must be Maximum of 20 characters")
	private String password;
	
	
	@NotNull
	private String about;
	
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
	List<Post> posts = new ArrayList<>();
	

}
