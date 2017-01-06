package color.kidpaint.com.kidpaintcolor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.io.File;

import color.kidpaint.com.kidpaintcolor.bean.Perspective;
import color.kidpaint.com.kidpaintcolor.dialog.BrushPickerDialog;
import color.kidpaint.com.kidpaintcolor.dialog.CustomAlertDialogBuilder;
import color.kidpaint.com.kidpaintcolor.dialog.DialogTermsOfUseAndService;
import color.kidpaint.com.kidpaintcolor.dialog.FillToolDialog;
import color.kidpaint.com.kidpaintcolor.dialog.IndeterminateProgressDialog;
import color.kidpaint.com.kidpaintcolor.dialog.TextToolDialog;
import color.kidpaint.com.kidpaintcolor.dialog.ToolsDialog;
import color.kidpaint.com.kidpaintcolor.dialog.colorpicker.ColorPickerDialog;
import color.kidpaint.com.kidpaintcolor.event.DrawingSurfaceListener;
import color.kidpaint.com.kidpaintcolor.tools.Tool;
import color.kidpaint.com.kidpaintcolor.tools.ToolFactory;
import color.kidpaint.com.kidpaintcolor.tools.ToolType;
import color.kidpaint.com.kidpaintcolor.tools.implementation.ImportTool;
import color.kidpaint.com.kidpaintcolor.util.FileIO;
import color.kidpaint.com.kidpaintcolor.widget.BottomBar;
import color.kidpaint.com.kidpaintcolor.widget.DrawingSurface;
import color.kidpaint.com.kidpaintcolor.widget.TopBar;

/**
 * Created by Tung Nguyen on 1/5/2017.
 */
public class ColorActivity extends AppCompatActivity{
    public static final String EXTRA_INSTANCE_FROM_CATROBAT = "EXTRA_INSTANCE_FROM_CATROBAT";
    public static final String EXTRA_ACTION_BAR_HEIGHT = "EXTRA_ACTION_BAR_HEIGHT";
    protected DrawingSurfaceListener mDrawingSurfaceListener;
    protected TopBar mTopBar;
    protected BottomBar mBottomBar;

    protected boolean mToolbarIsVisible = true;
    private Menu mMenu = null;
    private static final int ANDROID_VERSION_ICE_CREAM_SANDWICH = 14;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        ColorPickerDialog.init(this);
        BrushPickerDialog.init(this);
        ToolsDialog.init(this);
        IndeterminateProgressDialog.init(this);
        TextToolDialog.init(this);
        FillToolDialog.init(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        // setDefaultPreferences();
        initActionBar();

        AnimalsColoringApplication.catroidPicturePath = null;
        String catroidPicturePath = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            catroidPicturePath = extras
                    .getString(getString(R.string.extra_picture_path_catroid));

        }
        if (catroidPicturePath != null) {
            AnimalsColoringApplication.openedFromCatroid = true;
            if (!catroidPicturePath.equals("")) {
                AnimalsColoringApplication.catroidPicturePath = catroidPicturePath;
                AnimalsColoringApplication.scaleImage = false;
            }
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } else {
            AnimalsColoringApplication.openedFromCatroid = false;
        }

        AnimalsColoringApplication.drawingSurface = (DrawingSurface) findViewById(R.id.drawingSurfaceView);
        AnimalsColoringApplication.perspective = new Perspective(
                ((SurfaceView) AnimalsColoringApplication.drawingSurface).getHolder());
        mDrawingSurfaceListener = new DrawingSurfaceListener();
        mTopBar = new TopBar(this, AnimalsColoringApplication.openedFromCatroid);
        mBottomBar = new BottomBar(this);

        AnimalsColoringApplication.drawingSurface
                .setOnTouchListener(mDrawingSurfaceListener);

