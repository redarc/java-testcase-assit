package jcattestcaseassit.popup.actions;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import jcat.testcase.gen.JcatTCBuilderGen;
import jcat.testcase.gen.JcatTCGen;
import jcat.testcase.gen.JcatTCGenArgs;
import jcat.testcase.gen.JcatTCModuleGen;
import jcat.testcase.wizards.JcatTCWizard;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

public class NewJCATTCAction implements IObjectActionDelegate {

	private Shell shell;
	
	private static Button swtButton;
	private static Button swingButton;
	private static Button awtButton;
	private static Group group;
	private static Button button;
	private static Label benefitOfSwtLabel;
	private static List list;
	private IWorkbenchPart fWorkbenchPart;
	/** The structured selection, or <code>null</code> */
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
		if (fWorkbenchPart == null || fSelection == null)
			return;
	    // Create the wizard
		JcatTCWizard wizard = new JcatTCWizard();
		final IWorkbenchWindow window= fWorkbenchPart.getSite().getWorkbenchWindow();
		wizard.init(window.getWorkbench(), fSelection);
	    //wizard.init(PlatformUI.getWorkbench(), fSelection);
	
	    // Create the wizard dialog
	    WizardDialog dialog = new WizardDialog(shell,wizard);
	    // Open the wizard dialog
	    dialog.open();
	      
	    try {
	    	new ProgressMonitorDialog(shell).run(true, true, new IRunnableWithProgress(){
	            public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
	            	try {
						doFinish("\\PluginTest\\src\\com\\redarc", "WP1035", monitor);
					} catch (CoreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	            });
	    } catch (InvocationTargetException e) {
	    	
	    } catch (InterruptedException e) {
	    }
	    
//		ProgressMonitorDialog pd = new ProgressMonitorDialog(shell) {
//			public void run(IProgressMonitor monitor) throws InvocationTargetException {
//				try {
//					doFinish("\\PluginTest\\src\\com\\redarc", "WP1035", monitor);
//				} catch (CoreException e) {
//					throw new InvocationTargetException(e);
//				} finally {
//					monitor.done();
//				}
//			}
//		};

		/*
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			return false;
		}
		return true;
		*/
	}

	/**
	 * The worker method. It will find the container, create the
	 * file if missing or just replace its contents, and open
	 * the editor on the newly created file.
	 */

	private void doFinish(String containerName,
		                  String testCaseName,
		                  IProgressMonitor monitor)throws CoreException {
		
		// create a sample file
		//monitor.beginTask("Creating " + testCaseName, 4);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		if (!resource.exists() || !(resource instanceof IContainer)) {
			throwCoreException("Container \"" + containerName + "\" does not exist.");
		}
		IContainer container = (IContainer) resource;
		final IFile file = container.getFile(new Path(testCaseName.concat("Test.java")));
		
		JcatTCGenArgs jargs = new JcatTCGenArgs();
		jargs.setClassName(testCaseName.concat("Test"));
		jargs.setPkgName("com.ericsson.msr.tests." + testCaseName);
		JcatTCGen gen = new JcatTCGen();
		generatorJcatTC(container, testCaseName.concat("Test"), gen.generate(jargs), monitor);
		//monitor.worked(1);
		
		JcatTCGenArgs tcbuilderargs = new JcatTCGenArgs();
		tcbuilderargs.setClassName(testCaseName.concat("TestModuleBuilder"));
		tcbuilderargs.setPkgName("com.ericsson.msr.tests." + testCaseName);
		JcatTCBuilderGen buildergen = new JcatTCBuilderGen();
		generatorJcatTC(container, testCaseName.concat("TestModuleBuilder"), buildergen.generate(tcbuilderargs), monitor);
		//monitor.worked(2);
		
		JcatTCGenArgs jmoduleargs = new JcatTCGenArgs();
		jmoduleargs.setClassName(testCaseName.concat("TestModule"));
		jmoduleargs.setPkgName("com.ericsson.msr.tests." + testCaseName);
		JcatTCModuleGen modulegen = new JcatTCModuleGen();
		generatorJcatTC(container, testCaseName.concat("TestModule"), modulegen.generate(jmoduleargs), monitor);
		//monitor.worked(3);
		
		//Default open testcase.java
		//monitor.setTaskName("Opening file for editing...");
		shell.getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchPage page =
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				try {
					IDE.openEditor(page, file, true);
				} catch (PartInitException e) {
				}
			}
		});
		//monitor.worked(4);
	}
	
	private void generatorJcatTC(IContainer container,
            String fileName,
            String content,
            IProgressMonitor monitor) throws CoreException{

        final IFile file = container.getFile(new Path(fileName.concat(".java")));
        try {
        InputStream stream = new ByteArrayInputStream(content.getBytes());
        if (file.exists()) {
        	file.setContents(stream, true, true, monitor);
        } else {
        	file.create(stream, true, monitor);
        }
        stream.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }
	}	
	
	private void throwCoreException(String message) throws CoreException {
		IStatus status =
			new Status(IStatus.ERROR, "jcat-testcae-gen", IStatus.OK, message, null);
		throw new CoreException(status);
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
				//final Object element= structured.getFirstElement();
				fSelection= structured;
//				if (element instanceof IPackageFragmentRoot) {
//					final IPackageFragmentRoot root= (IPackageFragmentRoot) element;
//					fSelection= structured;
//				}
			}
		}
		action.setEnabled(fSelection != null);
	}

}
