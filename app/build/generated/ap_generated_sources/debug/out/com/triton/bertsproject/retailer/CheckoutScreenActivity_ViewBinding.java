// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailer;

import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.triton.bertsproject.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CheckoutScreenActivity_ViewBinding implements Unbinder {
  private CheckoutScreenActivity target;

  @UiThread
  public CheckoutScreenActivity_ViewBinding(CheckoutScreenActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CheckoutScreenActivity_ViewBinding(CheckoutScreenActivity target, View source) {
    this.target = target;

    target.ll_proceed = Utils.findRequiredViewAsType(source, R.id.ll_proceed, "field 'll_proceed'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CheckoutScreenActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ll_proceed = null;
  }
}
