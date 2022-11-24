package com.blog.demo.exceptions;

public class ResourceAlreadyAvalilableException extends RuntimeException{
	
	private String resourceName;
	
	public  ResourceAlreadyAvalilableException(String resourceName) {
		super(String.format("Resource with ResourceName:  '%s'  already Available", resourceName.trim()));
		this.resourceName = resourceName;
	}
}
