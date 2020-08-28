    import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.Table;

public class Book {

	

	@Entity
	@Table(name="books")
	public class Book {
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="bookid")
		private int bookid;
		
		@Column(name="bookname")
		private String bookname;
		
		@Column(name="bookauthor")
		private String bookauthor;
		
		@Column(name="publication")
		private String publication;
		
		public int getBookId() {
			return bookid;
		}

		public void setBookId(int id) {
			this.id = bookid;
		}

		public String getBookName() {
			return bookname;
		}

		public void setBookName(String name) {
			this.name = bookname;
		}

		public String getBookAuthor() {
			return bookauthor;
		}

		public void setBookAuthor(String author) {
			this.author = bookauthor;
		}

		public String getPublication() {
			return publication;
		}

		public void setPublication(String publication) {
			this.publication = publication;
		}


}
