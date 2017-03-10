// Generated code from Butter Knife. Do not modify!
package com.usehover.wm.test.dialogs;

import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class BuyAirTimeDialog$$ViewBinder<T extends BuyAirTimeDialog> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends BuyAirTimeDialog> implements Unbinder {
    protected T target;

    private View view2131558501;

    private View view2131558502;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      target.rlRoot = finder.findRequiredViewAsType(source, 2131558494, "field 'rlRoot'", RelativeLayout.class);
      target.etRecipientPhoneNo = finder.findRequiredViewAsType(source, 2131558498, "field 'etRecipientPhoneNo'", EditText.class);
      target.etAmountToBuyAirTime = finder.findRequiredViewAsType(source, 2131558500, "field 'etAmountToBuyAirTime'", EditText.class);
      view = finder.findRequiredView(source, 2131558501, "method 'onTapSendConfirm'");
      view2131558501 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onTapSendConfirm(p0);
        }
      });
      view = finder.findRequiredView(source, 2131558502, "method 'onTapSendCancel'");
      view2131558502 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onTapSendCancel(p0);
        }
      });
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.rlRoot = null;
      target.etRecipientPhoneNo = null;
      target.etAmountToBuyAirTime = null;

      view2131558501.setOnClickListener(null);
      view2131558501 = null;
      view2131558502.setOnClickListener(null);
      view2131558502 = null;

      this.target = null;
    }
  }
}
