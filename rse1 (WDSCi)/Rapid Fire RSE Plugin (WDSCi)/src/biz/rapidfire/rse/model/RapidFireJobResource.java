/*******************************************************************************
 * Copyright (c) 2012-2017 iSphere Project Owners
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *******************************************************************************/

package biz.rapidfire.rse.model;

import biz.rapidfire.core.model.IRapidFireJobResource;
import biz.rapidfire.core.model.JobName;
import biz.rapidfire.core.model.Phase;
import biz.rapidfire.core.model.Status;
import biz.rapidfire.core.subsystem.IRapidFireSubSystem;

import com.ibm.etools.systems.subsystems.SubSystem;
import com.ibm.etools.systems.subsystems.impl.AbstractResource;

public class RapidFireJobResource extends AbstractResource implements IRapidFireJobResource, Comparable<IRapidFireJobResource> {

    private String dataLibrary;
    private String job;
    private String description;
    private boolean doCreateEnvironment;
    private String jobQueueName;
    private String jobQueueLibrary;
    private Status status;
    private Phase phase;
    private boolean isError;
    private String errorText;
    private JobName batchJob;
    private boolean isStopApplyChanges;
    private String cmoneFormNumber;

    public RapidFireJobResource(String dataLibrary, String job) {

        this.dataLibrary = dataLibrary;
        this.job = job;
    }

    /*
     * IRapidFireJobResource methods
     */

    public String getDataLibrary() {
        return dataLibrary;
    }

    public String getName() {
        return job;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDoCreateEnvironment() {
        return doCreateEnvironment;
    }

    public void setDoCreateEnvironment(boolean doCreateEnvironment) {
        this.doCreateEnvironment = doCreateEnvironment;
    }

    public String getJobQueueName() {
        return jobQueueName;
    }

    public void setJobQueueName(String jobQueueName) {
        this.jobQueueName = jobQueueName;
    }

    public String getJobQueueLibrary() {
        return jobQueueLibrary;
    }

    public void setJobQueueLibrary(String jobQueueLibrary) {
        this.jobQueueLibrary = jobQueueLibrary;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean isError) {
        this.isError = isError;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public boolean isStopApplyChanges() {
        return isStopApplyChanges;
    }

    public void setStopApplyChanges(boolean isStopApplyChanges) {
        this.isStopApplyChanges = isStopApplyChanges;
    }

    public String getCmoneFormNumber() {
        return cmoneFormNumber;
    }

    public void setCmoneFormNumber(String cmoneFormNumber) {
        this.cmoneFormNumber = cmoneFormNumber;
    }

    public void setBatchJob(JobName job) {
        this.batchJob = job;
    }

    public JobName getBatchJob() {
        return batchJob;
    }

    public int compareTo(IRapidFireJobResource resource) {

        if (resource == null) {
            return 1;
        }

        int result = resource.getDataLibrary().compareTo(getDataLibrary());
        if (result != 0) {
            return result;
        }

        return getName().compareTo(resource.getName());
    }

    public void setParentSubSystem(IRapidFireSubSystem subSystem) {
        super.setSubSystem((SubSystem)subSystem);
    }

    public IRapidFireSubSystem getParentSubSystem() {
        return (IRapidFireSubSystem)super.getSubSystem();
    }

    @Override
    public String toString() {
        return getName();
    }

}