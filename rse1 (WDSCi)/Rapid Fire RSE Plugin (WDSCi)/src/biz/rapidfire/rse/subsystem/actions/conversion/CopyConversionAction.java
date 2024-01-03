/*******************************************************************************
 * Copyright (c) 2017-2017 Rapid Fire Project Team
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *******************************************************************************/

package biz.rapidfire.rse.subsystem.actions.conversion;

import biz.rapidfire.core.handlers.conversion.CopyConversionHandler;
import biz.rapidfire.rse.subsystem.actions.AbstractResourceAction;

public class CopyConversionAction extends AbstractResourceAction {

    public CopyConversionAction() {
        super(new CopyConversionHandler());
    }
}