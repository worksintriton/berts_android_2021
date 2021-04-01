// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailer;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.triton.bertsproject.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchProductListActivity_ViewBinding implements Unbinder {
  private SearchProductListActivity target;

  @UiThread
  public SearchProductListActivity_ViewBinding(SearchProductListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SearchProductListActivity_ViewBinding(SearchProductListActivity target, View source) {
    this.target = target;

    target.floatingActionButton = Utils.findRequiredViewAsType(source, R.id.fab, "field 'floatingActionButton'", FloatingActionButton.class);
    target.bottomNavigation = Utils.findRequiredViewAsType(source, R.id.bottomNavigation, "field 'bottomNavigation'", BottomNavigationView.class);
    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.rv_filterlist = Utils.findRequiredViewAsType(source, R.id.rv_filterlist, "field 'rv_filterlist'", RecyclerView.class);
    target.rv_searchprodlist = Utils.findRequiredViewAsType(source, R.id.rv_searchprodlist, "field 'rv_searchprodlist'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SearchProductListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.floatingActionButton = null;
    target.bottomNavigation = null;
    target.spin_kit_loadingView = null;
    target.rv_filterlist = null;
    target.rv_searchprodlist = null;
  }
}
