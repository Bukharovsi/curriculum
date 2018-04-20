package ru.curriculum.domain.admin.domain.printing;

import org.junit.Assert;
import org.junit.Test;
import ru.curriculum.domain.printing.exception.TemplateLoadingException;
import ru.curriculum.domain.printing.file.template.loader.ITemplateLoader;
import ru.curriculum.domain.printing.file.template.loader.TemplateLoaderFromResource;

import java.io.File;


public class TemplateLoaderFromResourceTest {

    @Test
    public void loadEtpTemplate_mustLoadCorrectly() {
        ITemplateLoader loader = new TemplateLoaderFromResource("etp.xls");
        File etpTemplate = loader.load();

        Assert.assertNotNull(etpTemplate);
        Assert.assertTrue(etpTemplate.getName().endsWith(".xls"));
    }

    @Test(expected = TemplateLoadingException.class)
    public void tryToLoadNonExistenceTemplate_loadMustBeFailed() {
        ITemplateLoader loader = new TemplateLoaderFromResource("noneExistent.xls");
        loader.load();
    }
}
