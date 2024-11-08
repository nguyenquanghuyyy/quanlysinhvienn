package qlsv.register.view;

import qlsv.register.dao.SubjectDao;
import qlsv.register.entity.Subject;
import java.util.HashMap;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.XChartPanel;

public class ChartView {
    private final HashMap<String, int[]> map = new HashMap<>();
    private final SubjectDao subjectDao = new SubjectDao();
    
    public void DataProcessing(List<Subject> subject) {
        for (Subject s : subjectDao.getListSubjects()) {
            int[] percent = new int[2];
            if (map.containsKey(s.getName())) {
                percent = map.get(s.getName());
                percent[0] += s.getRegistedStudent();
                percent[1] += s.getQuantity();
                map.put(s.getName(), percent);
            } else {
                percent[0] = s.getRegistedStudent();
                percent[1] = s.getQuantity();
                map.put(s.getName(), percent);
            }
        }
        
        CategoryChart barchart = new CategoryChartBuilder().width(1300).height(750).title("BIỂU ĐỒ THỂ HIỆN TỈ LỆ SINH VIÊN ĐÃ ĐĂNG KÝ CỦA MỖI MÔN HỌC")
                        .xAxisTitle("").yAxisTitle("Tỉ lệ sinh viên đăng ký (%)").build();

        
        int count = map.size();

        for (String s : map.keySet()) {
            int[] temp = map.get(s);
            double p = (double) (temp[0] * 100 / temp[1]);
            barchart.addSeries(s, new double[]{(double) count}, new double[]{(double) p});
        }


        barchart.getStyler().setYAxisMax(100.0); 

        barchart.getStyler().setXAxisTicksVisible(false);



        JFrame frame = new JFrame("Biểu Đồ Cột");
        JPanel panel = new XChartPanel<>(barchart);
        frame.add(panel);

        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle("Biểu đồ thống kê");
        ImageIcon icon = new ImageIcon("logo\\logo.png");
//        ImageIcon icon = new ImageIcon("src\\main\\resources\\img\\logo.png");
        frame.setIconImage(icon.getImage());
    }
}
