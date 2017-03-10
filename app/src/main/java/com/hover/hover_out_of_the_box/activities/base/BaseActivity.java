package com.hover.hover_out_of_the_box.activities.base;

import android.support.v7.app.AppCompatActivity;

import com.hover.hover_out_of_the_box.dialogs.PromptDialog;

/**
 * Created by aung on 3/1/17.
 */

public class BaseActivity extends AppCompatActivity {

    protected PromptDialog mPromptDialog;

    protected PromptDialog getPromptDialog() {
        if (mPromptDialog == null) {
            mPromptDialog = new PromptDialog(BaseActivity.this);
        }

        return mPromptDialog;
    }
}
