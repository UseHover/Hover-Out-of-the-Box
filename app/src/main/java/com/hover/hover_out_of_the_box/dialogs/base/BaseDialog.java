package com.hover.hover_out_of_the_box.dialogs.base;

import android.app.Dialog;
import android.content.Context;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by aung on 10/26/16.
 */

public abstract class BaseDialog extends Dialog {

    public BaseDialog(Context context) {
        super(context);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }

    @Subscribe
    public void onEvent(Object obj) {

    }
}
