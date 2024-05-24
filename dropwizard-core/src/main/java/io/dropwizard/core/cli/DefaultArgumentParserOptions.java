package io.dropwizard.core.cli;

import java.io.File;
import java.net.URL;
import java.util.Locale;
import java.util.Optional;

public class DefaultArgumentParserOptions implements ArgumentParserOptions {
    private final Class<?> klass;

    /**
     * Constructs a new {@link DefaultArgumentParserOptions} object which gets access to the code source with the provided parameter.
     *
     * @param klass the class to access the code source from
     */
    public DefaultArgumentParserOptions(Class<?> klass) {
        this.klass = klass;
    }

    /**
     * Returns the version of the current jar holding the provided {@code klass}.
     *
     * @return the version representation
     */
    @Override
    public Optional<String> getVersion() {
        return Optional.ofNullable(klass.getPackage())
            .map(Package::getImplementationVersion);
    }

    @Override
    public Optional<String> getLocation() {
        final URL location = klass.getProtectionDomain().getCodeSource().getLocation();
        try {
            final String jar = new File(location.toURI()).getName();
            if (jar.endsWith(".jar")) {
                return Optional.of(jar);
            }
        } catch (Exception ignored) {
            // handled at the end
        }
        return Optional.empty();
    }

    @Override
    public Optional<Locale> getLocale() {
        return Optional.of(Locale.getDefault());
    }
}
