package fr.sii.nosql.server;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import fr.sii.nosql.server.repository.file.FilePhotoRepository;
import fr.sii.nosql.server.repository.file.FilePosterRepository;

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
				FilePosterRepository posterRepository = (FilePosterRepository) getWebApplicationContext().getBean("posterRepository");
				picture = posterRepository.getPoster(Long.parseLong(id));
			} else if (type.equals("photo")) {
				FilePhotoRepository photoRepository = (FilePhotoRepository) getWebApplicationContext().getBean("photoRepository");
				picture = photoRepository.getPhoto(Long.parseLong(id));
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
