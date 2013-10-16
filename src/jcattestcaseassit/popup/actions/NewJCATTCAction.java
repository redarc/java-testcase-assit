package jcattestcaseassit.popup.actions;

import jcat.testcase.wizards.JcatTCWizard;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;

public class NewJCATTCAction implements IObjectActionDelegate {

	private Shell shell;
	
	private IWorkbenchPart fWorkbenchPart;
	
	private IStructuredSelection fSelection= null;
	
	/**
	 * Constructor for Action1.
	 */
	public NewJCATTCAction() {
		super();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		if (fWorkbenchPart == null || fSelection == null){
			return;
		}
	    // Create the wizard
		JcatTCWizard wizard = new JcatTCWizard();
		final IWorkbenchWindow window= fWorkbenchPart.getSite().getWorkbenchWindow();
		wizard.init(window.getWorkbench(), fSelection);
	    // Create the wizard dialog
	    WizardDialog dialog = new WizardDialog(shell,wizard);
	    // Open the wizard dialog
	    dialog.open();
	}
	
	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
		fWorkbenchPart = targetPart;
	}
	
	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		fSelection= null;
		if (selection instanceof IStructuredSelection) {
			final IStructuredSelection structured= (IStructuredSelection) selection;
			if (structured.size() == 1) {
				fSelection= structured;
			}
		}
		action.setEnabled(fSelection != null);
	}

}
