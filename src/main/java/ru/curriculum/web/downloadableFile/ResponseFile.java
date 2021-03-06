package ru.curriculum.web.downloadableFile;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import ru.curriculum.domain.printing.file.IDownloadableFile;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ResponseFile {
    private IDownloadableFile file;

    public ResponseFile(IDownloadableFile file) {
        this.file = file;
    }

    public HttpEntity<byte[]> toHttpEntity() throws UnsupportedEncodingException {
        byte[] content = file.content().toByteArray();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", file.format()));
        header.set("Content-Disposition", "inline; filename=" + URLEncoder.encode(file.name(), "UTF-8"));
        header.setContentLength(content.length);
        return new HttpEntity<>(content, header);
    }
}
