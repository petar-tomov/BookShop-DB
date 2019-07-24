import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;


public class MyFrame extends JFrame{
	
	//SQL Tables
	private JTable table_Book = new JTable();
	private JTable table_Author = new JTable();
	private JTable table_BookAuthor = new JTable();
	private JTable table_Genre = new JTable();
	
	//Searches 
	private JTable table_Book_Search = new JTable();
	private JTable table_Author_Search = new JTable();
	private JTable table_Genre_Search = new JTable();
	private JTable table_ComplexSearch1 = new JTable();
	private JTable table_ComplexSearch2 = new JTable();
	
	//Table Ids
	private int book_Id = 0;
	private int author_Id = 0;
	private int bookAuthor_Id = 0;
	private int genre_Id = 0;
	
	private Connection connection = null;
	private ResultSet resultSet = null;
	private PreparedStatement statement = null;
	
	//Combo Boxes
	private JComboBox jComboBox_BookIds = new JComboBox();
	private JComboBox jComboBox_AuthorIds = new JComboBox();
	private JComboBox jComboBox_GenreIds = new JComboBox();
	
	//Book Text Fields
	private JTextField book_ISBN = new JTextField();
	private JTextField book_Title = new JTextField();
	private JTextField book_Year = new JTextField();
	private JTextField book_Price = new JTextField();
	private JTextField book_GenreId = new JTextField();
	
	//Author Text Fields
	private JTextField author_FirstName = new JTextField();
	private JTextField author_LastName = new JTextField();
	
	//Genre Text Field
	private JTextField genre_Name = new JTextField();
	
	//Book_Author Text Field

	
	//SearchText Fields 
	private JTextField book_Search = new JTextField();
	private JTextField author_Search = new JTextField();
	private JTextField genre_Search = new JTextField();
	
	private JTextField complexSearch1_Price = new JTextField();
	private JTextField complexSearch1_Genre = new JTextField();
	
	private JTextField complexSearch2_Author = new JTextField();
	private JTextField complexSearch2_Genre = new JTextField();
	
