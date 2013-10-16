package jcattestcaseassit.popup.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IOpenable;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.WorkingCopyOwner;
import org.eclipse.jdt.internal.corext.codemanipulation.StubUtility;
import org.eclipse.jdt.internal.corext.util.JavaModelUtil;
import org.eclipse.jdt.ui.CodeGeneration;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class AttachAction implements IObjectActionDelegate {

	private Shell shell;
	private IWorkbenchPart workbenchPart;
	private ISelection selection;
	private IJavaElement curJavaElem;
	/**
	 * Constructor for Action1.
	 */
	public AttachAction() {
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

		//My source code
		ICompilationUnit originalUnit = null;
		if (selection != null && 
		    selection.isEmpty() == false &&
			selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			if (ssel.size() > 1)
				return;
			Object obj = ssel.getFirstElement();
            if(obj instanceof IFileEditorInput){
				IFileEditorInput fei = (IFileEditorInput)obj;
				IFile editFile = fei.getFile();
				curJavaElem = JavaCore.create(editFile);
				if(curJavaElem instanceof ICompilationUnit){
					 // Get original compilation unit
					originalUnit = (ICompilationUnit)curJavaElem;
			    }
			}
		}
		
		if(originalUnit == null){
			return;
		}

		//http://help.eclipse.org/helios/index.jsp?topic=%2Forg.eclipse.platform.doc.isv%2Freference%2Fapi%2Forg%2Feclipse%2Fjface%2Faction%2FIAction.html
	   
	    // Create working copy
		try {
			ICompilationUnit workingCopy = originalUnit.getWorkingCopy(new WorkingCopyOwner(){}, null);
		    // Modify buffer and reconcile
		    IBuffer buffer = ((IOpenable)workingCopy).getBuffer();
		    buffer.append("class X {}");
		    workingCopy.reconcile(ICompilationUnit.NO_AST, false, null, null);
		    
		    // Commit changes
		    workingCopy.commitWorkingCopy(false, null);
		    
		    // Destroy working copy
		    workingCopy.discardWorkingCopy();
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<IMember> getAllMember(IType type) throws JavaModelException { 
		 List<IMember> list = new ArrayList<IMember>(); 
		 // 得到所有方法，并添加到 list 中
		 for (IMethod method : type.getMethods()) { 
			 list.add(method); 
		 } 
		 // 得到所有字段，并添加到 list 中
		 for (IField field : type.getFields()) { 
			 list.add(field); 
		 } 
		 return list; 
	} 

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

}
