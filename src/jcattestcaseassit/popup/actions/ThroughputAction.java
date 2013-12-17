package jcattestcaseassit.popup.actions;

import org.eclipse.core.resources.IFile;

import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IOpenable;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.WorkingCopyOwner;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;

public class ThroughputAction implements IObjectActionDelegate {

	private Shell shell;
	private IWorkbenchPart workbenchPart;
	private ISelection selection;
	private IJavaElement curJavaElem;
	private IType iType;

	/**
	 * Constructor for Action1.
	 */
	public ThroughputAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
		workbenchPart = targetPart;
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		// MessageDialog.openInformation(
		// shell,
		// "Jcat-testcase-assit",
		// "ThroughputAction was executed.");
		// My source code
		ICompilationUnit originalUnit = null;
		if (selection != null && selection.isEmpty() == false
				&& selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			if (ssel.size() > 1)
				return;
			Object obj = ssel.getFirstElement();
			if (obj instanceof IFileEditorInput) {
				IFileEditorInput fei = (IFileEditorInput) obj;
				// IPersistableElement persistElem = fei.getPersistable();
				IFile editFile = fei.getFile();
				curJavaElem = JavaCore.create(editFile);
				if (curJavaElem instanceof ICompilationUnit) {
					// Get original compilation unit
					originalUnit = (ICompilationUnit) curJavaElem;
				}
			}
		}

		if (originalUnit == null) {
			return;
		}

		// http://help.eclipse.org/helios/index.jsp?topic=%2Forg.eclipse.platform.doc.isv%2Freference%2Fapi%2Forg%2Feclipse%2Fjface%2Faction%2FIAction.html

