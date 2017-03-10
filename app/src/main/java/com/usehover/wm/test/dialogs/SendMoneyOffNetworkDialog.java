package com.usehover.wm.test.dialogs;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.usehover.wm.test.R;
import com.usehover.wm.test.dialogs.base.BaseDialog;
import com.usehover.wm.test.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by aung on 10/31/16.
 */

public class SendMoneyOffNetworkDialog extends BaseDialog {

    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;

    @BindView(R.id.et_recipient_phone_no)
    EditText etRecipientPhoneNo;

    @BindView(R.id.et_nrc_number)
    EditText etNrcNumber;

    @BindView(R.id.et_amount_to_send)
    EditText etAmountToSend;

    @BindView(R.id.et_withdrawl_code)
    EditText etWithdrawlCode;

    private SendMoneyOffNetworkDelegate mDelegate;

    public SendMoneyOffNetworkDialog(Context context) {
        super(context);
        initDialog();
    }

    private void initDialog() {
        getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_send_money_off_network);
        ButterKnife.bind(this, this);

        float widthPx = ScreenUtils.getInstance().getScreenWidthPx();
        rlRoot.setLayoutParams(
                new FrameLayout.LayoutParams((int) (widthPx * 0.8f), FrameLayout.LayoutParams.WRAP_CONTENT));
    }

    public void show(SendMoneyOffNetworkDelegate sendMoneyOnNetworkDelegate) {
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

        String nrcNumber = etNrcNumber.getText().toString();
        if(TextUtils.isEmpty(nrcNumber)) {
            etNrcNumber.setError(getContext().getString(R.string.error_no_nrc_number));
            return;
        }

        String amountToSend = etAmountToSend.getText().toString();
        if(TextUtils.isEmpty(amountToSend)) {
            etAmountToSend.setError(getContext().getString(R.string.error_no_amount_to_send));
            return;
        }

        String withdrawalCode = etWithdrawlCode.getText().toString();
        if(TextUtils.isEmpty(withdrawalCode)) {
            etWithdrawlCode.setError(getContext().getString(R.string.error_no_withdrawl_code));
            return;
        }

        dismiss();
        etRecipientPhoneNo.setText("");
        etNrcNumber.setText("");
        etAmountToSend.setText("");
        etWithdrawlCode.setText("");
        if (mDelegate != null) {
            mDelegate.onConfirmSend(recipientPhoneNo, nrcNumber, amountToSend, withdrawalCode);
        }
    }

    @OnClick(R.id.btn_send_cancel)
    public void onTapSendCancel(View view) {
        dismiss();
    }

    public interface SendMoneyOffNetworkDelegate {
        void onConfirmSend(String recipientPhoneNo, String nrcNumber, String amountToSend, String withdrawlCode);
    }
}
