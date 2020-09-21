package com.xiniu.skin.inter;

import android.view.View;

public interface ISkin {

    public interface IImageViewSkin extends IViewSkin {
        void setImageResource(int i, int i2);
    }

    public interface IProgressBarViewSkin {
        void setProgressDrawable(int i, int i2);
    }

    public interface ISeekBarViewSkin {
        void setThumb(int i, int i2);
    }

    public interface ISkinAdapter {
        void apply(boolean z);

        void initSkin(View view);

        void setViewApplyImplListener(ViewApplyImplListener viewApplyImplListener);
    }

    public interface ITextViewSkin extends IViewSkin {
        void setHintTextColor(int i, int i2);

        void setTextColor(int i, int i2);
    }

    public interface IViewSkin {
        void setBackground(int i, int i2);
    }

    ISkinAdapter getAdpter();
}
