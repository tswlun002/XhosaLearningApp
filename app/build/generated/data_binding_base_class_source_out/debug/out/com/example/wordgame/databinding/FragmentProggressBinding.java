// Generated by view binder compiler. Do not edit!
package com.example.wordgame.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.wordgame.R;
import com.google.android.material.navigation.NavigationView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentProggressBinding implements ViewBinding {
  @NonNull
  private final DrawerLayout rootView;

  @NonNull
  public final FragmentContainerView ResultsHotsFrag;

  @NonNull
  public final DrawerLayout drawer;

  @NonNull
  public final NavigationView navView;

  private FragmentProggressBinding(@NonNull DrawerLayout rootView,
      @NonNull FragmentContainerView ResultsHotsFrag, @NonNull DrawerLayout drawer,
      @NonNull NavigationView navView) {
    this.rootView = rootView;
    this.ResultsHotsFrag = ResultsHotsFrag;
    this.drawer = drawer;
    this.navView = navView;
  }

  @Override
  @NonNull
  public DrawerLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentProggressBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentProggressBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_proggress, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentProggressBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.ResultsHots_frag;
      FragmentContainerView ResultsHotsFrag = ViewBindings.findChildViewById(rootView, id);
      if (ResultsHotsFrag == null) {
        break missingId;
      }

      DrawerLayout drawer = (DrawerLayout) rootView;

      id = R.id.nav_view;
      NavigationView navView = ViewBindings.findChildViewById(rootView, id);
      if (navView == null) {
        break missingId;
      }

      return new FragmentProggressBinding((DrawerLayout) rootView, ResultsHotsFrag, drawer,
          navView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}