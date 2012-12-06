package org.eclipse.sisu.binders;

import com.google.inject.Binder;
import com.google.inject.spi.ElementVisitor;

/**
 * TODO: Document this class / interface here
 *
 * @since v5.2
 */
public interface ElementVisitorsProvider
{
    Iterable<ElementVisitor> provide(Binder binder);
}
