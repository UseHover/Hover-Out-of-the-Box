package com.usehover.wm.test.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.hover.sdk.main.HoverParameters;
import com.usehover.wm.test.HoverWMApp;
import com.usehover.wm.test.R;
import com.usehover.wm.test.activities.base.BaseActivity;
import com.usehover.wm.test.dialogs.BuyAirTimeDialog;
import com.usehover.wm.test.dialogs.SendMoneyOffNetworkDialog;
import com.usehover.wm.test.dialogs.SendMoneyOnNetworkDialog;
import com.usehover.wm.test.utils.HoverWMConstants;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by aung on 3/2/17.
 */

public class WaveMoneyActionsActivity extends BaseActivity implements
        SendMoneyOnNetworkDialog.SendMoneyOnNetworkDelegate,
        SendMoneyOffNetworkDialog.SendMoneyOffNetworkDelegate,
        BuyAirTimeDialog.BuyAirTimeDelegate {

    private static final int RC_CHECK_BALANCE = 1001;
    private static final int RC_SEND_MONEY_ON_NETWORK = 1002;
    private static final int RC_SEND_MONEY_OFF_NETWORK = 1003;
    private static final int RC_BUY_AIRTIME = 1004;

    private SendMoneyOnNetworkDialog mSendMoneyOnNetworkDialog;
    private SendMoneyOffNetworkDialog mSendMoneyOffNetworkDialog;
    private BuyAirTimeDialog mBuyAirTimeDialog;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, WaveMoneyActionsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_money_actions);
        ButterKnife.bind(this, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            getPromptDialog().setUpPrompt(data.getStringExtra("response_message"));

            Log.d(HoverWMApp.TAG, data.getStringExtra("response_message") + " contains the message from the operator");
            Log.d(HoverWMApp.TAG, data.getLongExtra("transaction_id", -1) + " contains the id of the pending transaction");
        } else if (resultCode == Activity.RESULT_CANCELED) {
            getPromptDialog().setUpPrompt(data.getStringExtra("result"));

            Log.d(HoverWMApp.TAG, data.getStringExtra("result") + " contains the error that occured");
            if (data.hasExtra("transaction_id")) // If a request was actually made to the Mobile Money Operator
                Log.d(HoverWMApp.TAG, data.getStringExtra("transaction_id") + " contains the id of the failed transaction");
        }
    }

    @OnClick(R.id.btn_check_balance)
    public void onTapCheckBalance(View view) {
        Intent intent = new HoverParameters.Builder(this)
                .request(HoverWMConstants.WAVE_MONEY_ACTION_CHECK_BALANCE)
                .from(HoverWMConstants.WAVE_MONEY_SERVICE_ID)
                .buildIntent();
        startActivityForResult(intent, RC_CHECK_BALANCE);
    }

    @OnClick(R.id.btn_send_money_on_network)
    public void onTapSendMoneyOnNetwork(View view) {
        if (mSendMoneyOnNetworkDialog == null) {
            mSendMoneyOnNetworkDialog = new SendMoneyOnNetworkDialog(WaveMoneyActionsActivity.this);
        }

        mSendMoneyOnNetworkDialog.show(this);
    }

    @OnClick(R.id.btn_send_money_off_network)
    public void onTapSendMoneyOffNetwork(View view) {
        if (mSendMoneyOffNetworkDialog == null) {
            mSendMoneyOffNetworkDialog = new SendMoneyOffNetworkDialog(WaveMoneyActionsActivity.this);
        }

        mSendMoneyOffNetworkDialog.show(this);
    }

    @OnClick(R.id.btn_airtime_top_up_other_number)
    public void onConfirmBuyAirtime(View view) {
        if (mBuyAirTimeDialog == null) {
            mBuyAirTimeDialog = new BuyAirTimeDialog(WaveMoneyActionsActivity.this);
        }

        mBuyAirTimeDialog.show(this);
    }

    @Override
    public void onConfirmSend(String recipientPhoneNo, String amountToSend) {
        /*
        Intent intent = new HoverParameters.Builder(this)
                .request(HoverWMConstants.WAVE_MONEY_ACTION_SEND_MONEY_ON_NETWORK)
                .amount(amountToSend, HoverWMConstants.MM_CURRENCY_KYAT)
                .to(recipientPhoneNo)
                .from(HoverWMConstants.WAVE_MONEY_SERVICE_ID)
                .buildIntent();
                */

        Intent intent = new HoverParameters.Builder(this)
                .request(HoverWMConstants.WAVE_MONEY_ACTION_SEND_MONEY_ON_NETWORK,
                        amountToSend, HoverWMConstants.MM_CURRENCY_KYAT,
                        recipientPhoneNo)
                .from(HoverWMConstants.WAVE_MONEY_SERVICE_ID)
                .buildIntent();

        startActivityForResult(intent, RC_SEND_MONEY_ON_NETWORK);
    }

    @Override
    public void onConfirmSend(String recipientPhoneNo, String nrcNumber, String amountToSend, String withdrawalCode) {
        Intent intent = new HoverParameters.Builder(this)
                .request(HoverWMConstants.WAVE_MONEY_ACTION_SEND_MONEY_OFF_NETWORK,
                        amountToSend, HoverWMConstants.MM_CURRENCY_KYAT,
                        recipientPhoneNo)
                .from(HoverWMConstants.WAVE_MONEY_SERVICE_ID)
                .extra(HoverWMConstants.RECEPIENT_NRC, nrcNumber)
                .extra(HoverWMConstants.WITHDRAWAL_CODE, withdrawalCode)
                .buildIntent();

        startActivityForResult(intent, RC_SEND_MONEY_OFF_NETWORK);
    }

    @Override
    public void onConfirmBuy(String recipientPhoneNo, String amountToBuy) {
        Intent intent = new HoverParameters.Builder(this)
                .request(HoverWMConstants.WAVE_MONEY_ACTION_BUY_AIRTIME,
                        amountToBuy, HoverWMConstants.MM_CURRENCY_KYAT,
                        recipientPhoneNo)
                .from(HoverWMConstants.WAVE_MONEY_SERVICE_ID)
                .buildIntent();

        startActivityForResult(intent, RC_BUY_AIRTIME);
    }
}
