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
import android.graphics.PointF;

import color.kidpaint.com.kidpaintcolor.AnimalsColoringApplication;
import color.kidpaint.com.kidpaintcolor.R;
import color.kidpaint.com.kidpaintcolor.tools.ToolType;


public class MoveZoomTool extends BaseTool {
	private final static float ZOOM_IN_SCALE = 1.75f;

	public MoveZoomTool(Context context, ToolType toolType) {
		super(context, toolType);
	}

	@Override
	public void attributeButtonClick(ToolButtonIDs buttonNumber) {
		switch (buttonNumber) {
		case BUTTON_ID_PARAMETER_BOTTOM_1:
			zoomOut();
			break;
		case BUTTON_ID_PARAMETER_BOTTOM_2:
			zoomIn();
			break;
		default:
			super.attributeButtonClick(buttonNumber);
		}
	}

	private void zoomOut() {
		float scale = 1 / ZOOM_IN_SCALE;
		AnimalsColoringApplication.perspective.multiplyScale(scale);
	}

	private void zoomIn() {
		float scale = ZOOM_IN_SCALE;
		AnimalsColoringApplication.perspective.multiplyScale(scale);
		AnimalsColoringApplication.perspective.translate(0, 0);
	}

	@Override
	public int getAttributeButtonResource(ToolButtonIDs buttonNumber) {
		switch (buttonNumber) {
		case BUTTON_ID_PARAMETER_TOP:
			return NO_BUTTON_RESOURCE;
		case BUTTON_ID_PARAMETER_BOTTOM_1:
			return R.drawable.icon_zoom_out;
		case BUTTON_ID_PARAMETER_BOTTOM_2:
			return R.drawable.icon_zoom_in;
		default:
			return super.getAttributeButtonResource(buttonNumber);
		}
	}

	@Override
	public boolean handleDown(PointF coordinate) {
		AnimalsColoringApplication.perspective.convertFromCanvasToScreen(coordinate);
		mPreviousEventCoordinate = coordinate;
		return true;
	}

	@Override
	public boolean handleMove(PointF coordinate) {

		AnimalsColoringApplication.perspective.convertFromCanvasToScreen(coordinate);

		AnimalsColoringApplication.perspective.translate(coordinate.x
				- mPreviousEventCoordinate.x, coordinate.y
				- mPreviousEventCoordinate.y);
		mPreviousEventCoordinate.set(coordinate);

		return true;
	}

	@Override
	public boolean handleUp(PointF coordinate) {
		return false;
	}

	@Override
	public void resetInternalState() {
	}

	@Override
	public void draw(Canvas canvas) {
	}

}
