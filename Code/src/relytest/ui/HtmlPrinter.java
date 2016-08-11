/*
 * Copyright (C) 2016 Gabriela Sanchez - Miguel Sanchez
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
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

            input.put("Tester", dto.getDetails().getPlanification().getTester());
            input.put("PlanDuration", dto.getDetails().getPlanification().getDuration());
            input.put("OSname", dto.getDetails().getPlanification().getOperatingSystem());
            input.put("OSversion", dto.getDetails().getPlanification().getOperatingSystemVersion());
            input.put("OSarch", dto.getDetails().getPlanification().getOperatingSystemArch());

            input.put("UserName", dto.getDetails().getPlanification().getUserName());
            input.put("UserLanguage", dto.getDetails().getPlanification().getUserLanguage());
            input.put("UserTimezone", dto.getDetails().getPlanification().getUserTimezone());
            input.put("UserCountry", dto.getDetails().getPlanification().getUserCountry());

            input.put("Browser", dto.getDetails().getPlanification().getBrowser());
            dto.getDetails().getExecution().setNumberOfBugs(dto.getGroupNotes()[0].notes.size());
            dto.getDetails().getExecution().setNumberOfNotes(dto.getGroupNotes()[1].notes.size());
            dto.getDetails().getExecution().setNumberOfToDo(dto.getGroupNotes()[2].notes.size());
            dto.getDetails().getExecution().setNumberOfProblems(dto.getGroupNotes()[3].notes.size());
            dto.getDetails().getExecution().setNumberOfRisks(dto.getGroupNotes()[4].notes.size());

            input.put("numBugs", dto.getDetails().getExecution().getNumberOfBugs());
            input.put("numRisks", dto.getDetails().getExecution().getNumberOfRisks());
            input.put("numNotes", dto.getDetails().getExecution().getNumberOfNotes());
            input.put("numProblems", dto.getDetails().getExecution().getNumberOfProblems());
            input.put("numToDo", dto.getDetails().getExecution().getNumberOfToDo());

            input.put("RealDuration", dto.getDetails().getExecution().getRealDuration());

            input.put("FocusOnCharter", dto.getDetails().getMetrics().getFocusOnCharter());
            input.put("TimeSpentConfiguration", dto.getDetails().getMetrics().getTimeSpentConfiguration());
            input.put("TimeSpentReportingBugs", dto.getDetails().getMetrics().getTimeSpentReportingBugs());
            input.put("getTimeSpentTesting", dto.getDetails().getMetrics().getTimeSpentTesting());

            input.put("title", "RelyTest Summary");

            input.put("charterName", dto.getName());

            if (!dto.getNotesTaken().isEmpty()) {
                input.put("logNotes", dto.getNotesTaken());
            }
            for (int i = 0; i < 5; i++) {
                input.put("label" + i, dto.getGroupNotes()[i].getLabel());
                if (!dto.getGroupNotes()[i].notes.isEmpty()) {
                    input.put("label" + i + "badge", dto.getGroupNotes()[i].notes.size());
                    input.put("labels" + i, dto.getGroupNotes()[i].notes);
                }
            }

            if (!dto.getGroupNotes()[5].notes.isEmpty()) {
                input.put("label" + 5 + "badge", dto.getGroupNotes()[5].notes.size());
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
