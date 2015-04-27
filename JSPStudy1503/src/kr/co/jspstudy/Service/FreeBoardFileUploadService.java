package kr.co.jspstudy.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.co.jspstudy.DAO.PdsItemDao;
import kr.co.jspstudy.DBLoader.DBConnect;
import kr.co.jspstudy.Helper.FileSaveHelper;
import kr.co.jspstudy.VO.PdsItem;


public class FreeBoardFileUploadService implements JSPService {	
	
	@Override
	public String doService(HttpServletRequest request,
			HttpServletResponse response) {
		Connection conn = DBConnect.getConnection();
		
		String contentType = request.getContentType();
		PdsItem uploadedItem = null;
		
		if(contentType !=null && contentType.toLowerCase().startsWith("multipart/")){		
			try {
				uploadedItem = saveUploadFile(request);	
				int result = PdsItemDao.getInstance().insert(conn, uploadedItem);
			} catch (IllegalStateException | IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		request.setAttribute("uploadedItem", uploadedItem);		
		return "list.do";
	}

	private PdsItem saveUploadFile(HttpServletRequest req) throws IOException,
			ServletException {
		Part descPart = req.getPart("description");
		String description = readParameterValue(descPart);
		Part filePart = req.getPart("file");
		String fileName = getFileName(filePart);
		String realPath = FileSaveHelper.save("c:\\uploadTemp", filePart.getInputStream());

		AddRequest addRequest = new AddRequest();	
		addRequest.setFileName(fileName);
		addRequest.setFileSize(filePart.getSize());
		addRequest.setDescription(description);
		addRequest.setRealPath(realPath);

		PdsItem pdsItem = add(addRequest);
		return pdsItem;
	}

	private String getFileName(Part part) throws UnsupportedEncodingException {
		for (String cd : part.getHeader("Content-Disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				return cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		return null;
	}

	private String readParameterValue(Part part) throws IOException {
		InputStreamReader reader = new InputStreamReader(part.getInputStream(),"utf8");
		char[] data = new char[512];
		int len = -1;
		StringBuilder builder = new StringBuilder();
		while ((len = reader.read(data)) != -1) {
			builder.append(data, 0, len);
		}
		return builder.toString();
	}
	
	//
	public PdsItem add(AddRequest request) {
		Connection conn = null;
		try {
			conn = DBConnect.getConnection();			
			
			PdsItem pdsItem = request.toPdsItem();
			int id = PdsItemDao.getInstance().insert(conn, pdsItem);
			return pdsItem;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}		
	}


}
