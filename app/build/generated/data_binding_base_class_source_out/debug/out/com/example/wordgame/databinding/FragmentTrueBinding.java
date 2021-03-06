// Generated by view binder compiler. Do not edit!
package com.example.wordgame.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.wordgame.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentTrueBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final FloatingActionButton fab;

  @NonNull
  public final Guideline guideline29;

  @NonNull
  public final Guideline guideline62;

  @NonNull
  public final Guideline guideline63;

  @NonNull
  public final RecyclerView listviewIDtrueAdapter;

  @NonNull
  public final MaterialButton submit;

  private FragmentTrueBinding(@NonNull ConstraintLayout rootView, @NonNull FloatingActionButton fab,
      @NonNull Guideline guideline29, @NonNull Guideline guideline62,
      @NonNull Guideline guideline63, @NonNull RecyclerView listviewIDtrueAdapter,
      @NonNull MaterialButton submit) {
    this.rootView = rootView;
    this.fab = fab;
    this.guideline29 = guideline29;
    this.guideline62 = guideline62;
    this.guideline63 = guideline63;
    this.listviewIDtrueAdapter = listviewIDtrueAdapter;
    this.submit = submit;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentTrueBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentTrueBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_true, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentTrueBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.fab;
      FloatingActionButton fab = ViewBindings.findChildViewById(rootView, id);
      if (fab == null) {
        break missingId;
      }

      id = R.id.guideline29;
      Guideline guideline29 = ViewBindings.findChildViewById(rootView, id);
      if (guideline29 == null) {
        break missingId;
      }

      id = R.id.guideline62;
      Guideline guideline62 = ViewBindings.findChildViewById(rootView, id);
      if (guideline62 == null) {
        break missingId;
      }

      id = R.id.guideline63;
      Guideline guideline63 = ViewBindings.findChildViewById(rootView, id);
      if (guideline63 == null) {
        break missingId;
      }

      id = R.id.listviewIDtrueAdapter;
      RecyclerView listviewIDtrueAdapter = ViewBindings.findChildViewById(rootView, id);
      if (listviewIDtrueAdapter == null) {
        break missingId;
      }

      id = R.id.submit;
      MaterialButton submit = ViewBindings.findChildViewById(rootView, id);
      if (submit == null) {
        break missingId;
      }

      return new FragmentTrueBinding((ConstraintLayout) rootView, fab, guideline29, guideline62,
          guideline63, listviewIDtrueAdapter, submit);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
