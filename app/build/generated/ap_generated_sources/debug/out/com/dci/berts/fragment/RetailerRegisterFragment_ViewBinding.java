// Generated code from Butter Knife. Do not modify!
package com.dci.berts.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.dci.berts.R;
import com.dci.berts.customView.CustomEditText;
import com.github.ybq.android.spinkit.SpinKitView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RetailerRegisterFragment_ViewBinding implements Unbinder {
  private RetailerRegisterFragment target;

  @UiThread
  public RetailerRegisterFragment_ViewBinding(RetailerRegisterFragment target, View source) {
    this.target = target;

    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.edt_firstname = Utils.findRequiredViewAsType(source, R.id.edt_firstname, "field 'edt_firstname'", CustomEditText.class);
    target.edt_lastname = Utils.findRequiredViewAsType(source, R.id.edt_lastname, "field 'edt_lastname'", CustomEditText.class);
    target.edt_email = Utils.findRequiredViewAsType(source, R.id.edt_email, "field 'edt_email'", CustomEditText.class);
    target.edt_password = Utils.findRequiredViewAsType(source, R.id.edt_password, "field 'edt_password'", CustomEditText.class);
    target.edt_cnfmpassword = Utils.findRequiredViewAsType(source, R.id.edt_cnfmpassword, "field 'edt_cnfmpassword'", CustomEditText.class);
    target.btn_sigin = Utils.findRequiredViewAsType(source, R.id.btn_sigin, "field 'btn_sigin'", Button.class);
    target.btn_sigin_with_facebook = Utils.findRequiredViewAsType(source, R.id.btn_sigin_with_facebook, "field 'btn_sigin_with_facebook'", Button.class);
    target.txt_or = Utils.findRequiredViewAsType(source, R.id.txt_or, "field 'txt_or'", TextView.class);
    target.txt_terms_conditions = Utils.findRequiredViewAsType(source, R.id.txt_terms_conditions, "field 'txt_terms_conditions'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RetailerRegisterFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.spin_kit_loadingView = null;
    target.edt_firstname = null;
    target.edt_lastname = null;
    target.edt_email = null;
    target.edt_password = null;
    target.edt_cnfmpassword = null;
    target.btn_sigin = null;
    target.btn_sigin_with_facebook = null;
    target.txt_or = null;
    target.txt_terms_conditions = null;
  }
}
