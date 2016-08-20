package com.zcoder.admin.modules.core.config.upload;

import org.apache.commons.fileupload.ProgressListener;

import javax.servlet.http.HttpSession;
import java.text.NumberFormat;

/**
 * @Description: 上传进度监听
 * @author SongJian
 * @date 2016年6月11日 上午12:39:30
 *
 */
public class FileProcessListener implements ProgressListener {

    private HttpSession session;
    public FileProcessListener(HttpSession session) {
        this.session = session;
        this.session.removeAttribute("fileUploadProcess");
    }
    /*
     *
     * <p>Title: update</p>
     * <p>Description: </p>
     * @param pBytesRead
     * @param pContentLength
     * @param pItems
     * @see org.apache.commons.fileupload.ProgressListener#update(long, long, int)
     */
    @Override
    public void update(long pBytesRead, long pContentLength, int pItems) {
        double readByte = pBytesRead;
        double totalSize = pContentLength;
        if(pContentLength == -1) {
        } else {
            String p = NumberFormat.getPercentInstance().format(readByte / totalSize);
            session.setAttribute("fileUploadProcess", p);
        }
    }

}
