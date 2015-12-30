package com.company.objects;

import java.util.List;

import com.company.utils.Pair;

public class ASTMethod {

	private String name;
	
	private ASTClass containerClass;
	
	private List<ASTVariable> parameters;
	
	private List<ASTVariable> localVariables;
	
	private List<Pair<ASTVariable, ASTMethod>> calledMethods;
	
	
	public ASTMethod(String name, ASTClass c) {
		this.name = name;
		this.containerClass = c;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ASTClass getContainerClass() {
		return this.containerClass;
	}
	
	public ASTVariable getParameter(String name) {
		for (ASTVariable v : this.parameters) {
			if (v.getName().equals(name)) {
				return v;
			}
		}
		
		return null;
	}
	
	public List<ASTVariable> getParameters() {
		return this.parameters;
	}
	
	public ASTVariable getLocalVariable(String name) {
		for (ASTVariable v : this.localVariables) {
			if (v.getName().equals(name)) {
				return v;
			}
		}
		
		return null;
	}
	
	public List<ASTVariable> getLocalVariables() {
		return this.localVariables;
	}
	
	public List<Pair<ASTVariable, ASTMethod>> getCalledMethods() {
		return this.calledMethods;
	}
	
	public void addParameter(ASTVariable param) {
		this.parameters.add(param);
	}
	
	public void addLocalVariable(ASTVariable var) {
		this.parameters.add(var);
	}
	
	public void removeLocalVariable(String varName) {
		ASTVariable v = this.getLocalVariable(varName);
		if (v != null) {
			this.localVariables.remove(v);
		}
	}
	
	public void addCalledMethod(String varName, ASTMethod m) {
		ASTVariable v = this.getLocalVariable(varName);
		if (v != null) {
			this.calledMethods.add(new Pair<ASTVariable, ASTMethod>(v, m));
		}
	}
}
