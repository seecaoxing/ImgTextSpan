package com.example.imgtextspan;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.lang.reflect.Field;

public class ImgTextSpan extends ImageSpan {

    private final Paint mTagPaint;
    private final int mTagTextColor;
    private final int mTagBackgroundColor;

    private int mSize;
    private int mRadiusPx;
    private int mRightMarginPx;
    private int mTextLeftPadding;
    private int mTextRightPadding;
    private int mRectTopPaddingPx;
    private int mRectBottomPaddingPx;

    private int imageWidth = 50;

    private Paint.Style mTagStyle;

    private String url;
    private boolean picShowed;
    private Context context;

    private TextView textView;


    ImgTextSpan(Context context, Paint.Style tagStyle, int tagTextColor, int tagBackgroundColor, int textSizePx, int radiusPx) {
        this(context, tagStyle, tagTextColor, tagBackgroundColor, textSizePx, radiusPx, 0, 0, 0);
    }

    ImgTextSpan(Context context, Paint.Style tagStyle, int tagTextColor, int tagBackgroundColor, int textSizePx, int radiusPx, int rightMarginPx) {
        this(context, tagStyle, tagTextColor, tagBackgroundColor, textSizePx, radiusPx, rightMarginPx, 0, 0);
    }

    ImgTextSpan(Context context, Paint.Style tagStyle, int tagTextColor, int tagBackgroundColor, int textSizePx, int radiusPx, int rightMarginPx, int textLeftPadding, int textRightPadding) {
        this(context, tagStyle, tagTextColor, tagBackgroundColor, textSizePx, radiusPx, rightMarginPx, textLeftPadding, textRightPadding, 0, 0);
    }

    ImgTextSpan(Context context, Paint.Style tagStyle, int tagTextColor, int tagBackgroundColor, int textSizePx, int radiusPx, int rightMarginPx, int textLeftPadding, int textRightPadding, int rectTopPaddingPx, int rectBottomPaddingPx) {
        super(context, R.mipmap.icon_chat_zuanshi);
        this.context = context;

        mTagStyle = tagStyle;
        mTagTextColor = tagTextColor;
        mTagBackgroundColor = tagBackgroundColor;

        mRadiusPx = radiusPx;
        mRightMarginPx = rightMarginPx;

        mTextLeftPadding = textLeftPadding;
        mTextRightPadding = textRightPadding;

        mRectTopPaddingPx = rectTopPaddingPx;
        mRectBottomPaddingPx = rectBottomPaddingPx;

        mTagPaint = new Paint();
        mTagPaint.setTextSize(textSizePx);
        mTagPaint.setColor(mTagTextColor);
        mTagPaint.setAntiAlias(true);
        mTagPaint.setTextAlign(Paint.Align.CENTER);
    }

    ImgTextSpan(Context context, Bitmap bitmap, Paint.Style tagStyle, int tagTextColor, int tagBackgroundColor, int textSizePx, int radiusPx, int rightMarginPx, int textLeftPadding, int textRightPadding, int rectTopPaddingPx, int rectBottomPaddingPx) {
        super(context, bitmap);
        this.context = context;
        picShowed = true;

        mTagStyle = tagStyle;
        mTagTextColor = tagTextColor;
        mTagBackgroundColor = tagBackgroundColor;

        mRadiusPx = radiusPx;
        mRightMarginPx = rightMarginPx;

        mTextLeftPadding = textLeftPadding;
        mTextRightPadding = textRightPadding;

        mRectTopPaddingPx = rectTopPaddingPx;
        mRectBottomPaddingPx = rectBottomPaddingPx;

        mTagPaint = new Paint();
        mTagPaint.setTextSize(textSizePx);
        mTagPaint.setColor(mTagTextColor);
        mTagPaint.setAntiAlias(true);
        mTagPaint.setTextAlign(Paint.Align.CENTER);
    }

