package ui;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GiaoDienChonGhe extends JPanel implements ActionListener {
    private JButton datVe_button;
    private List<JButton> seatButtons; // Danh sách các nút ghế
    private List<JButton> selectedSeats; // Danh sách các nút ghế đã chọn

    public GiaoDienChonGhe() {
        setLayout(new BorderLayout());
        selectedSeats = new ArrayList<>();
        HangGhe(); // Gọi phương thức HangGhe() để thêm chức năng chọn ghế
        JPanel south = new JPanel(new BorderLayout());
        south.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 10));

        Box x = Box.createVerticalBox();
        Label info_wrapper1 = new Label("Lật Mặt 6");
        info_wrapper1.setFont(new Font("Arial", Font.BOLD, 23));
        Label info_wrapper2 = new Label("2D Phụ Đề Anh");

        Box y = Box.createHorizontalBox();
        Label total = new Label("Tổng Tiền:");
        total.setFont(new Font("Arial", Font.BOLD, 23));
        Label order_Total = new Label("000.000đ");
        order_Total.setFont(new Font("Arial", Font.BOLD, 23));

        JPanel labelsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        x.add(info_wrapper1);
        x.add(info_wrapper2);
        y.add(total);
        y.add(order_Total);
        x.add(y);
        labelsPanel.add(x);
        south.add(labelsPanel, BorderLayout.WEST);

        JPanel datVe_panel = new JPanel();
        datVe_panel.setLayout(new BorderLayout());
        datVe_button = new JButton("Đặt Vé");
        datVe_button.setBackground(Color.red);
        datVe_button.setForeground(Color.white);
        datVe_button.setFont(new Font("Arial", Font.BOLD, 18));
        datVe_button.setPreferredSize(new Dimension(100, 40));
        datVe_button.setBorderPainted(false);
        datVe_panel.add(datVe_button, BorderLayout.SOUTH);
        south.add(datVe_panel, BorderLayout.EAST);

        JPanel center = new JPanel();
        center.setBorder(new EmptyBorder(0, 30, 0, 0));
        center.setLayout(new BorderLayout());
        JLabel block_seats = new JLabel("Thường A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13");
        block_seats.setFont(new Font("Arial", Font.BOLD, 21));

        center.add(block_seats, BorderLayout.NORTH);
        south.add(center);

        add(south, BorderLayout.SOUTH);
    }

    public void HangGhe() {
        int kichThuocKhungNgang = 920;
        int kichThuocKhungDoc = 600;

        JPanel center = new JPanel();
        center.setPreferredSize(new Dimension(kichThuocKhungNgang, kichThuocKhungDoc));
        center.setLayout(new GridLayout(14, 1));
        int gapSize = 10;
        center.setBorder(new EmptyBorder(gapSize, gapSize, gapSize, gapSize));
        JLabel textManHinh = new JLabel("Màn Hình");
        Font customFont = new Font(textManHinh.getFont().getName(), Font.BOLD, 20);
        textManHinh.setFont(customFont);
        textManHinh.setHorizontalAlignment(JLabel.CENTER);
        center.add(textManHinh);

        seatButtons = new ArrayList<>(); // Khởi tạo danh sách nút ghế

        for (char row = 'A'; row <= 'I'; row++) {
            JPanel rowPanel = new JPanel(new GridLayout(1, 10));
            for (int i = 1; i <= 16; i++) {
                JButton button = new JButton(row + "" + i);
                button.setForeground(Color.WHITE);
                button.setBackground(new Color(192, 192, 192));
                if (row >= 'E' && row <= 'J') {
                    button.setBackground(new Color(255, 165, 0));
                }
                button.addActionListener(this); // Thêm trình xử lý sự kiện cho nút
                rowPanel.add(button);
                seatButtons.add(button); // Thêm nút vào danh sách
            }
            center.add(rowPanel);
        }
        JPanel totalPanel = new JPanel(new GridLayout(1, 6));
        for (int i = 1; i <= 8; i++) {
            JButton button = new JButton("K" + i);
            button.setForeground(Color.WHITE);
            button.setBackground(new Color(237, 90, 179));
            totalPanel.add(button);
            button.addActionListener(this); // Thêm trình xử lý sự kiện cho nút
            seatButtons.add(button); // Thêm nút vào danh sách
        }
        center.add(totalPanel);

        center.add(Box.createRigidArea(new Dimension(5, 0)));

        JPanel dangChon = new JPanel();
        JButton dangChon_button = new JButton();
        dangChon_button.setEnabled(false);
        dangChon_button.setPreferredSize(new Dimension(25, 25));
        dangChon_button.setBackground(Color.darkGray);
        dangChon_button.setBorderPainted(false);
        JLabel dangChon_lable = new JLabel("Đang chọn");

        JButton daDat_button = new JButton();
        daDat_button.setEnabled(false);
        daDat_button.setPreferredSize(new Dimension(25, 25));
        daDat_button.setBackground(Color.gray);
        daDat_button.setBorderPainted(false);
        JLabel daDat_lable = new JLabel("Đã đặt");

        JButton thuong_button = new JButton();
        thuong_button.setEnabled(false);
        thuong_button.setPreferredSize(new Dimension(25, 25));
        thuong_button.setBackground(new Color(192, 192, 192));
        thuong_button.setBorderPainted(false);
        JLabel thuong_lable = new JLabel("Thường");

        dangChon.add(dangChon_button);
        dangChon.add(dangChon_lable);
        dangChon.add(daDat_button);
        dangChon.add(daDat_lable);
        dangChon.add(thuong_button);
        dangChon.add(thuong_lable);

        center.add(dangChon);

        JPanel dangChon1 = new JPanel();
        JButton gheDoi_button = new JButton();
        gheDoi_button.setEnabled(false);
        gheDoi_button.setPreferredSize(new Dimension(25, 25));
        gheDoi_button.setBackground(new Color(237, 90, 179));
        gheDoi_button.setBorderPainted(false);
        JLabel gheDoi_lable = new JLabel("Ghế đôi");

        JButton vIP_button = new JButton();
        vIP_button.setEnabled(false);
        vIP_button.setPreferredSize(new Dimension(25, 25));
        vIP_button.setBackground(Color.orange);
        vIP_button.setBorderPainted(false);
        JLabel vIP_lable = new JLabel("VIP");

        dangChon1.add(gheDoi_button);
        dangChon1.add(gheDoi_lable);
        dangChon1.add(vIP_button);
        dangChon1.add(vIP_lable);

        center.add(dangChon1);
        add(center, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o instanceof JButton) {
            JButton clickedButton = (JButton) o;
            if (seatButtons.contains(clickedButton)) { // Kiểm tra xem nút được click có trong danh sách ghế không
                Color currentColor = clickedButton.getBackground();
                if (currentColor.equals(Color.darkGray)) {
                    clickedButton.setBackground(getSeatColor(clickedButton));
                    selectedSeats.remove(clickedButton); // Xóa nút khỏi danh sách đã chọn nếu bỏ chọn
                } else {
                    clickedButton.setBackground(Color.darkGray);
                    selectedSeats.add(clickedButton); // Thêm nút vào danh sách đã chọn nếu chọn
                }
//                printSelectedSeatCount(); // In số lượng và tên các ghế đã chọn
            }
        }
    }
    
    public List<JButton> getSelectedSeats() {
        return selectedSeats;
    }


    private Color getSeatColor(JButton seatButton) {
        // Thực hiện xử lý logic để xác định màu ban đầu của ghế dựa vào vị trí của nút ghế
        // Trong ví dụ này, chúng ta sẽ trả về màu tùy thuộc vào vị trí của ghế
        // Ví dụ:
        // Nếu ghế nằm trong các hàng 'A' - 'D', sẽ trả về màu đặc trưng cho các ghế VIP
        // Nếu ghế nằm trong các hàng 'E' - 'I', sẽ trả về màu đặc trưng cho các ghế thường
        // Tùy thuộc vào logic cụ thể của bạn, bạn có thể thay đổi cách xác định màu của ghế ở đây
        if (seatButton.getText().charAt(0) >= 'A' && seatButton.getText().charAt(0) <= 'D') {
            return new  Color(192, 192, 192); // Màu cho ghế thường
        } 
        else if(seatButton.getText().charAt(0) == 'K') {
            
            return new Color(237, 90, 179); // Màu cho ghế đôi
        }
        else {
            return new Color (255, 165, 0); // Màu cho ghế VIP
        }
    }
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("GiaoDienChonGhe");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.getContentPane().add(new GiaoDienChonGhe());
//            frame.pack();
//            frame.setLocationRelativeTo(null);
//            frame.setVisible(true);
//            
//        });
//        
//    }
    
//    private void printSelectedSeatCount() {
//        System.out.println("Số ghế được chọn: " + selectedSeats.size());
//        System.out.println("Các ghế đã chọn:");
//        for (JButton seat : selectedSeats) {
//            System.out.println(seat.getText());
//        }
//    }
}
