package org.eclipse.sisu.scanners.module;

import com.google.inject.Module;
import org.osgi.framework.Bundle;

public interface ModuleFactory
{
    static final String MODULE = ModuleFactory.class.getName();

    Module getModule(Bundle bundle);
}
