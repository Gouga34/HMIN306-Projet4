package com.company.ast.objects;

public class ASTVariable {

	private String name;
	
	private ASTClass type;
	
	
	public ASTVariable(String name, ASTClass type) {
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ASTClass getType() {
		return this.type;
	}

	@Override
	public String toString() {
		return "ASTVariable{" +
				"name='" + name + '\'' +
				", type=" + type.getName() +
				'}';
	}
}
