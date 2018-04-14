package ru.curriculum.domain.printing.file;

import java.io.ByteArrayOutputStream;

public interface IFile {

    ByteArrayOutputStream content();

    String name();

    String format();
}
