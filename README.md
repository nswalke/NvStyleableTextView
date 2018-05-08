# NvStyleableTextView
TextView, Button, EditText with custom attribute for fontName, so that fontName can be specified in xml layout itself.
#########################################
	How to install:
  
  Add these line to your project level build.gradle
  
  allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  Add this line to your module level build.gradle
  
  dependencies{
      implementation 'com.github.nswalke:NvStyleableTextView:1.0.4'
      
      // compile 'com.github.nswalke:NvStyleableTextView:1.0.4' //for older versions of gradle
  }
  
#########################################

Available classes: NvStyleableTextView, NvStyleableButton, NvStyleableEditText


All three can be used in similar way, as explained below.

#########################################
How to use:

Extend NvStyleableTextView class and override the two abstract methods as explained later below.

Add the following nameSpace to parent/container view
xmlns:app="http://schemas.android.com/apk/res-auto"

Add the view to your layout as follows:
  <com.yourcompany.YourStyleableTextView
  android:layout_width="wrap_content"
  android:layout_height="wrap_content"
  android:text="@string/hello_world"
  app:nvFontName="@string/font_arial" />

The attribute 'app:nvFontName' specifies the exact non-qualified case-sensitive name
of the .ttf font-file as an explicit string value or a reference to a string resource.

You can call setFont(strFontName) or setFont(fontNameStringResId) to set fontName in your code.

You need to include yout font files (.ttf) in assets folder and define those file names in string resources.
eg. Arial.ttf in assets folder. And <string name="font_arial">Arial.ttf</string> in strings.xml

*****Note that no specific code has been written to handle bold, italic, normal textStyles.

Override these abstract methods:

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
