// Generated by view binder compiler. Do not edit!
package com.example.wordgame.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.wordgame.R;
import com.google.android.material.button.MaterialButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class TrueFalseAdapterBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView QuestionImageViewID;

  @NonNull
  public final MaterialButton falseButton;

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
  public final Guideline guideline38;

  @NonNull
  public final Guideline guideline39;

  @NonNull
  public final Guideline guideline58;

  @NonNull
  public final TextView page;

  @NonNull
  public final TextView picQuestionTextViewID;

  @NonNull
  public final MaterialButton trueButton;

  private TrueFalseAdapterBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageView QuestionImageViewID, @NonNull MaterialButton falseButton,
      @NonNull Guideline guideline31, @NonNull Guideline guideline32,
      @NonNull Guideline guideline33, @NonNull Guideline guideline34,
      @NonNull Guideline guideline35, @NonNull Guideline guideline36,
      @NonNull Guideline guideline38, @NonNull Guideline guideline39,
      @NonNull Guideline guideline58, @NonNull TextView page,
      @NonNull TextView picQuestionTextViewID, @NonNull MaterialButton trueButton) {
    this.rootView = rootView;
    this.QuestionImageViewID = QuestionImageViewID;
    this.falseButton = falseButton;
    this.guideline31 = guideline31;
    this.guideline32 = guideline32;
    this.guideline33 = guideline33;
    this.guideline34 = guideline34;
    this.guideline35 = guideline35;
    this.guideline36 = guideline36;
    this.guideline38 = guideline38;
    this.guideline39 = guideline39;
    this.guideline58 = guideline58;
    this.page = page;
    this.picQuestionTextViewID = picQuestionTextViewID;
    this.trueButton = trueButton;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static TrueFalseAdapterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static TrueFalseAdapterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.true_false_adapter, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static TrueFalseAdapterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.QuestionImageViewID;
      ImageView QuestionImageViewID = ViewBindings.findChildViewById(rootView, id);
      if (QuestionImageViewID == null) {
        break missingId;
      }

      id = R.id.falseButton;
      MaterialButton falseButton = ViewBindings.findChildViewById(rootView, id);
      if (falseButton == null) {
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

      id = R.id.guideline58;
      Guideline guideline58 = ViewBindings.findChildViewById(rootView, id);
      if (guideline58 == null) {
        break missingId;
      }

      id = R.id.page;
      TextView page = ViewBindings.findChildViewById(rootView, id);
      if (page == null) {
        break missingId;
      }

      id = R.id.picQuestionTextViewID;
      TextView picQuestionTextViewID = ViewBindings.findChildViewById(rootView, id);
      if (picQuestionTextViewID == null) {
        break missingId;
      }

      id = R.id.trueButton;
      MaterialButton trueButton = ViewBindings.findChildViewById(rootView, id);
      if (trueButton == null) {
        break missingId;
      }

      return new TrueFalseAdapterBinding((ConstraintLayout) rootView, QuestionImageViewID,
          falseButton, guideline31, guideline32, guideline33, guideline34, guideline35, guideline36,
          guideline38, guideline39, guideline58, page, picQuestionTextViewID, trueButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
