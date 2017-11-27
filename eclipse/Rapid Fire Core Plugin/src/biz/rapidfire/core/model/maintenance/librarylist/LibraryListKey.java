/*******************************************************************************
 * Copyright (c) 2017-2017 Rapid Fire Project Owners
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *******************************************************************************/

package biz.rapidfire.core.model.maintenance.librarylist;

import biz.rapidfire.core.RapidFireCorePlugin;
import biz.rapidfire.core.helpers.ExceptionHelper;
import biz.rapidfire.core.model.maintenance.job.JobKey;

public class LibraryListKey implements Cloneable {

    private JobKey jobKey;
    private String libraryList;

    public LibraryListKey(JobKey jobKey, String libraryList) {

        this.jobKey = jobKey;
        if (libraryList != null) {
            this.libraryList = libraryList.trim();
        }
    }

    public String getJobName() {
        return jobKey.getJobName();
    }

    public String getLibraryList() {
        return libraryList;
    }

    public void setLibraryList(String libraryList) {
        this.libraryList = libraryList.trim();
    }

    @Override
    public Object clone() {
        try {

            LibraryListKey libraryListKey = (LibraryListKey)super.clone();
            libraryListKey.jobKey = (JobKey)jobKey.clone();

            return libraryListKey;

        } catch (CloneNotSupportedException e) {
            RapidFireCorePlugin.logError("*** Clone not supported. ***", e); //$NON-NLS-1$
            throw new biz.rapidfire.core.exceptions.CloneNotSupportedException(ExceptionHelper.getLocalizedMessage(e), e);
        }
    }
}