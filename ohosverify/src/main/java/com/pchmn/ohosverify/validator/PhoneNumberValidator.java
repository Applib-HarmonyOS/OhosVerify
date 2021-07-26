package com.pchmn.ohosverify.validator;

import com.pchmn.ohosverify.App;
import com.pchmn.ohosverify.ResourceTable;
import java.io.IOException;
import java.util.regex.Pattern;
import ohos.global.resource.NotExistException;
import ohos.global.resource.WrongTypeException;

/**
 * Basic unit that validates phone numbers.
 */
public class PhoneNumberValidator extends AbstractValidator {

    private static final Pattern PHONE
            = Pattern.compile("(\\+[0-9]+[\\- \\.]*)?" + "(\\([0-9]+\\)[\\- \\.]*)?" + "([0-9][0-9\\- \\.]+[0-9])");

    /**
     * Constructor to initialize error message.
     */
    public PhoneNumberValidator() {
        try {
            mErrorMessage = App.getInstance().getContext().getResourceManager()
                    .getElement(ResourceTable.String_error_invalid_phone_number).getString();
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
        if (value.length() > 15) {
            return false;
        }
        return PHONE.matcher(value).matches();
    }


    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
