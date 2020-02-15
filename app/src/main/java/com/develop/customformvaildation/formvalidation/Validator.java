package com.develop.customformvaildation.formvalidation;

import android.content.Context;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.develop.customformvaildation.R;
import com.develop.customformvaildation.utils.Logger;

import java.util.regex.Pattern;

import static android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;

public class Validator implements IValidationForm {
    private ViewGroup viewGroup;
    private Context context;
    private IValidationFormResultListeners iValidationFormResultListeners;

    public Validator(ViewGroup viewGroup, Context context) {
        this.viewGroup = viewGroup;
        this.context = context;
    }

    public Validator(ViewGroup viewGroup, Context context, IValidationFormResultListeners iValidationFormResultListeners) {
        this.viewGroup = viewGroup;
        this.context = context;
        this.iValidationFormResultListeners = iValidationFormResultListeners;
    }

    public void validate() {
        if (null != viewGroup) {
            for (int i = 0, count = viewGroup.getChildCount(); i < count; ++i) {
                View view = viewGroup.getChildAt(i);
                if (view instanceof EditText) {
                    checkInputType((EditText) view);
                }
            }
        }
    }

    /**
     * check type input handel validator
     *
     * @param editText
     */
    private void checkInputType(EditText editText) {
        String content = editText.getText().toString();
        int idForm = editText.getId();
        Logger.e(editText.getId() + " id");
        switch (editText.getInputType()) {
            case InputType.TYPE_CLASS_PHONE:
                if (!isValidPhone(content)) {
                    Logger.e("invalid Phone");
                    editText.setError(getResourcesString(R.string.msg_phone_error));
                    if (null != iValidationFormResultListeners) {
                        iValidationFormResultListeners.onResult(idForm);
                    }
                }
                break;
            case InputType.TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_EMAIL_ADDRESS:
                if (!isValidEmail(content)) {
                    editText.setError(getResourcesString(R.string.msg_email_error));
                    if (null != iValidationFormResultListeners) {
                        iValidationFormResultListeners.onResult(idForm);
                    }
                }
                break;
            case InputType.TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD:
                if (!isValidPassWord(content)) {
                    editText.setError(getResourcesString(R.string.msg_pass_word));
                    if (null != iValidationFormResultListeners) {
                        iValidationFormResultListeners.onResult(idForm);
                    }
                }
                break;
            default:

        }
    }


    @Override
    public boolean isValidPhone(String data) {
        return (!TextUtils.isEmpty(data) && Patterns.PHONE.matcher(data).matches());
    }

    @Override
    public boolean isValidEmail(String data) {
        return (!TextUtils.isEmpty(data) && Patterns.EMAIL_ADDRESS.matcher(data).matches());
    }

    @Override
    public boolean isValidPassWord(String data) {
        Pattern PASSWORD_PATTERN
                = Pattern.compile(
                "[a-zA-Z0-9\\!\\@\\#\\$]{8,24}");

        return (!TextUtils.isEmpty(data) && PASSWORD_PATTERN.matcher(data).matches());
    }

    private String getResourcesString(int id) {
        return context.getResources().getString(id);
    }
}
