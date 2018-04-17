package ru.curriculum.web.etp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.curriculum.application.route.Routes;
import ru.curriculum.domain.printing.file.IDownloadableFile;
import ru.curriculum.service.printing.IReportService;

import java.io.UnsupportedEncodingException;


@Controller
@RequestMapping(path = Routes.etp)
public class ETPFileDownloadController {
    @Autowired
    private IReportService reportService;

    @RequestMapping(value = "/{etpId}/download", method = RequestMethod.GET)
    public HttpEntity<byte[]> downloadFile(@PathVariable("etpId") Integer etpId) throws UnsupportedEncodingException {
        IDownloadableFile file = reportService.createWorkbookForEtp(etpId);
        return new ResponseFile(file).toHttpEntity();
    }
}
