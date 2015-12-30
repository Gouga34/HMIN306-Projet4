package com.company.objects;

import java.util.List;

public class ASTClass {
	
	private String name;
	
	private List<ASTVariable> attributes;
	
	private List<ASTMethod> methods;
	
	
	public ASTClass(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ASTVariable getAttribute(String name) {
		for (ASTVariable v : this.attributes) {
			if (v.getName().equals(name)) {
				return v;
			}
		}
		
		return null;
	}
	
	public List<ASTVariable> getAttributes() {
		return this.attributes;
	}
	
	public ASTMethod getMethod(String name) {
		for (ASTMethod m : this.methods) {
			if (m.getName().equals(name)) {
				return m;
			}
		}
		
		return null;
	}
	
	public List<ASTMethod> getMethods() {
		return this.methods;
	}
	
	public void addAttribute(ASTVariable attr) {
		this.attributes.add(attr);
	}
	
	public void addMethod(ASTMethod m) {
		this.methods.add(m);
	}
}
