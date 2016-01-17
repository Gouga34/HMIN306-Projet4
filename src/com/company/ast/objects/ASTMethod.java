package com.company.ast.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
	
	public void clearParameters() {
		this.parameters.clear();
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

	public int getWeightOfMethod(ASTClass cls) {

		int nb = 0;

		for(Pair<ASTVariable, ASTMethod> called : getCalledMethods())
			if (cls.equals(called.getValue1().getType()))
				nb++;

		return nb;
	}
	
	/**
	 * Recherche la variable varName dans les variables locales, puis les paramètres,
	 * puis les attributs de la classe.
	 * @param varName
	 * @return ASTVariable associée, null si pas trouvée
	 */
	private ASTVariable findVariable(String varName) {
		ASTVariable v = null;
		String[] vars = varName.split(Pattern.quote("."));

		if (vars.length == 1 && vars[0].equals("this")) {
			v = new ASTVariable(varName, getContainerClass());
		} else if (vars.length == 2 && vars[0].equals("this")) {
			v = this.getContainerClass().getAttribute(vars[1]);
		} else {
			v = this.getLocalVariable(varName);
			if (v == null) {
				v = this.getParameter(varName);
				if (v == null) {
					v = this.getContainerClass().getAttribute(varName);
				}
			}
		}

		return v;
	}

	private ASTMethod createMethodWithParameters(ASTMethod m, ASTClass cl) throws Exception {
		ASTMethod newMethod = new ASTMethod(m.getName(), cl);

		List<ASTVariable> args = m.getParameters();
		for (ASTVariable arg : args) {
			// Si le type de l'argument est vide, on cherche la variable
			if (arg.getType() == null || arg.getType().getName().isEmpty()) {
				String s = arg.getName();
				ASTVariable param = findVariable(s);
				if (param == null) {
					//throw new Exception("Unknown variable " + arg.getName());
					break ;
				}

				newMethod.addParameter(param);
			} else {		// Sinon, on ajoute l'arg déjà créé
				newMethod.addParameter(arg);
			}
		}

		return newMethod;
	}

	private void createMethodInClass(ASTMethod calledMethod) throws Exception {
		ASTClass methodClass = calledMethod.getContainerClass();
		methodClass.addMethod(calledMethod);
	}

	public void addCalledMethod(String varName, ASTMethod m) throws Exception {
		ASTVariable v = null;

		// Si on a pas le type du receveur, on le cherche
		if (m.getContainerClass() == null || m.getContainerClass().getName().isEmpty()) {
			v = findVariable(varName);
			if (v == null) {
				//throw new Exception("Unknown variable " + varName);
				return;
			}
		} else {
			v = new ASTVariable("new", m.getContainerClass());
		}

		ASTMethod calledMethod = createMethodWithParameters(m, v.getType());
		ASTMethod existingMethod = v.getType().getMethod(calledMethod);

		if (existingMethod != null) {
			calledMethod = existingMethod;
		} else {
			createMethodInClass(calledMethod);
		}

		this.calledMethods.add(new Pair<ASTVariable, ASTMethod>(v, calledMethod));
	}

	public void setReturnType(ASTClass returnType) {
		this.returnType = returnType;
	}

	public ASTClass getReturnType() {
		return returnType;
	}

	/**
	 * Compare la liste des paramètres des deux méthodes
	 * @param m
	 * @return true si les listes de type sont équivalentes
	 */
	public boolean compareParams(ASTMethod m) {
		for (int i = 0; i < this.parameters.size(); i++) {
			if (i >= m.getParameters().size()) {
				return false;
			}

			String param1 = this.parameters.get(i).getType().getName();
			String param2 = m.getParameters().get(i).getType().getName();

			if (!param1.equals(param2)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public String toString() {

		String result = "\n\nASTMethod{" +
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


		return result + "\n\n";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ASTMethod astMethod = (ASTMethod) o;

        if(name != null)
			if(!name.equals(astMethod.name) || !compareParams(astMethod))
				return false;

		return true;
	}
}
