// Generated by view binder compiler. Do not edit!
package com.example.wordgame.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.wordgame.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentMatchingBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton button;

  @NonNull
  public final TextView engTextView1;

  @NonNull
  public final TextView engTextView2;

  @NonNull
  public final TextView engTextView3;

  @NonNull
  public final TextView engTextView4;

  @NonNull
  public final FloatingActionButton fab;

  @NonNull
  public final Guideline guideline30;

  @NonNull
  public final Guideline guideline31;

  @NonNull
  public final Guideline guideline32;

  @NonNull
  public final Guideline guideline33;

  @NonNull
  public final Guideline guideline34;

  @NonNull
  public final Guideline guideline35;

  @NonNull
  public final Guideline guideline36;

  @NonNull
  public final Guideline guideline37;

  @NonNull
  public final Guideline guideline38;

  @NonNull
  public final Guideline guideline39;

  @NonNull
  public final Guideline guideline40;

  @NonNull
  public final Guideline guideline41;

  @NonNull
  public final Guideline guideline42;

  @NonNull
  public final Guideline guideline43;

  @NonNull
  public final Guideline guideline54;

  @NonNull
  public final Guideline guideline68;

  @NonNull
  public final TextView instructionTextView;

  @NonNull
  public final EditText xhosaEditText1;

  @NonNull
  public final EditText xhosaEditText2;

  @NonNull
  public final EditText xhosaEditText3;

  @NonNull
  public final EditText xhosaEditText4;

  @NonNull
  public final TextView xhosaMatchTextView1;

  @NonNull
  public final TextView xhosaMatchTextView2;

  @NonNull
  public final TextView xhosaMatchTextView3;

  @NonNull
  public final TextView xhosaMatchTextView4;

  @NonNull
  public final TextView xhosaMatchTextView5;

  @NonNull
  public final TextView xhosaMatchTextView6;

  @NonNull
  public final TextView xhosaMatchTextView7;

  @NonNull
  public final TextView xhosaMatchTextView8;

  private FragmentMatchingBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton button, @NonNull TextView engTextView1,
      @NonNull TextView engTextView2, @NonNull TextView engTextView3,
      @NonNull TextView engTextView4, @NonNull FloatingActionButton fab,
      @NonNull Guideline guideline30, @NonNull Guideline guideline31,
      @NonNull Guideline guideline32, @NonNull Guideline guideline33,
      @NonNull Guideline guideline34, @NonNull Guideline guideline35,
      @NonNull Guideline guideline36, @NonNull Guideline guideline37,
      @NonNull Guideline guideline38, @NonNull Guideline guideline39,
      @NonNull Guideline guideline40, @NonNull Guideline guideline41,
      @NonNull Guideline guideline42, @NonNull Guideline guideline43,
      @NonNull Guideline guideline54, @NonNull Guideline guideline68,
      @NonNull TextView instructionTextView, @NonNull EditText xhosaEditText1,
      @NonNull EditText xhosaEditText2, @NonNull EditText xhosaEditText3,
      @NonNull EditText xhosaEditText4, @NonNull TextView xhosaMatchTextView1,
      @NonNull TextView xhosaMatchTextView2, @NonNull TextView xhosaMatchTextView3,
      @NonNull TextView xhosaMatchTextView4, @NonNull TextView xhosaMatchTextView5,
      @NonNull TextView xhosaMatchTextView6, @NonNull TextView xhosaMatchTextView7,
      @NonNull TextView xhosaMatchTextView8) {
    this.rootView = rootView;
    this.button = button;
    this.engTextView1 = engTextView1;
    this.engTextView2 = engTextView2;
    this.engTextView3 = engTextView3;
    this.engTextView4 = engTextView4;
    this.fab = fab;
    this.guideline30 = guideline30;
    this.guideline31 = guideline31;
    this.guideline32 = guideline32;
    this.guideline33 = guideline33;
    this.guideline34 = guideline34;
    this.guideline35 = guideline35;
    this.guideline36 = guideline36;
    this.guideline37 = guideline37;
    this.guideline38 = guideline38;
    this.guideline39 = guideline39;
    this.guideline40 = guideline40;
    this.guideline41 = guideline41;
    this.guideline42 = guideline42;
    this.guideline43 = guideline43;
    this.guideline54 = guideline54;
    this.guideline68 = guideline68;
    this.instructionTextView = instructionTextView;
    this.xhosaEditText1 = xhosaEditText1;
    this.xhosaEditText2 = xhosaEditText2;
    this.xhosaEditText3 = xhosaEditText3;
    this.xhosaEditText4 = xhosaEditText4;
    this.xhosaMatchTextView1 = xhosaMatchTextView1;
    this.xhosaMatchTextView2 = xhosaMatchTextView2;
    this.xhosaMatchTextView3 = xhosaMatchTextView3;
    this.xhosaMatchTextView4 = xhosaMatchTextView4;
    this.xhosaMatchTextView5 = xhosaMatchTextView5;
    this.xhosaMatchTextView6 = xhosaMatchTextView6;
    this.xhosaMatchTextView7 = xhosaMatchTextView7;
    this.xhosaMatchTextView8 = xhosaMatchTextView8;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentMatchingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentMatchingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_matching, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentMatchingBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button;
      MaterialButton button = ViewBindings.findChildViewById(rootView, id);
      if (button == null) {
        break missingId;
      }

      id = R.id.engTextView1;
      TextView engTextView1 = ViewBindings.findChildViewById(rootView, id);
      if (engTextView1 == null) {
        break missingId;
      }

      id = R.id.engTextView2;
      TextView engTextView2 = ViewBindings.findChildViewById(rootView, id);
      if (engTextView2 == null) {
        break missingId;
      }

      id = R.id.engTextView3;
      TextView engTextView3 = ViewBindings.findChildViewById(rootView, id);
      if (engTextView3 == null) {
        break missingId;
      }

      id = R.id.engTextView4;
      TextView engTextView4 = ViewBindings.findChildViewById(rootView, id);
      if (engTextView4 == null) {
        break missingId;
      }

      id = R.id.fab;
      FloatingActionButton fab = ViewBindings.findChildViewById(rootView, id);
      if (fab == null) {
        break missingId;
      }

      id = R.id.guideline30;
      Guideline guideline30 = ViewBindings.findChildViewById(rootView, id);
      if (guideline30 == null) {
        break missingId;
      }

      id = R.id.guideline31;
      Guideline guideline31 = ViewBindings.findChildViewById(rootView, id);
      if (guideline31 == null) {
        break missingId;
      }

      id = R.id.guideline32;
      Guideline guideline32 = ViewBindings.findChildViewById(rootView, id);
      if (guideline32 == null) {
        break missingId;
      }

      id = R.id.guideline33;
      Guideline guideline33 = ViewBindings.findChildViewById(rootView, id);
      if (guideline33 == null) {
        break missingId;
      }

      id = R.id.guideline34;
      Guideline guideline34 = ViewBindings.findChildViewById(rootView, id);
      if (guideline34 == null) {
        break missingId;
      }

      id = R.id.guideline35;
      Guideline guideline35 = ViewBindings.findChildViewById(rootView, id);
      if (guideline35 == null) {
        break missingId;
      }

      id = R.id.guideline36;
      Guideline guideline36 = ViewBindings.findChildViewById(rootView, id);
      if (guideline36 == null) {
        break missingId;
      }

      id = R.id.guideline37;
      Guideline guideline37 = ViewBindings.findChildViewById(rootView, id);
      if (guideline37 == null) {
        break missingId;
      }

      id = R.id.guideline38;
      Guideline guideline38 = ViewBindings.findChildViewById(rootView, id);
      if (guideline38 == null) {
        break missingId;
      }

      id = R.id.guideline39;
      Guideline guideline39 = ViewBindings.findChildViewById(rootView, id);
      if (guideline39 == null) {
        break missingId;
      }

      id = R.id.guideline40;
      Guideline guideline40 = ViewBindings.findChildViewById(rootView, id);
      if (guideline40 == null) {
        break missingId;
      }

      id = R.id.guideline41;
      Guideline guideline41 = ViewBindings.findChildViewById(rootView, id);
      if (guideline41 == null) {
        break missingId;
      }

      id = R.id.guideline42;
      Guideline guideline42 = ViewBindings.findChildViewById(rootView, id);
      if (guideline42 == null) {
        break missingId;
      }

      id = R.id.guideline43;
      Guideline guideline43 = ViewBindings.findChildViewById(rootView, id);
      if (guideline43 == null) {
        break missingId;
      }

      id = R.id.guideline54;
      Guideline guideline54 = ViewBindings.findChildViewById(rootView, id);
      if (guideline54 == null) {
        break missingId;
      }

      id = R.id.guideline68;
      Guideline guideline68 = ViewBindings.findChildViewById(rootView, id);
      if (guideline68 == null) {
        break missingId;
      }

      id = R.id.instructionTextView;
      TextView instructionTextView = ViewBindings.findChildViewById(rootView, id);
      if (instructionTextView == null) {
        break missingId;
      }

      id = R.id.xhosaEditText1;
      EditText xhosaEditText1 = ViewBindings.findChildViewById(rootView, id);
      if (xhosaEditText1 == null) {
        break missingId;
      }

      id = R.id.xhosaEditText2;
      EditText xhosaEditText2 = ViewBindings.findChildViewById(rootView, id);
      if (xhosaEditText2 == null) {
        break missingId;
      }

      id = R.id.xhosaEditText3;
      EditText xhosaEditText3 = ViewBindings.findChildViewById(rootView, id);
      if (xhosaEditText3 == null) {
        break missingId;
      }

      id = R.id.xhosaEditText4;
      EditText xhosaEditText4 = ViewBindings.findChildViewById(rootView, id);
      if (xhosaEditText4 == null) {
        break missingId;
      }

      id = R.id.xhosaMatchTextView1;
      TextView xhosaMatchTextView1 = ViewBindings.findChildViewById(rootView, id);
      if (xhosaMatchTextView1 == null) {
        break missingId;
      }

      id = R.id.xhosaMatchTextView2;
      TextView xhosaMatchTextView2 = ViewBindings.findChildViewById(rootView, id);
      if (xhosaMatchTextView2 == null) {
        break missingId;
      }

      id = R.id.xhosaMatchTextView3;
      TextView xhosaMatchTextView3 = ViewBindings.findChildViewById(rootView, id);
      if (xhosaMatchTextView3 == null) {
        break missingId;
      }

      id = R.id.xhosaMatchTextView4;
      TextView xhosaMatchTextView4 = ViewBindings.findChildViewById(rootView, id);
      if (xhosaMatchTextView4 == null) {
        break missingId;
      }

      id = R.id.xhosaMatchTextView5;
      TextView xhosaMatchTextView5 = ViewBindings.findChildViewById(rootView, id);
      if (xhosaMatchTextView5 == null) {
        break missingId;
      }

      id = R.id.xhosaMatchTextView6;
      TextView xhosaMatchTextView6 = ViewBindings.findChildViewById(rootView, id);
      if (xhosaMatchTextView6 == null) {
        break missingId;
      }

      id = R.id.xhosaMatchTextView7;
      TextView xhosaMatchTextView7 = ViewBindings.findChildViewById(rootView, id);
      if (xhosaMatchTextView7 == null) {
        break missingId;
      }

      id = R.id.xhosaMatchTextView8;
      TextView xhosaMatchTextView8 = ViewBindings.findChildViewById(rootView, id);
      if (xhosaMatchTextView8 == null) {
        break missingId;
      }

      return new FragmentMatchingBinding((ConstraintLayout) rootView, button, engTextView1,
          engTextView2, engTextView3, engTextView4, fab, guideline30, guideline31, guideline32,
          guideline33, guideline34, guideline35, guideline36, guideline37, guideline38, guideline39,
          guideline40, guideline41, guideline42, guideline43, guideline54, guideline68,
          instructionTextView, xhosaEditText1, xhosaEditText2, xhosaEditText3, xhosaEditText4,
          xhosaMatchTextView1, xhosaMatchTextView2, xhosaMatchTextView3, xhosaMatchTextView4,
          xhosaMatchTextView5, xhosaMatchTextView6, xhosaMatchTextView7, xhosaMatchTextView8);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}