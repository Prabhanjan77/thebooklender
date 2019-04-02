package com.project.thebooklender.bean;
import java.util.Date;

public class Transaction {
	public Transaction() {}
	  
	  public int id;
	  public int txn_id;
	  public Date txn_date;
	  public int book_id;
	  public int lender_id;
	  public int borrower_id;
	  
	    public Transaction(int id,int txn_id,Date txn_date,int book_id,int lender_id,int borrower_id)
		{
			super();
			this.id=id;
			this.txn_id=txn_id;
			this.book_id=book_id;
            this.txn_date=txn_date;
            this.lender_id=lender_id;
            this.borrower_id=borrower_id;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getTxn_id() {
			return txn_id;
		}

		public void setTxn_id(int txn_id) {
			this.txn_id = txn_id;
		}

		public Date getTxn_date() {
			return txn_date;
		}

		public void setTxn_date(Date txn_date) {
			this.txn_date = txn_date;
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
			return "Transaction [id=" + id + ", txn_id=" + txn_id + ", txn_date=" + txn_date + ", book_id=" + book_id
					+ ", lender_id=" + lender_id + ", borrower_id=" + borrower_id + "]";
		}
	    
}
