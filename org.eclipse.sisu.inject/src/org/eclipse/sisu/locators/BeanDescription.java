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
package org.eclipse.sisu.locators;

import com.google.inject.Binder;

/**
 * Binding source locations should implement this interface to supply descriptions to the {@link BeanLocator}.
 * 
 * @see Binder#withSource(Object)
 */
public interface BeanDescription
{
    /**
     * @return Human-readable description
     */
    String getDescription();
}
