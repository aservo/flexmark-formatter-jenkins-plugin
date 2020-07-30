package de.aservo.jenkins;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import hudson.Extension;
import hudson.markup.MarkupFormatter;
import hudson.markup.MarkupFormatterDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.Writer;

public class FlexmarkFormatter extends MarkupFormatter {

    @DataBoundConstructor
    public FlexmarkFormatter() {
    }

    @Override
    public void translate(
            final String markup,
            final Writer output) throws IOException {

        final MutableDataSet options = new MutableDataSet();
        final Parser parser = Parser.builder(options).build();
        final HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        final Node document = parser.parse(markup);
        output.write(renderer.render(document));
    }

    @Extension
    public static class DescriptorImpl extends MarkupFormatterDescriptor {

        @Nonnull
        @Override
        public String getDisplayName() {
            return "Flexmark (Markdown)";
        }

    }

}
