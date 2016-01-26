package com.company.ast;

import com.company.ast.objects.ASTClass;
import com.company.ast.objects.ASTMethod;
import com.company.ast.objects.ASTVariable;

import org.eclipse.jdt.core.dom.*;

import java.util.List;

public class ASTUnit {
	
	private CompilationUnit compilationUnit;
	private TypeDeclaration typeDeclaration;

	private ASTClass unitClass;
	
	public ASTUnit(CompilationUnit cu) {
		compilationUnit = cu;

		if(compilationUnit.types().size() > 0)
			typeDeclaration = ((TypeDeclaration) compilationUnit.types().get(0));
		else
			typeDeclaration = null;
	}

	/**
	 * Permet de gérer les différents attributs de notre classe
	 */
	private void registerAttributes() {
		FieldDeclaration fields[] = typeDeclaration.getFields();
		for (FieldDeclaration field : fields) {
			String type = field.getType().toString();
			String name = ((VariableDeclarationFragment) field.fragments().get(0)).getName().toString();
			ASTClass klass = new ASTClass(type);
			ASTVariable att = new ASTVariable(name, klass);
			unitClass.addAttribute(att);
		}
	}

	/**
	 * Permet de gérer les différentes méthodes de notre classe
	 */
	private void registerMethods() {
		MethodDeclaration methods[] = typeDeclaration.getMethods();

		for (MethodDeclaration method : methods) {

			ASTMethod md = new ASTMethod(method.getName().toString(), unitClass);

			Type typeReturn = method.getReturnType2();

			if(typeReturn != null) {

				md.setReturnType(new ASTClass(typeReturn.toString()));

				addParamOfMethod(method, md);

				Block block = method.getBody();

				if(block != null) {

					MethodInvocationVisitor miv = new MethodInvocationVisitor();
					block.accept(miv);

					registerLocalVariables(md, miv);

					registerCalledMethods(md, miv);
				}

				unitClass.addMethod(md);
			}
		}
	}

	/**
	 *
	 * @param method
	 * @param md
	 */
	private void addParamOfMethod(MethodDeclaration method, ASTMethod md) {
		for (Object param : method.parameters()) {
			VariableDeclaration variableDeclaration = (VariableDeclaration) param;
			String type = variableDeclaration.getStructuralProperty(SingleVariableDeclaration.TYPE_PROPERTY).toString();
			ASTVariable var = new ASTVariable(variableDeclaration.getName().toString(), new ASTClass(type));
			md.addParameter(var);
		}
	}
	
	private void registerLocalVariables(ASTMethod md, MethodInvocationVisitor miv) {

		List<VariableDeclarationStatement> variableDeclarations = miv.getVariableDeclarations();
		for (VariableDeclarationStatement var : variableDeclarations) {
			String name = ((VariableDeclarationFragment) var.fragments().get(0)).getName().toString();
			String type = var.getType().toString();
			ASTVariable local = new ASTVariable(name, new ASTClass(type));
			md.addLocalVariable(local);
		}

		List<VariableDeclarationExpression> variableDeclarationExpressions = miv.getDeclarations();
		for(VariableDeclarationExpression var : variableDeclarationExpressions) {
			String name = ((VariableDeclarationFragment) var.fragments().get(0)).getName().toString();
			String type = var.getType().toString();
			ASTVariable local = new ASTVariable(name, new ASTClass(type));
			md.addLocalVariable(local);
		}

		List<SingleVariableDeclaration> singleVariableDeclarations = miv.getSingleDeclarations();
		for(SingleVariableDeclaration var : singleVariableDeclarations) {
			String name = var.getName().toString();
			String type = var.getType().toString();
			ASTVariable local = new ASTVariable(name, new ASTClass(type));
			md.addLocalVariable(local);
		}
	}
	
	private void registerCalledMethods(ASTMethod parentMethod, MethodInvocationVisitor miv) {

		List<MethodInvocation> listmi = miv.getMethods();
		for (MethodInvocation methodBody : listmi) {

			Expression exp = methodBody.getExpression();
			String methodName = methodBody.getName().toString();

			String varName = null;
			ASTMethod m = new ASTMethod(methodName, new ASTClass(""));

			// Si c'est une instanciation, on crée la méthode avec le bon type pour le receveur
			if (exp instanceof ClassInstanceCreation) {
				ClassInstanceCreation cic = (ClassInstanceCreation) exp;
				m = new ASTMethod(methodName, new ASTClass(cic.getType().toString()));
			} else if (exp == null) {
				varName = "this";
			} else {
				varName = exp.toString();
			}

			addParamOfCalledMethod(methodBody.arguments(), m);

			try {
				parentMethod.addCalledMethod(varName, m);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void addParamOfCalledMethod (List arguments, ASTMethod m) {
		for (Object arg : arguments) {
			ASTVariable param;

			if (StringLiteral.class.isInstance(arg)) {
				param = new ASTVariable(arg.toString(), new ASTClass("String"));
			} else if (NumberLiteral.class.isInstance(arg)) {
				param = new ASTVariable(arg.toString(), new ASTClass("Number"));
			} else if (BooleanLiteral.class.isInstance(arg)) {
				param = new ASTVariable(arg.toString(), new ASTClass("boolean"));
			} else if (ClassInstanceCreation.class.isInstance(arg)) {
				ClassInstanceCreation cic = (ClassInstanceCreation) arg;
				param = new ASTVariable(arg.toString(), new ASTClass(cic.getType().toString()));
			} else {
				param = new ASTVariable(arg.toString(), new ASTClass(""));
			}

			m.addParameter(param);
		}
	}
	
	public void initializeClass() {
		unitClass = new ASTClass(typeDeclaration.getName().toString());
		registerAttributes();
		registerMethods();
	}

	public ASTClass getUnitClass() {
		return unitClass;
	}

	public boolean isValidFile() {
		return typeDeclaration != null;
	}
}
