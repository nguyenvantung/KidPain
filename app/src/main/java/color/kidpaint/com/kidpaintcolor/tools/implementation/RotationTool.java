package color.kidpaint.com.kidpaintcolor.tools.implementation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;

import color.kidpaint.com.kidpaintcolor.AnimalsColoringApplication;
import color.kidpaint.com.kidpaintcolor.R;
import color.kidpaint.com.kidpaintcolor.command.Command;
import color.kidpaint.com.kidpaintcolor.command.implementation.RotateCommand;
import color.kidpaint.com.kidpaintcolor.dialog.IndeterminateProgressDialog;
import color.kidpaint.com.kidpaintcolor.tools.ToolType;


public class RotationTool extends BaseTool {

	public RotationTool(Context context, ToolType toolType) {
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
	public void draw(Canvas canvas) {

	}

	@Override
	public int getAttributeButtonResource(ToolButtonIDs toolButtonID) {
		switch (toolButtonID) {
		case BUTTON_ID_PARAMETER_BOTTOM_1:
			return R.drawable.icon_menu_rotate_left;
		case BUTTON_ID_PARAMETER_BOTTOM_2:
			return R.drawable.icon_menu_rotate_right;
		default:
			return super.getAttributeButtonResource(toolButtonID);
		}
	}

	@Override
	public void attributeButtonClick(ToolButtonIDs toolButtonID) {
		RotateCommand.RotateDirection rotateDirection = null;
		switch (toolButtonID) {
		case BUTTON_ID_PARAMETER_BOTTOM_1:
			rotateDirection = RotateCommand.RotateDirection.ROTATE_LEFT;
			break;
		case BUTTON_ID_PARAMETER_BOTTOM_2:
			rotateDirection = RotateCommand.RotateDirection.ROTATE_RIGHT;
			break;
		default:
			return;
		}

		Command command = new RotateCommand(rotateDirection);
		IndeterminateProgressDialog.getInstance().show();
		((RotateCommand) command).addObserver(this);
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

}
