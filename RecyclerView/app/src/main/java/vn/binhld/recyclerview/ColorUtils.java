package vn.binhld.recyclerview;

import android.content.Context;
import android.graphics.Color;

import androidx.core.content.ContextCompat;

/**
 * ColorUtils is a class with one method, used to color the ViewHolders in
 * the RecyclerView. I put in a separate class in an attempt to keep the
 * code organized.
 */
public class ColorUtils {

    private static final int N = 17;

    public static int getViewHolderBackgroundColorFromInstance(Context context, int instanceNum) {
        switch (instanceNum%N) {
            case 0:
                return ContextCompat.getColor(context, R.color.material50Green);
            case 1:
                return ContextCompat.getColor(context, R.color.material100Green);
            case 2:
                return ContextCompat.getColor(context, R.color.material150Green);
            case 3:
                return ContextCompat.getColor(context, R.color.material200Green);
            case 4:
                return ContextCompat.getColor(context, R.color.material250Green);
            case 5:
                return ContextCompat.getColor(context, R.color.material300Green);
            case 6:
                return ContextCompat.getColor(context, R.color.material350Green);
            case 7:
                return ContextCompat.getColor(context, R.color.material400Green);
            case 8:
                return ContextCompat.getColor(context, R.color.material450Green);
            case 9:
                return ContextCompat.getColor(context, R.color.material500Green);
            case 10:
                return ContextCompat.getColor(context, R.color.material550Green);
            case 11:
                return ContextCompat.getColor(context, R.color.material600Green);
            case 12:
                return ContextCompat.getColor(context, R.color.material650Green);
            case 13:
                return ContextCompat.getColor(context, R.color.material700Green);
            case 14:
                return ContextCompat.getColor(context, R.color.material750Green);
            case 15:
                return ContextCompat.getColor(context, R.color.material800Green);
            case 16:
                return ContextCompat.getColor(context, R.color.material850Green);
            case 17:
                return ContextCompat.getColor(context, R.color.material900Green);
            default:
                return Color.WHITE;
        }
    }
}
