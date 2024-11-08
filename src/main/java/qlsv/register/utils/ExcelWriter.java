
package qlsv.register.utils;
import qlsv.register.controller.StatisticsController;
import qlsv.register.dao.StudentDao;
import qlsv.register.dao.SubjectDao;
import qlsv.register.entity.Student;
import qlsv.register.entity.Subject;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.OverlappingFileLockException;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ExcelWriter {
    private static final String STUDENT_FILE_NAME = "student.xlsx";
    private List<Student> listStudents;
    private StudentDao studentDao = new StudentDao ();
    private SubjectDao subjectDao = new SubjectDao ();

    public ExcelWriter() {
       
    }
    
    
    public void ExcelWriterStudent () {
        this.writeStudentsToExcel(studentDao.getListStudents());
    }
    
    public void ExcelWriterSubject (){
        this.writeSubjectsToExcel(subjectDao.getListSubjects());
    }
     
    
    public static void writeStudentsToExcel (List <Student> list) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Học viên");

        XSSFRow row = null;
        Cell cell = null;

        // Tạo một Font mới
        XSSFFont font = workbook.createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 13);
        font.setBold(true);

        XSSFFont fontStudent = workbook.createFont();
        fontStudent.setFontName("Times New Roman");
        fontStudent.setFontHeightInPoints((short) 12);
        fontStudent.setBold(false);
                
                
                

        // Tạo một CellStyle mới và thiết lập font chữ
        CellStyle cellStyleHeader = workbook.createCellStyle();
        cellStyleHeader.setFont(font);
        cellStyleHeader.setAlignment(HorizontalAlignment.CENTER);
        cellStyleHeader.setVerticalAlignment(VerticalAlignment.CENTER);

        //Cho nội dung cần căn giữa
        CellStyle cellStyleContentOfTable1 = workbook.createCellStyle(); 
        cellStyleContentOfTable1.setFont(fontStudent);
        cellStyleContentOfTable1.setAlignment(HorizontalAlignment.CENTER);
        cellStyleContentOfTable1.setVerticalAlignment(VerticalAlignment.CENTER);

        //Cho nội dung không cần căn giữa và sử dụng wraptext (xuống dòng trong ô của file excel
        CellStyle cellStyleContentOfTable2 = workbook.createCellStyle(); 
        cellStyleContentOfTable2.setFont(fontStudent);
        cellStyleContentOfTable2.setAlignment(HorizontalAlignment.LEFT);
        cellStyleContentOfTable2.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyleContentOfTable2.setWrapText(true);


        row = sheet.createRow(0);
        // Merge cell từ (0, 0) đến (10, 0) và thông tin vào 
        CellRangeAddress mergedRegion = new CellRangeAddress(0, 0, 0, 10);
        sheet.addMergedRegion(mergedRegion);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("BẢNG THỐNG KÊ THÔNG TIN SINH VIÊN");
        cell.setCellStyle(cellStyleHeader);

        row = sheet.createRow(1);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("STT");
        cell.setCellStyle(cellStyleHeader);
        sheet.setColumnWidth(0, 9 * 256);

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("MSV");
        cell.setCellStyle(cellStyleHeader);
        sheet.setColumnWidth(1, 10 * 256);

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Họ và tên");
        cell.setCellStyle(cellStyleHeader);
        sheet.setColumnWidth(2, 30 * 256);

        cell = row.createCell(3, CellType.STRING);
        cell.setCellStyle(cellStyleHeader);
        cell.setCellValue("Giới tính");
        sheet.setColumnWidth(3, 12 * 256);

        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Ngày sinh");
        cell.setCellStyle(cellStyleHeader);
        sheet.setColumnWidth(4, 12 * 256);

        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("Địa chỉ");
        cell.setCellStyle(cellStyleHeader);
        sheet.setColumnWidth(5, 17 * 256); //Set độ rộng cột

        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("Chuyên ngành");
        cell.setCellStyle(cellStyleHeader);
        sheet.setColumnWidth(6, 17 * 256);

        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("Kì học");
        cell.setCellStyle(cellStyleHeader);
        sheet.setColumnWidth(7, 13 * 256);

        cell = row.createCell(8, CellType.STRING);
        cell.setCellValue("Giới hạn số TC");
        cell.setCellStyle(cellStyleHeader);
        sheet.setColumnWidth(8, 20 * 256);

        cell = row.createCell(9, CellType.STRING);
        cell.setCellValue("Môn học đã đăng ký");
        cell.setCellStyle(cellStyleHeader);
        sheet.setColumnWidth(9, 25 * 256);

        cell = row.createCell(10, CellType.STRING);
        cell.setCellValue("Số TC tích lũy");
        cell.setCellStyle(cellStyleHeader);
        sheet.setColumnWidth(10, 20 * 256);
            
        if (list != null){
            try {
                for (int i =0; i<list.size(); i++){
                    Student student = list.get(i);

                    row = sheet.createRow(2 + i);

                    cell = row.createCell(0, CellType.NUMERIC);
                    cell.setCellValue(i+1);
                    cell.setCellStyle(cellStyleContentOfTable1);

                    cell = row.createCell(1, CellType.STRING);
                    cell.setCellValue(student.getID());
                    cell.setCellStyle(cellStyleContentOfTable1);

                    cell = row.createCell(2, CellType.STRING);
                    cell.setCellValue(student.getName());
                    cell.setCellStyle(cellStyleContentOfTable2);

                    cell = row.createCell(3, CellType.STRING);
                    cell.setCellValue(student.getGender());
                    cell.setCellStyle(cellStyleContentOfTable1);

                    cell = row.createCell(4, CellType.STRING);
                    cell.setCellValue(student.getDob());
                    cell.setCellStyle(cellStyleContentOfTable1);

                    cell = row.createCell(5, CellType.STRING);
                    cell.setCellValue(student.getAddress());
                    cell.setCellStyle(cellStyleContentOfTable1);

                    cell = row.createCell(6, CellType.STRING);
                    cell.setCellValue(student.getMajor());
                    cell.setCellStyle(cellStyleContentOfTable1);

                    cell = row.createCell(7, CellType.STRING);
                    cell.setCellValue(student.getTerm());
                    cell.setCellStyle(cellStyleContentOfTable1);

                    cell = row.createCell(8, CellType.STRING);
                    cell.setCellValue(student.getLimitedCredit());
                    cell.setCellStyle(cellStyleContentOfTable1);
                    
                    String subject ="";
                    for (int j =0; j< student.getSubject().size(); j++){
                        subject += student.getSubject().get(j).getName();
                        if (j < student.getSubject().size()-1)
                        subject += ", ";
                    }
                    
                    cell = row.createCell(9, CellType.STRING);              
                    cell.setCellValue(subject);
                    cell.setCellStyle(cellStyleContentOfTable2);

                    cell = row.createCell(10, CellType.STRING);
                    cell.setCellValue(student.getRegistedCredit());
                    cell.setCellStyle(cellStyleContentOfTable1);
                }

                //save file
                File f = new File ("Student.xlsx");
                FileOutputStream fis = null;
                try {
                    fis = new FileOutputStream (f);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    workbook.write (fis);
                } catch (IOException ex) {
                    Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                fis.close();

            } catch (IOException ex) {
                Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
     public static void writeSubjectsToExcel (List <Subject> list) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Môn học");

        XSSFRow row = null;
        Cell cell = null;

        // Tạo một Font mới
        XSSFFont font = workbook.createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 13);
        font.setBold(true);

        XSSFFont fontStudent = workbook.createFont();
        fontStudent.setFontName("Times New Roman");
        fontStudent.setFontHeightInPoints((short) 12);
        fontStudent.setBold(false);
                
                
                

        // Tạo một CellStyle mới và thiết lập font chữ
        CellStyle cellStyleHeader = workbook.createCellStyle();
        cellStyleHeader.setFont(font);
        cellStyleHeader.setAlignment(HorizontalAlignment.CENTER);
        cellStyleHeader.setVerticalAlignment(VerticalAlignment.CENTER);

        //Cho nội dung cần căn giữa
        CellStyle cellStyleContentOfTable1 = workbook.createCellStyle(); 
        cellStyleContentOfTable1.setFont(fontStudent);
        cellStyleContentOfTable1.setAlignment(HorizontalAlignment.CENTER);
        cellStyleContentOfTable1.setVerticalAlignment(VerticalAlignment.CENTER);

        //Cho nội dung không cần căn giữa và sử dụng wraptext (xuống dòng trong ô của file excel
        CellStyle cellStyleContentOfTable2 = workbook.createCellStyle(); 
        cellStyleContentOfTable2.setFont(fontStudent);
        cellStyleContentOfTable2.setAlignment(HorizontalAlignment.LEFT);
        cellStyleContentOfTable2.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyleContentOfTable2.setWrapText(true);


        row = sheet.createRow(0);
        // Merge cell từ (0, 0) đến (10, 0) và thông tin vào 
        CellRangeAddress mergedRegion = new CellRangeAddress(0, 0, 0, 9);
        sheet.addMergedRegion(mergedRegion);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("BẢNG THỐNG KÊ THÔNG TIN HỌC PHẦN");
        cell.setCellStyle(cellStyleHeader);

        row = sheet.createRow(1);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("STT");
        cell.setCellStyle(cellStyleHeader);
        sheet.setColumnWidth(0, 9 * 256);

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Mã lớp");
        cell.setCellStyle(cellStyleHeader);
        sheet.setColumnWidth(1, 20 * 256);

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Tên môn học");
        cell.setCellStyle(cellStyleHeader);
        sheet.setColumnWidth(2, 25 * 256);

        cell = row.createCell(3, CellType.STRING);
        cell.setCellStyle(cellStyleHeader);
        cell.setCellValue("Số tín chỉ");
        sheet.setColumnWidth(3, 15 * 256);

        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Loại lớp");
        cell.setCellStyle(cellStyleHeader);
        sheet.setColumnWidth(4, 20 * 256);

        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("Giảng viên");
        cell.setCellStyle(cellStyleHeader);
        sheet.setColumnWidth(5, 25 * 256); //Set độ rộng cột

        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("Hình thức thi");
        cell.setCellStyle(cellStyleHeader);
        sheet.setColumnWidth(6, 17 * 256);

        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("Số sinh viên đã đăng kí");
        cell.setCellStyle(cellStyleHeader);
        sheet.setColumnWidth(7, 30 * 256);

        cell = row.createCell(8, CellType.STRING);
        cell.setCellValue("Giới hạn số sinh viên");
        cell.setCellStyle(cellStyleHeader);
        sheet.setColumnWidth(8, 30 * 256);

        cell = row.createCell(9, CellType.STRING);
        cell.setCellValue("Trạng thái");
        cell.setCellStyle(cellStyleHeader);
        sheet.setColumnWidth(9, 22 * 256);

 

//        List <Student> list = new ArrayList <>();
//        list.addAll(studentDao.getListStudents());
            
        if (list != null){
            try {
                for (int i =0; i<list.size(); i++){
                    Subject subject = list.get(i);

                    row = sheet.createRow(2 + i);

                    cell = row.createCell(0, CellType.NUMERIC);
                    cell.setCellValue(i+1);
                    cell.setCellStyle(cellStyleContentOfTable1);

                    cell = row.createCell(1, CellType.STRING);
                    cell.setCellValue(subject.getIDclass());
                    cell.setCellStyle(cellStyleContentOfTable1);

                    cell = row.createCell(2, CellType.STRING);
                    cell.setCellValue(subject.getName());
                    cell.setCellStyle(cellStyleContentOfTable2);

                    cell = row.createCell(3, CellType.STRING);
                    cell.setCellValue(subject.getCredit() + "");
                    cell.setCellStyle(cellStyleContentOfTable1);

                    cell = row.createCell(4, CellType.STRING);
                    cell.setCellValue(subject.getTypeClass());
                    cell.setCellStyle(cellStyleContentOfTable1);

                    cell = row.createCell(5, CellType.STRING);
                    cell.setCellValue(subject.getTeacher());
                    cell.setCellStyle(cellStyleContentOfTable1);

                    cell = row.createCell(6, CellType.STRING);
                    cell.setCellValue(subject.getExam());
                    cell.setCellStyle(cellStyleContentOfTable1);

                    cell = row.createCell(7, CellType.STRING);
                    cell.setCellValue(subject.getRegistedStudent());
                    cell.setCellStyle(cellStyleContentOfTable1);

                    cell = row.createCell(8, CellType.STRING);
                    cell.setCellValue(subject.getQuantity());
                    cell.setCellStyle(cellStyleContentOfTable1);
                    
                    
                    cell = row.createCell(9, CellType.STRING);
                    if (subject.getRegistedStudent() < subject.getQuantity()) {
                        cell.setCellValue("Còn trống");
                        cell.setCellStyle(cellStyleContentOfTable1);
                    } else {
                        cell.setCellValue("Đã đầy");
                        cell.setCellStyle(cellStyleContentOfTable1);
                    }
                }

                //save file
                File f = new File ("Subject.xlsx");
                FileOutputStream fis = null;
                try {
                    fis = new FileOutputStream (f);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    workbook.write (fis);
                } catch (IOException ex) {
                    Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                fis.close();

            } catch (IOException ex) {
                Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void openFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File không tồn tại");
        } else {
            try (FileChannel channel = FileChannel.open(file.toPath(), StandardOpenOption.WRITE)) {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
                
            } catch (OverlappingFileLockException e) {
                System.out.println("File đang được mở ở một chương trình khác.");
            } catch (IOException e) {
                System.out.println("Lỗi khi mở file: " + e.getMessage());
            }
        }
    }   
}

         
         
       
    
