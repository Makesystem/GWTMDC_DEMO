package gwt.material.design.components.demo.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.fileupload.util.Streams;

public class FileUploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2437603494034775907L;

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {

		if (!ServletFileUpload.isMultipartContent(request)) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Not a multipart request");
			return;
		}
		try {
			
			final RequestContext ctx = new ServletRequestContext(request);
			final ServletFileUpload upload = new ServletFileUpload();
			final FileItemIterator iter = upload.getItemIterator(ctx);

			final Map<String, String> params = new LinkedHashMap<>();
			final List<FileItem> items = new ArrayList<FileItem>();
			final FileItemFactory fileItemFactory = new DiskFileItemFactory();
			while (iter.hasNext()) {

				final FileItemStream item = iter.next();

				System.out.println("FieldName: " + item.getFieldName());
				System.out.println("ContentType: " + item.getContentType());
				System.out.println("FormField: " + item.isFormField());
				System.out.println("Name: " + item.getName());

				final Iterator<String> headers = item.getHeaders().getHeaderNames();
				while (headers.hasNext()) {
					final String header = headers.next();
					System.out.println("header: " + header + " = " + item.getHeaders().getHeader(header));
				}

				final FileItem fileItem = fileItemFactory.createItem(item.getFieldName(), item.getContentType(),
						item.isFormField(), item.getName());

				Streams.copy(item.openStream(), fileItem.getOutputStream(), true);

				System.out.println("Content:");
				System.out.println(fileItem.getString("UTF-8"));

				if (item.isFormField())
					params.put(item.getFieldName(), fileItem.getString("UTF-8"));
				else
					items.add(fileItem);

			}

			response.getWriter().print("Fez o upload do arquivo");
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
