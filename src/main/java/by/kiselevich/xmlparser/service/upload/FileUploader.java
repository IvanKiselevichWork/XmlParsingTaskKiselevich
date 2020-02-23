package by.kiselevich.xmlparser.service.upload;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUploader {

    private static final Logger LOG = LogManager.getLogger(FileUploader.class);
    private static final String UPLOAD_DIRECTORY = "upload";
    private static final String FILE_TYPE = "file";
    private static final String EMPTY_STRING = "";

    public FileUploader() {

    }

    public List<File> uploadFiles(HttpServletRequest req) {
        String uploadPath = req.getServletContext().getRealPath(EMPTY_STRING) + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        List<File> files = new ArrayList<>();

        try {
            String fullFilepath;
            String type;
            for (Part part : req.getParts()) {
                fullFilepath = uploadPath + File.separator + part.getSubmittedFileName();
                type = part.getName();
                if (FILE_TYPE.equals(type)) {
                    part.write(fullFilepath);
                    files.add(new File(fullFilepath));
                    LOG.trace("File uploaded: {}", fullFilepath);
                }
            }
        } catch (IOException | ServletException e) {
            LOG.warn(e);
        }

        return files;
    }
}
