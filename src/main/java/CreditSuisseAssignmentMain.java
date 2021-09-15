import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreditSuisseAssignmentMain {

    private static final Logger LOGGER = Logger.getLogger( CreditSuisseAssignmentMain.class.getName() );

    public static void main(String[] args) {
        if(args.length == 0){
            LOGGER.log(Level.SEVERE, "First Parameter must be a directory path for the log file.");
            return;
        }
        LOGGER.log(Level.INFO, "Executing  program to path: {0}.", args[0]);
        LogAnalizer logAnalizer = new LogAnalizer();
        try {
            logAnalizer.analyseLogs(args[0]);
        } catch(IOException e) {
            LOGGER.log(Level.SEVERE, "IO error analizing log.");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL error: ", e);
        }
        LOGGER.log(Level.INFO, "End process for:", args[0]);
    }

}
