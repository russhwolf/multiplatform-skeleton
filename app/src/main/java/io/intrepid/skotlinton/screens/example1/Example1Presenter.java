package io.intrepid.skotlinton.screens.example1;

import android.support.annotation.NonNull;

import io.intrepid.skotlinton.base.BasePresenter;
import io.intrepid.skotlinton.base.PresenterConfiguration;

public class Example1Presenter extends BasePresenter<Example1Contract.View> implements Example1Contract.Presenter {

    Example1Presenter(@NonNull Example1Contract.View view,
                      @NonNull PresenterConfiguration configuration) {
        super(view, configuration);
    }

    @Override
    public void onButtonClick() {
        view.gotoExample2();
    }
}
