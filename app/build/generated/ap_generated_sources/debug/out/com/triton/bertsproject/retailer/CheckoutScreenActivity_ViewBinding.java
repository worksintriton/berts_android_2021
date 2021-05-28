// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailer;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ybq.android.spinkit.SpinKitView;
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

    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.ll_proceed = Utils.findRequiredViewAsType(source, R.id.ll_proceed, "field 'll_proceed'", LinearLayout.class);
    target.txt_toolbar_title = Utils.findRequiredViewAsType(source, R.id.txt_toolbar_title, "field 'txt_toolbar_title'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CheckoutScreenActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.spin_kit_loadingView = null;
    target.ll_proceed = null;
    target.txt_toolbar_title = null;
  }
}
