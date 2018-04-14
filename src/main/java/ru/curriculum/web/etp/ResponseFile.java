package ru.curriculum.web.etp;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import ru.curriculum.domain.printing.file.IFile;

public class ResponseFile {
    private IFile file;

    public ResponseFile(IFile file) {
        this.file = file;
    }

    public HttpEntity<byte[]> toHttpEntity() {
        byte[] content = file.content().toByteArray();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", file.format()));
        header.set("Content-Disposition", "inline; filename=" + file.name());
        header.setContentLength(content.length);
        return new HttpEntity<>(content, header);
    }
}
