package custom_tags;

import models.entity.Author;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class PrintAuthor extends TagSupport {
    private final Logger logger = Logger.getLogger(PrintAuthor.class);
    private final String langUk = "uk";

    private Author author;
    private String language;

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            if (language != null && language.equals(langUk)) {
                out.print(author.getNameUk());
            } else {
                out.print(author.getNameEn());
            }
        }catch (IOException e) {
            logger.error(e);
        }
        return super.doStartTag();
    }
}
