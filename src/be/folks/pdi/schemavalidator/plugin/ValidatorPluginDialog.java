package be.folks.pdi.schemavalidator.plugin;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.pentaho.di.core.Const;
import org.pentaho.di.ui.core.dialog.EnterValueDialog;
import org.pentaho.di.core.row.ValueMetaAndData;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.ui.trans.step.BaseStepDialog;
import org.pentaho.di.trans.step.BaseStepMeta;
import org.pentaho.di.trans.step.StepDialogInterface;

public class ValidatorPluginDialog extends BaseStepDialog implements StepDialogInterface
{
    private ValidatorPluginMeta input;
    private ValueMetaAndData value;

    // JSON object:
    private Label componentLabelJsonObject;
    private Text componentJsonObject;
    private FormData formDataLabelJsonObject;
    private FormData formDataJsonObject;
    // JSON schema:
    private Label componentLabelJsonSchema;
    private Text componentJsonSchema;
    private FormData formDataLabelJsonSchema;
    private FormData formDataJsonSchema;
    // Validation output column name:
    private Label componentLabelValidationOutput;
    private Text componentValidationOutput;
    private FormData formDataLabelValidationOutput;
    private FormData formDataValidationOutput;
    // is schema URL checkbox:
    private Label componentLabelIsUrl;
    private Button componentIsUrl;
    private FormData formDataLabelIsUrl;
    private FormData formDataIsUrl;



    public ValidatorPluginDialog(Shell parent, Object in, TransMeta transMeta, String sname)
    {
        super(parent, (BaseStepMeta)in, transMeta, sname);
        input=(ValidatorPluginMeta)in;
        value = input.getValue();
    }

    public String open()
    {
        Shell parent = getParent();
        Display display = parent.getDisplay();

        shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.RESIZE | SWT.MIN | SWT.MAX);
        props.setLook( shell );
        setShellImage(shell, input);

        ModifyListener lsMod = new ModifyListener() {
            public void modifyText(ModifyEvent e)
            {
                input.setChanged();
            }
        };
        changed = input.hasChanged();

        FormLayout formLayout = new FormLayout ();
        formLayout.marginWidth  = Const.FORM_MARGIN;
        formLayout.marginHeight = Const.FORM_MARGIN;

        shell.setLayout(formLayout);
        shell.setText(Messages.getString("ValidatorPluginDialog.Shell.Title")); //$NON-NLS-1$

        int middle = props.getMiddlePct();
        int margin = Const.MARGIN;

