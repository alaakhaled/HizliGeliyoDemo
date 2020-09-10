package com.aabusabra.hizligeliyodemo.widgets;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.aabusabra.hizligeliyodemo.R;
import com.aabusabra.hizligeliyodemo.utils.Utils;

import butterknife.BindView;


public class CustomAlertDialog extends Dialog {

    @BindView(R.id.main_container)
    LinearLayout mainContainer;

    private AppCompatTextView tvTitle, tvText;
    private AppCompatButton btnPositive, btnNegative;
    private View titleContainer;
    private View.OnClickListener positiveListener, negativeListener;

    private String text, title, positiveText, negativeText;
    private Boolean isCentered=false;

    public CustomAlertDialog(Context context) {
        super(context, R.style.MyDialogStyle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.custome_alert_dialog);
//        ButterKnife.bind(this);


        getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        Utils.overrideFonts(getContext(),mainContainer);

        titleContainer = findViewById(R.id.title_container);

        tvText = ((AppCompatTextView)findViewById(R.id.dialog_text));
        tvTitle = ((AppCompatTextView)findViewById(R.id.dialog_title));



        btnPositive = (AppCompatButton) findViewById(R.id.positive_button);
        btnPositive.setVisibility(View.GONE);

        btnNegative = (AppCompatButton) findViewById(R.id.negative_button);
        btnNegative.setVisibility(View.GONE);


        refresh();
    }

    public void setText(String text) {
        this.text = text;
        refresh();
    }

    public void setTitle(String title) {
        this.title = title;
        refresh();
    }

    public void centeredTexts(boolean isCentered){
        this.isCentered = isCentered;
        refresh();
    }

    public void setFields(String text, String title) {
        this.text = text;
        this.title = title;
        refresh();
    }

    public void setPositiveButton(String text, View.OnClickListener listener) {
        positiveText = text;
        positiveListener = listener;
        refresh();
    }

    public void setNegativeButton(String text, View.OnClickListener listener) {
        negativeText = text;
        negativeListener = listener;
        refresh();
    }

    private void refresh() {

        if(tvText != null) {
            if(text != null) {
                tvText.setText(text);
                if (isCentered)
                {
                    tvText.setGravity(Gravity.CENTER);
                }
            }
            if(title != null) {
                tvTitle.setText(title);
                if (isCentered)
                {
                    tvTitle.setGravity(Gravity.CENTER);
                    ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) titleContainer.getLayoutParams();
                    LinearLayout.LayoutParams attributLayoutParams = (LinearLayout.LayoutParams) titleContainer.getLayoutParams();
                    attributLayoutParams.gravity = Gravity.CENTER;

//                    lp.(LinearLayout.CENTER_IN_PARENT);
                }
                if("".equals(title)) {
                    this.titleContainer.setVisibility(View.GONE);
                }else {
                    this.titleContainer.setVisibility(View.VISIBLE);
                }
            }else {
                this.titleContainer.setVisibility(View.GONE);
            }
            if(positiveText != null) {
                btnPositive.setText(positiveText);
                btnPositive.setOnClickListener(positiveListener);
                btnPositive.setVisibility(View.VISIBLE);
            }else {
                btnPositive.setVisibility(View.GONE);
            }
            if(negativeText != null) {
                btnNegative.setText(negativeText);
                btnNegative.setOnClickListener(negativeListener);
                btnNegative.setVisibility(View.VISIBLE);
            }else {
                btnNegative.setVisibility(View.GONE);
            }
        }
    }



}
