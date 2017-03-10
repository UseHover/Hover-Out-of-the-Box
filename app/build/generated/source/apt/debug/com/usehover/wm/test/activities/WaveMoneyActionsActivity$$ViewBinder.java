// Generated code from Butter Knife. Do not modify!
package com.usehover.wm.test.activities;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class WaveMoneyActionsActivity$$ViewBinder<T extends WaveMoneyActionsActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends WaveMoneyActionsActivity> implements Unbinder {
    protected T target;

    private View view2131558490;

    private View view2131558491;

    private View view2131558492;

    private View view2131558493;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      view = finder.findRequiredView(source, 2131558490, "method 'onTapCheckBalance'");
      view2131558490 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onTapCheckBalance(p0);
        }
      });
      view = finder.findRequiredView(source, 2131558491, "method 'onTapSendMoneyOnNetwork'");
      view2131558491 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onTapSendMoneyOnNetwork(p0);
        }
      });
      view = finder.findRequiredView(source, 2131558492, "method 'onTapSendMoneyOffNetwork'");
      view2131558492 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onTapSendMoneyOffNetwork(p0);
        }
      });
      view = finder.findRequiredView(source, 2131558493, "method 'onConfirmBuyAirtime'");
      view2131558493 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onConfirmBuyAirtime(p0);
        }
      });
    }

    @Override
    public void unbind() {
      if (this.target == null) throw new IllegalStateException("Bindings already cleared.");

      view2131558490.setOnClickListener(null);
      view2131558490 = null;
      view2131558491.setOnClickListener(null);
      view2131558491 = null;
      view2131558492.setOnClickListener(null);
      view2131558492 = null;
      view2131558493.setOnClickListener(null);
      view2131558493 = null;

      this.target = null;
    }
  }
}
