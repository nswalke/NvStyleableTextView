package com.viral.nirmal.nvstyleabletextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

/**
 * Custom Button with custom xml attribute to specify font in xml layout
 * ***
 * ***
 * add the following nameSpace to parent/container view
 * xmlns:app="http://schemas.android.com/apk/res-auto"
 * <p>
 * <p>
 * Extend this class and add the view to your layout as follows:
 * <com.yourcompany.YourStyleableButton
 * android:layout_width="wrap_content"
 * android:layout_height="wrap_content"
 * android:text="@string/hello_world"
 * app:nvFontName="@string/font_arial" />
 * ***
 * ***
 * The attribute 'app:nvFontName' specifies the exact non-qualified case-sensitive name
 * of the .ttf font-file as an explicit string value or a reference to a string resource
 */
public abstract class NvStyleableButton extends AppCompatButton {
    public NvStyleableButton(Context context) {
        super(context);
    }

    public NvStyleableButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            setFont(context, attrs);
        }
    }

    public NvStyleableButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) {
            setFont(context, attrs);
        }
    }

    private void setFont(Context context, AttributeSet attrs) {
        //Load attributes
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NvStyleableTextView);
        String fontName;
        try {
            fontName = typedArray.getString(R.styleable.NvStyleableTextView_nvFontName);
            setFont(context, fontName);
        } finally {
            typedArray.recycle();
        }
    }

    public final void setFont(Context context, String fontName) {
        if (fontName == null) {
            //if font is not specified in xml layout then fontName will be null
            //use a default font if fontName is null
            fontName = getDefaultFontName(context);
        }
        if (fontName != null) {
            Typeface typeface = getFont(fontName, context);
            if (typeface != null) {
                setTypeface(typeface);
            }
        }
    }

    public final void setFont(Context context, int fontNameStringResId) {
        String fontName;
        if (fontNameStringResId <= 0) {
            //if font is not specified in xml layout then fontName will be null
            //use a default font if fontName is null
            fontName = getDefaultFontName(context);
        } else {
            fontName = context.getString(fontNameStringResId);
        }
        Typeface typeface = getFont(fontName, context);
        if (typeface != null) {
            setTypeface(typeface);
        }
    }

    /**
     * Get default font name. If font name is not given in xml layout, this will be passed on to
     * the method getFont(String fontName, Context context).
     * Use font-file name for simplicity.
     * Return null to avoid setting font.
     *
     * @param context Context instance, to get string resource, if needed.
     * @return Default font name.
     */
    @Nullable
    public abstract String getDefaultFontName(Context context);

    /**
     * Get Typeface instance representing the given font name. Return null to avoid setting font.
     * For good performance, create a HashMap<fontName,typeface> in your Application subClass and initialize it with
     * all your typefaces present in assets folder. Use those values here, rather than creating new typeface every time.
     *
     * @param fontName name of the desired font
     * @param context  Context instance
     * @return Typeface instance corresponding to the given fontName.
     */
    @Nullable
    public abstract Typeface getFont(String fontName, Context context);
}
