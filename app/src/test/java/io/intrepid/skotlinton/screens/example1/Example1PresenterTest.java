package io.intrepid.skotlinton.screens.example1;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import io.intrepid.skotlinton.testutils.BasePresenterTest;
import io.intrepid.skotlinton.testutils.TestPresenterConfiguration;

import static org.mockito.Mockito.verify;

public class Example1PresenterTest extends BasePresenterTest<Example1Presenter> {

    @Mock
    Example1Contract.View mockView;

    @Before
    public void setup() {
        presenter = new Example1Presenter(mockView, TestPresenterConfiguration.createTestConfiguration());
    }

    @Test
    public void onButtonClick() throws Exception {
        presenter.onButtonClick();
        verify(mockView).gotoExample2();
    }
}
