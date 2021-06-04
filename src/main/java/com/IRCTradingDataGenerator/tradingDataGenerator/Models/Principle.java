package com.IRCTradingDataGenerator.tradingDataGenerator.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Principle {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int principle_Id;
	
	@Column
	private String day_count_convention;
	
	@Column
	private String float_ref_period_time;
	
	@Column
	private String float_pay_freq_time;
	
	@Column
	private String float_reset_freq_time;
	
	@Column
	private String margin_currency;
	
	@Column
	private String outstanding_ML_base_currency;
	
	@Column
	private String floating_rate;
	
	// non fk fields
	@Column
	private String fixed_rate;
	
	@Column
	private String outstanding_margin_loan;
	
	@Column
	private String short_market_value;
	
	@Column
	private String margin_currency_amount;
	
	@Column
	private String float_ref_period_multi;
	
	@Column
	private String float_pay_freq_multi;
	
	@Column
	private String float_reset_freq_multi;
	
	@Column
	private String spread;
	
	public Principle() {
		super();
	}

	public Principle(String day_count_convention, String float_ref_period_time, String float_pay_freq_time,
			String float_reset_freq_time, String margin_currency, String outstanding_ML_base_currency,
			String floating_rate, String fixed_rate, String outstanding_margin_loan, String short_market_value,
			String margin_currency_amount, String float_ref_period_multi, String float_pay_freq_multi,
			String float_reset_freq_multi, String spread) {
		super();
		this.day_count_convention = day_count_convention;
		this.float_ref_period_time = float_ref_period_time;
		this.float_pay_freq_time = float_pay_freq_time;
		this.float_reset_freq_time = float_reset_freq_time;
		this.margin_currency = margin_currency;
		this.outstanding_ML_base_currency = outstanding_ML_base_currency;
		this.floating_rate = floating_rate;
		this.fixed_rate = fixed_rate;
		this.outstanding_margin_loan = outstanding_margin_loan;
		this.short_market_value = short_market_value;
		this.margin_currency_amount = margin_currency_amount;
		this.float_ref_period_multi = float_ref_period_multi;
		this.float_pay_freq_multi = float_pay_freq_multi;
		this.float_reset_freq_multi = float_reset_freq_multi;
		this.spread = spread;
	}

	public int getPrinciple_Id() {
		return principle_Id;
	}

	public void setPrinciple_Id(int principle_Id) {
		this.principle_Id = principle_Id;
	}

	public String getDay_count_convention() {
		return day_count_convention;
	}

	public void setDay_count_convention(String day_count_convention) {
		this.day_count_convention = day_count_convention;
	}

	public String getFloat_ref_period_time() {
		return float_ref_period_time;
	}

	public void setFloat_ref_period_time(String float_ref_period_time) {
		this.float_ref_period_time = float_ref_period_time;
	}

	public String getFloat_pay_freq_time() {
		return float_pay_freq_time;
	}

	public void setFloat_pay_freq_time(String float_pay_freq_time) {
		this.float_pay_freq_time = float_pay_freq_time;
	}

	public String getFloat_reset_freq_time() {
		return float_reset_freq_time;
	}

	public void setFloat_reset_freq_time(String float_reset_freq_time) {
		this.float_reset_freq_time = float_reset_freq_time;
	}

	public String getMargin_currency() {
		return margin_currency;
	}

	public void setMargin_currency(String margin_currency) {
		this.margin_currency = margin_currency;
	}

	public String getOutstanding_ML_base_currency() {
		return outstanding_ML_base_currency;
	}

	public void setOutstanding_ML_base_currency(String outstanding_ML_base_currency) {
		this.outstanding_ML_base_currency = outstanding_ML_base_currency;
	}

	public String getFloating_rate() {
		return floating_rate;
	}

	public void setFloating_rate(String floating_rate) {
		this.floating_rate = floating_rate;
	}

	public String getFixed_rate() {
		return fixed_rate;
	}

	public void setFixed_rate(String fixed_rate) {
		this.fixed_rate = fixed_rate;
	}

	public String getOutstanding_margin_loan() {
		return outstanding_margin_loan;
	}

	public void setOutstanding_margin_loan(String outstanding_margin_loan) {
		this.outstanding_margin_loan = outstanding_margin_loan;
	}

	public String getShort_market_value() {
		return short_market_value;
	}

	public void setShort_market_value(String short_market_value) {
		this.short_market_value = short_market_value;
	}

	public String getMargin_currency_amount() {
		return margin_currency_amount;
	}

	public void setMargin_currency_amount(String margin_currency_amount) {
		this.margin_currency_amount = margin_currency_amount;
	}

	public String getFloat_ref_period_multi() {
		return float_ref_period_multi;
	}

	public void setFloat_ref_period_multi(String float_ref_period_multi) {
		this.float_ref_period_multi = float_ref_period_multi;
	}

	public String getFloat_pay_freq_multi() {
		return float_pay_freq_multi;
	}

	public void setFloat_pay_freq_multi(String float_pay_freq_multi) {
		this.float_pay_freq_multi = float_pay_freq_multi;
	}

	public String getFloat_reset_freq_multi() {
		return float_reset_freq_multi;
	}

	public void setFloat_reset_freq_multi(String float_reset_freq_multi) {
		this.float_reset_freq_multi = float_reset_freq_multi;
	}

	public String getSpread() {
		return spread;
	}

	public void setSpread(String spread) {
		this.spread = spread;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day_count_convention == null) ? 0 : day_count_convention.hashCode());
		result = prime * result + ((fixed_rate == null) ? 0 : fixed_rate.hashCode());
		result = prime * result + ((float_pay_freq_multi == null) ? 0 : float_pay_freq_multi.hashCode());
		result = prime * result + ((float_pay_freq_time == null) ? 0 : float_pay_freq_time.hashCode());
		result = prime * result + ((float_ref_period_multi == null) ? 0 : float_ref_period_multi.hashCode());
		result = prime * result + ((float_ref_period_time == null) ? 0 : float_ref_period_time.hashCode());
		result = prime * result + ((float_reset_freq_multi == null) ? 0 : float_reset_freq_multi.hashCode());
		result = prime * result + ((float_reset_freq_time == null) ? 0 : float_reset_freq_time.hashCode());
		result = prime * result + ((floating_rate == null) ? 0 : floating_rate.hashCode());
		result = prime * result + ((margin_currency == null) ? 0 : margin_currency.hashCode());
		result = prime * result + ((margin_currency_amount == null) ? 0 : margin_currency_amount.hashCode());
		result = prime * result
				+ ((outstanding_ML_base_currency == null) ? 0 : outstanding_ML_base_currency.hashCode());
		result = prime * result + ((outstanding_margin_loan == null) ? 0 : outstanding_margin_loan.hashCode());
		result = prime * result + principle_Id;
		result = prime * result + ((short_market_value == null) ? 0 : short_market_value.hashCode());
		result = prime * result + ((spread == null) ? 0 : spread.hashCode());
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
		Principle other = (Principle) obj;
		if (day_count_convention == null) {
			if (other.day_count_convention != null)
				return false;
		} else if (!day_count_convention.equals(other.day_count_convention))
			return false;
		if (fixed_rate == null) {
			if (other.fixed_rate != null)
				return false;
		} else if (!fixed_rate.equals(other.fixed_rate))
			return false;
		if (float_pay_freq_multi == null) {
			if (other.float_pay_freq_multi != null)
				return false;
		} else if (!float_pay_freq_multi.equals(other.float_pay_freq_multi))
			return false;
		if (float_pay_freq_time == null) {
			if (other.float_pay_freq_time != null)
				return false;
		} else if (!float_pay_freq_time.equals(other.float_pay_freq_time))
			return false;
		if (float_ref_period_multi == null) {
			if (other.float_ref_period_multi != null)
				return false;
		} else if (!float_ref_period_multi.equals(other.float_ref_period_multi))
			return false;
		if (float_ref_period_time == null) {
			if (other.float_ref_period_time != null)
				return false;
		} else if (!float_ref_period_time.equals(other.float_ref_period_time))
			return false;
		if (float_reset_freq_multi == null) {
			if (other.float_reset_freq_multi != null)
				return false;
		} else if (!float_reset_freq_multi.equals(other.float_reset_freq_multi))
			return false;
		if (float_reset_freq_time == null) {
			if (other.float_reset_freq_time != null)
				return false;
		} else if (!float_reset_freq_time.equals(other.float_reset_freq_time))
			return false;
		if (floating_rate == null) {
			if (other.floating_rate != null)
				return false;
		} else if (!floating_rate.equals(other.floating_rate))
			return false;
		if (margin_currency == null) {
			if (other.margin_currency != null)
				return false;
		} else if (!margin_currency.equals(other.margin_currency))
			return false;
		if (margin_currency_amount == null) {
			if (other.margin_currency_amount != null)
				return false;
		} else if (!margin_currency_amount.equals(other.margin_currency_amount))
			return false;
		if (outstanding_ML_base_currency == null) {
			if (other.outstanding_ML_base_currency != null)
				return false;
		} else if (!outstanding_ML_base_currency.equals(other.outstanding_ML_base_currency))
			return false;
		if (outstanding_margin_loan == null) {
			if (other.outstanding_margin_loan != null)
				return false;
		} else if (!outstanding_margin_loan.equals(other.outstanding_margin_loan))
			return false;
		if (principle_Id != other.principle_Id)
			return false;
		if (short_market_value == null) {
			if (other.short_market_value != null)
				return false;
		} else if (!short_market_value.equals(other.short_market_value))
			return false;
		if (spread == null) {
			if (other.spread != null)
				return false;
		} else if (!spread.equals(other.spread))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Principle [principle_Id=" + principle_Id + ", day_count_convention=" + day_count_convention
				+ ", float_ref_period_time=" + float_ref_period_time + ", float_pay_freq_time=" + float_pay_freq_time
				+ ", float_reset_freq_time=" + float_reset_freq_time + ", margin_currency=" + margin_currency
				+ ", outstanding_ML_base_currency=" + outstanding_ML_base_currency + ", floating_rate=" + floating_rate
				+ ", fixed_rate=" + fixed_rate + ", outstanding_margin_loan=" + outstanding_margin_loan
				+ ", short_market_value=" + short_market_value + ", margin_currency_amount=" + margin_currency_amount
				+ ", float_ref_period_multi=" + float_ref_period_multi + ", float_pay_freq_multi="
				+ float_pay_freq_multi + ", float_reset_freq_multi=" + float_reset_freq_multi + ", spread=" + spread
				+ "]";
	}
	
}
