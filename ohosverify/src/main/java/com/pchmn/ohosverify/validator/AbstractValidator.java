package com.pchmn.ohosverify.validator;

import com.pchmn.ohosverify.App;
import com.pchmn.ohosverify.ResourceTable;
import java.io.IOException;
import ohos.global.resource.NotExistException;
import ohos.global.resource.WrongTypeException;


/**
 * An abstract class that all validators extend.
 */
public abstract class AbstractValidator {

    String mErrorMessage;

    /**
     * Constructor to initialize error message.
     */
    protected AbstractValidator() {
        try {
            mErrorMessage = App.getInstance().getContext().getResourceManager()
                    .getElement(ResourceTable.String_error_invalid_field).getString();
        } catch (NotExistException e) {
            e.printStackTrace();
        } catch (WrongTypeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract boolean isValid(String value);

    public abstract String getErrorMessage();

    public void setErrorMessage(String message) {
        mErrorMessage = message;
    }

}
