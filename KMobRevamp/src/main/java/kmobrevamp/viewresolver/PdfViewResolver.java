package kmobrevamp.viewresolver;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import kmobrevamp.builder.PdfView;

public class PdfViewResolver implements ViewResolver{

	@Override
	public View resolveViewName(String s, Locale locale) throws Exception {
		
		PdfView view=new PdfView();
		return view;
		
	}

	
}
