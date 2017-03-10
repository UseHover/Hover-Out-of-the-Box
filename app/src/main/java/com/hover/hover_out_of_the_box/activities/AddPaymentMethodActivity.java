package com.hover.hover_out_of_the_box.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hover.hover_out_of_the_box.HoverWMApp;
import com.hover.hover_out_of_the_box.R;
import com.hover.hover_out_of_the_box.activities.base.BaseActivity;
import com.hover.hover_out_of_the_box.data.vos.PaymentIntegrationSuccessVO;
import com.hover.hover_out_of_the_box.utils.AppStateUtils;
import com.hover.hover_out_of_the_box.utils.HoverWMConstants;
import com.hover.hover_out_of_the_box.utils.NetworkUtils;
import com.hover.hover_out_of_the_box.utils.ScreenUtils;
import com.hover.sdk.onboarding.HoverIntegrationActivity;
import com.hover.sdk.operators.Permission;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddPaymentMethodActivity extends BaseActivity {

    private static final int RC_INTEGRATE_REQUEST = 2001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment_method);
        ButterKnife.bind(this, this);
        ScreenUtils.getInstance().initScreenDimension(AddPaymentMethodActivity.this);

        if (AppStateUtils.getInstance().isPaymentAlreadyIntegrated()) {
            navigateToWaveMoneyActions();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            addHoverIntegration();
        } else {
            // Explain why you need the permission and ask again
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Log.d(HoverWMApp.TAG, data.getStringExtra("response_message") + " contains the message from the operator");
            Log.d(HoverWMApp.TAG, data.getLongExtra("transaction_id", -1) + " contains the id of the pending transaction");
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Log.d(HoverWMApp.TAG, data.getStringExtra("result") + " contains the error that occured");
            if (data.hasExtra("transaction_id")) // If a request was actually made to the Mobile Money Operator
                Log.d(HoverWMApp.TAG, data.getStringExtra("transaction_id") + " contains the id of the failed transaction");
        }

        if (requestCode == RC_INTEGRATE_REQUEST && resultCode == RESULT_CANCELED) {
            data.getStringExtra("error");
        } else if (requestCode == RC_INTEGRATE_REQUEST && resultCode == RESULT_OK) {
            int serviceId = data.getIntExtra("serviceId", -1);
            String serviceName = data.getStringExtra("serviceName");
            String currency = data.getStringExtra("currency");
            String countryName = data.getStringExtra("countryName");
            String operatorName = data.getStringExtra("opSlug");

            Toast.makeText(getApplicationContext(), "onSuccess : serviceId - " + serviceId + " | serviceName - " + serviceName + " | operatorName - " + operatorName + " | countryName - " + countryName + " | currency - " + currency, Toast.LENGTH_SHORT).show();

            PaymentIntegrationSuccessVO successIntegration = PaymentIntegrationSuccessVO.newPaymentIntegrationSuccess(serviceId, serviceName, operatorName, countryName, currency);
            AppStateUtils.getInstance().saveSuccessPaymentIntegration(successIntegration);

            navigateToWaveMoneyActions();
        }
    }

    @OnClick(R.id.btn_add_payment_method)
    public void onTapAddPaymentMethod(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 0);
        } else {
            addHoverIntegration();
        }
    }

    /*
    @Override
    public void onError(String message) {
        Log.d(HoverWMApp.TAG, "onError : " + message);
        Toast.makeText(getApplicationContext(), "onError : " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSIMError(String message) {
        Log.d(HoverWMApp.TAG, "onSIMError : " + message);
        Toast.makeText(getApplicationContext(), "onSIMError : " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserDenied() {
        Log.d(HoverWMApp.TAG, "onUserDenied");
        Toast.makeText(getApplicationContext(), "onUserDenied", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(int serviceId, String serviceName, String operatorName, String countryName, String currency) {
        Log.d(HoverWMApp.TAG, "onSuccess : serviceId - " + serviceId + " | serviceName - " + serviceName + " | operatorName - " + operatorName + " | countryName - " + countryName + " | currency - " + currency);
        Toast.makeText(getApplicationContext(), "onSuccess : serviceId - " + serviceId + " | serviceName - " + serviceName + " | operatorName - " + operatorName + " | countryName - " + countryName + " | currency - " + currency, Toast.LENGTH_SHORT).show();

        PaymentIntegrationSuccessVO successIntegration = PaymentIntegrationSuccessVO.newPaymentIntegrationSuccess(serviceId, serviceName, operatorName, countryName, currency);
        AppStateUtils.getInstance().saveSuccessPaymentIntegration(successIntegration);

        navigateToWaveMoneyActions();
    }
    */

    private void addHoverIntegration() {
        if (NetworkUtils.getInstance().isOnline()) {
            //HoverIntegration.add(HoverWMConstants.WAVE_MONEY_SERVICE_ID, Permission.NORMAL, this, this);
            Intent integrationIntent = new Intent(this, HoverIntegrationActivity.class);
            integrationIntent.putExtra(HoverIntegrationActivity.SERVICE_IDS, new int[] { HoverWMConstants.WAVE_MONEY_SERVICE_ID });
            integrationIntent.putExtra(HoverIntegrationActivity.PERM_LEVEL, Permission.NORMAL);
            startActivityForResult(integrationIntent, RC_INTEGRATE_REQUEST);
        } else {
            getPromptDialog().setUpPrompt(getString(R.string.prompt_offline));
        }
    }

    private void navigateToWaveMoneyActions() {
        Intent intent = WaveMoneyActionsActivity.newIntent(getApplicationContext());
        startActivity(intent);
    }
}