        if (AnimalsColoringApplication.openedFromCatroid
                && catroidPicturePath != null
                && catroidPicturePath.length() > 0) {
            loadBitmapFromUriAndRun(Uri.fromFile(new File(catroidPicturePath)),
                    new RunnableWithBitmap() {
                        @SuppressLint("NewApi")
                        @Override
                        public void run(Bitmap bitmap) {
                            if (!bitmap.hasAlpha()) {

                                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
                                    bitmap.setHasAlpha(true);
                                } else {
                                    bitmap = addAlphaChannel(bitmap);
                                }
                            }
                            AnimalsColoringApplication.drawingSurface
                                    .resetBitmap(bitmap);
                        }

                        private Bitmap addAlphaChannel(Bitmap src) {
                            int width = src.getWidth();
                            int height = src.getHeight();
                            Bitmap dest = Bitmap.createBitmap(width, height,
                                    Bitmap.Config.ARGB_8888);

                            int[] pixels = new int[width * height];
                            src.getPixels(pixels, 0, width, 0, 0, width, height);
                            dest.setPixels(pixels, 0, width, 0, 0, width,
                                    height);

                            src.recycle();
                            return dest;
                        }
                    });

        } else {
            initialiseNewBitmap();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        checkIfLoadBitmapFailed();
    }

    public void checkIfLoadBitmapFailed() {
        if (loadBitmapFailed) {
            loadBitmapFailed = false;
            new InfoDialog(DialogType.WARNING,
                    R.string.dialog_loading_image_failed_title,
                    R.string.dialog_loading_image_failed_text).show(
                    getSupportFragmentManager(), "loadbitmapdialogerror");
        }
    }

    private void initActionBar() {

        getSupportActionBar().setCustomView(R.layout.top_bar);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        if (Build.VERSION.SDK_INT < ANDROID_VERSION_ICE_CREAM_SANDWICH) {
            Bitmap bitmapActionBarBackground = Bitmap.createBitmap(1, 1,
                    Bitmap.Config.ARGB_8888);
            bitmapActionBarBackground.eraseColor(getResources().getColor(
                    R.color.custom_background_color));
            Drawable drawable = new BitmapDrawable(getResources(),
                    bitmapActionBarBackground);
            getSupportActionBar().setBackgroundDrawable(drawable);
            getSupportActionBar().setSplitBackgroundDrawable(drawable);
        }
    }

    @Override
    public void onDetachedFromWindow() {
        IndeterminateProgressDialog.getInstance().dismiss();
        super.onDetachedFromWindow();
    }

    @Override
    protected void onDestroy() {

        AnimalsColoringApplication.commandManager.resetAndClear();
        AnimalsColoringApplication.drawingSurface.recycleBitmap();
        ColorPickerDialog.getInstance().setInitialColor(
                getResources().getColor(R.color.color_chooser_black));
        AnimalsColoringApplication.currentTool.changePaintStrokeCap(Paint.Cap.ROUND);
        AnimalsColoringApplication.currentTool.changePaintStrokeWidth(25);
        AnimalsColoringApplication.isPlainImage = true;
        AnimalsColoringApplication.savedPictureUri = null;
        AnimalsColoringApplication.saveCopy = false;

        ToolsDialog.getInstance().dismiss();
        IndeterminateProgressDialog.getInstance().dismiss();
        ColorPickerDialog.getInstance().dismiss();
        // BrushPickerDialog.getInstance().dismiss(); // TODO: how can there
        // ever be a null pointer exception?
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        mMenu = menu;
     /*   AnimalsColoringApplication.menu = mMenu;
        MenuInflater inflater = getSupportMenuInflater();
        if (AnimalsColoringApplication.openedFromCatroid) {
            inflater.inflate(R.menu.main_menu_opened_from_catroid, menu);
        } else {
            inflater.inflate(R.menu.main_menu, menu);
        }*/

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       /* switch (item.getItemId()) {
            case R.id.menu_item_back_to_catroid:
                showSecurityQuestionBeforeExit();
                return true;
            case R.id.menu_item_terms_of_use_and_service:
                DialogTermsOfUseAndService termsOfUseAndService = new DialogTermsOfUseAndService();
                termsOfUseAndService.show(getSupportFragmentManager(),
                        "termsofuseandservicedialogfragment");
                return true;
            case R.id.menu_item_about:
                DialogAbout about = new DialogAbout();
                about.show(getSupportFragmentManager(), "aboutdialogfragment");
                return true;
            case R.id.menu_item_hide_menu:
                setFullScreen(mToolbarIsVisible);
                return true;
            case android.R.id.home:
                if (PaintroidApplication.openedFromCatroid) {
                    showSecurityQuestionBeforeExit();
                }
                return true;
			*//* EXCLUDE PREFERENCES FOR RELEASE *//*
            // case R.id.menu_item_preferences:
            // Intent intent = new Intent(this, SettingsActivity.class);
            // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            // startActivity(intent);
            // return false;
            default:
                return super.onOptionsItemSelected(item);
        }*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (mToolbarIsVisible == false) {
            setFullScreen(false);
            return true;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if (!mToolbarIsVisible) {
            setFullScreen(false);

        } else if (AnimalsColoringApplication.currentTool.getToolType() == ToolType.BRUSH) {
            showSecurityQuestionBeforeExit();
        } else {
            switchTool(ToolType.BRUSH);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case REQUEST_CODE_IMPORTPNG:
                Uri selectedGalleryImageUri = data.getData();
                Tool tool = ToolFactory.createTool(this, ToolType.IMPORTPNG);
                switchTool(tool);

                loadBitmapFromUriAndRun(selectedGalleryImageUri,
                        new RunnableWithBitmap() {
                            @Override
                            public void run(Bitmap bitmap) {
                                if (AnimalsColoringApplication.currentTool instanceof ImportTool) {
                                    ((ImportTool) AnimalsColoringApplication.currentTool)
                                            .setBitmapFromFile(bitmap);

                                }
                            }
                        });

                break;
            case REQUEST_CODE_FINISH:
                finish();
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void importPng() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        startActivityForResult(intent, REQUEST_CODE_IMPORTPNG);
    }

    public synchronized void switchTool(ToolType changeToToolType) {

        switch (changeToToolType) {
            case REDO:
                AnimalsColoringApplication.commandManager.redo();
                break;
            case UNDO:
                AnimalsColoringApplication.commandManager.undo();
                break;
            case IMPORTPNG:
                importPng();
                break;
            default:
                Tool tool = ToolFactory.createTool(this, changeToToolType);
                switchTool(tool);
                break;
        }

    }

    public synchronized void switchTool(Tool tool) {
        Paint tempPaint = new Paint(
                AnimalsColoringApplication.currentTool.getDrawPaint());
        if (tool != null) {
            mTopBar.setTool(tool);
            mBottomBar.setTool(tool);
            AnimalsColoringApplication.currentTool = tool;
            AnimalsColoringApplication.currentTool.setDrawPaint(tempPaint);
        }
    }

    private void showSecurityQuestionBeforeExit() {
        if (AnimalsColoringApplication.isSaved
                || !AnimalsColoringApplication.commandManager.hasCommands()
                && AnimalsColoringApplication.isPlainImage) {
            finish();
            return;
        } else {
            AlertDialog.Builder builder = new CustomAlertDialogBuilder(this);
            if (AnimalsColoringApplication.openedFromCatroid) {
                builder.setTitle(R.string.closing_catroid_security_question_title);
                builder.setMessage(R.string.closing_security_question);
                builder.setPositiveButton(R.string.save_button_text,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                exitToCatroid();
                            }
                        });
                builder.setNegativeButton(R.string.discard_button_text,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });
            } else {
                builder.setTitle(R.string.closing_security_question_title);
                builder.setMessage(R.string.closing_security_question);
                builder.setPositiveButton(R.string.save_button_text,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                saveFileBeforeExit();
                                finish();
                            }
                        });
                builder.setNegativeButton(R.string.discard_button_text,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });
            }
            builder.setCancelable(true);
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    private void saveFileBeforeExit() {
        saveFile();
    }

    private void exitToCatroid() {
        String pictureFileName = getString(R.string.temp_picture_name);

        if (AnimalsColoringApplication.catroidPicturePath != null) {
            pictureFileName = AnimalsColoringApplication.catroidPicturePath;
        } else {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                String catroidPictureName = extras
                        .getString(getString(R.string.extra_picture_name_catroid));
                if (catroidPictureName != null
                        && catroidPictureName.length() > 0) {
                    pictureFileName = catroidPictureName;
                }
            }
            pictureFileName = FileIO.createNewEmptyPictureFile(this,
                    pictureFileName).getAbsolutePath();
        }

        Intent resultIntent = new Intent();

        if (FileIO.saveBitmap(ColorActivity.this,
                AnimalsColoringApplication.drawingSurface.getBitmapCopy(),
                pictureFileName)) {
            Bundle bundle = new Bundle();
            bundle.putString(getString(R.string.extra_picture_path_catroid),
                    pictureFileName);
            resultIntent.putExtras(bundle);
            setResult(RESULT_OK, resultIntent);
        } else {
            setResult(RESULT_CANCELED, resultIntent);
        }
        finish();
    }

    private void setFullScreen(boolean isFullScreen) {
        AnimalsColoringApplication.perspective.setFullscreen(isFullScreen);
        if (isFullScreen) {
            getSupportActionBar().hide();
            LinearLayout bottomBarLayout = (LinearLayout) findViewById(R.id.main_bottom_bar);
            bottomBarLayout.setVisibility(View.GONE);
            mToolbarIsVisible = false;
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().clearFlags(
                    WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        } else {
            getSupportActionBar().show();
            LinearLayout bottomBarLayout = (LinearLayout) findViewById(R.id.main_bottom_bar);
            bottomBarLayout.setVisibility(View.VISIBLE);
            mToolbarIsVisible = true;
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

	/* EXCLUDE PREFERENCES FOR RELEASE */
    // private void setDefaultPreferences() {
    // PreferenceManager
    // .setDefaultValues(this, R.xml.preferences_tools, false);
    // }
}