        // Stepname line
        wlStepname=new Label(shell, SWT.RIGHT);
        wlStepname.setText(Messages.getString("ValidatorPluginDialog.StepName.Label")); //$NON-NLS-1$
        props.setLook( wlStepname );
        fdlStepname=new FormData();
        fdlStepname.left = new FormAttachment(0, 0);
        fdlStepname.right= new FormAttachment(middle, -margin);
        fdlStepname.top  = new FormAttachment(0, margin);
        wlStepname.setLayoutData(fdlStepname);
        wStepname=new Text(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
        wStepname.setText(stepname);
        props.setLook( wStepname );
        wStepname.addModifyListener(lsMod);
        fdStepname=new FormData();
        fdStepname.left = new FormAttachment(middle, 0);
        fdStepname.top  = new FormAttachment(0, margin);
        fdStepname.right= new FormAttachment(100, 0);
        wStepname.setLayoutData(fdStepname);

        // JSON object line:
        componentLabelJsonObject = new Label(shell, SWT.RIGHT);
        componentLabelJsonObject.setText(Messages.getString("ValidatorPluginDialog.JsonObject.Label"));
        props.setLook(componentLabelJsonObject);
        formDataLabelJsonObject = new FormData();
        formDataLabelJsonObject.left = new FormAttachment(0, 0);
        formDataLabelJsonObject.right= new FormAttachment(middle, -margin);
        formDataLabelJsonObject.top  = new FormAttachment(wStepname, margin);
        componentLabelJsonObject.setLayoutData(formDataLabelJsonObject);
        componentJsonObject = new Text(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
        props.setLook(componentJsonObject);
        componentJsonObject.addModifyListener(lsMod);
        formDataJsonObject = new FormData();
        formDataJsonObject.left = new FormAttachment(middle, 0);
        formDataJsonObject.right = new FormAttachment(100, 0);
        formDataJsonObject.top  = new FormAttachment(wStepname, margin);
        componentJsonObject.setLayoutData(formDataJsonObject);

        // JSON schema line:
        componentLabelJsonSchema = new Label(shell, SWT.RIGHT);
        componentLabelJsonSchema.setText(Messages.getString("ValidatorPluginDialog.JsonSchema.Label"));
        props.setLook(componentLabelJsonSchema);
        formDataLabelJsonSchema = new FormData();
        formDataLabelJsonSchema.left = new FormAttachment(0, 0);
        formDataLabelJsonSchema.right= new FormAttachment(middle, -margin);
        formDataLabelJsonSchema.top  = new FormAttachment(componentJsonObject, margin);
        componentLabelJsonSchema.setLayoutData(formDataLabelJsonSchema);
        componentJsonSchema = new Text(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
        props.setLook(componentJsonSchema);
        componentJsonSchema.addModifyListener(lsMod);
        formDataJsonSchema = new FormData();
        formDataJsonSchema.left = new FormAttachment(middle, 0);
        formDataJsonSchema.right = new FormAttachment(100, 0);
        formDataJsonSchema.top  = new FormAttachment(componentJsonObject, margin);
        componentJsonSchema.setLayoutData(formDataJsonSchema);

        // Validation output column line:
        componentLabelValidationOutput = new Label(shell, SWT.RIGHT);
        componentLabelValidationOutput.setText(Messages.getString("ValidatorPluginDialog.ValidationOutput.Label"));
        props.setLook(componentLabelValidationOutput);
        formDataLabelValidationOutput = new FormData();
        formDataLabelValidationOutput.left = new FormAttachment(0, 0);
        formDataLabelValidationOutput.right= new FormAttachment(middle, -margin);
        formDataLabelValidationOutput.top  = new FormAttachment(componentJsonSchema, margin);
        componentLabelValidationOutput.setLayoutData(formDataLabelValidationOutput);
        componentValidationOutput = new Text(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
        props.setLook(componentValidationOutput);
        componentValidationOutput.addModifyListener(lsMod);
        formDataValidationOutput = new FormData();
        formDataValidationOutput.left = new FormAttachment(middle, 0);
        formDataValidationOutput.right = new FormAttachment(100, 0);
        formDataValidationOutput.top  = new FormAttachment(componentJsonSchema, margin);
        componentValidationOutput.setLayoutData(formDataValidationOutput);

        // Is schema URL checkbox:
        componentLabelIsUrl = new Label(shell, SWT.RIGHT);
        componentLabelIsUrl.setText(Messages.getString("ValidatorPluginDialog.IsUrl.Label"));
        props.setLook(componentLabelIsUrl);
        formDataLabelIsUrl = new FormData();
        formDataLabelIsUrl.left = new FormAttachment(0, 0);
        formDataLabelIsUrl.right= new FormAttachment(middle, -margin);
        formDataLabelIsUrl.top  = new FormAttachment(componentValidationOutput, margin);
        componentLabelIsUrl.setLayoutData(formDataLabelIsUrl);
        componentIsUrl = new Button(shell, SWT.CHECK);
        props.setLook(componentIsUrl);
        //componentIsUrl.addModifyListener(lsMod);
        formDataIsUrl = new FormData();
        formDataIsUrl.left = new FormAttachment(middle, 0);
        formDataIsUrl.right = new FormAttachment(100, 0);
        formDataIsUrl.top  = new FormAttachment(componentValidationOutput, margin);
        componentIsUrl.setLayoutData(formDataIsUrl);

        // Some buttons
        wOK=new Button(shell, SWT.PUSH);
        wOK.setText(Messages.getString("System.Button.OK")); //$NON-NLS-1$
        wCancel=new Button(shell, SWT.PUSH);
        wCancel.setText(Messages.getString("System.Button.Cancel")); //$NON-NLS-1$

        BaseStepDialog.positionBottomButtons(shell, new Button[] { wOK, wCancel}, margin, componentIsUrl);

        // Add listeners
        lsCancel = new Listener() {
            public void handleEvent(Event e) { cancel();
            }
        };
        lsOK = new Listener() {
            public void handleEvent(Event e) { ok();
            }
        };

        wCancel.addListener(SWT.Selection, lsCancel);
        wOK.addListener    (SWT.Selection, lsOK    );

        lsDef=new SelectionAdapter() { public void widgetDefaultSelected(SelectionEvent e) { ok(); } };

        wStepname.addSelectionListener( lsDef );
        componentJsonObject.addSelectionListener( lsDef );
        componentJsonSchema.addSelectionListener( lsDef );

        // Detect X or ALT-F4 or something that kills this window...
        shell.addShellListener(	new ShellAdapter() { public void shellClosed(ShellEvent e) { cancel(); } } );

        // Set the shell size, based upon previous time...
        setSize();

        getData();
        input.setChanged(changed);

        shell.open();
        while (!shell.isDisposed())
        {
            if (!display.readAndDispatch()) display.sleep();
        }
        return stepname;
    }

    // Read data from input (TextFileInputInfo)
    public void getData()
    {
        wStepname.selectAll();
        if (value!=null)
        {
            //wValName.setText(value.getValueMeta().getName());
            //wValue.setText(value.toString()+" ("+value.toStringMeta()+")"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    private void cancel()
    {
        stepname = null;
        input.setChanged(changed);
        dispose();
    }

    private void ok()
    {
        stepname = wStepname.getText(); // return value
        value.getValueMeta().setName(componentJsonObject.getText());
        input.setValue(value);
        dispose();
    }
}