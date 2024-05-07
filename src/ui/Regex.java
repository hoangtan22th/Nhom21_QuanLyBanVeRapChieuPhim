package ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Regex {
	public boolean regexTenPhim(JTextField tfTenPhim) {
		String input = tfTenPhim.getText().trim();
		String regex = "^([ A-Za-za-zA-Z0-9/-ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+(\\s?))+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.find()) {
			JOptionPane.showMessageDialog(null, "Tên phim không hợp lệ!\nMẫu: Đố em vì sao trái đất hình tròn", "Thông báo",
					JOptionPane.ERROR_MESSAGE);
			tfTenPhim.requestFocus();
			tfTenPhim.selectAll();
			return false;
		} else
			return true;
	}
	
	public boolean regexThoiLuong(JTextField tfThoiluong) {
		String input = tfThoiluong.getText().trim();
		String regex = "^[1-9]+[0-9]*(\\.[0-9]+)?$";
		if (!input.matches(regex)) {
			JOptionPane.showMessageDialog(null,
					"Thời lượng không được để trống, không được nhập chữ và phải lớn hơn 0\nVí dụ: 130", "Thông báo",
					JOptionPane.ERROR_MESSAGE);
			tfThoiluong.requestFocus();
			tfThoiluong.selectAll();
			return false;
		}
		return true;
	}
	
	public boolean regexGiaPhim(JTextField tfGiaPhim) {
		String input = tfGiaPhim.getText();
		String regex = "^[1-9]+[0-9]*(\\.[0-9]+)?$";
		if (!input.matches(regex)) {
			JOptionPane.showMessageDialog(null,
					"Giá phim không được để trống, không được nhập chữ và phải lớn hơn 0\nVí dụ: 1000000", "Thông báo",
					JOptionPane.ERROR_MESSAGE);
			tfGiaPhim.requestFocus();
			tfGiaPhim.selectAll();
			return false;
		}
		return true;
	}
	
	
}
