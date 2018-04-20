package ru.curriculum.domain.admin.web;

import org.junit.Test;
import ru.curriculum.domain.printing.file.IDownloadableFile;
import ru.curriculum.web.downloadableFile.ResponseFile;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class ResponseFileTest {

    @Test
    public void createResponseFile() throws UnsupportedEncodingException {
        ResponseFile file = new ResponseFile(getFakeDownloadableFile());

        assertNotNull(file.toHttpEntity());
        assertTrue(file.toHttpEntity().hasBody());
        assertEquals(3, file.toHttpEntity().getHeaders().size());
        assertEquals("application/txt", file.toHttpEntity().getHeaders().get("Content-Type").get(0));
        assertEquals("inline; filename=ResponseFile.txt", file.toHttpEntity().getHeaders().get("Content-Disposition").get(0));
        assertNotNull(file.toHttpEntity().getHeaders().get("Content-Length").get(0));
    }

    private IDownloadableFile getFakeDownloadableFile() {
        return new IDownloadableFile() {
            @Override
            public ByteArrayOutputStream content() {
                return new ByteArrayOutputStream();
            }

            @Override
            public String name() {
                return "ResponseFile.txt";
            }

            @Override
            public String format() {
                return "txt";
            }
        };
    }
}
