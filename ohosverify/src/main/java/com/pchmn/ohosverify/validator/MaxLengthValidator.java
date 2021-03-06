package com.pchmn.ohosverify.validator;

import ohos.hiviewdfx.HiLog;
import com.pchmn.ohosverify.App;
import com.pchmn.ohosverify.ResourceTable;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Basic unit that validates max length.
 */
public class MaxLengthValidator extends AbstractValidator {

    private int mLength;

    /**
     * Constructor for MaxLengthValidator.
     *
     * @param length max length allowed in the validator
     */
    public MaxLengthValidator(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("You put a negative max length (" + length + ")");
        }
        if (length == 0) {
            throw new IllegalArgumentException("Max length cannot be equal to zero");
        }
        mLength = length;
        try {
            mErrorMessage = App.getInstance().getContext().getResourceManager()
                    .getElement(ResourceTable.String_error_max_length).getString(mLength);
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            HiLog.error(LABEL, e.toString());
        }
    }

    @Override
    public boolean isValid(String value) {
        return value.length() <= mLength;
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}