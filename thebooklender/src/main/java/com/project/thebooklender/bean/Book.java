package com.project.thebooklender.bean;

public class Book {
	public Book() {}
		public int id;
        public String book_id;
		public String title;
		public String author;
		public String publisher;
		public String description;
		public String image;
		public String isbn;
		public String category;
		public int owner_id;
        
		public Book(int id,String book_id,String title,String author,String publisher,String description,String image,String isbn,String category,int owner_id)
		{
			super();
			this.id=id;
			this.book_id=book_id;
			this.title=title;
			this.author=author;
			this.publisher=publisher;
			this.description=description;
			this.image=image;
			this.isbn= isbn;
			this.category=category;
			this.owner_id=owner_id;
		}

		
		public int getId() {
			return id;
		}


		public String getDescription() {
			return description;
		}


		public void setDescription(String description) {
			this.description = description;
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

    	public String getPublisher() {
			return publisher;
		}


		public void setPublisher(String publisher) {
			this.publisher = publisher;
		}




		public String getImage() {
			return image;
		}




		public void setImage(String image) {
			this.image = image;
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


		@Override
		public String toString() {
			return "Book [id=" + id + ", book_id=" + book_id + ", title=" + title + ", author=" + author
					+ ", publisher=" + publisher + ", description=" + description + ", image=" + image + ", isbn="
					+ isbn + ", category=" + category + ", owner_id=" + owner_id + "]";
		}
		
}