    ImgTextSpan(Context context, String url, TextView textView, Paint.Style tagStyle, int tagTextColor, int tagBackgroundColor, int textSizePx, int radiusPx, int rightMarginPx, int textLeftPadding, int textRightPadding, int rectTopPaddingPx, int rectBottomPaddingPx) {
        super(context, R.mipmap.icon_chat_zuanshi);

        this.context = context;
        this.url = url;
        this.textView = textView;
        picShowed = false;

        mTagStyle = tagStyle;
        mTagTextColor = tagTextColor;
        mTagBackgroundColor = tagBackgroundColor;

        mRadiusPx = radiusPx;
        mRightMarginPx = rightMarginPx;

        mTextLeftPadding = textLeftPadding;
        mTextRightPadding = textRightPadding;

        mRectTopPaddingPx = rectTopPaddingPx;
        mRectBottomPaddingPx = rectBottomPaddingPx;

        mTagPaint = new Paint();
        mTagPaint.setTextSize(textSizePx);
        mTagPaint.setColor(mTagTextColor);
        mTagPaint.setAntiAlias(true);
        mTagPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    public Drawable getDrawable() {
        if (!picShowed) {
            Glide.with(context).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    Resources resources = context.getResources();

                    BitmapDrawable b = new BitmapDrawable(resources, resource);

                    b.setBounds(0, 0, b.getIntrinsicWidth(), b.getIntrinsicHeight());
                    Field mDrawable;
                    Field mDrawableRef;
                    try {
                        mDrawable = ImageSpan.class.getDeclaredField("mDrawable");
                        mDrawable.setAccessible(true);
                        mDrawable.set(ImgTextSpan.this, b);

                        mDrawableRef = DynamicDrawableSpan.class.getDeclaredField("mDrawableRef");
                        mDrawableRef.setAccessible(true);
                        mDrawableRef.set(ImgTextSpan.this, null);

                        picShowed = true;
                        textView.setText(textView.getText());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        return super.getDrawable();
    }


    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        mSize = (int) mTagPaint.measureText(text, start, end) + mRightMarginPx + mTextLeftPadding + mTextRightPadding + imageWidth;
        return mSize;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        RectF rect = createRect(x, y, paint);
        drawTagRect(canvas, paint, rect);
        drawTagText(canvas, text, start, end, rect);
        drawDrawable(canvas, paint, rect, x, y);

    }

    private RectF createRect(float x, int y, Paint paint) {
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        final float strokeWidth = paint.getStrokeWidth();
        float left = x + strokeWidth + 0.5f;
        int top = y + fontMetrics.ascent + mRectTopPaddingPx;
        float right = x + mSize + strokeWidth + 0.5f - mRightMarginPx;
        int bottom = y + fontMetrics.descent - mRectBottomPaddingPx;
        return new RectF(left, top, right, bottom);
    }

    private void drawDrawable(Canvas canvas, Paint paint, RectF rect, float x, int y) {
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        Drawable drawable = getDrawable();
        int transY = (y + fm.descent + y + fm.ascent) / 2 - drawable.getBounds().bottom / 2;
        canvas.save();
        canvas.translate(x, transY);
        drawable.draw(canvas);
        canvas.restore();
    }

    private void drawTagRect(Canvas canvas, Paint paint, RectF rect) {
        paint.setColor(mTagBackgroundColor);
        paint.setAntiAlias(true);

        paint.setStyle(mTagStyle);
        canvas.drawRoundRect(rect, mRadiusPx, mRadiusPx, paint);
    }

    private void drawTagText(Canvas canvas, CharSequence text, int start, int end, RectF rect) {

        Paint.FontMetricsInt tagFontMetrics = mTagPaint.getFontMetricsInt();
        final float textCenterX = (rect.right - rect.left) / 2 + imageWidth / 2;
        final float rectCenterY = rect.bottom - (rect.bottom - rect.top) / 2;
        final float tagBaseLineY = rectCenterY + (tagFontMetrics.descent - tagFontMetrics.ascent) / 2f - tagFontMetrics.descent;

        final String tag = text.subSequence(start, end).toString();
        canvas.drawText(tag, textCenterX, tagBaseLineY, mTagPaint);
    }

}
