package com.IRCTradingDataGenerator.tradingDataGenerator.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Collateral {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int collateral_Id;
	
	@Column
	private String LEI_of_issuer;
	
	@Column
	private String jurisdiction_of_issuer;
	
	@Column
	private String reused_collateral_currency;
	
	@Column
	private String funding_source_currency;
	
	@Column
	private String funding_source;
	
	@Column
	private String price_currency;
	
	@Column
	private String currency_collateral_nom_amount;
	
	@Column
	private String collateral_component;
	
	@Column
	private String collateral_quality;
	
	@Column
	private String collateral_type;
	
	@Column
	private String method_to_provide_collateral;
	
	@Column
	private String collateral_market_value;
	
	@Column
	private String price_per_unit;
	
	@Column
	private String collateral_quantity_or_nom_amount;
	
	@Column
	private String haircut_or_margin;
	
	@Column
	private String Market_value_of_funding_sources;
	
	@Column
	private String Estimated_reuse_of_collateral;
	
	@Column
	private String Value_of_reused_collateral;
	
	@Column
	private String Maturity_date_of_the_security;
	
	@Column
	private String Classification_of_security_used_as_collateral;
	
	@Column
	private String Identification_of_security_as_collateral;
	
	@Column
	private boolean Availability_for_collateral_reuse;

	public Collateral() {
		super();
	}

	public Collateral(String lEI_of_issuer, String jurisdiction_of_issuer, String reused_collateral_currency,
			String funding_source_currency, String funding_source, String price_currency,
			String currency_collateral_nom_amount, String collateral_component, String collateral_quality,
			String collateral_type, String method_to_provide_collateral, String collateral_market_value,
			String price_per_unit, String collateral_quantity_or_nom_amount, String haircut_or_margin,
			String market_value_of_funding_sources, String estimated_reuse_of_collateral,
			String value_of_reused_collateral, String maturity_date_of_the_security,
			String classification_of_security_used_as_collateral, String identification_of_security_as_collateral,
			boolean availability_for_collateral_reuse) {
		super();
		LEI_of_issuer = lEI_of_issuer;
		this.jurisdiction_of_issuer = jurisdiction_of_issuer;
		this.reused_collateral_currency = reused_collateral_currency;
		this.funding_source_currency = funding_source_currency;
		this.funding_source = funding_source;
		this.price_currency = price_currency;
		this.currency_collateral_nom_amount = currency_collateral_nom_amount;
		this.collateral_component = collateral_component;
		this.collateral_quality = collateral_quality;
		this.collateral_type = collateral_type;
		this.method_to_provide_collateral = method_to_provide_collateral;
		this.collateral_market_value = collateral_market_value;
		this.price_per_unit = price_per_unit;
		this.collateral_quantity_or_nom_amount = collateral_quantity_or_nom_amount;
		this.haircut_or_margin = haircut_or_margin;
		Market_value_of_funding_sources = market_value_of_funding_sources;
		Estimated_reuse_of_collateral = estimated_reuse_of_collateral;
		Value_of_reused_collateral = value_of_reused_collateral;
		Maturity_date_of_the_security = maturity_date_of_the_security;
		Classification_of_security_used_as_collateral = classification_of_security_used_as_collateral;
		Identification_of_security_as_collateral = identification_of_security_as_collateral;
		Availability_for_collateral_reuse = availability_for_collateral_reuse;
	}

	public int getCollateral_Id() {
		return collateral_Id;
	}

	public void setCollateral_Id(int collateral_Id) {
		this.collateral_Id = collateral_Id;
	}

	public String getLEI_of_issuer() {
		return LEI_of_issuer;
	}

	public void setLEI_of_issuer(String lEI_of_issuer) {
		LEI_of_issuer = lEI_of_issuer;
	}

	public String getJurisdiction_of_issuer() {
		return jurisdiction_of_issuer;
	}

	public void setJurisdiction_of_issuer(String jurisdiction_of_issuer) {
		this.jurisdiction_of_issuer = jurisdiction_of_issuer;
	}

	public String getReused_collateral_currency() {
		return reused_collateral_currency;
	}

	public void setReused_collateral_currency(String reused_collateral_currency) {
		this.reused_collateral_currency = reused_collateral_currency;
	}

	public String getFunding_source_currency() {
		return funding_source_currency;
	}

	public void setFunding_source_currency(String funding_source_currency) {
		this.funding_source_currency = funding_source_currency;
	}

	public String getFunding_source() {
		return funding_source;
	}

	public void setFunding_source(String funding_source) {
		this.funding_source = funding_source;
	}

	public String getPrice_currency() {
		return price_currency;
	}

	public void setPrice_currency(String price_currency) {
		this.price_currency = price_currency;
	}

	public String getCurrency_collateral_nom_amount() {
		return currency_collateral_nom_amount;
	}

	public void setCurrency_collateral_nom_amount(String currency_collateral_nom_amount) {
		this.currency_collateral_nom_amount = currency_collateral_nom_amount;
	}

	public String getCollateral_component() {
		return collateral_component;
	}

	public void setCollateral_component(String collateral_component) {
		this.collateral_component = collateral_component;
	}

	public String getCollateral_quality() {
		return collateral_quality;
	}

	public void setCollateral_quality(String collateral_quality) {
		this.collateral_quality = collateral_quality;
	}

	public String getCollateral_type() {
		return collateral_type;
	}

	public void setCollateral_type(String collateral_type) {
		this.collateral_type = collateral_type;
	}

	public String getMethod_to_provide_collateral() {
		return method_to_provide_collateral;
	}

	public void setMethod_to_provide_collateral(String method_to_provide_collateral) {
		this.method_to_provide_collateral = method_to_provide_collateral;
	}

	public String getCollateral_market_value() {
		return collateral_market_value;
	}

	public void setCollateral_market_value(String collateral_market_value) {
		this.collateral_market_value = collateral_market_value;
	}

	public String getPrice_per_unit() {
		return price_per_unit;
	}

	public void setPrice_per_unit(String price_per_unit) {
		this.price_per_unit = price_per_unit;
	}

	public String getCollateral_quantity_or_nom_amount() {
		return collateral_quantity_or_nom_amount;
	}

	public void setCollateral_quantity_or_nom_amount(String collateral_quantity_or_nom_amount) {
		this.collateral_quantity_or_nom_amount = collateral_quantity_or_nom_amount;
	}

	public String getHaircut_or_margin() {
		return haircut_or_margin;
	}

	public void setHaircut_or_margin(String haircut_or_margin) {
		this.haircut_or_margin = haircut_or_margin;
	}

	public String getMarket_value_of_funding_sources() {
		return Market_value_of_funding_sources;
	}

	public void setMarket_value_of_funding_sources(String market_value_of_funding_sources) {
		Market_value_of_funding_sources = market_value_of_funding_sources;
	}

	public String getEstimated_reuse_of_collateral() {
		return Estimated_reuse_of_collateral;
	}

	public void setEstimated_reuse_of_collateral(String estimated_reuse_of_collateral) {
		Estimated_reuse_of_collateral = estimated_reuse_of_collateral;
	}

	public String getValue_of_reused_collateral() {
		return Value_of_reused_collateral;
	}

	public void setValue_of_reused_collateral(String value_of_reused_collateral) {
		Value_of_reused_collateral = value_of_reused_collateral;
	}

	public String getMaturity_date_of_the_security() {
		return Maturity_date_of_the_security;
	}

	public void setMaturity_date_of_the_security(String maturity_date_of_the_security) {
		Maturity_date_of_the_security = maturity_date_of_the_security;
	}

	public String getClassification_of_security_used_as_collateral() {
		return Classification_of_security_used_as_collateral;
	}

	public void setClassification_of_security_used_as_collateral(String classification_of_security_used_as_collateral) {
		Classification_of_security_used_as_collateral = classification_of_security_used_as_collateral;
	}

	public String getIdentification_of_security_as_collateral() {
		return Identification_of_security_as_collateral;
	}

	public void setIdentification_of_security_as_collateral(String identification_of_security_as_collateral) {
		Identification_of_security_as_collateral = identification_of_security_as_collateral;
	}

	public boolean isAvailability_for_collateral_reuse() {
		return Availability_for_collateral_reuse;
	}

	public void setAvailability_for_collateral_reuse(boolean availability_for_collateral_reuse) {
		Availability_for_collateral_reuse = availability_for_collateral_reuse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (Availability_for_collateral_reuse ? 1231 : 1237);
		result = prime * result + ((Classification_of_security_used_as_collateral == null) ? 0
				: Classification_of_security_used_as_collateral.hashCode());
		result = prime * result
				+ ((Estimated_reuse_of_collateral == null) ? 0 : Estimated_reuse_of_collateral.hashCode());
		result = prime * result + ((Identification_of_security_as_collateral == null) ? 0
				: Identification_of_security_as_collateral.hashCode());
		result = prime * result + ((LEI_of_issuer == null) ? 0 : LEI_of_issuer.hashCode());
		result = prime * result
				+ ((Market_value_of_funding_sources == null) ? 0 : Market_value_of_funding_sources.hashCode());
		result = prime * result
				+ ((Maturity_date_of_the_security == null) ? 0 : Maturity_date_of_the_security.hashCode());
		result = prime * result + ((Value_of_reused_collateral == null) ? 0 : Value_of_reused_collateral.hashCode());
		result = prime * result + collateral_Id;
		result = prime * result + ((collateral_component == null) ? 0 : collateral_component.hashCode());
		result = prime * result + ((collateral_market_value == null) ? 0 : collateral_market_value.hashCode());
		result = prime * result + ((collateral_quality == null) ? 0 : collateral_quality.hashCode());
		result = prime * result
				+ ((collateral_quantity_or_nom_amount == null) ? 0 : collateral_quantity_or_nom_amount.hashCode());
		result = prime * result + ((collateral_type == null) ? 0 : collateral_type.hashCode());
		result = prime * result
				+ ((currency_collateral_nom_amount == null) ? 0 : currency_collateral_nom_amount.hashCode());
		result = prime * result + ((funding_source == null) ? 0 : funding_source.hashCode());
		result = prime * result + ((funding_source_currency == null) ? 0 : funding_source_currency.hashCode());
		result = prime * result + ((haircut_or_margin == null) ? 0 : haircut_or_margin.hashCode());
		result = prime * result + ((jurisdiction_of_issuer == null) ? 0 : jurisdiction_of_issuer.hashCode());
		result = prime * result
				+ ((method_to_provide_collateral == null) ? 0 : method_to_provide_collateral.hashCode());
		result = prime * result + ((price_currency == null) ? 0 : price_currency.hashCode());
		result = prime * result + ((price_per_unit == null) ? 0 : price_per_unit.hashCode());
		result = prime * result + ((reused_collateral_currency == null) ? 0 : reused_collateral_currency.hashCode());
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
		Collateral other = (Collateral) obj;
		if (Availability_for_collateral_reuse != other.Availability_for_collateral_reuse)
			return false;
		if (Classification_of_security_used_as_collateral == null) {
			if (other.Classification_of_security_used_as_collateral != null)
				return false;
		} else if (!Classification_of_security_used_as_collateral
				.equals(other.Classification_of_security_used_as_collateral))
			return false;
		if (Estimated_reuse_of_collateral == null) {
			if (other.Estimated_reuse_of_collateral != null)
				return false;
		} else if (!Estimated_reuse_of_collateral.equals(other.Estimated_reuse_of_collateral))
			return false;
		if (Identification_of_security_as_collateral == null) {
			if (other.Identification_of_security_as_collateral != null)
				return false;
		} else if (!Identification_of_security_as_collateral.equals(other.Identification_of_security_as_collateral))
			return false;
		if (LEI_of_issuer == null) {
			if (other.LEI_of_issuer != null)
				return false;
		} else if (!LEI_of_issuer.equals(other.LEI_of_issuer))
			return false;
		if (Market_value_of_funding_sources == null) {
			if (other.Market_value_of_funding_sources != null)
				return false;
		} else if (!Market_value_of_funding_sources.equals(other.Market_value_of_funding_sources))
			return false;
		if (Maturity_date_of_the_security == null) {
			if (other.Maturity_date_of_the_security != null)
				return false;
		} else if (!Maturity_date_of_the_security.equals(other.Maturity_date_of_the_security))
			return false;
		if (Value_of_reused_collateral == null) {
			if (other.Value_of_reused_collateral != null)
				return false;
		} else if (!Value_of_reused_collateral.equals(other.Value_of_reused_collateral))
			return false;
		if (collateral_Id != other.collateral_Id)
			return false;
		if (collateral_component == null) {
			if (other.collateral_component != null)
				return false;
		} else if (!collateral_component.equals(other.collateral_component))
			return false;
		if (collateral_market_value == null) {
			if (other.collateral_market_value != null)
				return false;
		} else if (!collateral_market_value.equals(other.collateral_market_value))
			return false;
		if (collateral_quality == null) {
			if (other.collateral_quality != null)
				return false;
		} else if (!collateral_quality.equals(other.collateral_quality))
			return false;
		if (collateral_quantity_or_nom_amount == null) {
			if (other.collateral_quantity_or_nom_amount != null)
				return false;
		} else if (!collateral_quantity_or_nom_amount.equals(other.collateral_quantity_or_nom_amount))
			return false;
		if (collateral_type == null) {
			if (other.collateral_type != null)
				return false;
		} else if (!collateral_type.equals(other.collateral_type))
			return false;
		if (currency_collateral_nom_amount == null) {
			if (other.currency_collateral_nom_amount != null)
				return false;
		} else if (!currency_collateral_nom_amount.equals(other.currency_collateral_nom_amount))
			return false;
		if (funding_source == null) {
			if (other.funding_source != null)
				return false;
		} else if (!funding_source.equals(other.funding_source))
			return false;
		if (funding_source_currency == null) {
			if (other.funding_source_currency != null)
				return false;
		} else if (!funding_source_currency.equals(other.funding_source_currency))
			return false;
		if (haircut_or_margin == null) {
			if (other.haircut_or_margin != null)
				return false;
		} else if (!haircut_or_margin.equals(other.haircut_or_margin))
			return false;
		if (jurisdiction_of_issuer == null) {
			if (other.jurisdiction_of_issuer != null)
				return false;
		} else if (!jurisdiction_of_issuer.equals(other.jurisdiction_of_issuer))
			return false;
		if (method_to_provide_collateral == null) {
			if (other.method_to_provide_collateral != null)
				return false;
		} else if (!method_to_provide_collateral.equals(other.method_to_provide_collateral))
			return false;
		if (price_currency == null) {
			if (other.price_currency != null)
				return false;
		} else if (!price_currency.equals(other.price_currency))
			return false;
		if (price_per_unit == null) {
			if (other.price_per_unit != null)
				return false;
		} else if (!price_per_unit.equals(other.price_per_unit))
			return false;
		if (reused_collateral_currency == null) {
			if (other.reused_collateral_currency != null)
				return false;
		} else if (!reused_collateral_currency.equals(other.reused_collateral_currency))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Collateral [collateral_Id=" + collateral_Id + ", LEI_of_issuer=" + LEI_of_issuer
				+ ", jurisdiction_of_issuer=" + jurisdiction_of_issuer + ", reused_collateral_currency="
				+ reused_collateral_currency + ", funding_source_currency=" + funding_source_currency
				+ ", funding_source=" + funding_source + ", price_currency=" + price_currency
				+ ", currency_collateral_nom_amount=" + currency_collateral_nom_amount + ", collateral_component="
				+ collateral_component + ", collateral_quality=" + collateral_quality + ", collateral_type="
				+ collateral_type + ", method_to_provide_collateral=" + method_to_provide_collateral
				+ ", collateral_market_value=" + collateral_market_value + ", price_per_unit=" + price_per_unit
				+ ", collateral_quantity_or_nom_amount=" + collateral_quantity_or_nom_amount + ", haircut_or_margin="
				+ haircut_or_margin + ", Market_value_of_funding_sources=" + Market_value_of_funding_sources
				+ ", Estimated_reuse_of_collateral=" + Estimated_reuse_of_collateral + ", Value_of_reused_collateral="
				+ Value_of_reused_collateral + ", Maturity_date_of_the_security=" + Maturity_date_of_the_security
				+ ", Classification_of_security_used_as_collateral=" + Classification_of_security_used_as_collateral
				+ ", Identification_of_security_as_collateral=" + Identification_of_security_as_collateral
				+ ", Availability_for_collateral_reuse=" + Availability_for_collateral_reuse + "]";
	}
	
}
