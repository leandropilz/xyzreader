package com.example.xyzreader.extras;

import android.content.Context;

class MyUtils {

    static int dpToPx(Context context) {
        return (int) (8 * context.getResources().getDisplayMetrics().density);
    }
}
