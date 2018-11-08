package com.eternel.jet.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class EditDialog extends DialogFragment {

    private EditText editText;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //设置全屏主题
        Dialog dialog = new Dialog(getActivity(), R.style.EditDialogTheme);
        dialog.setContentView(R.layout.dialog_fragment);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();

        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        window.setAttributes(lp);
        final View viewById = dialog.getWindow().findViewById(R.id.tv_published);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click != null) {
                    click.onItemClickListener(viewById);
                }
            }
        });
        editText = dialog.getWindow().findViewById(R.id.et_content);
        final View viewById1 = dialog.getWindow().findViewById(R.id.textView);
        viewById1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click != null) {
                    click.onItemClickListener(viewById1);
                }
            }
        });
        //为 commentEditText 设置监听器，在 DialogFragment 绘制完后立即呼出软键盘，呼出成功后即注销(dialog消失后键盘也会隐藏)
        editText.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager != null) {
                    if (inputMethodManager.showSoftInput(editText, 0)) {
                        editText.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            }
        });

        dialog.dismiss();
        return dialog;
    }

    @Override
    public void onCancel(DialogInterface dialog) {

        super.onCancel(dialog);
    }

    private itemClick click;

    public String getInputText() {
        if(editText!=null) {
            return editText.getText().toString();
        }else{
            return "";
        }
    }

    public interface itemClick {
        void onItemClickListener(View view);
    }

    public void setOnItemClickListener(itemClick itemClickListener) {
        click = itemClickListener;
    }

    public interface textChange {
        void onTextChangeListener(String text);
    }
}
