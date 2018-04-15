package ru.curriculum.service.printing;

import ru.curriculum.domain.printing.file.IFile;


public interface IPrintingService {

    IFile getExcelFileWillBePrinted(Integer etpId);
}
