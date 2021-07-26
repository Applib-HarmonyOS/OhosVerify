package com.pchmn.ohosverify.validator;

import com.pchmn.ohosverify.App;
import com.pchmn.ohosverify.ResourceTable;
import java.io.IOException;
import ohos.global.resource.NotExistException;
import ohos.global.resource.WrongTypeException;

/**
 * Basic unit that validates if the length lies within a given range.
 */
public class RangeLengthValidator extends AbstractValidator {

    private int mMaxLength;
    private int mMinLength;

    /**
     * Constructor for RangeLengthValidator.
     *
     * @param minLength min length allowed in the validator
     *
     * @param maxLength max length allowed in the validator
     */
    public RangeLengthValidator(int minLength, int maxLength) {
        if (minLength < 0) {
            throw new IllegalArgumentException("You put a negative min length (" + minLength + ")");
        }
        if (maxLength < 0) {
            throw new IllegalArgumentException("You put a negative max length (" + maxLength + ")");
        }
        if (minLength > maxLength) {
            throw new IllegalArgumentException("The max length has to be superior or equal to the min length");
        }

        mMaxLength = maxLength;
        mMinLength = minLength;
        try {
            mErrorMessage = App.getInstance().getContext().getResourceManager()
                    .getElement(ResourceTable.String_error_range_length).getString(mMinLength, mMaxLength);
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
        return value.length() >= mMinLength && value.length() <= mMaxLength;
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}