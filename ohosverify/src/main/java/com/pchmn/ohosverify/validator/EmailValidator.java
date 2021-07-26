package com.pchmn.ohosverify.validator;

import com.pchmn.ohosverify.App;
import com.pchmn.ohosverify.ResourceTable;
import java.io.IOException;
import ohos.global.resource.NotExistException;
import ohos.global.resource.WrongTypeException;
import ohos.utils.CommonPattern;


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
        } catch (NotExistException e) {
            e.printStackTrace();
        } catch (WrongTypeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
