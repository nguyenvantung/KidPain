package color.kidpaint.com.kidpaintcolor.command.implementation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import color.kidpaint.com.kidpaintcolor.AnimalsColoringApplication;

public class RotateCommand extends BaseCommand {

	private final static float ANGLE = 90;
	private RotateDirection mRotateDirection;

	public static enum RotateDirection {
		ROTATE_LEFT, ROTATE_RIGHT
	};

	public RotateCommand(RotateDirection rotateDirection) {
		mRotateDirection = rotateDirection;
	}

	@Override
	public void run(Canvas canvas, Bitmap bitmap) {
		setChanged();
		notifyStatus(NOTIFY_STATES.COMMAND_STARTED);
		if (mRotateDirection == null) {
			setChanged();
			notifyStatus(NOTIFY_STATES.COMMAND_FAILED);
			return;
		}

		Matrix rotateMatrix = new Matrix();

		switch (mRotateDirection) {
		case ROTATE_RIGHT:
			rotateMatrix.postRotate(ANGLE);
			break;

		case ROTATE_LEFT:
			rotateMatrix.postRotate(-ANGLE);
			break;

		default:
			setChanged();
			notifyStatus(NOTIFY_STATES.COMMAND_FAILED);
			return;
		}

		Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
				bitmap.getWidth(), bitmap.getHeight(), rotateMatrix, true);
		Canvas rotateCanvas = new Canvas(rotatedBitmap);

		rotateCanvas.drawBitmap(bitmap, rotateMatrix, new Paint());

		if (AnimalsColoringApplication.drawingSurface != null) {
			AnimalsColoringApplication.drawingSurface.setBitmap(rotatedBitmap);
		}

		setChanged();

		AnimalsColoringApplication.perspective.resetScaleAndTranslation();
		notifyStatus(NOTIFY_STATES.COMMAND_DONE);

	}
}