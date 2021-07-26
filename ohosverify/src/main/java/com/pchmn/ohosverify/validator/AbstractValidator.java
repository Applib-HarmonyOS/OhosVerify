package com.pchmn.ohosverify.validator;

import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import com.pchmn.ohosverify.App;
import com.pchmn.ohosverify.ResourceTable;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * An abstract class that all validators extend.
 */
public abstract class AbstractValidator {

    String mErrorMessage;
    static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00201, "MY_TAG");

    /**
     * Constructor to initialize error message.
     */
    protected AbstractValidator() {
        try {
            mErrorMessage = App.getInstance().getContext().getResourceManager()
                    .getElement(ResourceTable.String_error_invalid_field).getString();
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            HiLog.error(LABEL, e.toString());
        }
    }

    public abstract boolean isValid(String value);

    public abstract String getErrorMessage();

    public void setErrorMessage(String message) {
        mErrorMessage = message;
    }

}
