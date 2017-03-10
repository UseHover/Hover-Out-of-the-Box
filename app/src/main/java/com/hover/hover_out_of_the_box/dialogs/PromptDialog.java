package com.hover.hover_out_of_the_box.dialogs;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.hover.hover_out_of_the_box.R;
import com.hover.hover_out_of_the_box.dialogs.base.BaseDialog;
import com.hover.hover_out_of_the_box.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by aung on 10/31/16.
 */

public class PromptDialog extends BaseDialog {

    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;

    @BindView(R.id.tv_confirm_msg)
    TextView tvConfirmMsg;

    @BindView(R.id.btn_prompt_ok)
    Button btnOk;

    private PromptDelegate mPromptDelegate;

    public PromptDialog(Context context) {
        super(context);
        initDialog();
    }

    private void initDialog() {
        getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_prompt);
        ButterKnife.bind(this, this);

        float widthPx = ScreenUtils.getInstance().getScreenWidthPx();
        rlRoot.setLayoutParams(
                new FrameLayout.LayoutParams((int) (widthPx * 0.8f), FrameLayout.LayoutParams.WRAP_CONTENT));
    }

    public void setUpPrompt(String msg) {
        setUpPrompt(msg, null, null);
    }

    public void setUpPrompt(String msg, PromptDelegate promptDelegate) {
        setUpPrompt(msg, null, promptDelegate);
    }

    public void setUpPrompt(String msg, String okBtn) {
        setUpPrompt(msg, okBtn, null);
    }

    public void setUpPrompt(String msg, String okBtn, PromptDelegate promptDelegate) {
        mPromptDelegate = promptDelegate;
        tvConfirmMsg.setText(msg);

        if (!TextUtils.isEmpty(okBtn)) {
            btnOk.setText(okBtn);
        }

        show();
    }

    @OnClick(R.id.btn_prompt_ok)
    public void onTapOk(View view) {
        dismiss();
        if (mPromptDelegate != null) {
            mPromptDelegate.onConfirmed();
        }
    }

    public interface PromptDelegate {
        void onConfirmed();
    }
}
