package com.aabusabra.hizligeliyodemo.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aabusabra.hizligeliyodemo.HizliGeliyoApp;
import com.aabusabra.hizligeliyodemo.R;
import com.aabusabra.hizligeliyodemo.helper.Constants;
import com.aabusabra.hizligeliyodemo.sharedPreferences.SharedPreferencesService;
import com.amplitude.api.Amplitude;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Utils {


    private static final String TAG = Utils.class.getSimpleName();
    private static SharedPreferencesService sharedPreferencesService;

    public static SharedPreferencesService getSharedPreferences() {
        if (sharedPreferencesService == null)
            sharedPreferencesService = new SharedPreferencesService(PreferenceManager
                    .getDefaultSharedPreferences(HizliGeliyoApp.getContext()));
        return sharedPreferencesService;
    }

    public static SharedPreferencesService getSharedPreferences(Context context) {
        if (sharedPreferencesService == null)
            sharedPreferencesService = new SharedPreferencesService(PreferenceManager
                    .getDefaultSharedPreferences(context));
        return sharedPreferencesService;
    }

    public static void sendExceptionEvent(Class clazz, Throwable throwable) {
        JSONObject eventProperties = new JSONObject();
        try {
            eventProperties.put("class", clazz.getSimpleName());
            eventProperties.put("message", throwable.getMessage());
            if (throwable.getStackTrace() != null) {
                for (int i = 0; i < throwable.getStackTrace().length; i++) {
                    StackTraceElement element = throwable.getStackTrace()[i];
                    if (element != null)
                        eventProperties.put("stacktrace" + i, element.toString());
                }
            }
        } catch (JSONException exception) {
        }
        Amplitude.getInstance().logEvent("exception", eventProperties);
    }


    public static void overrideLightFonts(final View... views) {
        try {
            Context context = HizliGeliyoApp.getContext();
            String font = Constants.FONT_Titillium_LIGHT;
            for (View v : views) {
                if (v instanceof TextView) {
                    ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), font));
                } else if (v instanceof EditText) {
                    ((EditText) v).setTypeface(Typeface.createFromAsset(context.getAssets(), font));
                } else if (v instanceof TextInputLayout) {
                    ((TextInputLayout) v).setTypeface(Typeface.createFromAsset(context.getAssets
                            (), font));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void overrideBoldFonts(final View... views) {
        try {
            Context context = HizliGeliyoApp.getContext();
            String font = Constants.FONT_Titillium_BOLD;
            for (View v : views) {
                if (v instanceof TextView) {
                    ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), font));
                } else if (v instanceof EditText) {
                    ((EditText) v).setTypeface(Typeface.createFromAsset(context.getAssets(), font));
                } else if (v instanceof TextInputLayout) {
                    ((TextInputLayout) v).setTypeface(Typeface.createFromAsset(context.getAssets
                            (), font));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void overrideMediumFonts(final View... views) {
        try {
            Context context = HizliGeliyoApp.getContext();
            String font = Constants.FONT_Titillium_MEDIUM;
            for (View v : views) {
                if (v instanceof TextView) {
                    ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), font));
                } else if (v instanceof EditText) {
                    ((EditText) v).setTypeface(Typeface.createFromAsset(context.getAssets(), font));
                } else if (v instanceof TextInputLayout) {
                    ((TextInputLayout) v).setTypeface(Typeface.createFromAsset(context.getAssets
                            (), font));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void overrideRegularFonts(final View... views) {
        try {
            Context context = HizliGeliyoApp.getContext();
            String font = Constants.FONT_Titillium_REG;
            for (View v : views) {
                if (v instanceof TextView) {
                    ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), font));
                } else if (v instanceof EditText) {
                    ((EditText) v).setTypeface(Typeface.createFromAsset(context.getAssets(), font));
                } else if (v instanceof TextInputLayout) {
                    ((TextInputLayout) v).setTypeface(Typeface.createFromAsset(context.getAssets
                            (), font));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<View> getViewsByTag(final View v, final String tag) {
        List<View> views = new ArrayList<>();
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    if (child instanceof ViewGroup) {
                        if (child instanceof TextInputLayout)
                            views.add(child);
                        views.addAll(getViewsByTag(child, tag));
                    } else if (child instanceof TextView
                            || child instanceof EditText
                            || child instanceof Button
                            || child instanceof TextInputEditText
                            || child instanceof TextInputLayout
                    ) {
                        if (tag.equals(child.getTag())) {
                            views.add(child);
                        }
                    }
                }
            } else if (v instanceof TextView) {
                if (tag.equals(v.getTag())) {
                    views.add(v);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return views;
    }


    public static void overrideFonts(final Context context, final View v) {
        try {
            List<View> light = getViewsByTag(v, context.getString(R.string.text_light));
            List<View> mediums = getViewsByTag(v, context.getString(R.string.text_medium));
            List<View> normal = getViewsByTag(v, context.getString(R.string
                    .text_medium_italic));
            List<View> bolds = getViewsByTag(v, context.getString(R.string.text_bold));

            overrideLightFonts(light.toArray(new View[light.size()]));
            overrideMediumFonts(mediums.toArray(new View[mediums.size()]));
            overrideRegularFonts(normal.toArray(new View[normal.size()]));
            overrideBoldFonts(bolds.toArray(new View[bolds.size()]));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static boolean haveNetworkConnection(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }


    //Hide keyboard
    public static void setupKeyboardHider(final Activity activity, View mainView) {

        if (!(mainView instanceof EditText)) {
            mainView.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    View focusedView = activity.getCurrentFocus();
                    if (focusedView != null) {
                        try {
                            focusedView.clearFocus();
                            InputMethodManager inputMethodManager = (InputMethodManager)
                                    activity.getSystemService(Activity.INPUT_METHOD_SERVICE);

                            inputMethodManager.hideSoftInputFromWindow(
                                    activity.getCurrentFocus().getWindowToken(), 0);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    return false;
                }
            });
            if (mainView instanceof ViewGroup) {
                for (int i = 0; i < ((ViewGroup) mainView).getChildCount(); i++) {
                    View innerView = ((ViewGroup) mainView).getChildAt(i);
                    setupKeyboardHider(activity, innerView);
                }
            }
        }
    }

}
