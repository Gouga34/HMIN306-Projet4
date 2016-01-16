package com.company.ast;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.company.ast.objects.ASTClass;
import com.company.utils.ContentClass;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class ASTGenerator {
	
	private ASTParser parser;
	
	private Map<String, ASTUnit> units = new HashMap<String, ASTUnit>();
	
	public ASTGenerator() {
		
	}
	
	public void initialize() {
		parser = ASTParser.newParser(AST.JLS8);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
	}

	public void parseFile(String filePath) {
		ContentClass cc = new ContentClass();
		
		String content = cc.getContent(filePath);
		parser.setSource(content.toCharArray());
		
		
		CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		File file = new File(filePath);
		
		ASTUnit astUnit = new ASTUnit(cu);
		astUnit.initializeClass();

		this.units.put(file.getName(), astUnit);
	}
	
	public ASTClass getClass(String filename) {
		return units.get(filename).getUnitClass();
	}
}
