package com.pchmn.ohosverify.validator;

import ohos.hiviewdfx.HiLog;
import ohos.utils.CommonPattern;
import com.pchmn.ohosverify.App;
import com.pchmn.ohosverify.ResourceTable;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Basic unit that validates email.
 */
public class EmailValidator extends AbstractValidator {

    /**
     * Constructor to initialize error message.
     */
    public EmailValidator() {
        try {
            mErrorMessage = App.getInstance().getContext().getResourceManager()
                        .getElement(ResourceTable.String_error_invalid_email).getString();
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            HiLog.error(LABEL, e.toString());
        }
    }

    @Override
    public boolean isValid(String value) {
        return CommonPattern.getEmailAddress().matcher(value).matches();
    }

    @Override
    public String getErrorMessage() {
        return  mErrorMessage;
    }
}
