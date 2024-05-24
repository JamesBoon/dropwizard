package io.dropwizard.core.cli;

import java.util.Locale;
import java.util.Optional;

public interface ArgumentParserOptions {
    Optional<String> getVersion();
    Optional<String> getLocation();
    Optional<Locale> getLocale();
}
