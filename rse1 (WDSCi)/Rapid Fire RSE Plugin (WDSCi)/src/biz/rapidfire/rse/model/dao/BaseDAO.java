package biz.rapidfire.rse.model.dao;

import java.sql.Connection;

import biz.rapidfire.core.model.dao.AbstractBaseDAO;
import biz.rapidfire.core.model.dao.IBaseDAO;
import biz.rapidfire.rse.Messages;

import com.ibm.as400.access.AS400;
import com.ibm.etools.iseries.core.api.ISeriesConnection;

public class BaseDAO extends AbstractBaseDAO implements IBaseDAO {

    private ISeriesConnection ibmiConnection;
    private AS400 system;
    private Connection jdbcConnection;
    private boolean isCommitControl;

    BaseDAO(String connectionName, String libraryName, boolean isCommitControl) throws Exception {
        super(libraryName);

        if (connectionName == null) {
            throw new Exception(Messages.bind(Messages.RseBaseDAO_Invalid_or_missing_connection_name_A, connectionName));
        }

        this.isCommitControl = isCommitControl;
        this.ibmiConnection = ISeriesConnection.getConnection(connectionName);
        if (this.ibmiConnection == null) {
            throw new Exception(Messages.bind(Messages.RseBaseDAO_Connection_A_not_found, connectionName));
        }

        this.system = this.ibmiConnection.getAS400ToolboxObject(null);
    }

    public AS400 getSystem() {
        return system;
    }

    public String getHostName() {
        return ibmiConnection.getHostName();
    }

    public String getConnectionName() {
        return ibmiConnection.getConnectionName();
    }

    /*
     * Does not work at the moment due to a bug in
     * IBMiConnection.getJdbcConnection().
     */
    public Connection getJdbcConnection() throws Exception {

        String properties;
        if (getLibraryName() == null) {
            properties = "";
        } else {
            properties = ";libraries=" + getLibraryName() + ",*LIBL";
        }

        if (isCommitControl) {
            properties += ";transaction isolation=read committed";
        }

        Connection localJdbcConnection = ibmiConnection.getJDBCConnection(properties, false);
        if (localJdbcConnection == jdbcConnection) {
            return jdbcConnection;
        }

        if (jdbcConnection != null) {
            jdbcConnection.close();
        }

        jdbcConnection = localJdbcConnection;
        jdbcConnection.setAutoCommit(false);

        // Bugfix, because getJDBCConnection does not use the transaction
        // isolation of the JDBC properties.
        // (PMR 91446,031,724)
        if (isCommitControl) {
            jdbcConnection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } else {
            jdbcConnection.setTransactionIsolation(Connection.TRANSACTION_NONE);
        }

        // Bugfix, because getJDBCConnection does not set the default
        // schema.
        // (PMR 91446,031,724)
        setCurrentLibrary(jdbcConnection);

        return jdbcConnection;
    }

    protected boolean mustSetLibraries() {
        return true;
    }
}
