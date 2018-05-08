package com.viral.nirmal.nvstyleabletextview;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;

final class NvStyleableUtil {

    private HashMap<String, Typeface> fonts;

    private static NvStyleableUtil mInstance = null;

    private NvStyleableUtil() {
        fonts = new HashMap<>();
    }

    static synchronized NvStyleableUtil getInstance() {
        if (mInstance == null) {
            mInstance = new NvStyleableUtil();
        }
        return mInstance;
    }

    /**
     * Get Typeface instance associated with given fontName
     *
     * @param fontName name of font-file
     * @param context  context, to access assets
     * @return Typeface instance, null if fontName is null or empty
     */
    Typeface getFontFromAsset(@Nullable String fontName, @NonNull Context context) {
        if (fontName == null || fontName.trim().isEmpty()) {
            return null;
        } else {
            if (!fonts.containsKey(fontName)) {
                Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontName);
                fonts.put(fontName, typeface);
            }
            return fonts.get(fontName);
        }
    }
}
