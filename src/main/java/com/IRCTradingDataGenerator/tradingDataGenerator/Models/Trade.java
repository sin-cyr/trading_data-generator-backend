package com.IRCTradingDataGenerator.tradingDataGenerator.Models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "trade")
public class Trade {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int trade_Id;
	
	@Column
	private String trade_type;
	
	@Column
	private String trade_quality;
	
	@Column
	private String trade_level;
	
	@Column
	private String action_type;

	@Column
	private String reporting_timestamp;
	
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "trade_transaction", referencedColumnName = "transaction_id")
	private Transaction trade_transaction;
	
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "trade_counterparty", referencedColumnName = "counterparty_id")
	private Counterparty trade_counterparty;
	
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "trade_collateral", referencedColumnName = "collateral_id")
	private Collateral trade_collateral;
	
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "trade_principle", referencedColumnName = "principle_id")
	private Principle trade_principle;

	public Trade() {
		super();
	}

	public Trade(String trade_type, String trade_quality, String trade_level, String action_type,
				 String reporting_timestamp, Transaction trade_transaction, Counterparty trade_counterparty,
				 Collateral trade_collateral, Principle trade_principle) {
		this.trade_type = trade_type;
		this.trade_quality = trade_quality;
		this.trade_level = trade_level;
		this.action_type = action_type;
		this.reporting_timestamp = reporting_timestamp;
		this.trade_transaction = trade_transaction;
		this.trade_counterparty = trade_counterparty;
		this.trade_collateral = trade_collateral;
		this.trade_principle = trade_principle;
	}

	public int getTrade_Id() {
		return trade_Id;
	}

	public void setTrade_Id(int trade_Id) {
		this.trade_Id = trade_Id;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getTrade_quality() {
		return trade_quality;
	}

	public void setTrade_quality(String trade_quality) {
		this.trade_quality = trade_quality;
	}

	public String getTrade_level() {
		return trade_level;
	}

	public void setTrade_level(String trade_level) {
		this.trade_level = trade_level;
	}

	public String getAction_type() {
		return action_type;
	}

	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}

	public String getReporting_timestamp() {
		return reporting_timestamp;
	}

	public void setReporting_timestamp(String reporting_timestamp) {
		this.reporting_timestamp = reporting_timestamp;
	}

	public Transaction getTrade_transaction() {
		return trade_transaction;
	}

	public void setTrade_transaction(Transaction trade_transaction) {
		this.trade_transaction = trade_transaction;
	}

	public Counterparty getTrade_counterparty() {
		return trade_counterparty;
	}

	public void setTrade_counterparty(Counterparty trade_counterparty) {
		this.trade_counterparty = trade_counterparty;
	}

	public Collateral getTrade_collateral() {
		return trade_collateral;
	}

	public void setTrade_collateral(Collateral trade_collateral) {
		this.trade_collateral = trade_collateral;
	}

	public Principle getTrade_principle() {
		return trade_principle;
	}

	public void setTrade_principle(Principle trade_principle) {
		this.trade_principle = trade_principle;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Trade trade = (Trade) o;
		return trade_Id == trade.trade_Id &&
				Objects.equals(trade_type, trade.trade_type) &&
				Objects.equals(trade_quality, trade.trade_quality) &&
				Objects.equals(trade_level, trade.trade_level) &&
				Objects.equals(action_type, trade.action_type) &&
				Objects.equals(reporting_timestamp, trade.reporting_timestamp) &&
				Objects.equals(trade_transaction, trade.trade_transaction) &&
				Objects.equals(trade_counterparty, trade.trade_counterparty) &&
				Objects.equals(trade_collateral, trade.trade_collateral) &&
				Objects.equals(trade_principle, trade.trade_principle);
	}

	@Override
	public int hashCode() {
		return Objects.hash(trade_Id, trade_type, trade_quality, trade_level, action_type, reporting_timestamp, trade_transaction, trade_counterparty, trade_collateral, trade_principle);
	}

	@Override
	public String toString() {
		return "Trade{" +
				"trade_Id=" + trade_Id +
				", trade_type='" + trade_type + '\'' +
				", trade_quality='" + trade_quality + '\'' +
				", trade_level='" + trade_level + '\'' +
				", action_type='" + action_type + '\'' +
				", reporting_timestamp='" + reporting_timestamp + '\'' +
				", trade_transaction=" + trade_transaction +
				", trade_counterparty=" + trade_counterparty +
				", trade_collateral=" + trade_collateral +
				", trade_principle=" + trade_principle +
				'}';
	}
}
