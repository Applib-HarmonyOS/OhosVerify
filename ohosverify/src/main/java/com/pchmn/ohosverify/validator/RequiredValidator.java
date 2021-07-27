package com.pchmn.ohosverify.validator;

import ohos.hiviewdfx.HiLog;
import com.pchmn.ohosverify.App;
import com.pchmn.ohosverify.ResourceTable;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Basic unit that validates mandatory fields.
 */
public class RequiredValidator extends AbstractValidator {

    private boolean mRequired;

    /**
     * Constructor for RequiredValidator.
     *
     * @param required if the field is required
     */
    public RequiredValidator(boolean required) {
        mRequired = required;
        try {
            mErrorMessage = App.getInstance().getContext().getResourceManager()
                    .getElement(ResourceTable.String_error_field_required).getString();
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            HiLog.error(LABEL, e.toString());
        }
    }

    @Override
    public boolean isValid(String value) {
        if (mRequired) {
            if (value == null || value.length() == 0) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }

    @Override
    public void setErrorMessage(String message) {
        mRequired = true;
        mErrorMessage = message;
    }
}