package com.pchmn.ohosverify.validator;

import com.pchmn.ohosverify.App;
import com.pchmn.ohosverify.ResourceTable;
import java.io.IOException;
import ohos.global.resource.NotExistException;
import ohos.global.resource.WrongTypeException;
import ohos.utils.CommonPattern;

/**
 * Basic unit that validates URL addresses.
 */
public class UrlValidator extends AbstractValidator {

    /**
     * Constructor for RequiredValidator.
     */
    public UrlValidator() {
        try {
            mErrorMessage = App.getInstance().getContext().getResourceManager()
                    .getElement(ResourceTable.String_error_invalid_url).getString();
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
        return CommonPattern.getWebUrl().matcher(value).matches();
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
