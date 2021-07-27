package com.pchmn.ohosverify.validator;

import ohos.agp.components.TextField;
import ohos.hiviewdfx.HiLog;
import com.pchmn.ohosverify.App;
import com.pchmn.ohosverify.ResourceTable;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Basic unit that checks if two fields are identical.
 */
public class IdenticalValidator extends AbstractValidator {

    private TextField mOtherTextField;

    /**
     * Constructor for IdenticalValidator.
     *
     * @param textField TextField this is identical to
     */
    public IdenticalValidator(TextField textField) {
        mOtherTextField = textField;
        try {
            mErrorMessage = App.getInstance().getContext().getResourceManager()
                    .getElement(ResourceTable.String_error_fields_mismatch).getString();
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            HiLog.error(LABEL, e.toString());
        }
    }

    @Override
    public boolean isValid(String value) {
        if (mOtherTextField.getText() == null) {
            if (value == null) {
                return true;
            } else {
                return false;
            }
        }
        if (mOtherTextField.getText().length() == 0) {
            if (value.length() == 0) {
                return true;
            } else {
                return false;
            }
        }
        return value.equals(mOtherTextField.getText());
    }

    @Override
    public String getErrorMessage() {
        return  mErrorMessage;
    }
}
