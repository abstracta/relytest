/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relytest.ui;

import java.io.FileWriter;
import java.io.IOException;
import relytest.interfaces.IPrinter;
import relytest.ui.common.CharterDto;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class HtmlPrinter implements IPrinter {

    @Override
    public void print(CharterDto dto) {

        try {
            // 1. Configure FreeMarker
            //
            // You should do this ONLY ONCE, when your application starts,
            // then reuse the same Configuration object elsewhere.
            Configuration cfg = new Configuration();

            // Where do we load the templates from:
            cfg.setClassForTemplateLoading(HtmlPrinter.class, "templates");

            // Some other recommended settings:
            cfg.setIncompatibleImprovements(new Version(2, 3, 20));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setLocale(Locale.US);
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            // 2. Proccess template(s)
            //
            // You will do this for several times in typical applications.
            // 2.1. Prepare the template input:
            Map<String, Object> input = new HashMap<>();

            input.put("title", "RelyTest Summary");

            input.put("charterName", dto.getName());

            if (!dto.getNotesTaken().isEmpty()) {
                input.put("logNotes", dto.getNotesTaken());
            }
            for (int i = 0; i < 5; i++) {
                input.put("label" + i, dto.getGroupNotes()[i].getLabel());
                if (!dto.getGroupNotes()[i].notes.isEmpty()) {
                    input.put("labels" + i, dto.getGroupNotes()[i].notes);
                }
            }

            if (!dto.getGroupNotes()[5].notes.isEmpty()) {
                input.put("screenprints", dto.getGroupNotes()[5].notes);
            }
            // 2.2. Get the template

            Template template = cfg.getTemplate(Constants.HTML_TEMPLATE_FILE);

            // 2.3. Generate the output
            // Write output to the console
            Writer consoleWriter = new OutputStreamWriter(System.out);
            template.process(input, consoleWriter);

            // also write output into a file:
            Writer fileWriter = new FileWriter(new File(dto.getPathHtml()));
            try {
                template.process(input, fileWriter);
            } finally {
                fileWriter.close();
            }

        } catch (TemplateException ex) {
            Logger.getLogger(HtmlPrinter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HtmlPrinter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
