package custom_tags;

import models.entity.Edition;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class PrintEdition extends TagSupport {
    private final Logger LOGGER = Logger.getLogger(PrintEdition.class);

    private List<Edition> editions;
    private String language;

    public void setEditions(List<Edition> editions) {
        this.editions = editions;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

    private void printUkListOfEditions() throws IOException {
        JspWriter out = pageContext.getOut();
        for(Edition edition: editions){
            out.print("<div class=\"title_article\">\n");
            out.print(edition.getTitleUk());
            out.print("</div>\n" +
                    "<div class=\"description\">\n");
            out.print(edition.getDescriptionUk());
            out.print("</div>\n" +
                    "<div class=\"author\">\n" +
                    "<strong><fmt:message key=\"admin.form.author\"/>:</strong>");
            out.print(edition.getAuthor().getNameUk());
            out.print("</div>\n" +
                    "<div class=\"genre\">\n" +
                    "<strong><fmt:message key=\"admin.form.genre\"/>:</strong>");
            out.print(edition.getGenre().getNameUk());
            out.print("</div>");
        }
    }

    private void printEnListOfEditions() throws IOException {
        JspWriter out = pageContext.getOut();
        for(Edition edition: editions){
            out.print("<div class=\"title_article\">\n");
            out.print(edition.getTitleEn());
            out.print("</div>\n" +
                    "<div class=\"description\">\n");
            out.print(edition.getDescriptionEn());
            out.print("</div>\n" +
                    "<div class=\"author\">\n" +
                    "<strong><fmt:message key=\"admin.form.author\"/>:</strong>");
            out.print(edition.getAuthor().getNameEn());
            out.print("</div>\n" +
                    "<div class=\"genre\">\n" +
                    "<strong><fmt:message key=\"admin.form.genre\"/>:</strong>");
            out.print(edition.getGenre().getNameEn());
            out.print("</div>");
        }
    }
    @Override
    public int doStartTag() throws JspException {
        try {
            if (language != null && language.equals("uk")){
                printUkListOfEditions();
            }else{
                printEnListOfEditions();
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return SKIP_BODY;
    }
}
