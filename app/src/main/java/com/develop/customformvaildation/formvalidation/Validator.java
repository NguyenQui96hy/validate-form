package com.develop.customformvaildation.formvalidation;

import android.content.Context;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.develop.customformvaildation.utils.Logger;

import java.util.regex.Pattern;

public class Validator implements IValidationForm {
    private ViewGroup viewGroup;
    private Context context;

    public Validator(ViewGroup viewGroup, Context context) {
        this.viewGroup = viewGroup;
        this.context = context;
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
        switch (editText.getInputType()) {
            case InputType.TYPE_CLASS_PHONE:
                if (!isValidPhone(content)) {
                    Toast.makeText(context, "Phone Error", Toast.LENGTH_LONG).show();
                }
                break;
            case InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS:
                if (!isValidEmail(content)) {
                    Toast.makeText(context, "Email Error", Toast.LENGTH_LONG).show();
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
        return !TextUtils.isEmpty(data) && Patterns.EMAIL_ADDRESS.matcher(data).matches();
    }

    @Override
    public boolean isValidPassWord(String data) {
        Pattern PASSWORD_PATTERN
                = Pattern.compile(
                "[a-zA-Z0-9\\!\\@\\#\\$]{8,24}");

        return !TextUtils.isEmpty(data) && PASSWORD_PATTERN.matcher(data).matches();
    }
}
