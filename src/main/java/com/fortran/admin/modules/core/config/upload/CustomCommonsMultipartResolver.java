package com.fortran.admin.modules.core.config.upload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 15:05
 * @description:
 */

public class CustomCommonsMultipartResolver extends CommonsMultipartResolver {

    @Override
    protected MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {
        String encoding = determineEncoding(request);
        FileUpload fileUpload = prepareFileUpload(encoding);
        // 加入文件进度监听器 (原Source上添加) start
        FileProcessListener processListener = new FileProcessListener(request.getSession());
        fileUpload.setProgressListener(processListener);
        // 加入文件进度监听器 (原Source上添加) end
        try {
            List<FileItem> fileItems = ((ServletFileUpload) fileUpload).parseRequest(request);
            return parseFileItems(fileItems, encoding);
        } catch (FileUploadBase.SizeLimitExceededException ex) {
            throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), ex);
        }catch (FileUploadException ex) {
            throw new MultipartException("Could not parse multipart servlet request", ex);
        }catch (Exception exception){
            if(exception instanceof FileUploadBase.IOFileUploadException){
                throw new RuntimeException("upload bytes fail");
            }else{
                throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(),exception);
            }
        }
    }


}
