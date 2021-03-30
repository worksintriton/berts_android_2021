// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.fragment;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.triton.bertsproject.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RetailerRegisterFragment_ViewBinding implements Unbinder {
  private RetailerRegisterFragment target;

  @UiThread
  public RetailerRegisterFragment_ViewBinding(RetailerRegisterFragment target, View source) {
    this.target = target;

    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RetailerRegisterFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.spin_kit_loadingView = null;
  }
}
