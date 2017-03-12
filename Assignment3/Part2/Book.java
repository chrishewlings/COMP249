
public class Book {
	
	private String title;
	private String authorName;
	
	private int issueYear;
	private int numPages;
	
	private long isbn;
	
	private double price;

	// CONSTRUCTORS
	
	/**
	 * @param title
	 * @param authorName
	 * @param issueYear
	 * @param numPages
	 * @param isbn
	 * @param price
	 */
	public Book(String title, String authorName, int issueYear, int numPages, long isbn, double price) {
		this.title = title;
		this.authorName = authorName;
		this.issueYear = issueYear;
		this.numPages = numPages;
		this.isbn = isbn;
		this.price = price;
	}
	
	public Book() {
		this("","",0,0,-1,0.0);
	}
	
	public Book(Book book) {
		this(book.getTitle(), book.getAuthorName(), book.getIssueYear(), book.getNumPages(), book.getIsbn(), book.getPrice() );
	}

	// ACCESSORS
	
	public String getTitle() {
		return title;
	}

	public String getAuthorName() {
		return authorName;
	}

	public int getIssueYear() {
		return issueYear;
	}

	public int getNumPages() {
		return numPages;
	}

	public long getIsbn() {
		return isbn;
	}

	public double getPrice() {
		return price;
	}

	// MUTATORS
	
	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public void setIssueYear(int issueYear) {
		this.issueYear = issueYear;
	}

	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		String returnString = String.format("%d %s %d %s %.2f %d", isbn, title, issueYear, authorName, price, numPages);
		return returnString;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book secondBook = (Book) obj;
		if (authorName == null) {
			if (secondBook.authorName != null)
				return false;
		} else if (!authorName.equals(secondBook.authorName))
			return false;
		if (isbn != secondBook.isbn)
			return false;
		if (issueYear != secondBook.issueYear)
			return false;
		if (numPages != secondBook.numPages)
			return false;
		if (price != secondBook.price)
			return false;
		if (title == null) {
			if (secondBook.title != null)
				return false;
		} else if (!title.equals(secondBook.title))
			return false;
		return true;
	}

	
	
}
