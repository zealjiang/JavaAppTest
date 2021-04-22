package com.example.javaapptest.drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;

import com.example.javaapptest.R;


public class RoundedCornersDrawable extends Drawable{
    private Drawable mCurrentDelegate;
    private boolean mIsCircle = false;
    private float mBorderWidth = 0;
    private int mBorderColor = Color.TRANSPARENT;
    private int mOverlayColor = Color.TRANSPARENT;
    private float mPadding = 0;
    private final Path mPath = new Path();
    private final Path mBorderPath = new Path();
    private final RectF mTempRectangle = new RectF();
    private Bitmap bitmap;
    public enum Type {
        /**
         * Draws rounded corners on top of the underlying drawable by overlaying a solid color which
         * is specified by {@code setOverlayColor}. This option should only be used when the
         * background beneath the underlying drawable is static and of the same solid color.
         */
        OVERLAY_COLOR,

        /**
         * Clips the drawable to be rounded. This option is not supported right now but is expected to
         * be made available in the future.
         */
        CLIPPING
    }

    RoundedCornersDrawable.Type mType = RoundedCornersDrawable.Type.OVERLAY_COLOR;
    private final float[] mRadii = new float[8];
    final float[] mBorderRadii = new float[8];
    final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public RoundedCornersDrawable() {
    }

    public void setCircle(boolean isCircle,Bitmap bitmap) {
        mIsCircle = isCircle;
        this.bitmap = bitmap;
        updatePath();
        invalidateSelf();
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    public RectF getBound(){
        return new RectF(0,0,500,500);
    }
    private void updatePath() {
        mPath.reset();
        mBorderPath.reset();
        mTempRectangle.set(getBound());

        mTempRectangle.inset(mPadding, mPadding);
        if (mIsCircle) {
            mPath.addCircle(
                    mTempRectangle.centerX(),
                    mTempRectangle.centerY(),
                    Math.min(mTempRectangle.width(), mTempRectangle.height())/2,
                    Path.Direction.CW);
        } else {
            mPath.addRoundRect(mTempRectangle, mRadii, Path.Direction.CW);
        }
        mTempRectangle.inset(-mPadding, -mPadding);

        mTempRectangle.inset(mBorderWidth/2, mBorderWidth/2);
        if (mIsCircle) {
            float radius = Math.min(mTempRectangle.width(), mTempRectangle.height())/2;
            mBorderPath.addCircle(
                    mTempRectangle.centerX(), mTempRectangle.centerY(), radius, Path.Direction.CW);
        } else {
            for (int i = 0; i < mBorderRadii.length; i++) {
                mBorderRadii[i] = mRadii[i] + mPadding - mBorderWidth/2;
            }
            mBorderPath.addRoundRect(mTempRectangle, mBorderRadii, Path.Direction.CW);
        }
        mTempRectangle.inset(-mBorderWidth/2, -mBorderWidth/2);
    }

    @Override
    public void draw(Canvas canvas) {
/*        Rect bounds = getBounds();
        switch (mType) {
            case CLIPPING:
                int saveCount = canvas.save();
                // clip, note: doesn't support anti-aliasing
                mPath.setFillType(Path.FillType.EVEN_ODD);
                canvas.clipPath(mPath);
                canvas.restoreToCount(saveCount);
                break;
            case OVERLAY_COLOR:
                mPaint.setColor(mOverlayColor);
                mPaint.setStyle(Paint.Style.FILL);
                mPath.setFillType(Path.FillType.INVERSE_EVEN_ODD);
                canvas.drawPath(mPath, mPaint);

                if (mIsCircle) {
                    // INVERSE_EVEN_ODD will only draw inverse circle within its bounding box, so we need to
                    // fill the rest manually if the bounds are not square.
                    float paddingH = (bounds.width() - bounds.height() + mBorderWidth) / 2f;
                    float paddingV = (bounds.height() - bounds.width() + mBorderWidth) / 2f;
                    if (paddingH > 0) {
                        canvas.drawRect(bounds.left, bounds.top, bounds.left + paddingH, bounds.bottom, mPaint);
                        canvas.drawRect(
                                bounds.right - paddingH,
                                bounds.top,
                                bounds.right,
                                bounds.bottom,
                                mPaint);
                    }
                    if (paddingV > 0) {
                        canvas.drawRect(bounds.left, bounds.top, bounds.right, bounds.top + paddingV, mPaint);
                        canvas.drawRect(
                                bounds.left,
                                bounds.bottom - paddingV,
                                bounds.right,
                                bounds.bottom,
                                mPaint);
                    }
                }
                break;
        }

        if (mBorderColor != Color.TRANSPARENT) {
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(mBorderColor);
            mPaint.setStrokeWidth(mBorderWidth);
            mPath.setFillType(Path.FillType.EVEN_ODD);
            canvas.drawPath(mBorderPath, mPaint);
        }*/

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        Path mPath = new Path();
        //canvas.drawBitmap(bitmap,0,0,mPaint);
        mPath.addCircle(
                250,
                250,
                250,
                Path.Direction.CW);
        //canvas.drawPath(mPath, mPaint);

        //canvas.clipPath(mPath);
        //canvas.clipPath(mPath, Region.Op.INTERSECT);
        canvas.clipPath(mPath, Region.Op.DIFFERENCE);
        canvas.drawBitmap(bitmap,null,new Rect(0,0,500,500),mPaint);

    }
}
