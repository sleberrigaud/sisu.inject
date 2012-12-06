/*******************************************************************************
 * Copyright (c) 2010, 2012 Sonatype, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stuart McCulloch (Sonatype, Inc.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.sisu.binders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.inject.spi.ElementVisitor;
import org.eclipse.sisu.converters.FileTypeConverter;
import org.eclipse.sisu.converters.URLTypeConverter;
import org.eclipse.sisu.locators.BeanLocator;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.spi.Element;
import com.google.inject.spi.Elements;
import org.eclipse.sisu.scanners.analyzer.ElementVisitorFactory;

/**
 * Guice {@link Module} that automatically adds {@link BeanLocator}-backed bindings for non-local bean dependencies.
 */
public class WireModule
    implements Module
{
    // ----------------------------------------------------------------------
    // Implementation fields
    // ----------------------------------------------------------------------

    private final ElementVisitorsProvider extensionElementVisitorsProviders;
    private final List<Module> modules;

    // ----------------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------------

    public WireModule(Module... modules)
    {
        this(Arrays.asList(modules));
    }

    public WireModule(List<Module> modules)
    {
        this(modules, new ElementVisitorsProvider()
        {
            public Iterable<ElementVisitor> provide(Binder binder)
            {
                return Collections.emptyList();
            }
        });
    }
    public WireModule(List<Module> modules, ElementVisitorsProvider extensionElementVisitorsProviders)
    {
        this.extensionElementVisitorsProviders = extensionElementVisitorsProviders;
        this.modules = modules;
    }

    // ----------------------------------------------------------------------
    // Public methods
    // ----------------------------------------------------------------------

    public void configure( final Binder binder )
    {
        Iterable<ElementVisitor> elementVisitors = extensionElementVisitorsProviders.provide(binder);
        final ElementAnalyzer analyzer = getAnalyzer( binder );
        for ( final Module m : modules )
        {
            for ( final Element e : Elements.getElements( m ) )
            {
                for (ElementVisitor<?> elementVisitor : elementVisitors)
                {
                    e.acceptVisitor(elementVisitor);
                }
                e.acceptVisitor( analyzer );
            }
        }
        analyzer.apply( wiring( binder ) );
    }

    // ----------------------------------------------------------------------
    // Customizable methods
    // ----------------------------------------------------------------------

    protected Wiring wiring( final Binder binder )
    {
        binder.install( new FileTypeConverter() );
        binder.install( new URLTypeConverter() );

        return new LocatorWiring( binder );
    }

    // ----------------------------------------------------------------------
    // Implementation methods
    // ----------------------------------------------------------------------

    ElementAnalyzer getAnalyzer( final Binder binder )
    {
        return new ElementAnalyzer( binder );
    }
}
