package io.intrepid.skotlinton.base;

public class BaseContract {

    public interface View {
    }

    public interface Presenter<T extends View> {

        void bindView(T view);

        void unbindView();

        void onViewCreated();

        void onViewDestroyed();
    }
}
