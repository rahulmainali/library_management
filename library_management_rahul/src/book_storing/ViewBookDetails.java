import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.TableModel;


public class ViewBookDetails {
//	public static void main(String[] args) {
//		ViewBookDetails vbd = new ViewBookDetails();
//	}

	ViewBookDetails() {
		JFrame f = new JFrame("View Book Details");
		f.getContentPane().setBackground(Color.YELLOW);

		String column[] = { "Id", "Name", "PublisherName", "PublishedDate", "Status", "Price" };
		

		DbConnect db = new DbConnect();
		String query = "Select * from book_table";

		ArrayList<Book> bf = new ArrayList<Book>();
		try {
			ResultSet result = db.connection().executeQuery(query);

			while (result.next()) {
				int ID = Integer.parseInt(result.getString("Id"));
				String Name = result.getString("Name");
				String PublisherName = result.getString("PublisherName");
				String PublishedDate = result.getString("PublishedDate");
				String Status = result.getString("Status");
				int Price = Integer.parseInt(result.getString("Price"));

				Book bk = new Book(ID, Name, PublisherName, PublishedDate, Status, Price);
				bf.add(bk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Object data[][] = new Object[bf.size()][column.length];
		for (int i = 0; i < bf.size(); i++) {
			data[i][0] = bf.get(i).ID;
			data[i][1] = bf.get(i).Name;
			data[i][2] = bf.get(i).PublisherName;
			data[i][3] = bf.get(i).PublishedDate;
			data[i][4] = bf.get(i).Status;
			data[i][5] = bf.get(i).Price;
		}

		JTable jtBook = new JTable(data, column);
		jtBook.setAutoCreateRowSorter(true);
		JScrollPane sp = new JScrollPane(jtBook);
		f.add(sp);
		sp.setBounds(40, 40, 800, 370);

		JButton btnBack = new JButton("Back");
		f.add(btnBack);
		btnBack.setBounds(5, 5, 100, 30);
		btnBack.setFont(new Font("Serif", Font.CENTER_BASELINE,20));

		JButton btnUpdate = new JButton("Update");
		f.add(btnUpdate);
		btnUpdate.setBounds(40, 440, 100, 30);
		btnUpdate.setFont(new Font("Serif", Font.CENTER_BASELINE,20));

		JButton btnDelete = new JButton("Delete");
		f.add(btnDelete);
		btnDelete.setBounds(740, 440, 100, 30);
		btnDelete.setFont(new Font("Serif", Font.CENTER_BASELINE,20));

		btnBack.addActionListener(e -> {
			new BookForm();
			f.dispose();
		});

		btnDelete.addActionListener(e -> {
			int row = jtBook.getSelectedRow();
			if (row >= 0) {
				TableModel model = jtBook.getModel();
				int ID = (int) model.getValueAt(row, 0);
				String dquery = "Delete from book_table where id ='" + ID + "'";

				try {
					int dresult = db.connection().executeUpdate(dquery);
					if (dresult >= 1) {
						JOptionPane.showMessageDialog(sp, "Book Deleted");
						new ViewBookDetails();
						f.dispose();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} else {

				JOptionPane.showMessageDialog(sp, "please select Row");

			}
		});

		btnUpdate.addActionListener(e -> {
			int row = jtBook.getSelectedRow();
			System.out.println(row);
			if (row >=0) {

				JLabel lId = new JLabel("Id");
				f.add(lId);
				lId.setBounds(50, 500, 100, 50);
				JTextField tfId = new JTextField();
				f.add(tfId);
				tfId.setBounds(90, 510, 60, 30);

				JLabel lName = new JLabel("Name");
				f.add(lName);
				lName.setBounds(180, 500, 100, 50);
				JTextField tfName = new JTextField();
				f.add(tfName);
				tfName.setBounds(240, 510, 150, 30);

				JLabel lPName = new JLabel("Publisher Name");
				f.add(lPName);
				lPName.setBounds(420, 500, 200, 50);
				JTextField tfPName = new JTextField();
				f.add(tfPName);
				tfPName.setBounds(530, 510, 200, 30);

				JLabel lPDate = new JLabel("Published Date");
				f.add(lPDate);
				lPDate.setBounds(770, 500, 200, 50);
				JTextField tfDate = new JTextField();
				f.add(tfDate);
				tfDate.setBounds(880, 510, 200, 30);