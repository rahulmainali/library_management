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
				
				JLabel lStatus = new JLabel("Status");
				f.add(lStatus);
				lStatus.setBounds(280, 550, 200, 50);
				String s[]= {"Available", "Out of Stock"};
				JComboBox<String> c1= new JComboBox(s);
				f.add(c1);
				c1.setBounds(340, 560, 150, 30);

				JLabel lPrice = new JLabel("Price");
				f.add(lPrice);
				lPrice.setBounds(540, 550, 200, 50);
				JTextField tfPrice = new JTextField();
				f.add(tfPrice);
				tfPrice.setBounds(600, 560, 150, 30);

				JButton btnChange = new JButton("Make Change");
				f.add(btnChange);
				btnChange.setBounds(400, 600, 150, 45);

				JButton btnCancle = new JButton("Cancel");
				f.add(btnCancle);
				btnCancle.setBounds(580, 600, 150, 45);

				TableModel model = jtBook.getModel();
				int ID = (int) model.getValueAt(row, 0);
				tfId.setText(ID + "");

				tfName.setText((String) model.getValueAt(row, 1));
				tfPName.setText((String) model.getValueAt(row, 2));
				tfDate.setText((String) model.getValueAt(row, 3));
				c1.setSelectedItem((String) model.getValueAt(row, 4));
				int Price = (int) model.getValueAt(row, 5);
				tfPrice.setText(Price + "");

				// update action

				btnChange.addActionListener(e3 -> {
					int ID1 = Integer.parseInt(tfId.getText());
					String Name = tfName.getText();
					String PublisherName = tfPName.getText();
					String PublishedDate = tfDate.getText();
					String Status1 = (String) c1.getSelectedItem();
					int Price1 = Integer.parseInt(tfPrice.getText());
					int ID2 = (int) model.getValueAt(row, 0);
					String query1 = "Update book_table set Name='" + Name + "',PublisherName='" + PublisherName
							+ "',PublishedDate='" + PublishedDate + "',Status='" + Status1 + "', Price='" + Price
							+ "' where Id='" + ID1 + "'";

					System.out.println(query1);

					System.out.println(ID1 + Name + PublisherName + PublishedDate + Status1 + Price1);

					try {
						int upresult = db.connection().executeUpdate(query1);
						System.out.println(upresult);

						if (upresult > 0) {

							JOptionPane.showMessageDialog(sp, "Book updated");
							new ViewBookDetails();
							f.dispose();
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});

				// cancle action
				btnCancle.addActionListener(e2 -> {
					new ViewBookDetails();
					f.dispose();
				});

			} else {

				JOptionPane.showMessageDialog(sp, "Select Row for Update");
			}
		});
		
		JLabel lSearch, lSort;
		JTextField tfSearch;
		JTextField tfSearch1;
		JComboBox tfSort;
		JTextField tfSort1;
		
		JButton btnSearch, btnSort;
		
		lSearch= new JLabel("Search By");
		f.add(lSearch);
		lSearch.setBounds(890, 30, 250, 50);
		lSearch.setFont(new Font("Serif", Font.CENTER_BASELINE,20));
		
		tfSearch = new JTextField(30);
		f.add(tfSearch);
		tfSearch.setBounds(880, 80, 120, 25);
		
		
		
		btnSearch = new JButton("Search");
		f.add(btnSearch);
		btnSearch.setBounds(950, 150, 100, 30);


		
//		lSort= new JLabel("Sort In");
//		f.add(lSort);
//		lSort.setBounds(1030, 30, 250, 50);
//		lSort.setFont(new Font("Serif", Font.CENTER_BASELINE,20));
//		 
//		String s2[]= {"Ascending","Descending"};
//		tfSort= new JComboBox<>(s2);
//		f.add(tfSort);
//		tfSort.setBounds(1020, 80, 120, 25);
//		tfSort1= new JTextField(50);
//		f.add(tfSort1);
//		tfSort1.setBounds(1020, 120, 120, 25);

		
//		btnSort = new JButton("Sort");
//		f.add(btnSort);
//		btnSort.setBounds(1030, 200, 100, 25);
//		btnSort.setFont(new Font("Serif", Font.CENTER_BASELINE,20));
//		

		f.setSize(1200, 800);
		f.setLayout(null);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

}
