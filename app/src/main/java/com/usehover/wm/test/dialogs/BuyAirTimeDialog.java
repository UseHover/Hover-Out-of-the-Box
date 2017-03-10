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

public class BuyAirTimeDialog extends BaseDialog {

    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;

    @BindView(R.id.et_recipient_phone_no)
    EditText etRecipientPhoneNo;

    @BindView(R.id.et_amount_to_buy_airtime)
    EditText etAmountToBuyAirTime;

    private BuyAirTimeDelegate mDelegate;

    public BuyAirTimeDialog(Context context) {
        super(context);
        initDialog();
    }

    private void initDialog() {
        getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_buy_airtime);
        ButterKnife.bind(this, this);

        float widthPx = ScreenUtils.getInstance().getScreenWidthPx();
        rlRoot.setLayoutParams(
                new FrameLayout.LayoutParams((int) (widthPx * 0.8f), FrameLayout.LayoutParams.WRAP_CONTENT));
    }

    public void show(BuyAirTimeDelegate buyAirTimeDelegate) {
        mDelegate = buyAirTimeDelegate;
        show();
    }

    @OnClick(R.id.btn_buy_confirm)
    public void onTapSendConfirm(View view) {
        String recipientPhoneNo = etRecipientPhoneNo.getText().toString();
        if(TextUtils.isEmpty(recipientPhoneNo)) {
            etRecipientPhoneNo.setError(getContext().getString(R.string.error_no_recipient_no));
            return;
        }

        String amountToBuyAirTime = etAmountToBuyAirTime.getText().toString();
        if(TextUtils.isEmpty(amountToBuyAirTime)) {
            etAmountToBuyAirTime.setError(getContext().getString(R.string.error_no_amount_to_buy_airtime));
            return;
        }

        dismiss();
        etRecipientPhoneNo.setText("");
        etAmountToBuyAirTime.setText("");
        if (mDelegate != null) {
            mDelegate.onConfirmBuy(recipientPhoneNo, amountToBuyAirTime);
        }
    }

    @OnClick(R.id.btn_buy_cancel)
    public void onTapSendCancel(View view) {
        dismiss();
    }

    public interface BuyAirTimeDelegate {
        void onConfirmBuy(String recipientPhoneNo, String amountToBuy);
    }
}
