// Generated by view binder compiler. Do not edit!
package com.example.wordgame.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.wordgame.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class HeaderBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView lunga;

  @NonNull
  public final ImageView tsewu;

  private HeaderBinding(@NonNull LinearLayout rootView, @NonNull TextView lunga,
      @NonNull ImageView tsewu) {
    this.rootView = rootView;
    this.lunga = lunga;
    this.tsewu = tsewu;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static HeaderBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static HeaderBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.header, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static HeaderBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.lunga;
      TextView lunga = ViewBindings.findChildViewById(rootView, id);
      if (lunga == null) {
        break missingId;
      }

      id = R.id.tsewu;
      ImageView tsewu = ViewBindings.findChildViewById(rootView, id);
      if (tsewu == null) {
        break missingId;
      }

      return new HeaderBinding((LinearLayout) rootView, lunga, tsewu);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
