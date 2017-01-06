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

package color.kidpaint.com.kidpaintcolor.tools;

import android.app.Activity;

import color.kidpaint.com.kidpaintcolor.tools.implementation.CursorTool;
import color.kidpaint.com.kidpaintcolor.tools.implementation.DrawTool;
import color.kidpaint.com.kidpaintcolor.tools.implementation.EraserTool;
import color.kidpaint.com.kidpaintcolor.tools.implementation.FillTool;
import color.kidpaint.com.kidpaintcolor.tools.implementation.FlipTool;
import color.kidpaint.com.kidpaintcolor.tools.implementation.GeometricFillTool;
import color.kidpaint.com.kidpaintcolor.tools.implementation.ImportTool;
import color.kidpaint.com.kidpaintcolor.tools.implementation.LineTool;
import color.kidpaint.com.kidpaintcolor.tools.implementation.MoveZoomTool;
import color.kidpaint.com.kidpaintcolor.tools.implementation.PipetteTool;
import color.kidpaint.com.kidpaintcolor.tools.implementation.ResizeTool;
import color.kidpaint.com.kidpaintcolor.tools.implementation.RotationTool;
import color.kidpaint.com.kidpaintcolor.tools.implementation.StampTool;
import color.kidpaint.com.kidpaintcolor.tools.implementation.TextTool;


public class ToolFactory {

	public static Tool createTool(Activity context, ToolType toolType) {
		switch (toolType) {
		case BRUSH:
			return new DrawTool(context, toolType);
		case CURSOR:
			return new CursorTool(context, toolType);
		case ELLIPSE:
			return new GeometricFillTool(context, toolType);
		case STAMP:
			return new StampTool(context, toolType);
		case IMPORTPNG:
			return new ImportTool(context, toolType);
		case PIPETTE:
			return new PipetteTool(context, toolType);
		case FILL:
			return new FillTool(context, toolType);
		case RESIZE:
			return new ResizeTool(context, toolType);
		case RECT:
			return new GeometricFillTool(context, toolType);
		case ERASER:
			return new EraserTool(context, toolType);
		case FLIP:
			return new FlipTool(context, toolType);
		case LINE:
			return new LineTool(context, toolType);
		case MOVE:
		case ZOOM:
			return new MoveZoomTool(context, toolType);
		case ROTATE:
			return new RotationTool(context, toolType);
		case TEXT:
			return new TextTool(context, toolType);
		default:
			break;
		}
		return new DrawTool(context, ToolType.BRUSH);

	}

}
