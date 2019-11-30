package service.impl;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import model.Event;
import service.api.IEventLogger;

public class FileEventLogger implements IEventLogger {

    private File file;
    private String fileName;

    public FileEventLogger(String filename) {
        this.fileName = filename;
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString() + "\n", "UTF-8", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {
        this.file = new File(fileName);
        if (file.exists() && !file.canWrite()) {
            throw new IllegalArgumentException("Can't write to file " + this.fileName);
        } else if (!file.exists()) {
            file.createNewFile();
        }
    }
}
