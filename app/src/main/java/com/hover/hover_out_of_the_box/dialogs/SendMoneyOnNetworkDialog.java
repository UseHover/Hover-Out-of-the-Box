package com.hover.hover_out_of_the_box.dialogs;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
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

public class SendMoneyOnNetworkDialog extends BaseDialog {

    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;

    @BindView(R.id.et_recipient_phone_no)
    EditText etRecipientPhoneNo;

    @BindView(R.id.et_amount_to_send)
    EditText etAmountToSend;

    private SendMoneyOnNetworkDelegate mDelegate;

    public SendMoneyOnNetworkDialog(Context context) {
        super(context);
        initDialog();
    }

    private void initDialog() {
        getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_send_money_on_network);
        ButterKnife.bind(this, this);

        float widthPx = ScreenUtils.getInstance().getScreenWidthPx();
        rlRoot.setLayoutParams(
                new FrameLayout.LayoutParams((int) (widthPx * 0.8f), FrameLayout.LayoutParams.WRAP_CONTENT));
    }

    public void show(SendMoneyOnNetworkDelegate sendMoneyOnNetworkDelegate) {
        mDelegate = sendMoneyOnNetworkDelegate;
        show();
    }

    @OnClick(R.id.btn_send_confirm)
    public void onTapSendConfirm(View view) {
        String recipientPhoneNo = etRecipientPhoneNo.getText().toString();
        if(TextUtils.isEmpty(recipientPhoneNo)) {
            etRecipientPhoneNo.setError(getContext().getString(R.string.error_no_recipient_no));
            return;
        }

        String amountToSend = etAmountToSend.getText().toString();
        if(TextUtils.isEmpty(amountToSend)) {
            etAmountToSend.setError(getContext().getString(R.string.error_no_amount_to_send));
            return;
        }

        dismiss();
        etRecipientPhoneNo.setText("");
        etAmountToSend.setText("");
        if (mDelegate != null) {
            mDelegate.onConfirmSend(recipientPhoneNo, amountToSend);
        }
    }

    @OnClick(R.id.btn_send_cancel)
    public void onTapSendCancel(View view) {
        dismiss();
    }

    public interface SendMoneyOnNetworkDelegate {
        void onConfirmSend(String recipientPhoneNo, String amountToSend);
    }
}
