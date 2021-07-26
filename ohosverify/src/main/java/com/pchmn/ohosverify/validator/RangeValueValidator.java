package com.pchmn.ohosverify.validator;

import java.io.PrintWriter;
import java.io.StringWriter;
import ohos.hiviewdfx.HiLog;
import com.pchmn.ohosverify.App;
import com.pchmn.ohosverify.ResourceTable;

/**
 * Basic unit that validates if the value lies within a given range.
 */
public class RangeValueValidator extends AbstractValidator {

    private int mMinValue;
    private int mMaxValue;

    /**
     * Constructor   RangeLengthValidator.
     *
     * @param minValue min value allowed in the validator
     *
     * @param maxValue max value allowed in the validator
     */
    public RangeValueValidator(int minValue, int maxValue) {
        if (minValue > maxValue) {
            throw new IllegalArgumentException("The max value has to be superior or equal to the min value");
        }

        mMinValue = minValue;
        mMaxValue = maxValue;
        fetchErrorMessage();
    }

    @Override
    public boolean isValid(String value) {
        try {
            double d = Double.parseDouble(value);
            fetchErrorMessage();
            return d >= mMinValue && d <= mMaxValue;
        } catch (NumberFormatException nfe) {
            try {
                mErrorMessage = App.getInstance().getContext().getResourceManager()
                        .getElement(ResourceTable.String_error_invalid_number).getString();
            } catch (Exception e) {
                StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                HiLog.error(LABEL, e.toString());
            }
            return false;
        }
    }

    private void fetchErrorMessage()  {
        try {
            mErrorMessage = App.getInstance().getContext().getResourceManager()
                    .getElement(ResourceTable.String_error_range_value).getString(mMinValue, mMaxValue);
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
