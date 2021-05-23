// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailer;

import android.view.View;
import android.widget.Button;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.triton.bertsproject.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProductDetailDescriptionActivity_ViewBinding implements Unbinder {
  private ProductDetailDescriptionActivity target;

  @UiThread
  public ProductDetailDescriptionActivity_ViewBinding(ProductDetailDescriptionActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ProductDetailDescriptionActivity_ViewBinding(ProductDetailDescriptionActivity target,
      View source) {
    this.target = target;

    target.btn_addcart = Utils.findRequiredViewAsType(source, R.id.btn_addcart, "field 'btn_addcart'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProductDetailDescriptionActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btn_addcart = null;
  }
}
