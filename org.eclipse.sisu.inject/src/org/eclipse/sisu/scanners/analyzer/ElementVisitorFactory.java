package org.eclipse.sisu.scanners.analyzer;

import com.google.inject.Binder;
import com.google.inject.spi.ElementVisitor;
import org.osgi.framework.Bundle;

/**
 * TODO: Document this class / interface here
 *
 * @since v5.2
 */
public interface ElementVisitorFactory
{
    ElementVisitor<?> create(Bundle bundle, Binder binder);
}
