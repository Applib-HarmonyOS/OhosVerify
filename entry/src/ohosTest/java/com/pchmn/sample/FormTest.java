package com.pchmn.sample;

import com.pchmn.ohosverify.Form;
import com.pchmn.ohosverify.InputValidator;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import ohos.agp.components.TextField;
import ohos.app.Context;
import org.junit.*;

import static org.junit.Assert.*;

public class FormTest {

    private Context mContext;
    private Form mForm;
    private TextField mEmailEditText;
    private TextField mPasswordEditText;

    @Before
    public void setUp() {
        mContext = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        mEmailEditText = new TextField(mContext);
        mPasswordEditText = new TextField(mContext);
    }

    @Test
    public void validateEmptyForm() {
        mForm = new Form(mContext);
        assertTrue(mForm.isValid());
    }

    @Test
    public void validateJava() {
        // form
        mForm = new Form(mContext);

        // validators
        InputValidator emailInputValidator = new InputValidator(mContext);
        emailInputValidator.setValidatorType(InputValidator.IS_EMAIL);
        emailInputValidator.setRequired(true);
        emailInputValidator.setEditText(mEmailEditText);
        mForm.addInputValidator(emailInputValidator);

        InputValidator passwordInputValidator = new InputValidator(mContext);
        passwordInputValidator.setEditText(mPasswordEditText);
        passwordInputValidator.setMinLength(6);
        mForm.addInputValidator(passwordInputValidator);

        // validate form
        validate();
    }

    @Test
    public void validateBuilder() {
        // form
        mForm = new Form.Builder(mContext)
                .build();

        // validators
        InputValidator emailValidator = new InputValidator.Builder(mContext)
                .on(mEmailEditText)
                .required(true)
                .validatorType(InputValidator.IS_EMAIL)
                .build();
        mForm.addInputValidator(emailValidator);

        InputValidator passwordValidator = new InputValidator.Builder(mContext)
                .on(mPasswordEditText)
                .minLength(6)
                .build();
        mForm.addInputValidator(passwordValidator);

        // validate form
        validate();
    }

    public void validate() {
        // reset
        mEmailEditText.setText("");
        mPasswordEditText.setText("");

        // not valid
        assertFalse(mForm.isValid());
        mEmailEditText.setText("ab");
        assertFalse(mForm.isValid());
        mEmailEditText.setText("test@gmail.com");
        mPasswordEditText.setText("12345");
        assertFalse(mForm.isValid());

        // valid
        mEmailEditText.setText("test@gmail.com");
        mPasswordEditText.setText("123456");
        assertTrue(mForm.isValid());
        mPasswordEditText.setText("");
        assertTrue(mForm.isValid());
    }

    @Test
    public void showErrorsJava() {
        // form
        mForm = new Form(mContext);

        // validator
        InputValidator emailInputValidator = new InputValidator(mContext);
        emailInputValidator.setValidatorType(InputValidator.IS_EMAIL);
        emailInputValidator.setRequired(true);
        emailInputValidator.setEditText(mEmailEditText);
        mForm.addInputValidator(emailInputValidator);

        // show errors
        showErrors();
    }

    @Test
    public void showErrorsBuilder() {
        // form
        mForm = new Form.Builder(mContext)
                .build();

        // validator
        InputValidator emailValidator = new InputValidator.Builder(mContext)
                .on(mEmailEditText)
                .required(true)
                .validatorType(InputValidator.IS_EMAIL)
                .build();
        mForm.addInputValidator(emailValidator);

        // show errors
        showErrors();
    }

    public void showErrors() {
        // reset error
        String default_hint = mEmailEditText.getHint();
        // show error
        mEmailEditText.setText("abc");
        assertFalse(mForm.isValid());
        assertNotEquals(default_hint,mEmailEditText.getHint());

        // reset error
        mEmailEditText.setHint(default_hint);
        // do not show error
        mForm.setShowErrors(false);
        assertFalse(mForm.isValid());
        assertEquals(default_hint,mEmailEditText.getHint());
    }
}