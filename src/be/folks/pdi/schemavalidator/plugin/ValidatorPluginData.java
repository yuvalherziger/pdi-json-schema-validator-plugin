package be.folks.pdi.schemavalidator.plugin;

import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.trans.step.BaseStepData;
import org.pentaho.di.trans.step.StepDataInterface;

public class ValidatorPluginData extends BaseStepData implements StepDataInterface
{
    public RowMetaInterface outputRowMeta;

    public ValidatorPluginData()
    {
        super();
    }
}