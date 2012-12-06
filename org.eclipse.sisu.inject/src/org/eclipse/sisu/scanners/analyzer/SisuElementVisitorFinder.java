package org.eclipse.sisu.scanners.analyzer;

import com.google.inject.spi.ElementVisitor;
import org.eclipse.sisu.scanners.AbstractClassFinder;


public class SisuElementVisitorFinder extends AbstractClassFinder
{
    public SisuElementVisitorFinder(boolean global)
    {
        super(global, ElementVisitorFactory.class.getName());
    }
}
