// Generated code from Butter Knife. Do not modify!
package com.usehover.wm.test.dialogs;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class PromptDialog$$ViewBinder<T extends PromptDialog> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends PromptDialog> implements Unbinder {
    protected T target;

    private View view2131558504;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      target.rlRoot = finder.findRequiredViewAsType(source, 2131558494, "field 'rlRoot'", RelativeLayout.class);
      target.tvConfirmMsg = finder.findRequiredViewAsType(source, 2131558503, "field 'tvConfirmMsg'", TextView.class);
      view = finder.findRequiredView(source, 2131558504, "field 'btnOk' and method 'onTapOk'");
      target.btnOk = finder.castView(view, 2131558504, "field 'btnOk'");
      view2131558504 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onTapOk(p0);
        }
      });
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.rlRoot = null;
      target.tvConfirmMsg = null;
      target.btnOk = null;

      view2131558504.setOnClickListener(null);
      view2131558504 = null;

      this.target = null;
    }
  }
}
