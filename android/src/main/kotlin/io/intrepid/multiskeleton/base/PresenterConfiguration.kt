package io.intrepid.multiskeleton.base

import io.intrepid.multiskeleton.logging.CrashReporter
import io.intrepid.multiskeleton.rest.RestApi
import io.intrepid.multiskeleton.settings.UserSettings
import io.reactivex.Scheduler

/**
 * Wrapper class for common dependencies that all presenters are expected to have
 */
open class PresenterConfiguration(open val ioScheduler: Scheduler,
                                  open val uiScheduler: Scheduler,
                                  val userSettings: UserSettings,
                                  val restApi: RestApi,
                                  val crashReporter: CrashReporter)
