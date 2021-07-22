package com.pchmn.sample;

import com.pchmn.ohosverify.InputValidator;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import ohos.agp.components.TextField;
import ohos.app.Context;
import org.junit.*;

import static org.junit.Assert.*;

public class InputValidatorTest {

    private Context mContext;
    private TextField mEditText;

    @Before
    public void setUp() {
        mContext = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        mEditText = new TextField(mContext);
    }

    @Test
    public void required() {
        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .required(true)
                .build();

        assertFalse(inputValidator.isValid());
        mEditText.setText("test");
        assertTrue(inputValidator.isValid());
    }

    @Test
    public void isEmail() {
        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .validatorType(InputValidator.IS_EMAIL)
                .build();

        assertTrue(inputValidator.isValid());
        mEditText.setText("test@gmail.com");
        assertTrue(inputValidator.isValid());
        mEditText.setText("test@gmailcom");
        assertFalse(inputValidator.isValid());
    }

    @Test
    public void isPhoneNumber() {
        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .validatorType(InputValidator.IS_PHONE_NUMBER)
                .build();

        assertTrue(inputValidator.isValid());
        mEditText.setText("+331234567");
        assertTrue(inputValidator.isValid());
    }

    @Test
    public void isNumeric() {
        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .validatorType(InputValidator.IS_NUMERIC)
                .build();

        assertTrue(inputValidator.isValid());
        mEditText.setText("1562315");
        assertTrue(inputValidator.isValid());
        mEditText.setText("abc");
        assertFalse(inputValidator.isValid());
    }

    @Test
    public void minLength() {
        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .minLength(3)
                .build();

        assertTrue(inputValidator.isValid());
        mEditText.setText("abc");
        assertTrue(inputValidator.isValid());
        mEditText.setText("abcd");
        assertTrue(inputValidator.isValid());
        mEditText.setText("ab");
        assertFalse(inputValidator.isValid());

   }

    @Test
    public void maxLength() {
        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .maxLength(3)
                .build();

        assertTrue(inputValidator.isValid());
        mEditText.setText("abc");
        assertTrue(inputValidator.isValid());
        mEditText.setText("ab");
        assertTrue(inputValidator.isValid());
        mEditText.setText("abcd");
        assertFalse(inputValidator.isValid());

    }

    @Test
    public void rangeLength() {
        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .minLength(2)
                .maxLength(5)
                .build();

        assertTrue(inputValidator.isValid());
        mEditText.setText("ab");
        assertTrue(inputValidator.isValid());
        mEditText.setText("abcde");
        assertTrue(inputValidator.isValid());
        mEditText.setText("abc");
        assertTrue(inputValidator.isValid());
        mEditText.setText("abcdef");
        assertFalse(inputValidator.isValid());
        mEditText.setText("a");
        assertFalse(inputValidator.isValid());

    }

    @Test
    public void minValue() {
        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .minValue(3)
                .build();

        assertTrue(inputValidator.isValid());
        mEditText.setText("3");
        assertTrue(inputValidator.isValid());
        mEditText.setText("5");
        assertTrue(inputValidator.isValid());
        mEditText.setText("ab");
        assertFalse(inputValidator.isValid());
        mEditText.setText("2");
        assertFalse(inputValidator.isValid());
    }

    @Test
    public void maxValue() {
        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .maxValue(10)
                .build();

        assertTrue(inputValidator.isValid());
        mEditText.setText("10");
        assertTrue(inputValidator.isValid());
        mEditText.setText("7");
        assertTrue(inputValidator.isValid());
        mEditText.setText("ab");
        assertFalse(inputValidator.isValid());
        mEditText.setText("11");
        assertFalse(inputValidator.isValid());
    }

    @Test
    public void rangevalue() {
        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .minValue(5)
                .maxValue(10)
                .build();

        assertTrue(inputValidator.isValid());
        mEditText.setText("10");
        assertTrue(inputValidator.isValid());
        mEditText.setText("5");
        assertTrue(inputValidator.isValid());
        mEditText.setText("7");
        assertTrue(inputValidator.isValid());
        mEditText.setText("ab");
        assertFalse(inputValidator.isValid());
        mEditText.setText("11");
        assertFalse(inputValidator.isValid());
        mEditText.setText("4");
        assertFalse(inputValidator.isValid());

    }

    @Test
    public void regex() {
        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .regex("^[0-9]{4}$")
                .build();

        assertTrue(inputValidator.isValid());
        mEditText.setText("1234");
        assertTrue(inputValidator.isValid());
        mEditText.setText("0000");
        assertTrue(inputValidator.isValid());
        mEditText.setText("abcd");
        assertFalse(inputValidator.isValid());
        mEditText.setText("123");
        assertFalse(inputValidator.isValid());
    }

    @Test
    public void identicalAs() {
        TextField otherEditText = new TextField(mContext);
        String test_string = "salut les amis";
        otherEditText.setText(test_string);

        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .identicalAs(otherEditText)
                .build();

        mEditText.setText("salut");
        assertFalse(inputValidator.isValid());
        mEditText.setText(test_string);
        otherEditText.setText(test_string);
        assertTrue(inputValidator.isValid());
    }

    @Test
    public void errorMessage() {
        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .minLength(5)
                .errorMessage("Min length 5")
                .build();

        mEditText.setText("1234");
        assertFalse(inputValidator.isValid());
        assertEquals("Min length 5",mEditText.getHint().toString());
    }

    @Test
    public void requiredMessage() {
        InputValidator inputValidator = new InputValidator.Builder(mContext)
                .on(mEditText)
                .requiredMessage("required")
                .build();

        assertFalse(inputValidator.isValid());
        assertEquals("required", mEditText.getHint().toString());
    }

}
