package org.eclipse.sisu.scanners.module;

import org.eclipse.sisu.scanners.AbstractClassFinder;

public final class SisuModuleFinder extends AbstractClassFinder
{
    public SisuModuleFinder(boolean global)
    {
        super(global, ModuleFactory.MODULE);
    }
}
