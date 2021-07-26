package com.pchmn.ohosverify.validator;

import ohos.hiviewdfx.HiLog;
import com.pchmn.ohosverify.App;
import com.pchmn.ohosverify.ResourceTable;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Basic unit that validates min value.
 */
public class MinValueValidator extends AbstractValidator {

    private int mMinValue;

    /**
     * Constructor for MinValueValidator.
     *
     * @param value min value allowed in the validator
     */
    public MinValueValidator(int value) {
        mMinValue = value;
        fetchErrorMessage(false);
    }

    @Override
    public boolean isValid(String value) {
        try {
            double d = Double.parseDouble(value);
            fetchErrorMessage(false);
            return d >= mMinValue;
        } catch (NumberFormatException nfe) {
            fetchErrorMessage(true);
            return false;
        }
    }

    private void fetchErrorMessage(boolean isError) {
        try {
            if (isError) {
                mErrorMessage = App.getInstance().getContext().getResourceManager()
                        .getElement(ResourceTable.String_error_min_value).getString(mMinValue);
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
