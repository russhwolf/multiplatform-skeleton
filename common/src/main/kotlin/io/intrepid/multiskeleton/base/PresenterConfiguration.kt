package io.intrepid.multiskeleton.base

import io.intrepid.multiskeleton.logging.CrashReporter
import io.intrepid.multiskeleton.logging.Logger
import io.intrepid.multiskeleton.rest.RestApi
import io.intrepid.multiskeleton.settings.UserSettings

/**
 * Wrapper class for common dependencies that all presenters are expected to have
 */
open class PresenterConfiguration constructor(val userSettings: UserSettings,
                                              val restApi: RestApi,
                                              val logger: Logger,
                                              val crashReporter: CrashReporter)
