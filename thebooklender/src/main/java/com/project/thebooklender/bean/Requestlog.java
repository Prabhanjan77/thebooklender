package com.project.thebooklender.bean;

import java.util.Date;

public class Requestlog {
	public Requestlog() {
	}

	public int id;
	public int book_id;
	public int lender_id;
	public int borrower_id;

	public Requestlog(int id, Date txn_date, int book_id, int lender_id, int borrower_id) {
		super();
		this.id = id;
		this.book_id = book_id;
		this.lender_id = lender_id;
		this.borrower_id = borrower_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public int getLender_id() {
		return lender_id;
	}

	public void setLender_id(int lender_id) {
		this.lender_id = lender_id;
	}

	public int getBorrower_id() {
		return borrower_id;
	}

	public void setBorrower_id(int borrower_id) {
		this.borrower_id = borrower_id;
	}

	@Override
	public String toString() {
		return "Requestlog [id=" + id + ", book_id=" + book_id + ", lender_id=" + lender_id + ", borrower_id="
				+ borrower_id + "]";
	}
}
