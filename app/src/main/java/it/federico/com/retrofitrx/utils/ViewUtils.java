package it.federico.com.retrofitrx.utils;

import android.view.View;

public class ViewUtils {

    public static void setViewVisible(final View view) {
        view.setVisibility(View.VISIBLE);
    }

    public static void setViewGone(final View view) {
        view.setVisibility(View.GONE);
    }

    public static void setViewInvisible(final View view) {
        view.setVisibility(View.INVISIBLE);
    }

    public static boolean isViewVisible(final View view) {
        return view.getVisibility() == View.VISIBLE;
    }
}
