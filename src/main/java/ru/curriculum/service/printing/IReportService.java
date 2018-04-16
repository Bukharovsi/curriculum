package ru.curriculum.service.printing;

import ru.curriculum.domain.printing.file.IDownloadableFile;


public interface IReportService {

    IDownloadableFile createWorkbookForEtp(Integer etpId);
}
