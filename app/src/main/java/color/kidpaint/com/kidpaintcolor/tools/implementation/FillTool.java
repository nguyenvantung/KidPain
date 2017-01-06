/**
 *  Paintroid: An image manipulation application for Android.
 *  Copyright (C) 2010-2015 The Catrobat Team
 *  (<http://developer.catrobat.org/credits>)
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package color.kidpaint.com.kidpaintcolor.tools.implementation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.v4.app.FragmentManager;

import color.kidpaint.com.kidpaintcolor.AnimalsColoringApplication;
import color.kidpaint.com.kidpaintcolor.ColorActivity;
import color.kidpaint.com.kidpaintcolor.R;
import color.kidpaint.com.kidpaintcolor.command.Command;
import color.kidpaint.com.kidpaintcolor.command.implementation.FillCommand;
import color.kidpaint.com.kidpaintcolor.dialog.FillToolDialog;
import color.kidpaint.com.kidpaintcolor.dialog.IndeterminateProgressDialog;
import color.kidpaint.com.kidpaintcolor.tools.ToolType;


public class FillTool extends BaseTool {
	public static final int MAX_TOLERANCE = 510;

	private FillToolDialog.OnFillToolDialogChangedListener mOnFillToolDialogChangedListener;
	private float mColorTolerance;

	public FillTool(Context context, ToolType toolType) {
		super(context, toolType);
		mColorTolerance = getToleranceAbsoluteValue(FillToolDialog.getInstance().getColorTolerance());
		setupOnFillToolDialogChangedListener();
	}

	private void showFillToolDialog() {
		FragmentManager fm = ((ColorActivity) mContext).getSupportFragmentManager();
		FillToolDialog.getInstance().show(fm, "filltool");
	}

	private void setupOnFillToolDialogChangedListener() {
		mOnFillToolDialogChangedListener = new FillToolDialog.OnFillToolDialogChangedListener() {
			@Override
			public void updateColorTolerance(int colorToleranceInPercent) {
				mColorTolerance = getToleranceAbsoluteValue(colorToleranceInPercent);
			}
		};
		FillToolDialog.getInstance().setOnFillToolDialogChangedListener(mOnFillToolDialogChangedListener);
	}

	public float getToleranceAbsoluteValue(int toleranceInPercent) {
		return (MAX_TOLERANCE*toleranceInPercent) / 100.0f;
	}

	@Override
	public boolean handleDown(PointF coordinate) {
		return false;
	}

	@Override
	public boolean handleMove(PointF coordinate) {
		return false;
	}

	@Override
	public boolean handleUp(PointF coordinate) {
		int bitmapHeight = AnimalsColoringApplication.drawingSurface
				.getBitmapHeight();
		int bitmapWidth = AnimalsColoringApplication.drawingSurface.getBitmapWidth();

		if ((coordinate.x > bitmapWidth) || (coordinate.y > bitmapHeight)
				|| (coordinate.x < 0) || (coordinate.y < 0)) {
			return false;
		}

		if (mColorTolerance == 0 && mBitmapPaint.getColor() == AnimalsColoringApplication.drawingSurface.getPixel(coordinate)) {
			return false;
		}

		Command command = new FillCommand(new Point((int) coordinate.x, (int) coordinate.y), mBitmapPaint, mColorTolerance);

		IndeterminateProgressDialog.getInstance().show();
		((FillCommand) command).addObserver(this);
		AnimalsColoringApplication.commandManager.commitCommand(command);

		return true;
	}

	@Override
	public int getAttributeButtonResource(ToolButtonIDs buttonNumber) {
		switch (buttonNumber) {
		case BUTTON_ID_PARAMETER_TOP:
			return getStrokeColorResource();
		case BUTTON_ID_PARAMETER_BOTTOM_1:
			return R.drawable.icon_fill_options;
		case BUTTON_ID_PARAMETER_BOTTOM_2:
			return R.drawable.icon_menu_color_palette;
		default:
			return super.getAttributeButtonResource(buttonNumber);
		}
	}

	@Override
	public void attributeButtonClick(ToolButtonIDs buttonNumber) {
		switch (buttonNumber) {
			case BUTTON_ID_PARAMETER_TOP:
			case BUTTON_ID_PARAMETER_BOTTOM_2:
				showColorPicker();
				break;
			case BUTTON_ID_PARAMETER_BOTTOM_1:
				showFillToolDialog();
				break;
			default:
				super.attributeButtonClick(buttonNumber);
				break;
		}
	}

	@Override
	public void resetInternalState() {
	}

	@Override
	public void draw(Canvas canvas) {
	}
}
