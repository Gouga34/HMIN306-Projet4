package com.company.objects;

import org.eclipse.jdt.core.dom.*;

public class ASTUnit {
	
	private CompilationUnit compilationUnit;
	
	private ASTClass unitClass;
	
	public ASTUnit(CompilationUnit cu) {
		compilationUnit = cu;
	}
	
	private void registerAttributes() {
		
	}
	
	private void registerMethods() {
		
	}
	
	private void registerLocalVariables() {
		
	}
	
	private void registerCalledMethods() {
		
	}
	
	public void initializeClass() {
		unitClass = new ASTClass(compilationUnit.getClass().getName());
		registerAttributes();
		registerMethods();
		registerLocalVariables();
		registerCalledMethods();
	}

	public ASTClass getUnitClass() {
		return unitClass;
	}
}
