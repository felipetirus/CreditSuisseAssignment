import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import entity.LogEntity;
import json.LogJson;
import json.LogStateEnum;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import repository.LogRepository;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogAnalizer {

    private static final Logger LOGGER = Logger.getLogger( LogAnalizer.class.getName() );
    private static final String LOG_FILE = "\\logfile.txt";
    private Map<String, LogJson> logMap = new HashMap<>();
    private List<LogEntity> logEntityList = new ArrayList<>();
    private LogRepository repository = new LogRepository();

    public void analyseLogs(String path) throws InvalidParameterException, IOException, SQLException {
        checkValidLogFile(path);
        LineIterator it = FileUtils.lineIterator(FileUtils.getFile(path+LOG_FILE), "UTF-8");
        Gson g = new Gson();
        Long line = 1L;
        while (it.hasNext()) {
            LogJson log = jsonToOnject(g, it.nextLine(), line);
            if(log.isValid()) {
                matchLogStates(log);
            } else {
                LOGGER.log(Level.SEVERE, "The Line {0} is invalid.");
            }
            line++;
        }
        repository.insertLogs(logEntityList, path);
        System.out.println(logEntityList);
    }

    private void checkValidLogFile(String path) throws InvalidParameterException {
        File file = new File(path);
        if (!file.isDirectory()) {
            throw new InvalidParameterException("Invalid directory path.");
        }
        if (!file.exists()) {
            throw new InvalidParameterException("Directory does not exist.");
        }
        file = new File(path+LOG_FILE);
        if (!file.exists()) {
            throw new InvalidParameterException("Invalid log file.");
        }
    }

    private LogJson jsonToOnject(Gson g, String json, Long line) {
        try {
            return g.fromJson(json, LogJson.class);
        } catch (JsonSyntaxException e) {
            LOGGER.log(Level.SEVERE, "Invalid json on line: {0}", line);
            return new LogJson();
        }
    }

    private void matchLogStates(LogJson logJson) {
        if (logMap.containsKey(logJson.getId())) {
            if (logJson.getState() == LogStateEnum.STARTED) {
                addLogToList(logJson, logMap.get(logJson.getId()));
                logMap.remove(logJson.getId());
            } else {
                addLogToList(logMap.get(logJson.getId()), logJson);
            }
        } else {
            logMap.put(logJson.getId(), logJson);
        }
    }

    private void addLogToList(LogJson started, LogJson finished) {
        logEntityList.add(new LogEntity(started, finished));
    }

}
