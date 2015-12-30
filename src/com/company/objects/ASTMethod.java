package com.company.objects;

import java.util.ArrayList;
import java.util.List;

import com.company.utils.Pair;

public class ASTMethod {

	private String name;
	
	private ASTClass containerClass;
	
	private List<ASTVariable> parameters = new ArrayList<ASTVariable>();
	
	private List<ASTVariable> localVariables = new ArrayList<ASTVariable>();
	
	private List<Pair<ASTVariable, ASTMethod>> calledMethods = new ArrayList<Pair<ASTVariable, ASTMethod>>();

	private ASTClass returnType;
	
	
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
		this.localVariables.add(var);
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

	public void setReturnType(ASTClass returnType) {
		this.returnType = returnType;
	}

	public ASTClass getReturnType() {
		return returnType;
	}

	@Override
	public String toString() {
		String result = "ASTMethod{" +
				"name='" + name + '\'' +
				", containerClass=" + containerClass.getName() +
				", \nRETURN : " + returnType.getName();

		for(ASTVariable var : parameters) {
			result += "\nPARAM : " + var.toString();
		}

		if(parameters.size() == 0)
			result += "\nPARAM : " + "void";

		for(ASTVariable local : localVariables) {
			result += "\nLOCAL : " + local.toString();
		}


		return result;
	}
}