		// Create working copy
		try {

			int offset = 0;
			IEditorPart editor = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage()
					.getActiveEditor();
			if (editor instanceof ITextEditor) {
				ISelectionProvider selectionProvider = ((ITextEditor) editor)
						.getSelectionProvider();
				ISelection selection = selectionProvider.getSelection();
				if (selection instanceof ITextSelection) {
					ITextSelection textSelection = (ITextSelection) selection;
					offset = textSelection.getOffset(); // etc.
				}
			}

			ICompilationUnit workingCopy = originalUnit.getWorkingCopy(
					new WorkingCopyOwner() {
					}, null);

			// Modify buffer and reconcile
			IBuffer buffer = ((IOpenable) workingCopy).getBuffer();
			String content = buffer.getContents();

			// IMember member = (IMember) ((IStructuredSelection) selection)
			// .getFirstElement();
			// IScanner scanner = ToolFactory.createScanner(false, false, false,
			// false);
			// scanner.setSource(buffer.getCharacters());
			// ISourceRange sr = member.getSourceRange();
			// scanner.resetTo(sr.getOffset(), sr.getOffset() + sr.getLength() -
			// 1);
			// buffer.replace(scanner.getCurrentTokenStartPosition(),
			// scanner.getCurrentTokenEndPosition(), "good");

			// int token = scanner.getNextToken();
			// while (token != ITerminalSymbols.TokenNameEOF
			// && token != ITerminalSymbols.TokenNameLPAREN)
			// token = scanner.getNextToken();
			// if (token == ITerminalSymbols.TokenNamePUBLIC) {
			// buffer.replace(scanner.getCurrentTokenStartPosition(),
			// scanner.getCurrentTokenEndPosition(),
			// scanner.getCurrentTokenStartPosition() + 1, "private");
			// break;
			// }

			String headContent = content.substring(0, offset);
			String tailContent = content.substring(offset, content.length());
			//
			// String newContent = headContent + "public void dosomething(){}" +
			// tailContent;
			// buffer.setContents(newContent);

			// gaolei
			String autoInputFieldContent = null;
			String autoInputMethodContent = null;
			String newContent = null;

			iType = workingCopy.getTypes()[0];

			IField[] fields = iType.getFields();
			if (fields.length > 0) {
				for (IField field : fields) {
					if (field.toString().indexOf("UeNasMode") != -1) {
						// System.out.println("YES++++++++++++++++++++++++++++++++++++++++++++++++YES");
						autoInputFieldContent ="\n    private EnbConfigHandlerBuilder enbOriginalConfig;"
								+ "\n    private void doMultiUeThroughput(){"
								+ "\n        UeHandler.getInstance(LteRm.ue1).setUeNasMode(ueNasMode);"
								+ "\n        UeHandler.getInstance(LteRm.ue1).setCellIds(Integer.parseInt(servingEUtranCell));"
								+ "\n        enbTestHelper.attachUes();"
								+ "\n        enbTestHelper.startUserData(Protocol.UDP, Direction.BIDIRECTIONAL, duration);"
								+ "\n        enbTestHelper.analyzeIperfDataWhenFinished(duration, Protocol.UDP, Direction.BIDIRECTIONAL);"
								+ "\n        enbTestHelper.detachUes();"
								+ "\n    }";
						// MessageDialog.openInformation(shell,"Extend","Field is exiting!");
					} else {
						autoInputFieldContent = "\n    private UeNasMode ueNasMode;"
								+ "\n    private EnbHandler enbHandler;"
								+ "\n    private EnbTestHelper enbTestHelper;"
								+ "\n    private void doMultiUeThroughput(){"
								+ "\n        UeHandler.getInstance(LteRm.ue1).setUeNasMode(ueNasMode);"
								+ "\n        UeHandler.getInstance(LteRm.ue1).setCellIds(Integer.parseInt(servingEUtranCell));"
								+ "\n        enbTestHelper.attachUes();"
								+ "\n        enbTestHelper.startUserData(Protocol.UDP, Direction.BIDIRECTIONAL, duration);"
								+ "\n        enbTestHelper.analyzeIperfDataWhenFinished(duration, Protocol.UDP, Direction.BIDIRECTIONAL);"
								+ "\n        enbTestHelper.detachUes();"
								+ "\n    }";
						// MessageDialog.openInformation(shell,"Extend","Field has been created!");
					}
				}
			} else {
				autoInputFieldContent = "\n    private void doMultiUeThroughput(){"
						+ "\n        UeHandler.getInstance(LteRm.ue1).setUeNasMode(ueNasMode);"
						+ "\n        UeHandler.getInstance(LteRm.ue1).setCellIds(Integer.parseInt(servingEUtranCell));"
						+ "\n        enbTestHelper.attachUes();"
						+ "\n        enbTestHelper.startUserData(Protocol.UDP, Direction.BIDIRECTIONAL, duration);"
						+ "\n        enbTestHelper.analyzeIperfDataWhenFinished(duration, Protocol.UDP, Direction.BIDIRECTIONAL);"
						+ "\n        enbTestHelper.detachUes();"
						+ "\n    }";
			}

			IMethod[] methods = iType.getMethods();
			if (methods.length > 0) {
				for (IMethod method : methods) {
					if (iType.getMethods() != null) {
						if (method.getElementName().indexOf(
								iType.getElementName()) != -1) {
							autoInputMethodContent = "\n";
							break;
						} else {
							autoInputMethodContent = "\n"
									+ iType.getElementName() + "(){\nint i=1}";
						}
					}
				}
			} else {
				autoInputMethodContent = "\n    "
						+ iType.getElementName()
						+ "(){"
						+ "\n        ueNasMode = UeNasModeBuilders.fromCategoryNumber(LteRm.ue1,UeCategory.CAT4,ue_TotalNumber,Integer.parseInt(servingEUtranCell));"
						+ "\n        enbHandler = EnbHandler.getInstance(LteRm.enb1);"
						+ "\n        enbTestHelper = new EnbTestHelper(testId, LteRm.enb1,enbOriginalConfig.build());"
						+ "\n        enbOriginalConfig = EnbConfigHandler.getBuilder();"
						+ "\n    }";
			}
			// if (methods.length>0) {
			// for (IMethod method : methods) {
			// if (iType.getMethods() != null) {
			//
			// if (method.getElementName().indexOf(iType.getElementName()) !=
			// -1) {
			// //
			// System.out.println("NO++++++++++++++++++++++++++++++++++++++++++++++++NO"+method.getElementName());
			// autoInputMethodContent = "\n";
			// break;
			// //
			// MessageDialog.openInformation(shell,"Extend","Constructor is exiting!");
			// } else {autoInputMethodContent = "\n" + iType.getElementName()+
			// "(){\nint i=1;}";
			// //
			// MessageDialog.openInformation(shell,"Extend","Constructor has been created!");
			// }
			// }else {autoInputMethodContent = "\n" + iType.getElementName()+
			// "(){\nint i=1;}";}

			newContent = headContent + autoInputFieldContent
					+ autoInputMethodContent + tailContent;
			buffer.setContents(newContent);

			// buffer.replace(0, newContent.length(), newContent);
			// IType curClass = workingCopy.getTypes()[0];

			// buffer.append("class X }");

			workingCopy.reconcile(ICompilationUnit.NO_AST, false, null, null);

			// Commit changes
			workingCopy.commitWorkingCopy(false, null);

			// Destroy working copy
			workingCopy.discardWorkingCopy();

			/*
			 * AST ast = AST.newAST(AST.JLS4); CompilationUnit unit =
			 * ast.newCompilationUnit(); PackageDeclaration packageDeclaration =
			 * ast.newPackageDeclaration();
			 * packageDeclaration.setName(ast.newSimpleName("example"));
			 * unit.setPackage(packageDeclaration); ImportDeclaration
			 * importDeclaration = ast.newImportDeclaration(); QualifiedName
			 * name = ast.newQualifiedName( ast.newSimpleName("java"),
			 * ast.newSimpleName("util")); importDeclaration.setName(name);
			 * importDeclaration.setOnDemand(true);
			 * unit.imports().add(importDeclaration); TypeDeclaration type =
			 * ast.newTypeDeclaration(); type.setInterface(false);
			 * type.modifiers
			 * ().add(ast.newModifier(Modifier.ModifierKeyword.PUBLIC_KEYWORD));
			 * type.setName(ast.newSimpleName("HelloWorld")); MethodDeclaration
			 * methodDeclaration = ast.newMethodDeclaration();
			 * methodDeclaration.setConstructor(false); List modifiers =
			 * methodDeclaration.modifiers();
			 * modifiers.add(ast.newModifier(Modifier
			 * .ModifierKeyword.PUBLIC_KEYWORD));
			 * modifiers.add(ast.newModifier(Modifier
			 * .ModifierKeyword.STATIC_KEYWORD));
			 * methodDeclaration.setName(ast.newSimpleName("main"));
			 * methodDeclaration
			 * .setReturnType2(ast.newPrimitiveType(PrimitiveType.VOID));
			 * SingleVariableDeclaration variableDeclaration =
			 * ast.newSingleVariableDeclaration();
			 * variableDeclaration.setType(ast
			 * .newArrayType(ast.newSimpleType(ast.newSimpleName("String"))));
			 * variableDeclaration.setName(ast.newSimpleName("args"));
			 * methodDeclaration.parameters().add(variableDeclaration);
			 * org.eclipse.jdt.core.dom.Block block = ast.newBlock();
			 * MethodInvocation methodInvocation = ast.newMethodInvocation();
			 * name = ast.newQualifiedName( ast.newSimpleName("System"),
			 * ast.newSimpleName("out")); methodInvocation.setExpression(name);
			 * methodInvocation.setName(ast.newSimpleName("println"));
			 * InfixExpression infixExpression = ast.newInfixExpression();
			 * infixExpression.setOperator(InfixExpression.Operator.PLUS);
			 * StringLiteral literal = ast.newStringLiteral();
			 * literal.setLiteralValue("Hello");
			 * infixExpression.setLeftOperand(literal); literal =
			 * ast.newStringLiteral(); literal.setLiteralValue(" world");
			 * infixExpression.setRightOperand(literal);
			 * methodInvocation.arguments().add(infixExpression);
			 * ExpressionStatement expressionStatement =
			 * ast.newExpressionStatement(methodInvocation);
			 * block.statements().add(expressionStatement);
			 * methodDeclaration.setBody(block);
			 * type.bodyDeclarations().add(methodDeclaration);
			 * unit.types().add(type);
			 */
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

}
