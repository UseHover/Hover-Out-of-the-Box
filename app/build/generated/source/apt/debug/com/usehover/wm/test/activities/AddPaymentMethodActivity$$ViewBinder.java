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

public class AddPaymentMethodActivity$$ViewBinder<T extends AddPaymentMethodActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends AddPaymentMethodActivity> implements Unbinder {
    protected T target;

    private View view2131558489;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      view = finder.findRequiredView(source, 2131558489, "method 'onTapAddPaymentMethod'");
      view2131558489 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onTapAddPaymentMethod(p0);
        }
      });
    }

    @Override
    public void unbind() {
      if (this.target == null) throw new IllegalStateException("Bindings already cleared.");

      view2131558489.setOnClickListener(null);
      view2131558489 = null;

      this.target = null;
    }
  }
}
