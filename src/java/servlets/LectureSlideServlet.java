package servlets;

import beans.LectureSlide;
import beans.Course;
import dao.LectureSlideDAO;
import dao.CourseDAO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/lectures")
@MultipartConfig
public class LectureSlideServlet extends HttpServlet {
    private LectureSlideDAO lectureSlideDAO;
    private CourseDAO courseDAO;
    
    @Override
    public void init() {
        lectureSlideDAO = new LectureSlideDAO();
        courseDAO = new CourseDAO();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            
            if (action == null) {
                action = "view";
            }
            
            switch (action) {
                case "upload":
                    showUploadForm(request, response);
                    break;
                case "download":
                    downloadSlide(request, response);
                    break;
                case "delete":
                    deleteSlide(request, response);
                    break;
                case "edit":
                    showEditSlideForm(request, response);
                    break;
                case "update":
                    updateSlide(request, response);
                    break;
                case "view":
                default:
                    viewSlides(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
        String action = request.getParameter("action");
        
        if (action == null) {
            response.sendRedirect("lectures");
            return;
        }
        
        switch (action) {
            case "upload":
                uploadSlide(request, response);
                break;
            case "update":
                updateSlide(request, response);
                break;
            default:
                response.sendRedirect("lectures");
                break;
        }
    } catch (SQLException ex) {
        throw new ServletException(ex);
    }
}
    
    private void showUploadForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        Course course = courseDAO.getCourseById(courseId);
        request.setAttribute("course", course);
        request.getRequestDispatcher("/lectures/upload.jsp").forward(request, response);
    }
    
    private void viewSlides(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        List<LectureSlide> slides = lectureSlideDAO.getSlidesByCourse(courseId);
        Course course = courseDAO.getCourseById(courseId);
        
        request.setAttribute("slides", slides);
        request.setAttribute("course", course);
        request.getRequestDispatcher("/lectures/view.jsp").forward(request, response);
    }
    
    private void uploadSlide(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        
        Part filePart = request.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        
        // Create upload directory if it doesn't exist
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        
        // Save file
        String filePath = uploadPath + File.separator + fileName;
        try (InputStream fileContent = filePart.getInputStream()) {
            Files.copy(fileContent, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        }
        
        // Save to database
        LectureSlide slide = new LectureSlide();
        slide.setCourseId(courseId);
        slide.setTitle(title);
        slide.setDescription(description);
        slide.setFileName(fileName);
        slide.setFilePath(filePath);
        
        lectureSlideDAO.uploadLectureSlide(slide);
        
        response.sendRedirect("lectures?action=view&courseId=" + courseId);
    }
    
    private void downloadSlide(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int slideId = Integer.parseInt(request.getParameter("slideId"));
        LectureSlide slide = lectureSlideDAO.getSlideById(slideId);
        
        if (slide == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        
        File file = new File(slide.getFilePath());
        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + slide.getFileName() + "\"");
        
        Files.copy(file.toPath(), response.getOutputStream());
    }
    
    private void deleteSlide(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
    int slideId = Integer.parseInt(request.getParameter("slideId"));
    int courseId = Integer.parseInt(request.getParameter("courseId"));
    lectureSlideDAO.deleteSlide(slideId);
    response.sendRedirect("lectures?action=view&courseId=" + courseId);
}

    private void showEditSlideForm(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
    int slideId = Integer.parseInt(request.getParameter("slideId"));
    LectureSlide slide = lectureSlideDAO.getSlideById(slideId);
    Course course = courseDAO.getCourseById(slide.getCourseId());
    
    request.setAttribute("slide", slide);
    request.setAttribute("course", course);
    request.getRequestDispatcher("/lectures/edit.jsp").forward(request, response);
}
    
    private void updateSlide(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
    int slideId = Integer.parseInt(request.getParameter("slideId"));
    int courseId = Integer.parseInt(request.getParameter("courseId"));
    String title = request.getParameter("title");
    String description = request.getParameter("description");
    
    LectureSlide slide = new LectureSlide();
    slide.setSlideId(slideId);
    slide.setTitle(title);
    slide.setDescription(description);
    
    lectureSlideDAO.updateSlide(slide);
    response.sendRedirect("lectures?action=view&courseId=" + courseId);
}
}