	//Constructor
	MyFrame(){
		this.setMinimumSize(new Dimension(920, 700));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    this.setResizable(false);
	    this.setLocationRelativeTo(null);
	    this.setTitle("Library");
	    
	    //JTabbedPane
	    JTabbedPane tab = new JTabbedPane();
	    JPanel book = new JPanel();
	    tab.add("Book", book);
	    JPanel author = new JPanel();
	    tab.add("Author", author);
	    JPanel bookAuthor = new JPanel();
	    tab.add("Book_Author", bookAuthor);
	    JPanel genre = new JPanel();
	    tab.add("Genre", genre);
	    
	    //Search Panel
	    JPanel search_Panel = new JPanel();
	    tab.add("Search", search_Panel);
	    JPanel complexSearch1_Panel = new JPanel();
	    tab.add("Complex Search 1", complexSearch1_Panel);
	    JPanel complexSearch2_Panel = new JPanel();
	    tab.add("Complex Search 2", complexSearch2_Panel);
	    
	    //Panels
	    //Book Panels
	    JPanel topPanel_Book = new JPanel();
	    this.add(topPanel_Book);
	    JPanel midPanel_Book = new JPanel();
	    this.add(midPanel_Book);
	    JPanel downPanel_Book = new JPanel();
	    this.add(downPanel_Book);
	    
	    //Author Panels
	    JPanel topPanel_Author = new JPanel();
	    this.add(topPanel_Author);
	    JPanel midPanel_Author = new JPanel();
	    this.add(midPanel_Author);
	    JPanel downPanel_Author = new JPanel();
	    this.add(downPanel_Author);
	    
	    //Genre Panels
	    JPanel topPanel_Genre = new JPanel();
	    this.add(topPanel_Genre);
	    JPanel midPanel_Genre = new JPanel();
	    this.add(midPanel_Genre);
	    JPanel downPanel_Genre = new JPanel();
	    this.add(downPanel_Genre);
	    
	    //BookAuthor Panels
	    JPanel topPanel_BookAuthor = new JPanel();
	    this.add(topPanel_BookAuthor);
	    JPanel midPanel_BookAuthor = new JPanel();
	    this.add(midPanel_BookAuthor);
	    JPanel downPanel_BookAuthor = new JPanel();
	    this.add(downPanel_BookAuthor);
	    
	    //Search Book Panel
	    JPanel book_Search_Panel = new JPanel();
	    this.add(book_Search_Panel);
	    
	    //Author Search Panel
	    JPanel author_Search_Panel = new JPanel();
	    this.add(author_Search_Panel);
	    
	    //Genre Search Panel
	    JPanel genre_Search_Panel = new JPanel();
	    this.add(genre_Search_Panel);
	    
	    //First complex search Panels
	    JPanel topPanel_complexSearch1 = new JPanel();
	    this.add(topPanel_complexSearch1);
	    JPanel midPanel_complexSearch1 = new JPanel();
	    this.add(midPanel_complexSearch1);
	    JPanel downPanel_complexSearch1 = new JPanel();
	    this.add(downPanel_complexSearch1);
	    
	    //Second complex search Panels
	    JPanel topPanel_complexSearch2 = new JPanel();
	    this.add(topPanel_complexSearch2);
	    JPanel midPanel_complexSearch2 = new JPanel();
	    this.add(midPanel_complexSearch2);
	    JPanel downPanel_complexSearch2 = new JPanel();
	    this.add(downPanel_complexSearch2);	  
	    
	    this.add(tab);
	    
	    //Books page
	    book.setLayout(new GridLayout(3, 1));
	    book.add(topPanel_Book);
	    book.add(midPanel_Book);
	    book.add(downPanel_Book);
	    
	    //Authors page
	    author.setLayout(new GridLayout(3, 1));
	    author.add(topPanel_Author);
	    author.add(midPanel_Author);
	    author.add(downPanel_Author);
	    
	    //Genres page
	    genre.setLayout(new GridLayout(3, 1));
	    genre.add(topPanel_Genre);
	    genre.add(midPanel_Genre);
	    genre.add(downPanel_Genre);
	    
	    //BookAuthors page
	    bookAuthor.setLayout(new GridLayout(3, 1));
	    bookAuthor.add(topPanel_BookAuthor);
	    bookAuthor.add(midPanel_BookAuthor);
	    bookAuthor.add(downPanel_BookAuthor);
	    
	    //Search page
	    search_Panel.setLayout(new GridLayout(3, 1));
	    search_Panel.add(book_Search_Panel);
	    search_Panel.add(author_Search_Panel);
	    search_Panel.add(genre_Search_Panel);
	    
	    //Complex Search 1 page
	    complexSearch1_Panel.setLayout(new GridLayout(3, 1));
	    complexSearch1_Panel.add(topPanel_complexSearch1);
	    complexSearch1_Panel.add(midPanel_complexSearch1);
	    complexSearch1_Panel.add(downPanel_complexSearch1);
	    
	   //Complex Search 2  page
	    complexSearch2_Panel.setLayout(new GridLayout(3, 1));
	    complexSearch2_Panel.add(topPanel_complexSearch2);
	    complexSearch2_Panel.add(midPanel_complexSearch2);
	    complexSearch2_Panel.add(downPanel_complexSearch2);
	    
	    
	    //BOOKS:
	    //Panels with TextFields
	    topPanel_Book.setLayout(new GridLayout(8, 2));
	    JLabel bookTitle_Label = new JLabel("Title:");
        topPanel_Book.add(bookTitle_Label);
        topPanel_Book.add(book_Title);
        JLabel bookISBN_Label = new JLabel("ISBN:");
        topPanel_Book.add(bookISBN_Label);
        topPanel_Book.add(book_ISBN);
        JLabel bookYear_Label = new JLabel("Year:");
        topPanel_Book.add(bookYear_Label);
        topPanel_Book.add(book_Year);
        JLabel bookPrice_Label = new JLabel("Price:");
        topPanel_Book.add(bookPrice_Label);
        topPanel_Book.add(book_Price);
        JLabel bookGenreID_Label = new JLabel("Genre:");
        topPanel_Book.add(bookGenreID_Label);
        topPanel_Book.add(jComboBox_GenreIds);
        
        //Buttons:
        JButton add_Book = new JButton("Add book");
        midPanel_Book.add(add_Book);
        add_Book.addActionListener(new AddActionBook()); //add action listener 
        JButton delete_Book = new JButton("Delete book");
        midPanel_Book.add(delete_Book);
        delete_Book.addActionListener(new DeleteActionBook()); //add action listener
        JButton update_Book = new JButton("Update book");
        midPanel_Book.add(update_Book);
        update_Book.addActionListener(new UpdateActionBook()); //add action listener
        
        //Table Scroller:
        JScrollPane book_scroller = new JScrollPane(table_Book);
        downPanel_Book.add(book_scroller);
        book_scroller.setPreferredSize(new Dimension(780,150));
        fillTableBook(table_Book);       //private void fillTableBook
        table_Book.addMouseListener(new MouseClickBook());  //MouseClickBook class
        
       //AUTHORS:
       //Panel with TextFields
        topPanel_Author.setLayout(new GridLayout(5, 2));
	    JLabel authorFirstName_Label = new JLabel("First Name:");
        topPanel_Author.add(authorFirstName_Label);
        topPanel_Author.add(author_FirstName);
        JLabel authorLastName_Label = new JLabel("Last Name:");
        topPanel_Author.add(authorLastName_Label);
        topPanel_Author.add(author_LastName);
       
        
        //Buttons:
        JButton add_Author = new JButton("Add author");
        midPanel_Author.add(add_Author);
        add_Author.addActionListener(new AddActionAuthor()); //add action listener 
        JButton delete_Author = new JButton("Delete author");
        midPanel_Author.add(delete_Author);
        delete_Author.addActionListener(new DeleteActionAuthor()); //add action listener
        JButton update_Author = new JButton("Update author");
        midPanel_Author.add(update_Author);
        update_Author.addActionListener(new UpdateActionAuthor()); //add action listener
        
        //Table Scroller:
        JScrollPane author_scroller = new JScrollPane(table_Author);
        downPanel_Author.add(author_scroller);
        author_scroller.setPreferredSize(new Dimension(780,150));
        fillTableAuthor(table_Author);       //private void fillTableAuthor
        table_Author.addMouseListener(new MouseClickAuthor());  //MouseClickAuthor class
        
        //GENRES:
        //Panel with TextFields
        topPanel_Genre.setLayout(new GridLayout(3, 2));
	    JLabel genreName_Label = new JLabel("Name:");
        topPanel_Genre.add(genreName_Label);
        topPanel_Genre.add(genre_Name);
              
        //Buttons:
        JButton add_Genre = new JButton("Add genre");
        midPanel_Genre.add(add_Genre);
        add_Genre.addActionListener(new AddActionGenre()); //add action listener 
        JButton delete_Genre = new JButton("Delete genre");
        midPanel_Genre.add(delete_Genre);
        delete_Genre.addActionListener(new DeleteActionGenre()); //add action listener
        JButton update_Genre = new JButton("Update genre");
        midPanel_Genre.add(update_Genre);
        update_Genre.addActionListener(new UpdateActionGenre()); //add action listener
        
        //Table Scroller:
        JScrollPane genre_scroller = new JScrollPane(table_Genre);
        downPanel_Genre.add(genre_scroller);
        genre_scroller.setPreferredSize(new Dimension(780,150));
        fillTableGenre(table_Genre);       //private void fillTableGenre
        table_Genre.addMouseListener(new MouseClickGenre());  //MouseClickGenre class
        
        
	    //BOOKS_AUTHORS
	    //Panels with TextFields 
                
	    topPanel_BookAuthor.setLayout(new GridLayout(4, 2));
	    JLabel bookAuthorTitle_Label = new JLabel("Title:");
        topPanel_BookAuthor.add(bookAuthorTitle_Label);
        topPanel_BookAuthor.add(jComboBox_BookIds);
        JLabel bookAuthor_Label = new JLabel("Author:");
        topPanel_BookAuthor.add(bookAuthor_Label);
        topPanel_BookAuthor.add(jComboBox_AuthorIds);
        
        //Buttons:
        JButton add_BookAuthor = new JButton("Add book-author");
        midPanel_BookAuthor.add(add_BookAuthor);
        add_BookAuthor.addActionListener(new AddActionBookAuthor()); //add action listener 
        JButton delete_BookAuthor = new JButton("Delete book-author");
        midPanel_BookAuthor.add(delete_BookAuthor);
        delete_BookAuthor.addActionListener(new DeleteActionBookAuthor()); //add action listener
        JButton update_BookAuthor = new JButton("Update book-author");
        midPanel_BookAuthor.add(update_BookAuthor);
        update_BookAuthor.addActionListener(new UpdateActionBookAuthor()); //add action listener
        
        //Table Scroller:
        JScrollPane bookAuthor_scroller = new JScrollPane(table_BookAuthor);
        downPanel_BookAuthor.add(bookAuthor_scroller);
        bookAuthor_scroller.setPreferredSize(new Dimension(780,150));
        fillTableBookAuthor(table_BookAuthor);       //private void fillTableBook
        table_BookAuthor.addMouseListener(new MouseClickBookAuthor());  //MouseClickBook class
                
        
        //SEARCHES
        
        //Books
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,3));
        JLabel bookDescription = new JLabel("Search in table Book by title:  ");
        panel.add(bookDescription);
        panel.add(book_Search);
        JButton bookSearch_Btn = new JButton("Search");
        panel.add(bookSearch_Btn);
        bookSearch_Btn.addActionListener(new ShowActionBookSearch()); //ShowActionBookSearch class
        book_Search_Panel.add(panel);

        JScrollPane bookSearch_Scroller = new JScrollPane(table_Book_Search);
        book_Search_Panel.add(bookSearch_Scroller);
        bookSearch_Scroller.setPreferredSize(new Dimension(780,120));
        
        //Authors
        panel = new JPanel();
        panel.setLayout(new GridLayout(1,3));
        JLabel authorDescription = new JLabel("Search in table Author by last name: ");
        panel.add(authorDescription);
        panel.add(author_Search);
        JButton authorSearch_Btn = new JButton("Search");
        panel.add(authorSearch_Btn);
        authorSearch_Btn.addActionListener(new ShowActionAuthorSearch()); //ShowActionAuthorSearch class
        author_Search_Panel.add(panel);

        JScrollPane authorSearch_Scroller = new JScrollPane(table_Author_Search);
        author_Search_Panel.add(authorSearch_Scroller);
        authorSearch_Scroller.setPreferredSize(new Dimension(780,120));
        
       //Genres
        panel = new JPanel();
        panel.setLayout(new GridLayout(1,3));
        JLabel genreDescription = new JLabel("Search in table Genre by name: ");
        panel.add(genreDescription);
        panel.add(genre_Search);
        JButton genreSearch_Btn = new JButton("Search");
        panel.add(genreSearch_Btn);
        genreSearch_Btn.addActionListener(new ShowActionGenreSearch()); //ShowActionGenreSearch class
        genre_Search_Panel.add(panel);

        JScrollPane genreSearch_Scroller = new JScrollPane(table_Genre_Search);
        genre_Search_Panel.add(genreSearch_Scroller);
        genreSearch_Scroller.setPreferredSize(new Dimension(780,120));
        
        //ComplexSearch 1: Price & Genre
        topPanel_complexSearch1.setLayout(new GridLayout(3,2));
        JLabel complexSearch_Price = new JLabel("Enter price: ");
        topPanel_complexSearch1.add(complexSearch_Price);
        topPanel_complexSearch1.add(complexSearch1_Price);
        JLabel complexSearch_Genre = new JLabel("Enter genre: ");
        topPanel_complexSearch1.add(complexSearch_Genre);
        topPanel_complexSearch1.add(complexSearch1_Genre);

        JButton complexSearch1_Btn = new JButton("Search");
        midPanel_complexSearch1.add(complexSearch1_Btn);
        complexSearch1_Btn.addActionListener(new ShowActionComplexSearch1()); //ShowActionComplexSearch1 class

        JScrollPane complexSearch1_Scroller = new JScrollPane(table_ComplexSearch1);
        downPanel_complexSearch1.add(complexSearch1_Scroller);
        complexSearch1_Scroller.setPreferredSize(new Dimension(780,150));
        
        DBConnector.fillComboAuthor(jComboBox_AuthorIds);
        DBConnector.fillComboGenre(jComboBox_GenreIds);
        
        this.setVisible(true);
        
        //ComplexSearch 2: Author & Genre
        topPanel_complexSearch2.setLayout(new GridLayout(3,2));
        JLabel complexSearch_Author = new JLabel("Enter author`s last name: ");
        topPanel_complexSearch2.add(complexSearch_Author);
        topPanel_complexSearch2.add(complexSearch2_Author);
        JLabel complexSearch2_GenreLBL = new JLabel("Enter genre: ");
        topPanel_complexSearch2.add(complexSearch2_GenreLBL);
        topPanel_complexSearch2.add(complexSearch2_Genre);

        JButton complexSearch2_Btn = new JButton("Search");
        midPanel_complexSearch2.add(complexSearch2_Btn);
        complexSearch2_Btn.addActionListener(new ShowActionComplexSearch2()); //ShowActionComplexSearch2 class

        JScrollPane complexSearch2_Scroller = new JScrollPane(table_ComplexSearch2);
        downPanel_complexSearch2.add(complexSearch2_Scroller);
        complexSearch2_Scroller.setPreferredSize(new Dimension(780,150));
        
        DBConnector.fillComboAuthor(jComboBox_AuthorIds);
        DBConnector.fillComboBook(jComboBox_BookIds);
        
        this.setVisible(true);

        
	}
	
	//Mouse Click Book event
	public class MouseClickBook implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			String ISBN, title, year, price, genre;
			int row = table_Book.getSelectedRow();
			book_Id = Integer.parseInt(table_Book.getValueAt(row, 0).toString());
			ISBN = table_Book.getValueAt(row, 5).toString();
			title = table_Book.getValueAt(row, 1).toString();
			year = table_Book.getValueAt(row, 2).toString();
			price = table_Book.getValueAt(row, 3).toString();
			genre = table_Book.getValueAt(row, 4).toString();
			if (arg0.getClickCount()== 2) {
                book_ISBN.setText(ISBN);
                book_Title.setText(title);
                book_Year.setText(year);
                book_Price.setText(price);
                book_GenreId.setText(genre);
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

	}
	
	private ResultSet getAllDataFromTable(String tableName) {
        connection = DBConnector.getConnection();
        String sql = "SELECT * FROM " + tableName;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultSet;
    }
	
	private void fillTableBook(JTable tableMakes) {
        try {
            tableMakes.setModel(new MyModel(getAllDataFromTable("book")));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	// DELETE BOOK
	class DeleteActionBook implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent arg0) {
            connection = DBConnector.getConnection();
            String sql = "DELETE FROM BOOK WHERE Id=?;";
            try {
                statement = connection.prepareStatement(sql);
                statement.setInt(1, book_Id);
                statement.execute();
                fillTableBook(table_Book);
                book_Id = 0;

                DBConnector.fillComboBook(jComboBox_BookIds);
                DBConnector.fillComboAuthor(jComboBox_AuthorIds);
                DBConnector.fillComboGenre(jComboBox_GenreIds);
            } catch (SQLException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }
	
	private void resetFormBook() {
        book_ISBN.setText("");
        book_Title.setText("");
        book_Year.setText("");
        book_Price.setText("");
        book_GenreId.setText("");
    }
	
	// ADD BOOK
    class AddActionBook implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String title = book_Title.getText().trim();
            String year = book_Year.getText().trim();
            String price = book_Price.getText().trim();
            String ISBN = book_ISBN.getText().trim();
            String genre = jComboBox_GenreIds.getSelectedItem().toString();

            connection = DBConnector.getConnection();
            String sql = "INSERT INTO Book (Id, Title, Year, Price, Genre, ISBN) VALUES (null,?,?,?,?,?);";
            
            try {
                statement = connection.prepareStatement(sql);
                statement.setString(1, title);
                statement.setInt(2, Integer.parseInt(year));
                statement.setInt(3, Integer.parseInt(price));
                statement.setString(4, genre);
                statement.setString(5, ISBN);

                statement.execute();
                fillTableBook(table_Book);
                resetFormBook();

                DBConnector.fillComboBook(jComboBox_BookIds);
                DBConnector.fillComboAuthor(jComboBox_AuthorIds);
                DBConnector.fillComboGenre(jComboBox_GenreIds);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
    
 // UPDATE BOOK
    class UpdateActionBook implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        	String title = book_Title.getText().trim();
            String year = book_Year.getText().trim();
            String price = book_Price.getText().trim();
            String ISBN = book_ISBN.getText().trim();
            String genre = String.valueOf(jComboBox_GenreIds.getSelectedItem());

            connection = DBConnector.getConnection();
            if(connection == null) {
                return;
            }
            String sql = "UPDATE Book SET Title=?, ISBN=?, Year=?, Price=?, Genre= ? "
            		  +   "WHERE Id=? ";
            try {
                statement = connection.prepareStatement(sql);
                statement.setString(1, title);
                statement.setString(2, ISBN);
                statement.setInt(3, Integer.parseInt(year));
                statement.setInt(4, Integer.parseInt(price));
                statement.setString(5, genre);
                statement.setInt(6, book_Id);
                statement.execute();

                fillTableBook(table_Book);
                resetFormBook();
                DBConnector.fillComboBook(jComboBox_BookIds);
                DBConnector.fillComboAuthor(jComboBox_AuthorIds);
                DBConnector.fillComboGenre(jComboBox_GenreIds);
            }catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
    
    
    //Mouse Click Book Author Event
    class MouseClickBookAuthor implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			int row = table_BookAuthor.getSelectedRow();
            bookAuthor_Id = Integer.parseInt(table_BookAuthor.getValueAt(row, 0).toString());
            if(arg0.getClickCount() == 2) {
            }
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}  	
    }
    
    
    //Add Book Author
    class AddActionBookAuthor implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent arg0) {

            connection = DBConnector.getConnection();

            if(connection == null) {
                return ;
            }
            String sql = "INSERT INTO Book_Author(Id, Book, Author) VALUES (null,?,?);";

            try {
                String bookIdsSelectedItem = (String) jComboBox_BookIds.getSelectedItem();
                String bookId = bookIdsSelectedItem.split("-")[0].trim();

                String authorIdsSelectedItem = (String) jComboBox_AuthorIds.getSelectedItem();
                String authorId = authorIdsSelectedItem.split("-")[0].trim();

                if (authorId.equals("Id") || bookId.equals("Id")){
                    return;
                }

                statement = connection.prepareStatement(sql);
                statement.setString(1, bookId);
                statement.setString(2, authorId);
                statement.execute();
                fillTableBookAuthor(table_BookAuthor);
                DBConnector.fillComboBook(jComboBox_BookIds);
                DBConnector.fillComboAuthor(jComboBox_AuthorIds);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
    
    //Update Book Author
    class UpdateActionBookAuthor implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            connection = DBConnector.getConnection();
            if(connection == null) {
                return;
            }
            String sql = "UPDATE Book_Author Set Book=?, Author=? WHERE Id=?";
            try {
                String bookIdsSelectedItem = (String) jComboBox_BookIds.getSelectedItem();
                String bookId = bookIdsSelectedItem.split("-")[0].trim();

                String authorIdsSelectedItem = (String) jComboBox_AuthorIds.getSelectedItem();
                String authorId = authorIdsSelectedItem.split("-")[0].trim();

                if (bookId.equals("Id") || authorId.equals("Id")){
                    return;
                }

                statement = connection.prepareStatement(sql);
                statement.setString(1, bookId);
                statement.setString(2, authorId);
                statement.setInt(3, bookAuthor_Id);
                statement.execute();

                fillTableBookAuthor(table_BookAuthor);
                DBConnector.fillComboBook(jComboBox_BookIds);
                DBConnector.fillComboAuthor(jComboBox_AuthorIds);
            }catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
    
    
	// DELETE BOOK AUTHOR
	class DeleteActionBookAuthor implements ActionListener{
		
        @Override
        public void actionPerformed(ActionEvent arg0) {
            connection = DBConnector.getConnection();
            String sql = "DELETE FROM BOOK_AUTHOR WHERE Id=?; ";
            try {
                statement = connection.prepareStatement(sql);
                statement.setInt(1, bookAuthor_Id);
                statement.execute();
                
                
                bookAuthor_Id = 0;
                fillTableBookAuthor(table_BookAuthor);

                DBConnector.fillComboBook(jComboBox_BookIds);
                DBConnector.fillComboAuthor(jComboBox_AuthorIds);
                DBConnector.fillComboGenre(jComboBox_GenreIds);
            } catch (SQLException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }
    
    
    private void fillTableBookAuthor(JTable table) {
        try {
            table.setModel(new MyModel(getAllFromTableBookAuthor()));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private ResultSet getAllFromTableBookAuthor() {
        connection = DBConnector.getConnection();

        String sql = "SELECT BA.Id, B.Title, B.Price, B.Year, B.ISBN, A.First_Name, A.Last_Name " +
                "FROM Book_Author AS BA " +
                "JOIN Book AS B ON B.TITLE=BA.BOOK " +
                "JOIN Author AS A ON A.LAST_NAME=BA.AUTHOR";

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultSet;
    }
    
    //Mouse Click Author Event
    class MouseClickAuthor implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			String firstName, lastName;
            int row = table_Author.getSelectedRow();
            author_Id = Integer.parseInt(table_Author.getValueAt(row, 0).toString());
            firstName = table_Author.getValueAt(row, 1).toString();
            lastName = table_Author.getValueAt(row, 2).toString();
            if(e.getClickCount() == 2) {
                author_FirstName.setText(firstName);
                author_FirstName.setText(lastName);
            }
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    //ADD AUTHOR
    class AddActionAuthor implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String first_Name = author_FirstName.getText().trim();
			String last_Name = author_LastName.getText().trim();
			
			connection = DBConnector.getConnection();
            if(connection == null) {
                return ;
            }
            String sql = "INSERT INTO Author VALUES (null,?,?);";

            try {
                statement = connection.prepareStatement(sql);
                statement.setString(1, first_Name);
                statement.setString(2, last_Name);
                statement.execute();
                fillTableAuthor(table_Author);
                resetFormAuthor();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
		}  	
    }
    
    private void resetFormAuthor() {
        author_FirstName.setText("");
        author_LastName.setText("");
    }

    private void fillTableAuthor(JTable table) {
        try {
            table.setModel(new MyModel(getAllDataFromTable("author")));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    //DELETE AUTHOR
    class DeleteActionAuthor implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent arg0) {
            connection = DBConnector.getConnection();
            String sql = "DELETE FROM Author WHERE Id=?;";
            try {
                statement = connection.prepareStatement(sql);
                statement.setInt(1, author_Id);
                statement.execute();
                fillTableAuthor(table_Author);
                author_Id = 0;
            }catch (SQLException e1) {
                // TODO: handle exception
                e1.printStackTrace();
            }
        }
    }
    
    //UPDATE AUTHOR
    class UpdateActionAuthor implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String first_Name = author_FirstName.getText().trim();
			String last_Name = author_LastName.getText().trim();
			
			connection = DBConnector.getConnection();
            if(connection == null) {
                return;
            }
            String sql = "UPDATE Author SET First_Name=?, Last_Name=? WHERE Id=?";

            try {
                statement = connection.prepareStatement(sql);
                statement.setString(1, first_Name);
                statement.setString(2, last_Name);
                statement.setInt(3, author_Id);

                statement.execute();

                fillTableAuthor(table_Author);
                resetFormAuthor();
            }catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }			
		} 	
    }
    
  //Mouse Click Genre Event
    class MouseClickGenre implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			String name;
            int row = table_Genre.getSelectedRow();
            genre_Id = Integer.parseInt(table_Genre.getValueAt(row, 0).toString());
            name = table_Genre.getValueAt(row, 1).toString();
            if(e.getClickCount() == 2) {
                genre_Name.setText(name);
            }
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    //ADD GENRE
    class AddActionGenre implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = genre_Name.getText().trim();
			
			connection = DBConnector.getConnection();
            if(connection == null) {
                return ;
            }
            String sql = "INSERT INTO Genre VALUES (null,?);";

            try {
                statement = connection.prepareStatement(sql);
                statement.setString(1, name);
                statement.execute();
                fillTableGenre(table_Genre);
                resetFormGenre();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
		}  	
    }
    
    private void resetFormGenre() {
        genre_Name.setText("");
    }

    private void fillTableGenre(JTable table) {
        try {
            table.setModel(new MyModel(getAllDataFromTable("genre")));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    //DELETE GENRE
    class DeleteActionGenre implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent arg0) {
            connection = DBConnector.getConnection();
            String sql = "DELETE FROM Genre WHERE Id=?;";
            try {
                statement = connection.prepareStatement(sql);
                statement.setInt(1, genre_Id);
                statement.execute();
                fillTableGenre(table_Genre);
                genre_Id = 0;
            }catch (SQLException e1) {
                // TODO: handle exception
                e1.printStackTrace();
            }
        }
    }
    
    //UPDATE GENRE
    class UpdateActionGenre implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = genre_Name.getText().trim();
			
			connection = DBConnector.getConnection();
            if(connection == null) {
                return;
            }
            String sql = "UPDATE Genre SET Name=? WHERE Id=?";

            try {
                statement = connection.prepareStatement(sql);
                statement.setString(1, name);
                statement.setInt(2, genre_Id);

                statement.execute();

                fillTableGenre(table_Genre);
                resetFormGenre();
            }catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }			
		} 	
    }
    
    //BOOK SEARCH
    class ShowActionBookSearch implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            fillTable_Book_Search(table_Book_Search);
        }
    }
    
    private ResultSet getAllFromTable_Book_Search() {
        String title = book_Search.getText().trim();
        connection = DBConnector.getConnection();
        String sql = "SELECT * FROM Book WHERE Title=?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, title.trim());
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultSet;
    }

    private void fillTable_Book_Search(JTable table) {
        try {
            table.setModel(new MyModel(getAllFromTable_Book_Search()));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    //AUTHOR SEARCH
    class ShowActionAuthorSearch implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            fillTable_Author_Search(table_Author_Search);
        }
    }
    
    private ResultSet getAllFromTable_Author_Search() {
        String last_name = author_Search.getText().trim();
        connection = DBConnector.getConnection();
        String sql = "SELECT * FROM Author WHERE Last_Name=?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, last_name.trim());
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultSet;
    }

    private void fillTable_Author_Search(JTable table) {
        try {
            table.setModel(new MyModel(getAllFromTable_Author_Search()));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    //GENRE SEARCH
    class ShowActionGenreSearch implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            fillTable_Genre_Search(table_Genre_Search);
        }
    }

    private ResultSet getAllFromTable_Genre_Search() {
        String name = genre_Search.getText().trim();
        connection = DBConnector.getConnection();
        String sql = "SELECT * FROM Genre WHERE Name=?";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, name.trim());
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultSet;
    }

    private void fillTable_Genre_Search(JTable table) {
        try {
            table.setModel(new MyModel(getAllFromTable_Genre_Search()));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    //Complex Search 1 - by price and genre
    class ShowActionComplexSearch1 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            fillTableComplexSearch1(table_ComplexSearch1);
        }
    }
    
    private ResultSet getAllFromTableComplexSearch1() {
        String price = complexSearch1_Price.getText().trim();
        String genre = complexSearch1_Genre.getText().trim();

        connection = DBConnector.getConnection();

        String sql = "SELECT B.ISBN, B.Title, B.Year " +
                "FROM BOOK AS B " +
                "JOIN GENRE AS G ON B.GENRE=G.NAME " +
                "WHERE B.Price=? AND G.Name = ? ";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, price.trim());
            statement.setString(2, genre.trim());
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultSet;
    }
    
    private void fillTableComplexSearch1(JTable table) {
        try {
            table.setModel(new MyModel(getAllFromTableComplexSearch1()));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    //Complex Search 2 - by author and genre
    class ShowActionComplexSearch2 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            fillTableComplexSearch2(table_ComplexSearch2);
        }
    }
    
    private ResultSet getAllFromTableComplexSearch2() {
        String author = complexSearch2_Author.getText().trim();
        String genre = complexSearch2_Genre.getText().trim();

        connection = DBConnector.getConnection();

        String sql = "SELECT B.ISBN, B.Title, B.Year, B.Price " +
                "FROM BOOK AS B " +
                "JOIN GENRE AS G ON B.GENRE=G.NAME " +
                "JOIN BOOK_AUTHOR BA ON B.TITLE = BA.BOOK " + 
                "JOIN AUTHOR A ON BA.AUTHOR = A.LAST_NAME " + 
                "WHERE A.Last_Name = ? AND G.Name = ? ";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, author.trim());
            statement.setString(2, genre.trim());
            
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultSet;
    }
    
    private void fillTableComplexSearch2(JTable table) {
        try {
            table.setModel(new MyModel(getAllFromTableComplexSearch2()));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
