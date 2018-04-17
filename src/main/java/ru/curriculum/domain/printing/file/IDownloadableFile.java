package ru.curriculum.domain.printing.file;

import java.io.ByteArrayOutputStream;

public interface IDownloadableFile {

    ByteArrayOutputStream content();

    String name();

    String format();
}
