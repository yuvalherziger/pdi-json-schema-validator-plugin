package be.folks.pdi.schemavalidator.plugin;

import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.core.row.ValueMetaInterface;
import org.pentaho.di.trans.step.BaseStepData;
import org.pentaho.di.trans.step.StepDataInterface;

public class ValidatorPluginData extends BaseStepData implements StepDataInterface
{
    public RowMetaInterface outputRowMeta;
    // the size of the input rows
    public int inputSize;
    // where the key field indexes are
    public int[] keyFieldIndex;
    // meta info for a string conversion
    public ValueMetaInterface[] conversionMeta;

    public ValidatorPluginData()
    {
        super();
    }
}