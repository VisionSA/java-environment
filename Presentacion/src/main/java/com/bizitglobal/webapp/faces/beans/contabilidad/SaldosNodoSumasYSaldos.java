package com.bizitglobal.webapp.faces.beans.contabilidad;

import java.util.Enumeration;
import java.util.Vector;

import com.bizitglobal.tarjetafiel.contabilidad.negocio.SumasYSaldos;

import zeus.util.TreeNode;


public class SaldosNodoSumasYSaldos {

	SaldosNodoSumasYSaldos salNodo = null; // una referencia a si misma
	double debeInicial = 0;
	double debePeriodo = 0;
	double haberInicial = 0;
	double haberPeriodo = 0;

	TreeNode nodo;


	public SaldosNodoSumasYSaldos(TreeNode nodo) {
		this.nodo = nodo;
	}


	public void getSaldo() {
		Vector hijos = nodo.getChildren();
		for (int i = 0; i < hijos.size(); i++) {
			TreeNode hijo = (TreeNode) hijos.get(i);
			SumasYSaldos sumasYSaldos = (SumasYSaldos) hijo.getValue();
			if (hijo.isTerminal()) {
				debeInicial += sumasYSaldos.getDebeInicial().doubleValue();
				haberInicial += sumasYSaldos.getHaberInicial().doubleValue();
				debePeriodo += sumasYSaldos.getDebePeriodo().doubleValue();
				haberPeriodo += sumasYSaldos.getHaberPeriodo().doubleValue();
			} else {
				salNodo = new SaldosNodoSumasYSaldos(hijo);
				salNodo.getSaldo();
				// recorremos todos los hijos del nodo actual par determinar si va a ser mostrado o no en el reporte el nodo actual
				Enumeration sumasYSaldosEnumeration = hijo.values();
				while (sumasYSaldosEnumeration.hasMoreElements()) {
					SumasYSaldos element = (SumasYSaldos) sumasYSaldosEnumeration.nextElement();
					if (element.isMostrar()) {
						sumasYSaldos.setMostrar(true);
						break;
					}
				}
				this.setDebeInicial(salNodo.getDebeInicial() + debeInicial);
				this.setHaberInicial(salNodo.getHaberInicial() + haberInicial);
				this.setDebePeriodo(salNodo.getDebePeriodo() + debePeriodo);
				this.setHaberPeriodo(salNodo.getHaberPeriodo() + haberPeriodo);

			}
		}

		SumasYSaldos sumasYSaldosTotal = ((SumasYSaldos) nodo.getValue());
		sumasYSaldosTotal.setDebeInicial((new Double(this.debeInicial)));
		sumasYSaldosTotal.setHaberInicial((new Double(this.haberInicial)));
		sumasYSaldosTotal.setDebePeriodo((new Double(this.debePeriodo)));
		sumasYSaldosTotal.setHaberPeriodo((new Double(this.haberPeriodo)));
		nodo.setValue(sumasYSaldosTotal);

	}


	public TreeNode getNodo() {
		return nodo;
	}


	public void setNodo(TreeNode nodo) {
		this.nodo = nodo;
	}


	public double getDebeInicial() {
		return debeInicial;
	}


	public void setDebeInicial(double debeInicial) {
		this.debeInicial = debeInicial;
	}


	public double getDebePeriodo() {
		return debePeriodo;
	}


	public void setDebePeriodo(double debePeriodo) {
		this.debePeriodo = debePeriodo;
	}


	public double getHaberInicial() {
		return haberInicial;
	}


	public void setHaberInicial(double haberInicial) {
		this.haberInicial = haberInicial;
	}


	public double getHaberPeriodo() {
		return haberPeriodo;
	}


	public void setHaberPeriodo(double haberPeriodo) {
		this.haberPeriodo = haberPeriodo;
	}

}
