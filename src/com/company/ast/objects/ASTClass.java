package com.company.ast.objects;

import com.company.utils.Pair;

import java.util.ArrayList;
import java.util.List;

public class ASTClass {
	
	private String name;
	
	private List<ASTVariable> attributes = new ArrayList<ASTVariable>();
	
	private List<ASTMethod> methods = new ArrayList<ASTMethod>();
	
	
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
	
	public ASTMethod getMethod(ASTMethod method) {
		for (ASTMethod m : this.methods) {
			if (m.getName().equals(method.getName()) && m.compareParams(method)) {
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
		ASTMethod existingMethod = getMethod(m);



		if (existingMethod == null) {
			this.methods.add(m);
		} else {
			existingMethod.clearParameters();
			List<ASTVariable> params = m.getParameters();

			for (ASTVariable param : params) {
				existingMethod.addParameter(param);
			}

			existingMethod.setReturnType(m.getReturnType());
			existingMethod.getLocalVariables().clear();
			for(ASTVariable var : m.getLocalVariables()) {
				existingMethod.addLocalVariable(var);
			}

			existingMethod.getCalledMethods().clear();
			for(Pair<ASTVariable, ASTMethod> pair : m.getCalledMethods()) {
				try {
					existingMethod.addCalledMethod(pair.getValue1().getName(), pair.getValue2());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ASTClass astClass = (ASTClass) o;

		if (!name.equals(astClass.name)) return false;

		return true;
	}

}
