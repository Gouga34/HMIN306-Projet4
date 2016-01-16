package com.company.ast.objects;

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
	
	/**
	 * Recherche la variable varName dans les variables locales, puis les paramètres,
	 * puis les attributs de la classe.
	 * @param varName
	 * @return ASTVariable associée, null si pas trouvée
	 */
	private ASTVariable findVariable(String varName) {
		ASTVariable v = this.getLocalVariable(varName);
		if (v == null) {
			v = this.getParameter(varName);
			if (v == null) {
				v = this.getContainerClass().getAttribute(varName);
			}
		}

		return v;
	}

	public void addCalledMethod(String varName, ASTMethod m) throws Exception {
		ASTVariable v = null;

		if (m.getContainerClass().getName().isEmpty()) {
			v = findVariable(varName);
			if (v == null) {
				throw new Exception("Unknown variable " + varName);
			}
		} else {
			v = new ASTVariable("this", m.getContainerClass());
		}

		this.calledMethods.add(new Pair<ASTVariable, ASTMethod>(v, m));
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
				", containerClass=" + containerClass.getName();

		if(returnType != null)
			result += ", \nRETURN : " + returnType.getName();

		for(ASTVariable var : parameters) {
			result += "\nPARAM : " + var.toString();
		}

		if(parameters.size() == 0)
			result += "\nPARAM : " + "void";

		for(ASTVariable local : localVariables) {
			result += "\nLOCAL : " + local.toString();
		}

		for(Pair p : calledMethods) {
			result += "\nCALLED METHOD" +
					"\nvarName : " + p.getValue1()
					+ "\nmethod : " + p.getValue2().toString();
		}


		return result;
	}
}
