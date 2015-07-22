package com.openhr.employee.action;

import static com.openhr.factories.EmployeeFactory.findByEmployeeId;
import static com.openhr.factories.EmployeeFactory.update;
import static java.lang.Math.random;
import static net.coobird.thumbnailator.Thumbnails.of;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.geometry.Positions;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.openhr.data.Employee;
import com.openhr.employee.PhotoCropDimension;
import com.openhr.employee.form.EmployeePhotoForm;

public class EmployeePhotoAction extends Action {

    @Override
    public ActionForward execute(ActionMapping map, ActionForm form,
            HttpServletRequest request, HttpServletResponse reponse)
            throws Exception {

        EmployeePhotoForm fileForm = (EmployeePhotoForm) form;

        FormFile file = fileForm.getPhotoFile();

        // Get the servers upload directory real path name
        String filePath = getServlet().getServletContext().getRealPath(
                "/data/photo");
		//System.out.println("Upload path : " + filePath);

        String tempFileName = file.getFileName();
        tempFileName = "temp_" + tempFileName;

        // create the upload folder if not exists
        File folder = new File(filePath);
        if (!folder.exists()) {
            folder.mkdir();
        }

        String fileName = file.getFileName();
        //System.out.println("File Name " + fileName);
        if (!("").equals(fileName)) {

            File newFile = new File(filePath, tempFileName);
            String filePath1 = newFile.getAbsolutePath();
            if (newFile.exists()
                    && !fileName.equalsIgnoreCase("placeholder-pic.png")) {
                // to append a random identifier
                double a = random() * 999999999;
                long b = (long) a;
                String extension = fileName.substring(
                        fileName.lastIndexOf("."), fileName.length());
                fileName = fileName.replace(extension, "_" + b + extension);
                //System.out.println("New File Name " + fileName);
                newFile = new File(filePath, fileName);
                List<Employee> empList = findByEmployeeId(PhotoCropDimension.employeeId);
                if (empList.size() != 0) {
                    Employee e = empList.get(0);
                    e.setPhoto("/data/photo/" + fileName);
                    update(e);
                }
            }
            newFile = new File(filePath, tempFileName);
            try (FileOutputStream fos = new FileOutputStream(newFile)) {
                fos.write(file.getFileData());
                fos.flush();
            }

            /*
             * 
             * reads the temporary actual file and
             * make a thumbnail of it and write it back with the original file name
             * 
             */
            File file1 = new File(filePath1);
            System.out.println(file1);
            of(new File(filePath1))
                    .crop(Positions.CENTER).size(200, 250)
                    .outputQuality(0.8f)
                    .toFile(new File(filePath, fileName));

            if (file1.exists()) {

                //System.out.println(file1.getAbsolutePath()+ "\nTEMPORARY FILE IS NOT DELETED, ENGAGING TO DELETE...");
                file1.delete();
            }

        }

        /*PhotoCropper pc = new PhotoCropper(getServlet().getServletContext()
         .getRealPath("/data/photo/" + fileName));
         BufferedImage croppedPhoto = pc.getCroppedImage();
         File newFile = new File(filePath, fileName);

         ImageIO.write(
         croppedPhoto,
         fileName.substring((fileName.indexOf(".") + 1),
         fileName.length()), newFile);*/
        //System.out.println("PHOTO - NAME " + fileName);
        request.setAttribute("photo-name", fileName);

        return map.findForward("");
    }
}
