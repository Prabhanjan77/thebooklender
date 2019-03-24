package com.project.thebooklender.bean;

public class Book {
	public Book() {}
		public int id;
        public String book_id;
		public String title;
		public String author;
		public String isbn;
		public String category;
		public int owner_id;
    	public int lent_to;
        
		public Book(int id,String book_id,String title,String author,String isbn,String category,int owner_id,int lent_to)
		{
			super();
			this.id=id;
			this.book_id=book_id;
			this.title=title;
			this.author=author;
			this.isbn= isbn;
			this.category=category;
			this.owner_id=owner_id;
			this.lent_to=lent_to;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getBook_id() {
			return book_id;
		}

		public void setBook_id(String book_id) {
			this.book_id = book_id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getIsbn() {
			return isbn;
		}

		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public int getOwner_id() {
			return owner_id;
		}

		public void setOwner_id(int owner_id) {
			this.owner_id = owner_id;
		}

		public int getLent_to() {
			return lent_to;
		}

		public void setLent_to(int lent_to) {
			this.lent_to = lent_to;
		}

		@Override
		public String toString() {
			return "Book [id=" + id + ", book_id=" + book_id + ", title=" + title + ", author=" + author + ", isbn="
					+ isbn + ", category=" + category + ", owner_id=" + owner_id + ", lent_to=" + lent_to + "]";
		}

}
