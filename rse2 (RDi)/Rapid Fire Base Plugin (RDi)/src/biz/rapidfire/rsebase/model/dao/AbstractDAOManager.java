/*******************************************************************************
 * Copyright (c) 2017-2017 Rapid Fire Project Team
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *******************************************************************************/

package biz.rapidfire.rsebase.model.dao;

import com.ibm.as400.access.AS400;
import com.ibm.etools.iseries.subsystems.qsys.api.IBMiConnection;

public abstract class AbstractDAOManager {

    public AS400 getSystem(String connectionName) throws Exception {

        IBMiConnection connection = IBMiConnection.getConnection(connectionName);
        if (connection == null) {
            return null;
        }

        return connection.getAS400ToolboxObject();
    }

    public AS400 findSystem(String hostName) throws Exception {

        IBMiConnection[] connections = IBMiConnection.getConnections();
        for (IBMiConnection ibMiConnection : connections) {
            if (ibMiConnection.getHostName().equalsIgnoreCase(hostName)) {
                return ibMiConnection.getAS400ToolboxObject();
            }
        }

        return null;
    }
}
