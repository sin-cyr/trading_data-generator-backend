package com.IRCTradingDataGenerator.tradingDataGenerator.Models;

import java.util.Objects;

public class TradeRequest {
	
	private String tradeType;

	private int quantity;

	private String actionType;

	private int percentage;

	public TradeRequest() {
		super();
	}

	public TradeRequest(String tradeType) {
		super();
		this.tradeType = tradeType;
	}

	public TradeRequest(String tradeType, int quantity) {
		super();
		this.tradeType = tradeType;
		this.quantity = quantity;
	}

	public TradeRequest(String tradeType, int quantity, String actionType) {
		super();
		this.tradeType = tradeType;
		this.quantity = quantity;
		this.actionType = actionType;
	}

	public TradeRequest(String tradeType, int quantity, String actionType, int percentage) {
		this.tradeType = tradeType;
		this.quantity = quantity;
		this.actionType = actionType;
		this.percentage = percentage;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getActionType() { return actionType; }

	public void setActionType(String actionType) { this.actionType = actionType; }

	public int getPercentage() { return percentage; }

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TradeRequest that = (TradeRequest) o;
		return quantity == that.quantity &&
				percentage == that.percentage &&
				Objects.equals(tradeType, that.tradeType) &&
				Objects.equals(actionType, that.actionType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tradeType, quantity, actionType, percentage);
	}

	@Override
	public String toString() {
		return "TradeRequest{" +
				"tradeType='" + tradeType + '\'' +
				", quantity=" + quantity +
				", actionType='" + actionType + '\'' +
				", percentage=" + percentage +
				'}';
	}
}
