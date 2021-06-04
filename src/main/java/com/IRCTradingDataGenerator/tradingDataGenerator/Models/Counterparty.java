package com.IRCTradingDataGenerator.tradingDataGenerator.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Counterparty {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int counterparty_id;

	@Column
	private String reporting_counterparty;
	
	@Column 
	private String reporting_timestamp;
	
	@Column
	private String other_counterparty;
	
	@Column
	private String entity_responsible_for_report;
	
	@Column
	private String report_SubmittingEntity;

	@Column
	private String branch_reporting_counterparty;
	
	@Column
	private String branch_other_counterparty;
	
	@Column
	private String other_counterparty_country;
	
	@Column
	private String nature_of_the_reporting_counterparty;
	
	@Column
	private String additional_sector_classification;

	@Column
	private String sector_of_reporting_counterparty;
	
	@Column
	private String counterparty_side;
	

	public Counterparty() {
		super();
	}


	public Counterparty(String reporting_counterparty, String reporting_timestamp, String other_counterparty,
			String entity_responsible_for_report, String report_SubmittingEntity, String branch_reporting_counterparty,
			String branch_other_counterparty, String other_counterparty_country, String nature_of_the_reporting_counterparty,
			String additional_sector_classification, String sector_of_reporting_counterparty,
			String counterparty_side) {
		super();
		this.reporting_counterparty = reporting_counterparty;
		this.reporting_timestamp = reporting_timestamp;
		this.other_counterparty = other_counterparty;
		this.entity_responsible_for_report = entity_responsible_for_report;
		this.report_SubmittingEntity = report_SubmittingEntity;
		this.branch_reporting_counterparty = branch_reporting_counterparty;
		this.branch_other_counterparty = branch_other_counterparty;
		this.other_counterparty_country = other_counterparty_country;
		this.nature_of_the_reporting_counterparty = nature_of_the_reporting_counterparty;
		this.additional_sector_classification = additional_sector_classification;
		this.sector_of_reporting_counterparty = sector_of_reporting_counterparty;
		this.counterparty_side = counterparty_side;
	}


	public int getCounterparty_id() {
		return counterparty_id;
	}


	public void setCounterparty_id(int counterparty_id) {
		this.counterparty_id = counterparty_id;
	}


	public String getReporting_counterparty() {
		return reporting_counterparty;
	}


	public void setReporting_counterparty(String reporting_counterparty) {
		this.reporting_counterparty = reporting_counterparty;
	}


	public String getReporting_timestamp() {
		return reporting_timestamp;
	}


	public void setReporting_timestamp(String reporting_timestamp) {
		this.reporting_timestamp = reporting_timestamp;
	}


	public String getOther_counterparty() {
		return other_counterparty;
	}


	public void setOther_counterparty(String other_counterparty) {
		this.other_counterparty = other_counterparty;
	}


	public String getEntity_responsible_for_report() {
		return entity_responsible_for_report;
	}


	public void setEntity_responsible_for_report(String entity_responsible_for_report) {
		this.entity_responsible_for_report = entity_responsible_for_report;
	}


	public String getReport_SubmittingEntity() {
		return report_SubmittingEntity;
	}


	public void setReport_SubmittingEntity(String report_SubmittingEntity) {
		this.report_SubmittingEntity = report_SubmittingEntity;
	}


	public String getBranch_reporting_counterparty() {
		return branch_reporting_counterparty;
	}


	public void setBranch_reporting_counterparty(String branch_reporting_counterparty) {
		this.branch_reporting_counterparty = branch_reporting_counterparty;
	}


	public String getBranch_other_counterparty() {
		return branch_other_counterparty;
	}


	public void setBranch_other_counterparty(String branch_other_counterparty) {
		this.branch_other_counterparty = branch_other_counterparty;
	}


	public String getOther_counterparty_country() {
		return other_counterparty_country;
	}


	public void setOther_counterparty_country(String other_counterparty_country) {
		this.other_counterparty_country = other_counterparty_country;
	}


	public String getCounterparty_nature() {
		return nature_of_the_reporting_counterparty;
	}


	public void setCounterparty_nature(String nature_of_the_reporting_counterparty) {
		this.nature_of_the_reporting_counterparty = nature_of_the_reporting_counterparty;
	}


	public String getAdditional_sector_classification() {
		return additional_sector_classification;
	}


	public void setAdditional_sector_classification(String additional_sector_classification) {
		this.additional_sector_classification = additional_sector_classification;
	}


	public String getSector_of_reporting_counterparty() {
		return sector_of_reporting_counterparty;
	}


	public void setSector_of_reporting_counterparty(String sector_of_reporting_counterparty) {
		this.sector_of_reporting_counterparty = sector_of_reporting_counterparty;
	}


	public String getCounterparty_side() {
		return counterparty_side;
	}


	public void setCounterparty_side(String counterparty_side) {
		this.counterparty_side = counterparty_side;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((additional_sector_classification == null) ? 0 : additional_sector_classification.hashCode());
		result = prime * result + ((branch_other_counterparty == null) ? 0 : branch_other_counterparty.hashCode());
		result = prime * result
				+ ((branch_reporting_counterparty == null) ? 0 : branch_reporting_counterparty.hashCode());
		result = prime * result + counterparty_id;
		result = prime * result + ((nature_of_the_reporting_counterparty == null) ? 0 : nature_of_the_reporting_counterparty.hashCode());
		result = prime * result + ((counterparty_side == null) ? 0 : counterparty_side.hashCode());
		result = prime * result
				+ ((entity_responsible_for_report == null) ? 0 : entity_responsible_for_report.hashCode());
		result = prime * result + ((other_counterparty == null) ? 0 : other_counterparty.hashCode());
		result = prime * result + ((other_counterparty_country == null) ? 0 : other_counterparty_country.hashCode());
		result = prime * result + ((report_SubmittingEntity == null) ? 0 : report_SubmittingEntity.hashCode());
		result = prime * result + ((reporting_counterparty == null) ? 0 : reporting_counterparty.hashCode());
		result = prime * result + ((reporting_timestamp == null) ? 0 : reporting_timestamp.hashCode());
		result = prime * result
				+ ((sector_of_reporting_counterparty == null) ? 0 : sector_of_reporting_counterparty.hashCode());
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
		Counterparty other = (Counterparty) obj;
		if (additional_sector_classification == null) {
			if (other.additional_sector_classification != null)
				return false;
		} else if (!additional_sector_classification.equals(other.additional_sector_classification))
			return false;
		if (branch_other_counterparty == null) {
			if (other.branch_other_counterparty != null)
				return false;
		} else if (!branch_other_counterparty.equals(other.branch_other_counterparty))
			return false;
		if (branch_reporting_counterparty == null) {
			if (other.branch_reporting_counterparty != null)
				return false;
		} else if (!branch_reporting_counterparty.equals(other.branch_reporting_counterparty))
			return false;
		if (counterparty_id != other.counterparty_id)
			return false;
		if (nature_of_the_reporting_counterparty == null) {
			if (other.nature_of_the_reporting_counterparty != null)
				return false;
		} else if (!nature_of_the_reporting_counterparty.equals(other.nature_of_the_reporting_counterparty))
			return false;
		if (counterparty_side == null) {
			if (other.counterparty_side != null)
				return false;
		} else if (!counterparty_side.equals(other.counterparty_side))
			return false;
		if (entity_responsible_for_report == null) {
			if (other.entity_responsible_for_report != null)
				return false;
		} else if (!entity_responsible_for_report.equals(other.entity_responsible_for_report))
			return false;
		if (other_counterparty == null) {
			if (other.other_counterparty != null)
				return false;
		} else if (!other_counterparty.equals(other.other_counterparty))
			return false;
		if (other_counterparty_country == null) {
			if (other.other_counterparty_country != null)
				return false;
		} else if (!other_counterparty_country.equals(other.other_counterparty_country))
			return false;
		if (report_SubmittingEntity == null) {
			if (other.report_SubmittingEntity != null)
				return false;
		} else if (!report_SubmittingEntity.equals(other.report_SubmittingEntity))
			return false;
		if (reporting_counterparty == null) {
			if (other.reporting_counterparty != null)
				return false;
		} else if (!reporting_counterparty.equals(other.reporting_counterparty))
			return false;
		if (reporting_timestamp == null) {
			if (other.reporting_timestamp != null)
				return false;
		} else if (!reporting_timestamp.equals(other.reporting_timestamp))
			return false;
		if (sector_of_reporting_counterparty == null) {
			if (other.sector_of_reporting_counterparty != null)
				return false;
		} else if (!sector_of_reporting_counterparty.equals(other.sector_of_reporting_counterparty))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Counterparty [counterparty_id=" + counterparty_id + ", reporting_counterparty=" + reporting_counterparty
				+ ", reporting_timestamp=" + reporting_timestamp + ", other_counterparty=" + other_counterparty
				+ ", entity_responsible_for_report=" + entity_responsible_for_report + ", report_SubmittingEntity="
				+ report_SubmittingEntity + ", branch_reporting_counterparty=" + branch_reporting_counterparty
				+ ", branch_other_counterparty=" + branch_other_counterparty + ", other_counterparty_country="
				+ other_counterparty_country + ", nature_of_the_reporting_counterparty=" + nature_of_the_reporting_counterparty
				+ ", additional_sector_classification=" + additional_sector_classification
				+ ", sector_of_reporting_counterparty=" + sector_of_reporting_counterparty + ", counterparty_side="
				+ counterparty_side + "]";
	}

}
