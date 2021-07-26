package com.pchmn.ohosverify.validator;

import com.pchmn.ohosverify.App;
import com.pchmn.ohosverify.ResourceTable;
import java.io.IOException;
import ohos.global.resource.NotExistException;
import ohos.global.resource.WrongTypeException;


/**
 * Basic unit that validates min length.
 */
public class MinLengthValidator extends AbstractValidator {

    private int mLength;

    /**
     * Constructor for MinLengthValidator.
     *
     * @param length min length allowed in the validator
     */
    public MinLengthValidator(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("You put a negative min length (" + length + ")");
        }
        mLength = length;
        try {
            mErrorMessage = App.getInstance().getContext().getResourceManager()
                    .getElement(ResourceTable.String_error_min_length).getString(mLength);
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
        return value.length() >= mLength;
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
