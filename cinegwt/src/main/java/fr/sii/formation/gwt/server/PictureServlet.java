package fr.sii.formation.gwt.server;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import fr.sii.formation.gwt.server.service.PhotoService;
import fr.sii.formation.gwt.server.service.PosterService;

public class PictureServlet extends HttpServlet {
	private static final long serialVersionUID = -1L;

	private WebApplicationContext webApplicationContext;

	public WebApplicationContext getWebApplicationContext() {
		return webApplicationContext;
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletConfig.getServletContext());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpg");

		String id = request.getParameter("id");
		String type = request.getParameter("type");
		if (id != null && type != null) {
			byte[] picture = null;
			if (type.equals("poster")) {
				PosterService posterService = (PosterService) getWebApplicationContext().getBean("posterService");
				picture = posterService.getPoster(Long.parseLong(id));
			} else if (type.equals("photo")) {
				PhotoService photoService = (PhotoService) getWebApplicationContext().getBean("photoService");
				picture = photoService.getPhoto(Long.parseLong(id));
			}

			if (picture != null) {
				String scale = request.getParameter("scale");
				if (scale != null) {
					response.getOutputStream().write(MovieHelper.scale(picture));
				} else {
					response.getOutputStream().write(picture);
				}
			}
		}
	}
}
