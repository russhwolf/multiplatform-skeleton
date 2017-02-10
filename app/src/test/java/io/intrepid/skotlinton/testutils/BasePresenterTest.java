package io.intrepid.skotlinton.testutils;

import org.junit.Before;
import org.junit.Rule;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.intrepid.skotlinton.base.BasePresenter;
import io.intrepid.skotlinton.logging.CrashReporter;
import io.intrepid.skotlinton.rest.RestApi;
import io.intrepid.skotlinton.settings.UserSettings;
import io.reactivex.schedulers.TestScheduler;

public class BasePresenterTest<P extends BasePresenter> {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    protected P presenter;
    protected TestPresenterConfiguration testConfiguration;
    protected TestScheduler ioScheduler;
    protected TestScheduler uiScheduler;
    protected RestApi mockRestApi;
    protected UserSettings mockUserSettings;
    protected CrashReporter mockCrashReporter;

    @Before
    public void baseSetup() {
        testConfiguration = TestPresenterConfiguration.createTestConfiguration();
        ioScheduler = testConfiguration.getIoScheduler();
        uiScheduler = testConfiguration.getUiScheduler();
        mockRestApi = testConfiguration.getRestApi();
        mockUserSettings = testConfiguration.getUserSettings();
        mockCrashReporter = testConfiguration.getCrashReporter();
    }
}
