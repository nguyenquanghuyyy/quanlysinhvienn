# Đề tài 11: Quản lý Đăng ký môn học của sinh viên 

## Bộ code final
link github: https://github.com/nguyenquanghuyyy/quanlysinhvienn.git

## Giới Thiệu

Dự án "quản lý đăng ký môn học của sinh viên" là một ứng dụng Java sử dụng giao diện Swing nhằm hỗ trợ quản lý sinh viên và môn học đồng thời giúp trong việc đăng ký môn học và thống kê. Ứng dụng cho phép người dùng dễ dàng đăng nhập, thêm mới thông tin sinh viên và môn học cũng như dẽ dàng quản lý việc đăng ký môn học của sinh viên.

Lưu ý,res github mà bọn em gửi vào link nộp hôm 22/10/2024 bi lỗi mất hết commit nên bọn em đã tạo res gihub mới, 
file jar đóng gói của bọn e bị lỗi chỉ có thể đăng nhập được, vậy nên nếu thầy/cô chấm bài muốn kiểm tra hoạt động của hệ thống thì có thể vào netbean để chạy code trong đó ạ, em cảm ơn ạ.

Nhóm 15:
Nguyễn Quang Huy MSV 23010731
Lý Hoài Nam MSV 23010773
1. Tài khoản admin login
username: 1
password: 1

2. Môi trường
    1. NetBeans 23, JDK 23
    2. Dependencies: Apache Commons, JFreeChar, JCalendar,..
3. Cơ sở dữ liệu
    1. Lưu trữ trong xml.
    2. Gồm cơ sở dữ liệu của sinh viên và các môn học (student.xml và subject.xml).
4. Giao diện
    1. Sử dụng Apache Commons để hỗ trợ cho việc thao tác trên giao diện JCalendar cho chọn lịch cho ngày tháng năm sinh,..
5. Thực thể 
    1 sinh viên:
    String ID;
    String ten;
    String gioitinh;
    String ngaysinh;
    String diachi;
    String chuyennganh;
    String kyhoc;
    int sotinchitoida;
    int sotinchidadangky;

    2. môn học
    String tenmonhoc;
    String IDclass;
    String loaimonhoc;
    String thayco;
    int sotinchi;
    String baikiemtra;
    int soluongtoida;
    int soluongsinhviendangky;
6. View
    1. LoginView: Màn hình Đăng nhập.
    2. OptionView: Màn hình lựa chọn tính năng.
    3. RegisterView: Màn hình đăng ký môn học.
    4. StatisticsView, chartView: Màn hình thống kê.
    5. StudentView: màn hình quản lý sinh viên.
    6. SubjectView: Màn hình quản lý môn học.
7. Chức năng
    1. Thêm/sửa/xóa/sắp xếp/tìm kiếm(theo tên, id, chuyên ngành đối với sinh viên, theo tên, id đối với môn học) sinh viên và môn học.
    2. Đăng ký môn, quản lý việc đăng ký môn.
    3. thống kê sinh viên và môn học.
8. Hướng dẫn sử dụng

 8.1 cài dặt 
Có thể download folder source code được đính kèm trong bài nộp và mở trong ide(netbean) vì file jar đóng gói của bọn em không hoạt động tốt
Bước 1: Cài Đặt JDK

- Tải và cài đặt Java Development Kit (JDK) từ trang chính thức của Oracle: [Download JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

 Bước 2: Clone Repository

- Sử dụng Git để clone repository về máy tính của bạn:
   ```bash
   git clone <repository-url>
- Có thể download folder source code được đính kèm trong bài nộp và mở trong ide(netbean) vì file jar đóng gói của bọn em không hoạt động tốt
Bước 3: Mở Dự Án


- Mở IDE mà bạn ưa thích (IntelliJ IDEA, Eclipse, NetBeans, v.v.) và nhập dự án đã clone.

 Bước 4: Chạy Ứng Dụng
- Chạy file App.javađể khởi động ứng dụng.

8.2 Hướng Dẫn Sử Dụng
- Đăng Nhập:

- Khi ứng dụng khởi động, một cửa sổ đăng nhập sẽ xuất hiện. Nhập thông tin tài khoản:
- Tên người dùng: 1
- Mật khẩu: 1
- Nhấn nút "Đăng nhập". Nếu thông tin đúng, bạn sẽ được chuyển đến giao diện chính.
- có thể chọn 1 trong 4 tính năng
  + Quản lý sinh viên
  + Quản lý môn học
  + Đăng ký môn học
  + Thống kê
  * Quản lý sinh viên
    - nhập thông tin sinh viên muốn thêm vào các ô bên trên để thêm
    - ấn vào sinh viên và ấn nút xóa để xóa
    - ấn vào sinh viên và sửa lại thông tin ở các ô bên trên rồi ấn nút cập nhật để sửa thông tin sinh viên
    - ấn vào sắp xếp để sắp xếp
    - chọn loại tìm kiếm và nhập thông tin vào ô bên cạnh để tìm kiếm
* Quản lý môn học
    - nhập thông tin sinh môn học muốn thêm vào các ô bên trên để thêm
    - ấn vào môn học và ấn nút xóa để xóa
    - ấn vào môn học và sửa lại thông tin ở các ô bên trên rồi ấn nút cập nhật để sửa thông tin môn học
    - ấn vào sắp xếp để sắp xếp
    - chọn loại tìm kiếm và nhập thông tin vào ô bên cạnh để tìm kiếm
* Đăng ký môn học
    - ấn vào sinh viên để xem thông tin các môn học mà sinh viên đã đăng ký và thông tin của sinh viên
    - nhập thông tin của môn học vào các ô và ấn nút thêm để đăng ký môn học cho sinh viên
    - ấn vào môn học và ấn nút xóa để xóa môn học cảu sinh viên đó
    - ấn phân lớp để phân lớp cho sinh viên
* Thống kê
    - ấn biểu đồ thống kê để xem biêu đồ thống kê về sinh viên và các môn học
    - ấn in để in ra danh sách sinh viên/ môn học
* muốn quay trở lại thì ấn vào nút quay lại trên góc trái trên cùng của màn hình
* muốn đăng xuất thì ra quay lại màn hình các chức năng và ấn đăng xuất ở nút bên phải trên cùng màn hình
