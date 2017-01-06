package color.kidpaint.com.kidpaintcolor.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.ContextThemeWrapper;

import color.kidpaint.com.kidpaintcolor.R;


public class CustomAlertDialogBuilder extends AlertDialog.Builder {
	public CustomAlertDialogBuilder(Context context) {
		super(new ContextThemeWrapper(context, R.style.CustomPaintroidDialog));
	}
}
