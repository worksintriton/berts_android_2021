// Generated code from Butter Knife. Do not modify!
package com.dci.berts.retailer;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.dci.berts.R;
import com.github.ybq.android.spinkit.SpinKitView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TermsAndConditionsActivity_ViewBinding implements Unbinder {
  private TermsAndConditionsActivity target;

  @UiThread
  public TermsAndConditionsActivity_ViewBinding(TermsAndConditionsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TermsAndConditionsActivity_ViewBinding(TermsAndConditionsActivity target, View source) {
    this.target = target;

    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.txt_toolbar_title = Utils.findRequiredViewAsType(source, R.id.txt_toolbar_title, "field 'txt_toolbar_title'", TextView.class);
    target.img_back = Utils.findRequiredViewAsType(source, R.id.img_back, "field 'img_back'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TermsAndConditionsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.spin_kit_loadingView = null;
    target.txt_toolbar_title = null;
    target.img_back = null;
  }
}
