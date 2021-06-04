package com.IRCTradingDataGenerator.tradingDataGenerator.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
public class Transaction {

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int transaction_Id;
		
		@Column
		private String type_Of_Sft;
		
		@Column
		private String event_date;
		
		@Column
		private String execution_timestamp;
		
		@Column
		private String termination_date;

		public Transaction() {
			super();
		}

		public Transaction(String type_Of_Sft, String event_date, String execution_timestamp,
				String termination_date) {
			super();
			this.type_Of_Sft = type_Of_Sft;
			this.event_date = event_date;
			this.execution_timestamp = execution_timestamp;
			this.termination_date = termination_date;
		}

		public int getTransaction_Id() {
			return transaction_Id;
		}

		public void setTransaction_Id(int transaction_Id) {
			this.transaction_Id = transaction_Id;
		}

		public String getType_Of_Sft() {
			return type_Of_Sft;
		}

		public void setType_Of_Sft(String type_Of_Sft) {
			this.type_Of_Sft = type_Of_Sft;
		}

		public String getEvent_date() {
			return event_date;
		}

		public void setEvent_date(String event_date) {
			this.event_date = event_date;
		}

		public String getExecution_timestamp() {
			return execution_timestamp;
		}

		public void setExecution_timestamp(String execution_timestamp) {
			this.execution_timestamp = execution_timestamp;
		}

		public String getTermination_date() {
			return termination_date;
		}

		public void setTermination_date(String termination_date) {
			this.termination_date = termination_date;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((event_date == null) ? 0 : event_date.hashCode());
			result = prime * result + ((execution_timestamp == null) ? 0 : execution_timestamp.hashCode());
			result = prime * result + ((termination_date == null) ? 0 : termination_date.hashCode());
			result = prime * result + transaction_Id;
			result = prime * result + ((type_Of_Sft == null) ? 0 : type_Of_Sft.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Transaction other = (Transaction) obj;
			if (event_date == null) {
				if (other.event_date != null)
					return false;
			} else if (!event_date.equals(other.event_date))
				return false;
			if (execution_timestamp == null) {
				if (other.execution_timestamp != null)
					return false;
			} else if (!execution_timestamp.equals(other.execution_timestamp))
				return false;
			if (termination_date == null) {
				if (other.termination_date != null)
					return false;
			} else if (!termination_date.equals(other.termination_date))
				return false;
			if (transaction_Id != other.transaction_Id)
				return false;
			if (type_Of_Sft == null) {
				if (other.type_Of_Sft != null)
					return false;
			} else if (!type_Of_Sft.equals(other.type_Of_Sft))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Transactions [transaction_Id=" + transaction_Id + ", type_Of_Sft=" + type_Of_Sft + ", event_date="
					+ event_date + ", execution_timestamp=" + execution_timestamp + ", termination_date="
					+ termination_date + "]";
		}

}
