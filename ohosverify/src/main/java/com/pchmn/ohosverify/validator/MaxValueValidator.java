package com.pchmn.ohosverify.validator;

import ohos.hiviewdfx.HiLog;
import com.pchmn.ohosverify.App;
import com.pchmn.ohosverify.ResourceTable;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Basic unit that validates max value.
 */
public class MaxValueValidator extends AbstractValidator {

    private int mMaxValue;

    /**
     * Constructor for MaxValueValidator.
     *
     * @param value max value allowed in the validator
     */
    public MaxValueValidator(int value) {
        mMaxValue = value;
        fetchErrorMessage(false);
    }

    @Override
    public boolean isValid(String value) {
        try {
            double d = Double.parseDouble(value);
            fetchErrorMessage(false);
            return d <= mMaxValue;
        } catch (NumberFormatException nfe) {
            fetchErrorMessage(true);
            return false;
        }
    }

    private void fetchErrorMessage(boolean isError) {
        try {
            if (isError) {
                mErrorMessage = App.getInstance().getContext().getResourceManager()
                        .getElement(ResourceTable.String_error_max_value).getString(mMaxValue);
            } else {
                mErrorMessage = App.getInstance().getContext().getResourceManager()
                        .getElement(ResourceTable.String_error_invalid_number).getString();
            }
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            HiLog.error(LABEL, e.toString());
        }
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
