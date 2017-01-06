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
import android.graphics.Color;
import android.graphics.PointF;

import color.kidpaint.com.kidpaintcolor.AnimalsColoringApplication;
import color.kidpaint.com.kidpaintcolor.R;
import color.kidpaint.com.kidpaintcolor.command.Command;
import color.kidpaint.com.kidpaintcolor.command.implementation.FlipCommand;
import color.kidpaint.com.kidpaintcolor.dialog.IndeterminateProgressDialog;
import color.kidpaint.com.kidpaintcolor.tools.ToolType;


public class FlipTool extends BaseTool {

	public FlipTool(Context context, ToolType toolType) {
		super(context, toolType);
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
		return false;
	}

	@Override
	public void resetInternalState() {
	}

	@Override
	public int getAttributeButtonResource(ToolButtonIDs toolButtonID) {
		switch (toolButtonID) {
		case BUTTON_ID_PARAMETER_BOTTOM_1:
			return R.drawable.icon_menu_flip_horizontal;
		case BUTTON_ID_PARAMETER_BOTTOM_2:
			return R.drawable.icon_menu_flip_vertical;
		default:
			return super.getAttributeButtonResource(toolButtonID);
		}
	}

	@Override
	public void attributeButtonClick(ToolButtonIDs toolButtonID) {
		FlipCommand.FlipDirection flipDirection = null;
		switch (toolButtonID) {
		case BUTTON_ID_PARAMETER_BOTTOM_1:
			flipDirection = FlipCommand.FlipDirection.FLIP_HORIZONTAL;
			break;
		case BUTTON_ID_PARAMETER_BOTTOM_2:
			flipDirection = FlipCommand.FlipDirection.FLIP_VERTICAL;
			break;
		default:
			return;
		}

		Command command = new FlipCommand(flipDirection);
		IndeterminateProgressDialog.getInstance().show();
		((FlipCommand) command).addObserver(this);
		AnimalsColoringApplication.commandManager.commitCommand(command);
	}

	@Override
	public int getAttributeButtonColor(ToolButtonIDs buttonNumber) {
		switch (buttonNumber) {
		case BUTTON_ID_PARAMETER_TOP:
			return Color.TRANSPARENT;
		default:
			return super.getAttributeButtonColor(buttonNumber);
		}
	}

	@Override
	public void draw(Canvas canvas) {
	}

}
