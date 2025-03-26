package com.bizitglobal.webapp.faces.beans.contabilidad;

import java.util.Enumeration;
import java.util.Vector;

import com.bizitglobal.tarjetafiel.contabilidad.negocio.Balance;

import zeus.util.TreeNode;


public class SaldosNodo {

	SaldosNodo salNodo = null; // una referencia a si misma
	double saldoInicial = 0;
	double saldoPeriodo = 0;
	TreeNode nodo;


	public SaldosNodo(TreeNode nodo) {
		this.nodo = nodo;
	}


	public void getSaldo() {
		Vector hijos = nodo.getChildren();
		for (int i = 0; i < hijos.size(); i++) {
			TreeNode hijo = (TreeNode) hijos.get(i);
			Balance balance = (Balance) hijo.getValue();
			if (hijo.isTerminal()) {
				saldoInicial += balance.getSaldoInicial().doubleValue();
				saldoPeriodo += balance.getSaldoPeriodo().doubleValue();
				// System.out.println("soy el nodo "+ balance.getTitulo()+" y mi saldo inicial es "+ balance.getSaldoInicial() );
				// System.out.println("saldo iniicial acumul hasta ahora es.... " + saldoInicial);
			} else {
				salNodo = new SaldosNodo(hijo);
				salNodo.getSaldo();
				// recorremos todos los hijos del nodo actual par determinar si va a ser mostrado o no en el reporte el nodo actual
				Enumeration balances = hijo.values();
				while (balances.hasMoreElements()) {
					Balance element = (Balance) balances.nextElement();
					if (element.isMostrar()) {
						balance.setMostrar(true);
						break;
					}
				}
				this.setSaldoInicial(salNodo.getSaldoInicial() + saldoInicial);
				this.setSaldoPeriodo(salNodo.getSaldoPeriodo() + saldoPeriodo);

			}
		}

		Balance balanceTotal = ((Balance) nodo.getValue());
		balanceTotal.setSaldoInicial((new Double(this.saldoInicial)));
		balanceTotal.setSaldoPeriodo((new Double(this.saldoPeriodo)));
		nodo.setValue(balanceTotal);

	}


	public TreeNode getNodo() {
		return nodo;
	}


	public void setNodo(TreeNode nodo) {
		this.nodo = nodo;
	}


	public double getSaldoInicial() {
		return saldoInicial;
	}


	public void setSaldoInicial(double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}


	public double getSaldoPeriodo() {
		return saldoPeriodo;
	}


	public void setSaldoPeriodo(double saldoPeriodo) {
		this.saldoPeriodo = saldoPeriodo;
	}

}
